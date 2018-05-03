package com.ibm.oms.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.client.dto.MerchantBalanceDateLogDto;
import com.ibm.oms.dao.intf.MerchantBalanceDateLogDao;
import com.ibm.oms.domain.persist.MerchantBalanceDateLog;
import com.ibm.sc.dao.impl.BaseDaoImpl;

/**
 * Description: //模块目的、功能描述  
 * @author YanYanZhang
 * Date:   2018年3月8日 
 */
@Repository("merchantBalanceDateLogDao")
public class MerchantBalanceDateLogDaoImpl extends BaseDaoImpl<MerchantBalanceDateLog, Integer> implements MerchantBalanceDateLogDao{

	@Override
	public List<MerchantBalanceDateLogDto> findBalanceLogs(String merchantCode, Date startDate, Date endDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		StringBuilder querySql = new StringBuilder("SELECT");
		querySql.append("	om.PERFORM_STORE_CODE merchantCode,");
		querySql.append("	STR_TO_DATE(DATE_FORMAT(om.BALANCE_DATE,'%Y-%m-%d'), '%Y-%m-%d') balanceDate,");
		querySql.append("	sum(CASE WHEN om.STATUS_PAY IN ('0420', '0460') AND om.MERCHANT_TYPE = 'LS' AND om.BILL_TYPE = '1' THEN om.TOTAL_PAY_AMOUNT ELSE 0 END ) saleAmount,");
		querySql.append("	sum(CASE WHEN om.STATUS_PAY IN ('0420', '0460') AND om.BILL_TYPE = '1' AND om.MERCHANT_TYPE = 'LS' THEN om.total_product_count ELSE 0 END ) saleNum,");
		querySql.append("	sum(CASE WHEN om.STATUS_PAY IN ('0420', '0460') AND om.BILL_TYPE = '1' AND om.MERCHANT_TYPE IN ('WX', 'DG') THEN om.TOTAL_PAY_AMOUNT ELSE 0 END ) onlineSaleAmount,");
		querySql.append("	sum(CASE WHEN om.STATUS_PAY IN ('0420', '0460') AND om.BILL_TYPE = '1' AND om.MERCHANT_TYPE IN ('WX', 'DG') THEN om.total_product_count ELSE 0 END ) onlineSaleNum,");
		querySql.append(" 	sum(CASE WHEN om.STATUS_TOTAL ='0280' AND om.MERCHANT_TYPE = 'LS' AND om.BILL_TYPE = '-1' THEN om.TOTAL_PAY_AMOUNT ELSE 0 END) refundAmount,");
		querySql.append(" 	sum(CASE WHEN om.STATUS_TOTAL ='0280' AND om.BILL_TYPE = '-1' AND om.MERCHANT_TYPE = 'LS' THEN om.total_product_count ELSE 0 END) returnNum,");
		querySql.append("	sum(CASE WHEN om.STATUS_TOTAL ='0280' AND om.BILL_TYPE = '-1' AND om.MERCHANT_TYPE IN ('WX', 'DG') THEN om.TOTAL_PAY_AMOUNT ELSE 0 END) onlineRefundAmount,");
		querySql.append(" 	sum(CASE WHEN om.STATUS_TOTAL ='0280' AND om.BILL_TYPE = '-1' AND om.MERCHANT_TYPE IN ('WX', 'DG') THEN om.total_product_count ELSE 0 END) onlineRefundNum");
		querySql.append(" FROM order_main om WHERE ");
		querySql.append(" om.PERFORM_STORE_CODE = '");
		querySql.append(merchantCode);
		querySql.append("'");
		querySql.append(" AND om.BALANCE_DATE > '");
		querySql.append(dateFormat.format(startDate));
		querySql.append("'");
		querySql.append(" AND om.BALANCE_DATE < '");
		querySql.append(dateFormat.format(endDate));
		querySql.append("'");
		querySql.append(" GROUP BY om.PERFORM_STORE_CODE,DATE_FORMAT(om.BALANCE_DATE, '%Y-%m-%d')");
		
		SQLQuery recordQuery = getSession().createSQLQuery(querySql.toString());
		
		List<Object[]> values = recordQuery.list();
		List<MerchantBalanceDateLogDto> balanceLogs = new ArrayList<MerchantBalanceDateLogDto>();
		if (null != values) {
			for(Object[] value : values) {
				MerchantBalanceDateLogDto balanceLog = new MerchantBalanceDateLogDto();
				balanceLog.setMerchantCode(isNull(value[0]) ? null : value[0].toString());
				balanceLog.setBalanceDate(isNull(value[1]) ? null : (Date) value[1]);
				balanceLog.setSaleAmount(isNull(value[2]) ? null : (BigDecimal) value[2]);
				balanceLog.setSaleNum(isNull(value[3]) ? null : ((BigDecimal) value[3]).intValue());
				balanceLog.setOnlineSaleAmount(isNull(value[4]) ? null : (BigDecimal) value[4]);
				balanceLog.setOnlineSaleNum(isNull(value[5]) ? null : ((BigDecimal) value[5]).intValue());
				balanceLog.setRefundAmount(isNull(value[6]) ? null : (BigDecimal) value[6]);
				balanceLog.setReturnNum(isNull(value[7]) ? null : ((BigDecimal) value[7]).intValue());
				balanceLog.setOnlineRefundAmount(isNull(value[8]) ? null : (BigDecimal) value[8]);
				balanceLog.setOnlineRefundNum(isNull(value[9]) ? null : ((BigDecimal) value[9]).intValue());
				
				balanceLogs.add(balanceLog);
			}
		}
		return balanceLogs;
	}
	
	private boolean isNull(Object object){
		return object == null;
	}
}
