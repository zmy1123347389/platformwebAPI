package com.behere.portal.domin;
import java.io.Serializable;
public class AreaPoliceCasesAndSolvedCountBean implements Serializable {
	private static final long serialVersionUID = 7695062990468864000L;
	
	//辖区派出所编码
	private String areaPoliceCode;
	
	//发案数统计
	private int caseCount;
	
	//破案数统计
	private int solvedCount;
	
	public AreaPoliceCasesAndSolvedCountBean(){}

	public AreaPoliceCasesAndSolvedCountBean(String areaPoliceCode, int caseCount,
			int solvedCount) {
		super();
		this.areaPoliceCode = areaPoliceCode;
		this.caseCount = caseCount;
		this.solvedCount = solvedCount;
	}

	public String getAreaPoliceCode() {
		return areaPoliceCode;
	}

	public void setAreaPoliceCode(String areaPoliceCode) {
		this.areaPoliceCode = areaPoliceCode;
	}

	public int getCaseCount() {
		return caseCount;
	}

	public void setCaseCount(int caseCount) {
		this.caseCount = caseCount;
	}

	public int getSolvedCount() {
		return solvedCount;
	}

	public void setSolvedCount(int solvedCount) {
		this.solvedCount = solvedCount;
	}
}