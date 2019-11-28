package com.technology.share.admin.controller;

import com.technology.share.domain.User;
import com.technology.share.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminUserController")
@RequestMapping("/admin/user")
public class UserController extends BaseController<User,UserService> {
}
