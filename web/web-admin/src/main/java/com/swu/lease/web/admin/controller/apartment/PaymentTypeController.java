package com.swu.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.swu.lease.common.result.Result;
import com.swu.lease.model.entity.PaymentType;
import com.swu.lease.web.admin.service.PaymentTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "支付方式管理")
@RequestMapping("/admin/payment")
@RestController
public class PaymentTypeController {
    @Autowired
    private  PaymentTypeService paymentTypeService;
    @Operation(summary = "查询全部支付方式列表")
    @GetMapping("list")
    public Result<List<PaymentType>> listPaymentType() {
        LambdaQueryWrapper<PaymentType> paymentTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        paymentTypeLambdaQueryWrapper.eq(PaymentType::getIsDeleted, 0);
        List<PaymentType> list = paymentTypeService.list(paymentTypeLambdaQueryWrapper);
        return Result.ok(list);
    }

    @Operation(summary = "保存或更新支付方式")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdatePaymentType(@RequestBody PaymentType paymentType) {
        boolean b = paymentTypeService.saveOrUpdate(paymentType);
        if (b){
            return Result.ok();
        }
        return Result.fail();
    }

    @Operation(summary = "根据ID删除支付方式")
    @DeleteMapping("deleteById")
    public Result deletePaymentById(@RequestParam Long id) {
        boolean b = paymentTypeService.removeById(id);
        if (b){
            return Result.ok();
        }
        return Result.fail();
    }

}















