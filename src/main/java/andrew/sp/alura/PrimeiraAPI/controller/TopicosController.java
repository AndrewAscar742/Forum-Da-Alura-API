package andrew.sp.alura.PrimeiraAPI.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import andrew.sp.alura.PrimeiraAPI.controller.dto.TopicoForm;
import andrew.sp.alura.PrimeiraAPI.controller.dto.TopicosDto;
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
	
	@PostMapping //No Spring, quando se trata de POST, recebemos os parâmetros pelo o corpo da Requisição
	public ResponseEntity<TopicosDto> cadastrarTopico(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.converter(cursoRep);
		//Isso salva a entidade
		topicoRep.save(topico);
		/*
		 * URI: http://localhost:8080/recursos
		 * {id} = um parâmetro, será volátil
		 * buildAndExpand = ele vai substituir o parâmetro
		 * toUri = Transformando em uma URI
		 */
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicosDto(topico));
	}
}
