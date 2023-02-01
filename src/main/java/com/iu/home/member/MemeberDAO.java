package com.iu.home.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.home.util.DBConnection;

public class MemeberDAO {
	public MemberDTO setAddMenber() throws Exception { 
		Connection connection  = DBConnection.getConnnection();
		MemberDTO memberDTO = new MemberDTO();
		
		String sql = "INSERT INTO MEMBER (ID, PW, NAME, ADDRESS, PHONE, EMAIL "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement st = connection.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2, memberDTO.getPw());
		st.setString(3, memberDTO.getName());
		st.setString(4, memberDTO.getAddress());
		st.setString(5, memberDTO.getPhone());
		st.setString(6, memberDTO.getEmail());
		
		ResultSet rs = st.executeQuery();
		DBConnection.disConnect(rs, st, connection);
		return memberDTO;
	}
		
	public static void main(String[] args) {
		MemeberDAO memberDAO = new MemeberDAO();
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setId("id1");
		memberDTO.setPw("pw1");
		memberDTO.setName("name1");
		memberDTO.setAddress("address1");
		memberDTO.setPhone("phone1");
		memberDTO.setEmail("Email1");
		
		int result = memberDAO.setAddMenber(memberDTO);
		System.out.println(result ==1);
	}
	
}