package com.Raju.search;

import java.time.LocalDate;

import lombok.Data;



@Data
public class SearchReq {
	private String plan_Name;
	 private String plan_Status;
	 private String gender;
	 private String start_Date;
	 private String end_Date;
}
