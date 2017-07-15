package org.bymarx.account.dao.wordpress.xinminxuehui;

import org.bymarx.account.model.Usermeta;

public interface UsermetaXhMapper {
    int deleteByPrimaryKey(Long umetaId);

    int insert(Usermeta record);

    int insertSelective(Usermeta record);

    Usermeta selectByPrimaryKey(Long umetaId);

    int updateByPrimaryKeySelective(Usermeta record);

    int updateByPrimaryKeyWithBLOBs(Usermeta record);

    int updateByPrimaryKey(Usermeta record);
}