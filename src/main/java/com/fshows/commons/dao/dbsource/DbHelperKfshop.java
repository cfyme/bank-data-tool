package com.fshows.commons.dao.dbsource;

import com.fshows.commons.constant.DataConstant;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class DbHelperKfshop {
    private static DataSource dataSource;

    private DbHelperKfshop() {
    }


    //新的平台化数据源
    public static QueryRunner getQueryRunner() {
        if (DbHelperKfshop.dataSource == null) {
            //配置dbcp数据源
            BasicDataSource dbcpDataSource = new BasicDataSource();

            if (DataConstant.isProd) {
                setOnlineConnection(dbcpDataSource);
            }

            dbcpDataSource.setDefaultAutoCommit(true);
            dbcpDataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dbcpDataSource.setMaxActive(1);
            dbcpDataSource.setInitialSize(1);
            dbcpDataSource.setMaxIdle(1);
            dbcpDataSource.setMaxWait(5000);
            DbHelperKfshop.dataSource = (DataSource) dbcpDataSource;
            System.out.println("Initialize Liquidation dbcp..." + DataConstant.isProd);
        }
        return new QueryRunner(DbHelperKfshop.dataSource);
    }



    private static void setOnlineConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://rm-bp1ccd4o5m2sc4kkx.mysql.rds.aliyuncs.com/kf_shop_platform_v2?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("caofangyi");
        dbcpDataSource.setPassword("caofangyi@1234");
        System.out.println(" yanbian eConnection");

    }

}