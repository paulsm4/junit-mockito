package com.example.useradmin.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.*;

import com.example.useradmin.dto.User;
import com.example.useradmin.util.IDGenerator;

@RunWith(PowerMockRunner.class)
@PrepareForTest(IDGenerator.class)
public class UserDAOTest {

	@Test
	public void createShouldReturnAUserId () {
		UserDAO dao = new UserDAO();
		
		mockStatic(IDGenerator.class);  // Mock our static class
		when(IDGenerator.generateId()).thenReturn(1);  // Set expectation; uses Mockito syntax 
		
		int result = dao.create(new User());  // Make the test; verify the result
		assertEquals(1, result);
		// verifyStatic();  // Deprecated in PowerMock 1.7; will be removed in 2.0
		verifyStatic(IDGenerator.class);  // Preferred "verify()" syntax, PowerMock 1.7.1 and higher
	}

}
