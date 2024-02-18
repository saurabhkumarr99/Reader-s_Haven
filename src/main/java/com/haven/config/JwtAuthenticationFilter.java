package com.haven.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.haven.postgress.model.Customers;
import com.haven.postgress.service.CustomUserDetailService;
import com.haven.security.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
@EnableCaching
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String REQUEST_COUNT_KEY = "requestCount";
    private static final int REQUEST_LIMIT = 10; // Limit requests to 10 per second

    private final ConcurrentHashMap<String, AtomicLong> requestCounts = new ConcurrentHashMap<>();
    
    @Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ipAddress = request.getRemoteAddr();

        //	Rate limiter
        AtomicLong count = requestCounts.computeIfAbsent(ipAddress, k -> new AtomicLong(0));
        if (count.incrementAndGet() > REQUEST_LIMIT) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Get JWT
        String reqTokenHeader = request.getHeader("Authorization");
        Customers customers = null;
        String useremail = null;
        String jwtToken = null;

        // Check Bearer
        if (reqTokenHeader != null && reqTokenHeader.startsWith("Bearer ")) {
            jwtToken = reqTokenHeader.substring(7);

            // Validate Bearer
            try {
                if (jwtUtil.isTokenValid(jwtToken)) {
                    useremail = jwtUtil.extractUseremail(jwtToken);
                    System.out.println("Valid token: " + useremail);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (useremail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.customUserDetailService.loadUserByUsername(useremail);

                System.out.println(userDetails);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    System.out.println("UserNamePasswordAuthentication - " + usernamePasswordAuthenticationToken);
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    System.out.println("User not found");
                }
            } else {
                System.out.println("Token is not valid");
            }
        }

        filterChain.doFilter(request, response);
    }
}
