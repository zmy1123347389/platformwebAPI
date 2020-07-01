package com.behere.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.behere.common.annotation.Log;
import com.behere.common.config.FTPConfig;
import com.behere.common.constant.Constant;
import com.behere.common.sms.SmsUtil;
import com.behere.common.utils.ImageUtils;
import com.behere.common.utils.R;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.RequestUtils;
import com.behere.common.utils.StringUtils;
import com.behere.system.domain.AuthVideo;
import com.behere.system.domain.Disturb;
import com.behere.system.domain.QueryParam;
import com.behere.system.domain.ShareUser;
import com.behere.system.domain.SharingPlan;
import com.behere.system.domain.User;
import com.behere.system.domain.UserPic;
import com.behere.system.model.ImageModel;
import com.behere.system.model.InvitationUser;
import com.behere.system.model.UserAuthModel;
import com.behere.system.model.UserModel;
import com.behere.system.model.UserSearchModel;
import com.behere.system.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户操作接口
 * @author Behere
 *
 */
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserController {
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	UserService userService;
	@Autowired
	FTPConfig ftpConfig;

	@Log("发送短信验证码")
	@PostMapping(value = "/sendSMS")
	@ResponseBody
	public R sendSMS(String m) {
		try {
			String verificaCode = StringUtils.getRandomNumber();
			User user = RequestUtils.parseParameter(m, User.class);
			String mobile = user.getMobile();
			redisUtil.set(mobile, verificaCode, Constant.SMS_EXPIRE_SECONDS);
			SmsUtil.sendSMS(mobile, verificaCode);
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.VERIFI_CODE_FAIL);
		}
	}

	@Log("注册")
	@PostMapping(value = "/regist")
	@ResponseBody
	public R regist(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			String verificaCode = redisUtil.get(user.getMobile());
			if (!(user.getVerificaCode().equals(verificaCode))) {
				return R.error(-1, Constant.VERIFI_CODE_WRONG);
			}
			List<User> users = userService.queryUserByMobile(user.getMobile());
			if (!users.isEmpty()) {
				return R.error(-1, Constant.MOIBLE_REGISTED);
			}
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.REGIST_FAIL);
		}
	}

	@Log("登录")
	@PostMapping(value = "/login")
	@ResponseBody
	public R login(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			UserModel userInfo = userService.queryUserByMobileAndPassword(user.getMobile(), user.getPassword());
//			if (userInfo == null) {
//				return R.error(-1, Constant.MOBILE_OR_PASSWORD_WRONG);
//			}
			if (userInfo.getDeleted() == 1) {
			    return R.error(-1, Constant.USER_FREEZE);
            }
            userService.updateUserOnlineStatus(userInfo);
			return R.ok(userInfo);
		} catch (Exception e) {
			return R.error(-1, Constant.LOGIN_FAIL);
		}
	}


	@Log("上传用户头像")
	@PostMapping("/uploadImages")
	@ResponseBody
	public R uploadImages(@RequestParam("userId") String userId,
						  @RequestParam("file") MultipartFile file,
						  @RequestParam("type") int type) {
		ImageModel imageModel = new ImageModel();
		try {
			String picNewName = ImageUtils.uploadImage(file, ftpConfig);
			if (type == Constant.HEAD_PORTRAIT) {
				userService.updateUserHeadPortrait(picNewName, userId);
				userService.updateNeteaseUserIcon(String.valueOf(userId), picNewName);
			} else if (type == Constant.WECHAT_QRCODE) {
				userService.updateWechatQRcode(userId, picNewName);
			} else if (type == Constant.IDENTITY_CARD) {
				userService.updateUserIdentityCard(picNewName, userId);
			} else if (type == Constant.USER_INDEX_IMAGE) {
				userService.updateUserIndexImage(picNewName, userId);
			}
			imageModel.setType(type);
			imageModel.setImage(picNewName);
			return R.ok(imageModel);
		} catch (Exception e) {
			return R.error(-1, Constant.UPLOAD_IMAGE_FAIL);
		}
	}

	@Log("获取用户信息")
	@PostMapping("/getUserById")
	@ResponseBody
	public R getUserById(String m) {
		try {
//			User user = RequestUtils.parseParameter(m, User.class);
			User user = new User();
			user.setId("21522");
			user.setMobile(m);
			UserModel userInfo = userService.queryUserById(user.getId());
			userInfo.setDiamondToFlowerRate(Constant.DIAMOND_TO_FLOWER_RATE);
			return R.ok(userInfo);
		} catch (Exception e) {
			return R.error(-1, Constant.GET_USER_INFO_FAIL);
		}
	}

	@Log("修改用户性别")
	@PostMapping("/updateUserGenderById")
	@ResponseBody
	public R updateUserGenderById(String m) throws IOException {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			if (user != null) {
				userService.updateUserGenderById(user.getId(), user.getGender());
			}
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPDATE_USER_SEX_FAIL);
		}
	}
	
	@Log("更新用户信息")
	@PostMapping("/updateUserById")
	@ResponseBody
	public R updateUserById(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			List<User> users = userService.isExsitNickName(user.getNickName(), user.getId());
			if (!users.isEmpty()) {
				return R.error(-1, Constant.NICK_NAME_EXIST);
			}
			if (user.getAge() == 0) {
				user.setAge(18);
			}
			userService.updateUserInfoById(user);
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPDATE_USER_INFO_FAIL);
		}
	}

	@Log("修改用户密码")
	@PostMapping("/updatePassword")
	@ResponseBody
	public R updatePassword(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			String verificaCode = redisUtil.get(user.getMobile().toString());
			if (!(user.getVerificaCode().equals(verificaCode))) {
				return R.error(-1, Constant.VERIFI_CODE_WRONG);
			}
			userService.updatePassword(user.getPassword(), user.getMobile());
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPDATE_PASSWORD_FAIL);
		}
	}

	@Log("通过ID或者昵称查找用户")
	@PostMapping("/queryUserByIdOrNickName")
	@ResponseBody
	public R queryUserByIdOrNickName(String m,
									 @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
									 @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
            List<UserSearchModel> users = new ArrayList<UserSearchModel>();
			QueryParam queryParam = RequestUtils.parseParameter(m, QueryParam.class);
            PageHelper.startPage(pageNum, pageSize);
			if (StringUtils.isEmpty(queryParam.getIdOrNickName())) {
				UserModel user = userService.queryUserById(queryParam.getUserId());
				if (user != null) {
					queryParam.setGender(user.getGender());
					queryParam.setAuth(user.getAuth());
				}
                users = userService.queryRecommendUsers(queryParam);
            } else {
                users = userService.likeUserByNickName(queryParam);
            }
            PageInfo<UserSearchModel> pageInfo = new PageInfo<UserSearchModel>(users);
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error(-1, Constant.GET_DATA_FAIL);
		}
	}


	@Log("获取认证信息")
	@PostMapping("/queryUserAuth")
	@ResponseBody
	public R queryUserAuth(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			UserAuthModel userAuthModel = userService.queryUserAuth(user.getId());
			return R.ok(userAuthModel);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("提交认证信息")
	@PostMapping("/submitAuth")
	@ResponseBody
	public R submitAuth(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			userService.saveUserAuth(user.getId());
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("用户上传照片")
	@PostMapping("/uploadUserPic")
	@ResponseBody
	public R uploadUserPic(@RequestParam("userId") String userId, @RequestParam("files") MultipartFile[] files) {
		try {
			for (MultipartFile file : files) {
				String picNewName = ImageUtils.uploadImage(file, ftpConfig);
				if (picNewName != null) {
					userService.saveUserPic(StringUtils.getUUID(), picNewName, userId);
				}
			}
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("删除用户照片")
	@PostMapping("/deleteUserPic")
	@ResponseBody
	public R deleteUserPic(String m) {
		try {
			UserPic userPic = RequestUtils.parseParameter(m, UserPic.class);
			userService.deleteUserPic(userPic.getId());
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("获取用户照片")
	@PostMapping("/queryUserPics")
	@ResponseBody
	public R queryUserPics(String m,
						   @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
						   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
			PageHelper.startPage(pageNum, pageSize);
			UserPic userPic = RequestUtils.parseParameter(m, UserPic.class);
			List<UserPic> userPics = userService.queryUserPics(String.valueOf(userPic.getUserId()));
			PageInfo<UserPic> pageInfo = new PageInfo<UserPic>(userPics);
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("退出登录")
	@PostMapping("/logout")
	@ResponseBody
	public R logout(String m) {
		try {
            User user = RequestUtils.parseParameter(m, User.class);
            UserModel userModel = userService.queryUserById(user.getId());
            if (userModel.getOnline() == Constant.DONT_DISTURB) {
                userService.updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.LOGOUT_DONT_DISTURB);
            } else {
                userService.updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.LOGOUT_ONLINE);
            }
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

}