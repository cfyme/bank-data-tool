/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.fshows.commons.yanbian;

import com.fshows.commons.constant.DataConstant;
import com.fshows.commons.dao.base.DbUtil;
import com.fshows.commons.model.LpFinance;
import com.fshows.commons.util.DateUtil;
import com.fshows.commons.util.MyStringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * @author caofy
 * @version YanbianFinanceToOnline.java, v 0.1 2018-07-31 15:50 caofy
 */
public class YanbianFinanceToOnline {

    private static final Log logger = LogFactory.getLog(YanbianFinanceToOnline.class);

    int pageSize = 10000;
    String EMPTY = "";

    public void execute() {
        logger.info("YanbianFinanceToTest start ");
        copyData();
    }

    private void copyData() {
        logger.info("YanbianFinanceToTest 111 ");

        Map<String, Object> minAndMaxFinance = getMinAndMaxFinance();
        logger.info("YanbianFinanceToTest 222 ");

        //int minId = Integer.valueOf(minAndMaxFinance.get("minId") + "");
        //int maxId = Integer.valueOf(minAndMaxFinance.get("maxId") + "");
        int minId = 2608075;
        int maxId = 19267537;

        logger.info("copyData start >>minId=" + minId + ",maxId=" + maxId);

        int idx = 0;
        int start = minId;

        while (true) {
            idx++;

            int end = start + pageSize;

            if (end >= maxId) {
                end=maxId;
            }

            List<LpFinance> list = getFinanceList(start, end);


            logger.info("getFinanceList size=" + list.size() + ",idx=" + idx);

            long startTime = System.currentTimeMillis();
            batchInsert(list);
            long costTime = System.currentTimeMillis() - startTime;

            logger.info("getFinanceList costTime=" + costTime);

            start = end;

            if (start >= maxId) {
                break;
            }

        }

        logger.info("copyData end >>minId=" + minId + ",maxId=" + maxId);


    }


    private void batchInsert(List<LpFinance> list) {

        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        StringBuffer sb = new StringBuffer(1024 * 10);

        String sqlPrefix = "insert into lp_bank_order(bank_id, order_sn, pay_platform_order_sn, liquidator_order_sn, sub_mch_id, liquidator_id, " +
                "liquidator_name, liquidator_merchant_id, merchant_name, merchant_rate, store_id, notify_url, " +
                "pay_type, order_type, pay_platform_type, real_money, net_money, pay_platform_rate, pay_platform_fee, " +
                "bank_commission_rate, bank_commission_fee, gateway_commission_rate, gateway_commission_fee, liquidator_commission_rate, liquidator_commission_fee," +
                "pay_status, pay_time, create_day, pay_day, sub_appid, body, " +
                "refund_type, liquidator_ext_rate, union_overflow_fee, voucher_num, device_info, bank_type, is_credit," +
                "ext1, ext2, ext3, ext4, ext5, ext6, " +
                "create_time, update_time) values ";

        sb.append(sqlPrefix);

        int loop = 0;
        for (LpFinance obj : list) {
            loop++;
            String bankId = "1";
            String merchantName = MyStringUtil.trim(obj.getMerchantName());
            String merchantRate = "";
            String payType = "1";
            String payTime = obj.getPayTime() + "";
            String createDay = obj.getCreateDay() + "";
            String payDay = obj.getPayDay() + "";
            String subAppid = MyStringUtil.trim(obj.getAttribute1());
            String body = MyStringUtil.trim(obj.getAttribute2());
            String payStatus = String.valueOf(obj.getPayStatus());

            String refundType = "0";
            if ("3".equalsIgnoreCase(payStatus) || "4".equalsIgnoreCase(payStatus)
                    || "5".equalsIgnoreCase(payStatus)) {
                //退款类型 全额退款
                refundType = "2";
            }

            String liquidatorExtRate = MyStringUtil.trim(obj.getAttribute4());
            String unionOverflowFee = "0.00";
            String voucherNum = "";
            String deviceInfo = "";
            String bankType = MyStringUtil.trim(obj.getBankType());

            //是否使用卡交易 1.信用卡 0.储蓄卡
            String isCredit = "0";

            String format = "yyyy-MM-dd HH:mm:ss.SSS";
            String createTime = DateUtil.getStringByMillis(obj.getCreateTime(), format);
            String updateTime = DateUtil.getStringByMillis(obj.getUpdateTime(), format);

            String orderType = obj.getOrderType() + "";
            String payPlatformType = obj.getPayPlatformType() + "";
            String subMchId = MyStringUtil.trim(obj.getSubMchId());


            String tmp = String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'),",
                    bankId, obj.getOrderSn(), obj.getPayPlatformOrderSn(), obj.getLiquidatorOrderSn(), subMchId, obj.getLiquidatorId(),
                    obj.getLiquidatorName(), obj.getLiquidatorMerchantId(), merchantName, merchantRate, obj.getStoreId(), obj.getNotifyUrl(),
                    payType, orderType, payPlatformType, obj.getRealMoney(), obj.getNetMoney(), obj.getPayPlatformRate(), obj.getPayPlatformFee(),
                    obj.getBankCommissionRate(), obj.getBankCommissionFee(), obj.getGatewayCommissionRate(), obj.getBankCommissionFee(), obj.getLiquidatorCommissionRate(), obj.getLiquidatorCommissionFee(),
                    payStatus, payTime, createDay, payDay, subAppid, body,
                    refundType, liquidatorExtRate, unionOverflowFee, voucherNum, deviceInfo, bankType, isCredit,
                    EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,
                    createTime, updateTime

            );
            sb.append(tmp);


            if (loop % DataConstant.bachSize == 0) {
                sb.deleteCharAt(sb.length() - 1);
                final String insertSql = sb.toString();
                DbUtil.getYanbianOnlineDBTemplate().update(insertSql);
                sb = new StringBuffer(10240);
                sb.append(sqlPrefix);
                //System.out.println("loop=" + loop);
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

    public List<LpFinance> getFinanceList(int minId, int maxId) {
        String sql = "SELECT  id," +
                " bank_id bankId," +
                " order_sn orderSn," +
                " pay_platform_order_sn payPlatformOrderSn," +
                " liquidator_order_sn liquidatorOrderSn," +
                " liquidator_id liquidatorId, " +
                " liquidator_name liquidatorName," +
                " store_id storeId," +
                " merchant_name merchantName," +
                " liquidator_merchant_id liquidatorMerchantId," +
                " notify_url notifyUrl," +
                " order_type orderType, " +
                " pay_platform_type payPlatformType," +
                " real_money realMoney," +
                " net_money netMoney," +
                " pay_platform_rate payPlatformRate," +
                " pay_platform_fee payPlatformFee," +
                " bank_commission_rate bankCommissionRate, " +
                " bank_commission_fee bankCommissionFee," +
                " liquidator_commission_rate liquidatorCommissionRate," +
                " liquidator_commission_fee liquidatorCommissionFee," +
                " pay_status payStatus, " +
                " pay_time payTime," +
                " create_time createTime," +
                " update_time updateTime," +
                " create_day createDay," +
                " pay_day payDay," +
                " sub_mch_id subMchId, " +
                " attribute1, " +
                " attribute2," +
                " attribute3," +
                " attribute4," +
                " attribute5," +
                " bank_type," +
                " gateway_commission_rate gatewayCommissionRate, " +
                " gateway_commission_fee gatewayCommissionFee" +
                " from lp_finance" +
                " WHERE id>=" + minId + " AND id<" + maxId + "  AND liquidator_id='20160921085633894' ";
        List<LpFinance> list = DbUtil.getBankOldTemplate().find(LpFinance.class, sql);
        return list;
    }


    public Map<String, Object> getMinAndMaxFinance() {
        //String sql = "SELECT  min(id)minId, max(id)maxId from lp_finance WHERE liquidator_id='20160921085633894' AND  bank_id=5 ";
        //Map<String, Object> map = DbUtil.getBankOldTemplate().findFirst(sql);
        //return map;
        return null;
    }

}

