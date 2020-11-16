package com.example.util;

import com.example.model.HrAdmin;

/**
 * @author hqq
 */
public class SecurityUtil {

    /**
     * 获取登录用户
     */
    public static HrAdmin getCurrentUser() {
//        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
//        return principals == null ? null : (HrAdmin) principals.getPrimaryPrincipal();
        return null;
    }

    /**
     * 获取登录用户名
     */
    public static String getUsername() {
        HrAdmin currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getUsername();
    }

    /**
     * 获取当前登录用户的企业ID
     */
    public static String getOrgId() {
        HrAdmin currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getOrgId();
    }

    /**
     * 获取当前用户的部门ID
     */
    public static String getDeptId(){
        HrAdmin currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getDeptId();
    }

    /**
     * 获取当前用户的domain
     */
    public static String getDomain() {
        HrAdmin currentUser = getCurrentUser();
        return currentUser == null ? null : currentUser.getDomain();
    }

}
