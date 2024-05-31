package com.scratch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:custom.properties")
public class MyFirstService {

	private Environment environment;
	@Autowired
	private MyFirstClass myFirstClass;
	@Value("${my.prop}")
	private String customProperty;
	
	@Value("${my.custom.property.int}")
	private int myCustomPropertyInt;
	
	@Autowired
	public int getMyCustomPropertyInt() {
		return myCustomPropertyInt;
	}

	public String getCustomProperty() {
		return customProperty;
	}

	public String tellAStory() {
		return "the dependency saying : "+ myFirstClass.sayHello();
	}
	
	public String getJavaVersion() {
		return environment.getProperty("java.version");
	}
	
	public String getOsName() {
		return environment.getProperty("os.name");
	}

	@Autowired
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	
	
	
}
