package com.iu.home.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.home.util.DBConnection;

public class ProductOptionDAO {
	
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
}
