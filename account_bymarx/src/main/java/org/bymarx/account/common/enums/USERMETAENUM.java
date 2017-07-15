package org.bymarx.account.common.enums;

/**
 * @author howq
 * @create 2017-07-15 23:36
 **/
public enum USERMETAENUM {

    USERMETAENUM_0("0", "nickname"),
    USERMETAENUM_1("1", "first_name"),
    USERMETAENUM_2("2", "last_name"),
    USERMETAENUM_3("3", "description"),
    USERMETAENUM_4("4", "rich_editing"),
    USERMETAENUM_5("5", "comment_shortcuts"),
    USERMETAENUM_6("6", "admin_color"),
    USERMETAENUM_7("7", "use_ssl"),
    USERMETAENUM_8("8", "show_admin_bar_front"),
    USERMETAENUM_9("9", "locale"),
    USERMETAENUM_10("10", "capabilities"),
    USERMETAENUM_11("11", "user_level");
//    USERMETAENUM_12("12", "dismissed_wp_pointers");

    private String code;

    private String key;

    USERMETAENUM(String code, String key) {
        this.code = code;
        this.key = key;
    }

    /**
     * Getter for property 'code'.
     *
     * @return Value for property 'code'.
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for property 'code'.
     *
     * @param code Value to set for property 'code'.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for property 'key'.
     *
     * @return Value for property 'key'.
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter for property 'key'.
     *
     * @param key Value to set for property 'key'.
     */
    public void setKey(String key) {
        this.key = key;
    }

    public static String getOneKey(String code) {
        for(USERMETAENUM usermetaenum : USERMETAENUM.values()) {
            if(code.equals(usermetaenum.getCode())) {
                return usermetaenum.getKey();
            }
        }
        return null;
    }
}
