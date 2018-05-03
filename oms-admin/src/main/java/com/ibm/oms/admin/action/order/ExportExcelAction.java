package com.ibm.oms.admin.action.order;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.ibm.sc.admin.action.BaseAdminAction;

/**
 */

@ParentPackage("admin")
public abstract class  ExportExcelAction extends BaseAdminAction {
	
	public InputStream getByteInputStream() throws Exception {
		return new ByteArrayInputStream(getContentBytes());
	}
	
	public byte[] getContentBytes() throws Exception {

		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
//		CsvWriter csvWriter = new CsvWriter(out, ',', Charset.forName("GBK"));
//		// 写头
//		String[] title = this.getTitiles();
//		csvWriter.writeRecord(title,true);
//		
//		List<String[]> objList = this.getDataList();
//		
//		if(objList!=null){
//			for (String[] strings : objList) {
//				csvWriter.writeRecord(strings,true);
//			}
//		}		
//		csvWriter.flush();
		return out.toByteArray();
	}
	
	// 导出
	@Action(results = {
			@Result(name = "success", type = "stream", params = { "bufferSize",
					"1024", "contentType", "excel", "inputName",
					"byteInputStream", "contentDisposition",
					"attachment;filename=${fileName}" }),
			@Result(name = "error", type = "freemarker", location = "/WEB-INF/template/admin/error.ftl") })
	public String execute() throws IOException {
		
		exportExcel(getRequest(), getResponse());
		return SUCCESS;
	}
	
	public abstract String[] getTitiles();
	
	public abstract List<String[]> getDataList();
	
	public abstract String getFileName();
	
	/*
	 *  Excel 导出
	 */
	public void exportExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException{

		HSSFWorkbook wb = new HSSFWorkbook();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/x-download");

		// 设置导出excel文件名称
		String filedisplay = this.getFileName();
		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		resp.addHeader("Content-Disposition", "attachment;filename="+ filedisplay);
		
		// 设置sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setColumnWidth(0, 50*160);

		// 设置表头
		HSSFRow row = sheet.createRow((int) 0);		
		String[] titles = getTitiles();
		
		// 生成表格样式
		HSSFCellStyle style = wb.createCellStyle();	
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
	    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    // 生成一个字体
	    HSSFFont font = wb.createFont();
	    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	    // 把字体应用到当前的样式
	    style.setFont(font);
	      
	    // 设置表头值及样式
		for(int i = 0; i < titles.length; i++){
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			cell.setCellValue(titles[i]);
		}
		
		// 读入表体数据
		List<String[]> objList = this.getDataList();	
		for(int i = 0; i < objList.size(); i++){
			// 读取数据行
			HSSFRow dataRow = sheet.createRow((int) 1 + i);	
			String[] data = objList.get(i);
			for(int j = 0; j < data.length; j++){
				HSSFCell cell = dataRow.createCell(j);
				cell.setCellValue(data[j]);
			}
		}
		
		try {
			OutputStream out = resp.getOutputStream();
			wb.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
