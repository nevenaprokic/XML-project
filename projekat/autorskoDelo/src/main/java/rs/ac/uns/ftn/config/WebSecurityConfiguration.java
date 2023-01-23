package rs.ac.uns.ftn.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import rs.ac.uns.ftn.security.RestAuthenticationEntryPoint;
import rs.ac.uns.ftn.utils.TokenUtils;

@Configuration
// Ukljucivanje podrske za anotacije "@Pre*" i "@Post*" koje ce aktivirati autorizacione provere za svaki pristup metodi
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	private TokenUtils tokenUtils;

	
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

            .authorizeRequests()
            .antMatchers("/auth/**").permitAll()
            .antMatchers("/user/**").permitAll()
            .antMatchers("/**").permitAll()

            .anyRequest().authenticated().and()
            .cors().and();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.GET,  "/","/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js");
        web.ignoring().antMatchers(HttpMethod.GET,"/confirmation");
	    }
}
