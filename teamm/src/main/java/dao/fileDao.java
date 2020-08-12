package dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.mapper.FileMapper;
import logic.SNSFile;


@Repository
public class fileDao {

	@Autowired
	private SqlSessionTemplate template;
	private Map <String, Object> param = new HashMap <String, Object> ();
	
	public SNSFile maxfno() {
		return template.getMapper(FileMapper.class).maxfno();
	}

	public void insert_file(SNSFile f) {
		template.getMapper(FileMapper.class).insert_file(f);
	}
}
