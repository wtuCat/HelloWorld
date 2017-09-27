package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * 查询任务信息
 * @author Administrator
 *
 */
public class SearchTaskDao {
	//获得总记录数
	public int getCount(String payerCode,String payerName,String organId,String industryId,String startDate,String endDate){
		String sql="select count(*) c from tb_tax_source s join tb_tax_payer p join tb_tax_organ o join tb_industry i join tb_user u on s.payerId=p.id and s.subOrganId=o.id and p.industryId=i.id and s.recordUserId=u.id where s.removeState=0";
		//按纳税人识别号查询
		if(payerCode!=null&&payerCode.length()>0){
			sql=sql+" and p.payerCode="+payerCode;
		}
		//按纳税人名称查询
		if(payerName!=null&&payerName.length()>0){
			sql=sql+" and p.payerName like '%"+payerName+"%'";
		}
		//按税务机关查询
		if(organId!=null&&organId.length()>0&&Integer.valueOf(organId)>0){
			sql=sql+" and o.id="+organId;
		}
		//按行业查询
		if(industryId!=null&&industryId.length()>0&&Integer.valueOf(industryId)>0){
			sql=sql+" and i.id="+industryId;
		}
		//按日期查询
		if(startDate!=null&&endDate!=null){
			sql=sql+" and s.executeTime between '"+startDate+"' and '"+endDate+"'";
		}
		return Integer.valueOf(DBUtil.query(sql).get(0).get("c"));
	}
	//分页查询
	public List<Map<String,String>> searchTask(String payerCode,String payerName,String organId,String industryId,String startDate,String endDate,Integer pageNum,Integer pageSize){
		String sql="select * from tb_tax_source s join tb_tax_payer p join tb_tax_organ o join tb_industry i join tb_user u on s.payerId=p.id and s.subOrganId=o.id and p.industryId=i.id and s.recordUserId=u.id where s.removeState=0";
		//按纳税人识别号查询
		if(payerCode!=null&&payerCode.length()>0){
			sql=sql+" and p.payerCode="+payerCode;
		}
		//按纳税人名称查询
		if(payerName!=null&&payerName.length()>0){
			sql=sql+" and p.payerName like '%"+payerName+"%'";
		}
		//按税务机关查询
		if(organId!=null&&organId.length()>0&&Integer.valueOf(organId)>0){
			sql=sql+" and o.id="+organId;
		}
		//按行业查询
		if(industryId!=null&&industryId.length()>0&&Integer.valueOf(industryId)>0){
			sql=sql+" and i.id="+industryId;
		}
		//按日期查询
		if(startDate!=null&&endDate!=null){
			sql=sql+" and s.executeTime between '"+startDate+"' and '"+endDate+"'";
		}
		sql=sql+" limit ?,?";
		Object[] obj={(pageNum-1)*pageSize,pageSize};
		
		return DBUtil.query(sql,obj);
	}
	//通过任务表id查询任务详情
	public Map<String,String> getTaskInfo(String id){
		String sql="select * from tb_tax_source s join tb_tax_payer p join tb_tax_organ o join tb_industry i join tb_user u on s.payerId=p.id and s.subOrganId=o.id and p.industryId=i.id and s.recordUserId=u.id where s.removeState=0 and s.id="+id;
		return DBUtil.query(sql).get(0);
	}
}
