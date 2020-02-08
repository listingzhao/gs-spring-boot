package com.leyiju;

import com.leyiju.filter.CustomFromLoginFilter;
import com.leyiju.filter.JwtTokenFilter;
import com.leyiju.filter.qq.QQAuthenticationFilter;
import com.leyiju.filter.qq.QQAuthenticationManager;
import com.leyiju.service.CustomUserService;
import com.leyiju.service.handler.EntryPointUnauthorizedHandler;
import com.leyiju.service.handler.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created with com.leyiju.
 *
 * @author: Xavier
 * @time: 2019/6/5 11:42
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserService customUserService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // JWT不需要HttpSession
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                // swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/configuration/**").permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterAt(customFromLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(new EntryPointUnauthorizedHandler()).accessDeniedHandler(new RestAccessDeniedHandler());
        http.headers().cacheControl();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() throws Exception {
        return new JwtTokenFilter();
    }

    private CustomFromLoginFilter customFromLoginFilter() {
        return new CustomFromLoginFilter("/auth/token", customUserService);
    }

//    private QQAuthenticationFilter qqAuthenticationFilter() {
//        QQAuthenticationFilter authenticationFilter = new QQAuthenticationFilter("/login/qq");
//        authenticationFilter.setAuthenticationManager(new QQAuthenticationManager());
//        return authenticationFilter;
//    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
