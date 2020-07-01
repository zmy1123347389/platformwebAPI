package com.behere.portal.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.behere.common.domain.UserDO;
import com.behere.portal.domin.ArchiveExecutionBean;
import com.behere.portal.service.IHandlingService;
import com.behere.portal.util.PageQuery;

@Controller
@RequestMapping("handling")
public class IHandlingController {

	@Resource
	public IHandlingService handlingService;

	/**
	 * 统计办案数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectHandling")
	public List<Map<String, String>> selectHandling(PageQuery pageQuery) {
		return handlingService.selectHandling(pageQuery);
	}

	/**
	 * 跳转到办案数据页面
	 **/
	@RequestMapping(value = "/handlingIndex")
	public String handlingIndex() {
		return "/portal/handling/handling";
	}

	/**
	 * 跳转到办案数据明细页面
	 **/
	@RequestMapping(value = "/handlingDetailIndex")
	public ModelAndView handlingDetailIndex() {
		UserDO currentUser = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/handling/handlingDetail");
		if (null != currentUser) {
			mv.addObject("userId", currentUser.getUserId());
		}
		return mv;
	}

	/**
	 * 统计办案数据
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/selectHandlingMap")
	public Map<String, String> selectHandlingMap(String code) {
		return handlingService.selectHandlingMap(code);
	}

	/**
	 * 办案区案情涉案人员台账数据
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/selectInquestArchiveEvidenceList")
	public List<Map<String, String>> selectInquestArchiveEvidenceList(String code) {
		return handlingService.selectInquestArchiveEvidenceList(code);
	}

	/**
	 * 办案区案情涉案人员物品数据
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/selectInquestFollowGoodsList")
	public List<Map<String, String>> selectInquestFollowGoodsList(String code) {
		return handlingService.selectInquestFollowGoodsList(code);
	}

	/**
	 * 办案区案办案审讯信息
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/selectInquestList")
	public List<Map<String, String>> selectInquestList(String code) {
		return handlingService.selectInquestList(code);
	}

	/**
	 * 跳转到办案音视频页面
	 **/
	
	@RequestMapping(value = "/handlingVideoIndex")
	public String handlingVideoIndex() {
		return "/portal/handling/handlingVideo";
	}

	/**
	 * 跳转到办案音视频明细页面
	 **/
	
	@RequestMapping(value = "/handlingVideoDetailIndex")
	public ModelAndView handlingVideoDetailIndex() {
		UserDO currentUser = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/portal/handling/handlingVideoDetail");
		if (null != currentUser) {
			mv.addObject("userId", currentUser.getUserId());
		}
		return mv;
	}

	/**
	 * 统计办案数据
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/getHandlingPage")
	public List<ArchiveExecutionBean> getHandlingPage(PageQuery pageQuery, ArchiveExecutionBean archiveExecutionBean) {
		return handlingService.getHandlingPage(pageQuery, archiveExecutionBean);
	}

	/**
	 * 统计办案音视频
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/getHandlingVideoData")
	public List<Map<String, String>> getHandlingVideoData(PageQuery pageQuery) {
		return handlingService.selectHandling(pageQuery);
	}

	/**
	 * 统计办案出入区人数
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/selectHandlingCount")
	public Map<String, String> selectHandlingCount(String searchTime, String dtStart, String dtEnd) {
		return handlingService.selectHandlingCount(searchTime, dtStart, dtEnd);
	}

	/**
	 * 跳转到出入区人数更多页面
	 **/
	
	@RequestMapping(value = "/handlingCountIndex")
	public String handlingCountIndex() {
		return "/handling/handlingCountIndex";
	}

	/**
	 * 当前月份
	 **/
	
	@RequestMapping(value = "/handlingCountMonth")
	@ResponseBody
	public Map<String, Object> handlingCountMonth() {
		int month = new Date().getMonth() + 1;// 月
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("month", month);
		return map;
	}

	/**
	 * 案件数据
	 **/
	
	@RequestMapping(value = "/optionLineData")
	@ResponseBody
	public Object optionLineData() {
		String httpsUrl = "http://92.175.70.40:7000/Easy7/rest/inquestAlarm/getAlarmInfos?deptIds=&dojo.preventCache=1589689006388";
		String sendPost = sendPost(httpsUrl,"{}");
		return sendPost;
	}
	
	/**
	 * 办案类型
	 **/
	
	@RequestMapping(value = "/optionCaseTypeData")
	@ResponseBody
	public Object optionCaseTypeData() {
		String httpsUrl = "http://92.175.70.40:7000/Easy7/rest/statisticalAnalysis/caseTypeStatistical?inquestDept=&currentUserId=admin&localIp=92.175.70.20&dojo.preventCache=1589688430630";
		String sendPost = sendPost(httpsUrl,"{}");
		return sendPost;
	}
	
	/**
	 * 人员类型
	 **/
	
	@RequestMapping(value = "/optionLinePersonData")
	@ResponseBody
	public Object optionLinePersonData() {
		String httpsUrl = "http://92.175.70.40:7000/Easy7/rest/statisticalAnalysis/caseArchiveTypeStatistical?inquestDept=&localIp=92.175.70.20&dojo.preventCache=1589687627891";
		String sendPost = sendPost(httpsUrl,"{}");
		return sendPost;
	}
	
	/**
	 * 案件案由
	 **/
	
	@RequestMapping(value = "/optionCaselingData")
	@ResponseBody
	public Object optionCaselingData() {
		String httpsUrl = "http://92.175.70.40:7000/Easy7/rest/statisticalAnalysis/getCauseStatistical?inquestDept=&causeName=&localIp=92.175.70.20&dojo.preventCache=1589687746900";
		String sendPost = sendPost(httpsUrl,"{}");
		return sendPost;
	}

	/**
	 * 
	 * @author : cjd
	 * 
	 * @description : post接口 返回结果字符串
	 * 
	 * @params : [url, param]
	 * 
	 * @param url   请求接口
	 * 
	 * @param param 需要的json字符串
	 * 
	 * @return :java.lang.String
	 * 
	 * @date : 17:31 2018/8/1
	 * 
	 */

	public static String sendPost(String url, String param) {
		OutputStreamWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			HttpURLConnection conn = null; // 打开和URL之间的连接
			conn = (HttpURLConnection) realUrl.openConnection(); // 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST"); // POST方法
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			conn.connect(); // 获取URLConnection对象对应的输出流
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8"); // 发送请求参数
			out.write(param); // flush输出流的缓冲
			out.flush(); // 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		} // 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
