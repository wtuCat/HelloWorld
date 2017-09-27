package com.zhidisoft.system.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.DBUtil;

public class UserDao {
	public User getUser(String username){
		Object[] obj={username};
		User user=null;
		List<Map<String, String>> list=DBUtil.query("select * from tb_user where username=?", obj);
		if(list!=null){
			Map<String, String> map=list.get(0);
			user=new User();
			try {
				BeanUtils.populate(user, map);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return user;
	}
	//ÐÞ¸ÄÃÜÂë
	public int updatePassword(String newPassword,String username){
		String sql="update tb_user set password=? where username=?";
		Object[] obj={newPassword,username};
		return DBUtil.update(sql,obj);
	}
}
