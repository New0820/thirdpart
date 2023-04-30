package com.example.temp.common.base;

import com.example.temp.util.LocalUtils;
import com.example.temp.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 公共的控制层类<br/>
 * 所有的Controller层; 继承此类; 特殊情况除外(需说明)
 *
 * @author liuguangping
 */
@Slf4j
public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    protected RedisUtil redisUtil;

    /**
     * 获取第三方授权token
     *
     * @return
     */
    protected String getToken() {
        String token = request.getHeader("token");
        return token;
    }

    /**
     * 获取用户id
     *
     * @return
     */
    protected int getUserId() {
        String token = getToken();
        Integer userIdValue = 0;
        if (!LocalUtils.isEmptyAndNull(token)) {
            final val userId = redisUtil.get("userId");
            if (StringUtils.isNotBlank(userId)) {
                userIdValue = Integer.parseInt(userId);
            }
        }
        return userIdValue;
    }

    /**
     * 获取店铺id
     *
     * @return
     */
    protected int getShopId() {
        String token = getToken();
        Integer userIdValue = 0;
        if (!LocalUtils.isEmptyAndNull(token)) {
            final val userId = redisUtil.get("shopId");
            if (StringUtils.isNotBlank(userId)) {
                userIdValue = Integer.parseInt(userId);
            }
        }
        return userIdValue;
    }
}