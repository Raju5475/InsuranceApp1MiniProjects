package com.Raju.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Raju.entity.CitizenPlan;
import com.Raju.repo.citizenRepo;
@Component
public class ExcelGenerator {
	@Autowired
citizenRepo repo;
public void generateExcel(HttpServletResponse response,List<CitizenPlan> list) throws Exception{
	
	Workbook workbook=new HSSFWorkbook();
	Sheet sheet = workbook.createSheet("plans-Data");
	Row headerRow = sheet.createRow(0);
	headerRow.createCell(0).setCellValue("sno");
	headerRow.createCell(1).setCellValue("Holder Name");
	headerRow.createCell(2).setCellValue("Gender");
	headerRow.createCell(3).setCellValue("Plan Name");
	headerRow.createCell(4).setCellValue("Plan Status");
	headerRow.createCell(5).setCellValue("Benifit Amount");
	headerRow.createCell(6).setCellValue("Denial Reason");
	headerRow.createCell(7).setCellValue("Terminated Reason");
	

	int index=1;
	
	for(CitizenPlan plan:list) {
		Row row = sheet.createRow(index);
		row.createCell(0).setCellValue(plan.getCitizenName());
		row.createCell(1).setCellValue(plan.getGender());
		row.createCell(2).setCellValue(plan.getPlanName());
		row.createCell(3).setCellValue(plan.getPlanStatus());
		if(null!=plan.getBenefitAmount()) {
			row.createCell(4).setCellValue(plan.getBenefitAmount());
		}else {
			row.createCell(4).setCellValue("N/A");
			
			
		}
		if(null!=plan.getDenialReason()) {
			
			row.createCell(5).setCellValue(plan.getDenialReason());
		}else {
			row.createCell(5).setCellValue("N/A");
			
		}
		if(null!=plan.getTerminatedReason()) {
			
			row.createCell(6).setCellValue(plan.getTerminatedReason());
		}else {
		row.createCell(6).setCellValue("N/A");}
		index++;
	}
	FileOutputStream fos=new FileOutputStream(new File("users.xls"));
	workbook.write(fos);
	
	ServletOutputStream outputStream = response.getOutputStream(); 
	workbook.write(outputStream);
	workbook.close();
	
}
}
