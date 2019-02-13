package com.example.useradmin.dao;

import com.example.useradmin.dto.User;
import com.example.useradmin.util.IDGenerator;

public class UserDAO {
	public int create(User user) {
		int id = IDGenerator.generateId();
		// ... TBD: Save the user object to the DB ...
		return id;		
	}
}
