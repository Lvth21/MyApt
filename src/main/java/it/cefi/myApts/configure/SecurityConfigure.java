package it.cefi.myApts.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration//.csrf().disable()
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
		.authorizeRequests().antMatchers("/", "/signin").permitAll()
		.antMatchers("/insert").hasAuthority("USER")
		.antMatchers("/insert-rem").hasAuthority("USER")
		.antMatchers("/view").hasAuthority("USER")
		.antMatchers("/view-reminders").hasAuthority("USER")
		.anyRequest().authenticated().and().
		formLogin().loginPage("/login").permitAll()
		.and().logout().permitAll()
		.and().exceptionHandling().accessDeniedPage("/403");
		
	}
	
	@Bean
	public BCryptPasswordEncoder codifica() { //va a vedere il metodo che abbiamo messo nella cofigureUser
		return new BCryptPasswordEncoder();
	}
}
