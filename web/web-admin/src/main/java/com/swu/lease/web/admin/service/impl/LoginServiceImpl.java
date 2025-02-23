package com.swu.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.swu.lease.common.ExceptionHandle.LeaseException;
import com.swu.lease.common.constant.RedisConstant;
import com.swu.lease.common.result.ResultCodeEnum;
import com.swu.lease.model.entity.SystemUser;
import com.swu.lease.model.enums.BaseStatus;
import com.swu.lease.web.admin.mapper.SystemPostMapper;
import com.swu.lease.web.admin.mapper.SystemUserMapper;
import com.swu.lease.web.admin.mapper.UserInfoMapper;
import com.swu.lease.web.admin.service.LoginService;
import com.swu.lease.web.admin.vo.login.CaptchaVo;
import com.swu.lease.web.admin.vo.login.LoginVo;
import com.swu.lease.web.admin.vo.system.user.SystemUserInfoVo;
import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import utils.JwtUtils;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Override
    public CaptchaVo getCaptcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 2);
        String code = specCaptcha.text().toLowerCase();
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        stringRedisTemplate.opsForValue().set(key,code,RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        return new CaptchaVo(specCaptcha.toBase64(),key);
    }

    @Override
    public String login(LoginVo loginVo) {
        String code = stringRedisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if(loginVo.getCaptchaCode()==null){
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }
        if (code != null && !code.equals(loginVo.getCaptchaCode().toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }
        SystemUser systemUser = systemUserMapper.selectOneByUserName(loginVo.getUsername());
        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        if (systemUser.getStatus() == BaseStatus.DISABLE){
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }
        if (systemUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginVo.getPassword().getBytes()))){
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }
        return JwtUtils.createToken(systemUser.getId(), systemUser.getUsername());
    }

    @Override
    public SystemUserInfoVo getLoginUserInfo(Long userId) {
        SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
        SystemUser systemUser = systemUserMapper.selectById(userId);
        systemUserInfoVo.setName(systemUser.getUsername());
        systemUserInfoVo.setAvatarUrl(systemUser.getAvatarUrl());
        return systemUserInfoVo;
    }
}
