package com.behere.portal.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domin.AreaPoliceCasesAndSolvedCountBean;
import com.behere.portal.domin.AreaPoliceLawProblemCountBean;
import com.behere.portal.domin.AreaPoliceMapCountBean;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
public interface ILawEnforcementService {
	/**
	 * 统计派出所执法问题数据
	 * @return
	 */
	public List<Map<String, String>> getLawEnforceProblemData(PageQuery pageQuery);
	
	/**
	 * 统计接警数和处警数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public Map<String, Integer> selectReceivePoliceSize(String type, String startDate, String endDate);
	
	/**
	 * 统计行政案件受理数、刑事案件受理数、刑事案件立案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public Map<String, Integer> selectAdminCriminalSize(String type, String startDate, String endDate);
	
	/**
	 * 统计辖区派出所接处警、行政刑事案件数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 统计辖区派出所发案数和破案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceCasesAndSolvedCountBean> selectAreaPoliceCaseAndSolvedSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 区域执法热力图问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceLawProblemCountBean> selectAreaLawChartCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询案件列表信息
	 * @param pageQuery 分页对象
	 * @param ajmc 案件名称
	 * @param jyaq 简要案情
	 * @param sldw 受理单位
	 * @param slsj 受理时间
	 * @return
	 */
	public PageList<Map<String, String>> selectAllCaseByPage(PageQuery pageQuery, String ajbh, String ajmc, String jyaq, String sldw, String slsj);
}