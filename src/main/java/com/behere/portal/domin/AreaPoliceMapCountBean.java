package com.behere.portal.domin;
import java.io.Serializable;
public class AreaPoliceMapCountBean implements Serializable {
	private static final long serialVersionUID = 7695062990468864000L;
	
	//辖区派出所编码
	private String areaPoliceCode;
	
	//接警数统计
	private int alarmCount;
	
	//处警数统计
	private int policeCount;
	
	//行政案件受案数统计
	private int adminCaseAcceptCount;
	
	//刑事案件受案数统计
	private int criminalCaseAcceptCount;
	
	//刑事案件立案数统计
	private int criminalCaseFiledCount;
	
	public AreaPoliceMapCountBean(){}

	public AreaPoliceMapCountBean(String areaPoliceCode, int alarmCount,
			int policeCount, int adminCaseAcceptCount,
			int criminalCaseAcceptCount, int criminalCaseFiledCount) {
		super();
		this.areaPoliceCode = areaPoliceCode;
		this.alarmCount = alarmCount;
		this.policeCount = policeCount;
		this.adminCaseAcceptCount = adminCaseAcceptCount;
		this.criminalCaseAcceptCount = criminalCaseAcceptCount;
		this.criminalCaseFiledCount = criminalCaseFiledCount;
	}

	public String getAreaPoliceCode() {
		return areaPoliceCode;
	}

	public void setAreaPoliceCode(String areaPoliceCode) {
		this.areaPoliceCode = areaPoliceCode;
	}

	public int getAlarmCount() {
		return alarmCount;
	}

	public void setAlarmCount(int alarmCount) {
		this.alarmCount = alarmCount;
	}

	public int getPoliceCount() {
		return policeCount;
	}

	public void setPoliceCount(int policeCount) {
		this.policeCount = policeCount;
	}

	public int getAdminCaseAcceptCount() {
		return adminCaseAcceptCount;
	}

	public void setAdminCaseAcceptCount(int adminCaseAcceptCount) {
		this.adminCaseAcceptCount = adminCaseAcceptCount;
	}

	public int getCriminalCaseAcceptCount() {
		return criminalCaseAcceptCount;
	}

	public void setCriminalCaseAcceptCount(int criminalCaseAcceptCount) {
		this.criminalCaseAcceptCount = criminalCaseAcceptCount;
	}

	public int getCriminalCaseFiledCount() {
		return criminalCaseFiledCount;
	}

	public void setCriminalCaseFiledCount(int criminalCaseFiledCount) {
		this.criminalCaseFiledCount = criminalCaseFiledCount;
	}
}