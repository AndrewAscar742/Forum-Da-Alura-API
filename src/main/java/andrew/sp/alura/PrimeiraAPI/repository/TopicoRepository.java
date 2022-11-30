package andrew.sp.alura.PrimeiraAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import andrew.sp.alura.PrimeiraAPI.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
	
	//Padrão de nomenclatura do SpringBoot
	List<Topico> findByCursoNome(String nomeCurso);
	
	//Select feito na mão, orientado a objeto
	@Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
    List<Topico> carregarPorNomeDoCurso(@Param("nomeCurso") String nomeCurso);
	
	
}
