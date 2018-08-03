package com.fshows.commons.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LpLiquidatorStore implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.id
     *
     * @mbggenerated
     */
    private Integer id;

    private Integer isNew;

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.store_id
     *
     * @mbggenerated
     */
    private String storeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.bank_id
     *
     * @mbggenerated
     */
    private Integer bankId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.liquidator_store_id
     *
     * @mbggenerated
     */
    private String liquidatorStoreId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.liquidator_id
     *
     * @mbggenerated
     */
    private String liquidatorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.platform_store_id
     *
     * @mbggenerated
     */
    private String platformStoreId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.merchant_name
     *
     * @mbggenerated
     */
    private String merchantName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.alias_name
     *
     * @mbggenerated
     */
    private String aliasName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.service_phone
     *
     * @mbggenerated
     */
    private String servicePhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.contact_name
     *
     * @mbggenerated
     */
    private String contactName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.contact_phone
     *
     * @mbggenerated
     */
    private String contactPhone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.contact_mobile
     *
     * @mbggenerated
     */
    private String contactMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.contact_email
     *
     * @mbggenerated
     */
    private String contactEmail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.category_id
     *
     * @mbggenerated
     */
    private String categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.memo
     *
     * @mbggenerated
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.balance
     *
     * @mbggenerated
     */
    private BigDecimal balance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.create_time
     *
     * @mbggenerated
     */
    private Long createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.update_time
     *
     * @mbggenerated
     */
    private Long updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column lp_liquidator_store.alipid
     *
     * @mbggenerated
     */
    private String alipid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table lp_liquidator_store
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.id
     *
     * @return the value of lp_liquidator_store.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.id
     *
     * @param id the value for lp_liquidator_store.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.store_id
     *
     * @return the value of lp_liquidator_store.store_id
     *
     * @mbggenerated
     */
    public String getStoreId() {
        return storeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.store_id
     *
     * @param storeId the value for lp_liquidator_store.store_id
     *
     * @mbggenerated
     */
    public void setStoreId(String storeId) {
        this.storeId = storeId == null ? null : storeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.bank_id
     *
     * @return the value of lp_liquidator_store.bank_id
     *
     * @mbggenerated
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.bank_id
     *
     * @param bankId the value for lp_liquidator_store.bank_id
     *
     * @mbggenerated
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.liquidator_store_id
     *
     * @return the value of lp_liquidator_store.liquidator_store_id
     *
     * @mbggenerated
     */
    public String getLiquidatorStoreId() {
        return liquidatorStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.liquidator_store_id
     *
     * @param liquidatorStoreId the value for lp_liquidator_store.liquidator_store_id
     *
     * @mbggenerated
     */
    public void setLiquidatorStoreId(String liquidatorStoreId) {
        this.liquidatorStoreId = liquidatorStoreId == null ? null : liquidatorStoreId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.liquidator_id
     *
     * @return the value of lp_liquidator_store.liquidator_id
     *
     * @mbggenerated
     */
    public String getLiquidatorId() {
        return liquidatorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.liquidator_id
     *
     * @param liquidatorId the value for lp_liquidator_store.liquidator_id
     *
     * @mbggenerated
     */
    public void setLiquidatorId(String liquidatorId) {
        this.liquidatorId = liquidatorId == null ? null : liquidatorId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.platform_store_id
     *
     * @return the value of lp_liquidator_store.platform_store_id
     *
     * @mbggenerated
     */
    public String getPlatformStoreId() {
        return platformStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.platform_store_id
     *
     * @param platformStoreId the value for lp_liquidator_store.platform_store_id
     *
     * @mbggenerated
     */
    public void setPlatformStoreId(String platformStoreId) {
        this.platformStoreId = platformStoreId == null ? null : platformStoreId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.merchant_name
     *
     * @return the value of lp_liquidator_store.merchant_name
     *
     * @mbggenerated
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.merchant_name
     *
     * @param merchantName the value for lp_liquidator_store.merchant_name
     *
     * @mbggenerated
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.alias_name
     *
     * @return the value of lp_liquidator_store.alias_name
     *
     * @mbggenerated
     */
    public String getAliasName() {
        return aliasName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.alias_name
     *
     * @param aliasName the value for lp_liquidator_store.alias_name
     *
     * @mbggenerated
     */
    public void setAliasName(String aliasName) {
        this.aliasName = aliasName == null ? null : aliasName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.service_phone
     *
     * @return the value of lp_liquidator_store.service_phone
     *
     * @mbggenerated
     */
    public String getServicePhone() {
        return servicePhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.service_phone
     *
     * @param servicePhone the value for lp_liquidator_store.service_phone
     *
     * @mbggenerated
     */
    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone == null ? null : servicePhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.contact_name
     *
     * @return the value of lp_liquidator_store.contact_name
     *
     * @mbggenerated
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.contact_name
     *
     * @param contactName the value for lp_liquidator_store.contact_name
     *
     * @mbggenerated
     */
    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.contact_phone
     *
     * @return the value of lp_liquidator_store.contact_phone
     *
     * @mbggenerated
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.contact_phone
     *
     * @param contactPhone the value for lp_liquidator_store.contact_phone
     *
     * @mbggenerated
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.contact_mobile
     *
     * @return the value of lp_liquidator_store.contact_mobile
     *
     * @mbggenerated
     */
    public String getContactMobile() {
        return contactMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.contact_mobile
     *
     * @param contactMobile the value for lp_liquidator_store.contact_mobile
     *
     * @mbggenerated
     */
    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.contact_email
     *
     * @return the value of lp_liquidator_store.contact_email
     *
     * @mbggenerated
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.contact_email
     *
     * @param contactEmail the value for lp_liquidator_store.contact_email
     *
     * @mbggenerated
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail == null ? null : contactEmail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.category_id
     *
     * @return the value of lp_liquidator_store.category_id
     *
     * @mbggenerated
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.category_id
     *
     * @param categoryId the value for lp_liquidator_store.category_id
     *
     * @mbggenerated
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.memo
     *
     * @return the value of lp_liquidator_store.memo
     *
     * @mbggenerated
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.memo
     *
     * @param memo the value for lp_liquidator_store.memo
     *
     * @mbggenerated
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.balance
     *
     * @return the value of lp_liquidator_store.balance
     *
     * @mbggenerated
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.balance
     *
     * @param balance the value for lp_liquidator_store.balance
     *
     * @mbggenerated
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.status
     *
     * @return the value of lp_liquidator_store.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.status
     *
     * @param status the value for lp_liquidator_store.status
     *
     * @mbggenerated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.create_time
     *
     * @return the value of lp_liquidator_store.create_time
     *
     * @mbggenerated
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.create_time
     *
     * @param createTime the value for lp_liquidator_store.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.update_time
     *
     * @return the value of lp_liquidator_store.update_time
     *
     * @mbggenerated
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.update_time
     *
     * @param updateTime the value for lp_liquidator_store.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column lp_liquidator_store.alipid
     *
     * @return the value of lp_liquidator_store.alipid
     *
     * @mbggenerated
     */
    public String getAlipid() {
        return alipid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column lp_liquidator_store.alipid
     *
     * @param alipid the value for lp_liquidator_store.alipid
     *
     * @mbggenerated
     */
    public void setAlipid(String alipid) {
        this.alipid = alipid == null ? null : alipid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lp_liquidator_store
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", storeId=").append(storeId);
        sb.append(", bankId=").append(bankId);
        sb.append(", liquidatorStoreId=").append(liquidatorStoreId);
        sb.append(", liquidatorId=").append(liquidatorId);
        sb.append(", platformStoreId=").append(platformStoreId);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", aliasName=").append(aliasName);
        sb.append(", servicePhone=").append(servicePhone);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", contactMobile=").append(contactMobile);
        sb.append(", contactEmail=").append(contactEmail);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", memo=").append(memo);
        sb.append(", balance=").append(balance);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", alipid=").append(alipid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lp_liquidator_store
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        LpLiquidatorStore other = (LpLiquidatorStore) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getBankId() == null ? other.getBankId() == null : this.getBankId().equals(other.getBankId()))
            && (this.getLiquidatorStoreId() == null ? other.getLiquidatorStoreId() == null : this.getLiquidatorStoreId().equals(other.getLiquidatorStoreId()))
            && (this.getLiquidatorId() == null ? other.getLiquidatorId() == null : this.getLiquidatorId().equals(other.getLiquidatorId()))
            && (this.getPlatformStoreId() == null ? other.getPlatformStoreId() == null : this.getPlatformStoreId().equals(other.getPlatformStoreId()))
            && (this.getMerchantName() == null ? other.getMerchantName() == null : this.getMerchantName().equals(other.getMerchantName()))
            && (this.getAliasName() == null ? other.getAliasName() == null : this.getAliasName().equals(other.getAliasName()))
            && (this.getServicePhone() == null ? other.getServicePhone() == null : this.getServicePhone().equals(other.getServicePhone()))
            && (this.getContactName() == null ? other.getContactName() == null : this.getContactName().equals(other.getContactName()))
            && (this.getContactPhone() == null ? other.getContactPhone() == null : this.getContactPhone().equals(other.getContactPhone()))
            && (this.getContactMobile() == null ? other.getContactMobile() == null : this.getContactMobile().equals(other.getContactMobile()))
            && (this.getContactEmail() == null ? other.getContactEmail() == null : this.getContactEmail().equals(other.getContactEmail()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAlipid() == null ? other.getAlipid() == null : this.getAlipid().equals(other.getAlipid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table lp_liquidator_store
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getBankId() == null) ? 0 : getBankId().hashCode());
        result = prime * result + ((getLiquidatorStoreId() == null) ? 0 : getLiquidatorStoreId().hashCode());
        result = prime * result + ((getLiquidatorId() == null) ? 0 : getLiquidatorId().hashCode());
        result = prime * result + ((getPlatformStoreId() == null) ? 0 : getPlatformStoreId().hashCode());
        result = prime * result + ((getMerchantName() == null) ? 0 : getMerchantName().hashCode());
        result = prime * result + ((getAliasName() == null) ? 0 : getAliasName().hashCode());
        result = prime * result + ((getServicePhone() == null) ? 0 : getServicePhone().hashCode());
        result = prime * result + ((getContactName() == null) ? 0 : getContactName().hashCode());
        result = prime * result + ((getContactPhone() == null) ? 0 : getContactPhone().hashCode());
        result = prime * result + ((getContactMobile() == null) ? 0 : getContactMobile().hashCode());
        result = prime * result + ((getContactEmail() == null) ? 0 : getContactEmail().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAlipid() == null) ? 0 : getAlipid().hashCode());
        return result;
    }
}