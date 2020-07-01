package com.behere.portal.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
public interface ILawProblemWarningDao {
	/**
	 * 110接处警未及时反馈
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectReceptionOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw, @Param("flag")String flag);
	
	/**
	 * 行政案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectAdminCaseOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw, @Param("flag")String flag);
	
	/**
	 * 刑事案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw, @Param("flag")String flag);
	
	/**
	 * 刑事案件立案超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalRegisterOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw, @Param("flag")String flag);
	
	/**
	 * 治安行政案件办理超期30日
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectAdminCaseDealOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	/**
	 * 治安行政案件办理预警
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectAdminCaseDealWarn(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件拘留超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseDetentionOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件拘留预警
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseDetentionWarn(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件逮捕超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseArrestOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件逮捕预警
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseArrestWarn(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件取保候审超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseBPTOverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件取保候审预警
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseBPTWarn(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件监视居住超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseSOROverdue(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 刑事案件监视居住超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	public PageList<Map<String, String>> selectCriminalCaseSORWarn(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("slsj")String slsj, @Param("sldw")String sldw);
	
	/**
	 * 执法问题预警取消
	 * @param ajbh 案件编号或接处警编号
	 * @return
	 */
	public int lawWarningCancelInsert(@Param("ajbh")String ajbh, @Param("type")String type);
	
	/**
	 * 执法问题预警恢复
	 * @param ajbh 案件编号或接处警编号
	 * @return
	 */
	public int lawWarningCancelDeleteById(@Param("ajbh")String ajbh);
}