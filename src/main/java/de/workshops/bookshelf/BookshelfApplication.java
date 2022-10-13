package de.workshops.bookshelf;

import com.fasterxml.jackson.databind.util.ClassUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class BookshelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookshelfApplication.class, args);
	}

//	@Bean
//	ApplicationListener<ApplicationReadyEvent> contextListener (ApplicationContext context) {
//		return new ApplicationListener<ApplicationReadyEvent>() {
//			@Override
//			public void onApplicationEvent(ApplicationReadyEvent event) {
//				final var beanDefinitionNames = context.getBeanDefinitionNames();
//				Arrays.stream(beanDefinitionNames).forEach(name -> System.out.println(name));
//			}
//		};
//		return event -> {
//			final var beanDefinitionNames = context.getBeanDefinitionNames();
//			Arrays.stream(beanDefinitionNames).forEach(name -> System.out.println(name));
//		};
//	}
}
