package cn.duc.global.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.duc.global.repository.dao.UserMapper;
import cn.duc.global.repository.model.User;
import cn.duc.global.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMappering;

	@Override
	public User getUser() {
		return userMappering.selectByPrimaryKey(1);
	}
}
