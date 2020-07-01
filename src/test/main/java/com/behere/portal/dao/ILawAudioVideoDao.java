package com.behere.portal.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
public interface ILawAudioVideoDao {
	/**
	 * 查询案件列表信息
	 * @param pageQuery 分页对象
	 * @param ajmc 案件名称
	 * @param jyaq 简要案情
	 * @param sldw 受理单位
	 * @param slsj 受理时间
	 * @return
	 */
	public PageList<Map<String, String>> selectAllAudioVideoByPage(PageQuery pageQuery, @Param("docType")String docType, @Param("unitNumber")String unitNumber,
			@Param("isRelative")String isRelative, @Param("upLoadDate")String upLoadDate, @Param("picDate")String picDate, @Param("ajbh")String ajbh);

	/**
	 * 查询最新的音视频数据同步时间
	 * @param synchroDate 数据同步时间
	 * @param unitNumber 派出所
	 * @return
	 */
	public Map<String, String> selectVideoDataLatestDate(@Param("uploadDate")String uploadDate, @Param("unitNumber")String unitNumber);
	
	/**
	 * 执法音视频未关联统计查询
	 * @param type 日期类型 本月 本周 本年
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public List<Map<String, String>> selectVideoUnrelated(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 执法音视频关联统计查询
	 * @param type 日期类型 本月 本周 本年
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	public List<Map<String, String>> selectVideoRelated(@Param("type")String type, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	/**
	 * 更新执法音视频案件关联数据
	 * @param ajbh 案件编号
	 * @param videoId 执法音视频编号
	 * @param uploadUnit 上传音视频单位
	 * @return
	 */
	public int updateVideoRelatedCase(@Param("ajbh")String ajbh, @Param("videoId")String videoId, @Param("uploadUnit")String uploadUnit, @Param("userId")String userId, @Param("modifyTime")String modifyTime);
}