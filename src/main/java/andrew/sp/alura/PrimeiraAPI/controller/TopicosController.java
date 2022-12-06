package andrew.sp.alura.PrimeiraAPI.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
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
import org.springframework.web.util.UriComponentsBuilder;

import andrew.sp.alura.PrimeiraAPI.controller.dto.DetalhesTopicoDto;
import andrew.sp.alura.PrimeiraAPI.controller.dto.TopicosDto;
import andrew.sp.alura.PrimeiraAPI.controller.form.AtualizarTopicoForm;
import andrew.sp.alura.PrimeiraAPI.controller.form.TopicoForm;
import andrew.sp.alura.PrimeiraAPI.model.Topico;
import andrew.sp.alura.PrimeiraAPI.repository.CursoRepository;
import andrew.sp.alura.PrimeiraAPI.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos") // cria o endpoint para diversos verbos
public class TopicosController {

	@Autowired // Injeção de dependências
	private TopicoRepository topicoRep;

	@Autowired // Injeção de dependências
	private CursoRepository cursoRep;

	@GetMapping
	public List<TopicosDto> printarLista(String nomeCurso) {

		if (nomeCurso == null) {
			List<Topico> topicos = topicoRep.findAll();
			return TopicosDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRep.carregarPorNomeDoCurso(nomeCurso);
			return TopicosDto.converter(topicos);
		}
	}

	@PostMapping // No Spring, quando se trata de POST, recebemos os parâmetros pelo o corpo da
					// Requisição
	@Transactional
	public ResponseEntity<TopicosDto> cadastrarTopico(@RequestBody @Valid TopicoForm form,
			UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRep);
		// Isso salva a entidade
		topicoRep.save(topico);
		/*
		 * URI: http://localhost:8080/recursos {id} = um parâmetro, será volátil
		 * buildAndExpand = ele vai substituir o parâmetro toUri = Transformando em uma
		 * URI
		 */

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicosDto(topico));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalharTopicoDto(@PathVariable Long id) {
		Optional<Topico> topico = topicoRep.findById(id);
		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@Transactional // depois de realizar o comportamento do método, deve commitar
	public ResponseEntity<TopicosDto> atualizarTopico(Long id, @RequestBody @Valid AtualizarTopicoForm form) {
		Optional<Topico> optional = topicoRep.findById(id);
		if (optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRep);
			return ResponseEntity.ok(new TopicosDto(topico));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Topico> optional = topicoRep.findById(id);
		if (optional.isPresent()) {
			topicoRep.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
