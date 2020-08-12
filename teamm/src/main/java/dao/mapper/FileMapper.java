package dao.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import logic.SNSFile;

public interface FileMapper {

	@Select("select ifnull(max(fno), 0)+1 fno from file")
	SNSFile maxfno();

	@Insert("insert into file (fno, wno, wno2, wno3, fwhere, name, filename, fileurl, regdate) values(#{fno},#{wno},#{wno2},#{wno3},#{fwhere},#{name},#{filename},#{fileurl},now())")
	void insert_file(SNSFile f);

}
