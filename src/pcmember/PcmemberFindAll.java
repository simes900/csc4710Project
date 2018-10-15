package pcmember;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class PcmemberFindAll {

	public List<Object> findall() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection connect = null;
		ResultSet resultSet = null;
		List<Object> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connect = DriverManager
			          .getConnection("jdbc:mysql://localhost:3306/sampledb?"
				              + "user=john&password=pass1234");
			
			
			String sql = "select * from pcmember";
			PreparedStatement preparestatement = connect.prepareStatement(sql); 
			resultSet = preparestatement.executeQuery();
			
			while(resultSet.next()){
				Pcmember pcmember = new Pcmember();
				pcmember.setEmail(resultSet.getString("email"));
				pcmember.setName(resultSet.getString("name"));
	    		list.add(pcmember);
			 }
			 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}finally{
			if(resultSet !=null){
				try{
					connect.close();
					System.out.println("Closed AuthorfindAll servlet connection");
				}catch(SQLException e){
					//ignore
				}
			}
			if(connect != null){
				try{
					connect.close();
					System.out.println("Closed pcmemberfindall servlet connection");
				}catch(SQLException e){
					//ignore
				}
			}
		}
		return list;
		}
	
}
