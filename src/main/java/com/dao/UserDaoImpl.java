package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.domain.User;

@Repository
public class UserDaoImpl implements UserDao{
	private static final String SQL_INSERT_USER = "insert into Users (imie, nazwisko,email) values (?,?,?)";
	private static final String SQL_GET_USER = "select imie,nazwisko,email from Users where user_id=?";
	private SimpleJdbcTemplate jdbcTemplate;
	@Autowired
	public void setJdbcTemplate(SimpleJdbcTemplate template) {
		jdbcTemplate = template;
	}
	@Override
	public User get(int id) {
		List<User> users = jdbcTemplate.query(
                SQL_GET_USER,
                (rs, rowNum) ->
                        new User(
                                rs.getString("imie"),
                                rs.getString("nazwisko"),
                                rs.getString("email")
                        ),id
        );
		return users.get(0);
		
	}
	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
	    jdbcTemplate.update(SQL_INSERT_USER, user.getImie(),user.getNazwisko(),user.getEmail());
	    int newID = jdbcTemplate.queryForInt("select max(user_id) from Users");
		return newID;
		//jdbcTemplate.update(SQL_INSERT_USER, user.getImie(),user.getNazwisko(),user.getEmail());
	}

}
