package com.behere.system.model;

import com.behere.common.constant.Constant;
import com.behere.common.utils.StringUtils;

public class UserModel {
    private long id;
    private String headPortrait;
    private String nickName;
    private String city;
    private int age;
    private String professionName;
    private String signature;
    private String wechatCode;
    private String mobile;
    private short gender;
    private String neteaseToken;
    private int servicePrice;
    private int vip;
    private long balance;
    private int online;
    private int auth;
    private long flower;
    private int diamondToFlowerRate;
    private String myInvitationCode;
    private String invitationCode;
    private long totalFlower;
    private int faceTime;
    private short deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getNeteaseToken() {
        return neteaseToken;
    }

    public void setNeteaseToken(String neteaseToken) {
        this.neteaseToken = neteaseToken;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public long getFlower() {
        return flower;
    }

    public void setFlower(long flower) {
        this.flower = flower;
    }

    public int getDiamondToFlowerRate() {
        return diamondToFlowerRate;
    }

    public void setDiamondToFlowerRate(int diamondToFlowerRate) {
        this.diamondToFlowerRate = diamondToFlowerRate;
    }

    public String getMyInvitationCode() {
        return myInvitationCode;
    }

    public void setMyInvitationCode(String myInvitationCode) {
        this.myInvitationCode = myInvitationCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public long getTotalFlower() {
        return totalFlower;
    }

    public void setTotalFlower(long totalFlower) {
        this.totalFlower = totalFlower;
    }

    public int getFaceTime() {
        return faceTime;
    }

    public void setFaceTime(int faceTime) {
        this.faceTime = faceTime;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }
}