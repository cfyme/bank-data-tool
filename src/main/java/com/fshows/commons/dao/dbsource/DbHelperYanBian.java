package com.fshows.commons.dao.dbsource;

import com.fshows.commons.constant.DataConstant;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

public class DbHelperYanBian {
    private static DataSource dataSource;

    private DbHelperYanBian() {
    }


    //新的平台化数据源
    public static QueryRunner getQueryRunner() {
        if (DbHelperYanBian.dataSource == null) {
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
            DbHelperYanBian.dataSource = (DataSource) dbcpDataSource;
            System.out.println("Initialize Liquidation dbcp..." + DataConstant.isProd);
        }
        return new QueryRunner(DbHelperYanBian.dataSource);
    }



//    private static void setOnlineConnection(BasicDataSource dbcpDataSource) {
//        dbcpDataSource.setUrl("jdbc:mysql://rm-bp1e4l7jl4p0rt42m297.mysql.rds.aliyuncs.com/fs_bank_liquidation?zeroDateTimeBehavior=convertToNull");
//        dbcpDataSource.setUsername("fs_front_user0");
//        dbcpDataSource.setPassword("R67LqfjtHA9FoEI92t");
//        System.out.println(" yanbian eConnection");
//
//    }

    private static void setOnlineConnection(BasicDataSource dbcpDataSource) {
        dbcpDataSource.setUrl("jdbc:mysql://rm-bp1e4l7jl4p0rt42m.mysql.rds.aliyuncs.com/fs_bank_liquidation?zeroDateTimeBehavior=convertToNull");
        dbcpDataSource.setUsername("fs_front_user0");
        dbcpDataSource.setPassword("R67LqfjtHA9FoEI92t");
        System.out.println(" yanbian new prod connection");

    }

}