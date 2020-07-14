package com.example.demo.config;

import com.example.demo.repository.salesRepository.UserRepository;
import com.example.demo.repository.usersRepository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserDetailsManager userDetailsManager;

    public UserDetailsManager getUserDetailsManager()
    {
        return this.userDetailsManager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ return new BCryptPasswordEncoder(); }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("123456"))
                .authorities("ADMIN", "USER")
                .and()
                .withUser("vietanh")
                .password(bCryptPasswordEncoder().encode("123456"))
                .authorities("ADMIN");
    }

    public SecurityConfig( UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/products/**").hasAnyAuthority("ADMIN","MANAGER")
                    .antMatchers("/admin/**",
                            "/admin/products/add-product/**",
                            "/admin/categories/add-category/**",
                            "/admin/brands/add-brand/**").hasAnyAuthority("ADMIN", "MANAGER")
                    .antMatchers("/user/**").hasAnyAuthority("ADMIN", "MAMAGER")
                    .anyRequest().permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout?logout")
                .permitAll()
        .and()
        .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }
}
