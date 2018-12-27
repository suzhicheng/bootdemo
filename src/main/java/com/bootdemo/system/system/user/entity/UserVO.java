package com.bootdemo.system.system.user.entity;

/**
 * @author gaoyuzhe
 * @date 2017/12/15.
 */
public class UserVO {
    /**
     * 更新的用户对象
     */
    private UserEntity userDO = new UserEntity();
    /**
     * 旧密码
     */
    private String pwdOld;
    /**
     * 新密码
     */
    private String pwdNew;

    public UserEntity getUserDO() {
        return userDO;
    }

    public void setUserDO(UserEntity userDO) {
        this.userDO = userDO;
    }

    public String getPwdOld() {
        return pwdOld;
    }

    public void setPwdOld(String pwdOld) {
        this.pwdOld = pwdOld;
    }

    public String getPwdNew() {
        return pwdNew;
    }

    public void setPwdNew(String pwdNew) {
        this.pwdNew = pwdNew;
    }
}
