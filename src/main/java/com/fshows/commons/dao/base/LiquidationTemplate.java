/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.dao.base;

import com.fshows.commons.dao.dbsource.DbHelperBankNew;
import com.fshows.commons.dao.dbsource.DbHelperLiquidation;
import org.apache.commons.dbutils.QueryRunner;

/**
 * @author caofy
 * @version BankOldTemplate.java, v 0.1 2018-07-30 11:45 caofy
 */
public class LiquidationTemplate extends BaseDBTemplate {

    @Override
    public synchronized QueryRunner getQueryRunner() {
        String key = this.getClass().getName();
        QueryRunner queryRunner = DbUtil.globelMap.get(key);
        if (queryRunner == null) {

            //数据源中获取
            queryRunner = DbHelperLiquidation.getQueryRunner();

            //放到map中
            DbUtil.globelMap.put(key, queryRunner);
            return queryRunner;
        } else {
            return queryRunner;
        }
    }
}
