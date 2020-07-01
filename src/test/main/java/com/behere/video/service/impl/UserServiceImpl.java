package com.behere.video.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.StringUtils;
import com.behere.common.utils.UploadVideoUtil;
import com.behere.video.dao.UserDao;
import com.behere.video.domain.AuthVideo;
import com.behere.video.domain.BestFriend;
import com.behere.video.domain.Disturb;
import com.behere.video.domain.QueryParam;
import com.behere.video.domain.ShareUser;
import com.behere.video.domain.User;
import com.behere.video.domain.UserIndex;
import com.behere.video.domain.UserPic;
import com.behere.video.model.InvitationUser;
import com.behere.video.model.UserAuth;
import com.behere.video.model.UserAuthModel;
import com.behere.video.model.UserModel;
import com.behere.video.model.UserSearchModel;
import com.behere.video.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<User> queryUserByMobile(String mobile) {
        return userDao.queryUserByMobile(mobile);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

	@Override
	public UserModel queryUserByMobileAndPassword(String mobile, String password) {
		return userDao.queryUserByMobileAndPassword(mobile, password);
	}

	@Override
	public void updateUserHeadPortrait(String headPortrait, Long userId) {
		userDao.updateUserHeadPortrait(headPortrait, userId);
	}

	@Override
	public UserModel queryUserById(Long userId) {
		return userDao.queryUserById(userId);
	}

	@Override
	public void updateWechatQRcode(long userId, String webchatCode) {
		userDao.updateWechatQRcode(userId, webchatCode);
	}

	@Override
	public List<User> queryUserByNickName(String nickName) {
		return userDao.queryUserByNickName(nickName);
	}

	@Override
	public boolean isExist(String nickName) {
		return queryUserByNickName(nickName).isEmpty() ? false : true;
	}

	@Override
	public void updateUserInfoById(User user) throws Exception {
		userDao.updateUserInfoById(user);
		NeteaseUtils.updateNetease(Constant.NETEASE_UPDATE_ICON, String.valueOf(user.getId()), "name", user.getNickName());
	}

	@Override
	public void updateUserGenderById(long userId, short gender) {
		userDao.updateUserGenderById(userId, gender);
	}

	@Override
	public String updateNeteaseUserIcon(String accid, String icon) throws Exception {
		return NeteaseUtils.updateNetease(Constant.NETEASE_UPDATE_ICON, accid, "icon", icon);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void saveUserWithNetesaToken(User user) {
    	try {
			String token = "";
			user.setMyInvitationCode(StringUtils.toSerialCode(Long.valueOf(StringUtils.getSixRandomNumber())).toUpperCase());
			saveUser(user);
			String neteaseToken = NeteaseUtils.createNeteaseAccount(String.valueOf(user.getId()));
			if (!StringUtils.isEmpty(neteaseToken)) {
				token = StringUtils.parseNeteaseJSON(neteaseToken, "token");
			}
            updateUserNickName(Constant.DEFAULT_NICK_NAME + user.getId(),user.getId());
			updateUserNeteaseToken(token, user.getId());
			NeteaseUtils.createNeteaseVideoUser(user.getId(), token);
		} catch (Exception e) {
    		throw new RuntimeException();
		}
	}

	@Override
	public int updateUserNeteaseToken(String neteaseToken, long userId) {
		return userDao.updateUserNeteaseToken(neteaseToken, userId);
	}

	@Override
	public int updatePassword(String password, String mobile) {
		return userDao.updatePassword(password, mobile);
	}

	@Override
	public int updateServicePrice(int servicePrice, long userId) {
		return userDao.updateServicePrice(servicePrice, userId);
	}

    @Override
    public List<UserSearchModel> queryUsersByGender(short gender) {
        return userDao.queryUsersByGender(gender);
    }

    @Override
    public List<UserSearchModel> likeUserByNickName(QueryParam queryParam) {
        return userDao.likeUserByNickName(queryParam);
    }

	@Override
	public List<User> isExsitNickName(String nickName, long userId) {
		return userDao.isExsitNickName(nickName, userId);
	}

	@Override
	public void updateUserIdentityCard(String identityCard, Long userId) {
		userDao.updateUserIdentityCard(identityCard, userId);
	}

	@Override
	public int updateUserIndexImage(String indexImage, long userId) {
		return userDao.updateUserIndexImage(indexImage, userId);
	}

	@Override
	public int updateUserAuthVideo(AuthVideo authVideo) {
    	String url = "";
    	try {
			String result = UploadVideoUtil.getNeteaseVideoInformation(authVideo.getVid());
			JSONObject json = JSONObject.parseObject(result);
			JSONObject ret = JSONObject.parseObject(json.get("ret").toString());
			url= ret.get("origUrl").toString();
		} catch (Exception e) {
    		e.printStackTrace();
		}
		return userDao.updateUserAuthVideo(url, authVideo.getId());
	}

	@Override
	public UserAuthModel queryUserAuth(long userId) {
		return userDao.queryUserAuth(userId);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public int saveUserAuth(long userId) {
    	try {
            List<UserAuth> userAuths = queryUserAuths(userId);
            UserModel user = queryUserById(userId);
            if (userAuths.size() == 0 && (user.getAuth() == 0 || user.getAuth() == -2)) {
                userDao.saveUserAuth(userId);
                userDao.updateUserAuth(-1, userId);
                String content = "您的认证资料已成功提交，小颜会尽快处理，请耐心等待哦。";
                String msg = NeteaseUtils.setMsgExtMap(null, null, null, null, null, MsgConstant.AUTH_PROCESSING, content);
                NeteaseUtils.sendMsg(msg, userId);
            }
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return Constant.SUCCESS;
	}

	@Override
	public UserIndex queryUserIndex(long userId) {
		return userDao.queryUserIndex(userId);
	}

	@Override
	public int saveUserPic(String id, String picUrl, long userId) {
		return userDao.saveUserPic(id, picUrl, userId);
	}

	@Override
	public List<UserPic> queryUserPics(long userId) {
		return userDao.queryUserPics(userId);
	}

	@Override
	public int deleteUserPic(String picId) {
		return userDao.deleteUserPic(picId);
	}

	@Override
	public UserModel queryUserByUnionId(String unionId) {
		return userDao.queryUserByUnionId(unionId);
	}

    @Override
    public int updateUserNickName(String nickName, long userId) {
        return userDao.updateUserNickName(nickName, userId);
    }

	@Override
	public List<UserSearchModel> queryRecommendUsers(QueryParam queryParam) {
        UserModel userModel = userDao.queryUserById(queryParam.getUserId());
		return userDao.queryRecommend(null, userModel.getGender() , userModel.getAuth());
	}

    @Override
    public int updateUserOnOrOffOnline(String userId, int online) {
        return userDao.updateUserOnOrOffOnline(userId, online);
    }

    @Override
    public BestFriend queryMyPkInformation(long userId) {
        return userDao.queryMyPkInformation(userId);
    }

    @Override
    public List<UserModel> lockUser(long fromUser, long toUser) {
        return userDao.lockUser(fromUser, toUser);
    }

    @Override
    public int updateUserAuth(int userAuth, long userId) {
        return userDao.updateUserAuth(userAuth, userId);
    }

    @Override
    public List<UserAuth> queryUserAuths(long userId) {
        return userDao.queryUserAuths(userId);
    }

	@Override
	public int updateUserOnlineStatus(UserModel user) {
		if (user.getOnline() == Constant.LOGOUT_DONT_DISTURB || user.getOnline() == Constant.DONT_DISTURB) {
			updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.DONT_DISTURB);
		} else {
			updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.ONLINE);
		}
		return Constant.SUCCESS;
	}

    @Override
    public void updateUserFaceTime(long customerId, long serviceId, int status) {
        userDao.updateUserFaceTime(customerId, serviceId, status);
    }

}