package application.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * SecurityConfiguration.
 * <p>
 * Created by Adam_Skowron on 08.08.2016.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/").permitAll();
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/movies").hasRole("USER")
                .antMatchers("/newmovie").hasRole("ADMIN")
            .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/")
            .and()
                .csrf()
            .and()
                .logout().permitAll()
                // allows to perform logout action without csrf and via GET
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password("user").roles("USER");

        auth.inMemoryAuthentication()
            .withUser("admin").password("admin").roles("USER", "ADMIN");
    }
}
