package org.bymarx.account.dao.wordpress.xinminnews;

import org.bymarx.account.model.User;

public interface UserNewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByUserLogin(String userLogin);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}