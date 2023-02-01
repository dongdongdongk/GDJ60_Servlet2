package com.iu.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.events.Comment;

import com.iu.home.util.DBConnection;

public class ProductDAO {
//getMax
	public Long getProductNum() throws Exception {
		Connection connection = DBConnection.getConnnection();
		String sql = "SELECT PRODUCT_SEQ.NEXTVAL FROM DUAL";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
//데이터가 없어도 MAX가 0이어서 값이 무조건 나옴
		rs.next();
		Long num = rs.getLong(1);
//=rs.getLong(1);
		DBConnection.disConnect(rs, st, connection);
		return num;
	}

//---------------------------------------------------------------------------
//productoption
	public List<ProductOptionDTO> getProductOptionList() throws Exception {
		Connection connection = DBConnection.getConnnection();
		ArrayList<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
		String sql = "SELECT * FROM PRODUCTOPTION";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setOptionNum(rs.getLong("OPTIONNUM"));
			productOptionDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productOptionDTO.setOptionName(rs.getString("OPTIONNAME"));
			productOptionDTO.setOptionPrice(rs.getLong("OPTIONPRICE"));
			productOptionDTO.setOptionstock(rs.getLong("OPTIONSTOCK"));
			ar.add(productOptionDTO);
		}
		DBConnection.disConnect(rs, st, connection);
		return ar;
	}

	public int setaddProductOption(ProductOptionDTO productOptionDTO) throws Exception {
		Connection connection = DBConnection.getConnnection();
		String sql = "INSERT INTO PRODUCTOPTION(OPTIONNUM, PRODUCTNUM, OPTIONNAME, OPTIONPRICE, OPTIONSTOCK)"
				+ " VALUES (PTOPTION_SEQ.NEXTVAL,?,?,?,?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setLong(1, productOptionDTO.getProductNum());
		st.setString(2, productOptionDTO.getOptionName());
		st.setLong(3, productOptionDTO.getOptionPrice());
		st.setLong(4, productOptionDTO.getOptionstock());
		int result = st.executeUpdate();
		DBConnection.disConnect(st, connection);
		return result;
	}

//-------------------------------------------------------------------------------------
	//getProductDetail
	
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
	
	
	
	
	
	
	
	
//product
	public List<ProductDTO> getProductList() throws Exception {
//LinkedList를 만들어도 List, ArrayList를 만들어도 List
//즉 메소드 리턴타입을 부모형으로 만들어 수정하기 편하게 만들어준다.
		ArrayList<ProductDTO> ar = new ArrayList<ProductDTO>();

		Connection connection = DBConnection.getConnnection();
		String sql = "SELECT PRODUCTNUM, PRODUCTNAME, PRODUCTJUMSU" + " FROM PRODUCT ORDER BY PRODUCTJUMSU DESC";

		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productDTO.setProductName(rs.getString("PRODUCTNAME"));
			productDTO.setProductJumsu(rs.getDouble("PRODUCTJUMSU"));
			ar.add(productDTO);
		}
		DBConnection.disConnect(rs, st, connection);

		return ar;
	}

	public int setaddProduct(ProductDTO productDTO) throws Exception {
		Connection connection = DBConnection.getConnnection();
		String sql = "INSERT INTO PRODUCT (PRODUCTNUM, PRODUCTNAME, PRODUCTDETAIL, PRODUCTJUMSU)"
				+ " VALUES (?,?,?,0.0)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setLong(1, productDTO.getProductNum());
		st.setString(2, productDTO.getProductName());
		st.setString(3, productDTO.getProductDetail());
		int result = st.executeUpdate();
		DBConnection.disConnect(st, connection);
		return result;
	}

}