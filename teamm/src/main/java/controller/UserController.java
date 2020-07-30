package controller;


import java.util.List;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;




import logic.ShopService;
import logic.User;



@Controller   
@RequestMapping("user")  
public class UserController {
	
	@Autowired
	private ShopService service;
	
	@GetMapping("*")   
	public String userEntry(Model model) {
		model.addAttribute(new User());
		return null;
	}
	


	
	@PostMapping("login")
	public ModelAndView login(User user,BindingResult bresult, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(bresult.hasErrors()) {
			bresult.reject("error.input.user");
			return mav;
		}
		
		//1.db의 정보의 id와password 비교
		//2.일치 : session loginUser 정보 저장
		//3.불일치: 비밀번호 확인 내용 출력
		//4.db에 해당 id정보가 없는 경우 내용 출력
		try {
			
		User dbUser = service.getUser(user.getId());
		
		if(user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("loginUser", dbUser);
			mav.setViewName("redirect:main.shop");
		}else {  //비밀번호가 틀릴 경우
			bresult.reject("error.login.password");
		}
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
			bresult.reject("error.login.id");
		}

		return mav;
	}
	
	
	@GetMapping("myfeed")
	public ModelAndView myfeed(String name, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		User user = service.feedUser(name);
		String follow_num =service.follow_num(name);
		String follower_num = service.follower_num(name);
		User loginuser =  (User) session.getAttribute("loginUser");
		
		
		String follow_check = service.follow_check(name,loginuser.getName());

		mav.addObject("follow_check",follow_check);
		mav.addObject("follower_num",follower_num);
		mav.addObject("follow_num",follow_num);	
		mav.addObject("user",user);
		return mav;
		
	}
	
	@PostMapping("follow")
	public String follow(String loginuserid,String feeduserid, HttpSession session) {
		
	
		
		
		service.follow(loginuserid,feeduserid);
		
		return "redirect:myfeed.shop?name="+feeduserid;
	}
	
	@PostMapping("unfollow")
	public String unfollow(String loginuserid,String feeduserid, HttpSession session) {
		
	
		
		
		service.unfollow(loginuserid,feeduserid);
		
		return "redirect:myfeed.shop?name="+feeduserid;
	}
	
	
	
	@RequestMapping("logout")
	public String loginChecklogout(HttpSession session) {
		session.invalidate();
		return "redirect:login.shop";
	}
	
	@RequestMapping("main")
	//login 되어야 실행 가능. 메서드 이름 loginxxx로 지정
	public String loginCheckmain(HttpSession session) {
		return null;
	}
	

}