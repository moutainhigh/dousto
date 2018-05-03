/**
 * 
 */
package com.ibm.oms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.dao.intf.TransportAreaDao;
import com.ibm.oms.domain.persist.AreaBean;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.service.TransportAreaService;
import com.ibm.sc.service.impl.BaseServiceImpl;

/**
 * @author xiaonanxiang
 * 
 */
@Service("transportAreaService")
public class TransportAreaServiceImpl extends BaseServiceImpl<TransportArea, Long> implements TransportAreaService {

    @Resource
    private TransportAreaDao transportAreaDao;

    /**
     * 查询省份
     * 
     * @param provinceId 省份id
     * @return
     */
    public List<AreaBean> findStateList(String provinceId) {
        List<AreaBean> list = new ArrayList<AreaBean>();
        List<TransportArea> transportAreas = transportAreaDao.findByTreelevel(Treelevel_Province);
        for (TransportArea transportArea : transportAreas) {
            AreaBean areaBean = new AreaBean();
            areaBean.setId(transportArea.getId() + "");
            areaBean.setName(transportArea.getAreaName());
            if (String.valueOf(transportArea.getId()).equals(provinceId)) {
                areaBean.setChecked(true);
            } else {
                areaBean.setChecked(false);
            }
            list.add(areaBean);
        }
        return list;
    }

    /**
     * 查询城市或县区
     * 
     * @param parentid 父id
     * @param id 当前地址id
     * @return
     */
    public List<AreaBean> findOtherList(String parentid, String id) {
        List<AreaBean> list = new ArrayList<AreaBean>();
        if (null == id && "".equals(id)) {
            return list;
        }
        if (null != parentid && !("".equals(parentid))) {
            List<TransportArea> transportAreas = transportAreaDao.findByParentId(NumberUtils.toLong(parentid));
            for (TransportArea transportArea : transportAreas) {
                AreaBean areaBean = new AreaBean();
                areaBean.setId(transportArea.getId() + "");
                areaBean.setName(transportArea.getAreaName());
                if (String.valueOf(transportArea.getId()).equals(id)) {
                    areaBean.setChecked(true);
                } else {
                    areaBean.setChecked(false);
                }
                list.add(areaBean);
            }
        }
        return list;
    }

    /**
     * 根据id查询完整配送地址（省+市+县）
     * 
     * @param id
     * @return
     */
    public String findAreaNameById(Long id) {
        String result = "";
        TransportArea transportArea = this.get(id);// 根据当前id查询
        if (null == transportArea) {
            return result;
        }
        result = transportArea.getAreaName();
        Long parentId = transportArea.getParentId();// 获取其父id
        
        // 递归查询，直到area_levl为0L（即到达顶端）
        while(true)
        {
        	if((null == parentId))
        		break;
        	
        	String areaName = "";
        	TransportArea parentArea = this.get(parentId);
        	if((null == parentArea) || this.BASE_AREA_LEVEL.equals(parentArea.getAreaLevel()))
        	{
        		break;
        	}
        	areaName = parentArea.getAreaName();
            // 当前的AreaName和之前的累加，如：省+市+县
            result = areaName + result;
            parentId = parentArea.getParentId();
        }
        
        
        
        /*// 递归查询，直到id为888888L（即到达顶端）
        while (!this.BASE_AREA_ID.equals(parentId)) {
            TransportArea parentArea = this.get(parentId);
            String areaName = "";
            if(null == parentArea)
            	continue;
        	areaName = parentArea.getAreaName();
        	
            // 当前的AreaName和之前的累加，如：省+市+县
            result = areaName + result;
            parentId = parentArea.getParentId();
        }*/
        return result;
    }

    /**
     * 根据最末级地址id，获取完整的地址(省、市、区),
     * key:level, value TransportArea
     * 
     * @param areaId
     * @return
     */
    public Map<String,TransportArea> getAllByAreaId(String areaId) {
        Map<String,TransportArea> map = new HashMap();
        if (StringUtils.isEmpty(areaId)) {
            return map;
        }
        TransportArea transportArea = this.get(Long.valueOf(areaId));
        if(transportArea == null){
            return map;
        }
        Long parentId = transportArea.getParentId();// 获取其父id
        map.put(transportArea.getAreaLevel().toString(), transportArea);
        
        // 递归查询，直到area_levl为0L（即到达顶端）
        while(true)
        {
        	if((null == parentId))
        		break;
        	
        	TransportArea parentArea = this.get(parentId);
        	if((null == parentArea) || this.BASE_AREA_LEVEL.equals(parentArea.getAreaLevel()))
        	{
        		break;
        	}
        	// 当前的AreaName和之前的累加，如：省+市+县
            map.put(parentArea.getAreaLevel().toString(), parentArea);
            parentId = parentArea.getParentId();
        }
        
        
       /* while (!BASE_AREA_ID.equals(parentId)) {
            TransportArea parentArea = this.get(parentId);
            // 当前的AreaName和之前的累加，如：省+市+县
            map.put(parentArea.getAreaLevel().toString(), parentArea);
            parentId = parentArea.getParentId();
        }*/
        return map;
    }

    @Autowired
    public void setTransportAreaDao(TransportAreaDao transportAreaDao) {
        super.setBaseDao(transportAreaDao);
        this.transportAreaDao = transportAreaDao;
    }

    @Override
    public void setStateCityCounty(String addressCodeStr,DistributeAddress distributeAddress)
    {
    	// 省
    	TransportArea stateArea = null;
    	// 城市
    	TransportArea cityArea = null;
    	// 区县
    	TransportArea countyArea = null;
    	// 街道
    	TransportArea streetArea = null;
    	
    	if(null == addressCodeStr || "null".equals(addressCodeStr) || "".equals(addressCodeStr))
    	{
    		return;
    	}
    	
    	Long addressCode = Long.valueOf(addressCodeStr);
    			
    	stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
		if(null != stateArea)
		{
			distributeAddress.setState(stateArea.getId());
			return;
		}
		cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
		if(null != cityArea)
		{
			distributeAddress.setCity(cityArea.getId());
			stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			distributeAddress.setState(stateArea.getId());
			return;
		}
		countyArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_County.getCode()));
		if(null != countyArea)
		{
			distributeAddress.setCounty(countyArea.getId());
			cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(countyArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
			distributeAddress.setCity(cityArea.getId());
			stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			distributeAddress.setState(stateArea.getId());
		}
		streetArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_Street.getCode()));
		if(null != streetArea)
		{
			distributeAddress.setStreet(streetArea.getId());
			countyArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(streetArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_County.getCode()));
			distributeAddress.setCounty(countyArea.getId());
			cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(countyArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
			distributeAddress.setCity(cityArea.getId());
			stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			distributeAddress.setState(stateArea.getId());
		}
    	/*TransportArea streetArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_Street.getCode()));
    	if(null != streetArea)
    	{
    		addressCode = streetArea.getParentId();
    	}
    	TransportArea countyArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_County.getCode()));
    	if(null != countyArea)
    	{
    		distributeAddress.setCounty(countyArea.getId());
    		Long countyParentId = countyArea.getParentId();
    		TransportArea cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(countyParentId, Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
    		distributeAddress.setCity(cityArea.getId());
    		Long cityParentId = cityArea.getParentId();
    		TransportArea stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityParentId, Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
    		distributeAddress.setState(stateArea.getId());
    	}
    	else
    	{
    		TransportArea cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
    		if(null != cityArea)
    		{
    			distributeAddress.setCity(cityArea.getId());
    			Long cityParentId = cityArea.getParentId();
    			TransportArea stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityParentId, Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
    			distributeAddress.setState(stateArea.getId());
    		}
    		else
    		{
    			TransportArea stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
    			if(null != stateArea)
    			{
    				distributeAddress.setState(stateArea.getId());
    			}
    		}
    	}*/
    }

	@Override
	public Long getAddressCodeByLastGradId(Long state, Long city,
			Long county, Long street) {
		if(street != null){
			return street;
		}
		if (county != null) {
			return county;
		}
		if (city != null) {
			return city;
		}
		if (state != null) {
			return state;
		}
		return null;
	}
}
