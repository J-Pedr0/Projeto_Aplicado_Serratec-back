package br.serratec.dev.pa.security;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.serratec.dev.pa.dto.UserTokenDTO;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken implements UserDetails{
	private static final long serialVersionUID = 1L;

	private UserTokenDTO userTokenDTO;

	public CustomUsernamePasswordAuthenticationToken(String username, String password, UserTokenDTO userTokenDTO) {
		super(username, password, Arrays.asList(new SimpleGrantedAuthority(userTokenDTO.getRole())));
		this.userTokenDTO = userTokenDTO;
		
		
	}

	public UserTokenDTO getUserTokenDTO() {
		return userTokenDTO;
	}

	public void setUserTokenDTO(UserTokenDTO userTokenDTO) {
		this.userTokenDTO = userTokenDTO;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userTokenDTO.getCorporativeEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

	
	
	

}
