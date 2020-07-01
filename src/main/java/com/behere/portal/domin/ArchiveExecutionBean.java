package com.behere.portal.domin;

import java.io.Serializable;

public class ArchiveExecutionBean implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2356894018891002709L;

	private String searchTime;
	private String associated;
	private String unrelated;
	private String dtStart;
	private String dtEnd;

	private String ajbh;
	private String code;   
	private String fullName;
	private String caption;
	private String reason;
	private String name;
	private String dept;
	private String inquestDeptCaption;
	private String dtCreateTime;
	private String dtStartUseTime;
	private String dtEndUseTime;

	public ArchiveExecutionBean() {
	}

	public String getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}

	public String getAssociated() {
		return associated;
	}

	public void setAssociated(String associated) {
		this.associated = associated;
	}

	public String getUnrelated() {
		return unrelated;
	}

	public void setUnrelated(String unrelated) {
		this.unrelated = unrelated;
	}

	public String getAjbh() {
		return ajbh;
	}

	public void setAjbh(String ajbh) {
		this.ajbh = ajbh;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getInquestDeptCaption() {
		return inquestDeptCaption;
	}

	public void setInquestDeptCaption(String inquestDeptCaption) {
		this.inquestDeptCaption = inquestDeptCaption;
	}

	public String getDtCreateTime() {
		return dtCreateTime;
	}

	public void setDtCreateTime(String dtCreateTime) {
		this.dtCreateTime = dtCreateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDtStartUseTime() {
		return dtStartUseTime;
	}

	public void setDtStartUseTime(String dtStartUseTime) {
		this.dtStartUseTime = dtStartUseTime;
	}

	public String getDtEndUseTime() {
		return dtEndUseTime;
	}

	public void setDtEndUseTime(String dtEndUseTime) {
		this.dtEndUseTime = dtEndUseTime;
	}

	public String getDtStart() {
		return dtStart;
	}

	public void setDtStart(String dtStart) {
		this.dtStart = dtStart;
	}

	public String getDtEnd() {
		return dtEnd;
	}

	public void setDtEnd(String dtEnd) {
		this.dtEnd = dtEnd;
	}
	
	


	
}
