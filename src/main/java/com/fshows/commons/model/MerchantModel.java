/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.model;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.util.MyStringUtil;

import java.io.Serializable;

/**
 * @author caofy
 * @version MerchantModel.java, v 0.1 2018-07-07 23:33 caofy
 */
public class MerchantModel implements Serializable {

    private static final long serialVersionUID = -8468104249470898709L;

    String bandId = DataConstant.yanbian_bank_id;
    String alipid = DataConstant.yanbian_alipid;
    String is_new = "0";

    // 取得第row行第0列的数据
    String liquidator_id = DataConstant.liquidator_id;

    String uid = "";
    String liquidator_store_id = "";
    String store_id = "";
    String merchant_name = "";
    String alias_name = "";
    String service_phone = "";
    String contact_name = "";
    String contact_phone = "";
    String contact_mobile = "";
    String contact_email = "";
    String category_id = "";
    String memo = "";
    String branch = "";

    String bank_no = "";
    String username = "";

    //2对公，其他对私
    String is_public_account = "";

    String open_bank = "";
    String super_bank_no = "";
    String united_bank_no = "";
    String id_card_num = "";
    String id_card_name = "";

    String id_card_hand_img = "";
    String business_license_img = "";
    String business_license = "";
    String province_code = "";
    String city_code = "";
    String district_code = "";

    String business_license_type = "";
    String contact_type = "";
    String store_address = "";
    String store_front_img = "";

    public String getBandId() {
        return bandId;
    }

    public void setBandId(String bandId) {
        this.bandId = bandId;
    }

    public String getAlipid() {
        return alipid;
    }

    public void setAlipid(String alipid) {
        this.alipid = alipid;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getLiquidator_id() {
        return liquidator_id;
    }

    public void setLiquidator_id(String liquidator_id) {
        this.liquidator_id = liquidator_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLiquidator_store_id() {
        return liquidator_store_id;
    }

    public void setLiquidator_store_id(String liquidator_store_id) {
        this.liquidator_store_id = liquidator_store_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
    }

    public String getAlias_name() {
        return alias_name;
    }

    public void setAlias_name(String alias_name) {
        this.alias_name = alias_name;
    }

    public String getService_phone() {
        return service_phone;
    }

    public void setService_phone(String service_phone) {
        this.service_phone = service_phone;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBank_no() {
        return bank_no;
    }

    public void setBank_no(String bank_no) {
        this.bank_no = bank_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIs_public_account() {
        return is_public_account;
    }

    public void setIs_public_account(String is_public_account) {
        this.is_public_account = is_public_account;
    }

    public String getOpen_bank() {
        return open_bank;
    }

    public void setOpen_bank(String open_bank) {
        this.open_bank = open_bank;
    }

    public String getSuper_bank_no() {
        return super_bank_no;
    }

    public void setSuper_bank_no(String super_bank_no) {
        this.super_bank_no = super_bank_no;
    }

    public String getUnited_bank_no() {
        return united_bank_no;
    }

    public void setUnited_bank_no(String united_bank_no) {
        this.united_bank_no = united_bank_no;
    }

    public String getId_card_num() {
        return id_card_num;
    }

    public void setId_card_num(String id_card_num) {
        this.id_card_num = id_card_num;
    }

    public String getId_card_name() {
        return id_card_name;
    }

    public void setId_card_name(String id_card_name) {
        this.id_card_name = id_card_name;
    }

    public String getId_card_hand_img() {
        return id_card_hand_img;
    }

    public void setId_card_hand_img(String id_card_hand_img) {
        this.id_card_hand_img = id_card_hand_img;
    }

    public String getBusiness_license_img() {
        return business_license_img;
    }

    public void setBusiness_license_img(String business_license_img) {
        this.business_license_img = business_license_img;
    }

    public String getBusiness_license() {
        return business_license;
    }

    public void setBusiness_license(String business_license) {
        this.business_license = business_license;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getBusiness_license_type() {
        return business_license_type;
    }

    public void setBusiness_license_type(String business_license_type) {
        this.business_license_type = business_license_type;
    }

    public String getContact_type() {
        return contact_type;
    }

    public void setContact_type(String contact_type) {
        this.contact_type = contact_type;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getStore_front_img() {
        return store_front_img;
    }

    public void setStore_front_img(String store_front_img) {
        this.store_front_img = store_front_img;
    }


    ///////////////////////////////////////
    ///////////////////////////////////////
    public MerchantModel(String[] array) {
        uid = MyStringUtil.trim(array[0]);
        liquidator_store_id = MyStringUtil.trim(array[1]);
        store_id = MyStringUtil.trim(array[2]);
        merchant_name = MyStringUtil.trim(array[3]);
        alias_name = MyStringUtil.trim(array[4]);
        alias_name = alias_name.replace("（平安银行）", "");
        alias_name = alias_name.replace("（平安银行商户）", "");


        service_phone = MyStringUtil.trim(array[5]);
        contact_name = MyStringUtil.trim(array[6]);
        contact_phone = MyStringUtil.trim(array[7]);
        contact_mobile = MyStringUtil.trim(array[8]);
        contact_email = MyStringUtil.trim(array[9]);
        category_id = MyStringUtil.trim(array[10]);
        memo = MyStringUtil.trim(array[11]);
        branch = MyStringUtil.trim(array[12]);


        bank_no = MyStringUtil.trim(array[13]);
        username = MyStringUtil.trim(array[14]);

        // 2对公，其他对私

        is_public_account = MyStringUtil.trim(array[15]);
        if("2".equalsIgnoreCase(is_public_account)){
            is_public_account="1";
        }else{
            is_public_account="0";

        }

        open_bank = MyStringUtil.trim(array[16]);
        super_bank_no = MyStringUtil.trim(array[17]);
        united_bank_no = MyStringUtil.trim(array[18]);
        id_card_num = MyStringUtil.trim(array[19]);
        id_card_name = MyStringUtil.trim(array[20]);

        id_card_hand_img = MyStringUtil.trim(array[21]);
        business_license_img = MyStringUtil.trim(array[22]);
        business_license = MyStringUtil.trim(array[23]);
        province_code = MyStringUtil.trim(array[24]);
        city_code = MyStringUtil.trim(array[25]);
        district_code = MyStringUtil.trim(array[26]);

        business_license_type = MyStringUtil.trim(array[27]);
        contact_type = MyStringUtil.trim(array[28]);
        store_address = MyStringUtil.trim(array[29]);
        store_front_img = MyStringUtil.trim(array[30]);
    }
}
