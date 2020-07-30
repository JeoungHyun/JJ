package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;


import dao.mapper.UserMapper;
import logic.User;


@Repository
public class UserDao {
	
	@Autowired	
	private SqlSessionTemplate template;
	private Map<String ,Object> param = new HashMap<>();






	public User selectOne(String id) {
		param.clear();
		param.put("id",id);
		List<User> list = template.getMapper(UserMapper.class).select(param);
		
		if(list.size() == 0) return null;
		
		return list.get(0);
	}


	public void update(User user) {
		
	template.getMapper(UserMapper.class).update(user);
		
	}


	public void delete(String userid) {
	
		param.clear();
		param.put("userid",userid);
		template.getMapper(UserMapper.class).delete(param);
		
	}


	public List<User> userList() {
	
		 return template.getMapper(UserMapper.class).select(null);
		
	}


	public List<User> userList(String[] idchks) {
	
		
		param.clear();
		param.put("userids", idchks);
		return template.getMapper(UserMapper.class).select(param);
	}


	public void follow(String loginuserid,String feeduserid ) {
		param.clear();
		param.put("loginuserid", loginuserid);
		param.put("feeduserid", feeduserid);
		template.getMapper(UserMapper.class).insert(param);
		return ;
	}
	
	public void unfollow(String loginuserid, String feeduserid) {
		param.clear();
		param.put("loginuserid", loginuserid);
		param.put("feeduserid", feeduserid);
		template.getMapper(UserMapper.class).unfollow(param);
		return ;
	}


	public User feeduser(String name) {
		param.clear();
		param.put("name", name);
		List<User> list = template.getMapper(UserMapper.class).feeduser(param);
		return list.get(0);
	}


	public String follow_num(String name) {
		param.clear();
		param.put("name", name);
		return template.getMapper(UserMapper.class).follow_num(param);
	}


	public String follower_num(String name) {
		param.clear();
		param.put("name", name);
		return template.getMapper(UserMapper.class).follower_num(param);
	}


	public String follow_check(String user, String loginuser) {
		param.clear();
		param.put("user", user);
		param.put("loginuser", loginuser);
		return template.getMapper(UserMapper.class).follow_check(param);
	}
	public List<Map<String , Object>> graph2(String loginuserid) {
		param.clear();
		param.put("loginuserid", loginuserid);
		
		return template.getMapper(UserMapper.class).graph2(param);
		
	}


	
	
}
