package com.zhidisoft.manage.dao;

import com.zhidisoft.manage.entity.Payer;
import com.zhidisoft.util.DBUtil;

/**
 * �޸Ĳ�������˰����Ϣdao
 * @author Administrator
 *
 */
public class EditTaxPayerDao {
	//�޸���˰����Ϣ
	 public Integer updateTaxPayer(Payer p){
		 String sql="update tb_tax_payer set payerName=?,bizAddress=?,bizAddressPhone=?,taxOrganId=?,industryId=?,bizScope=?,invoiceType=?,legalPerson=?,legalIdCard=?,finaceName=?,finaceIdCard=? where id=?";
		 Object[] obj={p.getPayerName(),p.getBizAddress(),p.getBizAddressPhone(),p.getTaxOrganId(),p.getIndustryId(),p.getBizScope(),p.getInvoiceType(),p.getLegalPerson(),p.getLegalIdCard(),p.getFinaceName(),p.getFinaceIdCard(),p.getId()};
		 return DBUtil.update(sql, obj); 
	 }
}
