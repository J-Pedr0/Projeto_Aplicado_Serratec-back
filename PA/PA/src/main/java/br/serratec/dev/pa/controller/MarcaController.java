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

import br.serratec.dev.pa.model.Marca;
import br.serratec.dev.pa.service.MarcaService;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    @Autowired
	private MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> obterTodos() {
        List<Marca> marcas = marcaService.obterTodos();
        return ResponseEntity.ok(marcas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> obterPorId(@PathVariable Long id) {
        Marca marca = marcaService.obterPorId(id);
        return ResponseEntity.ok(marca);
    }

    @PostMapping
	public ResponseEntity<Marca> cadastrar(@Valid @RequestBody Marca marcaRecebido) {
		Marca marca = marcaService.cadastrar(marcaRecebido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(marca.getIdMarca())
				.toUri();
		return ResponseEntity.created(uri).body(marca);
	}

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id, @Valid @RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.atualizar(id, marca));
    }

    @DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		marcaService.deletar(id);
		return ResponseEntity.noContent().build();
	}
    
}
