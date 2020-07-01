package com.behere.portal.dao;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
public interface ILawProblemCountDao {
	/**
	 * 110接处警热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectReceivePoliceCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 行政案件受理热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectAdminCaseCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 刑事案件受理热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectCriminalCaseCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 刑事案件立案热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectCriminalRegisterCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 行政案件办理热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectAdminCaseDealCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 刑事案件拘留热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectCriminalCaseDetentionCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 刑事案件逮捕热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectCriminalCaseArrestCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 刑事案件取保候审热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectCriminalCaseBPTCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 刑事案件监视居住热力图执法问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<Map<String, String>> selectCriminalCaseSORCount(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
}