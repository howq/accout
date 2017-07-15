package org.bymarx.account.common;

/**
 * VTopic 常量
 *
 * @author howq
 * @create 2017/3/10 14:17
 **/

public class AccountConst {

    /**
     * Cookie的名字
     */
    public static final String COOKIE_NAME = "Account";
    /**
     * Token
     **/
    public static final String TOKEN = "token_";

    /**
     * domain bymarx
     */
    public static final byte DOMAIN_BYMARX = 0;
    /**
     * domain xinminnews
     */
    public static final byte DOMAIN_XINMINNEWS = 1;
    /**
     * domain xinminxuehui
     */
    public static final byte DOMAIN_XINMINXUEHUI = 2;


    /**
     * role subscriber 订阅者
     */
    public static final String ROLE_SUBSCRIBER = "subscriber";
    /**
     * role contributor 投稿者
     */
    public static final String ROLE_CONTRIBUTOR = "contributor";
    /**
     * role author 作者
     */
    public static final String ROLE_AUTHOR = "author";
    /**
     * role editor 编辑
     */
    public static final String ROLE_EDITOR = "editor";
    /**
     * role administrator 管理员
     */
    public static final String ROLE_ADMINISTRATOR = "administrator";
}
