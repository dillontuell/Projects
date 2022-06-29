/*package com.revature.daos;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.utils.ConnectionUtil;


public class UserDAO {

    //This method will return true in the case of successful login, otherwise it will return false
    public boolean users(String username, String password) {
        String dbUsername = "";
        String dbPassword = "";

        String SQL = "SELECT * FROM public.login WHERE username = " + "'" + username + "'";



        try(
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL)
        ){
            if(resultSet.next()) {

                dbUsername = resultSet.getString(2);

                dbPassword = resultSet.getString(3);


            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(username.equals(dbUsername) && password.equals(dbPassword)) {
            return true;
        }

        return false;

    }

	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
    
}*/


package com.revature.daos;

public class UserDAO {
	
	
	public boolean login(String username, String password) {
		
		if(username.equals("Skyguy") && password.equals("RogueOne")) {
			return true;
		}
		
		return false;


}
	}



   