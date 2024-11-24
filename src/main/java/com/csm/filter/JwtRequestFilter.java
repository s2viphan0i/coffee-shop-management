package com.csm.filter;

import com.csm.exception.BadRequestException;
import com.csm.factory.ResponseFactory;
import com.csm.model.ResponseStatusEnum;
import com.csm.model.response.GeneralResponse;
import com.csm.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.csm.model.ResponseStatusEnum.INVALID_CREDENTIAL;

@WebFilter
public class JwtRequestFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if ("/auth".equals(path) || "/register".equals(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            setUnauthorizedResponse(response);
            return;
        }
        token = token.substring(7);
        String username = JwtUtil.getUsername(token);
        List<String> roles = JwtUtil.getRoles(token);

        if (username == null || !JwtUtil.validateToken(token, username)) {
            setUnauthorizedResponse(response);
            return;
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                username, null, Collections.singletonList(new SimpleGrantedAuthority(roles.get(0))));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private void setUnauthorizedResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseFactory.errorObject(INVALID_CREDENTIAL)));
    }
}
