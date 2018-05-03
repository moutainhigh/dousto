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
 * 更新sync_log
 * @author pjsong
 *
 */
public class OrderEndJdbcMessageHandler extends AbstractJdbcOperationsSupport {
	
	private static Logger log = LoggerFactory
			.getLogger(OrderEndJdbcMessageHandler.class);

	/**
	 * value="update  ORDER_STATUS_SYNC_LOG set sync_flag ='Y',date_updated=sysdate  where id=:id" />
	 * **/
	private volatile String updateSql;
	private volatile SqlParameterSourceFactory sqlParameterSourceFactory = new BeanPropertySqlParameterSourceFactory();


	@Resource
	private OrderStatusSyncLogService orderStatusSyncLogService;
	
	public OrderEndJdbcMessageHandler(){
		
	}

	public OrderEndJdbcMessageHandler(
			String updateSql) {
		super();
		this.updateSql = updateSql;
	}

	@ServiceActivator
	public void execute( List<OrderStatusSyncLog> list){
		
		log.debug("=======OrderEndJdbcMessageHandler start ======================");
		
		for (OrderStatusSyncLog log : list) {
			executeSave(log);
		}

		log.debug("=======OrderEndJdbcMessageHandler end ======================");
	}

    protected List<? extends Map<String, Object>> executeSave(Object obj) {
        SqlParameterSource paramSource = new MapSqlParameterSource();
        if (obj instanceof Map) {
            paramSource = new MapSqlParameterSource((Map) obj);
        } else if (this.sqlParameterSourceFactory != null) {
            paramSource = this.sqlParameterSourceFactory.createParameterSource(obj);
        }

        return executeUpdate(this.updateSql, paramSource);

    }
	
	public void setUpdateSql(String updateSql) {
		this.updateSql = updateSql;
	}

	public void setSqlParameterSourceFactory(
			SqlParameterSourceFactory sqlParameterSourceFactory) {
		this.sqlParameterSourceFactory = sqlParameterSourceFactory;
	}

}
