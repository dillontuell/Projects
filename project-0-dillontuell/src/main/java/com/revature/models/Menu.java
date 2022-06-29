package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.MemberDAO;
import com.revature.daos.RankDAO;
import com.revature.daos.UserDAO;

public class Menu {
	
	MemberDAO mDAO = new MemberDAO();	
	RankDAO rDAO = new RankDAO();
	UserDAO uDAO = new UserDAO();
	
		public void displayMenu() {
		
		boolean displayLogin = true;
		boolean displayMenu = true; 
		Scanner scan = new Scanner(System.in); 
		
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		System.out.println("Greetings! Welcome to the Jedi Academy Archives");
		System.out.println("*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*");
		
		System.out.println("Hello Member of the Jedi Order... please log in to view the rest of the application.");
		
		while(displayLogin) {
			
			System.out.println("USERNAME:");
			String username = scan.nextLine();
			
			System.out.println("PASSWORD:");
			String password = scan.nextLine();
			
			if(uDAO.login(username, password)) {
				System.out.println("Login Successful! Welcome.");
				displayLogin = false;
				break;
			}
			
			System.out.println("LOGIN FAILED! TRY AGAIN.");
			
		}
		
		while(displayMenu) {
			
			
			System.out.println("----------------");
			System.out.println("CHOOSE AN OPTION");
			System.out.println("----------------");
			
			
			System.out.println("1: get greeted");
			System.out.println("2: exit the application");
			System.out.println("3: show all members");
			System.out.println("4: show all ranks");
			System.out.println("5: get rank by ID");
			System.out.println("6: update rank tenure");
			System.out.println("7: add member");
			System.out.println("8: remove member");
			
			
			
			
			int input = scan.nextInt();
			scan.nextLine(); 
			
			switch(input) {
			
			case 1: {
				System.out.println("Hello User!");
				break;
			}
			
			case 2: {
				System.out.println("Bye user! Come again soon.");
				displayMenu = false; 
				break;
			}
			
			case 3: {
				ArrayList<Member> members = mDAO.getMembers();
				
				members.forEach(member -> {
					System.out.println("----------------------");
					System.out.println(member.getMember_id());
					System.out.println(member.getFirst_name());
					System.out.println(member.getLast_name());
					System.out.println(member.getRank());
					System.out.println("----------------------");
				});
				
				break;
			}
			
			case 4: {
				
				ArrayList <Rank> ranks = rDAO.getRank();
				for(Rank rank : ranks) {
					System.out.println(rank);
				}
				
				break;
			}
			
			case 5: {
				
				System.out.println("What Rank ID would you like to search?");
				
				int idInput = scan.nextInt();
				scan.nextLine(); 
				Rank rank = rDAO.getRankById(idInput);
				System.out.println(rank);
				
				break;
			}
			
			case 6: {
				
				System.out.println("Which rank would you like to change?");
				
				String titleInput = scan.nextLine();
				
				System.out.println("What is the new tenure?");
				
				int tenureInput = scan.nextInt();
				scan.nextLine();
				rDAO.updateRankTenure(titleInput, tenureInput);
				
				break;
			}
			
			case 7: {
				
				
				System.out.println("Enter Member First Name");
				String fName = scan.nextLine();
				
				System.out.println("Enter Member Last Name");
				String lName = scan.nextLine();
				
				System.out.println("Enter Member Rank");
				System.out.println("Grandmaster = 1 | Battle Master = 2 | Jedi Master = 3 | Jedi Knight = 4 | Jedi Padawan = 5");
				
				int rankId = scan.nextInt();
			
				Member mem = new Member(fName, lName, null);
				mDAO.insertMember(mem, rankId);
				
				break;
			}
			
			case 8: {
				
				System.out.println("Lost to the darkside. Enter the ID of the member you want to delete");
				
				int idInput = scan.nextInt();
				
				mDAO.removeMember(idInput);
				
				break;
			}
			
			default: {
				System.out.println("What did you say?? Try again. Do better...");
				break;
			}
			
			} 
			
		} 
		
	} 
	
} 