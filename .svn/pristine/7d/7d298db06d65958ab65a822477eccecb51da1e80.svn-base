package com.ibm.oms.integration.order.jdbc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.jdbc.BeanPropertySqlParameterSourceFactory;
import org.springframework.integration.jdbc.SqlParameterSourceFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.ibm.oms.domain.persist.OrderStatusSyncLog;
import com.ibm.oms.service.OrderStatusSyncLogService;
import com.ibm.sc.integration.jdbc.AbstractJdbcOperationsSupport;

/**
 * <int:channel id="settleOrRefundChannel"/>
    <int:inbound-channel-adapter ref="settleOrRefundOrderService"
 * **/
public class OrderJdbcMessageHandler extends AbstractJdbcOperationsSupport {
	
	private static Logger log = LoggerFactory
			.getLogger(OrderJdbcMessageHandler.class);

	/**
	 * value="select * from tc_ufb_file where tc_ufb001=:tc_ufb001 and tc_ufb002=:tc_ufb002" /
	 */
	private volatile String selectSql;
	private volatile String selectSqla;
    /**
     * value=
     * "insert into tc_ufb_file (tc_ufb001, tc_ufb002,tc_ufb003, tc_ufb004,trans_date,trans_time,trans_flag,trans_crea,tc_ufb005,tc_ufb006,tc_ufb007,tc_ufb008,tc_ufb009,tc_ufb010,tc_ufb011,tc_ufb012) values (:tc_ufb001, :tc_ufb002,:tc_ufb003, :tc_ufb004,:trans_date,:trans_time,:trans_flag,:trans_crea,:tc_ufb005,:tc_ufb006,:tc_ufb007,:tc_ufb008,:tc_ufb009,:tc_ufb010,:tc_ufb011,:tc_ufb012)"
     * **/
	private volatile String insertSql;
	private volatile String insertSqla;
	private volatile String updateSql;
	private volatile SqlParameterSourceFactory sqlParameterSourceFactory = new BeanPropertySqlParameterSourceFactory();


	@Resource
	private OrderStatusSyncLogService orderStatusSyncLogService;
	
	public OrderJdbcMessageHandler(){
		
	}

	public OrderJdbcMessageHandler(String selectSql, String insertSql,
			String updateSql) {
		super();
		this.selectSql = selectSql;
		this.insertSql = insertSql;
		this.updateSql = updateSql;
	}

	@ServiceActivator
	public List<OrderStatusSyncLog> execute( Map<String,Object> params){
		
		log.debug("=======OrderJdbcMessageHandler start ======================");
		if(params == null){
		    return null;
		}
        List<Map<String, Object>> list_data_paidOrCancel = (List<Map<String, Object>>) params.get("data_paidOrCancel");
        List<Map<String, Object>> list_data_settleOrRefund = (List<Map<String, Object>>) params
                .get("data_settleOrRefund");
        List<Map<String, Object>> data = (List<Map<String, Object>>) params.get("data");
        List<OrderStatusSyncLog> logList = (List<OrderStatusSyncLog>) params.get("updata");
        if (list_data_paidOrCancel != null && !list_data_paidOrCancel.isEmpty()) {
            for (Map<String, Object> map : list_data_paidOrCancel) {
                executeSave(map, true);
            }
        }
        if (list_data_settleOrRefund != null && !list_data_settleOrRefund.isEmpty()) {
            for (Map<String, Object> map : list_data_settleOrRefund) {
                executeSave(map, false);
            }
        }
        if (data != null && !data.isEmpty()) {
            for (Map<String, Object> map : data) {
                executeSave(map, false);
            }
        }
        log.debug("=======OrderJdbcMessageHandler end ======================");
		
		return logList;
		
	}

    protected List<? extends Map<String, Object>> executeSave(Object obj, Boolean isUfa) {
        String selectStr = isUfa? selectSqla:selectSql;
        String insertStr = isUfa? insertSqla:insertSql;
        SqlParameterSource paramSource = new MapSqlParameterSource();
        if (obj instanceof Map) {
            paramSource = new MapSqlParameterSource((Map) obj);
        } else if (this.sqlParameterSourceFactory != null) {
            paramSource = this.sqlParameterSourceFactory.createParameterSource(obj);
        }

        if (selectStr != null) {
            //ufb不做重复检查
//            boolean isUfb = (selectStr.indexOf("tc_ufb") != -1);
//            if (!isUfb) {
                List query = executeQuery(selectStr, paramSource);
                if ((query != null) && (!(query.isEmpty()))) {
                    return null;
                }
//            }
        }

        return executeUpdate(insertStr, paramSource);
    }
	
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}

	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

	public void setInsertSql(String insertSql) {
		this.insertSql = insertSql;
	}
    public void setSelectSqla(String selectSql) {
        this.selectSqla = selectSql;
    }

    public void setInsertSqla(String insertSql) {
        this.insertSqla = insertSql;
    }
	public void setSqlParameterSourceFactory(
			SqlParameterSourceFactory sqlParameterSourceFactory) {
		this.sqlParameterSourceFactory = sqlParameterSourceFactory;
	}

}
