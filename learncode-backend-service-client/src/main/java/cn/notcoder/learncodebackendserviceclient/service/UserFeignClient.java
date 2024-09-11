package cn.notcoder.learncodebackendserviceclient.service;

import cn.notcoder.learncodebackendcommon.common.ErrorCode;
import cn.notcoder.learncodebackendcommon.exception.BusinessException;
import cn.notcoder.learncodebackendmodel.model.entity.User;
import org.springframework.cloud.openfeign.FeignClient;

import javax.servlet.http.HttpServletRequest;

import static cn.notcoder.learncodebackendcommon.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务
 */
@FeignClient(name = "learncode-backend-user-service", path = "/api/user/inner")
public interface UserFeignClient {

    default User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }
}
