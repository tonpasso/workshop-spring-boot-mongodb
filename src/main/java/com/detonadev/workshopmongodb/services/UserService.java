package com.detonadev.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.detonadev.workshopmongodb.domain.User;
import com.detonadev.workshopmongodb.dto.UserDTO;
import com.detonadev.workshopmongodb.repository.UserRepository;
import com.detonadev.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User novoObj = findById(obj.getId());
		updateData(novoObj, obj);
		return repo.save(novoObj);
	}
	
	private void updateData(User novoObj, User obj) {
		novoObj.setName(obj.getName());
		novoObj.setEmail(obj.getEmail());		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}


