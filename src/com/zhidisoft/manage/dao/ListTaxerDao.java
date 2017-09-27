package com.zhidisoft.manage.dao;


import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;
	/**
	 * ����רԱ��ҳdao
	 * 
	 * */
public class ListTaxerDao {
	//��ȡ�ܼ�¼��
	public int getCount(String taxerName){
		String sql="select count(*) c from tb_taxer where state=0";
		
		if(taxerName.length()>0){
			sql=sql+" and taxerName like '%"+taxerName+"%'";
		}
		return Integer.valueOf(DBUtil.query(sql).get(0).get("c"));
	}
	
	//��ѯ��˰רԱ
	public List<Map<String, String>> getPage(int pageNumber, int pageSize, String taxerName) {
		StringBuilder sb = new StringBuilder();
		//sql���
		sb.append("select * from tb_taxer where state=0");
		//����в�ѯ������ƴ���ں���
		if (taxerName != null && taxerName.length() >0) {
			//��˰רԱ������ģ����ѯ
			sb.append(" and taxerName like '%"+taxerName+"%'");
		}
		//ƴ��limit�Ӿ�
		sb.append(" limit ?,?");
		//ռλ��������
		Object[] obj={(pageNumber-1)*pageSize,pageSize};
		List<Map<String, String>> list = DBUtil.query(sb.toString(),obj);
		return list;
	}
}
