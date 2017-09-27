package com.zhidisoft.manage.dao;

import com.zhidisoft.manage.entity.Payer;
import com.zhidisoft.util.DBUtil;

/**
 * 添加纳税人的dao
 * @author Administrator
 *
 */
public class AddTaxPayerDao {
	//添加纳税人的方法
	public Integer addTaxPayer(Payer payer){
		String sql="insert into tb_tax_payer(payerCode,payerName,bizAddress,bizAddressPhone,taxOrganId,industryId,bizScope,invoiceType,legalPerson,legalIdCard,finaceName,finaceIdCard,userId,legalIdCardImageURL,finaceIdCardImageURL) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		Object[] obj={payer.getPayerCode(),payer.getPayerName(),payer.getBizAddress(),payer.getBizAddressPhone(),payer.getTaxOrganId(),payer.getIndustryId(),payer.getBizScope(),payer.getInvoiceType(),payer.getLegalPerson(),payer.getLegalIdCard(),payer.getFinaceName(),payer.getFinaceIdCard(),payer.getUserId(),payer.getLegalIdCardImageURL(),payer.getFinaceIdCardImageURL()};
		Integer rows=DBUtil.update(sql, obj);
		return rows;
	}
}
