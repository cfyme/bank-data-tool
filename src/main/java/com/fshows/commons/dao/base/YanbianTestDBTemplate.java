/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.dao.base;

import com.fshows.commons.dao.dbsource.DbYanbianTestEnv;
import org.apache.commons.dbutils.QueryRunner;

/**
 * @author caofy
 * @version YanbianTestDBTemplate.java, v 0.1 2018-07-30 11:45 caofy
 */
public class YanbianTestDBTemplate extends BaseDBTemplate {

    @Override
    public synchronized QueryRunner getQueryRunner() {
        String key = this.getClass().getName();
        QueryRunner queryRunner = DbUtil.globelMap.get(key);
        if (queryRunner == null) {

            //数据源中获取
            queryRunner = DbYanbianTestEnv.getQueryRunner();

            //放到map中
            DbUtil.globelMap.put(key, queryRunner);
            return queryRunner;
        } else {
            return queryRunner;
        }
    }
}
