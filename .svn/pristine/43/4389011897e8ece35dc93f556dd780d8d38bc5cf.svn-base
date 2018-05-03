/**
 * 
 */
package com.ibm.oms.service;

import java.util.List;
import java.util.Map;

import com.ibm.oms.domain.persist.AreaBean;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.sc.service.BaseService;

/**
 * @author xiaonanxiang
 * 
 */
public interface TransportAreaCacheService{

    /**
     * 顶级区域ID，如：中国 1602
     */
    public static final Long BASE_AREA_ID = 1602L;
    
    /**
	 * 顶级区域级别，如：中国 0L
	 */
    public static final Long BASE_AREA_LEVEL = 0L;// 地址级别：顶级（中国）
    
    public static final String Treelevel_Province = "1";// 地址级别：第一级（省）
    public static final String Treelevel_City = "2";// 地址级别：第二级（市）
    public static final String Treelevel_Area = "3";// 地址级别：第三级（区域）
    
    public static final String ShenZhen_Area_Code = "31359";// 深圳的地址ID
    
    public static final Long MAX_AREA_LEVEL = 3L;

    /**
     * 查询省份
     * 
     * @param provinceId 省份id
     * @return
     */
    List<AreaBean> findStateList(String provinceId);

    /**
     * 查询城市或县区
     * 
     * @param parentid 父id
     * @param id 当前地址id
     * @return
     */
    List<AreaBean> findOtherList(String parentid, String id);

    /**
     * 根据id查询完整配送地址（省+市+县）
     * 
     * @param id
     * @return
     */
    String findAreaNameById(Long id);

    /**
     * 根据最末级地址id，获取完整的地址
     * 
     * @param areaId
     * @return
     */
    public Map<String,TransportArea> getAllByAreaId(String areaId);
    
    /**
     * 根据areaCode查询完整配送地址（省+市+县）
     * 
     * @param id
     * @return
     */
    public String getAllByByAreaCode(String areaCode);
    
    /**
     * 设置配送地址省、城市、县区
     * @param addressCode
     * @param distributeAddress
     */
    public void setStateCityCounty(String addressCode,DistributeAddress distributeAddress);
    
    /**
     * 根据最末级id获取addressCode
     * @param state
     * @param city
     * @param county
     * @param street
     * @return
     */
    public Long getAddressCodeByLastGradId(Long state,Long city,Long county,Long street);
    
    public List<String> setAddressCodeByTransportAreaId(Long transportAreaId);
    
    /**
     * 判断是否属于深圳区域
     * @param addressCode
     * @return
     */
    public boolean isShenzhenAddress(String addressCode);
    
    /**
     * 根据areaCode查询完整配送地址（省+市+县）
     * 
     * @param areaCode
     * @return
     */
    public String getCombinedByAreaCode(String areaCode);
    
    /**
     * 根据areaCode设置 省 市 县
     * 
     * @param addressCodeStr
     * @param distributeAddress
     */
    public void newSetStateCityCounty(String addressCodeStr,DistributeAddress distributeAddress);
}