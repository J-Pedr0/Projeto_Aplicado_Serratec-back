package br.serratec.dev.pa.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.serratec.dev.pa.dto.LoginResponseDTO;
import br.serratec.dev.pa.dto.UserDTO;
import br.serratec.dev.pa.dto.UserTokenDTO;
import br.serratec.dev.pa.security.CustomUsernamePasswordAuthenticationToken;

@Service
public class AuthService {
	
	@Value("${app.endereco-auth}")
	private String enderecoAuth;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public LoginResponseDTO login(String email, String senha) {
		
		Map<String,String> dataPost = new HashMap<>();
		dataPost.put("email", email);
		dataPost.put("password", senha);
		HttpEntity<Map<String,String>> request = new HttpEntity<>(dataPost);
		LoginResponseDTO resp = restTemplate.postForObject(enderecoAuth+"/api/login", request, LoginResponseDTO.class);
		return resp;
		
	}
	
	
	public List<UserDTO> getUser() {
		CustomUsernamePasswordAuthenticationToken userDetails = (CustomUsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		String token = userDetails.getUserTokenDTO().getLoginResponseDTO().getToken();
		HttpHeaders headers = new  HttpHeaders();
		headers.set("Authorization", "Bearer "+ token);
		DataResponse<List<UserDTO>> listaUsuarios = new DataResponse<>();
		
		HttpEntity<List<UserDTO>> request = new HttpEntity<>(null, headers);
		ResponseEntity<? extends DataResponse> resp = restTemplate.exchange(enderecoAuth+"/api/User", HttpMethod.GET, request,  listaUsuarios.getClass());
		listaUsuarios = resp.getBody();
		return listaUsuarios.getData();
	}
	
	public UserDTO getUser(String id) {
		CustomUsernamePasswordAuthenticationToken userDetails = (CustomUsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		String token = userDetails.getUserTokenDTO().getLoginResponseDTO().getToken();
		HttpHeaders headers = new  HttpHeaders();
		headers.set("Authorization", "Bearer "+ token);
		DataResponse<UserDTO> listaUsuarios = new DataResponse<>();
		
		HttpEntity<UserDTO> request = new HttpEntity<>(null, headers);
		ResponseEntity<DataResponse<UserDTO>> resp = restTemplate.exchange(enderecoAuth+"/api/User/"+id, HttpMethod.GET, request,  new ParameterizedTypeReference<DataResponse<UserDTO>>() {});
		listaUsuarios = resp.getBody();
		return listaUsuarios.getData();
	}

	
	public void getUserActive() {
		
	}
	
	public void postUSer() {
		
	}
	public void putUSer() {
		
	}
	

	private UserTokenDTO getUserTokenDTO() {
		return ((CustomUsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getUserTokenDTO();
	}
}
