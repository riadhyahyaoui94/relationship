package com.datajpa.relationship;

import com.datajpa.relationship.model.ERole;
import com.datajpa.relationship.model.Role;
import com.datajpa.relationship.model.Utilisateur;
import com.datajpa.relationship.repository.RoleRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication

@EnableEurekaClient
@EnableFeignClients("com.datajpa.relationship")
public class RelationshipApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RelationshipApplication.class, args);

		RoleRepository roleRepository = context.getBean(RoleRepository.class);
		UtilisateurRepository adminRepository = context.getBean(UtilisateurRepository.class);
		PasswordEncoder encoder=context.getBean(PasswordEncoder.class);



		for (ERole d : ERole.values()) {


			if (roleRepository.findByName(d).isEmpty()) {
				Role role = new Role();
				role.setName(d);
				roleRepository.save(role);


			}


		}

if(adminRepository.findUtilisateursByRoles(roleRepository.findByName(ERole.ROLE_ADMIN)).isEmpty()){

	Utilisateur admin=new Utilisateur();
	admin.setUsername("youssefadmin");
	admin.setPassword(encoder.encode("12345678"));
	admin.setEmail("wars.youssef@gmail.fr");
	admin.getRoles().add(roleRepository.findByName(ERole.ROLE_ADMIN).orElse(null));
    adminRepository.save(admin);
		}

	}

}
