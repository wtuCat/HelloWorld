package com.zhidisoft.manage.entity;

public class Source {
	private  String payerId;
	private  String taskName;
	private  String subOrganId;
	private  String approverId;
	private  String executeId;
	private  String executeTime;
	private  String taskFrom;
	private  String taskState;
	private  String idea;
	private  String riskLevel;
	private  String recordDate;
	private  String recordUserId;
	public Source() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Source(String payerId, String taskName, String subOrganId, String approverId, String executeId, String executeTime,
			String taskFrom, String taskState, String idea, String riskLevel, String recordDate, String recordUserId) {
		this.payerId = payerId;
		this.taskName = taskName;
		this.subOrganId = subOrganId;
		this.approverId = approverId;
		this.executeId = executeId;
		this.executeTime = executeTime;
		this.taskFrom = taskFrom;
		this.taskState = taskState;
		this.idea = idea;
		this.riskLevel = riskLevel;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getSubOrganId() {
		return subOrganId;
	}
	public void setSubOrganId(String subOrganId) {
		this.subOrganId = subOrganId;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getExecuteId() {
		return executeId;
	}
	public void setExecuteId(String executeId) {
		this.executeId = executeId;
	}
	public String getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}
	public String getTaskFrom() {
		return taskFrom;
	}
	public void setTaskFrom(String taskFrom) {
		this.taskFrom = taskFrom;
	}
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getRecordUserId() {
		return recordUserId;
	}
	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}
	
}
