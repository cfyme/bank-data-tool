package com.fshows.commons.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlipayContactInfo {

    /**
     * 联系人姓名
     */
    //@NotBlank
    //@Length(max = 64)
    private String name;
    /**
     * 电话
     */
    //@Length(max = 20)
    private String phone;
    /**
     * 手机
     */
    // @Length(max = 20)
    private String mobile;
    /**
     * 邮箱
     */
    // @Length(max = 128)
    private String email;
    /**
     * 联系人类型 取值范围: LEGAL_PERSON,CONTROLLER,AGENT,OTHER
     */
    //@NotBlank
    // @Length(max = 20)
    private String type;
    /**
     * 身份证号
     */
    //@NotBlank
    //@Length(max = 32)
    private String idCardNo;

    public AlipayContactInfo() {
    }

    public AlipayContactInfo(String name, String phone, String mobile, String email, String type, String idCardNo) {
        this.name = name;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.type = type;
        this.idCardNo = idCardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }
}
