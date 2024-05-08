package andrew.sp.alura.PrimeiraAPI.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;

import andrew.sp.alura.PrimeiraAPI.model.Usuario;

@Service
public class TokenService {
	
	public String createToken(Usuario usuario) {
		Algorithm algorithm = Algorithm.HMAC256("Forum-API-Alura");
		
		try {
			return JWT.create()
					.withIssuer("Forum-API-Alura")
					.withSubject(usuario.getEmail())
					.withExpiresAt(getExpiresToken())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new JWTCreationException("Erro durante a criação do token", e);
		}
		
		
	}
	
	public String validateToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256("Forum-API-Alura");
		
		try {
			return JWT.require(algorithm)
					.withIssuer("Forum-API-Alura")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTDecodeException e) {
			throw new JWTDecodeException("Erro durante a validação do Token", e);
		}
		
		
	}

	private Instant getExpiresToken() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
