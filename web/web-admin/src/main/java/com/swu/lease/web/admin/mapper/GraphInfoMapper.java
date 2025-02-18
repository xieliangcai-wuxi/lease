package com.swu.lease.web.admin.mapper;

import com.swu.lease.model.entity.GraphInfo;
import com.swu.lease.model.enums.ItemType;
import com.swu.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.swu.lease.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> getUrlById(Integer apartmentType, Long id);
}




