package goott;

import java.sql.*;
import java.util.Scanner;


public class Insert {

	public static void main(String[] args) {
		
		// 오라클 데이터베이스와 연결하는 객체
				Connection con = null;
				
				// SQL문을 데이터베이스에 전송하는 객체.
				PreparedStatement pstmt = null;
				
				// SQL문을 실행한 후에 결과값을 가지고 있는 객체.
				ResultSet rs = null;
				
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				
				String user = "goott";
				
				String password = "99229922";
				
				Scanner sc = new Scanner(System.in);
				
				System.out.print("회원 아이디 입력 : ");
				String id = sc.nextLine();
				
				System.out.print("회원 이름 입력 : ");
				String name = sc.nextLine();
				
				System.out.print("회원 비밀번호 입력 : ");
				String pwd = sc.nextLine();
				
				System.out.print("회원 나이 입력 : ");
				String age = sc.nextLine();
				
				System.out.print("회원 마일리지 입력 : ");
				String mileage = sc.nextLine();
				
				System.out.print("회원 직업 입력 : ");
				String job = sc.nextLine();
				
				System.out.print("회원 주소 입력 : ");
				String addr = sc.nextLine();
				
				try {
				// 1단계 : 오라클 드라이버 동적으로 메모리 로딩.
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
			    // 2단계 : 오라클 데이터베이스와 연결 시도(진행).
					con = DriverManager.getConnection(url, user, password);
					
				// 3단계 : 데이터베이스에 전송할 SQL문 작성.
				    String sql = "select max(memno) from member";
				    
				    pstmt = con.prepareStatement(sql);
				    
				// 4단계 : 데이터베이스에 SQL문 전송 및 실행.
				    rs =  pstmt.executeQuery();
				    
				    int count = 0;
				    
				    if(rs.next()) {
				    	count = rs.getInt(1);
				    }
				    
				    // 3단계 : 데이터베이스에 전송할 SQL문 작성.
				    sql = "insert into member values(?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
				    
				    pstmt = con.prepareStatement(sql);
				    
				    pstmt.setInt(1, count + 1);
				    pstmt.setString(2, id);
				    pstmt.setString(3, name);
				    pstmt.setString(4, pwd);
				    pstmt.setInt(5, Integer.parseInt(age));
				    pstmt.setInt(6, Integer.parseInt(mileage));
				    pstmt.setString(7, job);
				    pstmt.setString(8, addr);
				    
				    // 4단계 : 데이터베이스에 SQL문을 전송 및 실행.
				    /*
				     *  - SQL문이 select 쿼리문인 경우
				     *    ==> executeQuery() 메서드를 이용해야 함.
				     *    ==> 반환형은 ResultSet 타입으로 반환.
				     *    
				     *  - SQL문이 insert, update, delete 쿼리문인 경우
				     *    ==> executeUpdate() 메서드를 이용해야 함.
				     *    ==> 반환형은 int 타입으로 반환.
				     */
				    
				    int result = pstmt.executeUpdate();
				    
				    if(result > 0) {
				    	System.out.println("회원 등록 성공!!!");
				    }else {
				    	System.out.println("회원 등록 실패;;;");
				    }		
				    // 6단계 : 연결되어 있던 자원 종료시켜 주자.
				    rs.close(); pstmt.close(); con.close(); sc.close();
			 } catch (Exception e) {				
					e.printStackTrace();
				}			
	}
}
