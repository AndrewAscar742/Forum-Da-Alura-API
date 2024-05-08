package andrew.sp.alura.PrimeiraAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import andrew.sp.alura.PrimeiraAPI.model.Usuario;
import andrew.sp.alura.PrimeiraAPI.repository.UsuarioRepository;

@Configuration
public class DataSqlConfiguration {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Bean
	public void updateUsers() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		Usuario usuario = new Usuario("Aluno", "aluno@email.com", bCryptPasswordEncoder.encode("123456"));
		
		usuarioRepository.save(usuario);
	}
}
