package com.reimbursementapp.controller;

import javax.servlet.http.HttpServletRequest;
import com.reimbursementapp.utilities.AppLogger;
import javax.servlet.http.HttpServletResponse;

public class HomeController {

		
	public static String home(HttpServletRequest request, HttpServletResponse response) {
		
//		String cssPath = request.getContextPath();
//		System.out.println(cssPath);
		
		System.out.println();
		return "/resources/html/home.html";
	}
}
