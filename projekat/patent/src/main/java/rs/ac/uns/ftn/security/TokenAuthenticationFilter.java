package rs.ac.uns.ftn.security;

import rs.ac.uns.ftn.utils.TokenUtils;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    private TokenUtils tokenUtils;

	@Autowired
    private UserDetailsService userDetailsService;

	public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = tokenUtils.getToken(request);
        System.out.println("CCCCCCCCC " + token);
        if (token != null) {

            String username = tokenUtils.getUsernameFromToken(token);
            System.out.println("DDDDDDDDD " + username);
            if (username != null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println(userDetails.getUsername() + " " + userDetails.getPassword() + " " + userDetails.getAuthorities());
                if (tokenUtils.validateToken(token, userDetails)) {

                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authentication.getToken());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
