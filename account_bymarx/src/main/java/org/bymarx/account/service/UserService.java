package org.bymarx.account.service;

import org.bymarx.account.dto.wordpress.UserInfo;

/**
 * @author howq
 * @create 2017-07-11 下午4:09
 **/
public interface UserService {

    boolean isLogin(String username, String pwd);

    void addUser(UserInfo userInfo,byte domain);
}