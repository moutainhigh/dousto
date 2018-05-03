/**
 * 
 */
package com.ibm.oms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.domain.persist.AreaBean;
import com.ibm.oms.domain.persist.DistributeAddress;
import com.ibm.oms.domain.persist.TransportArea;
import com.ibm.oms.service.TransportAreaCacheService;
import com.ibm.oms.service.util.CommonCacheUtil;
import com.ibm.sup.rs.bean.TransportAreaBean;
import com.ibm.sup.rs.service.TransportAreaRsService;

/**
 * @author xiaonanxiang
 * 
 */
@Service("transportAreaCacheService")
public class TransportAreaCacheServiceImpl implements TransportAreaCacheService {
	
    @Autowired
    CommonCacheUtil commonCacheUtil;
    @Autowired
	private TransportAreaRsService transportAreaRsService;
    
    /**
     * 查询省份
     * 
     * @param provinceId 省份id
     * @return
     */
    public List<AreaBean> findStateList(String provinceId) {
        List<AreaBean> list = new ArrayList<AreaBean>();
        List<TransportArea> transportAreas = commonCacheUtil.getProvinceAreaMap().get(Long.valueOf(Treelevel_Province));
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
            List<TransportArea> transportAreas = commonCacheUtil.getTransportParentAreaMap().get(NumberUtils.toLong(parentid));
            if(transportAreas==null || transportAreas.isEmpty()){
            	return list;
            }
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
        //TransportArea transportArea = this.get(id);// 根据当前id查询
        TransportArea transportArea = commonCacheUtil.getTransportIdMap().get(id);
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
        	//TransportArea parentArea = this.get(parentId);
        	TransportArea parentArea =commonCacheUtil.getTransportIdMap().get(parentId);
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
     * 根据areaCode查询完整配送地址（省+市+县）
     * 
     * @param areaCode
     * @return
     */
    public String getAllByByAreaCode(String areaCode) {
        String result = "";
        //TransportArea transportArea = this.get(id);// 根据当前id查询
        TransportArea transportArea = commonCacheUtil.getTransportAreaMap().get(areaCode);
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
            //TransportArea parentArea = this.get(parentId);
            TransportArea parentArea =commonCacheUtil.getTransportIdMap().get(parentId);
            if((null == parentArea) || BASE_AREA_LEVEL.equals(parentArea.getAreaLevel()))
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
        //TransportArea transportArea = this.get(Long.valueOf(areaId));
        TransportArea transportArea =commonCacheUtil.getTransportIdMap().get(Long.valueOf(areaId)); 
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
        	
        	//TransportArea parentArea = this.get(parentId);
        	TransportArea parentArea = commonCacheUtil.getTransportIdMap().get(parentId); 
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

    /**
     * 判断是否属于深圳区域
     * @param addressCode
     * @return
     */
    public boolean isShenzhenAddress(String addressCode){
        boolean flag = false;
        if(StringUtils.isBlank(addressCode)){
            return flag;
        }
        Map<String,TransportArea> addressMap = getAllByAreaId(addressCode);
        if(!addressMap.isEmpty()){
            Iterator<Map.Entry<String, TransportArea>> it = addressMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, TransportArea> entry = it.next();
                TransportArea area = entry.getValue();
                if(area.getAreaCode().equals(TransportAreaCacheService.ShenZhen_Area_Code)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
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
    			
    	//stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
    	stateArea = (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(addressCode,  Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
		if(null != stateArea)
		{
			distributeAddress.setState(stateArea.getId());
			return;
		}
		//cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
		cityArea =  (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(addressCode,  Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
		if(null != cityArea)
		{
			distributeAddress.setCity(cityArea.getId());
			//stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			stateArea =  (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(cityArea.getParentId(),  Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			distributeAddress.setState(stateArea.getId());
			return;
		}
		//countyArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_County.getCode()));
		countyArea =  (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(addressCode,  Long.valueOf(CommonConst.TransportArea_AreaLevel_County.getCode()));
		if(null != countyArea)
		{
			distributeAddress.setCounty(countyArea.getId());
			//cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(countyArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
			cityArea =  (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(countyArea.getParentId(),  Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
			distributeAddress.setCity(cityArea.getId());
			//stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			stateArea =  (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(cityArea.getParentId(),  Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			distributeAddress.setState(stateArea.getId());
		}
		//streetArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(addressCode, Long.valueOf(CommonConst.TransportArea_AreaLevel_Street.getCode()));
		streetArea =  (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(addressCode,  Long.valueOf(CommonConst.TransportArea_AreaLevel_Street.getCode()));
		if(null != streetArea)
		{
			distributeAddress.setStreet(streetArea.getId());
			//countyArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(streetArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_County.getCode()));
			countyArea =  (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(streetArea.getParentId(),  Long.valueOf(CommonConst.TransportArea_AreaLevel_County.getCode()));
			distributeAddress.setCounty(countyArea.getId());
			//cityArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(countyArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
			cityArea = (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(countyArea.getParentId(),  Long.valueOf(CommonConst.TransportArea_AreaLevel_City.getCode()));
			distributeAddress.setCity(cityArea.getId());
			//stateArea = transportAreaDao.getTransportAreaByIdAndAreaLevel(cityArea.getParentId(), Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
			stateArea = (TransportArea)commonCacheUtil.getTransportMultiAreaMap().get(cityArea.getParentId(),  Long.valueOf(CommonConst.TransportArea_AreaLevel_State.getCode()));
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
    
    /**
	 * 根据transportAreaId来设置addressCode。递归寻找叶子节点。
	 * 
	 * @param order
	 * @return
	 */
	public List<String> setAddressCodeByTransportAreaId(Long transportAreaId) {

		List<String> addressCodes = new ArrayList<String>();

		if (null != transportAreaId) {
		/*	transportAreas = this.transportAreaDaoImpl
					.findById(transportAreaId);*/
			// order.setAddressCode(transportAreas.get(0).getAreaCode());
			TransportArea  transportArea = commonCacheUtil.getTransportIdMap().get(transportAreaId);
			addressCodes.add(transportArea.getAreaCode());

			addSubAreaCode(transportAreaId, addressCodes);
		}
		return addressCodes;
	}

	/**
	 * 查找子areaCode
	 * 
	 * @param transportAreaId
	 * @param addressCodes
	 */
    private void addSubAreaCode(Long transportAreaId, List<String> addressCodes) {
        
         // List<TransportArea> subTransportAreas = this.transportAreaDaoImpl .findByParentId(transportAreaId);
         
        List<TransportArea> subTransportAreas = commonCacheUtil.getTransportParentAreaMap().get(transportAreaId);
        if (null == subTransportAreas)
            return;

        for (TransportArea transportArea : subTransportAreas) {
            // if (this.MAX_AREA_LEVEL.equals(transportArea.getAreaLevel())) {
            addressCodes.add(transportArea.getAreaCode());
            // }
            transportAreaId = transportArea.getId();

            // 递归查询子areaCode
            addSubAreaCode(transportAreaId, addressCodes);
        }
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
		return 0L;
	}
	
    /**
     * 根据areaCode查询完整配送地址（省+市+县）
     * 
     * @param areaCode
     * @return
     */
    public String getCombinedByAreaCode(String areaCode) {
        String result = "";
        //TransportArea transportArea = this.get(id);// 根据当前id查询
        TransportAreaBean transportArea = transportAreaRsService.getByCode(areaCode);
        if (null == transportArea) {
            return result;
        }
        result = transportArea.getAreaName();
        String parentId = transportArea.getParentId();// 获取其父id
        
        // 递归查询，直到area_levl为0L（即到达顶端）
        while(true)
        {
            if((null == parentId))
                break;
            
            String areaName = "";
            //TransportArea parentArea = this.get(parentId);
            TransportAreaBean parentArea =transportAreaRsService.getByCode(parentId);
            if((null == parentArea) || BASE_AREA_LEVEL.equals(parentArea.getAreaLevel()))
            {
                break;
            }
            areaName = parentArea.getAreaName();
            // 当前的AreaName和之前的累加，如：省+市+县
            result = areaName + result;
            parentId = parentArea.getParentId();
        }        
        return result;
    }
    
    @Override
    public void newSetStateCityCounty(String addressCodeStr,DistributeAddress distributeAddress)
    {
    	// 区县
    	TransportAreaBean anyArea = null;
    	
    	if(null == addressCodeStr || "null".equals(addressCodeStr) || "".equals(addressCodeStr))
    	{
    		return;
    	}

    	anyArea =  transportAreaRsService.getByCode(addressCodeStr);
		if(null != anyArea)
		{
			String parentId = anyArea.getParentId();// 获取其父id
			String level = anyArea.getAreaLevel();
			if(level.equals(CommonConst.TransportArea_AreaLevel_City.getCode())){	            	
            	distributeAddress.setCity(Long.valueOf(anyArea.getAreaCode()));
            }else if(level.equals(CommonConst.TransportArea_AreaLevel_State.getCode())){
            	distributeAddress.setState(Long.valueOf(anyArea.getAreaCode()));
            }else{
            	distributeAddress.setCounty(Long.valueOf(anyArea.getAreaCode()));
            }			
			// 递归查询，直到area_levl为0L（即到达顶端）
	        while(true)
	        {
	        	
	            if((null == parentId)){
	                break;
	            }	           
	            TransportAreaBean parentArea =transportAreaRsService.getByCode(parentId);
	            if((null == parentArea) || BASE_AREA_LEVEL.equals(parentArea.getAreaLevel()))
	            {
	                break;
	            }	            
	            level = parentArea.getAreaLevel();
	            if(level.equals(CommonConst.TransportArea_AreaLevel_City.getCode())){	            	
	            	distributeAddress.setCity(Long.valueOf(parentArea.getAreaCode()));
	            }else if(level.equals(CommonConst.TransportArea_AreaLevel_State.getCode())){
	            	distributeAddress.setState(Long.valueOf(parentArea.getAreaCode()));
	            }else{
	            	distributeAddress.setCounty(Long.valueOf(parentArea.getAreaCode()));
	            }
	            parentId = parentArea.getParentId();	            
	        }        
		}
    }
}