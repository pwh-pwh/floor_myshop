package com.example.floor_myshop.interceptor;


import com.example.floor_myshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtFilter implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authHeader = request.getHeader("token");
//        if (authHeader != null) {
//            final String token = authHeader; // The part
//            try {
//                Claims claims = jwtUtil.parseJWT(token);
//                if (claims != null) {
//                    if("merchant".equals(claims.get("roles"))){//如果是用户
//                        request.setAttribute("merchant_claims", claims);
//                        return true;
//                    } else {
//                        new RuntimeException("token角色错误").printStackTrace();
//                        return false;
//                    }
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//                new RuntimeException("token解析异常").printStackTrace();
//                return false;
//            }
//        }
//        new RuntimeException("无token字段 header").printStackTrace();
//        return false;
//    }


}
