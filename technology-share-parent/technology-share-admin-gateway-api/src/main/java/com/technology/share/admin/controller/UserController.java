package com.technology.share.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.technology.share.domain.User;
import com.technology.share.domain.view.VRole;
import com.technology.share.enums.CodeMessage;
import com.technology.share.exception.BizException;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.UserService;
import com.technology.share.service.VRoleService;
import com.technology.share.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("AdminUserController")
@RequestMapping("/admin/user")
public class UserController extends BaseController<User,UserService> {


    @Autowired
    private UserService userService;
    @Autowired
    private VRoleService vRoleService;


    /**
     * 增加或修改
     * @param user 传入需要添加或修改的实体
     * @return 返回操作信息
     */
    @Override
    @RequestMapping("save")
    public ResponseResult save(User user) {
        user.setUserPassword(MD5Utils.encode(user.getUserPassword()));
        return super.save(user);
    }

    /**
     * 登录接口
     * @param username 用户名
     * @param password 用户密码
     * @return 返回用户信息，包含权限树
     */
    @RequestMapping("login")
    public ResponseResult login(String username,String password){
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new BizException(CodeMessage.USERNAME_PASSWORD_IS_BLANK);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        queryWrapper.eq("user_password", MD5Utils.encode(password));
        User user = getService().getOne(queryWrapper);
        if(user == null){
            throw new BizException(CodeMessage.USERNAME_PASSWORD_IS_ERROR);
        }
        VRole role= vRoleService.getById(user.getRoleId());
        user.setRole(role);
        return ResponseResult.ok(user);
    }

    /**
     * 根据ID重置用户密码
     * @param id 传入ID
     * @return 返回操作信息
     */
    @RequestMapping("resetPassword")
    public ResponseResult resetPassword(String id){
        User user = getService().getById(id);
        user.setUserPassword(MD5Utils.encode("123456"));
        getService().updateById(user);
        return ResponseResult.ok();
    }

    /**
     * 根据ID启用禁用用户
     * @param id 传入用户ID
     * @return 返回操作信息
     */
    @RequestMapping("enableDisable")
    public ResponseResult enableDisable(String id){
        userService.enableDisable(IdTypeHandler.decode(id));
        return ResponseResult.ok();
    }
}
