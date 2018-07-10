/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBLiquidationUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @author caofy
 * @version DbBankOldCheck.java, v 0.1 2018-07-07 13:11 caofy
 */
public class DbLiquidationOldCheck {

    private static final Log logger = LogFactory.getLog(DbLiquidationOldCheck.class);


    public void execute() {


        String sql = "SELECT count(1) FROM lp_liquidator_wx_account WHERE liquidator_id='20160921085633894' ";

        Map<String, Object> map =  DBLiquidationUtilsTemplate.findFirst(sql);

        logger.info("老清算平台数据库 >> DbLiquidationOldCheck >> map="+map);

    }

}
