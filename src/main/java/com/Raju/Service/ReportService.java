package com.Raju.Service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.aop.ThrowsAdvice;

import com.Raju.entity.CitizenPlan;
import com.Raju.search.SearchReq;
import com.lowagie.text.DocumentException;

public interface ReportService {

	public List<String> getplan_names();
	public List<String> getplan_status();
	public List<CitizenPlan> search(SearchReq req);
	public void exportExcel(HttpServletResponse response)throws Exception;
	public void exportPdf( HttpServletResponse response)throws Exception;
	
	
	
	
	
}
