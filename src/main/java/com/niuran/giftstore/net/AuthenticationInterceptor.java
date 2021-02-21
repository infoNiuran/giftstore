package com.niuran.giftstore.net;

import com.alibaba.fastjson.JSON;
import com.niuran.giftstore.bean.UserBaseInfo;
import com.niuran.giftstore.exception.CommonException;
import com.niuran.giftstore.feign.FeignUserInfoService;
import com.niuran.giftstore.enume.ResponseEnum;
import com.niuran.giftstore.response.base.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@SuppressWarnings("Duplicates")
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final static String ACCESS_TOKEN = "token";
//    private final static String ACCESS_TOKEN_ADMIN = "tokenAdmin";

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private FeignUserInfoService feignUserInfoService;

    // 在业务处理器处理请求之前被调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (loginRequired != null) {
            // 判断是否存在令牌信息，如果存在，则允许登录
            String accessToken = request.getHeader(ACCESS_TOKEN);
            if (null == accessToken) {
                throw new CommonException(ResponseEnum.EMPTY_TOKEN);
            } else {
                DataResponse dataResponse = feignUserInfoService.getLoginStatus();
                if (dataResponse.getRetCode() != 0) {
                    throw new CommonException(dataResponse.getRetCode(), dataResponse.getMessage());
                } else {
                    UserBaseInfo userBaseInfo = JSON.parseObject(JSON.toJSONString(dataResponse.getData()), UserBaseInfo.class);
                    if (!accessToken.equals(redisTemplate.opsForValue().get("userApi:userToken:" + userBaseInfo.getMobile()))) {
                        throw new CommonException(ResponseEnum.ERROR_TOKEN);
                    }
                    request.setAttribute("CURRENT_USER", userBaseInfo);
                }
                return true;
            }

//        }else if(adminRequired != null){
//            // 判断是否存在令牌信息，如果存在，则允许登录
//            String accessToken = request.getHeader(ACCESS_TOKEN_ADMIN);
//            if (null == accessToken) {
//                throw new CommonException(ResponseEnum.EMPTY_TOKEN);
//            }else{
//                // Redis校验token
//                // jwt校验token
//                DecodedJWT jwt;
//                jwt = TokenUtil.deToken(accessToken);
//                String userName = jwt.getClaim("userName").asString();
//                if(!accessToken.equals(redisTemplate.opsForValue().get("userApi:userToken:"+userName))){
//                    throw new CommonException(ResponseEnum.ERROR_TOKEN);
//                }
//                redisTemplate.expire("channelApi:adminToken:"+userName,7, TimeUnit.DAYS);
//                Long userId = jwt.getClaim("userId").asLong();
//                AdminUser user = adminUserService.getAdminUserById(userId);
//                if(user.getStatus().equals(StatusEnum.不可用.getValue())){
//                    throw new CommonException(ResponseEnum.ERROR_USER_ACCOUNT_EXPIRED);
//                }
//                request.setAttribute("CURRENT_ADMIN_USER", user);
//            }
//            return true;
        } else {
            return true;
        }
    }

    // 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        //刷新token 需要写代码
    }

    // 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

}
