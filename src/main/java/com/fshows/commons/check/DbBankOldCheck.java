/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.check;

import com.fshows.commons.dao.base.DbUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @author caofy
 * @version DbBankOldCheck.java, v 0.1 2018-07-07 13:11 caofy
 */
public class DbBankOldCheck {

    private static final Log logger = LogFactory.getLog(DbBankOldCheck.class);


    public void execute() {


        String sql = "SELECT  * from lp_liquidator ORDER BY  id DESC LIMIT 1";

        Map<String, Object> map = DbUtil.getBankOldTemplate().findFirst(sql);

        logger.info("老平台化数据库 >> DbBankOldCheck >> map=" + map);


        sql = "SELECT min(id),max(id),count(1) FROM lp_liquidator_store WHERE liquidator_id='20160921085633894' ";

        Map<String, Object> storeMap = DbUtil.getBankOldTemplate().findFirst(sql);

        logger.info("老平台化数据库 >> DbYanBianCheck >> storeMap=" + storeMap);


        sql = "SELECT min(id),max(id),count(1) FROM lp_liquidator_store_auth WHERE liquidator_id='20160921085633894' ";

        Map<String, Object> storeAuthMap = DbUtil.getBankOldTemplate().findFirst(sql);

        logger.info("老平台化数据库 >> DbYanBianCheck >> storeAuthMap=" + storeAuthMap);


        sql = "SELECT count(1) FROM lp_finance WHERE id>=2608075 and id<19267537  and liquidator_id='20160921085633894'";

        Map<String, Object> orderMap = DbUtil.getBankOldTemplate().findFirst(sql);

        logger.info("老平台化数据库 >> DbYanBianCheck >> orderMap=" + orderMap);
    }

}
