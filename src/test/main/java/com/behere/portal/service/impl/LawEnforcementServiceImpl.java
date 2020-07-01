package com.behere.portal.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.behere.portal.dao.ICaseDetailDao;
import com.behere.portal.dao.ILawEnforcementDao;
import com.behere.portal.dao.ILawProblemCountDao;
import com.behere.portal.dao.ILawProblemWarningDao;
import com.behere.portal.domin.AreaPoliceCasesAndSolvedCountBean;
import com.behere.portal.domin.AreaPoliceLawProblemCountBean;
import com.behere.portal.domin.AreaPoliceMapCountBean;
import com.behere.portal.service.ILawEnforcementService;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
@Service
public class LawEnforcementServiceImpl implements ILawEnforcementService {
	@Resource
	public ILawEnforcementDao lawEnforcementDao;
	
	@Resource
	public ILawProblemWarningDao lawProblemWarningDao;
	
	@Resource
	public ILawProblemCountDao lawProblemCountDao;
	
	@Resource
	public ICaseDetailDao caseDetailDao;
	
	/**
	 * 统计接警数和处警数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Override
	public Map<String, Integer> selectReceivePoliceSize(String type, String startDate, String endDate) {
		return lawEnforcementDao.selectReceivePoliceSize(type, startDate, endDate);
	}
	
	/**
	 * 统计行政案件受理数、刑事案件受理数、刑事案件立案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Override
	public Map<String, Integer> selectAdminCriminalSize(String type, String startDate, String endDate) {
		return lawEnforcementDao.selectAdminCriminalSize(type, startDate, endDate);
	}
	
	/**
	 * 统计辖区派出所接处警、行政刑事案件数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Override
	public List<AreaPoliceMapCountBean> selectAreaPoliceMapSize(String type, String startDate, String endDate) {
		//派出所接警统计
		List<AreaPoliceMapCountBean> receivePoliceSizeMap = lawEnforcementDao.selectAreaPoliceMapReceiveSize(type, startDate, endDate);
		//派出所处警统计,需和处警单位为空的数据进行合并,默认按照接警单位
		List<AreaPoliceMapCountBean> policeSizeMap = lawEnforcementDao.selectAreaPoliceMapPoliceSize(type, startDate, endDate);
		List<AreaPoliceMapCountBean> policeSizeNullMap = lawEnforcementDao.selectAreaPoliceMapPoliceCodeNullSize(type, startDate, endDate);
		//派出所行政案件受案数
		List<AreaPoliceMapCountBean> adminCaseMap = lawEnforcementDao.selectAreaPoliceMapAdminCaseSize(type, startDate, endDate);
		//派出所刑事案件受案数
		List<AreaPoliceMapCountBean> criminalCaseMap = lawEnforcementDao.selectAreaPoliceMapCriminalCaseSize(type, startDate, endDate);
		//派出所刑事案件立案数
		List<AreaPoliceMapCountBean> criminalCaseFiledMap = lawEnforcementDao.selectAreaPoliceMapCriminalFilingSize(type, startDate, endDate);
		receivePoliceSizeMap.addAll(policeSizeMap);
		receivePoliceSizeMap.addAll(policeSizeNullMap);
		receivePoliceSizeMap.addAll(adminCaseMap);
		receivePoliceSizeMap.addAll(criminalCaseMap);
		receivePoliceSizeMap.addAll(criminalCaseFiledMap);
		listMapMergeAdd(receivePoliceSizeMap);
		return receivePoliceSizeMap;
	}
	
	/**
	 * 合并处警两个集合的数据
	 * @param policeList
	 * @param policeNullList
	 */
	private void listMapMergeAdd(List<AreaPoliceMapCountBean> policeList) {
		Map<String, AreaPoliceMapCountBean> tempMap = new HashMap<String, AreaPoliceMapCountBean>();
		for(AreaPoliceMapCountBean areaPoliceMapCountBean : policeList) {
			String tempCode = areaPoliceMapCountBean.getAreaPoliceCode();
			if (tempMap.containsKey(tempCode)) {
				AreaPoliceMapCountBean tempBean = tempMap.get(tempCode);
				tempBean.setAlarmCount(tempBean.getAlarmCount() + areaPoliceMapCountBean.getAlarmCount());
				tempBean.setPoliceCount(tempBean.getPoliceCount() + areaPoliceMapCountBean.getPoliceCount());
				tempBean.setAdminCaseAcceptCount(tempBean.getAdminCaseAcceptCount() + areaPoliceMapCountBean.getAdminCaseAcceptCount());
				tempBean.setCriminalCaseAcceptCount(tempBean.getCriminalCaseAcceptCount() + areaPoliceMapCountBean.getCriminalCaseAcceptCount());
				tempBean.setCriminalCaseFiledCount(tempBean.getCriminalCaseFiledCount() + areaPoliceMapCountBean.getCriminalCaseFiledCount());
				tempMap.put(tempCode, tempBean);
			} else {
				tempMap.put(tempCode, areaPoliceMapCountBean);
			}
		}
		policeList.clear();
		for (Map.Entry<String, AreaPoliceMapCountBean> entry : tempMap.entrySet()) { 
			policeList.add(entry.getValue());
		}
	}
	
	/**
	 * 统计辖区派出所发案数和破案数
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Override
	public List<AreaPoliceCasesAndSolvedCountBean> selectAreaPoliceCaseAndSolvedSize(String type, String startDate, String endDate) {
		List<AreaPoliceCasesAndSolvedCountBean> caseList = lawEnforcementDao.selectAreaPoliceCaseSize(type, startDate, endDate);
		List<AreaPoliceCasesAndSolvedCountBean> solvedList = lawEnforcementDao.selectAreaPoliceSolvedSize(type, startDate, endDate);
		for (AreaPoliceCasesAndSolvedCountBean caseBean : caseList) {
			for (AreaPoliceCasesAndSolvedCountBean solvedBean : solvedList) {
				if (caseBean.getAreaPoliceCode().equals(solvedBean.getAreaPoliceCode())) {
					caseBean.setSolvedCount(solvedBean.getSolvedCount());
					break;
				}
			}
		}
		return caseList;
	}
	
	/**
	 * 统计派出所执法问题数据
	 * @return
	 */
	@Override
	public List<Map<String, String>> getLawEnforceProblemData(PageQuery pageQuery) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		PageList<Map<String, String>> receptionOverdueList = lawProblemWarningDao.selectReceptionOverdue(pageQuery, "", "", "", "", "1");
		PageList<Map<String, String>> adminCaseOverdueList = lawProblemWarningDao.selectAdminCaseOverdue(pageQuery, "", "", "", "", "1");
		PageList<Map<String, String>> criminalCaseOverdueList = lawProblemWarningDao.selectCriminalCaseOverdue(pageQuery, "", "", "", "", "1");
		PageList<Map<String, String>> criminalRegisterOverdueList = lawProblemWarningDao.selectCriminalRegisterOverdue(pageQuery, "", "", "", "", "1");
		PageList<Map<String, String>> selectAdminCaseDealWarn = lawProblemWarningDao.selectAdminCaseDealWarn(pageQuery, "", "", "", "");
		PageList<Map<String, String>> selectCriminalCaseDetentionWarn = lawProblemWarningDao.selectCriminalCaseDetentionWarn(pageQuery, "", "", "", "");
		PageList<Map<String, String>> selectCriminalCaseArrestWarn = lawProblemWarningDao.selectCriminalCaseArrestWarn(pageQuery, "", "", "", "");
		PageList<Map<String, String>> selectCriminalCaseBPTWarn = lawProblemWarningDao.selectCriminalCaseBPTWarn(pageQuery, "", "", "", "");
		PageList<Map<String, String>> selectCriminalCaseSORWarn = lawProblemWarningDao.selectCriminalCaseSORWarn(pageQuery, "", "", "", "");
		resultList.addAll(receptionOverdueList);
		resultList.addAll(adminCaseOverdueList);
		resultList.addAll(criminalCaseOverdueList);
		resultList.addAll(criminalRegisterOverdueList);
		resultList.addAll(selectAdminCaseDealWarn);
		resultList.addAll(selectCriminalCaseDetentionWarn);
		resultList.addAll(selectCriminalCaseArrestWarn);
		resultList.addAll(selectCriminalCaseBPTWarn);
		resultList.addAll(selectCriminalCaseSORWarn);
		return resultList;
	}
	
	/**
	 * 区域执法热力图问题数统计
	 * @param type 1:本年 2:本月 3:本日 4:本周 5:日历
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	@Override
	public List<AreaPoliceLawProblemCountBean> selectAreaLawChartCount(String type, String startDate, String endDate) {
		List<AreaPoliceLawProblemCountBean> resultList = new ArrayList<AreaPoliceLawProblemCountBean>();
		
		List<Map<String, String>> receivePoliceList = lawProblemCountDao.selectReceivePoliceCount(type, startDate, endDate);
		List<Map<String, String>> adminCaseList = lawProblemCountDao.selectAdminCaseCount(type, startDate, endDate);
		List<Map<String, String>> criminalCaseList = lawProblemCountDao.selectCriminalCaseCount(type, startDate, endDate);
		List<Map<String, String>> criminalRegisterList = lawProblemCountDao.selectCriminalRegisterCount(type, startDate, endDate);
		List<Map<String, String>> adminCaseDealList = lawProblemCountDao.selectAdminCaseDealCount(type, startDate, endDate);
		List<Map<String, String>> criminalCaseDetentionList = lawProblemCountDao.selectCriminalCaseDetentionCount(type, startDate, endDate);
		List<Map<String, String>> criminalCaseArrestList = lawProblemCountDao.selectCriminalCaseArrestCount(type, startDate, endDate);
		List<Map<String, String>> criminalCaseBPTList = lawProblemCountDao.selectCriminalCaseBPTCount(type, startDate, endDate);
		List<Map<String, String>> criminalCaseSORList = lawProblemCountDao.selectCriminalCaseSORCount(type, startDate, endDate);
		
		AreaPoliceLawProblemCountBean policeBeanOne = new AreaPoliceLawProblemCountBean();
		policeBeanOne.setPoliceCode("610626600000");
		AreaPoliceLawProblemCountBean policeBeanSecond = new AreaPoliceLawProblemCountBean();
		policeBeanSecond.setPoliceCode("610626620000");
		AreaPoliceLawProblemCountBean policeBeanThree = new AreaPoliceLawProblemCountBean();
		policeBeanThree.setPoliceCode("610626530000");
		AreaPoliceLawProblemCountBean policeBeanFour = new AreaPoliceLawProblemCountBean();
		policeBeanFour.setPoliceCode("610626510000");
		AreaPoliceLawProblemCountBean policeBeanFive = new AreaPoliceLawProblemCountBean();
		policeBeanFive.setPoliceCode("610626570000");
		AreaPoliceLawProblemCountBean policeBeanSix = new AreaPoliceLawProblemCountBean();
		policeBeanSix.setPoliceCode("610626580000");
		AreaPoliceLawProblemCountBean policeBeanSeven = new AreaPoliceLawProblemCountBean();
		policeBeanSeven.setPoliceCode("610626590000");
		AreaPoliceLawProblemCountBean policeBeanEight = new AreaPoliceLawProblemCountBean();
		policeBeanEight.setPoliceCode("610626540000");
		
		AreaPoliceLawProblemCountBean policeBeanNine = new AreaPoliceLawProblemCountBean();
		policeBeanNine.setPoliceCode("610626520000");
		AreaPoliceLawProblemCountBean policeBeanTen = new AreaPoliceLawProblemCountBean();
		policeBeanTen.setPoliceCode("610626560000");
		AreaPoliceLawProblemCountBean policeBeanEleven = new AreaPoliceLawProblemCountBean();
		policeBeanEleven.setPoliceCode("610626610000");
		AreaPoliceLawProblemCountBean policeBeanTwelve = new AreaPoliceLawProblemCountBean();
		policeBeanTwelve.setPoliceCode("610626550000");
		for (Map<String, String> dataMap : receivePoliceList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setReceivePoliceCount(Integer.parseInt(String.valueOf(String.valueOf(dataMap.get("PRO_COUNT")))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanNine.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanNine.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTen.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTen.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanEleven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEleven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTwelve.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTwelve.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : adminCaseList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setAdminCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanNine.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanNine.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTen.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTen.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanEleven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEleven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTwelve.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTwelve.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : criminalCaseList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setCriminalCaseCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanNine.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanNine.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTen.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTen.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanEleven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEleven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTwelve.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTwelve.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : criminalRegisterList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setCriminalRegisterCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanNine.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanNine.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTen.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTen.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanEleven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEleven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTwelve.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTwelve.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : adminCaseDealList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setAdminCaseDealCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanNine.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanNine.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTen.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTen.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanEleven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEleven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTwelve.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTwelve.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : criminalCaseDetentionList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setCriminalCaseDetentionCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanNine.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanNine.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTen.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTen.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanEleven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEleven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTwelve.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTwelve.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : criminalCaseArrestList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setCriminalCaseArrestCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanNine.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanNine.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTen.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTen.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanEleven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEleven.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}else if (policeBeanTwelve.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanTwelve.setReceivePoliceCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : criminalCaseBPTList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setCriminalCaseBPTCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		for (Map<String, String> dataMap : criminalCaseSORList) {
			if (policeBeanOne.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanOne.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSecond.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSecond.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanThree.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanThree.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFour.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFour.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanFive.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanFive.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSix.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSix.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanSeven.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanSeven.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			} else if (policeBeanEight.getPoliceCode().equals(dataMap.get("POLICECODE"))) {
				policeBeanEight.setCriminalCaseSORCount(Integer.parseInt(String.valueOf(dataMap.get("PRO_COUNT"))));
			}
		}
		policeBeanOne.setTotalCount(policeBeanOne.getReceivePoliceCount()+policeBeanOne.getAdminCaseCount()+
				policeBeanOne.getCriminalCaseCount()+policeBeanOne.getCriminalRegisterCount()+policeBeanOne.getAdminCaseDealCount()+
				policeBeanOne.getCriminalCaseDetentionCount()+policeBeanOne.getCriminalCaseArrestCount()+
				policeBeanOne.getCriminalCaseBPTCount()+policeBeanOne.getCriminalCaseSORCount());
		policeBeanSecond.setTotalCount(policeBeanSecond.getReceivePoliceCount()+policeBeanSecond.getAdminCaseCount()+
				policeBeanSecond.getCriminalCaseCount()+policeBeanSecond.getCriminalRegisterCount()+policeBeanSecond.getAdminCaseDealCount()+
				policeBeanSecond.getCriminalCaseDetentionCount()+policeBeanSecond.getCriminalCaseArrestCount()+
				policeBeanSecond.getCriminalCaseBPTCount()+policeBeanSecond.getCriminalCaseSORCount());
		policeBeanThree.setTotalCount(policeBeanThree.getReceivePoliceCount()+policeBeanThree.getAdminCaseCount()+
				policeBeanThree.getCriminalCaseCount()+policeBeanThree.getCriminalRegisterCount()+policeBeanThree.getAdminCaseDealCount()+
				policeBeanThree.getCriminalCaseDetentionCount()+policeBeanThree.getCriminalCaseArrestCount()+
				policeBeanThree.getCriminalCaseBPTCount()+policeBeanThree.getCriminalCaseSORCount());
		policeBeanFour.setTotalCount(policeBeanFour.getReceivePoliceCount()+policeBeanFour.getAdminCaseCount()+
				policeBeanFour.getCriminalCaseCount()+policeBeanFour.getCriminalRegisterCount()+policeBeanFour.getAdminCaseDealCount()+
				policeBeanFour.getCriminalCaseDetentionCount()+policeBeanFour.getCriminalCaseArrestCount()+
				policeBeanFour.getCriminalCaseBPTCount()+policeBeanFour.getCriminalCaseSORCount());
		policeBeanFive.setTotalCount(policeBeanFive.getReceivePoliceCount()+policeBeanFive.getAdminCaseCount()+
				policeBeanFive.getCriminalCaseCount()+policeBeanFive.getCriminalRegisterCount()+policeBeanFive.getAdminCaseDealCount()+
				policeBeanFive.getCriminalCaseDetentionCount()+policeBeanFive.getCriminalCaseArrestCount()+
				policeBeanFive.getCriminalCaseBPTCount()+policeBeanFive.getCriminalCaseSORCount());
		policeBeanSix.setTotalCount(policeBeanSix.getReceivePoliceCount()+policeBeanSix.getAdminCaseCount()+
				policeBeanSix.getCriminalCaseCount()+policeBeanSix.getCriminalRegisterCount()+policeBeanSix.getAdminCaseDealCount()+
				policeBeanSix.getCriminalCaseDetentionCount()+policeBeanSix.getCriminalCaseArrestCount()+
				policeBeanSix.getCriminalCaseBPTCount()+policeBeanSix.getCriminalCaseSORCount());
		policeBeanSeven.setTotalCount(policeBeanSeven.getReceivePoliceCount()+policeBeanSeven.getAdminCaseCount()+
				policeBeanSeven.getCriminalCaseCount()+policeBeanSeven.getCriminalRegisterCount()+policeBeanSeven.getAdminCaseDealCount()+
				policeBeanSeven.getCriminalCaseDetentionCount()+policeBeanSeven.getCriminalCaseArrestCount()+
				policeBeanSeven.getCriminalCaseBPTCount()+policeBeanSeven.getCriminalCaseSORCount());


		policeBeanNine.setTotalCount(policeBeanNine.getReceivePoliceCount()+policeBeanNine.getAdminCaseCount()+
				policeBeanNine.getCriminalCaseCount()+policeBeanNine.getCriminalRegisterCount()+policeBeanNine.getAdminCaseDealCount()+
				policeBeanNine.getCriminalCaseDetentionCount()+policeBeanNine.getCriminalCaseArrestCount()+
				policeBeanNine.getCriminalCaseBPTCount()+policeBeanNine.getCriminalCaseSORCount());
		policeBeanTen.setTotalCount(policeBeanTen.getReceivePoliceCount()+policeBeanTen.getAdminCaseCount()+
				policeBeanTen.getCriminalCaseCount()+policeBeanTen.getCriminalRegisterCount()+policeBeanTen.getAdminCaseDealCount()+
				policeBeanTen.getCriminalCaseDetentionCount()+policeBeanTen.getCriminalCaseArrestCount()+
				policeBeanTen.getCriminalCaseBPTCount()+policeBeanTen.getCriminalCaseSORCount());
		policeBeanEleven.setTotalCount(policeBeanEleven.getReceivePoliceCount()+policeBeanEleven.getAdminCaseCount()+
				policeBeanEleven.getCriminalCaseCount()+policeBeanEleven.getCriminalRegisterCount()+policeBeanEleven.getAdminCaseDealCount()+
				policeBeanEleven.getCriminalCaseDetentionCount()+policeBeanEleven.getCriminalCaseArrestCount()+
				policeBeanEleven.getCriminalCaseBPTCount()+policeBeanEleven.getCriminalCaseSORCount());
		policeBeanTwelve.setTotalCount(policeBeanTwelve.getReceivePoliceCount()+policeBeanTwelve.getAdminCaseCount()+
				policeBeanTwelve.getCriminalCaseCount()+policeBeanTwelve.getCriminalRegisterCount()+policeBeanTwelve.getAdminCaseDealCount()+
				policeBeanTwelve.getCriminalCaseDetentionCount()+policeBeanTwelve.getCriminalCaseArrestCount()+
				policeBeanTwelve.getCriminalCaseBPTCount()+policeBeanTwelve.getCriminalCaseSORCount());
		resultList.add(policeBeanOne);
		resultList.add(policeBeanSecond);
		resultList.add(policeBeanThree);
		resultList.add(policeBeanFour);
		resultList.add(policeBeanFive);
		resultList.add(policeBeanSix);
		resultList.add(policeBeanSeven);
		resultList.add(policeBeanEight);
		resultList.add(policeBeanNine);
		resultList.add(policeBeanTen);
		resultList.add(policeBeanEleven);
		resultList.add(policeBeanTwelve);
		return resultList;
	}
	
	/**
	 * 查询案件列表信息
	 * @param pageQuery 分页对象
	 * @param ajmc 案件名称
	 * @param jyaq 简要案情
	 * @param sldw 受理单位
	 * @param slsj 受理时间
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectAllCaseByPage(PageQuery pageQuery, String ajbh, String ajmc, String jyaq, String sldw, String slsj) {
		return lawEnforcementDao.selectAllCaseByPage(pageQuery, ajbh, ajmc, jyaq, sldw, slsj);
	}
}