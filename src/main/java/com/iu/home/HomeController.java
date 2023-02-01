package com.iu.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.home.product.ProductService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("kdhkdh");
//		PrintWriter out = response.getWriter(); //클라이언트와 연결된 아웃풋 스트링 가져오는것
//		out.println("<h1>dong page</h1>"); 
//		out.println("<h2>header2</h2>");
//		
//		out.close();
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/home.jsp"); //절대경로만 가능
		view.forward(request, response);
		
//		productService.setAddProduct(null, new ArrayList<ProductOption>)
//		String method = request.getMethod();
//		System.out.println("Method : " + method);
		
//	StringBuffer sb = request.getRequestURL();
//	System.out.println("URL :" + sb);
//	
//	String uri =request.getRequestURI();
//	System.out.println("uri :" + uri);
//	
//	
//	Cookie[] cookies= request.getCookies();
//	for(Cookie cookie : cookies) {
//		System.out.println("name" +cookie.getName());
//		System.out.println("value" + cookie.getValue());
//	}
//	String cp = request.getContextPath();
//	System.out.println("conpath :"+ cp );
//	
//	String name = request.getParameter("name");
//	System.out.println("Pname" + name);
//	
//	String age = request.getParameter("age");
//	if(age!=null) {
	//	int a = Integer.parseInt(age);
	//	System.out.println("age" + a);
		
//	}
//	String[] moneys = request.getParameterValues("money");
//	
//	for(String m : moneys) {
//		System.out.println(m);
//	}
//	
//	
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
