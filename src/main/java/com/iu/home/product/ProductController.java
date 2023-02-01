package com.iu.home.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductController
 */
@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private ProductDAO productDAO;
	private ProductService productService;
	private ProductDTO productDTO;
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
        productDAO = new ProductDAO();
        productService = new ProductService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("productpaget");
	
		System.out.println(request.getRequestURL());
		System.out.println(request.getRequestURI());
		
		String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1);
		
		if(uri.equals("list.do")) {
			System.out.println("리스트 조회");
			try {
				List<ProductDTO> ar = productService.getProductList();
				request.setAttribute("list",ar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("errrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
				e.printStackTrace();
			}
			uri = "productList.jsp";
		}
		else if(uri.equals("add.do")) {
			System.out.println("상품등록");
			uri = "productAdd.jsp";
		}
		else if(uri.equals("Update.do")) {
			System.out.println("상품수정");
			uri = "productUpdate.jsp";
		}
		else if(uri.equals("Delete.do")) {
			System.out.println("상품삭제");
		}
		else {
			System.out.println("상품하나조회");
			try {
				Long num = Long.parseLong(request.getParameter("productNum"));
				productDTO = new ProductDTO();
				productDTO.setProductNum(num);
				productService.getproductDetail(productDTO);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/product/"+uri);
		view.forward(request, response);
		
//		String name = request.getParameter("name");
//		System.out.println(name);
//		
//		String detail = request.getParameter("detail");
//		System.out.println(detail);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
