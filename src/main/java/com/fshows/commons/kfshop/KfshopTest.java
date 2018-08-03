/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.kfshop;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.base.DbUtil;
import com.fshows.commons.dao.base.KfshopTemplate;
import com.fshows.commons.model.LpFinance;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyStringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version BaybianFinanceToTest.java, v 0.1 2018-07-31 15:50 caofy
 */
public class KfshopTest {

    private static final Log logger = LogFactory.getLog(KfshopTest.class);


    public void execute() {
        copyData();
    }

    private void copyData() {

        KfshopTemplate kfshopTemplate = DbUtil.getKfshopTemplate();

        String sql = "select count(*) from kf_shop_platform_v2_aivy_0000.fbs_merchant";

        long count = kfshopTemplate.count(sql, null);

        logger.info("count1=" + count);


        for (int i = 0; i <= 7; i++) {

            String database = "kf_shop_platform_v2_aivy_000" + i;

            for (int j = 0; j <= 127; j++) {
                String table = "fbs_day_order_%s";

                int idx = i * 128 + j;
                DecimalFormat df = new DecimalFormat("0000");
                String tableIdx = df.format(idx);
                table = String.format(table, tableIdx);

                sql = String.format("select order_sn,create_day from %s.%s order by order_sn desc limit 1", database, table);

                List<Map<String, Object>> list = kfshopTemplate.find(sql);

                logger.info("from " + database + ",table=" + table);
                logger.info("list=" + list);
            }
        }


    }

    public static void main(String[] args) {
        int total = 0;
        for (int i = 0; i <= 7; i++) {

            String database = "kf_shop_platform_v2_aivy_000" + i;

            for (int j = 0; j <= 127; j++) {
                String table = "fbs_day_order_%s";

                int idx = i * 128 + j;
                DecimalFormat df = new DecimalFormat("0000");
                String tableIdx = df.format(idx);
                table = String.format(table, tableIdx);
                total++;
                logger.info("from " + database + ",table=" + table);
            }
        }
        logger.info("total " + total + ",total=" + total);

    }


}

