package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Rank;
import com.revature.utils.ConnectionUtil;
 
public class RankDAO implements RankDAOInterface{

	
	
	public ArrayList<Rank> getRank(){
		
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			
			String sql = "select * from ranks;";
			
			
			Statement s = conn.createStatement(); 
			
			
			ResultSet rs = s.executeQuery(sql);
			
			
			
		ArrayList<Rank> rankList = new ArrayList<>();
			
		
			while(rs.next()) {
				
				
				Rank r = new Rank(
											
						rs.getInt("rank_id"),
						rs.getString("rank_title"),
						rs.getInt("rank_tenure")
						);
				
				rankList.add(r);
			}
			
			
			return rankList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong contacting the database!");
			e.printStackTrace(); 
		}
		
		return null; 
	}

	
	
	@Override
	public Rank getRankById(int id) {
		
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
		
			String sql = "select * from ranks where rank_id = ?";
			
			
			PreparedStatement ps = conn.prepareStatement(sql); 
			
			
			ps.setInt(1, id); 
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				
			return new Rank(
					rs.getInt("rank_id"),
					rs.getString("rank_title"),
					rs.getInt("rank_tenure")
					);
				
			}	
			
			
		} catch (SQLException e) {
			System.out.println("Something went wrong fetching this data!!");
			e.printStackTrace(); 
		}
		
		return null;
	}

	@Override
	public void updateRankTenure(String title, int tenure) {
	
		try(Connection conn = ConnectionUtil.getConnection()){
			
			
			String sql = "update ranks set rank_tenure = ? where rank_title = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setInt(1, tenure); 
			ps.setString(2, title); 
			
			//execute the update!!
			ps.executeUpdate();
			
			//tell the user that the update was successful
			System.out.println(title + " tenure has been updated to: " + tenure);
			
		} catch (SQLException e) {
			System.out.println("Couldn't update :(");
			e.printStackTrace();
		}
		
	}





	
	//public ArrayList<Rank> getRank() {
		// TODO Auto-generated method stub
		//return null;
	



	@Override
	public ArrayList<Rank> getRanks() {
		// TODO Auto-generated method stub
		return null;
	}

}


	
	
	

