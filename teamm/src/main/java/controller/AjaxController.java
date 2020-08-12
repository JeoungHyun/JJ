package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import logic.SNSFile;
import logic.SNSService;



@RestController // @ResponseBody: View 없이 직접 데이터를 클라이언트에 전송
@RequestMapping("ajax")
public class AjaxController {
	
	@Autowired
	private SNSService service;
	

	@RequestMapping(value = "fileupload", produces = "text/plain; charset=UTF-8", method = RequestMethod.POST)
	public String fileupload(MultipartFile[] files, HttpServletRequest request) {
		System.out.println("ajax");
		System.out.println(files);
		for(MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
			service.uploadFileCreate(file, request, "test/file/");
			SNSFile f = service.maxfno();
			f.setfileurl("file/");
			f.setFilename(file.getOriginalFilename());
			service.insert_file(f);
		}
		return null;
	}
}
