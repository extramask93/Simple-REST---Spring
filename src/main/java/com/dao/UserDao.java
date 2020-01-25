package com.dao;

import com.domain.User;

public interface UserDao {
	int save(User user);
	User get(int id);
}
