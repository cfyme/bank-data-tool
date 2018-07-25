/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBBankNewUtilsTemplate;
import com.fshows.commons.util.MyCsvUtil;
import com.fshows.commons.util.MyStringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version DbCheck5000Store.java, v 0.1 2018-07-13 15:34 caofy
 */
public class DbTransFromCsvOther {

    private static final Log logger = LogFactory.getLog(DbTransFromCsvOther.class);


    public void execute() {

        ArrayList<String[]> csvFileList = MyCsvUtil.getCsvList();
        List<String> sourceList = Lists.newArrayList();


        for (int row = 0; row < csvFileList.size(); row++) {
            String[] array = csvFileList.get(row);
            
            System.out.println("array.length="+array.length);
            
            String store_id = MyStringUtil.trim(array[2]);
            sourceList.add(store_id);
        }
        System.out.println("sourceList.size="+sourceList.size());


        int total = 0;
        int dbNotExist = 0;

        String sql = "select store_id from lp_all_lifecircle_merchant_temp";

        List<Map<String, Object>> dbList = DBBankNewUtilsTemplate.find(sql);
        System.out.println("dbList.size=" + dbList.size());
        HashMap storeIdMap = Maps.newHashMap();
        for (Map<String, Object> map : dbList) {
            String storeId = map.get("store_id") + "";
            storeIdMap.put(storeId, "");
        }





        int idNumNull = 0;

        // 遍历读取的CSV文件
        for (int row = 0; row < csvFileList.size(); row++) {

            StringBuffer sb = new StringBuffer(1024);
            String sqlPrefix = "insert into lp_all_lifecircle_merchant_temp (uid,liquidator_id,liquidator_store_id,store_id,merchant_name, alias_name," +
                    "service_phone,contact_name,contact_phone,contact_mobile,contact_email," +
                    "memo,branch,category_id,bank_no,username," +
                    "is_public_account,open_bank,super_bank_no,united_bank_no,id_card_num," +
                    "id_card_name,store_address,id_card_hand_img,store_front_img,business_license_img," +
                    "province_code,city_code,district_code,business_license,business_license_type,contact_type) values";
            sb.append(sqlPrefix);

            // 取得第row行第0列的数据
            String liquidator_id = DataConstant.liquidator_id;

            String[] array = csvFileList.get(row);
            String uid = MyStringUtil.trim(array[0]);
            String liquidator_store_id = MyStringUtil.trim(array[1]);
            String store_id = MyStringUtil.trim(array[2]);

            if (array.length < 30) {
                logger.info("array not valid, storeId=" + store_id);
                continue;
            }

            try {
                String merchant_name = MyStringUtil.trim(array[3]);
                String alias_name = MyStringUtil.trim(array[4]);
                alias_name = alias_name.replace("（平安银行）", "");
                alias_name = alias_name.replace("（平安银行商户）", "");

                //赵延涛（平安银行）

                String service_phone = MyStringUtil.trim(array[5]);
                String contact_name = MyStringUtil.trim(array[6]);
                String contact_phone = MyStringUtil.trim(array[7]);
                String contact_mobile = MyStringUtil.trim(array[8]);
                String contact_email = MyStringUtil.trim(array[9]);
                String category_id = MyStringUtil.trim(array[10]);
                String memo = MyStringUtil.trim(array[11]);
                String branch = MyStringUtil.trim(array[12]);

                String bank_no = MyStringUtil.trim(array[13]);
                String username = MyStringUtil.trim(array[14]);
                String is_public_account = MyStringUtil.trim(array[15]);

                String open_bank = MyStringUtil.trim(array[16]);
                String super_bank_no = MyStringUtil.trim(array[17]);
                String united_bank_no = MyStringUtil.trim(array[18]);
                String id_card_num = MyStringUtil.trim(array[19]);
                String id_card_name = MyStringUtil.trim(array[20]);

                String id_card_hand_img = MyStringUtil.trim(array[21]);
                String business_license_img = MyStringUtil.trim(array[22]);
                String business_license = MyStringUtil.trim(array[23]);
                String province_code = MyStringUtil.trim(array[24]);
                String city_code = MyStringUtil.trim(array[25]);
                String district_code = MyStringUtil.trim(array[26]);

                String business_license_type = MyStringUtil.trim(array[27]);
                String contact_type = MyStringUtil.trim(array[28]);
                String store_address = MyStringUtil.trim(array[29]);
                String store_front_img = MyStringUtil.trim(array[30]);

                if (contact_mobile.length() > 16) {
                    logger.info("contact_mobile too long, storeId=" + store_id + ",contact_mobile=" + contact_mobile + ",uid=" + uid);
                    contact_mobile = contact_mobile.substring(0, 16);
                }

                if (business_license.length() > 32) {
                    logger.info("business_license too long, storeId=" + store_id + ",business_license=" + business_license + ",uid=" + uid);
                    continue;

                }

                if (id_card_hand_img.length() > 200) {
                    logger.info("id_card_hand_img not valid, uid=" + uid + ", storeId=" + store_id);

                    id_card_hand_img = "9999";
                }


                if (StringUtils.isBlank(id_card_hand_img)) {
                    idNumNull++;
                    logger.info("id_card_hand_img is null, uid=" + uid + ",storeId=" + store_id);

                }

                if (business_license_img.length() > 200) {
                    logger.info("business_license_img not valid, uid=" + uid + ", storeId=" + store_id);
                    business_license_img = "9999";
                }
                if (store_front_img.length() > 200) {
                    logger.info("store_front_img not valid, uid=" + uid + ", storeId=" + store_id);
                    //;
                    store_front_img = "9999";
                }



                String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                        uid, liquidator_id, liquidator_store_id, store_id, merchant_name, alias_name,
                        service_phone, contact_name, contact_phone, contact_mobile, contact_email,
                        memo, branch, category_id, bank_no, username,
                        is_public_account, open_bank, super_bank_no, united_bank_no, id_card_num,
                        id_card_name, store_address, id_card_hand_img, store_front_img, business_license_img,
                        province_code, city_code, district_code, business_license, business_license_type, contact_type
                );

                sb.append(tmp);


                if (!storeIdMap.containsKey(store_id)) {
                    DBBankNewUtilsTemplate.update(sb.toString());
                    dbNotExist++;
                    System.out.println(dbNotExist);
                }else{
                    total++;
                }


            } catch (Exception e) {
                logger.error("==============storeId=" + store_id, e);
            }


        }

        System.out.println("storeIdMap.size=" + storeIdMap.size() + ", exist in db total=" + total + ",not exist="+dbNotExist);

    }

    public static void main(String[] args) {
        DbTransFromCsvOther gaara = new DbTransFromCsvOther();
        gaara.execute();
    }

}
