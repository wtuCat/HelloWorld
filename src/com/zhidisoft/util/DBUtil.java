package com.zhidisoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
	private static final String URL="jdbc:mysql://127.0.0.1:3306/db_tax_source";
	private static final String USER="root";
	private static final String PASSWORD="root";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//�ر���
	private static void close(ResultSet set,PreparedStatement statement, Connection connection) throws SQLException{
		if(set!=null){
			set.close();
		}
		if(statement!=null){
			statement.close();
		}
		if(connection!=null){
			connection.close();	
		}
	}
	//��������
	private static Connection getConnection(){
		 Connection conn=null;
		try {
			 conn=DriverManager.getConnection(URL, USER, PASSWORD);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	 
	//��ɾ�ĵķ���
	public static Integer update(String sql){
		return update(sql,null);
	}
	
	public static Integer update(String sql,Object[] obj){
		Connection conn=getConnection();
		int rows=0;
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			if(obj!=null){
				//��ռλ����ֵ
				int len=obj.length;
				for(int i=0;i<len;i++){
					statement.setObject(i+1, obj[i]);
				}
			}
			//ִ�з��ؽ��
			 rows=statement.executeUpdate();
			 close(null, statement, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rows>0){
			return rows;
		}
		return null;
		
	}
	
	//��ѯ����
	public static List<Map<String,String>> query(String sql){
		return query(sql,null);
	}
	
	public static List<Map<String,String>> query(String sql,Object[] obj){
		Connection conn=getConnection();
		List<Map<String,String>> list=new ArrayList<>();
		try {
			PreparedStatement statement=conn.prepareStatement(sql);
			if(obj!=null){
				int len=obj.length;
				for(int i=0;i<len;i++){
					statement.setObject(i+1, obj[i]);
				}
			}
			ResultSet set=statement.executeQuery();
			//��ò�ѯ������Ϣ�ṹ
			ResultSetMetaData metadata=set.getMetaData();
			//��ȡ�е�����
			int count=metadata.getColumnCount();
			while(set.next()){
				HashMap<String , String > map=new HashMap<>();
				for(int i=0;i<count;i++){
					String columnName=metadata.getColumnName(i+1);
					map.put(columnName, set.getString(columnName));
				}
				list.add(map);
			}
			close(set, statement, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
