package com.peisia.spring.mi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peisia.dto.UserDto;
import com.peisia.spring.mi.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/user/*")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	// 회원가입
	@GetMapping("/register")
	public void register() {

	}

	@GetMapping("/registerProc")
	public String registerProc(UserDto user) {
		log.info("등록입니다=====================" + user.getUserName());
		userService.register(user);
		return "/home";

	}

	// 로그인
	// login과 loginProc은 따로 생성해야한다. 이떄 login은 빈생성자로 남겨둔다.
	@GetMapping("/login")
	public void login() {

	}

	// loginProc은 유저서비스에서 불러와 로그인처리를 하고 세션으로 로그인정보를 저장해 가지고와 홈으로 반환하는 역할을 한다.
	@GetMapping("/loginProc")
	// return을 불러오려면 void가아닌 String이어야한다.
	public String loginProc(UserDto user, HttpSession session) {
		// 로그인 proc으로 잘넘어오는지 log를 찍어본다.
		log.info("로그인입니다=======================");
		// String id 를 지정해 userService.login을 불러온다.
		String id = userService.login(user);
		// 로그로 String id가 잘 불러와졌는지 확인하자. 앞에는 문구, 뒤에는 실제 아이디이다.
		log.info("id:" + id);
		// 세션 아이디설정을 하고 가지고온다.
		session.setAttribute("id", id);
		// string se로 (String으로 한번더 형변환) id정보를 가져온다.
		String se = (String) session.getAttribute("id");
		// id결과를 log로찍는다.
		log.info(se);
		// 홈으로 돌아간다. (String으로 생성자를 만들어야 return이 가능하다.)
		return "/home";
	}

	// 로그인후, 멤버인지 아닌지 감별하고 비회원일경우와 회원일경우의 수를 모두 계산해 행동한다.
	@GetMapping("/memberContent")
	// return을 받으려면 String으로 생성자를 만들어야한다. session을 받으려면 뒤의 생성자를 써줘야한다.
	public String memberContent(HttpSession session) {
		// String se를 (뒤에서 String으로 2차 형변환 하여) id를 가지고 온다.
		String se = (String) session.getAttribute("id");
		// se에 저장된 id,pw정보와 일치하지 않을경우
		if (se == null) {
			// 비회원으로 처리하고
			log.info("============비회원입니다.==============");
			// 홈화면으로 보내버린다.
			return "/home";
			// 아니면 맞을경우
		} else {
			// 회원으로 처리하고 se로 성공한 아이디를 로그로찍는다.
			log.info("============회원입니다.==============" + se);
			// 이후 멤버쉽 페이지로 넘어간다.
			return "/user/memberContent";
		}
	}

	// 로그아웃 페이지이다.
	@GetMapping("/logout")
	// return을 받으려면 String으로 가져와야한다. session을 날릴때도 뒤의 생성자를 써주자
	public String logout(HttpSession session) {
		// invalidate는 현재 로그인생성된 session을 날린다.
		session.invalidate();
		// 로그아웃이 완료되면 home.jps로 이동한다.
		return "/home";
	}

}