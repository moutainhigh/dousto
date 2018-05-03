/**
 * 
 */
package com.ibm.oms.service.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ibm.sc.model.sys.User;
import com.ibm.sc.service.sys.UserService;

/**
 * @author xiaonanxiang
 * 当前登录人工具类
 */
@Component
public class UserUtil {
    @Resource
    private UserService userService;
    
    private User loginUser;
    
    private User getLoginUser() {
        loginUser = userService.getLoginUser();
        return loginUser;
    }
    
    /**
     * 获取当前登录者
     * @return 若realName不为空则取realName，否则取userName
     */
    public String getLoginUserRealName() {
        String name = "";
        User user = getLoginUser();
        if (null != user) {
            String realName = user.getRealname();
            if (null != realName) {
                name = realName;
            } else {
                name = user.getUserName();
            }
        }

        return name;
    }
}
