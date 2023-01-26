package br.serratec.dev.pa.controller;
// package br.serratec.t2m.sga.controller;

// import java.net.URI;
// import java.util.List;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// import br.serratec.t2m.sga.model.MovimentoItens;
// import br.serratec.t2m.sga.service.MovimentoItensService;

// @RestController
// @RequestMapping("/api/movimentoItens")
// public class MovimentoItensController {

//     @Autowired
// 	private MovimentoItensService movimentoItensService;

//     @GetMapping
//     public ResponseEntity<List<MovimentoItens>> obterTodos() {
//         List<MovimentoItens> movimentoItenss = movimentoItensService.obterTodos();
//         return ResponseEntity.ok(movimentoItenss);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<MovimentoItens> obterPorId(@PathVariable Long id) {
//         MovimentoItens movimentoItens = movimentoItensService.obterPorId(id);
//         return ResponseEntity.ok(movimentoItens);
//     }

//     @PostMapping
// 	public ResponseEntity<MovimentoItens> cadastrar(@Valid @RequestBody MovimentoItens movimentoItensRecebido) {
// 		MovimentoItens movimentoItens = movimentoItensService.cadastrar(movimentoItensRecebido);
// 		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(movimentoItens.getIdMovimentoIten())
// 				.toUri();
// 		return ResponseEntity.created(uri).body(movimentoItens);
// 	}

//     @PutMapping("/{id}")
//     public ResponseEntity<MovimentoItens> atualizar(@PathVariable Long id, @Valid @RequestBody MovimentoItens movimentoItens) {
//         return ResponseEntity.ok(movimentoItensService.atualizar(id, movimentoItens));
//     }

//     @DeleteMapping("/{id}")
// 	public ResponseEntity<Void> deletar(@PathVariable Long id) {
// 		movimentoItensService.deletar(id);
// 		return ResponseEntity.noContent().build();
// 	}
    
// }
