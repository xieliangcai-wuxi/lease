package com.swu.lease.web.admin.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swu.lease.common.result.Result;
import com.swu.lease.model.entity.SystemUser;
import com.swu.lease.model.enums.BaseStatus;
import com.swu.lease.web.admin.service.SystemUserService;
import com.swu.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.swu.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {
    @Autowired
    private  SystemUserService systemUserService;
    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<IPage<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {
        Page<SystemUserItemVo> systemUserItemVoPage = new Page<>(current, size);
        IPage<SystemUserItemVo> result = systemUserService.getSystemUserItemVoByPage(systemUserItemVoPage,queryVo);
        return Result.ok(result);
    }

    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        SystemUserItemVo result = systemUserService.getSystemUserItemVoById(id);
        return Result.ok(result);
    }

    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemUser systemUser) {
        if(systemUser.getPassword()!=null){
            systemUser.setPassword(org.apache.commons.codec.digest.DigestUtils.md5Hex(systemUser.getPassword()));

        }
        systemUserService.saveOrUpdate(systemUser);
        return Result.ok();
    }

    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        LambdaQueryWrapper<SystemUser> systemUserLambdaQueryWrapper = new LambdaQueryWrapper<SystemUser>();
        systemUserLambdaQueryWrapper.eq(SystemUser::getUsername,username);
        long count = systemUserService.count(systemUserLambdaQueryWrapper);
        if(count>0){
            return Result.ok(false);
        }else {
            return Result.ok(true);
        }
    }

    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result removeById(@RequestParam Long id) {
        systemUserService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<SystemUser> systemUserLambdaUpdateWrapper = new LambdaUpdateWrapper<SystemUser>();
        systemUserLambdaUpdateWrapper.eq(SystemUser::getId,id);
        systemUserLambdaUpdateWrapper.set(SystemUser::getStatus,status);
        systemUserService.update(systemUserLambdaUpdateWrapper);
        return Result.ok();
    }
}
