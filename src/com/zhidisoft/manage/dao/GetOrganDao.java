package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;
/**
 * ���ڲ�ѯ˰����ص�dao
 * @author Administrator
 *
 */
public class GetOrganDao {
	//��ѯ����˰�����
	public List<Map<String,String>> getOrgan(){
		String sql="select * from tb_tax_organ where state=0";
		List<Map<String,String>> list=DBUtil.query(sql);
		return list;
	}
	
	//����id��ѯ����Ӧ��˰���������
	public Map<String,String> getOrganName(String id){
		String sql="select * from tb_tax_organ where id="+id;
		return DBUtil.query(sql).get(0);
	}
}
