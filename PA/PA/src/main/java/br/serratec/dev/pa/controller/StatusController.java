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

import br.serratec.dev.pa.model.Status;
import br.serratec.dev.pa.service.StatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
	private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> obterTodos() {
        List<Status> statuss = statusService.obterTodos();
        return ResponseEntity.ok(statuss);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> obterPorId(@PathVariable Long id) {
        Status status = statusService.obterPorId(id);
        return ResponseEntity.ok(status);
    }

    @PostMapping
	public ResponseEntity<Status> cadastrar(@Valid @RequestBody Status statusRecebido) {
		Status status = statusService.cadastrar(statusRecebido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(status.getIdStatus())
				.toUri();
		return ResponseEntity.created(uri).body(status);
	}

    @PutMapping("/{id}")
    public ResponseEntity<Status> atualizar(@PathVariable Long id, @Valid @RequestBody Status status) {
        return ResponseEntity.ok(statusService.atualizar(id, status));
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		statusService.deletar(id);
		return ResponseEntity.noContent().build();
	}
    
}
