package com.behere.portal.domin;
import java.io.Serializable;
public class AreaPoliceLawProblemCountBean implements Serializable {
	private static final long serialVersionUID = -6887038682902254485L;
	
	//派出所编码
	public String policeCode;
	
	//接处警问题数
	public int receivePoliceCount;
	
	//行政案件受理问题数
	public int adminCaseCount;
	
	//刑事案件受理问题数
	public int criminalCaseCount;
	
	//刑事案件立案问题数
	public int criminalRegisterCount;
	
	//行政案件办理问题数
	public int adminCaseDealCount;
	
	//刑事案件拘留问题数
	public int criminalCaseDetentionCount;
	
	//刑事案件逮捕问题数
	public int criminalCaseArrestCount;
	
	//刑事案件取保候审问题数
	public int criminalCaseBPTCount;
	
	//刑事案件监视居住问题数
	public int criminalCaseSORCount;
	
	//问题总数
	public int totalCount;
	
	

	public AreaPoliceLawProblemCountBean() {}

	public AreaPoliceLawProblemCountBean(String policeCode,
			int receivePoliceCount, int adminCaseCount,
			int criminalCaseCount, int criminalRegisterCount,
			int adminCaseDealCount, int criminalCaseDetentionCount,
			int criminalCaseArrestCount, int criminalCaseBPTCount,
			int criminalCaseSORCount, int totalCount) {
		super();
		this.policeCode = policeCode;
		this.receivePoliceCount = receivePoliceCount;
		this.adminCaseCount = adminCaseCount;
		this.criminalCaseCount = criminalCaseCount;
		this.criminalRegisterCount = criminalRegisterCount;
		this.adminCaseDealCount = adminCaseDealCount;
		this.criminalCaseDetentionCount = criminalCaseDetentionCount;
		this.criminalCaseArrestCount = criminalCaseArrestCount;
		this.criminalCaseBPTCount = criminalCaseBPTCount;
		this.criminalCaseSORCount = criminalCaseSORCount;
		this.totalCount = totalCount;
	}

	public String getPoliceCode() {
		return policeCode;
	}

	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}

	public int getReceivePoliceCount() {
		return receivePoliceCount;
	}

	public void setReceivePoliceCount(int receivePoliceCount) {
		this.receivePoliceCount = receivePoliceCount;
	}

	public int getAdminCaseCount() {
		return adminCaseCount;
	}

	public void setAdminCaseCount(int adminCaseCount) {
		this.adminCaseCount = adminCaseCount;
	}

	public int getCriminalCaseCount() {
		return criminalCaseCount;
	}

	public void setCriminalCaseCount(int criminalCaseCount) {
		this.criminalCaseCount = criminalCaseCount;
	}

	public int getCriminalRegisterCount() {
		return criminalRegisterCount;
	}

	public void setCriminalRegisterCount(int criminalRegisterCount) {
		this.criminalRegisterCount = criminalRegisterCount;
	}

	public int getAdminCaseDealCount() {
		return adminCaseDealCount;
	}

	public void setAdminCaseDealCount(int adminCaseDealCount) {
		this.adminCaseDealCount = adminCaseDealCount;
	}

	public int getCriminalCaseDetentionCount() {
		return criminalCaseDetentionCount;
	}

	public void setCriminalCaseDetentionCount(int criminalCaseDetentionCount) {
		this.criminalCaseDetentionCount = criminalCaseDetentionCount;
	}

	public int getCriminalCaseArrestCount() {
		return criminalCaseArrestCount;
	}

	public void setCriminalCaseArrestCount(int criminalCaseArrestCount) {
		this.criminalCaseArrestCount = criminalCaseArrestCount;
	}

	public int getCriminalCaseBPTCount() {
		return criminalCaseBPTCount;
	}

	public void setCriminalCaseBPTCount(int criminalCaseBPTCount) {
		this.criminalCaseBPTCount = criminalCaseBPTCount;
	}

	public int getCriminalCaseSORCount() {
		return criminalCaseSORCount;
	}

	public void setCriminalCaseSORCount(int criminalCaseSORCount) {
		this.criminalCaseSORCount = criminalCaseSORCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}