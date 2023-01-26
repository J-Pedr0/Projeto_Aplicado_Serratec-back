package br.serratec.dev.pa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.serratec.dev.pa.dto.UserDTO;
import br.serratec.dev.pa.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	AuthService authService;

	@ApiOperation(value = "Retorna todos os usuarios", notes = "Usuario")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna todos os usuarios"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"),
	})
	@GetMapping
	public ResponseEntity<List<UserDTO>> listar() {
		return ResponseEntity.ok(authService.getUser());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> pegar(@PathVariable String id) {
		return ResponseEntity.ok(authService.getUser(id));
	}

}
