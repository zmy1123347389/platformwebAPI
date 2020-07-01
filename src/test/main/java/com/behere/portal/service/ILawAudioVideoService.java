package com.behere.portal.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
public interface ILawAudioVideoService {
	/**
	 * 查询音视频列表数据
	 * @param pageQuery
	 * @param docType 类型
	 * @param unitNumber 单位
	 * @param isRelative 是否关联
	 * @param upLoadDate 上传时间
	 * @param picDate 拍摄时间
	 * @return
	 */
	public PageList<Map<String, String>> selectAllAudioVideoByPage(PageQuery pageQuery, String docType, 
			String unitNumber, String isRelative, String upLoadDate, String picDate, String ajbh);
	
	/**
	 * 查询最新的音视频数据同步时间
	 * @param synchroDate 数据同步时间
	 * @param unitNumber 派出所
	 * @return
	 */
	public Map<String, String> selectVideoDataLatestDate(String synchroDate, String unitNumber);
	
	/**
	 * 执法音视频未关联统计查询
	 * @param type 日期类型 本月 本周 本年
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public Map<String, List<Integer>> selectVideoRelateEchart(String type, String startDate, String endDate);
	
	/**
	 * 更新执法音视频案件关联数据
	 * @param ajbh 案件编号
	 * @param videoId 执法音视频编号
	 * @param uploadUnit 上传音视频单位
	 * @return
	 */
	public int updateVideoRelatedCase(@Param("ajbh")String ajbh, @Param("videoId")String videoId, @Param("uploadUnit")String uploadUnit);
}