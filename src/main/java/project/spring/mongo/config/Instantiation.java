package project.spring.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import project.spring.mongo.dto.AuthorDTO;
import project.spring.mongo.dto.CommentDTO;
import project.spring.mongo.model.Post;
import project.spring.mongo.model.User;
import project.spring.mongo.repository.PostRepository;
import project.spring.mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.save(Arrays.asList(maria, alex, bob));
		
		Post post = new Post(null, sdf.parse("22/07/2019"), "Partiu ferias", "Vou para os EUA", new AuthorDTO(maria));
		Post post1 = new Post(null, sdf.parse("23/07/2019"), "Agora vai", "Elevando o nivel",  new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Boa viajem !", sdf.parse("22/07/2019"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite !", sdf.parse("22/07/2019"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um otimo dia !", sdf.parse("22/07/2019"), new AuthorDTO(alex));
		
		post.getComments().addAll(Arrays.asList(c1, c2));
		post1.getComments().addAll(Arrays.asList(c3));
		
		postRepository.save(Arrays.asList(post, post1));
		
		maria.getPosts().addAll(Arrays.asList(post, post1));
		userRepository.save(maria);
	}	

}
