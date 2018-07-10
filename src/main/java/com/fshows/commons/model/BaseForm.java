package com.fshows.commons.model;

public class BaseForm {

    //清算方ID
    private String appId;

    //清算平台商户ID
    private String lpStoreId;

    private String method;

    private String sign;

    private String version = "1.0";

    private String content;

    private String notifyUrl;

    /**支付创建时间. */
    private Integer createDay;

    public BaseForm() {

    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getLpStoreId() {
        return lpStoreId;
    }

    public void setLpStoreId(String lpStoreId) {
        this.lpStoreId = lpStoreId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public Integer getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Integer createDay) {
        this.createDay = createDay;
    }
}
