package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.User;

public interface UserMapper {

	@Select({"<script>",
		"select * from user ",
		"<if test='id != null'> where id=#{id}</if>",
		"<if test='id == null'> where id != 'admin'</if>",
		"</script>"})
	List<User> select(Map<String, Object> param);

	@Insert("insert into user "
			+ "(name, id, password, email, regdate) "
			+ "values(#{name}, #{id}, #{password}, #{email}, now())")
	void insert(User user);

	@Update("update user set name=#{name},introduction=#{introduction},"
			+ "email=#{email} where id=#{id}")
	void update(User user);

	@Delete("delete from user where id=#{id}")
	void delete(Map<String, Object> param);

	
}
