package com.ibm.oms.service.util;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.Collator;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.oms.dao.constant.CommonConst;
import com.ibm.oms.domain.persist.AreaBean;
import com.ibm.oms.domain.persist.IntfReceived;
import com.ibm.oms.domain.persist.IntfSent;
import com.ibm.oms.domain.persist.OrderDic;
import com.ibm.oms.domain.persist.SelfTakeMerchantTmp;
import com.ibm.oms.domain.persist.SelfTakePointTmp;
import com.ibm.oms.domain.persist.StatusDict;
import com.ibm.oms.intf.intf.BtcOmsReceiveOrderDTO;
import com.ibm.oms.intf.intf.inner.OrderItemDTO;
import com.ibm.oms.intf.intf.inner.OrderItemVirtualDTO;
import com.ibm.oms.intf.intf.inner.OrderMainDTO;
import com.ibm.oms.intf.intf.inner.OrderSubDTO;
import com.ibm.oms.service.IntfReceivedService;
import com.ibm.oms.service.IntfSentService;
import com.ibm.oms.service.OrdiErrOptLogService;
import com.ibm.oms.service.SelfTakePointTmpService;
import com.ibm.sc.beans.sys.OptionBean;
import com.ibm.sc.model.payment.PaymentMethod;
import com.ibm.sc.model.payment.PaymentMode;
import com.ibm.sc.model.shipping.SelfTakePoint;
import com.ibm.sc.model.sys.Option;
import com.ibm.sup.rs.service.OptionRsService;

/**
 * @author xiaohl
 * 
 */
@Component
public class CommonUtilService {
    @Autowired
    IntfSentService intfSentService;
    @Autowired
    IntfReceivedService intfReceivedService;
    @Autowired
    OrdiErrOptLogService ordiErrOptLogService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    SelfTakePointTmpService selfTakePointTmpService;
    @Autowired
	static OptionRsService  optionRsService;//数据字典
    private final static Logger logger = LoggerFactory.getLogger(CommonUtilService.class);
    public BigDecimal string2BigDecimal(String str) {
        return NumberUtils.isNumber(str) ? new BigDecimal(str).setScale(2, 4) : null;
    }
    
    /**
     * 小数点后的位数不做限制
     * @param str
     * @return
     */
    public BigDecimal string2BigDecimalNoLimit(String str) {
        return NumberUtils.isNumber(str) ? new BigDecimal(str) : null;
    }

    public Long StrToLong(String str) {
        return NumberUtils.isDigits(str) ? Long.parseLong(str) : null;
    }
    public String Long2Str(Long lv) {
        return lv == null ? "" : lv.toString();
    }

    public String booleanToString(Boolean bl){
        return (bl!=null && bl) ? "1":"0";
    }
    
    public <TOutput> TOutput jsonGet(String url, Class<TOutput> ct, Integer timeOut) {
        if (timeOut != null) {
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(timeOut);
        }
        logger.info("get[{}]]",url);
        return (TOutput) restTemplate.getForObject(url, ct);
    }

    public <TOutput> TOutput jsonGetWithTrack(String url, String intfCode, Class<TOutput> ct, Integer timeOut) {
        // 发起报文写入db
        logger.info("begin getMethod: {}", intfCode);
        
        IntfSent is = writeIntfSentJson(null, null, intfCode, url);
        TOutput output = null;
        boolean exceptionThrown = false;
        try {
        	 logger.info("get[{}]]",url);
            output = jsonGet(url, ct, timeOut);
        } catch (Exception e) {
            logger.info("{}", e);
            exceptionThrown = true;
        }

        if (exceptionThrown || output == null) {
            is.setSucceedFlag(CommonConstService.FAILEDLong);
            intfSentService.update(is);
            return null;
        }
        is.setSucceedFlag(CommonConstService.OKLong);
        intfSentService.update(is);
        
        // 返回写入报文
        IntfReceived irv = saveIntfReceivedJson(null, null, intfCode, (Serializable) output, null);
        
        return output;
    }

    public <TInput, TOutput> TOutput jsonPost(String url, TInput object, Class<TOutput> outputClazz, Integer timeOut) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity(object, headers);
        logger.info("post[{}]-params[{}]",url,ObjectTransJsonstr(object));
        if(timeOut != null){
            ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(timeOut.intValue());
        }
        TOutput returnedOutput = (TOutput) restTemplate.postForObject(url, request, outputClazz);
        return returnedOutput;
    }

    /**
     * @param url
     * @param intfCode
     * @param object
     * @param outputClazz
     * @return
     */
    public <TInput, TOutput> TOutput jsonPostWithTrack(String url, String intfCode, TInput object,
            Class<TOutput> outputClazz, Integer timeOut) {
    	 logger.info("post[{}]-params[{}]",url,ObjectTransJsonstr(object));
        // 发起报文写入db
        IntfSent is = writeIntfSentJson(null, null, intfCode, (Serializable) object);
        // 开始调用接口
        TOutput output = null;
        boolean exceptionThrown = false;
        try {
            output = jsonPost(url, object, outputClazz, timeOut);
           
        } catch (Exception e) {
            logger.info("{}", e);
            exceptionThrown = true;
        }
        if (exceptionThrown || output == null) {
            is.setSucceedFlag(CommonConstService.FAILEDLong);
            intfSentService.update(is);
            return null;
        }
        is.setSucceedFlag(CommonConstService.OKLong);
        intfSentService.update(is);
        // 返回写入报文
        IntfReceived irv = saveIntfReceivedJson(null, null, intfCode, (Serializable) output, null);
        /*String msg = CommonUtilService.createOrderValidate(output);
        if(!CommonConstService.OK.equals(msg)){
            return null;
        }*/
        return output;
    }

    /**如果对象中存在orderNo，orderSubNo 写入db**/
    
    public String[] getOrdeNoFromFCObject(Object obj){
        String[] orderNoFields = new String[]{"orderNo"};
        String[] orderSubNoFields = new String[]{"orderSubNo","txLogisticID"};
        if (obj == null) {return null;}  
        String orderNo = null;
        String orderSubNo = null;
        Class clazz = obj.getClass();
        if(clazz.isArray() || obj instanceof ArrayList || obj instanceof HashMap){
            return new String[]{null, null};
        }else{
            for (String orderNoFieldName : orderNoFields) {
                try {
                    Field orderNoField = clazz.getDeclaredField(orderNoFieldName);
                    orderNoField.setAccessible(true);  
                    orderNo = (String) orderNoField.get(obj);
                    break;
                } catch (Exception e) {
                }
            }
            for (String orderSubNoFieldName : orderSubNoFields) {
                try {
                    Field orderSubNoField = clazz.getDeclaredField(orderSubNoFieldName);
                    orderSubNoField.setAccessible(true);  
                    orderSubNo = (String) orderSubNoField.get(obj);
                    break;
                } catch (Exception e) {
                }
            }
            return new String[]{orderNo, orderSubNo};
        }
 
    }
    
    public static Long booleanToLong(Boolean isLowGross) {
        return isLowGross ? 1l : 0l;
    }

    public boolean longToBoolean(Long input) {
        return (input != null && input.longValue()==1l) ? true : false;
    }
    
    public static Date strToDate(String str, String format) {
        if (str == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            logger.info("{}", e);
            return null;
        }
    }

    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 
     * 功能描述: 写接口接收表
     * @param orderNo 
     * @param orderSubNo 
     * 
     * @param btcOrderNo
     * @param intfCode
     * @param dio
     * @param status: succeed flag
     * @return
     * 
     */
    public IntfReceived saveIntfReceivedJson(String orderNo, String orderSubNo, String intfCode, Serializable dio, Long status) {
        String[] orderNos = getOrdeNoFromFCObject(dio);
        IntfReceived rec = new IntfReceived();
        ObjectMapper mapper = new ObjectMapper();
        String msg;
        try {
            msg = mapper.writeValueAsString(dio);
            rec.setOrderNo(orderNo == null?orderNos[0]:orderNo);
            rec.setOrderSubNo(orderSubNo == null?orderNos[1]:orderSubNo);
            rec.setIntfCode(intfCode);
            rec.setMsg(msg);
            rec.setCreateTime(new Date());
            if(status == null){
                status = CommonConstService.WAITLong;
            }
            rec.setSucceed(status);
            intfReceivedService.save(rec);
        } catch (JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
        return rec;
    }

    public IntfReceived writeListIntfReceivedJson(String btcOrderNo, String intfCode, Collection dio) {
        IntfReceived rec = new IntfReceived();
        ObjectMapper mapper = new ObjectMapper();
        String msg;
        try {
            msg = mapper.writeValueAsString(dio);
            if (NumberUtils.isNumber(btcOrderNo)) {
                rec.setBtcOrderNo(btcOrderNo);
            }
            rec.setSucceed(CommonConstService.WAITLong);
            rec.setIntfCode(intfCode);
            rec.setMsg(msg);
            rec.setCreateTime(new Date());
        } catch (JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
        return rec;
    }

   /**
    * 功能描述: 写接口发送表
    * @param btcOrderNo
    * @param orderNo
    * @param intfCode
    * @param dio
    * @return
    */
    public IntfSent writeIntfSentJson(String orderNo, String orderSubNo, String intfCode, Serializable dio) {
        String[] orderNos = getOrdeNoFromFCObject(dio);
        IntfSent rec = new IntfSent();
        if(dio == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        String msg;
        try {
            msg = mapper.writeValueAsString(dio);
            rec.setOrderNo(orderNo == null ? orderNos[0] : orderNo);
            rec.setOrderSubNo(orderSubNo == null ? orderNos[1] : orderSubNo);
            rec.setIntfCode(intfCode);
            rec.setMsg(msg);
            rec.setCreateTime(new Date());
        } catch (JsonProcessingException e) {
            logger.error("", e);
            return null;
        }
        intfSentService.save(rec);
        return rec;
    }

    /**
     * @param dio
     * @return nullable
     */
    public String writeIntObjectJson(Object dio) {

        ObjectMapper mapper = new ObjectMapper();
        String msg = null;
        try {
            msg = mapper.writeValueAsString(dio);
        } catch (JsonProcessingException e) {
            logger.error("{}", e);
        }
        return msg;
    }

    /**
     * 对接口入口对象作校验
     * @param <T>
     * @param dio
     * @return
     */
    public static <T> String createOrderValidate(T dio) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(dio);
        StringBuilder violationMsg = new StringBuilder();
        if (violations.size() > 0) {
            for (ConstraintViolation<T> v : violations) {
                violationMsg.append("msg:" + v.getLeafBean().getClass().getSimpleName()+":"+ v.getMessage() + " invalidValue:" + v.getInvalidValue() + "\r\n");
            }
            return violationMsg.toString();
        }
        //btc snapshot字符串二次校验
        if(dio instanceof BtcOmsReceiveOrderDTO){
            ObjectMapper om = new ObjectMapper();
            BtcOmsReceiveOrderDTO btcDIO = (BtcOmsReceiveOrderDTO)dio;
            for(OrderMainDTO omDTO:btcDIO.getOmDTO()){
                List<OrderSubDTO> osDTOs = omDTO.getOsDTOs();
                if(osDTOs == null || osDTOs.isEmpty()){
                    break;
                }
                for(OrderSubDTO osDTO:osDTOs){
                    if(osDTO.getOiDTOs() != null && !osDTO.getOiDTOs().isEmpty()){
                        for(OrderItemDTO oi:osDTO.getOiDTOs()){
                            //非组合商品，却没有条码
                            boolean suite = CommonConst.OrderItem_OrderItemClass_Suite.getCode().equals(oi.getOrderItemClass());
                            boolean hasBarCode = !StringUtils.isBlank(oi.getBarCode());
                            if(!suite && !hasBarCode){
                                violationMsg.append("suite item should has bar_code");
                                return violationMsg.toString();
                            }
                            
                            //20180123 订单行快照注释掉 
                            //TODO 待确认订单行快照功能
//                            String snapShotStr = oi.getItemSnapshot();
//                            OrderItemSnapShotDTO snapShotDTO = null;
//                            try {
//                            	snapShotDTO = om.readValue(snapShotStr, OrderItemSnapShotDTO.class);
//                            } catch (Exception e) {
//                                violationMsg.append("itemSnapShot resolved Exception");
//                                logger.info("{}", e);
//                                return violationMsg.toString();
//                            } 
//                            Set<ConstraintViolation<OrderItemSnapShotDTO>> snapShotViolations = validator.validate(snapShotDTO);
//                            if (snapShotViolations.size() > 0) {
//                                for (ConstraintViolation<OrderItemSnapShotDTO> v : snapShotViolations) {
//                                    violationMsg.append("msg:" + v.getLeafBean().getClass().getSimpleName()+":"+ v.getMessage() + " invalidValue:" + v.getInvalidValue() + "\r\n");
//                                    logger.info("{}", violationMsg.toString());
//                                }
//                                return violationMsg.toString();
//                            }
                       }
                    }else if(osDTO.getOivDTOs() != null && !osDTO.getOivDTOs().isEmpty()){
                        for(OrderItemVirtualDTO oiv:osDTO.getOivDTOs()){
                        	//20180123 订单行快照注释掉 
                            //TODO 待确认订单行快照功能
                        	//暂时没有虚拟商品的概念
//                        	String snapShotStr = oiv.getItemSnapshot();
//                            OrderItemSnapShotDTO snapShotDTO = null;
//                            try {
//                                snapShotDTO = om.readValue(snapShotStr, OrderItemSnapShotDTO.class);
//                            } catch (Exception e) {
//                                violationMsg.append("itemSnapShot resolved Exception");
//                                logger.info("{}", e);
//                                return violationMsg.toString();
//                            } 
//                            Set<ConstraintViolation<OrderItemSnapShotDTO>> snapShotViolations = validator.validate(snapShotDTO);
//                            if(!CommonConst.OrderSub_Distribute_Type7.getCode().equals(osDTO.getDistributeType())){
//                                violationMsg.append("for a virtual product, distribute_type must '7' \r\n");
//                                return violationMsg.toString();
//                            }
//                            if (snapShotViolations.size() > 0) {
//                                for (ConstraintViolation<OrderItemSnapShotDTO> v : snapShotViolations) {
//                                    if("shipCat".equals(v.getPropertyPath().toString())){
//                                        //虚拟商品忽略shipCat
//                                        continue;
//                                    }
//                                    violationMsg.append("msg:" + v.getLeafBean().getClass().getSimpleName()+":"+ v.getMessage() + " invalidValue:" + v.getInvalidValue() + "\r\n");
//                                    logger.info("{}", violationMsg.toString());
//                                    return violationMsg.toString();
//                                }
//                            }
                        }
                    }

                }
            }
            
        }
        return CommonConstService.OK;
    }

    /**
     * @param <T>
     * @param dio
     * @return
     */
    public <T> String createOrderListValidate(Collection<T> dios) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        StringBuilder violationMsg = new StringBuilder(CommonConstService.OK);
        for (T dio : dios) {
            Set<ConstraintViolation<T>> violations = validator.validate(dio);
            if (violations.size() > 0) {
                for (ConstraintViolation<T> v : violations) {
                    violationMsg.append("msg:" + v.getMessage() + "  invalidValue:" + v.getInvalidValue() + "\r\n");
                }
            }
        }

        if (violationMsg.length() > 0) {
            return violationMsg.toString();
        } else {
            return CommonConstService.OK;
        }

    }
    
    /**
     * 根据displayName排序StatusDict对象List集合。 
     * 把对象拆成字符串list，排序，再合成对象list
	 * @param list
	 * @return
	 */
	public List<StatusDict> sortStatusDictListbyDisplayName(
			List<StatusDict> list) {
		List<String> nameCodeList = new ArrayList<String>();
		for (StatusDict statusDict : list) {
			if (null == statusDict){
				continue;
			}
			nameCodeList.add(statusDict.getDisplayName() + ","
					+ statusDict.getStatusCode()); 
			// ["DisplayName1,statusCode1","DisplayName2,statusCode2","DisplayName3,statusCode3"]
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		StatusDict statusDictTmp = null;
        List<StatusDict> resultList = new ArrayList<StatusDict>();
		resultList.add(null);
		for (String nameCode : nameCodeList) {
			statusDictTmp = new StatusDict();
			nameCodeSplits = nameCode.split(",");
			statusDictTmp.setDisplayName(nameCodeSplits[0]);
			statusDictTmp.setStatusCode(nameCodeSplits[1]);
			resultList.add(statusDictTmp);
		}
		return resultList;
	}
	
	/**
	 * 根据dicName排序OrderDic对象List集合
	 * @param list
	 * @return
	 */
	public List<OrderDic> sortOrderDicListbyDisplayName(List<OrderDic> list) {
		List<OrderDic> resultList = new ArrayList<OrderDic>();
		List<String> nameCodeList = new ArrayList<String>();
		for (OrderDic dic : list) {
			if (null == dic)
				continue;
			nameCodeList.add(dic.getDicName() + "," + dic.getDicCode()); // ["name1,code1","name2,code2","name3,code3"]
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		OrderDic dictTmp = null;
		resultList.add(null);
		for (String nameCode : nameCodeList) {
			dictTmp = new OrderDic();
			nameCodeSplits = nameCode.split(",");
			dictTmp.setDicName(nameCodeSplits[0]);
			dictTmp.setDicCode(nameCodeSplits[1]);
			resultList.add(dictTmp);
		}
		return resultList;
	}
	
	/**
	 * 根据name排序Option对象List集合
	 * @param list
	 * @return
	 */
	public List<Option> sortOptionListbyDisplayName(List<Option> list) {
		List<Option> resultList = new ArrayList<Option>();
		List<String> nameCodeList = new ArrayList<String>();
		for (Option dic : list) {
			if (null == dic)
				continue;
			nameCodeList.add(dic.getName() + "," + dic.getCode()); // ["name1,code1","name2,code2","name3,code3"]
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		Option dictTmp = null;
		resultList.add(null);
		for (String nameCode : nameCodeList) {
			dictTmp = new Option();
			nameCodeSplits = nameCode.split(",");
			dictTmp.setName(nameCodeSplits[0]);
			dictTmp.setCode(nameCodeSplits[1]);
			resultList.add(dictTmp);
		}
		return resultList;
	}
	
	/**
	 * 根据name排序PaymentMode对象List集合
	 * @param list
	 * @return
	 */
	public List<PaymentMode> sortPaymentModeListbyDisplayName(List<PaymentMode> list) {
		List<PaymentMode> resultList = new ArrayList<PaymentMode>();
		List<String> nameCodeList = new ArrayList<String>();
		for (PaymentMode dic : list) {
			if (null == dic)
				continue;
			nameCodeList.add(dic.getName() + "," + dic.getCode()); // ["name1,code1","name2,code2","name3,code3"]
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		PaymentMode dictTmp = null;
		resultList.add(null);
		for (String nameCode : nameCodeList) {
			dictTmp = new PaymentMode();
			nameCodeSplits = nameCode.split(",");
			dictTmp.setName(nameCodeSplits[0]);
			dictTmp.setCode(nameCodeSplits[1]);
			resultList.add(dictTmp);
		}
		return resultList;
	}
	
	/**
	 * 根据name排序PaymentMethod对象List集合
	 * @param list
	 * @return
	 */
	public List<PaymentMethod> sortPaymentMethodListbyDisplayName(List<PaymentMethod> list) {
		List<PaymentMethod> resultList = new ArrayList<PaymentMethod>();
		List<String> nameCodeList = new ArrayList<String>();
		for (PaymentMethod dic : list) {
			if (null == dic)
				continue;
			nameCodeList.add(dic.getName() + "," + dic.getId()); // ["name1,code1","name2,code2","name3,code3"]
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		PaymentMethod dictTmp = null;
//		resultList.add(null);
		for (String nameCode : nameCodeList) {
			dictTmp = new PaymentMethod();
			nameCodeSplits = nameCode.split(",");
			dictTmp.setName(nameCodeSplits[0]);
			dictTmp.setId(Long.valueOf(nameCodeSplits[1]));
			resultList.add(dictTmp);
		}
		return resultList;
	}
	
	/**
	 * 根据name排序SelfTakeMerchantTmp对象List集合
	 * @param list
	 * @return
	 */
	public List<SelfTakeMerchantTmp> sortSelfTakeMerchantTmpListbyDisplayName(List<SelfTakeMerchantTmp> list) {
		List<SelfTakeMerchantTmp> resultList = new ArrayList<SelfTakeMerchantTmp>();
		List<String> nameCodeList = new ArrayList<String>();
		for (SelfTakeMerchantTmp dic : list) {
			if (null == dic)
				continue;
			nameCodeList.add(dic.getName() + "," + dic.getCode() + "," + dic.isChecked()); 
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		SelfTakeMerchantTmp dictTmp = null;
		//resultList.add(null);
		for (String nameCode : nameCodeList) {
			dictTmp = new SelfTakeMerchantTmp();
			nameCodeSplits = nameCode.split(",");
			dictTmp.setName(nameCodeSplits[0]);
			dictTmp.setCode(nameCodeSplits[1]);
			dictTmp.setChecked(Boolean.valueOf(nameCodeSplits[2]));
			resultList.add(dictTmp);
		}
		return resultList;
	}
	
	/**
	 * 根据name排序SelfTakePointTmp对象List集合
	 * @param list
	 * @return
	 */
	public List<SelfTakePointTmp> sortSelfTakePointTmpListbyDisplayName(List<SelfTakePointTmp> list) {
		List<SelfTakePointTmp> resultList = new ArrayList<SelfTakePointTmp>();
		List<String> nameCodeList = new ArrayList<String>();
		for (SelfTakePointTmp dic : list) {
			if (null == dic)
				continue;
			nameCodeList.add(dic.getPointName() + "," + dic.getId() + "," + dic.isChecked() + "," + dic.getDetailAddress()); 
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		SelfTakePointTmp dictTmp = null;
		//resultList.add(null);
		for (String nameCode : nameCodeList) {
			dictTmp = new SelfTakePointTmp();
			nameCodeSplits = nameCode.split(",");
			dictTmp.setPointName(nameCodeSplits[0]);
			dictTmp.setId(nameCodeSplits[1]);
			dictTmp.setChecked(Boolean.valueOf(nameCodeSplits[2]));
			dictTmp.setDetailAddress(nameCodeSplits[3]);
			resultList.add(dictTmp);
		}
		return resultList;
	}
	
	/**
	 * 根据name排序AreaBean对象List集合
	 * @param list
	 * @return
	 */
	public List<AreaBean> sortAreaBeanListbyDisplayName(List<AreaBean> list) {
		List<AreaBean> resultList = new ArrayList<AreaBean>();
		List<String> nameCodeList = new ArrayList<String>();
		for (AreaBean dic : list) {
			if (null == dic)
				continue;
			nameCodeList.add(dic.getName() + "," + dic.getId() + "," + dic.isChecked()); // ["name1,code1,checked1","name2,code2,checked2","name3,code3,checked3"]
		}
		// 排序
		Collections.sort(nameCodeList,
				Collator.getInstance(java.util.Locale.CHINA));
		String[] nameCodeSplits = null;
		AreaBean dictTmp = null;
		//resultList.add(null);
		for (String nameCode : nameCodeList) {
			dictTmp = new AreaBean();
			nameCodeSplits = nameCode.split(",");
			dictTmp.setName(nameCodeSplits[0]);
			dictTmp.setId(nameCodeSplits[1]);
			dictTmp.setChecked(Boolean.valueOf(nameCodeSplits[2]));
			resultList.add(dictTmp);
		}
		return resultList;
	}
    
    
    
    public String bigDecimal2String(BigDecimal saleTotalMoney) {
        return saleTotalMoney == null ? "0" : saleTotalMoney.setScale(2, 4).toString();
    }
    
    public SimpleDateFormat format24Hours(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
    
    public SimpleDateFormat formatDay(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    public double bigDecimalToDouble(BigDecimal bd) {
        return bd == null ? 0d : bd.doubleValue();
    }
    
    /**
     * 判断是否需退款：1、需退款(网天财务)，2、门店退款
     * @return
     */
    public boolean checkIfNeedRefund(Long ifNeedRefund) {
        if(null == ifNeedRefund){
            return false;
        }
        if(ifNeedRefund.longValue()==CommonConst.OrderMain_IfNeedRefund_Yes.getCodeLong() 
                || ifNeedRefund.longValue()==CommonConst.OrderMain_IfNeedRefund_Yes_Store.getCodeLong()){
            return true;
        }
        return false;
    }
    public SimpleDateFormat formatDayToHours(){
        return new SimpleDateFormat("HH");
    }
    
    /**
     * 设置自提点信息
     * @param selfFetchAddress
     * @param selfTakePoint
     */
    public void setSelfTakePointInfo(String selfFetchAddress,SelfTakePoint selfTakePoint)
    {
    	if(!StringUtils.isBlank(selfFetchAddress)&&!"null".equals(selfFetchAddress)){
	    	String partnerId= selfTakePointTmpService.findPointDeliverPartnerId(selfFetchAddress);
			selfTakePoint.setId(StringUtils.isBlank(selfFetchAddress)?null:Long.valueOf(selfFetchAddress));
			selfTakePoint.setPointDeliverPartnerId(StringUtils.isBlank(partnerId)?null:Long.valueOf(partnerId));
			selfTakePoint.setAddress(selfTakePointTmpService.findPointDetailAddress(selfFetchAddress));
    	}
    }
    
    //输出对象为json  日记级别为info
    public void logInfoObjectToJson(String tip,Logger logger,Object o){
		 ObjectMapper om = new ObjectMapper();
	     String outputStr;
	     try {
	         outputStr = om.writeValueAsString(o);
	         logger.info("[{}:{}]",tip,outputStr);
	     } catch (JsonProcessingException e) {
	         logger.info("{}", e);
	     }
	}
    
    public String ObjectTransJsonstr(Object o){
    	 ObjectMapper om = new ObjectMapper();
    	 String outputStr = "";
    	 try {
			outputStr = om.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return outputStr;
    }
    /**
     * 获取支付回调URL
     * @param strDate
     * @return
     */
    public  static String getPayCallbackUrl() {
    	List<OptionBean> optionByGroupCode = optionRsService.getOptionByGroupCode("PayType");
    	for(int i = 0;i < optionByGroupCode.size(); i++){
    		OptionBean optionBean = optionByGroupCode.get(i);
    		if("MyInformURL".equals(optionBean.getCode())){
    			 return optionBean.getName();
    		}
    	}
		return null;
    }
	public <T extends Object> T jsonstrToObject(String jsonstr, Class<?> cl, T t){
    	ObjectMapper om = new ObjectMapper();
    	try {
			t = (T) om.readValue(jsonstr.trim(), cl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return t;
    }
    /**
     * String 转换 Date
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
  	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  	      ParsePosition pos = new ParsePosition(0); 
  	      Date strtodate = formatter.parse(strDate.trim(), pos);
  	     return strtodate;
    }

	/**
	 * xml转换成JavaBean
	 * @param xml
	 * @param c
	 * @return
	 */
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
		T t = null;
		try {
			JAXBContext context = JAXBContext.newInstance(c);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			t = (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}
    public static void main(String[] args) {
		
	}
    
}
