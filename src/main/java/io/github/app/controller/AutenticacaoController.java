package io.github.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.app.model.user.Usuario;
import io.github.app.model.user.UsuarioDTORead;
import io.github.app.security.TokenJWTDTO;
import io.github.app.security.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenJWTDTO> postLogin(@RequestBody @Valid UsuarioDTORead usuarioDTORead){
		var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTORead.login(), usuarioDTORead.senha());
		var auth = manager.authenticate(authenticationToken);
		var tokenJWT = tokenService.generateToken((Usuario) auth.getPrincipal());
		return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
	}
}
