package org.bymarx.account.model;

public class Usermeta {
    private Long umetaId;

    private Long userId;

    private String metaKey;

    private String metaValue;

    public Usermeta(Long userId) {
        this.userId = userId;
    }

    public Long getUmetaId() {
        return umetaId;
    }

    public void setUmetaId(Long umetaId) {
        this.umetaId = umetaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = this.userId;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey == null ? null : metaKey.trim();
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue == null ? null : metaValue.trim();
    }
}