package andrew.sp.alura.PrimeiraAPI.controller.dto;

import java.time.LocalDateTime;

import andrew.sp.alura.PrimeiraAPI.model.Resposta;

public class RespostaDto {
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String nomeAutor;

	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.nomeAutor = resposta.getAutor().getNome();
		
	}
}
