package com.Raju.contrl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Raju.Service.ReportService;
import com.Raju.entity.CitizenPlan;
import com.Raju.search.SearchReq;
//57 min completed
@Controller
public class ReportController {
@Autowired
private ReportService service;	

@GetMapping("/pdf")
public void exportPdf(HttpServletResponse response)throws Exception {
	
	response.setContentType("application/pdf");
	response.addHeader("Content-Disposition","attachment; filename=users.pdf");
	service.exportPdf(response);
}
@GetMapping("/excel")
public void excel(HttpServletResponse response)throws Exception {
	response.setContentType("application/octect-stream");
	response.addHeader("Content-Disposition","attachment; filename=users.xls");
	service.exportExcel(response);
}
@PostMapping("/search")
public String handleSearch(@ModelAttribute("search")SearchReq req,Model model) {
	System.out.println(req);
	List<CitizenPlan> plans=service.search(req);
	model.addAttribute("plans", plans);
	init(model);
	return "index";
}
@GetMapping("/")
	
	public String indexpage(Model model){
	
	model.addAttribute("search", new SearchReq());
	init(model);
		return "index";
	}
private void init(Model model) {
	model.addAttribute("names",service.getplan_names());
	model.addAttribute("status",service.getplan_status());
}

}
