package com.behere.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.RedisUtil;
import com.behere.system.dao.UserDao;
import com.behere.system.domain.BestFriend;
import com.behere.system.domain.QueryParam;
import com.behere.system.domain.User;
import com.behere.system.domain.UserIndex;
import com.behere.system.domain.UserPic;
import com.behere.system.model.UserAuth;
import com.behere.system.model.UserAuthModel;
import com.behere.system.model.UserModel;
import com.behere.system.model.UserSearchModel;
import com.behere.system.service.UserService;

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
	public void updateUserHeadPortrait(String headPortrait, String userId) {
		userDao.updateUserHeadPortrait(headPortrait, userId);
	}

	@Override
	public UserModel queryUserById(String userId) {
		return userDao.queryUserById(userId);
	}

	@Override
	public void updateWechatQRcode(String userId, String webchatCode) {
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
	public void updateUserGenderById(String userId, short gender) {
		userDao.updateUserGenderById(userId, gender);
	}

	@Override
	public String updateNeteaseUserIcon(String accid, String icon) throws Exception {
		return NeteaseUtils.updateNetease(Constant.NETEASE_UPDATE_ICON, accid, "icon", icon);
	}

	@Override
	public int updateUserNeteaseToken(String neteaseToken, String userId) {
		return userDao.updateUserNeteaseToken(neteaseToken, userId);
	}

	@Override
	public int updatePassword(String password, String mobile) {
		return userDao.updatePassword(password, mobile);
	}

	@Override
	public int updateServicePrice(int servicePrice, String userId) {
		return userDao.updateServicePrice(servicePrice, userId);
	}

    @Override
    public List<UserSearchModel> likeUserByNickName(QueryParam queryParam) {
        return userDao.likeUserByNickName(queryParam);
    }

	@Override
	public List<User> isExsitNickName(String nickName, String userId) {
		return userDao.isExsitNickName(nickName, userId);
	}

	@Override
	public void updateUserIdentityCard(String identityCard, String userId) {
		userDao.updateUserIdentityCard(identityCard, userId);
	}

	@Override
	public int updateUserIndexImage(String indexImage, String userId) {
		return userDao.updateUserIndexImage(indexImage, userId);
	}

	@Override
	public UserAuthModel queryUserAuth(String userId) {
		return userDao.queryUserAuth(userId);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public int saveUserAuth(String userId) {
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
	public UserIndex queryUserIndex(String userId) {
		return userDao.queryUserIndex(userId);
	}

	@Override
	public int saveUserPic(String id, String picUrl, String userId) {
		return userDao.saveUserPic(id, picUrl, userId);
	}

	@Override
	public List<UserPic> queryUserPics(String userId) {
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
	public List<UserSearchModel> queryRecommendUsers(QueryParam queryParam) {
        UserModel userModel = userDao.queryUserById(queryParam.getUserId());
		return userDao.queryRecommend(null, userModel.getGender() , userModel.getAuth());
	}

    @Override
    public int updateUserOnOrOffOnline(String userId, int online) {
        return userDao.updateUserOnOrOffOnline(userId, online);
    }

    @Override
    public BestFriend queryMyPkInformation(String userId) {
        return userDao.queryMyPkInformation(userId);
    }

    @Override
    public List<UserModel> lockUser(long fromUser, long toUser) {
        return userDao.lockUser(fromUser, toUser);
    }

    @Override
    public int updateUserAuth(int userAuth, String userId) {
        return userDao.updateUserAuth(userAuth, userId);
    }

    @Override
    public List<UserAuth> queryUserAuths(String userId) {
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