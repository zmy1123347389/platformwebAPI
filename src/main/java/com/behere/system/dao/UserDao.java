package com.behere.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.system.domain.*;
import com.behere.system.model.*;

@Mapper
public interface UserDao {

	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return
	 */
    List<User> queryUserByMobile(String mobile);

	/**
	 * 通过手机号和密码查找用户
	 * @param mobile
	 * @param password
	 * @return
	 */
	UserModel queryUserByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户头像
     */
    void updateUserHeadPortrait(@Param("headPortrait") String headPortrait, @Param("userId") String userId);

	/**
	 * 更新用户身份证
	 * @param identityCard
	 * @param userId
	 */
	void updateUserIdentityCard(@Param("identityCard") String identityCard, @Param("userId") String userId);

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
	void updateWechatQRcode(@Param("userId") String userId, @Param("wechatCode") String webchatCode);
	
	/**
	 * 通过昵称查询用户
	 * @param nickName
	 * @return
	 */
	List<User> queryUserByNickName(@Param("nickName") String nickName);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	void updateUserInfoById(User user);
	
	/**
	 * 通过用户ID修改用户性别
	 * @param userId
	 * @param gender
	 */
	void updateUserGenderById(@Param("userId") String userId, @Param("gender") short gender);

	/**
	 * 更新用户网易云信token
	 * @param neteaseToken
	 * @param userId
	 * @return
	 */
	int updateUserNeteaseToken(@Param("neteaseToken") String neteaseToken, @Param("userId") String userId);

	/**
	 *   修改密码
	 * @param password
	 * @param mobile
	 * @return
	 */
	int updatePassword(@Param("password") String password, @Param("mobile") String mobile);

	/**
	 * 修改服务价格
	 * @param servicePrice
	 * @param userId
	 * @return
	 */
	int updateServicePrice(@Param("servicePrice") int servicePrice, @Param("userId") String userId);

	/**
	 * 更新用户vip状态
	 * @param vip
	 * @param userId
	 * @return
	 */
	int updateUserVip(@Param("vip") short vip, @Param("userId") String userId);

	/**
	 * 通过昵称查找用户
	 * @param queryParam
	 * @return
	 */
	List<UserSearchModel> likeUserByNickName(QueryParam queryParam);

	/**
	 * 查询所有用户
	 * @return
	 */
	List<UserModel> queryAllUsers();

	int reduceFlower(@Param("userId") String userId, @Param("num") double num);

	/**
	 * 通过userId扣除余额、钻石、鲜花
	 * @param userId
	 * @param num
	 * @return
	 */
	int reduceBalance(@Param("userId") String userId, @Param("num") long num);

	int addBalance(@Param("userId") String userId, @Param("num") long num);

	/**
	 * 除了自己是否有重名昵称
	 * @param nickName
	 * @param userId
	 * @return
	 */
	List<User> isExsitNickName(@Param("nickName") String nickName, @Param("userId") String userId);

	/**
	 * 更新认证 封面头像
	 * @param indexImage
	 * @param userId
	 * @return
	 */
	int updateUserIndexImage(@Param("indexImage") String indexImage, @Param("userId") String userId);

	/**
	 * 获取用户认证信息
	 * @param userId
	 * @return
	 */
	UserAuthModel queryUserAuth(@Param("userId") String userId);

	/**
	 * 提交用户认证信息
	 * @param userId
	 * @return
	 */
	int saveUserAuth(@Param("userId") String userId);

	/**
	 * 查询用户首页资料
	 * @param userId
	 * @return
	 */
	UserIndex queryUserIndex(@Param("userId") String userId);

	/**
	 * 上传用户照片
	 * @param id
	 * @param picUrl
	 * @param userId
	 * @return
	 */
	int saveUserPic(@Param("id") String id, @Param("picUrl") String picUrl, @Param("userId") String userId);


	/**
	 * 获取用户照片
	 * @param userId
	 * @return
	 */
	List<UserPic> queryUserPics(@Param("userId") String userId);

	/**
	 * 删除用户照片
	 * @param picId
	 * @return
	 */
	int deleteUserPic(@Param("picId") String picId);

	/**
	 * 通过unionId获取用户信息
	 * @param unionId
	 * @return
	 */
	UserModel queryUserByUnionId(@Param("unionId") String unionId);


	/**
	 * 通过邀请码获取用户信息
	 * @param invitationCode
	 * @return
	 */
	UserModel queryUserByInvitationCode(@Param("invitationCode") String invitationCode);

	List<UserSearchModel> queryRecommend(@Param("list") List<String> userIds, @Param("gender") int gender, @Param("auth") int auth);

	int updateUserOnOrOffOnline(@Param("userId") String userId, @Param("online") int online);

    /**
     * PK榜个人信息
     * @param userId
     * @return
     */
	BestFriend queryMyPkInformation(@Param("userId") String userId);

    /**
     * 行级锁
     * @param fromUserId
     * @param toUser
     * @return
     */
	List<UserModel> lockUser(@Param("fromUser") long fromUserId, @Param("toUser") long toUser);

	/**
	 * 修改用户认证状态
	 * @param userAuth
	 * @param userId
	 * @return
	 */
	int updateUserAuth(@Param("userAuth") int userAuth, @Param("userId") String userId);

    /**
     * 查询用户提交的认证信息
     * @param userId
     * @return
     */
	List<UserAuth> queryUserAuths(@Param("userId") String userId);

    void updateUserFaceTime(@Param("customerId") long customerId, @Param("serviceId") long serviceId, @Param("status") int status);
}