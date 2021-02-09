package com.unifacef.tcc.configuration.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.unifacef.tcc.base.dto.BaseResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Value("${jwt.secret-key}")
  private String jwtSecretKey;

  @Autowired
  private ObjectMapper mapper;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(
            new JwtInterceptorConfiguration(this.jwtSecretKey),
            UsernamePasswordAuthenticationFilter.class
        )
        .exceptionHandling()
        .authenticationEntryPoint((request, response, e) -> {
          response.setStatus(HttpStatus.UNAUTHORIZED.value());
          response.setContentType("application/json");
          response.getWriter().write(
              this.mapper.writeValueAsString(
                  new BaseResponseError("Unauthorized", "Unauthorized", 401)
              )
          );
        });
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers(
        "/",
        "/docs",
        "/docs/",
        "/swagger",
        "/swagger/",
        "/swagger-ui.html",
        "/swagger-ui",
        "/swagger-ui/**",
        "/v2/api-docs",
        "/swagger-resources/**",
        "/actuator/**"
    );
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authManager) throws Exception {
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
