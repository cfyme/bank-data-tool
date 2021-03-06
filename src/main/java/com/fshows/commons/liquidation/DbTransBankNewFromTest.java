/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankNewUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBTestEnvUtilsTemplate;
import com.fshows.commons.model.Merchant;
import com.fshows.commons.model.MerchantModel;
import com.fshows.commons.util.MyCsvUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caofy
 * @version DbTransBankNewFromTest.java, v 0.1 2018-07-08 23:47 caofy
 */
public class DbTransBankNewFromTest {

    private static final Log logger = LogFactory.getLog(DbTransToBankNew.class);


    /**
     * 新的平台化数据批量导入 来自测试环境表lp_all_lifecircle_merchant_temp
     * lp_liquidator_store bank_id=5, is_new
     * lp_liquidator_store_auth
     * lp_store_bank
     */
    public void execute(int maxId) {

        List<Merchant> list = getList(maxId);

        logger.info("从测试环境导入 execute start");


        for (Merchant merchant : list) {

            //exist check
            boolean exist = existInStore(merchant.getStore_id());
            if (exist) {
                logger.info("exist in store, storeId=" + merchant.getStore_id());
                continue;
            }

            //1,批量插入到表lp_liquidator_store
            executeDataCopy(merchant);

        }


        logger.info("从测试环境导入 execute end");

    }

    // id>#maxId#
    public List<Merchant> getList(int maxId) {
        String sql = "SELECT * FROM lp_all_lifecircle_merchant_temp WHERE  id>"+maxId;
        List<Merchant> list = DBTestEnvUtilsTemplate.find(Merchant.class, sql);
        return list;
    }

    public boolean existInStore(String storeId) {
        String sql = "select count(1) from lp_liquidator_store where store_id='" + storeId + "'";
        long count = DBBankNewUtilsTemplate.count(sql, null);
        return count > 0;
    }

    public void executeDataCopy(Merchant model) {

        String bandId = DataConstant.new_bank_lp_bank_id;
        String alipid = DataConstant.yanbian_alipid;
        String is_new = "0";
        String status = "1";
        String liquidator_id = DataConstant.liquidator_id;

        StringBuffer sqlStore = new StringBuffer("insert into lp_liquidator_store (store_id,bank_id,liquidator_store_id,liquidator_id," +
                "merchant_name,alias_name,service_phone,contact_name,contact_phone,contact_mobile," +
                "contact_email,category_id,branch,memo,is_new) values ");

        StringBuffer sqlAuth = new StringBuffer("insert into lp_liquidator_store_auth (store_id,liquidator_id, id_card_num, id_card_name,store_address," +
                "id_card_hand_img,store_front_img,business_license_img," +
                "status, province_code,city_code,district_code," +
                "business_license,business_license_type,card_no,contact_type)" +
                " values ");

        StringBuffer sqlBank = new StringBuffer("insert into lp_store_bank (store_id,bank_no,username,is_public_account, open_bank," +
                "super_bank_no,united_bank_no) values ");

        String uid = model.getUid();
        String liquidator_store_id = model.getLiquidator_store_id();
        String store_id = model.getStore_id();
        String merchant_name = model.getMerchant_name();
        String alias_name = model.getAlias_name();

        String service_phone = model.getService_phone();
        String contact_name = model.getContact_name();
        String contact_phone = model.getContact_phone();
        String contact_mobile = model.getContact_mobile();
        String contact_email = model.getContact_email();
        String category_id = model.getCategory_id();
        String memo = model.getMemo();
        String branch = model.getBranch();

        String bank_no = model.getBank_no();
        String username = model.getUsername();

        //2对公，其他对私
        String is_public_account = model.getIs_public_account();

        String open_bank = model.getOpen_bank();
        String super_bank_no = model.getSuper_bank_no();
        String united_bank_no = model.getUnited_bank_no();
        String id_card_num = model.getId_card_num();
        String id_card_name = model.getId_card_name();

        String id_card_hand_img = model.getId_card_hand_img();
        String business_license_img = model.getBusiness_license_img();
        String business_license = model.getBusiness_license();
        String province_code = model.getProvince_code();
        String city_code = model.getCity_code();
        String district_code = model.getDistrict_code();

        String business_license_type = model.getBusiness_license_type();
        String contact_type = model.getContact_type();
        String store_address = model.getStore_address();
        String store_front_img = model.getStore_front_img();

        if (contact_mobile.length() > 16 || contact_phone.length() > 16) {
            logger.info("abcd >> contact_mobile too long, storeId=" + store_id + ",contact_mobile=" + contact_mobile);
            return;
        }

        if (contact_email.length() > 32) {
            logger.info("emal >> contact_email too long, storeId=" + store_id + ",contact_email=" + contact_email);
            contact_email = contact_email.substring(0, 31);
        }

        if (id_card_hand_img.length() > 200) {
            logger.info("id_card_hand_img not valid, storeId=" + store_id);
            return;
        }
        if (business_license_img.length() > 200) {
            logger.info("business_license_img not valid, storeId=" + store_id);
            return;
        }
        if (store_front_img.length() > 200) {
            logger.info("store_front_img not valid, storeId=" + store_id);
            return;
        }

        sqlStore.append(String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                store_id, bandId, liquidator_store_id, liquidator_id,
                merchant_name, alias_name, service_phone, contact_name, contact_phone, contact_mobile,
                contact_email, category_id, branch, memo, is_new
        ));

        sqlAuth.append(String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                store_id, liquidator_id, id_card_num, id_card_name, store_address,
                id_card_hand_img, store_front_img, business_license_img,
                status, province_code, city_code, district_code,
                business_license, business_license_type, bank_no, contact_type
        ));

        sqlBank.append(String.format("('%s','%s','%s','%s','%s','%s','%s')",
                store_id, bank_no, username, is_public_account, open_bank, super_bank_no, united_bank_no
        ));

        DBBankNewUtilsTemplate.update(sqlStore.toString());
        DBBankNewUtilsTemplate.update(sqlAuth.toString());
        DBBankNewUtilsTemplate.update(sqlBank.toString());

        logger.info("data copy success, storeId=" + model.getStore_id());

    }


}
