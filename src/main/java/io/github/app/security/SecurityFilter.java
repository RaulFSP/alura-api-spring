package io.github.app.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.app.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var token = getToken(request);
		if (token != null) {
			try {
				
				var subject = tokenService.getSubject(token);
				var usuario = usuarioRepository.findByLogin(subject);
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			} catch (RuntimeException e) {
				System.out.println("Token deu ruim: " + e.getMessage());
			}
		}
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		var auth = request.getHeader("Authorization");
		if (auth != null) {

			return auth.replace("Bearer ", "").trim();
		}
		return null;
	}

}
