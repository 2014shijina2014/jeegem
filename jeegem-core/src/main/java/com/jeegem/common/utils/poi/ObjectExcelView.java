package com.jeegem.common.utils.poi;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.jeegem.common.utils.DateUtils;
/**
* 导出Exl窗口
 */
public class ObjectExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fileName=(String) model.get("fileName");
		String sheetName=(String) model.get("sheetName");
		List<String> titles = (List<String>) model.get("titles");
		List<Map<String,String>> varList =(List<Map<String, String>>) model.get("varList");
		if(StringUtils.isBlank(fileName))fileName =DateUtils.getDate("yyyyMMddHHmmss");
		if(StringUtils.isBlank(sheetName))sheetName ="sheet1";
		HSSFSheet sheet;
		HSSFCell cell;
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
		sheet = workbook.createSheet(sheetName);		
		int len = titles.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		short width = 20,height=25*20;
		sheet.setDefaultColumnWidth(width);
		for(int i=0; i<len; i++){ //设置标题
			String title = titles.get(i);
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell,title);
		}
		sheet.getRow(0).setHeight(height);	
		HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);	
		int varCount = varList.size();
		for(int i=0; i<varCount; i++){
			Map<String,String> vpd = varList.get(i);
			for(int j=0;j<len;j++){
				String varstr = vpd.get("var"+(j+1)) != null ? vpd.get("var"+(j+1)) : "";
				cell = getCell(sheet, i+1, j);
				cell.setCellStyle(contentStyle);
				setText(cell,varstr);
			}		
		}	
	}

}
