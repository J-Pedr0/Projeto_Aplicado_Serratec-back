package br.serratec.dev.pa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import br.serratec.dev.pa.dto.LoginResponseDTO;
import br.serratec.dev.pa.dto.UserTokenDTO;
import br.serratec.dev.pa.service.AuthService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	AuthService authService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String senha = authentication.getCredentials().toString();
		try {
			LoginResponseDTO response = authService.login(email, senha);
			return new CustomUsernamePasswordAuthenticationToken(email, senha, new UserTokenDTO(response));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;//authentication.equals(CustomUsernamePasswordAuthenticationToken.class);
	}

	

}
