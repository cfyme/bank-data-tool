/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.dbtemplate.DBLiquidationUtilsTemplate;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyStringUtil;
import com.mysql.jdbc.exceptions.MySQLTransientException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.omg.PortableInterceptor.LOCATION_FORWARD;

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
public class DbTransWxAccountAppid {

    private static final Log logger = LogFactory.getLog(DbTransWxAccountAppid.class);


    /**
     * 新的平台化数据批量导入
     * 微信子商户表  lp_liquidator_wx_account
     * 微信子商户关联APPID及推荐关注APPID表 lp_liquidator_wx_account_appid
     */
    public void execute(Long lastId) {
        logger.info("微信子商户表导入 execute start");
        try {
            executeWxAccountAppidBuildSql(lastId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("微信子商户表 execute end");
    }


    public void executeWxAccountAppidBuildSql(Long lastId) throws Exception {

        String sql = "SELECT * FROM lp_liquidator_wx_account_appid WHERE id>? and liquidator_id=? limit 5";

        List<Map<String, Object>> list = DBLiquidationUtilsTemplate.find(sql, new Object[]{lastId, DataConstant.liquidator_id});

        logger.info("list.zise=" + list.size());


        PrintWriter pw = new PrintWriter(new FileWriter("sql.txt"));
        Integer lastIdStr = 0;
        for (Map<String, Object> map : list) {

            Integer id = (Integer) map.get("id");
            lastIdStr = id;
            String liquidator_id = (String) map.get("liquidator_id");
            String store_id = (String) map.get("store_id");
            String wx_merchant_id = (String) map.get("wx_merchant_id");
            String sub_appid = (String) map.get("sub_appid");
            String subscribe_appid = (String) map.get("subscribe_appid");
            String remarks = (String) map.get("remarks");
            remarks = MyStringUtil.trim(remarks);
            Object state =  map.get("state");
            Object create_time =  map.get("create_time");
            Object update_time =  map.get("update_time");

            String tmp = String.format("insert into lp_liquidator_wx_account_appid " +
                            "(liquidator_id, store_id, wx_merchant_id, sub_appid, subscribe_appid, remark, status,create_time,update_time)" +
                            "values ('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                    liquidator_id, store_id, wx_merchant_id, sub_appid, subscribe_appid, remarks, state, create_time, update_time);
            pw.println(tmp);


        }
        pw.write("lastIdStr=" + lastIdStr);
        pw.close();
    }


}
