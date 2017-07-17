package org.bymarx.account.model;

import org.bymarx.account.common.AccountConst;
import org.bymarx.account.common.enums.USERMETAENUM;
import org.bymarx.account.common.enums.USERROLEENUM;
import org.bymarx.account.dto.wordpress.UserInfo;

import java.util.ArrayList;

/**
 * @author howq
 * @create 2017-07-15 23:08
 **/
public class UsermetaCollection {

    /**
     * 用户元素的初始容量
     */
    private static final int metaCapacity = 12;

    ArrayList<Usermeta> usermetas = new ArrayList<>(metaCapacity);

    UserInfo userInfo;

    byte domain;

    Long userId;

    String table_pre;

    String bm_capabilities;

    String user_level;

    public UsermetaCollection(UserInfo userInfo, byte domain, Long id) {
        this.userInfo = userInfo;
        this.domain = domain;
        this.userId = id;

        if (this.domain != AccountConst.DOMAIN_BYMARX) {
            table_pre = "bm_";
        } else {
            table_pre = "wp_";
        }
        if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_SUBSCRIBER.getRole())){
            bm_capabilities = USERROLEENUM.USERROLEENUM_SUBSCRIBER.getCapabilities();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_CONTRIBUTOR.getRole())){
            bm_capabilities = USERROLEENUM.USERROLEENUM_CONTRIBUTOR.getCapabilities();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_AUTHOR.getRole())){
            bm_capabilities = USERROLEENUM.USERROLEENUM_AUTHOR.getCapabilities();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_EDITOR.getRole())){
            bm_capabilities = USERROLEENUM.USERROLEENUM_EDITOR.getCapabilities();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_ADMINISTRATOR.getRole())){
            bm_capabilities = USERROLEENUM.USERROLEENUM_ADMINISTRATOR.getCapabilities();
        }
        if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_SUBSCRIBER.getRole())){
            user_level = USERROLEENUM.USERROLEENUM_SUBSCRIBER.getUserLevel();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_CONTRIBUTOR.getRole())){
            user_level = USERROLEENUM.USERROLEENUM_CONTRIBUTOR.getUserLevel();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_AUTHOR.getRole())){
            user_level = USERROLEENUM.USERROLEENUM_AUTHOR.getUserLevel();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_EDITOR.getRole())){
            user_level = USERROLEENUM.USERROLEENUM_EDITOR.getUserLevel();
        }else if(userInfo.getRole().equals(USERROLEENUM.USERROLEENUM_ADMINISTRATOR.getRole())){
            user_level = USERROLEENUM.USERROLEENUM_ADMINISTRATOR.getUserLevel();
        }

        this.genericUserMetas();
    }

    private void genericUserMetas() {
        int i = 0;
        for (; i < metaCapacity; i++) {
            Usermeta usermeta = new Usermeta(this.userId);
            usermetas.add(usermeta);
        }
        i = 0;
        for (USERMETAENUM usermetaenum : USERMETAENUM.values()) {
            if (10 != i && 11 != i) {
                usermetas.get(i).setMetaKey(usermetaenum.getKey());
            }
            switch (i) {
                case 0:
                    usermetas.get(i).setMetaValue(userInfo.getUser_login());
                    break;
                case 1:
                    usermetas.get(i).setMetaValue(userInfo.getFirst_name());
                    break;
                case 2:
                    usermetas.get(i).setMetaValue(userInfo.getLast_name());
                    break;
                case 3:
                    usermetas.get(i).setMetaValue("");
                    break;
                case 4:
                    usermetas.get(i).setMetaValue("true");
                    break;
                case 5:
                    usermetas.get(i).setMetaValue("false");
                    break;
                case 6:
                    usermetas.get(i).setMetaValue("fresh");
                    break;
                case 7:
                    usermetas.get(i).setMetaValue("0");
                    break;
                case 8:
                    usermetas.get(i).setMetaValue("true");
                    break;
                case 9:
                    usermetas.get(i).setMetaValue("");
                    break;
                case 10:
                    usermetas.get(i).setMetaKey(table_pre + usermetaenum.getKey());
                    usermetas.get(i).setMetaValue(bm_capabilities);
                    break;
                case 11:
                    usermetas.get(i).setMetaKey(table_pre + usermetaenum.getKey());
                    usermetas.get(i).setMetaValue(user_level);
                    break;
                default:
                    break;
            }
            i++;
        }
    }

    /**
     * Getter for property 'arrayList'.
     *
     * @return Value for property 'arrayList'.
     */
    public ArrayList<Usermeta> getArrayList() {
        return usermetas;
    }

    /**
     * Setter for property 'arrayList'.
     *
     * @param arrayList Value to set for property 'arrayList'.
     */
    public void setArrayList(ArrayList<Usermeta> arrayList) {
        this.usermetas = arrayList;
    }

    /**
     * Getter for property 'userInfo'.
     *
     * @return Value for property 'userInfo'.
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Setter for property 'userInfo'.
     *
     * @param userInfo Value to set for property 'userInfo'.
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Getter for property 'domain'.
     *
     * @return Value for property 'domain'.
     */
    public byte getDomain() {
        return domain;
    }

    /**
     * Setter for property 'domain'.
     *
     * @param domain Value to set for property 'domain'.
     */
    public void setDomain(byte domain) {
        this.domain = domain;
    }

    /**
     * Getter for property 'userId'.
     *
     * @return Value for property 'userId'.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Setter for property 'userId'.
     *
     * @param userId Value to set for property 'userId'.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
