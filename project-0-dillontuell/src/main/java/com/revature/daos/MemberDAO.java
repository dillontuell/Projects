package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Member;
import com.revature.models.Rank;
import com.revature.utils.ConnectionUtil;

//DAO stands for Data Access Object - it's the layer of classes that directly interact with our database
//EVENTUALLY (not yet) we'll have a bunch of methods here that send SQL statements to our database
//In order to insert/manipulate/delete/view the data in the database

//PLEASE NOTE OUR ACTUAL DAO WILL LOOK VERY DIFFERENT FROM THIS
public class MemberDAO implements MemberDAOInterface{

	//Instantiate a RoleDAO object so that we can use one of it's methods in the getEmployees() method
	RankDAO rDAO = new RankDAO();
	
	//This TEMPORARY method will return the employees from the database
	//In the future, this method body will actually be communicating directly to the database
	public ArrayList<Member> getMembers(){
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//a String that will represent our SQL statement
			String sql = "select * from members;";
			
			//a Statement object to execute our query 
			Statement s = conn.createStatement();
			
			//execute our query into a ResultSet object, which will hold all the data 
			//executeQuery() is what actually queries the database! Then we put the records into a ResultSet
			ResultSet rs = s.executeQuery(sql);
			
			//Instantiate an ArrayList to put our Employee objects into
			ArrayList<Member> memberList = new ArrayList<>();
			
			//use rs.next() in a while loop to create Employee objects and populate our ArrayList with them.
			//remember, the ResultSet is what's holding our data. We need to turn it into something Java can read (objects)
			while(rs.next()) {
				//Create a new Employee object from each record in the ResultSet
				//we're using the all args constructor of Employee to fill in new Employee objects with DB data
				Member m = new Member(
						rs.getInt("member_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						null //there is no JDBC method for getRole... we'll add the Role object in below
						//this is an extra step we have to take because in the DB, the role_id_fk is an int
						//but we need a Role object here
						);
				
				//we need to get the role of each employee somehow...
				//we need to use the DAO method for getRoleById from the RoleDAO
				int rankFK = rs.getInt("rank_id_fk");
				
				//get a Role object from the RoleDAO
				Rank r = rDAO.getRankById(rankFK);
				
				//use the SETTER of the Employee class to set the Role object to the one we got from the DB above.
				m.setRank(r);
				//thanks to this setter, our Employee objects can be FULLY initialized (every variable has a value)
				
				//add the fully initialized Employee into the ArrayList
				memberList.add(m);
			}
			
			//once the while loop ends (when rs.next() == false), return the ArrayList
			return memberList;
			
		} catch (SQLException m) {
			System.out.println("Something went wrong selecting all members!");
			m.printStackTrace();
		}
		
		return null;
		
	}


	//We want a method that can take in a Role title, and return all Employees with that Role
	@Override
	public ArrayList<Member> getMembersByRole(String title) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//We need a SQL String with a JOIN
			//We need to join employees on roles in order to access the role_title column from the roles table
			//since I want to get employees by their role title, I need access to the data in both tables
			String sql = "select * from members inner join ranks "
					+ "on rank_id = rank_id_fk where rank_title = ?;";
			
			//we have a variable in the SQL statement, so we need a PreparedStatement to fill it in
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//now we just need to input the variable value
			ps.setString(1, title);
			
			//Execute the query into a ResultSet object
			ResultSet rs = ps.executeQuery();
			
			//Instantiate an empty ArrayList that we'll fill with the data from the ResultSet
			ArrayList<Member> memberList = new ArrayList<>();
			
			//while there are records remaining in the ResultSet...
			while(rs.next()) {
				
				//create new Employee objects based on the data, and fill in the ArrayList
				Member m = new Member(
						rs.getInt("member_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						null
						);
				
				//get the foreign key from the Employees table to use in our getRoleById() method
				int rankFK = rs.getInt("rank_id_fk");
				
				Rank r = rDAO.getRankById(rankFK);
				
				//fill in the previously null Role variable in this new Employee object (with the setter!)
				m.setRank(r);
				
				//fill in the employeeList with each while loop until eventually rs.next() == false.
				memberList.add(m);
			}
			
			return memberList;
			
		} catch (SQLException m) {
			System.out.println("Something went wrong selecting members by ID");
			m.printStackTrace();
		}
		
		return null;
	}


	@Override
	public void insertMember(Member member, int rank_id) {
	
		try(Connection conn = ConnectionUtil.getConnection()){
		
		//First we need our SQL String that represents the INSERT statement we'll send to the DB
		//Again, there are variables in this statement, that we can fill out thanks to PreparedStatement
		String sql = "insert into members (first_name, last_name, rank_id_fk)"
				+ "values (?, ?, ?);";
				
		//Instantiate a PreparedStatement to fill in the variables of our initial SQL String
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//fill in the values of our variables using ps.setXYZ()
		ps.setString(1, member.getFirst_name());
		ps.setString(2, member.getLast_name());
		ps.setInt(3, rank_id); 
		//note how the DB role_id is an int, but in Java, Employees have a Role OBJECT
		//this is my workaround of choice... have the user input the id of the desired role when inserting the user data
		
		//Execute the Update!! (the method is called executeUpdate(), but it's for INSERTS, UPDATES, and DELETES)
		ps.executeUpdate();
		
		//Tell the user the insert was successful
		System.out.println("Member " + member.getFirst_name() + " added. Welcome to Order of the Force!");
			
		} catch (SQLException e) {
			System.out.println("Something went wrong inserting Member!");
			e.printStackTrace();
		}
		
	} 


	@Override
	public void removeMember(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//SQL String that we want to send to the DB
			String sql = "delete from members where member_id = ?;";
			
			//instantiate our PreparedStatement to fill in the variable
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			//ps.executeUpdate() to send our delete to the DB
			ps.executeUpdate();
			
			//let the user know that the dreams of their former employee have been crushed
			System.out.println("Get outta here, member #" + id);
			
		} catch (SQLException e) {
			System.out.println("The dark side is their master now");
			e.printStackTrace();
		}
		
	}


	
	public Member getMemberById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Member getMembersById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}


	