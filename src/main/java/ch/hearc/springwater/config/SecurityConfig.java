package ch.hearc.springwater.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordencoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordencoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers("/admin/").authenticated().and().formLogin();
		http
			.authorizeRequests()
				.antMatchers("/h2/**").permitAll()
			.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
			.and()
			.authorizeRequests()
				.antMatchers("/admin/").hasRole("ADMIN")
				.and()
				.formLogin();

		http.headers().frameOptions().disable();
	}
}
