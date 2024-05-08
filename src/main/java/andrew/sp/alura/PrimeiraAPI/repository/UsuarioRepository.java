package andrew.sp.alura.PrimeiraAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import andrew.sp.alura.PrimeiraAPI.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	UserDetails findByEmail(String email);
}
