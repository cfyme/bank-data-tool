/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.liquidation;

import com.fshows.commons.dao.dbtemplate.DBBankNewUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @author caofy
 * @version DbBankNewCheck.java, v 0.1 2018-07-07 13:11 caofy
 */
public class DbBankNewCheck {

    private static final Log logger = LogFactory.getLog(DbBankNewCheck.class);


    public void execute() {


        String sql = "SELECT  * from lp_liquidator ORDER BY  id DESC LIMIT 1";

        Map<String, Object> map =  DBBankNewUtilsTemplate.findFirst(sql);

        logger.info("新平台化 >> DbBankNewCheck >> map="+map);

    }

}
