package com.swu.lease.web.admin.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swu.lease.common.result.Result;
import com.swu.lease.model.entity.UserInfo;
import com.swu.lease.model.enums.BaseStatus;
import com.swu.lease.web.admin.service.UserInfoService;
import com.swu.lease.web.admin.vo.user.UserInfoQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "移动端用户信息管理")
@RequestMapping("admin/user")
@Controller
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Operation(summary = "分页查询移动端用户信息")
    @GetMapping("/page")
    public Result<IPage<UserInfo>> pageUserInfo(@RequestParam long current, @RequestParam long size,UserInfo userInfo) {
        Page<UserInfo> userInfoPage = new Page<>(current, size);
        LambdaQueryWrapper<UserInfo> userInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userInfoLambdaQueryWrapper.like(userInfo.getPhone()!=null,UserInfo::getPhone,userInfo.getPhone());
        userInfoLambdaQueryWrapper.eq(userInfo.getStatus()!=null,UserInfo::getStatus,userInfo.getStatus());
        IPage<UserInfo>result = userInfoService.page(userInfoPage, userInfoLambdaQueryWrapper);
        return Result.ok(result);
    }
    @Operation(summary = "根据id改变用户状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<UserInfo> userInfoLambdaUpdateWrapper = new LambdaUpdateWrapper<UserInfo>();
        userInfoLambdaUpdateWrapper.eq(UserInfo::getId,id);
        userInfoLambdaUpdateWrapper.set(UserInfo::getStatus,status);
        userInfoService.update(userInfoLambdaUpdateWrapper);
        return Result.ok();
    }
}
