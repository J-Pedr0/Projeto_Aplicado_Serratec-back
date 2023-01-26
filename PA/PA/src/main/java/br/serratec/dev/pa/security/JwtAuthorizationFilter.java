package br.serratec.dev.pa.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;

import br.serratec.dev.pa.dto.UserTokenDTO;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	   private JwtUtil jwtUtil;
	   public JwtAuthorizationFilter(AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
	       super(authenticationManager);
	       this.jwtUtil=jwtUtil;
	   }
	   @Override
	   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	           throws IOException, ServletException {
	       String header = request.getHeader("Authorization");
	       if(header!=null && header.startsWith("Bearer ")) {
	    	   CustomUsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7));
	           if (auth!=null) {
	        	   SecurityContext context = SecurityContextHolder.createEmptyContext();
					context.setAuthentication(auth);
					SecurityContextHolder.setContext(context);
	               //SecurityContextHolder.getContext().setAuthentication(auth);
	           }
	       }
	       chain.doFilter(request, response);
	   }
	   private CustomUsernamePasswordAuthenticationToken getAuthentication(String token) {
	       if (jwtUtil.isValidToken(token)) {
	    	   LinkedTreeMap data = (LinkedTreeMap) jwtUtil.getUserData(token);
	    	   JsonObject jsonObject = new Gson().toJsonTree(data).getAsJsonObject();
	    	   UserTokenDTO userData = new Gson().fromJson(jsonObject, UserTokenDTO.class);
	           return new CustomUsernamePasswordAuthenticationToken(userData.getCorporativeEmail(), null, userData);
	       }
	       return null;
	   }
	}
