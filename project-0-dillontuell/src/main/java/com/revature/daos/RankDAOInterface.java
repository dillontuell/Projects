package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Rank;

public interface RankDAOInterface {

	
	ArrayList<Rank> getRanks();
	
	Rank getRankById(int id);
	
	void updateRankTenure(String title, int tenure);
 
	}