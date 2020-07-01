package com.behere.portal.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.domin.ArchiveExecutionBean;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;

public interface IHandlingService {
	
	/**
	 * 统计办案数据
	 * @return
	 */
	public List<Map<String, String>> selectHandling(PageQuery pageQuery);
	
	/**
	 * 办案区案情详情数据
	 * @return
	 */
	public Map<String, String> selectHandlingMap(@Param("code")String code);
	
	/**
	 * 统计办案数据分页已关联查询
	 * @return
	 */
	public PageList<ArchiveExecutionBean> getHandlingPage(PageQuery pageQuery,ArchiveExecutionBean archiveExecutionBean);
	
	
	/**
	 *  办案区案情涉案人员台账数据
	 * @return
	 */
	public List<Map<String, String>> selectInquestArchiveEvidenceList(@Param("code")String code);
	
	/**
	 *  办案区案情涉案人员物品数据
	 * @return
	 */
	public List<Map<String, String>> selectInquestFollowGoodsList(@Param("code")String code);
	
	
	/**
	 * 办案区案办案审讯信息
	 */
	public List<Map<String, String>> selectInquestList(@Param("code")String code);
	
	
	/**
	 * 统计办案音视频
	 * @return
	 */
	public List<Map<String, String>> getHandlingVideoData(PageQuery pageQuery);
	
	/**
	 * 统计办案出入区人数 
	 * @return
	 */
	public Map<String, String> selectHandlingCount(String searchTime,String dtStart,String dtEnd);

}
