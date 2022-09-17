package com.pfa.LeaveManagementSystem.config.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.pfa.LeaveManagementSystem.utils.constant.*;


@Configuration  @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JWTAuthenticationFilter jwtFilter = new JWTAuthenticationFilter(authenticationManagerBean());
        jwtFilter.setFilterProcessesUrl(appRoot+"/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests()

        // Our public endpoints
        .antMatchers(
                appRoot+"/login/**",
                USERS_ENDPOINT+"/refreshtoken",
                // -- Swagger UI v2
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**").permitAll()

        // Our private endpoints
        .antMatchers(
                USERS_ENDPOINT+"/all",
                USERS_ENDPOINT+"/createUser",
                USERS_ENDPOINT+"/createRole",
                USERS_ENDPOINT+"/addRole",
                 EMPLOYES_ENDPOINT+"/create/{idManager}",
                 EMPLOYES_ENDPOINT+"/delete/{idEmploye}",
                 EMPLOYES_ENDPOINT+"id/{idEmploye}",
                 EMPLOYES_ENDPOINT+"name/{nom}").hasAuthority("ROLE_ADMIN")


        .antMatchers(
                CONGES_ENDPOINT+"/create",
                CONGES_ENDPOINT+"/update",
                CONGES_ENDPOINT+"/cancel/{idConge}",
                EMPLOYES_ENDPOINT+"/update",
                EMPLOYES_ENDPOINT+"listConges/{idEmploye}").hasAuthority("ROLE_EMPLOYE")


        .antMatchers(
                CONGES_ENDPOINT+"/accept/{idConge}",
                CONGES_ENDPOINT+"/refuse/{idConge}",
                CONGES_ENDPOINT+"/historique/{type}",
                EMPLOYES_ENDPOINT+"id/{idEmploye}",
                EMPLOYES_ENDPOINT+"name/{nom}",
                EMPLOYES_ENDPOINT+"list/{idManager}").hasAuthority("ROLE_MANAGER")


        .anyRequest().authenticated();
        http.addFilter(jwtFilter);
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean() ;
    }
}
