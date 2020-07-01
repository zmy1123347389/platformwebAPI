package com.behere.portal.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.behere.portal.dao.IHandlingDao;
import com.behere.portal.domin.ArchiveExecutionBean;
import com.behere.portal.service.IHandlingService;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;

@Service
public class IHandlingServiceImpl implements IHandlingService {

	@Resource
	IHandlingDao handlingDao;

	/**
	 * 统计办案数据
	 * @return
	 */
	@Override
	public List<Map<String, String>> selectHandling(PageQuery pageQuery) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		PageList<Map<String, String>> handlingList = handlingDao.selectHandling(pageQuery);
		resultList.addAll(handlingList);
		return resultList;
	}
	
	/**
	 * 统计办案数据
	 * @return
	 */
	@Override
	public Map<String, String> selectHandlingMap(String code) {
		return handlingDao.selectHandlingMap(code);
	}
	
	/**
	 * 统计办案数据
	 * @return
	 */
	@Override
	public PageList<ArchiveExecutionBean> getHandlingPage(PageQuery pageQuery,ArchiveExecutionBean archiveExecutionBean){
		String unrelated = archiveExecutionBean.getUnrelated();
		//未关联
		if(StringUtils.isNotEmpty(unrelated)) {
			return handlingDao.selectHandlingRelevancePage(pageQuery, archiveExecutionBean);
		}
		return handlingDao.selectHandlingPage(pageQuery, archiveExecutionBean);
	}
	
	/**
	 * 办案区案情涉案人员台账数据
	 * @return
	 */
	@Override
	public List<Map<String, String>> selectInquestArchiveEvidenceList(String code){
		//获取下载链接
		String path = "http://92.175.70.40:7000/Easy7/rest/file/downloadForDZTZ?";
		List<Map<String, String>> selectInquestArchiveEvidence = handlingDao.selectInquestArchiveEvidenceList(code);
		for (Map<String, String> map : selectInquestArchiveEvidence) {
			if(StringUtils.isNoneEmpty(map.get("PATH")) && StringUtils.isNoneEmpty((map.get("ID")))) {
				String string = map.get("PATH");
				String id = map.get("ID");
				//需要两次URLEncoder编码
				String encode = URLEncoder.encode("王虎_临时出区");
				String encodePath = URLEncoder.encode(encode);
				path += "fileName=" +id + "&fullName="+encodePath+".DOC";
				map.put("PATH",path);
			}
		}
		return selectInquestArchiveEvidence;
	}
	
	/**
	 * 办案区案情涉案人员台账数据
	 * @return
	 */
	@Override
	public List<Map<String, String>> selectInquestFollowGoodsList(String code){
		return handlingDao.selectInquestFollowGoodsList(code);
	}
	
	/**
	 * 办案区案办案审讯信息
	 */
	@Override
	public List<Map<String, String>> selectInquestList(String code){
		return handlingDao.selectInquestList(code);
	}
	
	/**
	 * 统计办案音视频
	 * @return
	 */
	@Override
	public List<Map<String, String>> getHandlingVideoData(PageQuery pageQuery) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		PageList<Map<String, String>> handlingList = handlingDao.selectHandling(pageQuery);
		resultList.addAll(handlingList);
		return resultList;
	}

	/**
	 * 统计办案出入区人数 
	 * @return
	 */
	@Override
	public Map<String, String> selectHandlingCount(String searchTime, String dtStart, String dtEnd) {
		return handlingDao.selectHandlingCount(searchTime, dtStart, dtEnd);
	}

}
