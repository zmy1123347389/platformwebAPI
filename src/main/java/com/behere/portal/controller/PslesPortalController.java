package com.behere.portal.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.behere.common.domain.UserDO;

@Controller
@RequestMapping(value = "/")
public class PslesPortalController {
	
	/**
	 * 跳转到执法监督管理首页
	 **/
	@RequestMapping(value = "/pslesIndex")
	public ModelAndView pslesIndex() {
		UserDO currentUser = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/portal/mainDashboard/basic");
		if (null != currentUser) {
			mv.addObject("userName", currentUser.getUsername());
		}
		return mv;
	}
	
	/**
	 * 跳转到执法问题预警页面
	 **/
	
	@RequestMapping(value = "/lawProblemIndex")
	public String lawProblemIndex() {
		return "/portal/lawProblemWarning/lawProblem";
	}
	
	/**
	 * 跳转到执法问题案件明细页面
	 **/
	
	@RequestMapping(value = "/lawCaseDetailIndex")
	public ModelAndView lawCaseDetailIndex() {
		UserDO currentUser = null;
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/portal/lawProblemWarning/lawCaseDetail");
		if (null != currentUser) {
			mv.addObject("userId", currentUser.getUserId());
		}
		return mv;
	}
	
	/**
	 * 跳转到执法音视频管理页面
	 **/
	
	@RequestMapping(value = "/lawVideoIndex")
	public String lawVideoIndex() {
		return "/portal/lawAudioAndVideo/lawVideo";
	}
	
	/**
	 * 跳转到派出所视频监控页面
	 **/
	
	@RequestMapping(value = "/policeMonitorIndex")
	public String policeMonitorIndex() {
		return "/portal/policeMonitor/policeMonitorList";
	}
	
	/**
	 * 跳转到执法监督管理首页
	 **/
	
	@RequestMapping(value = "/allCase")
	public String allCaseIndex() {
		return "/portal/allCase/allCase";
	}
	
	/**
	 * 跳转到执法监督管理登录页面
	 * @throws IOException
	 **/
	
	@RequestMapping(value = "/pslesLogin")
	public String pslesLogin(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "/portal/mainDashboard/login";
	}
	
	/**
	 * 用户登录验证
	 * @param userName
	 * @param password
	 * @param isRemember
	 * @param validateCode
	 * @param model
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/authc/pslesLogin")
	public String loginAuth(String userName, String password,
			boolean isRemember, String captcha, Model model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		UserDO user = null;
		// 判断用户是否已经登录
		if (user == null && user.getUserId().equals(userName)) {
			return "redirect:/";
		}
		if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)) {
			return "redirect:/pslesLogin";
		}
		/*如果登陆成功，就构建用户的门户菜单，写入登陆日志，并跳转到登陆成功页面*/
		if (true) {
			return "redirect:/pslesIndex";
		} else {
			model.addAttribute("authcFailResult", "");
			return pslesLogin(model, request, response);
		}
	}
	
	/**
	 * 用户注销,同时清除所有cookies
	 */
	
	@RequestMapping(value = "/pslesLogout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		return "redirect:/mainDashboard/login";
	}
}