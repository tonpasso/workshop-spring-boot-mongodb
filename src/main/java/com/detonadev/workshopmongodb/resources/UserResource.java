package com.detonadev.workshopmongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.detonadev.workshopmongodb.domain.User;
import com.detonadev.workshopmongodb.dto.UserDTO;
import com.detonadev.workshopmongodb.services.UserService;

@RestController //  Classe Controlador REST
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>>  findAll(){
		List<User> lista = service.findAll();
		List<UserDTO> listaDto = lista.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);
	}

}
