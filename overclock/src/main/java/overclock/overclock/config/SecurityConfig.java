//package overclock.overclock.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import overclock.overclock.entity.Member;
//import overclock.overclock.security.filter.ApiCheckFilter;
//import overclock.overclock.security.filter.ApiLoginFilter;
//import overclock.overclock.security.util.JWTUtil;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//  @Bean
//  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
//    http.csrf().disable();
//    http.authorizeRequests().antMatchers("/user/**").permitAll()
//            .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//            .anyRequest().permitAll()
//            .and()
//            .formLogin().disable();
////                .loginPage("/loginForm");
//    http.addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class);
//    http.addFilterBefore(apiLoginFilter(authenticationManager),
//            UsernamePasswordAuthenticationFilter.class);
//    return http.build();
//  }
//  @Bean
//  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//    return authenticationConfiguration.getAuthenticationManager();
//  }
//  @Bean
//  public ApiLoginFilter apiLoginFilter(AuthenticationManager authenticationManager) throws Exception {
//
//    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/member/login", jwtUtil());
//    apiLoginFilter.setAuthenticationManager(authenticationManager);
//    return apiLoginFilter;
//  }
//
//  @Bean
//  public JWTUtil jwtUtil(){
//    return new JWTUtil();
//  }
//
//
//  @Bean
//  public ApiCheckFilter apiCheckFilter(){
//    return new ApiCheckFilter("/api/**/*", jwtUtil());
//  }
//
//  @Bean
//  PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser(Member.("user").password("{noop}user").roles("USER"))
////                .withUser(Member.withUsername("admin").password("{noop}admin").roles("USER", "ADMIN"));
////        /* auth.inMemoryAuthentication()
////                .withUser("manager")
////                .password("{noop}manager")
////                .roles("MANAGER"); */
////    }
//}
