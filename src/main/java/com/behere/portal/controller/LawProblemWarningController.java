package com.behere.portal.controller;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.portal.service.ILawProblemWarningService;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
@Controller
@RequestMapping("lawproblem")
public class LawProblemWarningController {
	@Resource
	public ILawProblemWarningService lawProblemWarningService;
	
	/**
	 * 110接处警未及时反馈
	 * @param pageQuery 分页对象
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getReceptionOverdueByPage")
	public PageList<Map<String, String>> getReceptionOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectReceptionOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 行政案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getAdminCaseOverdueByPage")
	public PageList<Map<String, String>> getAdminCaseOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectAdminCaseOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getCriminalCaseOverdueByPage")
	public PageList<Map<String, String>> getCriminalCaseOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectCriminalCaseOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件立案超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getCriminalRegisterOverdueByPage")
	public PageList<Map<String, String>> getCriminalRegisterOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectCriminalRegisterOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 治安行政案件办理超期30日
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getAdminCaseDealOverdueByPage")
	public PageList<Map<String, String>> getAdminCaseDealOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectAdminCaseDealOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件拘留超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getCriminalCaseDetentionOverdueByPage")
	public PageList<Map<String, String>> getCriminalCaseDetentionOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectCriminalCaseDetentionOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件逮捕超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getCriminalCaseArrestOverdueByPage")
	public PageList<Map<String, String>> getCriminalCaseArrestOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectCriminalCaseArrestOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件取保候审超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getCriminalCaseBPTOverdueByPage")
	public PageList<Map<String, String>> getCriminalCaseBPTOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectCriminalCaseBPTOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件监视居住超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("/getCriminalCaseSOROverdueByPage")
	public PageList<Map<String, String>> getCriminalCaseSOROverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningService.selectCriminalCaseSOROverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 执法预警取消
	 * @param ajbh 案件编号或接处警编号
	 * @param type 哪类预警/超期的规则
	 */
	
	@ResponseBody
	@RequestMapping("/lawWarningCancel")
	public void lawWarningCancel(String ajbh, String type) {
		lawProblemWarningService.lawWarningCancelInsert(ajbh, type);
	}
	
	/**
	 * 执法预警恢复
	 * @param ajbh
	 */
	
	@ResponseBody
	@RequestMapping("/lawWarningRsume")
	public void lawWarningRsume(String ajbh) {
		lawProblemWarningService.lawWarningCancelDeleteById(ajbh);
	}
	
	/**
	 * 获取案件预警或超期的信息
	 * @param caseNum 案件编号
	 * @param type 案件预警或超期的类型
	 */
	
	@ResponseBody
	@RequestMapping("/caseDetailLawWarning")
	public PageList<Map<String, String>> getCaseDetailWarning(String caseNum, String type) {
		PageQuery pageQuery = new PageQuery(1, 1);
		PageList<Map<String, String>> dataList = null;
		if (!StringUtils.isEmpty(type) && type.indexOf("-") > -1) {
			String[] array = type.split("-");
			if (array[0].equals("1")) {
				dataList = lawProblemWarningService.selectReceptionOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("2")) {
				dataList = lawProblemWarningService.selectAdminCaseOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("3")) {
				dataList = lawProblemWarningService.selectCriminalCaseOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("4")) {
				dataList = lawProblemWarningService.selectCriminalRegisterOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("5")) {
				dataList = lawProblemWarningService.selectAdminCaseDealOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("6")) {
				dataList = lawProblemWarningService.selectCriminalCaseDetentionOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("7")) {
				dataList = lawProblemWarningService.selectCriminalCaseArrestOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("8")) {
				dataList = lawProblemWarningService.selectCriminalCaseBPTOverdue(pageQuery, caseNum, "", "", "", array[1]);
			} else if (array[0].equals("9")) {
				dataList = lawProblemWarningService.selectCriminalCaseSOROverdue(pageQuery, caseNum, "", "", "", array[1]);
			}
		}
		return dataList;
	}
}