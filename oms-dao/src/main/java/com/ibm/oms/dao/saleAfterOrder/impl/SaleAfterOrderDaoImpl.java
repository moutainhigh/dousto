package com.ibm.oms.dao.saleAfterOrder.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.ibm.oms.client.dto.saleAfterOrder.SaleAfterOrderFromLasaDto;
import com.ibm.oms.client.dto.saleAfterOrder.ShopSaleAfterOrderQueryDto;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.saleAfterOrder.SaleAfterOrderDao;
import com.ibm.oms.domain.persist.OrderSearch;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.dao.impl.BaseDaoImpl;
import com.ibm.sc.util.DateUtils;

@Repository("saleAfterOrderDao")
public class SaleAfterOrderDaoImpl extends BaseDaoImpl<OrderSearch, Long> implements SaleAfterOrderDao{
	
	@Override
	public Pager findSaleAfterOrder(ShopSaleAfterOrderQueryDto dto, Pager pager) throws ParseException{		
		StringBuffer totalHqlHeader = new StringBuffer();
		totalHqlHeader.append("select count(1) ");
		StringBuffer queryHqlHeader = querySqlSelect();
		StringBuffer searchConditions = querySqlFrom(dto);
		SQLQuery totalQuery = getSession().createSQLQuery(totalHqlHeader.append(searchConditions).toString());
		SQLQuery recordQuery = getSession().createSQLQuery(queryHqlHeader.append(searchConditions).toString());

		// 查总数
		List totalList = totalQuery.list();
		Object totalObj = (Object) totalList.get(0);
		pager.setTotalCount(Integer.parseInt(totalObj.toString()));
		// 查分页记录
		recordQuery.setFirstResult(pager.getStart()).setMaxResults(pager.getPageSize());

		List<OrderSearch> orderLists = convertOrderSearchSqlList(recordQuery.list());
		
		pager.setList(orderLists);
		return pager;
	}

	@Override
	public List<OrderSearch> findSaleAfterOrder(ShopSaleAfterOrderQueryDto dto) throws ParseException{
		
		StringBuffer queryHqlHeader = querySqlSelect();
		StringBuffer searchConditions = querySqlFrom(dto);
		SQLQuery recordQuery = getSession().createSQLQuery(queryHqlHeader.append(searchConditions).toString());
				
		return convertOrderSearchSqlList(recordQuery.list());
	}
	
	@Override
	public int countSaleAfterOrder(ShopSaleAfterOrderQueryDto dto){
		int cnt = 0;		
		StringBuffer totalHqlHeader = new StringBuffer();
		totalHqlHeader.append("select count(1) ");
		StringBuffer searchConditions = querySqlFrom(dto);
		SQLQuery totalQuery = getSession().createSQLQuery(totalHqlHeader.append(searchConditions).toString());

		// 查总数
		List totalList = totalQuery.list();
		Object totalObj = (Object) totalList.get(0);
		cnt = Integer.parseInt(totalObj.toString());
		
		return cnt;
	}
	
	@Override
	public SaleAfterOrderFromLasaDto findSaleAfterOrderDetail(String retOrderNo){
		SaleAfterOrderFromLasaDto dto = new SaleAfterOrderFromLasaDto();
		
		return dto;
	}
	
	/**
	 * 查询条件 select
	 * 
	 * @return
	 */
	public StringBuffer querySqlSelect() {
		StringBuffer sb = new StringBuffer();
		sb.append("select   ");			  
		   sb.append(" ordersearc0_.id   as col_0_0_,");
		   sb.append("ordersearc0_.ORDER_NO                   as col_1_0_,");
		   sb.append("ordersearc0_.MEMBER_NO                  as col_2_0_,");
		   sb.append("ordersearc0_.ORDER_TIME                 as col_3_0_,");
		   sb.append("ordersearc0_.FINISH_TIME                as col_4_0_,");
		   sb.append("ordersearc0_.ORDER_SOURCE               as col_5_0_,");
		   sb.append("ordersearc0_.ORDER_TYPE                 as col_6_0_,");
		   sb.append("ordersearc0_.MERCHANT_NO                as col_7_0_,");
		   sb.append("ordersearc0_.ORDER_RELATED_ORIGIN       as col_8_0_,");
		   sb.append("ordersearc0_.FINISH_USER_NO             as col_9_0_,");
		   sb.append("ordersearc0_.CONFIRMER_NAME             as col_10_0_,");
		   sb.append("ordersearc0_.TOTAL_PRODUCT_PRICE        as col_11_0_,");
		   sb.append("ordersearc0_.DISCOUNT_TOTAL             as col_12_0_,");
		   sb.append("ordersearc0_.BILL_TYPE                  as col_15_0_,");
		   sb.append("ordersearc0_.DATE_CREATED               as col_16_0_,");
		   sb.append("ordersearc0_.TOTAL_PAY_AMOUNT           as col_17_0_,");
		   sb.append("ordersearc0_.CUSTOMER_NAME              as col_18_0_,");
		   sb.append("ordersearc0_.ORDER_CATEGORY             as col_19_0_,");
		   sb.append("ordersearc0_.REMARK                     as col_21_0_,");
		   sb.append("ordersearc0_.ALIAS_ORDER_NO             as col_24_0_,");
		   sb.append("ordersearc0_.IF_NEED_REFUND             as col_25_0_,");
		   sb.append("ordersearc0_.CREATED_BY                 as col_26_0_,");
		   sb.append("ordersearc0_.CONFIRM_TIME               as col_29_0_,");
		   sb.append("ordersearc0_.CHGOUT_ORDER_NO            as col_30_0_,");//换货意向单所产生的出库单
		   sb.append("ordersearc0_.STATUS_TOTAL               as col_45_0_,");
		   sb.append("ordersearc0_.STATUS_PAY                 as col_46_0_,");
		   sb.append("ordersearc0_.STATUS_CONFIRM             as col_47_0_,");
		   sb.append("ordersearc0_.UPDATED_BY                 as col_52_0_,");
		   sb.append("ordersearc0_2_.ID                       as col_31_0_,");
		   sb.append("ordersearc0_2_.DISTRIBUTE_TYPE          as col_32_0_,");//1客户寄回，3门店代退
		   sb.append("ordersearc0_2_.ADDRESS_CODE             as col_33_0_,");
		   sb.append("ordersearc0_2_.ADDRESS_DETAIL           as col_34_0_,");
		   sb.append("ordersearc0_2_.USER_NAME                as col_36_0_,");
		   sb.append("ordersearc0_2_.PHONE_NUM                as col_39_0_,");
		   sb.append("ordersearc0_2_.ORDER_SUB_NO             as col_41_0_,");
		   sb.append("ordersearc0_2_.ORDER_SUB_RELATED_ORIGIN as col_49_0_,");//退/换货关联的原子单号
		   sb.append("ordersearc0_.SALESCLERK_NO              as col_53_0_,");//下单营业员编号
		   sb.append("ordersearc0_2_.OUT_STORE_TIME           as col_54_0_,");		   
		   sb.append("ordersearc0_2_.RET_PRE_START_TIME       as col_13_0_,");//退货预约开始时间
		   sb.append("ordersearc0_2_.RET_PRE_END_TIME         as col_14_0_");//退货预约结束时间
		   
		return sb;
	}
	
	/**
	 * 查询条件 From
	 * 
	 * @param order
	 * @return
	 */
	public StringBuffer querySqlFrom(ShopSaleAfterOrderQueryDto order) {

	   StringBuffer sb = new StringBuffer();
	   	   
	   //where
	   sb.append(" from ORDER_MAIN ordersearc0_");
	   sb.append(" left join ORDER_SUB ordersearc0_2_ on ordersearc0_.ORDER_NO = ordersearc0_2_.ORDER_NO");	   
	   sb.append(" where 1=1 ");
	   sb.append(" and ordersearc0_.BILL_TYPE=").append(CommonConst.OrderMain_BillType_Negative.getCodeLong());
	   if(StringUtils.isNotBlank(order.getRetChgFlg())){
		   sb.append(" and ordersearc0_.ORDER_CATEGORY = '").append(order.getRetChgFlg()).append("'");
	   }else{
		   sb.append(" and (ordersearc0_.ORDER_CATEGORY = '").append("ret").append("'");
		   sb.append(" or ").append("ordersearc0_.ORDER_CATEGORY = '").append("chg").append("'");
		   sb.append(" )");
	   }
	   /*// 会员信息统一搜索
	   if (StringUtils.isNotBlank(order.getMemberInfo())) {
	       sb.append(" and  (ordersearc0_.CUSTOMER_NAME like '%").append(order.getMemberInfo()).append("%'");
	       sb.append(" or ordersearc0_.MEMBER_NO like '%").append(order.getMemberInfo()).append("%')");
	   }*/
	   
	  /* if(StringUtils.isNotBlank(order.getRemark())){
		   sb.append(" and ordersearc0_.REMARK like '%").append(order.getRemark()).append("%'");
	   }

	   if(StringUtils.isNotBlank(order.getRefundType())){
		   sb.append(" and ordersearc0_.refund_type = '").append(order.getRefundType()).append("'");
	   }*/

	   /*if(StringUtils.isNotBlank(order.getOrderSubNo())){
	       String[] orderSubNos = order.getOrderSubNo().split(",");
           if(orderSubNos.length==1){
               sb.append(" and ordersearc0_2_.ORDER_SUB_NO = '").append(order.getOrderSubNo()).append("'");
           }else{
               //update by 20141016 for 支持多个订单号一起搜索
               sb.append(" and (");
               String orderNoSql = "";
               int countTmp = 0;
               for(String orNo:orderSubNos){
                   countTmp++;
                   if(countTmp==1){
                       orderNoSql = " ordersearc0_2_.ORDER_SUB_NO = '"+orNo+"'";
                   }else{
                       orderNoSql = orderNoSql +" or ordersearc0_2_.ORDER_SUB_NO = '"+orNo+"'";    
                   }
               }
               sb.append(orderNoSql).append(")");
           }
		   //sb.append(" and ordersearc0_2_.ORDER_SUB_NO = '").append(order.getOrderSubNo()).append("'");
	   }*/
	   
	   if(StringUtils.isNotBlank(order.getReturnNo())){
		   sb.append(" and ordersearc0_.ORDER_NO = '").append(order.getReturnNo()).append("'");
	   }
	   
	   /*
	   if(order.getBillType()!=null){
		   sb.append(" and ordersearc0_.BILL_TYPE=").append(order.getBillType()); 
	   }*/
	   
	   /*if(StringUtils.isNotBlank(order.getOrderType())){
		   sb.append(" and ordersearc0_.ORDER_TYPE = '").append(order.getOrderType()).append("'");
	   }*/
	   
	   /*if(StringUtils.isNotBlank(order.getStatusPay())){
		   sb.append(" and (ordersearc0_.STATUS_PAY = '").append(order.getStatusPay()).append("'");
		   
		   if (statusPayOther != null && statusPayOther.size() > 0) {

				for (String statusPay : statusPayOther) {
					 sb.append(" or ").append("ordersearc0_.STATUS_PAY = '").append(statusPay).append("'");
				}
			}
		   
		   sb.append(" )");
		   
		   if(columnId == OrderColumn.ORDER_REVERSE)
			{
			   sb.append(" and ordersearc0_.IF_NEED_REFUND = ").append(1l).append("");
			}
	   }*/
	   
	   /*if(StringUtils.isNotBlank(order.getStatusTotal())) {
			
			// 待支付时判断某些状态是否作不等条件查询  true: 当做不等条件查询   false:不当做不等条件查询
			if(order.getIsNotEqual4OrderNeedPay())
			{
				
				 sb.append(" and (ordersearc0_.STATUS_TOTAL <> '").append(order.getStatusTotal()).append("'");
				
				if (statusTotalOther != null && statusTotalOther.size() > 0) {
					for (String statusTotal : statusTotalOther) {
						 sb.append(" or ").append("ordersearc0_.STATUS_TOTAL <> '").append(statusTotal).append("'");
					}
				}
				 sb.append(" )");
			}
			else
			{
				 sb.append(" and (ordersearc0_.STATUS_TOTAL = '").append(order.getStatusTotal()).append("'");
				if (statusTotalOther != null && statusTotalOther.size() > 0) {
	
					for (String statusTotal : statusTotalOther) {
						 sb.append(" or ").append("ordersearc0_.STATUS_TOTAL = '").append(statusTotal).append("'");
					}
				}
				 sb.append(" )");
			}
		}*/   
	   
       /* // 根据多个支付方式(payCode)查询
        if (null != order.getPayCodeList() && order.getPayCodeList().size() > 0) {
            List<String> payCodeList = order.getPayCodeList();

            for (int i = 0; i < payCodeList.size(); i++) {
                if (i == 0) {
                    sb.append(" and (ordersearc0_3_.PAY_CODE = '").append(payCodeList.get(0)).append("'");
                } else {
                    sb.append(" or ").append("ordersearc0_3_.PAY_CODE = '").append(payCodeList.get(i))
                            .append("'");

                }

            }
            sb.append(" )");

        }*/
        /*// 根据多个处理状态查询
        if (null != order.getStatusTotalList() && order.getStatusTotalList().size() > 0) {
            List<String> statusTotalList = order.getStatusTotalList();

            for (int i = 0; i < statusTotalList.size(); i++) {
                if (i == 0) {
                    sb.append(" and (ordersearc0_.STATUS_TOTAL = '").append(statusTotalList.get(0)).append("'");
                } else {
                    sb.append(" or ").append("ordersearc0_.STATUS_TOTAL = '").append(statusTotalList.get(i))
                            .append("'");

                }

            }
            sb.append(" )");

        }		*/
		/*// 根据单个orderCategory查询
		if (isNotAllString(order.getOrderCategory())) {
		    sb.append(" and ordersearc0_.ORDER_CATEGORY = '").append(order.getOrderCategory()).append("'");
        }
		
		// 根据多个orderCategory查询
		if (null != order.getOrderCategoryList()&& order.getOrderCategoryList().size() > 0) {
			List<String> orderCategoryList = order.getOrderCategoryList();
			for(int i = 0;i< orderCategoryList.size();i++){
				if(i == 0){
					sb.append(" and (ordersearc0_.ORDER_CATEGORY = '").append(orderCategoryList.get(0)).append("'");
				}
				else{
					 sb.append(" or ").append("ordersearc0_.ORDER_CATEGORY = '").append(orderCategoryList.get(i)).append("'");
					
				}
				
			}
			 sb.append(" )");
		}*/

		/*// 需退款(财务) / 门店退款
		if(null != order.getIfNeedRefundList() && order.getIfNeedRefundList().size() > 0){
			List<Long> ifNeedRefundList = order.getIfNeedRefundList();
			if(ifNeedRefundList!=null && !ifNeedRefundList.isEmpty()){
				
				sb.append(" and (ordersearc0_.IF_NEED_REFUND = ").append(ifNeedRefundList.get(0));
			
				for(int i = 1;i< ifNeedRefundList.size();i++){
					 sb.append(" or ").append("ordersearc0_.IF_NEED_REFUND = ").append(ifNeedRefundList.get(i));
				}
				sb.append(" )");
			
			}
		}*/
	   
	   /*if(StringUtils.isNotBlank(order.getOrderSource())){
		   sb.append(" and ordersearc0_.ORDER_SOURCE = '").append(order.getOrderSource()).append("'");//订单来源
	   }*/
	   
	   if (StringUtils.isNotBlank(order.getMemberNo())) {
		   sb.append(" and ordersearc0_.MEMBER_NO = '").append(order.getMemberNo()).append("'");
		}
	   /*
	   if (isNotAllString(order.getOrderRelatedOrigin())) {
			  sb.append(" and ordersearc0_.ORDER_RELATED_ORIGIN = '").append(order.getOrderRelatedOrigin()).append("'");
		}*/

		/*//下单时间段
		Date orderTimeFrom = order.getOrderTimeFrom();
		Date orderTimeTo = order.getOrderTimeTo();
		if (!(orderTimeFrom == null) && !(orderTimeTo == null)) {
			sb.append(" and ordersearc0_.ORDER_TIME >= " + "str_to_date('" + DateUtils.formatGeneralDateTimeFormat(orderTimeFrom) + "','%Y-%m-%d %H:%i:%s')");
			sb.append(" and ordersearc0_.ORDER_TIME <= " + "str_to_date('" + DateUtils.formatGeneralDateTimeFormat(orderTimeTo) + "','%Y-%m-%d %H:%i:%s')");
		} else if (!(null == orderTimeFrom)) {
			sb.append(" and ordersearc0_.ORDER_TIME >= " + "str_to_date('" + DateUtils.formatGeneralDateTimeFormat(orderTimeFrom) + "','%Y-%m-%d %H:%i:%s')");
		} else if (!(null == orderTimeTo)) {
			sb.append(" and ordersearc0_.ORDER_TIME <= " + "str_to_date('" + DateUtils.formatGeneralDateTimeFormat(orderTimeTo) + "','%Y-%m-%d %H:%i:%s')");
		}

        if (isNotAllString(order.getChgOurOrderNo())) {
            sb.append(" and ordersearc0_.CHGOUT_ORDER_NO = '")
                    .append(order.getChgOurOrderNo().substring(0, order.getChgOurOrderNo().length() - 2)).append("'");
        }*/
        
        //门店代码     
        if (StringUtils.isNotBlank(order.getShopNo())) {
            sb.append(" and ordersearc0_.SHIP_STORE_CODE = '").append(order.getShopNo()).append("'");//商家
        }

        /*//退货预约开始时间
		if (StringUtils.isNotBlank(order.getStartDate())) {
			sb.append(" and ordersearc0_2_.RET_PRE_START_TIME >= " + "str_to_date('"+ order.getStartDate() +"','%Y-%m-%d')");
		}
		//退货预约结束时间
		if (StringUtils.isNotBlank(order.getEndDate())) {
			sb.append(" and ordersearc0_2_.RET_PRE_END_TIME <= " + "str_to_date('"+ order.getEndDate() +"','%Y-%m-%d')");
		}*/
		
		//开始时间
		if (StringUtils.isNotBlank(order.getStartDate())) {
			sb.append(" and ordersearc0_.ORDER_TIME >= " + "str_to_date('"+ order.getStartDate() +"','%Y-%m-%d')");
		}
		//结束时间
		if (StringUtils.isNotBlank(order.getEndDate())) {
			sb.append(" and ordersearc0_.ORDER_TIME <= " + "str_to_date('"+ order.getEndDate() +"','%Y-%m-%d')");
		}
        
		// 根据下单时间排序
		sb.append(" order by ordersearc0_.ORDER_TIME desc");
	    return sb;
	}
	
	private boolean isNull(Object obj){
		return obj==null?true:false;
	}
	
	private List<OrderSearch> convertOrderSearchSqlList(List<Object[]> resultList) throws ParseException{
		List<OrderSearch> list =new ArrayList<OrderSearch>();
		OrderSearch orderSearch = null;
		if(resultList!=null && !resultList.isEmpty()){
			for ( Object[] values : resultList ) {
				orderSearch = new OrderSearch();
			    orderSearch.setId(isNull(values[0])?null:((BigInteger) values[0]).longValue());
			    orderSearch.setOrderNo(isNull(values[1])?null:(String)values[1]);
			    orderSearch.setMemberNo(isNull(values[2])?null:(String)values[2]);
			    orderSearch.setOrderTime(isNull(values[3])?null:(Date)values[3]);
			    orderSearch.setFinishTime(isNull(values[4])?null:(Date)values[4]);
			    
			    orderSearch.setOrderSource(isNull(values[5])?null:(String)values[5]);
			    orderSearch.setOrderType(isNull(values[6])?null:(String)values[6]);
			    orderSearch.setMerchantNo(isNull(values[7])?null:(String)values[7]);
			    orderSearch.setOrderRelatedOrigin(isNull(values[8])?null:(String)values[8]);
			    orderSearch.setFinishUserNo(isNull(values[9])?null:(String)values[9]);
			    
			    orderSearch.setConfirmerName(isNull(values[10])?null:(String)values[10]);
			    orderSearch.setTotalProductPrice(isNull(values[11])?new BigDecimal(0):(BigDecimal)values[11]);
			    orderSearch.setDiscountTotal(isNull(values[12])?new BigDecimal(0):(BigDecimal)values[12]);			    
			    orderSearch.setBillType(isNull(values[13])?null:((Integer)values[13]).longValue());
			    orderSearch.setDateCreated(isNull(values[14])?null:(Date)values[14]);
			    orderSearch.setTotalPayAmount(isNull(values[15])?new BigDecimal(0):(BigDecimal)values[15]);
			    orderSearch.setCustomerName(isNull(values[16])?null:(String)values[16]);
			    orderSearch.setOrderCategory(isNull(values[17])?null:(String)values[17]);
			    orderSearch.setRemark(isNull(values[18])?null:(String)values[18]);
			    orderSearch.setAliasOrderNo(isNull(values[19])?null:(String)values[19]);			    
			    orderSearch.setIfNeedRefund(isNull(values[20])?null:((Integer)values[20]).longValue());
			    orderSearch.setCreatedBy(isNull(values[21])?null:(String)values[21]);
			    orderSearch.setConfirmTime(isNull(values[22])?null:(Date)values[22]);			    
			    orderSearch.setChgOurOrderNo(isNull(values[23])?null:(String)values[23]);

//			    orderSearch.setInvoicePrinted(isNull(values[40])?null:((Integer)values[40]).longValue());
//			    orderSearch.setDeliveryMerchantNo(isNull(values[42])?null:(String)values[42]);
//			    orderSearch.setDeliveryMerchantName(isNull(values[43])?null:(String)values[43]);
//			    orderSearch.setCheckCode(isNull(values[44])?null:(String)values[44]);
			    
			    orderSearch.setStatusTotal(isNull(values[24])?null:(String)values[24]);
			    orderSearch.setStatusPay(isNull(values[25])?null:(String)values[25]);
			    orderSearch.setStatusConfirm(isNull(values[26])?null:(String)values[26]);
//			    orderSearch.setLogisticsOutNo(isNull(values[48])?null:(String)values[48]);
//			    orderSearch.setIfBlackListMember(isNull(values[50])?null:((Integer)values[50]).longValue());
//			    orderSearch.setChgOurOrderNo(isNull(values[51])?null:((String)values[51]));
			    orderSearch.setUpdatedBy(isNull(values[27])?null:((String)values[27]));
			    
				orderSearch.setOrderSubId(isNull(values[28])?null:((BigInteger)values[28]).longValue());
			    orderSearch.setDistributeType(isNull(values[29])?null:(String)values[29]);
			    orderSearch.setAddressCode(isNull(values[30])?null:(String)values[30]);
			    orderSearch.setAddressDetail(isNull(values[31])?null:(String)values[31]);
			    
//			    orderSearch.setLogisticsStatus(isNull(values[35])?null:(String)values[35]);
			    orderSearch.setUserName(isNull(values[32])?null:(String)values[32]);
//			    orderSearch.setSelfFetchAddress(isNull(values[37])?null:(String)values[37]);
//			    orderSearch.setMobPhoneNum(isNull(values[38])?null:(String)values[38]);
			    orderSearch.setPhoneNum(isNull(values[33])?null:(String)values[33]);			    
			    orderSearch.setOrderSubNo(isNull(values[34])?null:(String)values[34]);
			    orderSearch.setOrderSubRelatedOrigin(isNull(values[35])?null:(String)values[35]);
			    
			    orderSearch.setSalesClerkNo(isNull(values[36])?null:((String)values[36]));
			    orderSearch.setOutStoreTime(isNull(values[37])?null:((Date)values[37]));
			    			    
			    orderSearch.setRetPreStartTime(isNull(values[38])?null:(Date)values[38]);
			    orderSearch.setRetPreEndTime(isNull(values[39])?null:(Date)values[39]);
			    
			    list.add(orderSearch);
			}
		}
		return list;
	}
}