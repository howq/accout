package org.bymarx.account.common.enums;

/**
 * @author howq
 * @create 2017-07-16 0:31
 **/
public enum USERROLEENUM {

    USERROLEENUM_SUBSCRIBER("subscriber","bm_capabilities  {s:10:\"subscriber\";b:1;}", "0"),
    USERROLEENUM_CONTRIBUTOR("contributor","{s:11:\"contributor\";b:1;}", "1"),
    USERROLEENUM_AUTHOR("author","{s:6:\"author\";b:1;}", "2"),
    USERROLEENUM_EDITOR("editor","{s:6:\"editor\";b:1;}", "7"),
    USERROLEENUM_ADMINISTRATOR("administrator","a:1:{s:13:\"administrator\";b:1;}", "10");

    USERROLEENUM(String role, String capabilities, String userLevel) {
        this.role = role;
        this.capabilities = capabilities;
        this.userLevel = userLevel;
    }

    private String role;

    private String capabilities;

    private String userLevel;

    /**
     * Getter for property 'role'.
     *
     * @return Value for property 'role'.
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for property 'role'.
     *
     * @param role Value to set for property 'role'.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Getter for property 'capabilities'.
     *
     * @return Value for property 'capabilities'.
     */
    public String getCapabilities() {
        return capabilities;
    }

    /**
     * Setter for property 'capabilities'.
     *
     * @param capabilities Value to set for property 'capabilities'.
     */
    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    /**
     * Getter for property 'userLevel'.
     *
     * @return Value for property 'userLevel'.
     */
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * Setter for property 'userLevel'.
     *
     * @param userLevel Value to set for property 'userLevel'.
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

}
