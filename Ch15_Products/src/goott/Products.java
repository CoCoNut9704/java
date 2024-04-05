package goott;

import java.sql.*;

public class Products {

	public static void main(String[] args) {
		
		Connection con = null;
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
				
		String user = "goott";
		
		String password = "99229922";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("드라이버 로딩 성공");
			con = DriverManager.getConnection(url, user, password);
			
			if(con != null) {
				System.out.println("오라클 데이터베이스와 연결 성공");
			}
			
			String sql = "select * from products";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int num = rs.getInt("pnum");
				int fk = rs.getInt("category_fk");
				String name = rs.getString("products_name");
				int code = rs.getInt("ep_code_fk");
				int input = rs.getInt("input_price");
				int output = rs.getInt("output_price");
				int cost = rs.getInt("trans_cost");
				int mile = rs.getInt("mileage");
				String com = rs.getString("company");
				
				System.out.println(num + "\t" + fk + "\t" + name + "\t" 
				+ code + "\t" + input + "\t" + output + "\t" + cost + "\t" + mile + "\t" + com);
				
				System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				
			}
			rs.close(); con.close(); pstmt.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		

	}

}
