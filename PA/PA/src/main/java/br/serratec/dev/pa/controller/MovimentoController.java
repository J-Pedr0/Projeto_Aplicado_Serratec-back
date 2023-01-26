package br.serratec.dev.pa.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.serratec.dev.pa.dto.MovimentoDTO;
import br.serratec.dev.pa.model.Movimento;
import br.serratec.dev.pa.service.MovimentoService;

@RestController
@RequestMapping("/api/movimento")
public class MovimentoController {

    @Autowired
	private MovimentoService movimentoService;

    @GetMapping
    public ResponseEntity<List<Movimento>> obterTodos() {
        List<Movimento> movimentos = movimentoService.obterTodos();
        return ResponseEntity.ok(movimentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimento> obterPorId(@PathVariable Long id) {
        Movimento movimento = movimentoService.obterPorId(id);
        return ResponseEntity.ok(movimento);
    }

    // @PostMapping
	// public ResponseEntity<Movimento> cadastrar(@Valid @RequestBody Movimento movimentoRecebido) {
	// 	Movimento movimento = movimentoService.cadastrar(movimentoRecebido);
	// 	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movimento.getIdMovimento())
	// 			.toUri();
	// 	return ResponseEntity.created(uri).body(movimento);
	// }

    @PostMapping
	public ResponseEntity<Movimento> inserirMovimento(@Valid @RequestBody MovimentoDTO movimentoDTO) {
		Movimento movimento = movimentoService.inserirMovimento(movimentoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movimento.getIdMovimento())
				.toUri();
		return ResponseEntity.created(uri).body(movimento);
	}

    @PutMapping("/{id}")
    public ResponseEntity<Movimento> atualizar(@PathVariable Long id, @Valid @RequestBody Movimento movimento) {
        return ResponseEntity.ok(movimentoService.atualizar(id, movimento));
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		movimentoService.deletar(id);
		return ResponseEntity.noContent().build();
	}
    
    // @GetMapping
    // public ResponseEntity<List<HistoricoMovimentoIdAtivo>> obterTodos() {
    //     List<Movimento> movimentos = movimentoService.obterTodos();
    //     return ResponseEntity.ok(movimentos);
    // }

}
