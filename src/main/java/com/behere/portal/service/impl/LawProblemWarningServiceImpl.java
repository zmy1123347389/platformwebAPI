package com.behere.portal.service.impl;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.behere.portal.dao.ILawProblemWarningDao;
import com.behere.portal.service.ILawProblemWarningService;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
@Service
public class LawProblemWarningServiceImpl implements ILawProblemWarningService {
	@Resource
	public ILawProblemWarningDao lawProblemWarningDao;
	private static final String ONE = "1";//预警

	/**
	 * 110接处警未及时反馈
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectReceptionOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningDao.selectReceptionOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 行政案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectAdminCaseOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningDao.selectAdminCaseOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件受理超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectCriminalCaseOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningDao.selectCriminalCaseOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 刑事案件立案超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectCriminalRegisterOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		return lawProblemWarningDao.selectCriminalRegisterOverdue(pageQuery, ajbh, ajmc, slsj, sldw, flag);
	}
	
	/**
	 * 治安行政案件办理超期30日
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectAdminCaseDealOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		if(ONE.equals(flag)){
			return lawProblemWarningDao.selectAdminCaseDealWarn(pageQuery, ajbh, ajmc, slsj, sldw);
		}else{
			return lawProblemWarningDao.selectAdminCaseDealOverdue(pageQuery, ajbh, ajmc, slsj, sldw);
		}		
	}
	
	/**
	 * 刑事案件拘留超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectCriminalCaseDetentionOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		if(ONE.equals(flag)){
			return lawProblemWarningDao.selectCriminalCaseDetentionWarn(pageQuery, ajbh, ajmc, slsj, sldw);
		}else{
			return lawProblemWarningDao.selectCriminalCaseDetentionOverdue(pageQuery, ajbh, ajmc, slsj, sldw);
		}
	}
	
	/**
	 * 刑事案件逮捕超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectCriminalCaseArrestOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		if(ONE.equals(flag)){
			return lawProblemWarningDao.selectCriminalCaseArrestWarn(pageQuery, ajbh, ajmc, slsj, sldw);
		}else{
			return lawProblemWarningDao.selectCriminalCaseArrestOverdue(pageQuery, ajbh, ajmc, slsj, sldw);
		}
	}
	
	/**
	 * 刑事案件取保候审超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectCriminalCaseBPTOverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		if(ONE.equals(flag)){
			return lawProblemWarningDao.selectCriminalCaseBPTWarn(pageQuery, ajbh, ajmc, slsj, sldw);
		}else{
			return lawProblemWarningDao.selectCriminalCaseBPTOverdue(pageQuery, ajbh, ajmc, slsj, sldw);
		}
	}
	
	/**
	 * 刑事案件监视居住超期
	 * @param pageQuery 分页对象
	 * @return
	 */
	@Override
	public PageList<Map<String, String>> selectCriminalCaseSOROverdue(PageQuery pageQuery, String ajbh, String ajmc, String slsj, String sldw, String flag) {
		if(ONE.equals(flag)){
			return lawProblemWarningDao.selectCriminalCaseSORWarn(pageQuery, ajbh, ajmc, slsj, sldw);
		}else{
			return lawProblemWarningDao.selectCriminalCaseSOROverdue(pageQuery, ajbh, ajmc, slsj, sldw);
		}
	}
	
	/**
	 * 执法问题预警取消
	 * @param ajbh 案件编号或接处警编号
	 * @return
	 */
	@Override
	public int lawWarningCancelInsert(String ajbh, String type) {
		return lawProblemWarningDao.lawWarningCancelInsert(ajbh, type);
	}
	
	/**
	 * 执法问题预警恢复
	 * @param ajbh 案件编号或接处警编号
	 * @return
	 */
	@Override
	public int lawWarningCancelDeleteById(String ajbh) {
		return lawProblemWarningDao.lawWarningCancelDeleteById(ajbh);
	}
}