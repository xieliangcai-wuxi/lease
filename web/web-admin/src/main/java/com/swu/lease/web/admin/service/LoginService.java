package com.swu.lease.web.admin.service;

import com.swu.lease.web.admin.vo.login.CaptchaVo;
import com.swu.lease.web.admin.vo.login.LoginVo;
import com.swu.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);

    SystemUserInfoVo getLoginUserInfo(Long userId);

    SystemUserInfoVo getUserInfoById(Long userId);
}
