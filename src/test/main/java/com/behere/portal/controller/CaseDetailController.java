package com.behere.portal.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.portal.domin.SuspectAcceptProcessBean;
import com.behere.portal.service.ICaseDetailService;
@Controller
@RequestMapping("caseDetail")
public class CaseDetailController {
	@Resource
	public ICaseDetailService caseDetailService;
	
	/**
	 * 案件基本信息查询
	 * @param pageQuery 分页对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCaseDetailMessage")
	public Map<String, String> getCaseDetailMessage(String caseNum) {
		return caseDetailService.selectCaseDetailMessage(caseNum);
	}
	
	/**
	 * 嫌疑人信息查询
	 * @param pageQuery 分页对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getSuspectDetailMessage")
	public List<SuspectAcceptProcessBean> getSuspectDetailMessage(String caseNum) {
		return caseDetailService.selectSuspectDetailMessage(caseNum);
	}
	
	/**
	 * 受害人信息查询
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getVictimDetailMessage")
	public List<SuspectAcceptProcessBean> getVictimDetailMessage(String caseNum) {
		return caseDetailService.selectVictimDetailMessage(caseNum);
	}
	
	/**
	 * 证人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getAttestorDetailMessage")
	public List<SuspectAcceptProcessBean> getAttestorDetailMessage(String caseNum) {
		return caseDetailService.selectAttestorDetailMessage(caseNum);
	}
	
	/**
	 * 查询涉案物品信息
	 * @param caseNum 案件编号
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getArticlesInvolved")
	public List<Map<String, String>> getArticlesInvolved(String caseNum) {
		return caseDetailService.selectArticlesInvolved(caseNum);
	}
}