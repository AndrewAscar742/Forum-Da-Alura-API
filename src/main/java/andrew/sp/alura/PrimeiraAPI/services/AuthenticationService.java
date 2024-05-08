package andrew.sp.alura.PrimeiraAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import andrew.sp.alura.PrimeiraAPI.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return usuarioRepository.findByEmail(username);
	}

}
