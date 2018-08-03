/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.yanbian;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.base.DbUtil;
import com.fshows.commons.model.LpFinance;
import com.fshows.commons.model.LpRefundOrder;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyStringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version YanbianRefundOrderSync.java, v 0.1 2018-07-31 15:50 caofy
 */
public class YanbianRefundOrderSync {

    private static final Log logger = LogFactory.getLog(YanbianRefundOrderSync.class);

    int pageSize = 10000;
    String EMPTY = "";

    public void execute(int day) {
        logger.info("YanbianFinanceToTest start ");
        syncDada(day);
    }

    private void syncDada(int day) {

        List<LpRefundOrder> list = getRefundOrderList(day);

        logger.info("YanbianRefundOrderSync >> list size=" + list.size());

        for (LpRefundOrder refund : list) {

            String orderSn = refund.getOrderSn();

            LpFinance bankOrder = getBankOrder(orderSn);

            if(bankOrder==null){

                logger.info("orderSn=" + orderSn + ", bankOrder is null");
            }else{
                Integer payStatus = bankOrder.getPayStatus();

                logger.info("orderSn=" + orderSn + ",payStatus=" + payStatus);
            }



        }

    }


    public List<LpRefundOrder> getRefundOrderList(int day) {
        String sql = "SELECT  " +
                " bank_id bankId," +
                " order_sn orderSn," +
                " refund_no refundNo," +
                " liquidator_order_sn liquidatorOrderSn," +
                " liquidator_id liquidatorId, " +
                " liquidator_name liquidatorName," +
                " store_id storeId," +
                " refund_status refundStatus," +
                " is_more_day isMoreDay" +

                " from lp_refund_order" +
                " WHERE  liquidator_id='20160921085633894' and create_time >=UNIX_TIMESTAMP('" + day + "')*1000";
        List<LpRefundOrder> list = DbUtil.getBankOldTemplate().find(LpRefundOrder.class, sql);
        return list;
    }


    public Map<String, Object> getMinAndMaxFinance() {
        //String sql = "SELECT  min(id)minId, max(id)maxId from lp_finance WHERE liquidator_id='20160921085633894' AND  bank_id=5 ";
        //Map<String, Object> map = DbUtil.getBankOldTemplate().findFirst(sql);
        //return map;
        return null;
    }

    public LpFinance getBankOrder(String orderSn) {
        String sql = "SELECT  " +
                " bank_id bankId," +
                " order_sn orderSn," +
                " pay_status payStatus," +
                " pay_platform_order_sn payPlatformOrderSn," +
                " liquidator_order_sn liquidatorOrderSn," +
                " liquidator_id liquidatorId, " +
                " liquidator_name liquidatorName," +
                " store_id storeId" +

                " from lp_bank_order" +
                " WHERE  order_sn='" + orderSn + "'";
        LpFinance first = DbUtil.getYanbianOnlineDBTemplate().findFirst(LpFinance.class, sql);
        return first;
    }

}

