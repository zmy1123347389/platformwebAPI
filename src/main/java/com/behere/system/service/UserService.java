package com.behere.system.service;

import java.util.List;

import com.behere.system.domain.*;
import com.behere.system.model.*;

public interface UserService {

	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return
	 */
    List<User> queryUserByMobile(String mobile);

	/**
	 * 根据手机号和密码查询用户
	 * @param mobile
	 * @param password
	 * @return
	 */
	UserModel queryUserByMobileAndPassword(String mobile, String password);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户头像
     */
    void updateUserHeadPortrait(String headPortrait, String userId);

    /**
     * 通过用户ID获取用户信息
     * @param userId
     * @return
     */
	UserModel queryUserById(String userId);
	
	/**
	 * 更新用户微信二维码
	 * @param userId
	 * @param webchatCode
	 */
	void updateWechatQRcode(String userId, String webchatCode);
	
	/**
	 * 通过昵称查询用户
	 * @param nickName
	 * @return
	 */
	List<User> queryUserByNickName(String nickName);
	
	/**
	 * 通过昵称判断用户是否已经存在
	 * @param nickName
	 * @return
	 */
	boolean isExist(String nickName);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	void updateUserInfoById(User user) throws Exception;
	
	/**
	 * 通过用户ID修改用户性别
	 * @param userId
	 * @param gender
	 */
	void updateUserGenderById(String userId, short gender);

	/**
	 * 更新用户网易云信token
	 * @param neteaseToken
	 * @param userId
	 * @return
	 */
	int updateUserNeteaseToken(String neteaseToken, String userId);

	/**
	 *   修改密码
	 * @param password
	 * @param mobile
	 * @return
	 */
	int updatePassword(String password, String mobile);


	/**
	 * 修改服务价格
	 * @param servicePrice
	 * @param userId
	 * @return
	 */
	int updateServicePrice(int servicePrice, String userId);

	/**
	 * 通过ID或者昵称查找用户
	 * @param queryParam
	 * @return
	 */
	List<UserSearchModel> likeUserByNickName(QueryParam queryParam);

	/**
	 * 除了自己是否有重名昵称
	 * @param nickName
	 * @param userId
	 * @return
	 */
	List<User> isExsitNickName(String nickName, String userId);

	/**
	 * 更新用户身份证图片
	 * @param identityCard
	 * @param userId
	 */
	void updateUserIdentityCard(String identityCard, String userId);

	/**
	 * 更新认证 封面头像
	 * @param indexImage
	 * @param userId
	 * @return
	 */
	int updateUserIndexImage(String indexImage, String userId);

	/**
	 * 获取用户认证信息
	 * @param userId
	 * @return
	 */
	UserAuthModel queryUserAuth(String userId);

	/**
	 * 提交用户认证信息
	 * @param userId
	 * @return
	 */
	int saveUserAuth(String userId);

	/**
	 * 查询用户首页资料
	 * @param userId
	 * @return
	 */
	UserIndex queryUserIndex(String userId);

	/**
	 * 更新网易云信用户头像
	 * @param accid
	 * @param icon
	 */
	String updateNeteaseUserIcon(String accid, String icon) throws Exception;

	/**
	 * 上传用户照片
	 * @param id
	 * @param picUrl
	 * @param userId
	 * @return
	 */
	int saveUserPic(String id, String picUrl,  String userId);

	/**
	 * 获取用户照片
	 * @param userId
	 * @return
	 */
	List<UserPic> queryUserPics(String userId);

	/**
	 * 删除用户照片
	 * @param picId
	 * @return
	 */
	int deleteUserPic(String picId);

	/**
	 * 通过unionId获取用户信息
	 * @param unionId
	 * @return
	 */
	UserModel queryUserByUnionId(String unionId);

    List<UserSearchModel> queryRecommendUsers(QueryParam queryParam);

    int updateUserOnOrOffOnline(String userId, int online);

    /**
     * PK榜个人信息
     * @param userId
     * @return
     */
    BestFriend queryMyPkInformation(String userId);

    /**
     * 行级锁
     * @param fromUser
     * @param toUser
     * @return
     */
	List<UserModel> lockUser(long fromUser, long toUser);

	/**
	 * 修改用户认证状态
	 * @param userAuth
	 * @param userId
	 * @return
	 */
	int updateUserAuth(int userAuth, String userId);

    /**
     * 查询用户提交的认证信息
     * @param userId
     * @return
     */
    List<UserAuth> queryUserAuths(String userId);

	/**
	 *
	 * @param user
	 * @return
	 */
	int updateUserOnlineStatus(UserModel user);

    /**
     * 更新通话/空闲状态
     * @param customerId
     * @param serviceId
     * @param status
     */
    void updateUserFaceTime(long customerId, long serviceId, int status);
}