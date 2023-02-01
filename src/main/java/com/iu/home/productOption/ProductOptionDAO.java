package com.iu.home.productOption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.iu.home.util.DBConnection;

public class ProductOptionDAO {
	
	public ArrayList<ProductOptionDTO> getProductOptionList() throws Exception {
		Connection connection = DBConnection.getConnnection();
		ArrayList<ProductOptionDTO> ar = new ArrayList<ProductOptionDTO>();
		String sql = "SELECT PRODUCTOPTIONNUM, PRODUCTNUM, OPTIONNAME, OPTIONPRISE "
				+ "OPTIONJAGO FROM PRODUCTOPTION";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			ProductOptionDTO productOptionDTO = new ProductOptionDTO();
			productOptionDTO.setOptionNum(rs.getInt("PRDODUCTNUM"));
			productOptionDTO.setProductNum(rs.getLong("PRODUCTNUM"));
			productOptionDTO.setOptionName(rs.getString("OPTIONNAME"));
			productOptionDTO.setOptionPrise(rs.getInt("OPTIONPRISE"));
			productOptionDTO.setOptionjago(rs.getInt("OPTIONJAGO"));
			ar.add(productOptionDTO);
			
		}
		DBConnection.disConnect(rs, st, connection);
		return ar;
	}
	public int setProductOption(ProductOptionDTO productOptionDTO) throws Exception {
		Connection connection  = DBConnection.getConnnection();
		String sql = "INSERT INTO PRODUCTOPTION(PRDODUCTNUM, PRODUCTNUM "
				+ "OPTIONNAME, OPTIONPRISE, OPTIONJAGO VALUES(?, ?, ?, ?, ?";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setInt(1, productOptionDTO.getOptionNum());
		st.setLong(2, productOptionDTO.getProductNum());		
		st.setString(3, productOptionDTO.getOptionName());
		st.setInt(4, productOptionDTO.getOptionPrise());
		st.setInt(5, productOptionDTO.getOptionjago());
		
		int result = st.executeUpdate();
		DBConnection.disConnect(null, st, connection);
		return result;
		
	}
}
