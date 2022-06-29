package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Member;

//Recall that interfaces contain abstract methods. They are meant to be implemented by concrete classes
//We want employeeDAO to implement this interface, for the sake of organizing what we want our EmployeeDAO to do
public interface MemberDAOInterface {

	//I'm going to lay out a bunch of abstract methods that I want to force the EmplyoeeDAO to implement
	//This is the way I lay out the methods that WILL be in my DAO classes
	
	//DAO classes get VERY long. instead of scrolling through them, we can check this interface
	//for a quick reference of everything the EmployeeDAO can do
	
	//(Ben showed ctrl + shift + o to do multi-imports)
	
	//Interface methods are public, abstract by default
	
	//This method will return every employee from the database
	ArrayList<Member> getMembers();
	
	//This method will return an employee which we'll get by their id
	Member getMembersById(int id);
	
	//This method will return every employee of a given role (we'll use a string here, to search by role title)
	ArrayList<Member> getMembersByRole(String title);
	
	//This method will take in an employee object, and insert it into the database
	void insertMember(Member member, int rank_id);
	
	//This method will take in an int, and remove the employee whose id matches the int
	void removeMember(int id);
	
	
	
	//I'll show update in the RoleDAO
	
}