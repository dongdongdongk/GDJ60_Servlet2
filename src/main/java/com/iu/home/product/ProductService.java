package com.iu.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.home.util.DBConnection;

public class ProductService {
	private ProductDAO productDAO;

	
	
	public ProductService() {
		this.productDAO = new ProductDAO();
	}
	public List<ProductDTO> getProductList() throws Exception {
	
		return productDAO.getProductList();
	}

	public int setAddProduct(ProductDTO productDTO, List<ProductOptionDTO> ar) throws Exception {
//product, option
		Long productNUM = productDAO.getProductNum();
		productDTO.setProductNum(productNUM);
		int result = productDAO.setaddProduct(productDTO);
		for (ProductOptionDTO productOptionDTO : ar) {
			productOptionDTO.setProductNum(productNUM);
			result = productDAO.setaddProductOption(productOptionDTO);
		}
		return result;
	}

	public ProductDTO getproductDetail(ProductDTO productDTO) throws Exception {
		Connection connection =DBConnection.getConnnection();
		String sql = "SELECT * FROM PRODUCT WHERE PRODUCTNUM=?";
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setLong(1, 0);
		
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductDetail(rs.getString("PRODUCTDETAIL"));
			productDTO.setProductJumsu(rs.getDouble("PRODUCTJUMSU"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
		}else {
			productDTO = null;
		}
		DBConnection.disConnect(st, connection);
		return productDTO;
	
		
	}
//	public static void main(String[] args) {
//// product의 productnum 모를 때, product테이블과 productoption테이블에
//// insert 하는 과정
//// ProductDAO의 getProductNum() 메소드 사용
//		ProductDAO productDAO = new ProductDAO();
//
//		ProductDTO productDTO = new ProductDTO();
//		productDTO.setProductName("product1");
//		productDTO.setProductDetail("detail1");
//
//		ProductOptionDTO productOptionDTO = new ProductOptionDTO();
//		productOptionDTO.setOptionName("optionName1");
//		productOptionDTO.setOptionPrice(100L);
//		productOptionDTO.setOptionstock(10L);
//		productOptionDTO.setProductNum(null);
//
//		ProductOptionDTO productOptionDTO2 = new ProductOptionDTO();
//		productOptionDTO2.setOptionName("optionName2");
//		productOptionDTO2.setOptionPrice(200L);
//		productOptionDTO2.setOptionstock(20L);
//		productOptionDTO2.setProductNum(null);
//
//		try {
//			Long num = productDAO.getProductNum();
//			productDTO.setProductNum(num);
//			int result = productDAO.setaddProduct(productDTO);
//			productOptionDTO.setProductNum(num);
//			if (result > 0) {
//				result = productDAO.setaddProductOption(productOptionDTO2);
//			}
//		} catch (Exception e) {
//// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//}

}