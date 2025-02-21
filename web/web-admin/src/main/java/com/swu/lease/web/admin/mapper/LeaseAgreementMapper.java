package com.swu.lease.web.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.swu.lease.model.entity.LeaseAgreement;
import com.swu.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.swu.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Mapper
* @createDate 2023-07-24 15:48:00
* @Entity com.swu.lease.model.LeaseAgreement
*/
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    IPage<AgreementVo> pageAgreementByQuery(IPage<AgreementVo> page, AgreementQueryVo queryVo);
}




