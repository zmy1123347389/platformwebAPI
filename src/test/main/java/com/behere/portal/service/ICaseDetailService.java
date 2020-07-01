package com.behere.portal.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domin.SuspectAcceptProcessBean;
public interface ICaseDetailService {
	/**
	 * 案件基本信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public Map<String, String> selectCaseDetailMessage(String caseNum);
	
	/**
	 * 嫌疑人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<SuspectAcceptProcessBean> selectSuspectDetailMessage(String caseNum);
	
	/**
	 * 受害人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<SuspectAcceptProcessBean> selectVictimDetailMessage(String caseNum);
	
	/**
	 * 证人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<SuspectAcceptProcessBean> selectAttestorDetailMessage(String caseNum);
	
	/**
	 * 查询涉案物品信息
	 * @param caseNum 案件编号
	 * @return
	 */
	public List<Map<String, String>> selectArticlesInvolved(@Param("caseNum") String caseNum);
}