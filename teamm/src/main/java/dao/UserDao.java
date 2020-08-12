package dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.UserMapper;
import logic.User;

@Repository
public class UserDao {
	@Autowired
	private SqlSessionTemplate template;
	Map<String,Object> param = new HashMap<>();
	
	public User selectOne(String id) {
		param.clear();
		param.put("id",id);	
		return template.getMapper(UserMapper.class).select(param).get(0);
	}

	public void insert(User user) {
		template.getMapper(UserMapper.class).insert(user);
	}

	public void update(User user) {
		template.getMapper(UserMapper.class).update(user);
	}

	public void delete(String id) {
		param.clear();
		param.put("id", id);
		template.getMapper(UserMapper.class).delete(param);
	}
}
