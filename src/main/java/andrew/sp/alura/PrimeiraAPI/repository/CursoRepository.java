package andrew.sp.alura.PrimeiraAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import andrew.sp.alura.PrimeiraAPI.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	Curso findByNome(String nomeCurso);

}
