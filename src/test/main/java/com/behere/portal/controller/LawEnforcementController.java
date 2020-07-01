package com.behere.portal.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.portal.domin.AreaPoliceCasesAndSolvedCountBean;
import com.behere.portal.domin.AreaPoliceLawProblemCountBean;
import com.behere.portal.domin.AreaPoliceMapCountBean;
import com.behere.portal.service.ILawEnforcementService;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
@Controller
@RequestMapping("lawenforce")
public class LawEnforcementController {
	@Resource
	public ILawEnforcementService lawEnforcementService;
	
	/**
	 * 统计接处警数、行政刑事案件数等
	 * @param type 日期类型
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	@RequiresPermissions("sys:user:user")
	@ResponseBody
	@RequestMapping(value = "/getRecepolCount")
	public Map<String, Integer> getRecepolSize(String type, String startDate, String endDate) {
		Map<String, Integer> recPoliceMap = lawEnforcementService.selectReceivePoliceSize(type, startDate, endDate);
		Map<String, Integer> adminCriminalMap = lawEnforcementService.selectAdminCriminalSize(type, startDate, endDate);
		recPoliceMap.putAll(adminCriminalMap);
		return recPoliceMap;
	}
	
	/**
	 * 统计接处警数、行政刑事案件数等
	 * @param type 日期类型
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	@RequiresPermissions("sys:user:user")
	@ResponseBody
	@RequestMapping(value = "/getPoliceMapCount")
	public List<AreaPoliceMapCountBean> getPoliceMapCount(String type, String startDate, String endDate) {
		return lawEnforcementService.selectAreaPoliceMapSize(type, startDate, endDate);
	}
	
	/**
	 * 统计派出所发案数和破案数
	 * @param type 日期类型
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	@RequiresPermissions("sys:user:user")
	@ResponseBody
	@RequestMapping(value = "/getPoliceCaseAndSolvedCount")
	public List<AreaPoliceCasesAndSolvedCountBean> getPoliceCaseAndSolvedCount(String type, String startDate, String endDate) {
		return lawEnforcementService.selectAreaPoliceCaseAndSolvedSize(type, startDate, endDate);
	}
	
	/**
	 * 统计派出所执法问题数据
	 * @return
	 */
	@RequiresPermissions("sys:user:user")
	@ResponseBody
	@RequestMapping(value = "/getLawEnforceProblemData")
	public List<Map<String, String>> getLawEnforceProblemData(PageQuery pageQuery) {
		return lawEnforcementService.getLawEnforceProblemData(pageQuery);
	}
	
	/**
	 * 热力图执法问题数统计
	 * @return
	 */
	@RequiresPermissions("sys:user:user")
	@ResponseBody
	@RequestMapping(value = "/getAreaLawChartCount")
	public List<AreaPoliceLawProblemCountBean> getAreaLawChartCount(String type, String startDate, String endDate) {
		return lawEnforcementService.selectAreaLawChartCount(type, startDate, endDate);
	}
	
	/**
	 * 查询案件列表信息
	 * @param pageQuery 分页对象
	 * @return
	 */
	@RequiresPermissions("sys:user:user")
	@ResponseBody
	@RequestMapping("/getAllCaseByPage")
	public PageList<Map<String, String>> getAllCase(PageQuery pageQuery, String ajbh, String ajmc, String jyaq, String sldw, String slsj) {
		return lawEnforcementService.selectAllCaseByPage(pageQuery, ajbh, ajmc, jyaq, sldw, slsj);
	}
}