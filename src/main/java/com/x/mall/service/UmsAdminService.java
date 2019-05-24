package com.x.mall.service;

import com.x.mall.mbg.model.UmsAdmin;
import com.x.mall.mbg.model.UmsPermission;

import java.util.List;

/**
 * 后台管理员服务
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUserName(String userName);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登陆功能
     * @param userName 用户名
     * @param password 密码
     * @return 生成JWT的token
     */
    String login(String userName,String password);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);

}
