package cn.duc.global.service.impl;

import cn.duc.global.repository.dao.generator.SzyUserMapper;
import cn.duc.global.repository.model.generator.SzyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.duc.global.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("szyUserMapper")
	private SzyUserMapper szyUserMapper;

	@Override
	public SzyUser getUser() {
		SzyUser szyUser = szyUserMapper.selectByPrimaryKey("1");
		return szyUser;
	}
}
