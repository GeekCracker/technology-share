package com.technology.share.service.impl;

import com.technology.share.domain.User;
import com.technology.share.mapper.UserMapper;
import com.technology.share.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

}
