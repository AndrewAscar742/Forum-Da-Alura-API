package andrew.sp.alura.PrimeiraAPI.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import andrew.sp.alura.PrimeiraAPI.repository.UsuarioRepository;
import andrew.sp.alura.PrimeiraAPI.services.TokenService;


@Component
public class SecurityFilter extends OncePerRequestFilter{
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = receiveToken(request);
		
		if (token != null) {
			String emailSubject = tokenService.validateToken(token);
			UserDetails userDetails = userRepository.findByEmail(emailSubject);
			
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

	private String receiveToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if (token != null) {
			token = token.replace("Bearer ", "");
			return token;
		}
		
		return null;
	}

}
