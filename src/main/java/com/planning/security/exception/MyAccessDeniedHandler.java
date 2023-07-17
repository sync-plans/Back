package com.planning.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;

public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(403);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("message", accessDeniedException.getMessage());
        String responseString = new ObjectMapper().writeValueAsString(responseMap);
        response.getWriter().write(responseString);
    }
}
