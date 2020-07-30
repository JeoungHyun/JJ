package logic;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import dao.UserDao;

@Service
public class ShopService {

	@Autowired
	private UserDao userDao;

	private Map<String,Object> map = new HashMap<>();





	private void uploadFileCreate(MultipartFile picture, HttpServletRequest request, String path) {
		//picture: 파일의 내용 저장
		String orgFile = picture.getOriginalFilename();
		String uploadPath=request.getServletContext().getRealPath("/")
				+ path;  
		File fpath = new File(uploadPath);
		if(fpath.exists()) fpath.mkdirs();
		try {
			//파일의 내용을 실제 파일로 저장
			picture.transferTo(new File(uploadPath + orgFile));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}





	public User getUser(String userid) {
		return userDao.selectOne(userid);
	}

	/*
	 * db에 sale정보와 saleitem 정보 저장.
	 * 저장된 내용을 Sale 객체로 리턴
	 * 1. sale 테이블의 saleid 값을 설정 => 최대값 + 1
	 * 2. sale의 내용 설정. => insert
	 * 3. Cart 정보(itemSet) SaleItem 내용설정 => insert
	 * 4. 모든 정보를 Sale 객체로 저장
	 */



	public void update(User user) {
		userDao.update(user);
	}

	public void delete(String userid) {
		userDao.delete(userid);
	}

	public List<User> getUserList() {
		return userDao.userList();
	}

	public List<User> userList(String[] idchks) {
		return userDao.userList(idchks);
	}



	public void follow(String loginuserid,String feeduserid) {
		userDao.follow(loginuserid,feeduserid );
		
	}





	public User feedUser(String name) {
		
		return userDao.feeduser(name);
	}





	public String follow_num(String name) {
		
		return userDao.follow_num(name);
	}





	public String follower_num(String name) {
		
		return userDao.follower_num(name);
	}





	public String follow_check(String user, String loginuser) {
		return userDao.follow_check(user,loginuser);
	}





	public void unfollow(String loginuserid, String feeduserid) {
		userDao.unfollow(loginuserid,feeduserid );
		
	}

	public Map<String, Object> graph2(String loginuserid) {
		Map<String,Object> map = new HashMap<String ,Object>();
		for(Map<String,Object> m : userDao.graph2(loginuserid)) {
			map.put((String)m.get("regdate"),m.get("cnt"));
		}
		return map;
	}







	

	
}
