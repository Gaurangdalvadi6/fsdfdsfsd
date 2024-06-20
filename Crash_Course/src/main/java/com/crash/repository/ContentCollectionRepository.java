package com.crash.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.crash.model.Content;
import com.crash.model.Status;
import com.crash.model.Type;

import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository {

	
	private final List<Content> content = new ArrayList<>();
	
	public ContentCollectionRepository() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Content> findAll(){
		return content;
	}
	
	public Optional<Content> findById(Integer id){
		return content.stream().filter(c -> c.id().equals(id)).findFirst();
	}
	
	@PostConstruct
	public void init() {
		Content c = new Content(1,
				"Gaurang",
				"Java Developer",
				Status.IDEA,
				Type.ARTICLE,
				LocalDateTime.now(),
				null,
				"");
		content.add(c);
		
	}

	public void save(Content content2) {
		content.add(content2);
		
	}
}
