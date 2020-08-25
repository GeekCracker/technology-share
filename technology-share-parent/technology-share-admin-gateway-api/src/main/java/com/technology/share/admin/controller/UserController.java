package com.technology.share.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.technology.share.domain.User;
import com.technology.share.domain.view.VRole;
import com.technology.share.domain.view.VUser;
import com.technology.share.enums.CodeMessage;
import com.technology.share.exception.BizException;
import com.technology.share.handler.IdTypeHandler;
import com.technology.share.response.ResponseResult;
import com.technology.share.service.UserService;
import com.technology.share.service.VRoleService;
import com.technology.share.service.VUserService;
import com.technology.share.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController("AdminUserController")
@RequestMapping("/admin/user")
public class UserController extends BaseController<User,UserService> {

    @Autowired
    private VUserService vUserService;
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
        User user = service.getOne(queryWrapper);
        if(user == null){
            throw new BizException(CodeMessage.USERNAME_PASSWORD_IS_ERROR);
        }
        VRole role= vRoleService.getById(user.getRoleIdRaw());
        user.setRole(role);
        user.setLastLoginTime(new Date());//最后登录时间
        service.updateById(user);
        return ResponseResult.ok(user);
    }

    /**
     * 根据ID重置用户密码
     * @param id 传入ID
     * @return 返回操作信息
     */
    @RequestMapping("resetPassword")
    public ResponseResult resetPassword(String id){
        User user = service.getById(id);
        user.setUserPassword(MD5Utils.encode("123456"));
        service.updateById(user);
        return ResponseResult.ok();
    }

    /**
     * 根据ID启用禁用用户
     * @param id 传入用户ID
     * @return 返回操作信息
     */
    @RequestMapping("enableDisable")
    public ResponseResult enableDisable(String id){
        service.enableDisable(IdTypeHandler.decode(id));
        return ResponseResult.ok();
    }

    /**
     * 查询数据列表（带分页）
     * @param pageNum 当前页
     * @param pageSize 每页显示数据条数
     * @param request 传入request请求
     * @return 返回分页数据
     */
    @Override
    @RequestMapping("queryPageData")
    public ResponseResult queryPageData(@RequestParam(defaultValue = "1") Long pageNum, @RequestParam(defaultValue = "10") Long pageSize, HttpServletRequest request) {
        QueryWrapper<VUser> queryWrapper = new QueryWrapper(JSONObject.parseObject(JSONObject.toJSONString(getQueryParam(request)),VUser.class));
        return ResponseResult.ok(vUserService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }
}
