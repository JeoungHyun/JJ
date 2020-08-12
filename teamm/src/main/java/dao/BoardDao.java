package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import dao.mapper.BoardMapper;
import logic.Board;

@Repository
public class BoardDao {	
	@Autowired
	private SqlSessionTemplate template;
	private Map<String,Object> param = new HashMap<>();
	  private String boardcolumn = "select num, name, pass, subject,"
	         + "content, file1 fileurl, regdate, readcnt, grp,"
	         + "grplevel, grpstep from board";
	
	public int maxnum(int no) {
		return template.getMapper(BoardMapper.class).maxnum(no);
	}

	public void insert(Board board) {
		template.getMapper(BoardMapper.class).insert(board);
	}

	public int count(int no,String searchtype, String searchcontent) {
		
//		String sql = "select count(*) from board";
//		if(searchtype != null && searchcontent != null) {
//			sql += " where " + searchtype + " like :searchcontent ";
//			param.clear();
//			param.put("searchcontent", "%" + searchcontent + "%");
//		}
		
		param.clear();
		param.put("no",no);
		param.put("searchtype", searchtype);
		param.put("searchcontent", searchcontent);
		return template.getMapper(BoardMapper.class).count(param);
	}

	public List<Board> list(int no,Integer pageNum, int limit, String searchtype, String searchcontent) {
		param.clear();

		param.put("searchtype", searchtype);
		param.put("searchcontent", "%" + searchcontent + "%");
		param.put("startrow", (pageNum - 1) * limit);
		param.put("limit", limit);
		param.put("no",no);
		System.out.println(no);
		return template.getMapper(BoardMapper.class).list(param);
	}

	public Board detail(int no, int bno) {
		param.clear();
		param.put("no", no);
		param.put("bno", bno);
		
		return template.getMapper(BoardMapper.class).detail(param);
	}

	public void readcnt(int num) {
		param.clear();
		param.put("no", num);
		template.getMapper(BoardMapper.class).readcnt(param);
	}



	public void update(Board board) {

		template.getMapper(BoardMapper.class).update(board);
	}

	public void delete(Board board) {

		template.getMapper(BoardMapper.class).delete(board);
	}






}
