package com.prajwal.agrolive.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired private CustomUserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            auth ->
                auth
                    // ✅ Public pages - NO LOGIN REQUIRED
                    .requestMatchers("/", "/register", "/login", "/test")
                    .permitAll()

                    // ✅ Static resources - MUST BE ACCESSIBLE WITHOUT LOGIN
                    .requestMatchers("/css/**", "/js/**", "/img/**", "/images/**")
                    .permitAll()
                    .requestMatchers("/lib/**", "/fonts/**", "/assets/**")
                    .permitAll()
                    .requestMatchers("/webjars/**", "/resources/**", "/static/**")
                    .permitAll()
                    .requestMatchers("/*.css", "/*.js", "/*.jpg", "/*.png", "/*.gif")
                    .permitAll()

                    // 🔒 Protected pages - LOGIN REQUIRED
                    .requestMatchers("/market", "/allCommodities", "/contact", "/api/**")
                    .authenticated()

                    // Any other request requires authentication
                    .anyRequest()
                    .authenticated())
        .formLogin(
            form ->
                form.loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true")
                    .permitAll())
        .logout(
            logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .logoutRequestMatcher(
                        new org.springframework.security.web.util.matcher.AntPathRequestMatcher(
                            "/logout", "GET")))
        .sessionManagement(session -> session.maximumSessions(1).maxSessionsPreventsLogin(false));

    return http.build();
  }
}
