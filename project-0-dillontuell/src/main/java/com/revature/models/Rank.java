package com.revature.models;

public class Rank {

	private int rank_id;
	private String rank_title;
	private int rank_tenure;

	public Rank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rank(int rank_id, String rank_title, int rank_tenure) {
		super();
		this.rank_id = rank_id;
		this.rank_title = rank_title;
		this.rank_tenure = rank_tenure;
	}
	
	public Rank(String rank_title, int rank_tenure) {
		super();
		this.rank_title = rank_title;
		this.rank_tenure = rank_tenure;
	}
	
	@Override
	public String toString() {
		return "Rank [rank_id=" + rank_id + ", rank_title=" + rank_title + ", rank_tenure=" + rank_tenure + "]";
	}

	public int getRank_id() {
		return rank_id;
	}


	public void setRank_id(int rank_id) {
		this.rank_id = rank_id;
	}


	public String getRank_title() {
		return rank_title;
	}


	public void setRank_title(String rank_title) {
		this.rank_title = rank_title;
	}


	public int getRank_tenure() {
		return rank_tenure;
	}


	public void setRank_tenure(int rank_tenure) {
		this.rank_tenure = rank_tenure;
	}
	
	
}