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
 * @version MerchantBankModel.java, v 0.1 2018-07-07 23:33 caofy
 */
public class MerchantBankModel implements Serializable {

    private static final long serialVersionUID = -934858009437528704L;
    String store_id = "";
    String bank_no = "";
    String username = "";
    //2对公，其他对私
    String is_public_account = "";
    String open_bank = "";
    String super_bank_no = "";
    String united_bank_no = "";


    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

    public MerchantBankModel(String[] array) {
        store_id = MyStringUtil.trim(array[0]);
        bank_no = MyStringUtil.trim(array[1]);
        username = MyStringUtil.trim(array[2]);
        is_public_account = MyStringUtil.trim(array[3]);
        if ("2".equalsIgnoreCase(is_public_account)) {
            is_public_account = "1";
        } else {
            is_public_account = "0";

        }
        open_bank = MyStringUtil.trim(array[4]);
        super_bank_no = MyStringUtil.trim(array[5]);
        united_bank_no = MyStringUtil.trim(array[6]);
    }
}
