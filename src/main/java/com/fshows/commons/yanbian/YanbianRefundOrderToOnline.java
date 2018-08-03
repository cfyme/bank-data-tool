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
 * @version YanbianRefundOrderToOnline.java, v 0.1 2018-07-31 15:50 caofy
 */
public class YanbianRefundOrderToOnline {

    private static final Log logger = LogFactory.getLog(YanbianRefundOrderToOnline.class);

    int pageSize = 10000;
    String EMPTY = "";

    public void execute(int minId) {
        logger.info("YanbianFinanceToTest start ");
        copyData(minId);
    }

    private void copyData(int minId) {

        List<LpRefundOrder> refundOrderList = getRefundOrderList(minId);

        logger.info("copyData refundOrderList.size=" + refundOrderList.size());

        batchInsert(refundOrderList);

    }


    private void batchInsert(List<LpRefundOrder> list) {

        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        StringBuffer sb = new StringBuffer(1024 * 10);

        String sqlPrefix = "insert into lp_refund_order_temp(order_sn, bank_id, liquidator_id, liquidator_name, store_id, " +
                "merchant_name, liquidator_order_sn, platform_order_sn, refund_no, out_refund_no," +
                " platform_refund_no, platform_type, pay_type, order_money, refund_money," +
                " refund_status, remit_status, pay_time, bank_commission_rate, bank_commission_fee," +
                " pay_platform_rate, pay_platform_fee, liquidator_commission_rate, liquidator_commission_fee, gateway_commission_rate, " +
                "gateway_commission_fee, is_more_day, " +
                "is_part_refund, execute_status, is_stock_refund," +
                " create_time, update_time) values ";

        sb.append(sqlPrefix);
        String bankId = "1";
        int loop = 0;
        for (LpRefundOrder obj : list) {
            String merchantName = MyStringUtil.trim(obj.getMerchantName());
            String payType = "1";
            String payTime = obj.getPayTime() + "";
            String format = "yyyy-MM-dd HH:mm:ss.SSS";
            String createTime = DateUtil.getStringByMillis(obj.getCreateTime(), format);
            String updateTime = DateUtil.getStringByMillis(obj.getUpdateTime(), format);

            String payPlatformType = obj.getPlatformType() + "";

            String refundStatus = String.valueOf(obj.getRefundStatus());
            String remitStatus = String.valueOf(obj.getRemitStatus());
            String isMoreDay = String.valueOf(obj.getIsMoreDay());
            String executeStatus = String.valueOf(obj.getExecuteStatus());
            String isPartRefund = "0";
            String isStockRefund = "0";


            String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                    obj.getOrderSn(), bankId, obj.getLiquidatorId(), obj.getLiquidatorName(), obj.getStoreId(),
                    merchantName, obj.getLiquidatorOrderSn(), obj.getPlatformOrderSn(), obj.getRefundNo(), obj.getOutRefundNo(),
                    obj.getPlatformRefundNo(), payPlatformType, payType, obj.getOrderMoney(), obj.getRefundMoney(),
                    refundStatus, remitStatus, payTime, obj.getBankCommissionRate(), obj.getBankCommissionFee(),
                    obj.getPayPlatformRate(), obj.getPayPlatformFee(), obj.getLiquidatorCommissionRate(), obj.getLiquidatorCommissionFee(), obj.getGatewayCommissionRate(),
                    obj.getGatewayCommissionFee(), isMoreDay,
                    isPartRefund, executeStatus, isStockRefund,
                    createTime, updateTime
            );
            sb.append(tmp);


            if (loop % DataConstant.bachSize == 0) {
                sb.deleteCharAt(sb.length() - 1);
                final String insertSql = sb.toString();
                DbUtil.getYanbianOnlineDBTemplate().update(insertSql);
                sb = new StringBuffer(10240);
                sb.append(sqlPrefix);
            }


        }

        sb.deleteCharAt(sb.length() - 1);

        try {
            if (sb.length() > sqlPrefix.length() + 10) {
                DbUtil.getYanbianOnlineDBTemplate().update(sb.toString());
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    public List<LpRefundOrder> getRefundOrderList(int minId) {
        String sql = "SELECT  id," +
                " bank_id bankId," +
                " order_sn orderSn," +
                " platform_order_sn platformOrderSn," +
                " liquidator_order_sn liquidatorOrderSn," +
                " liquidator_id liquidatorId, " +
                " liquidator_name liquidatorName," +
                " store_id storeId," +
                " merchant_name merchantName," +
                " refund_no refundNo," +
                " out_refund_no outRefundNo, " +
                " platform_refund_no platformRefundNo, " +
                " platform_type platformType," +
                " order_money orderMoney," +
                " refund_money refundMoney," +
                " refund_status refundStatus," +
                " remit_status remitStatus," +
                " bank_commission_rate bankCommissionRate," +
                " bank_commission_fee bankCommissionFee," +
                " pay_platform_rate payPlatformRate," +
                " pay_platform_fee payPlatformFee," +
                " is_more_day isMoreDay," +
                " liquidator_commission_rate liquidatorCommissionRate," +
                " liquidator_commission_fee liquidatorCommissionFee," +
                " execute_status executeStatus," +
                " gateway_commission_rate gatewayCommissionRate," +
                " gateway_commission_fee gatewayCommissionFee," +
                " pay_time payTime," +
                " create_time createTime," +
                " update_time updateTime" +
                " from lp_refund_order" +
                " WHERE id>" + minId + " AND liquidator_id='20160921085633894' ";
        List<LpRefundOrder> list = DbUtil.getBankOldTemplate().find(LpRefundOrder.class, sql);
        return list;
    }


}

