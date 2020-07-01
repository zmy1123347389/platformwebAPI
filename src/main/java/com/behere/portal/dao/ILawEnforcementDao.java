package com.behere.portal.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domin.AreaPoliceCasesAndSolvedCountBean;
import com.behere.portal.domin.AreaPoliceMapCountBean;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
public interface ILawEnforcementDao {
	/**
	 * 统计接警数和处警数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public Map<String, Integer> selectReceivePoliceSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 统计行政案件受理数、刑事案件受理数、刑事案件立案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public Map<String, Integer> selectAdminCriminalSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);

	/**
	 * 查询辖区派出所接警数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapReceiveSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询辖区派出所处警数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapPoliceSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询辖区派出所处警数(处警单位为空,但接警单位不为空与selectAreaPoliceMapPoliceSize结果进行合并)
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapPoliceCodeNullSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询辖区派出所行政案件受案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapAdminCaseSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询辖区派出所刑事案件受案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapCriminalCaseSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询辖区派出所刑事案件立案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapCriminalFilingSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询辖区派出所发案数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceCasesAndSolvedCountBean> selectAreaPoliceCaseSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询辖区派出所破案数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	public List<AreaPoliceCasesAndSolvedCountBean> selectAreaPoliceSolvedSize(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 查询案件列表信息
	 * @param pageQuery 分页对象
	 * @param ajmc 案件名称
	 * @param jyaq 简要案情
	 * @param sldw 受理单位
	 * @param slsj 受理时间
	 * @return
	 */
	public PageList<Map<String, String>> selectAllCaseByPage(PageQuery pageQuery, @Param("ajbh")String ajbh, @Param("ajmc")String ajmc, @Param("jyaq")String jyaq, @Param("sldw")String sldw, @Param("slsj")String slsj);
}