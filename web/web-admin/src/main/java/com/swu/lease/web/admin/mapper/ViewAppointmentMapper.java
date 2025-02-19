package com.swu.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.swu.lease.model.entity.ViewAppointment;
import com.swu.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.swu.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.swu.lease.model.ViewAppointment
*/
public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {


    IPage<AppointmentVo> selectAppointmentVoBypage(Page<AppointmentVo> appointmentVoPage, AppointmentQueryVo queryVo);
}




