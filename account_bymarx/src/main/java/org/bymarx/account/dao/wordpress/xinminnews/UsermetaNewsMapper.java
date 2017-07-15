package org.bymarx.account.dao.wordpress.xinminnews;

import org.bymarx.account.model.Usermeta;

public interface UsermetaNewsMapper {
    int deleteByPrimaryKey(Long umetaId);

    int insert(Usermeta record);

    int insertSelective(Usermeta record);

    Usermeta selectByPrimaryKey(Long umetaId);

    int updateByPrimaryKeySelective(Usermeta record);

    int updateByPrimaryKeyWithBLOBs(Usermeta record);

    int updateByPrimaryKey(Usermeta record);
}