package cn.duc.global.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.duc.global.repository.model.User;
import cn.duc.global.service.UserService;

/**
 * @描述
 * @作者 szy
 * @创建时间 2018年5月21日
 * @版本 1.0
 */
@RestController
public class TestController {

	@Autowired
	private UserService userServiceImpl;

	/**
	 * 测试
	 * @return
	 */
	@GetMapping("/video")
	public User addVideo() {
		return userServiceImpl.getUser();
	}
}
