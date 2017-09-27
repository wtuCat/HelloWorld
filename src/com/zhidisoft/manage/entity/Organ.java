package com.zhidisoft.manage.entity;

public class Organ {
	private Integer id;
	private String organName;
	private int parentId;
	private String address;
	private String phone;
	private String faxPhone;
	private String email;
	private int leaderId;
	private String taxTypeCode;
	private int state;
	private String recordDate;
	private int recordUserId;
	public Organ() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Organ(Integer id,String organName, int parentId, String address, String phone, String faxPhone, String email,
			int leaderId, String taxTypeCode, int state, String recordDate, int recordUserId) {
		this.id=id;
		this.organName = organName;
		this.parentId = parentId;
		this.address = address;
		this.phone = phone;
		this.faxPhone = faxPhone;
		this.email = email;
		this.leaderId = leaderId;
		this.taxTypeCode = taxTypeCode;
		this.state = state;
		this.recordDate = recordDate;
		this.recordUserId = recordUserId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFaxPhone() {
		return faxPhone;
	}
	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
	public String getTaxTypeCode() {
		return taxTypeCode;
	}
	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
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
