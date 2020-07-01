package com.behere.portal.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.behere.common.utils.DateUtils;
import com.behere.portal.dao.ILawAudioVideoDao;
import com.behere.portal.service.ILawAudioVideoService;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
@Service
public class LawAudioVideoServiceImpl implements ILawAudioVideoService {
	@Resource
	private ILawAudioVideoDao lawAudioVideoDao;
	
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
	@Override
	public PageList<Map<String, String>> selectAllAudioVideoByPage(
			PageQuery pageQuery, String docType, String unitNumber,
			String isRelative, String upLoadDate, String picDate, String ajbh) {
		return lawAudioVideoDao.selectAllAudioVideoByPage(pageQuery, docType, unitNumber, isRelative, upLoadDate, picDate, ajbh);
	}
	
	/**
	 * 查询最新的音视频数据同步时间
	 * @param synchroDate 数据同步时间
	 * @param unitNumber 派出所
	 * @return
	 */
	@Override
	public Map<String, String> selectVideoDataLatestDate(String synchroDate, String unitNumber) {
		return lawAudioVideoDao.selectVideoDataLatestDate(synchroDate, unitNumber);
	}
	
	/**
	 * 执法音视频关联和未关联统计查询
	 * @param type 日期类型 本月 本周 本年
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	@Override
	public Map<String, List<Integer>> selectVideoRelateEchart(String type, String startDate, String endDate) {
		Map<String, List<Integer>> chartDataMap = new HashMap<String, List<Integer>>();
		List<Integer> chartRelatedList = new ArrayList<Integer>();
		List<Integer> chartUnRelatedList = new ArrayList<Integer>();
		String policeCode[] = {"610626600000", "610626620000", "610626530000", "610626510000","610626570000","610626580000","610626590000","610626540000","610626520000","610626560000","610626610000","610626550000"};
		List<Map<String, String>> relatedList = lawAudioVideoDao.selectVideoRelated(type, startDate, endDate);
		for (int i = 0;i < policeCode.length;i++) {
			boolean isExist = false;
			for (int j = 0;j < relatedList.size();j++) {
				if (relatedList.get(j).containsValue(policeCode[i])) {
					String related = String.valueOf(relatedList.get(j).get("RELATED"));
					chartRelatedList.add(Integer.parseInt(related));
					isExist = true;
				}
			}
			if (!isExist) {
				chartRelatedList.add(0);
			}
		}
		List<Map<String, String>> unrelatedList = lawAudioVideoDao.selectVideoUnrelated(type, startDate, endDate);
		for (int i = 0;i < policeCode.length;i++) {
			boolean isExist = false;
			for (int j = 0;j < unrelatedList.size();j++) {
				if (unrelatedList.get(j).containsValue(policeCode[i])) {
					String unrelated = String.valueOf(unrelatedList.get(j).get("UNRELATED"));
					chartUnRelatedList.add(Integer.parseInt(unrelated));
					isExist = true;
				}
			}
			if (!isExist) {
				chartUnRelatedList.add(0);
			}
		}
		chartDataMap.put("rel", chartRelatedList);
		chartDataMap.put("unrel", chartUnRelatedList);
		return chartDataMap;
	}
	
	/**
	 * 更新执法音视频案件关联数据
	 * @param ajbh 案件编号
	 * @param videoId 执法音视频编号
	 * @param uploadUnit 上传音视频单位
	 * @return
	 */
	@Override
	public int updateVideoRelatedCase(String ajbh, String videoId, String uploadUnit) {
		return lawAudioVideoDao.updateVideoRelatedCase(ajbh, videoId, uploadUnit, null, DateUtils.formatNowDate());
	}
}