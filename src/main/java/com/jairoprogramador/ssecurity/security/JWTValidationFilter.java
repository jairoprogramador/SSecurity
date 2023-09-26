package com.jairoprogramador.ssecurity.security;

import com.jairoprogramador.ssecurity.services.JWTService;
import com.jairoprogramador.ssecurity.services.JWTUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@AllArgsConstructor
@Slf4j
public class JWTValidationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final JWTUserDetailService jwtUserDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var requestTokenHeader = request.getHeader("Authorization");
        String userName = null;
        String jwt = null;
        if(Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")){
            jwt = requestTokenHeader.substring(7);
            try {
                userName = jwtService.getUserNameFromToken(jwt);
            }catch (IllegalArgumentException e){
                log.error(e.getLocalizedMessage());
            }catch (ExpiredJwtException e){
                log.warn(e.getMessage());
            }
        }

        if(Objects.nonNull(userName) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())){
            final var userDetails = this.jwtUserDetailService.loadUserByUsername(userName);
            if (this.jwtService.validateToken(jwt, userDetails)){
                var userNameAndPassAuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                userNameAndPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userNameAndPassAuthToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
