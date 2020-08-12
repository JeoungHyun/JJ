package logic;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dao.BoardDao;
import dao.UserDao;
import dao.fileDao;


@Service
public class SNSService {

	@Autowired
	private fileDao filedao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired 
	private BoardDao boardDao;
	
	private Map<String,Object> map = new HashMap<>();
	
	public void uploadFileCreate(MultipartFile picture, HttpServletRequest request, String path) {
		String orgFile = picture.getOriginalFilename();
		String uploadPath = request.getServletContext().getRealPath("/") + path;
		// 웹 어플리케이션의 경로를 구하기 (webapp폴더까지)
		System.out.println(uploadPath);
		System.out.println("orgFile: "+ orgFile);
		File fpath = new File(uploadPath);
		if(!fpath.exists()) fpath.mkdirs();
		try {
			picture.transferTo(new File(uploadPath + orgFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SNSFile maxfno() {
		return filedao.maxfno();
	}

	public void insert_file(SNSFile f) {
		filedao.insert_file(f);
	}
	
	public User getUser(String id) {
		return userDao.selectOne(id);
	}

	public void userInsert(User user) {
		userDao.insert(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void delete(String id) {
		userDao.delete(id);
	}
	


	public int boardCount(int no, String searchtype, String searchcontent) {
		map.clear();
		if(searchtype != null) {
			String[] cols = searchtype.split(",");
			map.put("col1", cols[0]);
			if(cols.length > 1) {
				map.put("col2", cols[1]);
			}
			if(cols.length > 2) {
				map.put("col3", cols[2]);
			}
		}
		map.put("searchcontent", searchcontent);
		return boardDao.count(no,searchtype,searchcontent);
	}

	public List<Board> boardlist(int no,Integer pageNum, int limit, String searchtype, String searchcontent) {
		return boardDao.list(no,pageNum,limit,searchtype,searchcontent);
	}
	
	public void boardWrite(Board board, HttpServletRequest request) {

		int max = boardDao.maxnum(board.getNo());
		board.setBno(++max);
		boardDao.insert(board); 
	}
	
	
	
	
	public Board selectOne(int no,int bno) {
		return boardDao.detail(no,bno);
	}

	public Board getBoard(Integer no, Integer bno, boolean able) {
//		if(able) {
//			boardDao.readcnt(no,bno);
//		}
		return boardDao.detail(no,bno);
	}
	
	public void boardDelete(Board board) {
		boardDao.delete(board);
	}
}
