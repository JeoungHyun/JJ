package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.User;

public interface UserMapper {


	
	@Select({"<script>"
			+ "select * from user "
			+ "<if test='id != null'> where id=#{id} </if>"
			+ "<if test='id == null'> where id !='admin' </if>"
			+ "<if test='ids != null'> and id in "
			+ "<foreach collection='userids' item='id' separator=',' "
			+ "open='(' close= ')'>#{id}</foreach></if> "
			+ "</script>"})
	List<User> select(Map<String, Object> param);

	
	@Update("update useraccount set username = #{username} , phoneno=#{phoneno} , postcode=${postcode}, address=#{address} ,email=#{email} , birthday=#{birthday} where userid=#{userid}")
	void update(User user);

	
	
	@Delete("delete from useraccount where userid = #{userid}")
	void delete(Map<String, Object> param);


	@Insert("INSERT INTO follow VALUES ( #{loginuserid} ,#{feeduserid} , now())")
	void insert(Map<String, Object> param);


	@Select("select * from user where name like #{name}")
	List<User> feeduser(Map<String, Object> param);


	@Select("SELECT IFNULL(COUNT(*),0)  FROM follow WHERE user_from LIKE #{name}")
	String follow_num(Map<String, Object> param);


	@Select("SELECT IFNULL(COUNT(*),0)  FROM follow WHERE user_to LIKE #{name}")
	String follower_num(Map<String, Object> param);


	@Select("SELECT IFNULL(COUNT(*),0) FROM follow WHERE user_from LIKE #{loginuser} AND user_to LIKE #{user} ")
	String follow_check(Map<String, Object> param);


	
	@Delete("delete from follow where user_from like #{loginuserid} and user_to like #{feeduserid}")
	void unfollow(Map<String, Object> param);


	@Select("SELECT  DATE_FORMAT(d.regdate,'%Y-%m-%d') regdate, COUNT(*) cnt FROM follow f, date_t d WHERE date_format(f.regdate,'%y-%M-%d') LIKE date_format(d.regdate,'%y-%M-%d') AND f.user_to = '${loginuserid}' group by DATE_FORMAT(regdate,'%Y-%m-%d')  ORDER BY DATE_FORMAT(NOW(),'%y-%M-%d') LIMIT 0,7\r\n" + 
			"\r\n" + 
			"")
	List<Map<String, Object>> graph2(Map<String, Object> param);

	
	
	
}
