package br.serratec.dev.pa.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.serratec.dev.pa.security.CustomAuthenticationProvider;
import br.serratec.dev.pa.security.JwtAuthenticationFilter;
import br.serratec.dev.pa.security.JwtAuthorizationFilter;
import br.serratec.dev.pa.security.JwtUtil;

@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomAuthenticationProvider authProvider;

	@Autowired
	JwtUtil jwtUtil;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().antMatchers("/").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/api/ativo", "/api/marca", "/api/movimento").permitAll()
				.antMatchers(HttpMethod.GET, "/api/ativo", "/api/marca", "/api/movimento").permitAll()
				.antMatchers(HttpMethod.GET, "/api/colaborador").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.GET, "/api/pedido").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, "/api/pedido").hasAnyAuthority("ADMIN", "USER")
				.antMatchers(HttpMethod.POST, "/api/categoria", "/api/produto").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/categoria", "/api/produto").hasAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/categoria", "/api/produto", "/api/pedido").hasAuthority("ADMIN")
				.anyRequest().authenticated().and().httpBasic().and().cors().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilter(new JwtAuthenticationFilter(this.authenticationManager(), jwtUtil));
		http.addFilter(new JwtAuthorizationFilter(this.authenticationManager(), jwtUtil));
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.cors().and().csrf().disable().exceptionHandling().and().sessionManagement()
	// .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().anyRequest()
	// .permitAll();
	// }

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
	}

}