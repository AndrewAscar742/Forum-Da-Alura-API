package andrew.sp.alura.PrimeiraAPI.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import andrew.sp.alura.PrimeiraAPI.model.Topico;

public class TopicosDto {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	public TopicosDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}
	
	public Long getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<TopicosDto> converter(List<Topico> topicos) {
		// TODO Auto-generated method stub
		return topicos.stream().map(TopicosDto::new).collect(Collectors.toList());
	}
	
	
}
