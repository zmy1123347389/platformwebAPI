package com.behere.portal.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.behere.portal.dao.ICaseDetailDao;
import com.behere.portal.domin.SuspectAcceptProcessBean;
import com.behere.portal.service.ICaseDetailService;
@Service
public class CaseDetailServiceImpl implements ICaseDetailService {
	@Resource
	public ICaseDetailDao caseDetailDao;
	
	/**
	 * 案件基本信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	@Override
	public Map<String, String> selectCaseDetailMessage(String caseNum) {
		Map<String, String> caseDetailMap = caseDetailDao.selectCaseDetailMessage(caseNum);
		String policeNum = caseDetailMap.get("SLR");
		String policeName = "";
		if (!StringUtils.isEmpty(policeNum)) {
			if (policeNum.indexOf(",") > -1) {
				String[] policeNumArray = policeNum.split(",");
				for (int i = 0;i < policeNumArray.length;i++) {
					Map<String, String> policeMap = caseDetailDao.selectPoliceMessage(policeNumArray[i]);
					if (i == 0) {
						policeName = policeMap.get("YHXM");
					} else {
						policeName = policeName + "," + policeMap.get("YHXM");
					}
				}
			} else {
				Map<String, String> policeMap = caseDetailDao.selectPoliceMessage(policeNum);
				if (null != policeMap) {
					policeName = policeMap.get("YHXM");
				}
			}
		}
		caseDetailMap.put("SLR", policeName);
		return caseDetailMap;
	}
	
	/**
	 * 嫌疑人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	@Override
	public List<SuspectAcceptProcessBean> selectSuspectDetailMessage(String caseNum) {
		List<SuspectAcceptProcessBean> suspectList = caseDetailDao.selectSuspectDetailMessage(caseNum);
		for (int i = 0;i < suspectList.size();i++) {
			String suspect = suspectList.get(i).getNumber();
			List<Map<String, String>> processList = caseDetailDao.selectSuspectAcceptProcess(caseNum, suspect);
			suspectList.get(i).setAcceptPro(processList);
		}
		return suspectList;
	}
	
	/**
	 * 受害人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	@Override
	public List<SuspectAcceptProcessBean> selectVictimDetailMessage(String caseNum) {
		return caseDetailDao.selectVictimDetailMessage(caseNum);
	}
	
	/**
	 * 证人信息查询
	 * @param caseNum 案件编号
	 * @return
	 */
	@Override
	public List<SuspectAcceptProcessBean> selectAttestorDetailMessage(String caseNum) {
		return caseDetailDao.selectAttestorDetailMessage(caseNum);
	}
	
	/**
	 * 查询涉案物品信息
	 * @param caseNum 案件编号
	 * @return
	 */
	@Override
	public List<Map<String, String>> selectArticlesInvolved(String caseNum) {
		return caseDetailDao.selectArticlesInvolved(caseNum);
	}
}