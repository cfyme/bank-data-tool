/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.alibaba.fastjson.JSON;
import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.model.MerchantModel;
import com.fshows.commons.util.MyCsvUtil;
import com.fshows.commons.util.MyDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

/**
 * @author caofy
 * @version DbTransToBankOld.java, v 0.1 2018-07-05 20:33 caofy
 */
public class DbTransToBankOld {


    private static final Log logger = LogFactory.getLog(DbTransToBankOld.class);


    /**
     * 老的平台化数据批量导入
     * lp_liquidator_store bank_id=5, is_new
     * lp_liquidator_store_auth
     * lp_store_bank
     */
    public void execute() {

        ArrayList<String[]> csvList = MyCsvUtil.getCsvList();

        logger.info("execute start 11111111");
        //1,批量插入到表lp_liquidator_store
        executeLpliquidatorStore(csvList);

        //2,批量插入到表lp_liquidator_store_auth
        executeLpliquidatorStoreAuth(csvList);

        //3,批量插入到表lp_store_bank
        executeLpliquidatorBank(csvList);
        logger.info("execute end111111111111111");

    }



    public void executeLpliquidatorStore(ArrayList<String[]> csvFileList) {

        try {


            StringBuffer sb = new StringBuffer(10240);
            String sqlPrefix = "insert into lp_liquidator_store (store_id,bank_id,liquidator_store_id,liquidator_id, platform_store_id," +
                    "merchant_name,alias_name,service_phone,contact_name,contact_phone,contact_mobile," +
                    "contact_email,category_id,memo,alipid,is_new," +
                    "create_time,update_time) values";
            sb.append(sqlPrefix);
            long startTime = System.currentTimeMillis();


            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                String bandId = DataConstant.yanbian_bank_id;
                String alipid = DataConstant.yanbian_alipid;
                String is_new = "0";

                String liquidator_id = DataConstant.liquidator_id;
                String[] array = csvFileList.get(row);
                if (array.length < 30) {
                    logger.info("array not valid, storeId="+ JSON.toJSONString(array));
                    continue;
                }
                MerchantModel model = new MerchantModel(array);


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
                String id_card_name =model.getId_card_name();

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

                if (contact_mobile.length() > 16 || contact_phone.length()>16) {
                    logger.info("contact_mobile too long, storeId=" + store_id + ",contact_mobile=" + contact_mobile);
                    continue;
                }

                if (id_card_hand_img.length() > 200) {
                    logger.info("id_card_hand_img not valid, storeId=" + store_id);
                    //continue;
                    id_card_hand_img="999";
                }
                if (business_license_img.length() > 200) {
                    logger.info("business_license_img not valid, storeId=" + store_id);
                    //continue;
                    business_license_img="999";
                }
                if (store_front_img.length() > 200) {
                    logger.info("store_front_img not valid, storeId=" + store_id);
                    //continue;
                    store_front_img="999";
                }


                String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                        store_id, bandId, liquidator_store_id, liquidator_id, "",
                        merchant_name, alias_name, service_phone, contact_name, contact_phone, contact_mobile,
                        contact_email, category_id, memo, alipid, is_new,
                        DataConstant.create_time,DataConstant.create_time
                );
                sb.append(tmp);

                if ((row + 1) % DataConstant.bachSize == 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    //logger.info(sb.toString());
                    DBBankOldUtilsTemplate.update(sb.toString());
                    sb = new StringBuffer(10240);
                    sb.append(sqlPrefix);
                }

            }


            if (sb.length() > sqlPrefix.length()) {
                sb.deleteCharAt(sb.length() - 1);
                DBBankOldUtilsTemplate.update(sb.toString());
                logger.info(sb.toString());
            }


            long costTime = System.currentTimeMillis() - startTime;

            System.out.println("costTime999 = " + costTime);

            logger.info("execute costTime=" + costTime);


        } catch (Exception e) {
            logger.error("executeLpliquidatorStore error,", e);
            e.printStackTrace();
        }

    }


    public void executeLpliquidatorStoreAuth(ArrayList<String[]> csvFileList) {
        try {

            StringBuffer sb = new StringBuffer(10240);
            String sqlPrefix = "insert into lp_liquidator_store_auth (id,store_id,liquidator_id, id_card_num, id_card_name,store_address," +
                    "id_card_hand_img,store_front_img,business_license_img," +
                    "status, province_code,city_code,district_code," +
                    "business_license,business_license_type,card_no,contact_type," +
                    "create_time,update_time) values";
            sb.append(sqlPrefix);
            long startTime = System.currentTimeMillis();


            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {
                String authId = MyDataUtil.createDataId();
                String status="1";

                String bandId = DataConstant.yanbian_bank_id;
                String alipid = DataConstant.yanbian_alipid;
                String is_new = "0";

                String liquidator_id = DataConstant.liquidator_id;
                String[] array = csvFileList.get(row);
                if (array.length < 30) {
                    logger.info("array not valid, storeId="+ JSON.toJSONString(array));
                    continue;
                }
                MerchantModel model = new MerchantModel(array);


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
                String id_card_name =model.getId_card_name();

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

                if (contact_mobile.length() > 16 || contact_phone.length()>16) {
                    logger.info("contact_mobile too long, storeId=" + store_id + ",contact_mobile=" + contact_mobile);
                    continue;
                }

                if (id_card_hand_img.length() > 200) {
                    logger.info("id_card_hand_img not valid, storeId=" + store_id);
                    //continue;
                    id_card_hand_img="999";
                }
                if (business_license_img.length() > 200) {
                    logger.info("business_license_img not valid, storeId=" + store_id);
                   // continue;
                    business_license_img="999";
                }
                if (store_front_img.length() > 200) {
                    logger.info("store_front_img not valid, storeId=" + store_id);
                    //continue;
                    store_front_img="999";
                }




                String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                        authId,store_id, liquidator_id, id_card_num, id_card_name, store_address,
                        id_card_hand_img,store_front_img,business_license_img,
                        status, province_code, city_code, district_code,
                        business_license, business_license_type, bank_no, contact_type,
                        DataConstant.create_time,DataConstant.create_time
                );
                sb.append(tmp);

                if ((row + 1) % DataConstant.bachSize == 0) {
                    sb.deleteCharAt(sb.length() - 1);
                    //logger.info(sb.toString());
                    DBBankOldUtilsTemplate.update(sb.toString());
                    sb = new StringBuffer(10240);
                    sb.append(sqlPrefix);
                }

            }


            if (sb.length() > sqlPrefix.length()) {
                sb.deleteCharAt(sb.length() - 1);
                DBBankOldUtilsTemplate.update(sb.toString());
                //logger.info(sb.toString());
            }


            long costTime = System.currentTimeMillis() - startTime;

            System.out.println("costTime = " + costTime);

            logger.info("execute costTime=" + costTime);


        } catch (Exception e) {
            logger.error("executeLpliquidatorStoreAuth error,", e);
        }

    }


    public void executeLpliquidatorBank(ArrayList<String[]> csvFileList) {

        try {

            StringBuffer sb = new StringBuffer(10240);
            String sqlPrefix = "insert into lp_store_bank (store_id,bank_no,username,is_public_account, open_bank," +
                    "super_bank_no,united_bank_no,create_time,update_time) values";
            sb.append(sqlPrefix);
            long startTime = System.currentTimeMillis();


            // 遍历读取的CSV文件
            for (int row = 0; row < csvFileList.size(); row++) {

                String bandId = DataConstant.yanbian_bank_id;
                String alipid = DataConstant.yanbian_alipid;
                String is_new = "0";

                String liquidator_id = DataConstant.liquidator_id;
                String[] array = csvFileList.get(row);
                if (array.length < 30) {
                    logger.info("array not valid, storeId="+ JSON.toJSONString(array));
                    continue;
                }
                MerchantModel model = new MerchantModel(array);


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
                String id_card_name =model.getId_card_name();

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

                if (contact_mobile.length() > 16 || contact_phone.length()>16) {
                    logger.info("contact_mobile too long, storeId=" + store_id + ",contact_mobile=" + contact_mobile);
                    continue;
                }

                if (id_card_hand_img.length() > 200) {
                    logger.info("id_card_hand_img not valid, storeId=" + store_id);
                    //continue;
                    id_card_hand_img="999";
                }
                if (business_license_img.length() > 200) {
                    logger.info("business_license_img not valid, storeId=" + store_id);
                   // continue;
                    business_license_img="999";
                }
                if (store_front_img.length() > 200) {
                    logger.info("store_front_img not valid, storeId=" + store_id);
                   // continue;
                    store_front_img="999";
                }




                String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                        store_id, bank_no, username, is_public_account, open_bank, super_bank_no, united_bank_no,
                        DataConstant.create_time,DataConstant.create_time
                );
                sb.append(tmp);

                if ((row + 1) % DataConstant.bachSize == 0) {
                    sb.deleteCharAt(sb.length() - 1);
                   //logger.info(sb.toString());
                    DBBankOldUtilsTemplate.update(sb.toString());
                    sb = new StringBuffer(10240);
                    sb.append(sqlPrefix);
                }

            }


            if (sb.length() > sqlPrefix.length()) {
                sb.deleteCharAt(sb.length() - 1);
                DBBankOldUtilsTemplate.update(sb.toString());
                //logger.info(sb.toString());
            }


            long costTime = System.currentTimeMillis() - startTime;

            System.out.println("costTime = " + costTime);

            logger.info("execute costTime=" + costTime);


        } catch (Exception e) {
            logger.error("executeLpliquidatorBank error,", e);
            e.printStackTrace();
        }

    }


}
