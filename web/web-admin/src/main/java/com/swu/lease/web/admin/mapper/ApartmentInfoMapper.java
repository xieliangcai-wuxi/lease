package com.swu.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swu.lease.model.entity.ApartmentInfo;
import com.swu.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.swu.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.swu.lease.model.ApartmentInfo
*/
public interface ApartmentInfoMapper extends BaseMapper<ApartmentInfo> {

    IPage<ApartmentItemVo> pageApartmentItemByQuery(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);
}




