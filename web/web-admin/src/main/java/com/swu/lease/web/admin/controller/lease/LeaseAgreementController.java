package com.swu.lease.web.admin.controller.lease;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swu.lease.common.result.Result;
import com.swu.lease.model.entity.LeaseAgreement;
import com.swu.lease.model.enums.LeaseStatus;
import com.swu.lease.web.admin.service.LeaseAgreementService;
import com.swu.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.swu.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "租约管理")
@RestController
@RequestMapping("/admin/agreement")
public class LeaseAgreementController {
    @Autowired
    private LeaseAgreementService leaseAgreementService;
    @Operation(summary = "保存或修改租约信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody LeaseAgreement leaseAgreement) {
        leaseAgreementService.saveOrUpdate(leaseAgreement);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询租约列表")
    @GetMapping("page")
    public Result<IPage<AgreementVo>> page(@RequestParam long current, @RequestParam long size, AgreementQueryVo queryVo) {
        IPage<AgreementVo> page = new Page<>(current, size);
        IPage<AgreementVo> list = leaseAgreementService.pageAgreementByQuery(page, queryVo);
        return Result.ok(list);

    }

    @Operation(summary = "根据id查询租约信息")
    @GetMapping(name = "getById")
    public Result<AgreementVo> getById(@RequestParam Long id) {
        AgreementVo result = leaseAgreementService.getAgreementVoById(id);
        return Result.ok(result);
    }

    @Operation(summary = "根据id删除租约信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        leaseAgreementService.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id更新租约状态")
    @PostMapping("updateStatusById")
    public Result updateStatusById(@RequestParam Long id, @RequestParam LeaseStatus status) {
        LambdaUpdateWrapper<LeaseAgreement> leaseAgreementLambdaUpdateWrapper = new LambdaUpdateWrapper<LeaseAgreement>();
        leaseAgreementLambdaUpdateWrapper.eq(LeaseAgreement::getId, id);
        leaseAgreementLambdaUpdateWrapper.set(LeaseAgreement::getStatus, status);
        leaseAgreementService.update(leaseAgreementLambdaUpdateWrapper);

        return Result.ok();
    }

}

