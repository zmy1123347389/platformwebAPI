package com.behere.portal.service;
import java.util.Map;

import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
public interface ILawProblemWarningService {
	/**
	 * 110接处警未及时反馈
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectReceptionOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 行政案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectAdminCaseOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 刑事案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 刑事案件立案超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalRegisterOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 治安行政案件办理超期30日
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectAdminCaseDealOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 刑事案件拘留超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseDetentionOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 刑事案件逮捕超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseArrestOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 刑事案件取保候审超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseBPTOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);
	
	/**
	 * 刑事案件监视居住超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseSOROverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag);

	/**
	 * 执法问题预警取消
	 * @param ajbh 案件编号或接处警编号
	 * @return
	 */
	public int lawWarningCancelInsert(String ajbh, String type);
	
	/**
	 * 执法问题预警恢复
	 * @param ajbh 案件编号或接处警编号
	 * @return
	 */
	public int lawWarningCancelDeleteById(String ajbh);
}