/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBLiquidationUtilsTemplate;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyDataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version DbTransToBankOld.java, v 0.1 2018-07-05 20:33 caofy
 */
public class DbTransWxAccount {

    private static final Log logger = LogFactory.getLog(DbTransWxAccount.class);


    /**
     * 新的平台化数据批量导入
     * 微信子商户表  lp_liquidator_wx_account
     * 微信子商户关联APPID及推荐关注APPID表 lp_liquidator_wx_account_appid
     */
    public void execute(String lastId) {
        logger.info("微信子商户表导入 execute start");
        try {
            executeWxAccountBuildSql(lastId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信子商户表 execute end");
    }


    public void executeWxAccountBuildSql(String lastId)  throws  Exception{

        String sql = "SELECT * FROM lp_liquidator_wx_account WHERE liquidator_id=? and id>?";

        List<Map<String, Object>> list = DBLiquidationUtilsTemplate.find(sql, new String[]{DataConstant.liquidator_id, lastId});


        logger.info("list.zise="+list.size());
        PrintWriter pw = new PrintWriter(new FileWriter("sql.txt"));
        String lastIdStr = "";
        for (Map<String, Object> map : list) {

            String id = (String) map.get("id");
            lastIdStr = id;
            String liquidator_id = (String) map.get("liquidator_id");
            String sub_appid = (String) map.get("sub_appid");
            String wx_merchant_id = (String) map.get("wx_merchant_id");
            String memo = (String) map.get("memo");
            Long create_time = (Long) map.get("create_time");
            Long update_time = (Long) map.get("update_time");
            Object is_default = map.get("is_default");
            Object status =  map.get("status");
            String channel_id = (String) map.get("channel_id");
            Object pay_type =  map.get("pay_type");
            Object account_type =  map.get("account_type");
            String store_id = (String) map.get("store_id");
            Object is_new =  map.get("is_new");


            String createTime = DateUtil.getStringByMillis(create_time, "yyyy-MM-dd HH:mm:ss");
            String updateTime = DateUtil.getStringByMillis(update_time, "yyyy-MM-dd HH:mm:ss");

            String tmp = String.format("insert into lp_liquidator_wx_account " +
                            "(liquidator_id,sub_appid,wx_merchant_id,channel_id,store_id,pay_type,is_new,is_default,account_type,status,memo,create_time,update_time)" +
                            "values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    liquidator_id, sub_appid, wx_merchant_id, channel_id, store_id, pay_type, is_new, is_default, account_type, status, memo, createTime, updateTime);
            pw.println(tmp);

        }
        pw.write("lastIdStr=" + lastIdStr);
        pw.close();
    }

    private void query(List<String> list) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("sql.txt"));
        for (String storeId : list) {

            String sql = "";
            pw.println(sql);

        }

        pw.close();
    }


    private void executeWxAccount() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2016-10-25");
        Date end = sdf.parse("2018-07-07");
        List<String> lists = DateUtil.getRangeDateStrList(start, end);
        long total = 0;
        long myStart = System.currentTimeMillis();
        for (String date : lists) {

            Map<String, Long> dateMap = DateUtil.getStartAndEndByDay(date);

            Long startTime = dateMap.get("startTime");
            Long endTime = dateMap.get("endTime");

            String sql = "SELECT count(1)num  from lp_liquidator_wx_account WHERE liquidator_id='20160921085633894' and create_time>=" + startTime + " and create_time<" + endTime;


            long count = DBLiquidationUtilsTemplate.count(sql, null);
            total += count;

            logger.info("date=" + date + ",count=" + count);
        }
        long costTime = System.currentTimeMillis() - myStart;

        logger.info("total=" + total + ",costTime=" + costTime);
    }

    public static void main(String[] args) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.toDate("20161025", "yyyyMMdd"));

        calendar.add(Calendar.DAY_OF_MONTH, 1);

        System.out.println(DateUtil.formatDateTime(calendar.getTime()));

        System.out.println("11111111111");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2016-10-25");
        Date end = sdf.parse("2018-07-07");
        List<String> lists = DateUtil.getRangeDateStrList(start, end);
        if (!lists.isEmpty()) {
            for (String date : lists) {
                System.out.println(date);
            }
        }
    }


}
