package andrew.sp.alura.PrimeiraAPI.controller.dto;

import andrew.sp.alura.PrimeiraAPI.model.Curso;
import andrew.sp.alura.PrimeiraAPI.model.Topico;
import andrew.sp.alura.PrimeiraAPI.repository.CursoRepository;

public class TopicoForm {
	private String titulo;
	private String mensagem;
	private String nomeCurso;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converter(CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, null);
	}

}
