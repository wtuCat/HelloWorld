package com.zhidisoft.manage.entity;

public class Industry {
	private String industryName;
	private String recordDate;
	private int recordUserId;
	public Industry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Industry(String industryName, String recordDate, int recordUserId) {
		super();
		this.industryName = industryName;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public int getRecordUserId() {
		return recordUserId;
	}
	public void setRecordUserId(int recordUserId) {
		this.recordUserId = recordUserId;
	}
	
}
