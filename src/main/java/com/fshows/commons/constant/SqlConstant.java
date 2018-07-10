/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.constant;

/**
 * @author caofy
 * @version SqlConstant.java, v 0.1 2018-07-10 10:02 caofy
 */
public class SqlConstant {

    public static String   sqlStore_bank_old = "insert into lp_liquidator_store (store_id,bank_id,liquidator_store_id,liquidator_id, platform_store_id," +
            "merchant_name,alias_name,service_phone,contact_name,contact_phone,contact_mobile," +
            "contact_email,category_id,memo,alipid,is_new," +
            "create_time,update_time) values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s') ";


    public static String   sqlAuth_bank_old = "insert into lp_liquidator_store_auth (id,store_id,liquidator_id, id_card_num, id_card_name,store_address," +
            "id_card_hand_img,store_front_img,business_license_img," +
            "status, province_code,city_code,district_code," +
            "business_license,business_license_type,card_no,contact_type," +
            "create_time,update_time) values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')";

    public static String   sqlBANK_bank_old = "insert into lp_store_bank (store_id,bank_no,username,is_public_account, open_bank,super_bank_no,united_bank_no,create_time,update_time) values ('%s','%s','%s','%s','%s','%s','%s','%s','%s') ";


}
