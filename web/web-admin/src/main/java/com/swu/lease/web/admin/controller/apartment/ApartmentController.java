package com.swu.lease.web.admin.controller.apartment;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swu.lease.common.result.Result;
import com.swu.lease.model.entity.ApartmentFacility;
import com.swu.lease.model.entity.ApartmentInfo;
import com.swu.lease.model.enums.ReleaseStatus;
import com.swu.lease.web.admin.service.ApartmentFacilityService;
import com.swu.lease.web.admin.service.ApartmentInfoService;
import com.swu.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.swu.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.swu.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.swu.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "公寓信息管理")
@RestController
@RequestMapping("/admin/apartment")
public class ApartmentController {
    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Operation(summary = "保存或更新公寓信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ApartmentSubmitVo apartmentSubmitVo) {
        apartmentInfoService.saveOrUpdateApartmentSubmitVo(apartmentSubmitVo);
        return Result.ok();
    }

    @Operation(summary = "根据条件分页查询公寓列表")
    @GetMapping("pageItem")
    public Result<IPage<ApartmentItemVo>> pageItem(@RequestParam long current, @RequestParam long size, ApartmentQueryVo queryVo) {

        IPage<ApartmentItemVo> page = new Page<>(current, size);
        IPage<ApartmentItemVo> list = apartmentInfoService.pageApartmentItemByQuery(page, queryVo);
        return Result.ok(list);
    }

    @Operation(summary = "根据ID获取公寓详细信息")
    @GetMapping("getDetailById")
    public Result<ApartmentDetailVo> getDetailById(@RequestParam Long id) {
        ApartmentDetailVo apartmentDetailVo = apartmentInfoService.getDetailById(id);

        return Result.ok(apartmentDetailVo);
    }

    @Operation(summary = "根据id删除公寓信息")
    @DeleteMapping("removeById")
    public Result removeById(@RequestParam Long id) {
        apartmentInfoService.removeApartmentById(id);
        return Result.ok();
    }

    @Operation(summary = "根据id修改公寓发布状态")
    @PostMapping("updateReleaseStatusById")
    public Result updateReleaseStatusById(@RequestParam Long id, @RequestParam ReleaseStatus status) {
        return Result.ok();
    }

    @Operation(summary = "根据区县id查询公寓信息列表")
    @GetMapping("listInfoByDistrictId")
    public Result<List<ApartmentInfo>> listInfoByDistrictId(@RequestParam Long id) {
        return Result.ok();
    }
}














