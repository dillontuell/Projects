package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import com.revature.daos.MemberDAO;
import com.revature.daos.RankDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Member;

public class TestClass {

	UserDAO ud = new UserDAO();
	MemberDAO ed = new MemberDAO();
	RankDAO rd = new RankDAO();
	
	@Test
	public void testSuccessfulLogin() {
		
		assertTrue(ud.login("user", "password"));
		
	}
	
	@Test 
	public void testFailedLogin() {
		
		assertFalse(ud.login("Success", "Failure"));
	
	}
	
	
}