package br.serratec.dev.pa.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.serratec.dev.pa.dto.AtivoDTO;
import br.serratec.dev.pa.dto.AtivoManutencaoDTO;
import br.serratec.dev.pa.model.Ativo;
import br.serratec.dev.pa.service.AtivoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")

@RestController
@RequestMapping("/api/ativo")
public class AtivoController {

    @Autowired
	private AtivoService ativoService;

    
    @ApiOperation(value="Retorna todos os ativos", notes="Listagem de Ativo")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna todos os ativos"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
    @GetMapping
    public ResponseEntity<List<Ativo>> obterTodos() {
        List<Ativo> ativos = ativoService.obterTodos();
        return ResponseEntity.ok(ativos);
    }

    @ApiOperation(value="Retorna um ativo por id ", notes="Ativo")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Retorna um ativo por id"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
    @GetMapping("/{id}")
    public ResponseEntity<AtivoDTO> obterPorId(@PathVariable Long id) {
        AtivoDTO ativo = ativoService.obterPorId(id);
        return ResponseEntity.ok(ativo);
    }

    @ApiOperation(value="Insere um novo ativo", notes="Inserir Ativo")
	@ApiResponses(value= {
	@ApiResponse(code=201, message="Ativo inserido com sucesso"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
    @PostMapping
	public ResponseEntity<Ativo> cadastrar(@Valid @RequestBody AtivoDTO ativoRecebido) {
		Ativo ativo = ativoService.cadastrar(ativoRecebido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ativo.getIdAtivo())
				.toUri();
		return ResponseEntity.created(uri).body(ativo);
	}

    @ApiOperation(value="Atualiza dados de um ativo", notes="Atualizar Ativo")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Ativo Atualizado"),
	@ApiResponse(code=201, message="Ativo Atualizado"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
    @PutMapping("/{id}")
    public ResponseEntity<Ativo> atualizar(@PathVariable Long id, @Valid @RequestBody AtivoDTO ativoDTO) {
        return ResponseEntity.ok(ativoService.atualizar(id, ativoDTO));
    }

    @ApiOperation(value="Exclui um ativo", notes="Remover Ativo")
	@ApiResponses(value= {
	@ApiResponse(code=200, message="Ativo Removido"),
	@ApiResponse(code=401, message="Erro de autenticação"),
	@ApiResponse(code=403, message="Não há permissão para acessar o recurso"),
	@ApiResponse(code=404, message="Recurso não encontrado"),
	@ApiResponse(code=505, message="Exceção interna da aplicação"),
	})
    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		ativoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/listarAtivoManutencao")
	public List<AtivoManutencaoDTO>listaEquipamentos(){
        return ativoService.listaEquipamentos();
    }

	@GetMapping("/listarInativoManutencao")
	public List<AtivoManutencaoDTO>listaEquipamentosInativo(){
        return ativoService.listaEquipamentosInativo();
    }

	@PutMapping("/deleteLogico/{id}")
    public ResponseEntity<Ativo> deleteLogico(@PathVariable Long id) {
        return ResponseEntity.ok(ativoService.deleteLogico(id));
    }

	@PutMapping("/ativacaoAtivo/{id}")
    public ResponseEntity<Ativo> ativacaoAtivo(@PathVariable Long id) {
        return ResponseEntity.ok(ativoService.ativacaoAtivo(id));
    }

}
