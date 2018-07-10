package com.fshows.commons.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fshows.commons.util.MyStringUtil;

import java.util.List;


@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlipaySubMerchantCreateForm extends BaseForm {


    //商户在支付宝入驻成功后，生成的支付宝内全局唯一的商户编号，与external_id二选一	2088101156429885
    private String subMerchantId;

    /**
     * 二级商户编号(清算方商户号)
     */
    private String externalId;

    /**
     * 二级商户名称
     */
    //@NotBlank(message = "二级商户名称不能为空")
    private String name;

    /**
     * 二级商户简称
     */
    //@NotBlank(message = "二级商户简称不能为空")
    //@Length(min = 1, max = 32)
    private String aliasName;

    /**
     * 客服电话
     */
    //@NotBlank(message = "客服电话不能为空")
    //@Length(min = 1, max = 64)
    private String servicePhone;

    /**
     * 联系人名称
     */
    //@Length(max = 64)
    private String contactName;

    /**
     * 联系人电话
     */
    //@Length(max = 64)
    private String contactPhone;

    /**
     * 联系人手机号
     */
    //@Length(max = 64)
    private String contactMobile;

    /**
     * 可联系人邮箱
     */
    //@Email
    //@Length(max = 128)
    private String contactEmail;

    /**
     * 经营类目
     */
    //@NotBlank(message = "经营类目不能为空")
    //@Length(min = 1, max = 128)
    private String categoryId;

    /**
     * 商户来源机构标识
     */
    private String source;

    /**
     * 商户备注
     */
    //@Length(max = 512)
    private String memo;

    ///* list 包含M2的条件  包含省市区地址*/
    private List<AlipayAddressInfo> addressInfo;

    /* 升级M3所需参数*/
    /*  营业证件类型 */
    //@Length(max = 32)
    private String businessLicenseType;
    /*  商户证件编号 */
    //@Length(max = 32)
    private String businessLicense;
    /*  结算卡信息 */
    private List<AlipayBankcardInfo> bankcardInfo;
    /*  受理商负责人信息*/
    private List<AlipayContactInfo> contactInfo;

    public AlipaySubMerchantCreateForm() {

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {

        return MyStringUtil.trim(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAliasName() {

        return MyStringUtil.trim(aliasName);
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBusinessLicenseType() {
        return businessLicenseType;
    }

    public void setBusinessLicenseType(String businessLicenseType) {
        this.businessLicenseType = businessLicenseType;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public List<AlipayAddressInfo> getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(List<AlipayAddressInfo> addressInfo) {
        this.addressInfo = addressInfo;
    }

    public List<AlipayBankcardInfo> getBankcardInfo() {
        return bankcardInfo;
    }

    public void setBankcardInfo(List<AlipayBankcardInfo> bankcardInfo) {
        this.bankcardInfo = bankcardInfo;
    }

    public List<AlipayContactInfo> getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(List<AlipayContactInfo> contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getSubMerchantId() {
        return subMerchantId;
    }

    public void setSubMerchantId(String subMerchantId) {
        this.subMerchantId = subMerchantId;
    }
}
