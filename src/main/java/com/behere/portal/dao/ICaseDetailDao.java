package com.behere.portal.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domin.SuspectAcceptProcessBean;
public interface ICaseDetailDao {
	/**
	 * 案件基本信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public Map<String, String> selectCaseDetailMessage(@Param("caseNum") String caseNum);
	
	/**
	 * 民警信息查询
	 * @param policeNum 民警编号
	 * @return
	 */
	public Map<String, String> selectPoliceMessage(@Param("policeNum") String policeNum);
	
	/**
	 * 嫌疑人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<SuspectAcceptProcessBean> selectSuspectDetailMessage(@Param("caseNum") String caseNum);
	
	/**
	 * 受害人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<SuspectAcceptProcessBean> selectVictimDetailMessage(@Param("caseNum") String caseNum);
	
	/**
	 * 证人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<SuspectAcceptProcessBean> selectAttestorDetailMessage(@Param("caseNum") String caseNum);
	
	/**
	 * 嫌疑人受理流程查询
	 * @param caseNum 案件编号
	 * @param suspectNum 嫌疑人编号
	 * @return
	 */
	public List<Map<String, String>> selectSuspectAcceptProcess(@Param("caseNum") String caseNum, @Param("suspectNum") String suspectNum);
	
	/**
	 * 查询涉案物品信息
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<Map<String, String>> selectArticlesInvolved(@Param("caseNum") String caseNum);
}