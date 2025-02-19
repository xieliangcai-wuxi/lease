package com.swu.lease.web.admin.mapper;

import com.swu.lease.model.entity.LabelInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【label_info(标签信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.swu.lease.model.LabelInfo
*/
public interface LabelInfoMapper extends BaseMapper<LabelInfo> {

    //根据公寓id查询
    List<LabelInfo> selectListById(Long id);

    List<LabelInfo> selectListByRoomId(Long id);
}




