package io.github.app.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import io.github.app.model.user.Usuario;

@Service
public class TokenService {

	@Value("{api.security.token.xpto}")
	private String XPTO;
	
	public String generateToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(XPTO);
			return JWT
					.create()
					.withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId())
					.withIssuer("Api Voll.med")
					.withExpiresAt(expires())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar token ",e);
		}
	}
	
	private Instant expires(){
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
