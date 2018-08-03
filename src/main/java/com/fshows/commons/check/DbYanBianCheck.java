/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.check;

import com.fshows.commons.dao.base.DbUtil;
import com.fshows.commons.dao.dbtemplate.DBBankNewUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBYanBianUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @author caofy
 * @version DbYanBianCheck.java, v 0.1 2018-07-07 13:11 caofy
 */
public class DbYanBianCheck {

    private static final Log logger = LogFactory.getLog(DbYanBianCheck.class);


    public void execute() {


        String sql = "SELECT  * from lp_liquidator ORDER BY  id DESC LIMIT 1";

        Map<String, Object> map = DbUtil.getYanbianOnlineDBTemplate().findFirst(sql);

        logger.info("新平台化 >> DbYanBianCheck >> map=" + map);


        sql = "SELECT min(id),max(id),count(1) FROM lp_liquidator_store WHERE liquidator_id='20160921085633894'";

        Map<String, Object> storeMap = DbUtil.getYanbianOnlineDBTemplate().findFirst(sql);

        logger.info("新平台化 >> DbYanBianCheck >> storeMap=" + storeMap);


        sql = "SELECT min(id),max(id),count(1) FROM lp_liquidator_store_auth WHERE liquidator_id='20160921085633894'";

        Map<String, Object> storeAuthMap = DbUtil.getYanbianOnlineDBTemplate().findFirst(sql);

        logger.info("新平台化 >> DbYanBianCheck >> storeAuthMap=" + storeAuthMap);


        sql = "SELECT min(id),max(id),count(1) FROM lp_bank_order WHERE id>=64 ";

        Map<String, Object> orderMap = DbUtil.getYanbianOnlineDBTemplate().findFirst(sql);

        logger.info("新平台化 >> DbYanBianCheck >> orderMap=" + orderMap);

    }

}
