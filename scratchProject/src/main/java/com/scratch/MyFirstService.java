package com.scratch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyFirstService {

	@Autowired
	private MyFirstClass myFirstClass;
	

	public String tellAStory() {
		return "the dependency saying : "+ myFirstClass.sayHello();
	}
}
