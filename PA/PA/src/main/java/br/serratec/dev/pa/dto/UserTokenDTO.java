package br.serratec.dev.pa.dto;

import java.io.IOException;
import java.util.Base64;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.internal.LinkedTreeMap;

public class UserTokenDTO {
	private String id;
	private String fullName;
	private String corporativeEmail;
	private String personalEmail;
	private String phone;
	private String cpf;
	private String birthDate;
	private String admissionDate; 
	private String role;
	private Long nbf;
	private Long exp;
	private Long iat;
	private LoginResponseDTO loginResponseDTO;
	
	public UserTokenDTO(LoginResponseDTO  loginResponseDTO) throws IOException {
		String token = loginResponseDTO.getToken();
		int posStart = token.indexOf(".");
		int posEnd = token.indexOf(".", posStart+1);
		
		String tk = token.substring(posStart+1, posEnd);
		byte[] jsonByte = Base64.getDecoder().decode(tk);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(jsonByte);
		setId(json.get("Id").asText());
		setFullName(json.get("FullName").asText());
		setCorporativeEmail(json.get("CorporativeEmail").asText());
		setPersonalEmail(json.get("PersonalEmail").asText());
		setPhone(json.get("Phone").asText());
		setCpf(json.get("Cpf").asText());
		setBirthDate(json.get("BirthDate").asText());
		setAdmissionDate(json.get("AdmissionDate").asText());
		setRole(json.get("Role").asText());
		setNbf(json.get("nbf").asLong());
		setExp(json.get("exp").asLong());
		setIat(json.get("iat").asLong());
		this.loginResponseDTO = loginResponseDTO;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCorporativeEmail() {
		return corporativeEmail;
	}
	public void setCorporativeEmail(String corporativeEmail) {
		this.corporativeEmail = corporativeEmail;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(String admissionDate) {
		this.admissionDate = admissionDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Long getNbf() {
		return nbf;
	}
	public void setNbf(Long nbf) {
		this.nbf = nbf;
	}
	public Long getExp() {
		return exp;
	}
	public void setExp(Long exp) {
		this.exp = exp;
	}
	public Long getIat() {
		return iat;
	}
	public void setIat(Long iat) {
		this.iat = iat;
	}

	public LoginResponseDTO getLoginResponseDTO() {
		return loginResponseDTO;
	}

	public void setLoginResponseDTO(LoginResponseDTO loginResponseDTO) {
		this.loginResponseDTO = loginResponseDTO;
	}
	
	
}
