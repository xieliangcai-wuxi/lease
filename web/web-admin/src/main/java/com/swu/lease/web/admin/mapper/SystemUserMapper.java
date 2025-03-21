package com.swu.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swu.lease.model.entity.SystemUser;
import com.swu.lease.web.admin.vo.login.LoginVo;
import com.swu.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.swu.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【system_user(员工信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.swu.lease.model.SystemUser
*/
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    IPage<SystemUserItemVo> getSystemUserItemVoByPage(Page<SystemUserItemVo> systemUserItemVoPage, SystemUserQueryVo queryVo);


    SystemUser selectOneByUserName(String username);
}




