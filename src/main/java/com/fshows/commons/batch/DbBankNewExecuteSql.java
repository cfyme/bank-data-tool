/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.batch;

import com.fshows.commons.dao.dbtemplate.DBBankNewUtilsTemplate;
import com.fshows.commons.dao.dbtemplate.DBBankOldUtilsTemplate;
import com.fshows.commons.liquidation.DbBankOldCheck;
import com.fshows.commons.util.MyFileUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @author caofy
 * @version DbBankNewExecuteSql.java, v 0.1 2018-07-09 15:50 caofy
 */
public class DbBankNewExecuteSql {


    private static final Log logger = LogFactory.getLog(DbBankOldCheck.class);


    public void execute() {

        logger.info("新平台化 >> sql >> 执行开始");

        List<String> listFromFile = MyFileUtil.getListFromFile("sql.txt");


        logger.info("新平台化 >> sql >> 执行开始, 数量 =  " + listFromFile.size());

        for (int i = 0; i < listFromFile.size(); i++) {

            String sql = listFromFile.get(i);
            logger.info("execute sql=" + sql + ",i=" + i);

            DBBankNewUtilsTemplate.update(sql);
        }

        logger.info("新平台化 >> sql >> 执行完成, 数量 =  " + listFromFile.size());

    }
}
