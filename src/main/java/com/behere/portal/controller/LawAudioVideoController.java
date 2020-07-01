package com.behere.portal.controller;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.portal.service.ILawAudioVideoService;
import com.behere.portal.util.PageList;
import com.behere.portal.util.PageQuery;
import com.behere.portal.util.RespJson;
@Controller
@RequestMapping("lawenAudioVideo")
public class LawAudioVideoController {
	@Resource
	private ILawAudioVideoService lawAudioVideoService;
	
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
	@ResponseBody
	@RequestMapping("/getAllAudioVideoByPage")
	public PageList<Map<String, String>> getAllAudioVideo(PageQuery pageQuery, String docType, 
			String unitNumber, String isRelative, String upLoadDate, String picDate, String ajbh) {
		return lawAudioVideoService.selectAllAudioVideoByPage(pageQuery, docType, unitNumber, isRelative, upLoadDate, picDate, ajbh);
	}
	
	/**
	 * 统计各派出所执法音视频关联柱状图
	 * @param type 日期类型
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVideoRelateEchart")
	public Map<String, List<Integer>> getVideoRelateEchart(String type, String startDate, String endDate) {
		return lawAudioVideoService.selectVideoRelateEchart(type, startDate, endDate);
	}
	
	/**
	 * 保存执法音视频关联案件的数据
	 * @param ajbh 案件编号
	 * @param videoId 视频编号
	 * @param uploadUnit 视频上传单位
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveVideoRelatedCase")
	public RespJson saveVideoRelatedCase(String ajbh, String videoId, String uploadUnit) {
		lawAudioVideoService.updateVideoRelatedCase(ajbh, videoId, uploadUnit);
		return new RespJson("1", "更新成功");
	}
}