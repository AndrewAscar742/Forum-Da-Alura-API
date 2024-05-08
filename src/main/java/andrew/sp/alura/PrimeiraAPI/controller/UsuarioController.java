package andrew.sp.alura.PrimeiraAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import andrew.sp.alura.PrimeiraAPI.controller.dto.UsuarioDTO;
import andrew.sp.alura.PrimeiraAPI.model.Usuario;
import andrew.sp.alura.PrimeiraAPI.repository.UsuarioRepository;
import andrew.sp.alura.PrimeiraAPI.services.TokenService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	//Essa clase é responsável pelo gerenciamento da authenticação do usuário
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/login")
	public ResponseEntity<String> logar(@RequestBody UsuarioDTO usuario) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario.email(), usuario.senha());
		
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		String token = tokenService.createToken((Usuario) authenticate.getPrincipal());
		
		return ResponseEntity.ok("Token: " + token);
	}
}
