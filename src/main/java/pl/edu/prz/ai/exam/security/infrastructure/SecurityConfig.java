package pl.edu.prz.ai.exam.security.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/login").permitAll()
                    .anyRequest().authenticated()
                .and()
                .logout()
                    .permitAll()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jsonAuthenticationRequestFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(
                userDetailsService(), jwtService());
    }

    @Bean
    public JsonAuthenticationRequestFilter jsonAuthenticationRequestFilter() throws Exception {
        JsonAuthenticationRequestFilter jsonAuthenticationRequestFilter = new JsonAuthenticationRequestFilter();
        jsonAuthenticationRequestFilter.setFilterProcessesUrl("/api/login");
        jsonAuthenticationRequestFilter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler());
        jsonAuthenticationRequestFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler());
        jsonAuthenticationRequestFilter.setAuthenticationManager(authenticationManager());
        return jsonAuthenticationRequestFilter;
    }

    @Bean
    public RestAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler(jwtService());
    }

    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }
}
