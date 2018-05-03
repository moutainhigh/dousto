package com.ibm.oms.domain.persist;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HangOrderMain.class)
public abstract class HangOrderMain_ {

	public static volatile SingularAttribute<HangOrderMain, String> orderType;
	public static volatile SingularAttribute<HangOrderMain, String> confirmerNo;
	public static volatile SingularAttribute<HangOrderMain, Long> ifPriviledgedMember;
	public static volatile SingularAttribute<HangOrderMain, Long> ifBlackListMember;
	public static volatile SingularAttribute<HangOrderMain, Integer> isSuspension;
	public static volatile ListAttribute<HangOrderMain, HangOrderPromotion> orderPromotions;
	public static volatile SingularAttribute<HangOrderMain, BigDecimal> totalPromo;
	public static volatile SingularAttribute<HangOrderMain, Integer> isBarter;
	public static volatile SingularAttribute<HangOrderMain, Date> orderTime;
	public static volatile ListAttribute<HangOrderMain, HangOrderPay> hangOrderPays;
	public static volatile SingularAttribute<HangOrderMain, Long> ifShowPrice;
	public static volatile SingularAttribute<HangOrderMain, String> customerEmail;
	public static volatile SingularAttribute<HangOrderMain, Integer> totalGivePoints;
	public static volatile SingularAttribute<HangOrderMain, BigDecimal> totalProductPrice;
	public static volatile SingularAttribute<HangOrderMain, Long> id;
	public static volatile SingularAttribute<HangOrderMain, Date> finishTime;
	public static volatile SingularAttribute<HangOrderMain, String> orderRelatedOrigin;
	public static volatile SingularAttribute<HangOrderMain, String> orderNo;
	public static volatile SingularAttribute<HangOrderMain, String> updatedBy;
	public static volatile SingularAttribute<HangOrderMain, Long> ifWarnOrder;
	public static volatile SingularAttribute<HangOrderMain, String> receiveAreaId;
	public static volatile SingularAttribute<HangOrderMain, String> ip;
	public static volatile SingularAttribute<HangOrderMain, BigDecimal> weight;
	public static volatile SingularAttribute<HangOrderMain, Date> confirmTime;
	public static volatile SingularAttribute<HangOrderMain, String> finishUserNo;
	public static volatile SingularAttribute<HangOrderMain, BigDecimal> totalPayAmount;
	public static volatile SingularAttribute<HangOrderMain, BigDecimal> discountTransport;
	public static volatile SingularAttribute<HangOrderMain, String> customerName;
	public static volatile SingularAttribute<HangOrderMain, String> memberNo;
	public static volatile SingularAttribute<HangOrderMain, String> memberCardLevel;
	public static volatile SingularAttribute<HangOrderMain, String> cancelReasonNo;
	public static volatile SingularAttribute<HangOrderMain, String> statusTotal;
	public static volatile SingularAttribute<HangOrderMain, Integer> isMerge;
	public static volatile ListAttribute<HangOrderMain, HangOrderSub> hangOrderSubs;
	public static volatile SingularAttribute<HangOrderMain, String> clientRemark;
	public static volatile SingularAttribute<HangOrderMain, String> merchantNo;
	public static volatile SingularAttribute<HangOrderMain, BigDecimal> transportFee;
	public static volatile SingularAttribute<HangOrderMain, String> confirmerName;
	public static volatile SingularAttribute<HangOrderMain, String> remark;
	public static volatile SingularAttribute<HangOrderMain, String> statusPay;
	public static volatile SingularAttribute<HangOrderMain, String> immigrationVersion;
	public static volatile SingularAttribute<HangOrderMain, String> statusConfirm;
	public static volatile SingularAttribute<HangOrderMain, String> refundType;
	public static volatile SingularAttribute<HangOrderMain, String> customerPhone;
	public static volatile SingularAttribute<HangOrderMain, String> chgOurOrderNo;
	public static volatile SingularAttribute<HangOrderMain, Date> dateCreated;
	public static volatile SingularAttribute<HangOrderMain, Long> isDeleted;
	public static volatile SingularAttribute<HangOrderMain, String> clientServiceRemark;
	public static volatile SingularAttribute<HangOrderMain, Long> needConfirm;
	public static volatile SingularAttribute<HangOrderMain, String> payOnArrivalPayType;
	public static volatile SingularAttribute<HangOrderMain, String> sellerMessage;
	public static volatile SingularAttribute<HangOrderMain, String> salesclerkNo;
	public static volatile SingularAttribute<HangOrderMain, String> merchantType;
	public static volatile SingularAttribute<HangOrderMain, Long> remindSent;
	public static volatile SingularAttribute<HangOrderMain, Integer> isSplit;
	public static volatile SingularAttribute<HangOrderMain, String> orderSource;
	public static volatile SingularAttribute<HangOrderMain, Long> ifNeedRefund;
	public static volatile SingularAttribute<HangOrderMain, Integer> billType;
	public static volatile SingularAttribute<HangOrderMain, String> deliveryTimeFlag;
	public static volatile SingularAttribute<HangOrderMain, String> deliveryType;
	public static volatile SingularAttribute<HangOrderMain, String> batchNum;
	public static volatile ListAttribute<HangOrderMain, HangOrderInvoice> hangOrderInvoices;
	public static volatile SingularAttribute<HangOrderMain, Long> ifPayOnArrival;
	public static volatile ListAttribute<HangOrderMain, HangOrderItem> hangOrderItems;
	public static volatile SingularAttribute<HangOrderMain, Date> dateUpdated;
	public static volatile SingularAttribute<HangOrderMain, BigDecimal> discountTotal;
	public static volatile SingularAttribute<HangOrderMain, Integer> needInvoice;
	public static volatile SingularAttribute<HangOrderMain, String> createdBy;
	public static volatile SingularAttribute<HangOrderMain, String> aliasOrderNo;
	public static volatile SingularAttribute<HangOrderMain, String> deliveryDateFlag;
	public static volatile SingularAttribute<HangOrderMain, String> orderCategory;

}

