package com.ibm.oms.dao.intf;

import java.util.List;

import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.sc.dao.BaseDao;
/**
 * TransportArea Data Access Object (DAO) interface.
 * Creation date:2014-04-01 11:43:10
 * @author:Yong Hong Luo
 */
public interface TransportAreaDao extends BaseDao<TransportArea,Long>{
	
	/**
	 * 根据id查询地址列表
	 * @param id
	 * @return
	 */
	public List<TransportArea> findById(Long id);
	
	/**
	 * 根据名称查询地址列表
	 * @param name
	 * @return
	 */
	public List<TransportArea> findByName(String name);
	
	/**
	 * 根据地址code查询地址列表
	 * @param code
	 * @return
	 */
	public List<TransportArea> findByCode(String code);
	
	/**
	 * 根据父id查询所有子项列表
	 * @param parent_id
	 * @return
	 */
	public List<TransportArea> findByParentId(Long parent_id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<TransportArea> findShow();
	
	public List<TransportArea> findByTreelevel(String areaLevel);

	List<TransportArea> findByLevel(Long level);
	
	/**
	 * 根据id和areaLevel获取配送地址
	 * @param id
	 * @param areaLevel
	 * @return
	 */
	TransportArea getTransportAreaByIdAndAreaLevel(Long id,Long areaLevel);
	
	/**
	 * 根据parentId和areaLevel获取配送地址
	 * @param parentId
	 * @param areaLevel
	 * @return
	 */
	TransportArea getTransportAreaByParentIdAndAreaLevel(Long parentId,Long areaLevel);
}
