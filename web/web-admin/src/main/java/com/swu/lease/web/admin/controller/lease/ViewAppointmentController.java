package com.swu.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swu.lease.common.result.Result;
import com.swu.lease.model.entity.ViewAppointment;
import com.swu.lease.model.enums.AppointmentStatus;
import com.swu.lease.web.admin.service.ViewAppointmentService;
import com.swu.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.swu.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "预约看房管理")
@RequestMapping("/admin/appointment")
@RestController
public class ViewAppointmentController {
    @Autowired
    private ViewAppointmentService viewAppointmentService;
    @Operation(summary = "分页查询预约信息")
    @GetMapping("page")
    public Result<IPage<AppointmentVo>> page(@RequestParam long current, @RequestParam long size, AppointmentQueryVo queryVo) {
        Page<AppointmentVo> appointmentVoPage = new Page<>(current, size);
        IPage<AppointmentVo> result = viewAppointmentService.selectAppointmentVoBypage(appointmentVoPage,queryVo);
        return Result.ok(result);
    }

    @Operation(summary = "根据id更新预约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> viewAppointmentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        viewAppointmentLambdaUpdateWrapper.eq(ViewAppointment::getId, id);
        viewAppointmentLambdaUpdateWrapper.set(ViewAppointment::getAppointmentStatus, status);
        viewAppointmentService.update(viewAppointmentLambdaUpdateWrapper);
        return Result.ok();
    }

}
