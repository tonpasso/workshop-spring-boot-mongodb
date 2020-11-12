package com.detonadev.workshopmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.detonadev.workshopmongodb.domain.Post;
import com.detonadev.workshopmongodb.domain.User;
import com.detonadev.workshopmongodb.dto.AuthorDTO;
import com.detonadev.workshopmongodb.repository.PostRepository;
import com.detonadev.workshopmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		date.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, date.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, date.parse("23/05/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		Post post3 = new Post(null, date.parse("25/09/2018"), "Detona Dev", "Iniciando mais um projeto Dev", new AuthorDTO(alex));
		Post post4 = new Post(null, date.parse("15/11/2018"), "Grande Jogo", "Final muito disputada", new AuthorDTO(bob));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		alex.getPosts().addAll(Arrays.asList(post3));
		bob.getPosts().addAll(Arrays.asList(post4));
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));	
		
	}

}
