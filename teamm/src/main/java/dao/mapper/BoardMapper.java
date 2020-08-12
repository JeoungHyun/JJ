package dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import logic.Board;

public interface BoardMapper {

	
	@Select("select ifnull(max(bno),0) from board where no=#{no}")
	int maxnum(int no);

	@Insert("insert into board (no,bno,name,title,content,regdate) "
			+ "values (#{no},#{bno},#{name},#{title},#{content},now() )")
	void insert(Board board);

	
	@Select({"<script> "
			+ "select count(*) from board "
			+ "<if test='searchtype != null and searchcontent != null '> where ${searchtype} like '%${searchcontent}%' </if>"
			+ "<if test='searchtype == null and searchcontent == null '> where no=${no} </if>"
			+ "</script>"})
	int count(Map<String, Object> param);

	@Select({"<script>"
			+ "select no, bno, name, title,"
	         + "content, regdate, open "
	         + " from board "
	         + "<if test='searchtype != null and searchcontent != null'> where ${searchtype} like #{searchcontent} </if>"
	         + "<if test='searchtype == null and searchcontent == null'> where no=#{no} </if>"
	         +" where no = #{no} order by bno desc limit #{startrow} , #{limit} "
			+ "</script>"})
	List<Board> list(Map<String, Object> param);

	
	@Select("select *  from board "
			+ "where bno = #{bno} and no=#{no} ")
	Board detail(Map<String, Object> param);

	@Update("update board set readcnt = (readcnt+1) where num= #{num} ")
	void readcnt(Map<String, Object> param);

	@Update("update board set grpstep = grpstep +1 where grp = :grp and grpstep > #{grpstep} ")
	void updateGrpStep(Map<String, Object> param);

	@Update("update board set name= #{name}, subject= #{subject}, content= #{content}, file1= #{fileurl} where num= #{num}")
	void update(Board board);
	
	@Delete("delete from board where num= #{num}")
	void delete(Board board);
	
	@Select("select name , count(*) cnt from board group by name order by cnt desc LIMIT 0,7")
	List<Map<String, Object>> graph1();

	@Select("select DATE_FORMAT(regdate,'%Y-%m-%d') date , count(*) cnt from board group by DATE_FORMAT(regdate,'%Y-%m-%d') order by cnt desc LIMIT 0,7")
	List<Map<String, Object>> graph2();

	

	
	


	
	
}
