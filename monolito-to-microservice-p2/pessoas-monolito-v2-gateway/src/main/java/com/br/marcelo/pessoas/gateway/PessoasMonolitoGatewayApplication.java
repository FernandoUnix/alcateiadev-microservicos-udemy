package com.br.marcelo.pessoas.gateway;

import com.br.marcelo.pessoas.gateway.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableZuulProxy
public class PessoasMonolitoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PessoasMonolitoGatewayApplication.class, args);
    }

    @RequestMapping("/status")
    public String hasWrite() {
        return "OK";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Map<String, Object> user(Principal user) {
        return Collections.<String, Object>singletonMap("name", user.getName());
    }

    @RequestMapping("/login")
    public String login() {
        return "forward:/";
    }

    @Configuration
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private UsersService users;

        @Autowired
        public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
            // @formatter:off
            auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
            // @formatter:on
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            String[] permis = new String[]{
                    "/index.html", "/principal/template/**", "/js/**", "/img/**", "/usuarios/**", "/template/**"
            };

            // @formatter:off
            http
                    .httpBasic()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                    .and()
                    .authorizeRequests()
                    .antMatchers(
                            permis
                    ).permitAll()

                    .anyRequest().authenticated()
                    .and().
                    formLogin()
                    .loginPage("/index.html")
                    .failureUrl("/error.html")
                    .permitAll()
                    .and()
                    .csrf().disable()/*
                    .csrfTokenRepository(csrfTokenRepository())
                    .and()
                    .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)*/
                    .formLogin().failureUrl("/");

            // @formatter:on
        }

        private Filter csrfHeaderFilter() {

            return new OncePerRequestFilter() {

                @Override
                protected void doFilterInternal(HttpServletRequest request,
                                                HttpServletResponse response, FilterChain filterChain)
                        throws ServletException, IOException {

                    CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

                    if (csrf != null) {
                        Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
                        String token = csrf.getToken();

                        if (cookie == null || token != null && !token.equals(cookie.getValue())) {
                            cookie = new Cookie("XSRF-TOKEN", token);
                            cookie.setPath("/");
                            response.addCookie(cookie);

                        }

                        response.setHeader("X-CSRF-HEADER", csrf.getHeaderName());
                        response.setHeader("X-CSRF-PARAM", csrf.getParameterName());
                        response.setHeader("X-CSRF-TOKEN", csrf.getToken());

                    }
                    filterChain.doFilter(request, response);

                }
            };
        }

        private CsrfTokenRepository csrfTokenRepository() {
            HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
            repository.setHeaderName("X-XSRF-TOKEN");
            return repository;
        }
    }

}
