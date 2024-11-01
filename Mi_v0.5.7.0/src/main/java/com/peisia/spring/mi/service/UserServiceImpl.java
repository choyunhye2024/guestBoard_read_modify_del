package com.peisia.spring.mi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peisia.dto.UserDto;
import com.peisia.spring.mi.mapper.UserMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class UserServiceImpl implements UserService {

	@Setter(onMethod_ = @Autowired)
	UserMapper mapper;

	// 회원가입
	public void register(UserDto user) {
		log.info("impl테스트입니다=========" + user.getUserName());
		mapper.register(user);
	}

	// 로그인
	@Override
	// return을 불러오려면 String으로 생성자를 불러와야한다.
	public String login(UserDto user) {
		// 로그인하려는 아이디 비밀번호가 잘 넘어오는지 확인한다.
		log.info("impl login text =======" + user.getId() + user.getPw());
		// UserDto를 x라는 객체에 넘겨 mapper.login을 불러온다.
		UserDto x = mapper.login(user);
		// sysout으로 아이디 비밀번호가 잘 넘어왔는지 확인한다. UserDto를 불러와 만든 x를 호출해 뒤에붙인다.
		System.out.println(x.getId() + x.getPw());
		// String을 정의해 유저 아이디를 불러온다.
		String id = user.getId();
		// 위에서 불러온 id를 리턴으로 받아낸다. String으로 생성자를 바꿔야 return받을수있다.
		return id;
	}
}
