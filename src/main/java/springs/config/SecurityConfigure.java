package springs.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sun.org.apache.bcel.internal.generic.NOP;

import springs.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService service;

	@Bean
	protected AuthenticationProvider autProvider() throws Exception {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(service);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().antMatchers("/").
		permitAll().and()
		.formLogin().loginProcessingUrl("/login").defaultSuccessUrl("/home/welcome")
		.permitAll().and().rememberMe().userDetailsService(service).tokenValiditySeconds(200).key("abc").and()
		.logout().invalidateHttpSession(true).clearAuthentication(true).
		logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home/clogout").permitAll();
	}

	
	
	
	/*
	  @Bean
	  
	  @Override protected UserDetailsService userDetailsService() {
	 
	  List<UserDetails> li=new ArrayList<>();
	  li.add(User.withDefaultPasswordEncoder().username("harsh").password("12345").
	  roles("USER").build());
	  li.add(User.withDefaultPasswordEncoder().username("admin").password(
	  "admin123").roles("ADMIN").build()); return new
	  InMemoryUserDetailsManager(li); }
	 */

    

	
}
