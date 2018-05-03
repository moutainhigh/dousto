package com.ibm.oms.service.report.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.OrderStatus;
import com.ibm.oms.dao.intf.OrderPayDao;
import com.ibm.oms.dao.intf.TransportAreaDao;
import com.ibm.oms.dao.report.BaseOrderReportDao;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.OrderReport;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.service.report.BaseOrderReportService;
import com.ibm.oms.service.util.SelfTakeMerchantUtil;
import com.ibm.oms.service.util.StatusUtil;
import com.ibm.sc.bean.Pager;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.sys.Option;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * 
 * Creation date:2014-03-14 04:20:47
 * 
 * @author:Yong Hong Luo
 */
@Service("baseOrderReportService")
public class BaseOrderReportServiceImpl extends BaseServiceImpl<OrderReport, Long> implements BaseOrderReportService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
	// 顶级区域ID，如：中国
	private static final Long BASE_AREA_ID = 888888L;
	
	/**
	 * 顶级区域级别，如：中国
	 */
	private static final Long BASE_AREA_LEVEL = 0L;

	@Autowired
	BaseOrderReportDao baseOrderReportDao;
	@Autowired
	TransportAreaDao transportAreaDao;
	@Autowired
	OrderPayDao orderPayDao;
	@Autowired
	CommonReportUtilService commonReportUtilService;
	@Autowired
    StatusUtil statusUtil;
	@Autowired
    private SelfTakeMerchantUtil selfTakeMerchantUtil;

	/*
	 *  非分页查询
	 */
	public List<OrderReport> findByOrderReport(OrderReport orderReport) {

		List<OrderReport> list = new ArrayList<OrderReport>();
		try{
		    // 查询前的相关操作
            operations4BeforeQuery(orderReport);
            
			list = baseOrderReportDao.findByOrderReport(orderReport);	
			
			// 查询后的相关操作
            operations4AfterQuery(list);
		}catch(Exception e){
			logger.info("{}", e);
		}
		return list;
	}

	/*
	 *  分页查询
	 */
	public Pager findByOrderReport(OrderReport orderReport, Pager pager) {

		try {
		    // 查询前的相关操作
		    operations4BeforeQuery(orderReport);
		    
			Pager page = baseOrderReportDao.findByOrderReport(orderReport, pager);
			
			List<OrderReport> resultList = (List<OrderReport>) page.getList();
			
			// 查询后的相关操作
			operations4AfterQuery(resultList);
			
			page.setList((List<OrderReport>) resultList);			
		} catch (Exception e) {
			logger.info("{}", e);
		}
		return pager;
	}
	
	/**
     * 查询前的相关操作
     * @param orderReport 查询对象
     */
    private void operations4BeforeQuery(OrderReport orderReport) {
        // 获取用来搜索的自提点id
        getSelfTakePointQueryId(orderReport);
        
        // 获取用于搜索的配送地址ID
        getTransportAreaQueryId(orderReport);
    }
	
    /**
     * 查询后的相关操作
     * @param resultList 查询结果对象
     */
    private void operations4AfterQuery(List<OrderReport> resultList) {
        // 获取名称 （订单类型、订单来源、配送方式）
        changeCodeToName(resultList);
        // 订单状态编码转为状态名称
        changeOrderStatusCode2Name(resultList);
        // 拼装配送地址
        setTransportAreaName(resultList);
    }
    
	/**
     * 获取用来搜索的自提点id（设置id优先级：自提点（取下拉列表）-> 商户）
     * @param orderReport
     */
    public void getSelfTakePointQueryId(OrderReport orderReport) {
        SelfTakePoint selfTakePoint = orderReport.getSelfTakePoint();
        Long selfTakePointId = selfTakePoint.getId();
        Long pointDeliverPartnerId = selfTakePoint.getPointDeliverPartnerId();
        //自提点
        if (null != selfTakePointId) {
            orderReport.setSelfTakePointId(selfTakePointId);
            return;
        }
        // 商户
        if (null != pointDeliverPartnerId) {
            Map<String,List<SelfTakePoint>> selfTakePointMap = selfTakeMerchantUtil.getSelfTakePointMap();
            List<SelfTakePoint> selfTakePoints = selfTakePointMap.get(pointDeliverPartnerId.toString());
          /*  List<SelfTakePoint> selfTakePoints = selfTakePointService.findByField(SelfTakePoint_.pointDeliverPartnerId,
                    pointDeliverPartnerId);
*/            List<String> selfTakePointIdList = new ArrayList<String>();
            if(selfTakePoints!=null && !selfTakePoints.isEmpty()){
                for (SelfTakePoint st : selfTakePoints) {
                    selfTakePointIdList.add(String.valueOf(st.getId()));
                }
            }
            orderReport.setSelfTakePointIdList(selfTakePointIdList);
        }
    }

	/**
	 * 获取用来搜索的transportAreaId
	 * 
	 * @param OrderReportSearch
	 */
	public void getTransportAreaQueryId(OrderReport orderReport) {
		// 省/直辖市对应的下拉值
		Long state = orderReport.getState();
		// 城市对应的下拉值
		Long city = orderReport.getCity();
		// 县区对应的下拉值
		Long county = orderReport.getCounty();
		// 街道
		Long street = orderReport.getStreet();
		// 获取优先级：县区 -> 城市 -> 省/直辖市，即省/直辖市、城市、县区都选择时，则使用县区的code去搜索
		if (null != state) {
			orderReport.setTransportAreaId(state);
			if (null != city ) {
				orderReport.setTransportAreaId(city);
				if (null != county) {
					orderReport.setTransportAreaId(county);
					if(null != street){
						orderReport.setTransportAreaId(street);
					}
				}
			}
		}
	}
	
	public void changeCodeToName(List<OrderReport> resultList) {
		Map<String, OrderDic> orderTypeMap = commonReportUtilService.getOrderTypeMap();
		Map<String, OrderDic> orderSourceMap = commonReportUtilService.getOrderSourceMap();
		Map<String, Option> distributeTypeMap = commonReportUtilService.getDistributeTypeMap();
		String orderType = null;
		String orderSource = null;
		String distributeType = null;
		for (OrderReport orderReport : resultList) {
			// 获取订单类型名称
			orderType = orderReport.getOrderType();
			if(null != orderTypeMap.get(orderType)){
				orderReport.setOrderType(orderTypeMap.get(orderType).getDicName());
			}
			// 获取订单来源名称
			orderSource = orderReport.getOrderSource();
			if(null != orderSourceMap.get(orderSource)){
				orderReport.setOrderSource(orderSourceMap.get(orderSource).getDicName());
			}
			// 获取配送方式名称
			distributeType = orderReport.getDistributeType();
			if(null != distributeTypeMap.get(distributeType)){
				orderReport.setDistributeType(distributeTypeMap.get(distributeType).getName());
			}
		}
	}

	/**
	 * 转换OrderStatus的code为name
	 * 
	 * @param resultList
	 */
	public void changeOrderStatusCode2Name(List<OrderReport> resultList) {
		// 订单状态
		String statusTotal = null;
		// 审核状态
		String statusConfirm = null;
		// 支付状态
		String statusPay = null;
		// 物流状态
		String logisticsStatus = null;
		List<OrderStatus> orderStatuss = null;

		for (OrderReport orderReport : resultList) {
			statusTotal = orderReport.getStatusTotal();
			statusConfirm = orderReport.getStatusConfirm();
			statusPay = orderReport.getStatusPay();
			logisticsStatus = orderReport.getLogisticsStatus();
			// 获取所有状态
			//orderStatuss = OrderStatus.getAll();
            Map<String, StatusDict> orderStatusMap = statusUtil.getOrderStatusMap();
            StatusDict statusTotalDict = orderStatusMap.get(statusTotal);
            StatusDict statusConfirmDict = orderStatusMap.get(statusConfirm);
            StatusDict statusPayDict = orderStatusMap.get(statusPay);
            StatusDict logisticsStatusDict = orderStatusMap.get(logisticsStatus);
            if (null != statusTotalDict) {
                orderReport.setStatusTotal(statusTotalDict.getDisplayName());
            }
            if (null != statusConfirmDict) {
                orderReport.setStatusConfirm(statusConfirmDict.getDisplayName());
            }
            if (null != statusPayDict) {
                orderReport.setStatusPay(statusPayDict.getDisplayName());
            }
            if (null != logisticsStatusDict) {
                orderReport.setLogisticsStatus(logisticsStatusDict.getDisplayName());
            }
			
			/*for (OrderStatus orderStatus : orderStatuss) {
				if (null == orderStatus)
					continue;
				if (null != statusTotal) {
					if (statusTotal.equals(orderStatus.getCode())) {
						orderReport.setStatusTotal(orderStatus.getDesc());
					}
				}
				if (null != statusConfirm) {
					if (statusConfirm.equals(orderStatus.getCode())) {
						orderReport.setStatusConfirm(orderStatus.getDesc());
					}
				}
				if (null != statusPay) {
					if (statusPay.equals(orderStatus.getCode())) {
						orderReport.setStatusPay(orderStatus.getDesc());
					}
				}
				if (null != logisticsStatus) {
					if (logisticsStatus.equals(orderStatus.getCode())) {
						orderReport.setLogisticsStatus(orderStatus.getDesc());
					}
				}
			}*/
		}
	}

	/**
	 * 根据addressCode查询SM_TRANSPORT_AREA中的areaName，将查询出来的areaName设置到OrderReportSearch中
	 * 
	 * @param resultList
	 */
	public void setTransportAreaName(List<OrderReport> resultList) {
		
		String addressCode = "";
		List<TransportArea> transportAreas;		
		TransportArea transportArea;
		String transportAreaName = "";
		Long parentId = null;

		for (OrderReport orderReport : resultList) {
			// 获取OrderReportSearch对象中的addressCode
			addressCode = orderReport.getAddressCode();
			// 根据code获取SM_TRANSPORT_AREA表的记录
			transportAreas = transportAreaDao.findByCode(addressCode);
			// 遍历所有配送区域，根据AreaCode查询AreaName，将查询出来的AreaName设置到OrderReportSearch中
			if ((null == transportAreas) || (0 >= transportAreas.size()) || (null == addressCode))
				continue;

			transportArea = transportAreas.get(0);
			transportAreaName = transportArea.getAreaName();
			orderReport.setAreaName(transportAreaName);
			parentId = transportArea.getParentId();

			setParentTransportAreaName(transportAreaName, parentId, orderReport, transportArea);
		}
	}

	/**
	 * 根据父id查找父区域，将查询出的areaName设置到OrderReportSearch中
	 * 
	 * @param transportAreaName
	 * @param parentId
	 * @param OrderReportSearch
	 * @param transportArea
	 */
	public void setParentTransportAreaName(String transportAreaName, Long parentId, OrderReport orderReport, TransportArea transportArea) {
		
		TransportArea parentTransportArea;
		Long newtransportAreaId;
		if (null == parentId)
			return;		
		/*if (this.BASE_AREA_ID.equals(parentId)) 
			return;	*/	
		
		List<TransportArea> transportAreas = transportAreaDao.findById(parentId);
		if((null == transportAreas) || (transportAreas.size() <= 0))
		{
			return;
		}
		
		for (TransportArea newtransportArea : transportAreas) {
			if((null == newtransportArea) || (this.BASE_AREA_LEVEL.equals(newtransportArea.getAreaLevel())))
			{
				break;
			}
			
			newtransportAreaId = newtransportArea.getId();
			parentTransportArea = newtransportArea;
			transportAreaName = parentTransportArea.getAreaName() + transportAreaName;
			orderReport.setAreaName(transportAreaName);
			parentId = newtransportArea.getParentId();
			setParentTransportAreaName(transportAreaName, parentId, orderReport, newtransportArea);
		}
	}
}
