package com.cubicfox.security;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException
    {
        String requestURI = request.getRequestURI();
        if(requestURI.equals("/login")){
            if(authException instanceof BadCredentialsException){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials!");
            }

            if(authException instanceof DisabledException){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User disabled!");
            }
        }



        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }


}