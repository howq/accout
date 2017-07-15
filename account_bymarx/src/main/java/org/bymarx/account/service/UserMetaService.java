package org.bymarx.account.service;

import org.bymarx.account.dto.wordpress.UserInfo;

/**
 * @author howq
 * @create 2017-07-15 10:22
 **/
public interface UserMetaService {
    void addUserMeta(UserInfo userInfo,String domain);
}
