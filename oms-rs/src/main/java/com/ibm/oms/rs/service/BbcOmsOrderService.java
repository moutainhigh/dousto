package com.ibm.oms.rs.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.oms.intf.intf.BBCBatchRemarkDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderDTO;
import com.ibm.oms.intf.intf.BBCUpdateOrderStatusDTO;
import com.ibm.oms.intf.intf.CommonOutputDTO;
import com.ibm.oms.intf.intf.ResultDTO;


@Path("/bbcoms")
@Produces(MediaType.APPLICATION_JSON)
public interface BbcOmsOrderService {

    /**
     * BBC更新订单信息（收货人信息，配送信息）
     * @param bbcUpdateOrderDTO
     * @return
     */
    @POST
    @Path("/updateOrderInfo")
    public CommonOutputDTO bbcUpdateOrderInfo(BBCUpdateOrderDTO bbcUpdateOrderDTO);
    
    /**
     * BBC更新订单信息（批量修改 客服备注）
     * @param bbcUpdateOrderDTO
     * @return
     */
    @POST
    @Path("/batchUpdateRemarkInfo")
    public CommonOutputDTO bbcBatchUpdateRemarkInfo(BBCBatchRemarkDTO bbcBatchRemarkDTO);

    /**
     * BBC修改订单状态与物流状态
     * @param bbcUpdateOrderStatusDTO
     * @return 
     */
    @POST
    @Path("/updateOrderStatus")
    public CommonOutputDTO bbcUpdateOrderStatus(BBCUpdateOrderStatusDTO bbcUpdateOrderStatusDTO);
    
    /**
     * BBC订单审核接口
     * @param bbcUpdateOrderStatusDTO
     * @return 
     */
    @GET
    @Path("/orderAudit")
    public CommonOutputDTO bbcOrderAudit(@QueryParam("orderNo") String orderNo,@QueryParam("operateName")String operateName,@QueryParam("flag")String flag,@QueryParam("exceptionFlag")String exceptionFlag);
   
    /**
     * BBC退货订单已收货
     * @param bbcUpdateOrderStatusDTO
     * @return 
     */
    @GET
    @Path("/saleAfterOrderReceive")
    public CommonOutputDTO bbcSaleAfterOrderReceive(@QueryParam("saleAfterSubNo") String saleAfterSubNo,@QueryParam("operateName")String operateName);
    
    /**
     * BBC退货订单审核
     * @param bbcUpdateOrderStatusDTO
     * @return 
     */
    @GET
    @Path("/saleAfterOrderAudit")
    public CommonOutputDTO bbcSaleAfterOrderAudit(@QueryParam("returnSubNo") String returnSubNo,@QueryParam("operateName")String operateName);
    
    /**
     * BBC正向订单取消订单
     * @param orderNo
     * @return
     */
    @GET
    @Path("/cancleOrder")
    public ResultDTO bbcCancleOrder(@QueryParam("orderNo") String orderNo,@QueryParam("operateName")String operateName);
    
    /**
     * BBC逆向订单取消订单
     * @param orderSubNoR
     * @return
     */
    @GET
    @Path("/cancleSaleAfterOrder")
    public ResultDTO bbcCancleSaleAfterOrder(@QueryParam("orderSubNoR") String orderSubNoR,@QueryParam("operateName")String operateName);
    
    
    @GET
    @Path("/updateOrderStatusByActionCode")
    public boolean updateOrderStatusBySaveProcess(@QueryParam("orderSubNo") String orderSubNo, @QueryParam("statusActionCode")String statusActionCode);
    
    @GET
    @Path("/updateOrderTotalStatus")
    public CommonOutputDTO updateOrderStatus(@QueryParam("orderSubNo") String orderSubNo, @QueryParam("payStatus")String totalPayStatus,@QueryParam("confirmStatus")String totalPayConfirmStatus,@QueryParam("totalStatus")String totalStatus);
    
}

