/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.constant;

/**
 * @author caofy
 * @version DataConstant.java, v 0.1 2018-07-05 20:07 caofy
 */
public class DataConstant {

    public  static  boolean isProd = false;

    public static String myexcel = "/users/caofy/excel/a.xlsx";
    public static String mycsv = "/users/caofy/excel/tmp.csv";

    /** 批量执行sql的数量 **/
    public static final  int bachSize = 1000;

    //public static String lifecircle_csv = "/users/caofy/excel/lifecircle.csv";
   public static String lifecircle_csv = "/users/caofy/excel/lifecircle_merchant_0708.txt";
   // public static String lifecircle_csv = "lifecircle_merchant_0708.txt";



    /** 清算方友店ID 固定值 **/
    public static  String  liquidator_id = "20160921085633894";


    public static String yanbian_bank_id = "5";
    public static String yanbian_alipid = "2088621862443282";
    public static String youdian_alipid = "2088011993690428";
    public static String charset = "GBK";
    public static String create_time = "1530892800000";


    //新平台化
    public static String new_bank_lp_bank_id = "1";



    public static String redisKey = "limit-speed";


    public static String province_code = "330000";
    public static String city_code = "330100";
    public static String district_code = "330110";
}
