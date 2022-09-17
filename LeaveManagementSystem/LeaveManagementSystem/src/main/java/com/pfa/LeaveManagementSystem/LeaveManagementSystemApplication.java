package com.pfa.LeaveManagementSystem;
import com.pfa.LeaveManagementSystem.model.Employe;
import com.pfa.LeaveManagementSystem.model.Role;
import com.pfa.LeaveManagementSystem.model.SoldeConge;
import com.pfa.LeaveManagementSystem.model.Utilisateur;
import com.pfa.LeaveManagementSystem.service.EmployeService;
import com.pfa.LeaveManagementSystem.service.SoldeCongeService;
import com.pfa.LeaveManagementSystem.service.UtilisateurService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.ArrayList;

@SpringBootApplication
public class LeaveManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(LeaveManagementSystemApplication.class, args);
	      }
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
				= new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

/*
 @Bean
	CommandLineRunner run(UtilisateurService userService){
		return args -> {
			userService.addRole(new Role(null,"ROLE_ADMIN"));
			userService.addRole(new Role(null,"ROLE_EMPLOYE"));
			userService.addRole(new Role(null,"ROLE_MANAGER"));

			userService.addUser(new Utilisateur(null,"admin@gmail","123",new ArrayList<>()));
			userService.addUser(new Utilisateur(null,"employe@gmail","123",new ArrayList<>()));
			userService.addUser(new Utilisateur(null,"manager@gmail","123",new ArrayList<>()));

			userService.addRoleToUser("admin@gmail","ROLE_ADMIN");
			userService.addRoleToUser("employe@gmail","ROLE_EMPLOYE");
			userService.addRoleToUser("manager@gmail","ROLE_MANAGER");

		};
	}


 */







}
