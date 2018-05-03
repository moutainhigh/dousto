package com.ibm.oms.service.business;

import com.ibm.oms.domain.persist.OrderMain;
import com.ibm.oms.intf.intf.BtcOmsReturnChangeDTO;
import com.ibm.oms.intf.intf.CommodityStockInfoDTO;
import com.ibm.oms.intf.intf.CommodityStockInfoOutPutDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.OmsMemberVipCardOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;

/**
 * @author xiaohl
 * 售后意向单对外接口处理类
 * 
 */
public interface ReturnChangeOrderService {

    final String OK = "OK";
    final String FAILED = "FAILED";



    public final String errorMsgOrderMain = "ReturnChangeOrderServiceImpl.writeOrderMain cloneBean throws Exception";
    public final String errorMsgOrderSub = "ReturnChangeOrderServiceImpl.writeOrderSub cloneBean throws Exception";
    public final String errorMsgRetChgItem = "ReturnChangeOrderServiceImpl.writeOrderRetChgItem cloneBean throws Exception";
    public final String errorMsgOrderPay = "ReturnChangeOrderServiceImpl.writeOrderPay cloneBean throws Exception";
    public final String errorMsgRetChg = "ReturnChangeOrderServiceImpl.errorMsgRetChg throws Exception";
    public final String errorMsg_AddExcOrder_Fail_InventoryLock = "换货出库单添加失败：锁定库存不成功";

    /** WMS售后意向单传输 **/
    public final String WMS_DateFormate = "yyyy-MM-dd HH:mm:ss";
    public final String WMS_ReturnFlag_Success = "1";
    public final String WMS_WarehouseID = "0196";// 仓库
    public final String WMS_CustomerID = "01";// 货主
    public final String WMS_ModiFlag = "Y";// 修改标记
    public final String WMS_SupplierId = "0100";// 供应商id
    // WmsSecurityInfo相关
    public final String WMS_WmsSecurityInfo_UserName = "flux";
    public final String WMS_WmsSecurityInfo_Password = "flux";
    // WmsParamInfo相关
    public final String WMS_WmsParamInfo_Customerid = "TH";
    public final String WMS_WmsParamInfo_Messageid = "104";
    public final String WMS_WmsParamInfo_Warehouseid = "WH01";
    public final String WMS_WmsParamInfo_Stdno = "ASN";
    //WMS意向单类型
    public final String WMS_AsnType_RET = "RET";//退货
    public final String WMS_AsnType_REJ = "REJ";//拒收
    public final String WMS_AsnType_RT = "RT";//换货

    /**取消入库的参数**/
    public final String WMS_ReturnCode_ReCancel = "999";//重复取消返回code
    public final String WMS_WmsParamInfo_Warehouseid_Cancel = "WH01";
    public final String WMS_WmsParamInfo_Messageid_Cancel = "106_DEL";
    public final String WMS_WmsParamInfo_Customerid_Cancel = "TH";
    public final String WMS_WmsParamInfo_Stdno_Cancel = "DEL";
    
    
    public final int WMS_timeOut = 5000;//WMS入库接口超时时间(毫秒)

    /** 售后意向单传输至TMS **/
    public final String TMS_TYPE_OS = "os";// 销售单
    public final String TMS_TYPE_HH = "hh";// 换货单
    public final String TMS_TYPE_TH = "th";// 退货单
    public final String TMS_TYPE_JS = "js";// 拒收单
    public final int TMS_FLAG_NORMAL = 0;//正常订单

    /**
     * 功能描述：获取sku信息
     * @param skuCode
     * @return
     */
    public CommodityStockInfoDTO getSkuInfo(String skuCode);
    
    /**
     * 功能描述: 获取商品库存信息
     * @param skuCode sku编码
     * @return
     */
    public CommodityStockInfoOutPutDTO getStockInfo(String skuCode);
    
    
    /**
     * 
     * 功能描述: 创建退货订单(B2C)
     * 
     * @param returnChangeDTO
     * @return CommonOutputDTO
     */
    public CommonOutputDTO writeReturnOrder(BtcOmsReturnChangeDTO returnChangeDTO);

    /**
     * 
     * 功能描述: 创建换货订单(B2C)
     * 
     * @param returnChangeDTO
     * @return CommonOutputDTO
     */
    public CommonOutputDTO writeChangeOrder(BtcOmsReturnChangeDTO returnChangeDTO);

    /**
     * 取消订单(B2C)
     * 
     * @param orderSubNo
     * @param cancelScene
     */
    CommonOutputDTO writeCancelOrder(String orderSubNo, String operator);

    /**
     * 退、换、拒收订单传输富勒WMS（审核通过后）
     * 
     * @param orderSubNo
     */
    public boolean sendOmsToWmsSaleAfterOrder(String orderSubNo);

    /**
     * 售后意向单传输TMS(出库后：WMS物流出库状态传输至OMS时，调用此接口)
     * 
     * @param orderSubNo
     */
    public boolean sendOmsToTmsSaleAfterOrder(String orderSubNo);
    
    /**
     * 取消入库单传输至WMS
     * @param orderSubNo
     * @return
     */
    public boolean sendOmsToWmsSaleAfterOrderCancel(String orderSubNoR);
    
    /**
     * 取消入库单传输至TMS
     * @param orderSubNo
     * @return
     */
    public boolean sendOmsToTmsSaleAfterOrderCancel(String orderSubNoR);
    
    
    /**
     * 退款 退maycard
     * @param orderNo 主订单号
     * @param operator 操作者
     * @param orderMain
     * @param isAddErrLog 接口异常时是否记录异常记录表
     * @return
     */
    public ResultDTO returnMyCard(String orderNo,String operator,OrderMain orderMain,boolean isAddErrLog);
    
    /**
     * 积分操作（增加、删除）
     * @param operator 操作者
     * @param orderNo 主订单号
     * @param isAddFlag  true:加回积分，false：扣减积分
     * @param isAddErrLog 接口异常时是否记录异常记录表
     * @return
     */
    public ResultDTO handelIntegral(String operator,String orderNo,boolean isAddFlag,boolean isAddErrLog);
    
    /**
     * 会员VIP卡账户查询
     * @param memberNo
     * @return
     */
    public OmsMemberVipCardOutputDTO searchMemberVipCard(String memberNo);

}