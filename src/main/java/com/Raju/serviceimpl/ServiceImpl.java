package com.Raju.serviceimpl;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Raju.Service.ReportService;
import com.Raju.entity.CitizenPlan;
import com.Raju.repo.citizenRepo;
import com.Raju.search.SearchReq;
import com.Raju.utils.EmailUtils;
import com.Raju.utils.ExcelGenerator;
import com.Raju.utils.PDFGenerator;
@Service
public class ServiceImpl implements ReportService{	
	
	@Autowired
	private ExcelGenerator excel;
	
	@Autowired
	private PDFGenerator pdf;
	
	@Autowired
	private EmailUtils utils;
	
	
	@Autowired
	private citizenRepo repo;
	
	@Override
	public List<String> getplan_status() {
		
		return repo.getPlanNames();
	}
	@Override
	public List<String> getplan_names() {
		
		return repo.getPlanStatus();
	}
	public void exportExcel(HttpServletResponse response) throws Exception{
		
		List<CitizenPlan> plans = repo.findAll();
		excel.generateExcel(response,plans);
		String subject="Test Subject";
		String body="<h1>Test Body</h2>";
		String to="rajeshaju7687@gmail.com";
		File f=new File("users.xls");
		utils.sendmail(subject,body,to,f);
	}
@Override
public void exportPdf(HttpServletResponse response) throws Exception {
	
	List<CitizenPlan> plans = repo.findAll();
	pdf.generatepdf(response, plans);
	String subject="Test Subject";
	String body="Test Body";
	String to="rajeshaju7687@gmail.com";
	File f=new File("users.pdf");
	utils.sendmail(subject,body,to,f);
	
}
	
	@Override
	public List<CitizenPlan> search(SearchReq req) {
	
		CitizenPlan plan=new CitizenPlan();
		if(null!=req.getPlan_Name() && !"".equals(req.getPlan_Name())) {
			plan.setPlanName(req.getPlan_Name());
		}
		if(null!=req.getPlan_Status() && !"".equals(req.getPlan_Status())) {
			plan.setPlanStatus(req.getPlan_Status());
		}
		if(null!=req.getGender() && !"".equals(req.getGender())) {
			plan.setGender(req.getGender());
		}
		if(null!=req.getStart_Date() && !"".equals(req.getStart_Date())) {
		String start_Date = req.getStart_Date();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"+" ");
		LocalDate date = LocalDate.parse(start_Date, formatter);
		plan.setPlanStartDate(date);
		}
		if(null!=req.getEnd_Date() && !"".equals(req.getEnd_Date())) {
			String start_Date = req.getEnd_Date();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"+" ");
			LocalDate date = LocalDate.parse(start_Date, formatter);
			plan.setPlanStartDate(date);
		}
		return repo.findAll(Example.of(plan));
	}
}
