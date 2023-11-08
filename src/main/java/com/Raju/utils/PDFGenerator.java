package com.Raju.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Raju.entity.CitizenPlan;
import com.Raju.repo.citizenRepo;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
@Component
public class PDFGenerator {
	@Autowired
	citizenRepo repo;
		
	public void generatepdf(HttpServletResponse response,List<CitizenPlan> plans) throws Exception{
			
			Document document = new Document(PageSize.A4);
			PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
			
			document.open();
			// Creating font
			// Setting font style and size
			Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
			fontTiltle.setSize(20);

			// Creating paragraph
			Paragraph paragraph = new Paragraph("Citizen Plan", fontTiltle);

			// Aligning the paragraph in document
			paragraph.setAlignment(Paragraph.ALIGN_CENTER);

			// Adding the created paragraph in document
			document.add(paragraph);

			PdfPTable table = new PdfPTable(7);
			table.setSpacingBefore(6);

			table.addCell("Sno");
			table.addCell("Citizen Name");
			table.addCell("Gender");
			table.addCell("Plan Name");
			table.addCell("Plan Status");
			table.addCell("Benift Amount");
			table.addCell("Denial Reason");
			table.addCell("Terminated Reason");
			

			for (CitizenPlan plan : plans) {
				table.addCell(String.valueOf(plan.getCitizenId()));
				table.addCell(plan.getCitizenName());
				table.addCell(plan.getPlanName());
				table.addCell(plan.getPlanStatus());

				if (null != plan.getBenefitAmount()) {
					table.addCell(plan.getBenefitAmount() + "");
				} else {
					table.addCell("N/A");
				}
				if(null!=plan.getDenialReason()) {
					table.addCell(plan.getDenialReason());
					
				}else {
					table.addCell("N/A");
				}
				if(null!=plan.getDenialReason()) {
					table.addCell(plan.getDenialReason());
				}else {
					table.addCell("N/A");
				}
				if(null!=plan.getTerminatedReason()) {
					table.addCell(plan.getTerminatedReason());
				}else {
					table.addCell("N/A");
				}
			}
			document.add(table);
			document.close();
			FileOutputStream fos=new FileOutputStream(new File("users.pdf"));
		
	}


}
