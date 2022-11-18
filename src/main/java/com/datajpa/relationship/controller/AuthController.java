package com.datajpa.relationship.controller;

import com.datajpa.relationship.dto.request.LoginRequest;
import com.datajpa.relationship.dto.request.SignupRequest;
import com.datajpa.relationship.dto.request.SignupRequestConsultant;
import com.datajpa.relationship.dto.request.SignupRequestPersonnelMedical;
import com.datajpa.relationship.dto.response.MessageResponse;
import com.datajpa.relationship.dto.response.UtilisateurResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.RoleRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;
import com.datajpa.relationship.security.jwt.JwtUtils;
import com.datajpa.relationship.service.UtilisateurDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
//@CrossOrigin(origins = "${baseUrl}",allowCredentials ="true",allowedHeaders = "*")
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UtilisateurRepository utilisateurRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UtilisateurDetailsImpl utilisateurDetails = (UtilisateurDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(utilisateurDetails);

    List<String> roles = utilisateurDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());


      return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
              .body(new UtilisateurResponseDto(utilisateurDetails.getId(),
                      utilisateurDetails.getUsername(),
                      utilisateurDetails.getEmail(),
                      utilisateurDetails.getNom(),
                      utilisateurDetails.getPrenom(),
                      utilisateurDetails.getTelephone(),
                      utilisateurDetails.getPhoto(),
                      utilisateurDetails.getLastUpdatedAt(),
                      utilisateurDetails.getPostedAt(),

                      roles));

  }

  @PostMapping("/Admin/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (utilisateurRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (utilisateurRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new admin's account
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.setUsername( signUpRequest.getUsername());
    utilisateur.setEmail( signUpRequest.getEmail());
    utilisateur.setPassword( encoder.encode(signUpRequest.getPassword()));
    utilisateur.setTelephone( signUpRequest.getTelephone());
    utilisateur.setPhoto( signUpRequest.getPhoto());
    utilisateur.setPrenom( signUpRequest.getPrenom());
    utilisateur.setNom( signUpRequest.getNom());

    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role utilisateurRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(utilisateurRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "ROLE_ADMIN":

          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;

          case "ROLE_ACCEUIL":
          Role acRole = roleRepository.findByName(ERole.ROLE_ACCEUIL)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(acRole);


          break;
          case "ROLE_ATTENTE":
          Role attRole = roleRepository.findByName(ERole.ROLE_ATTENTE)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(attRole);
            break;
 case "ROLE_SERVICECONSULTATION":
          Role attRol = roleRepository.findByName(ERole.ROLE_SERVICECONSULTATION)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(attRol);
   break;
 case "ROLE_PAIEMENT":
          Role attRo = roleRepository.findByName(ERole.ROLE_PAIEMENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(attRo);
   break;
 case "ROLE_CONFIGURATION":
          Role attR = roleRepository.findByName(ERole.ROLE_CONFIGURATION)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(attR);
   break;
          case "ROLE_PRISERDV":
          Role att = roleRepository.findByName(ERole.ROLE_PRISERDV)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(att);
            break;
          case "ROLE_CONSULTANT":
          Role at = roleRepository.findByName(ERole.ROLE_CONSULTANT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(at);
   break;
 case "Role_DASHBORD":
          Role a = roleRepository.findByName(ERole.Role_DASHBORD)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(a);


          break;
        default:
          Role utilisateurRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(utilisateurRole);

        }
      });
    }

    utilisateur.setRoles(roles);
    utilisateurRepository.save(utilisateur);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/Consultant/signup")
  public ResponseEntity<?> registerConsultant(@Valid @RequestBody SignupRequestConsultant signUpRequest) {
    if (utilisateurRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (utilisateurRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new consultant's account
    Consultant utilisateur = new Consultant();
               utilisateur.setUsername(signUpRequest.getUsername());
               utilisateur.setNom(signUpRequest.getNom());
               utilisateur.setPrenom(signUpRequest.getPrenom());
               utilisateur.setPhoto(signUpRequest.getPhoto());
               utilisateur.setDossierMedicals(signUpRequest.getDossierMedicals());
               utilisateur.setOrdonnances(signUpRequest.getOrdonnances());
               utilisateur.setServiceConsultation(signUpRequest.getServiceConsultation());
               utilisateur.setEmail(signUpRequest.getEmail());
               utilisateur.setPassword(encoder.encode(signUpRequest.getPassword()));
               utilisateur.setCalendrier(signUpRequest.getCalendrier());
               utilisateur.setFonction(signUpRequest.getFonction());
               utilisateur.setTitre(signUpRequest.getTitre());
               utilisateur.setTelephone(signUpRequest.getTelephone());


    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role utilisateurRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(utilisateurRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "ROLE_ADMIN":

            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;

          case "ROLE_ACCEUIL":
            Role acRole = roleRepository.findByName(ERole.ROLE_ACCEUIL)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(acRole);


            break;
          case "ROLE_ATTENTE":
            Role attRole = roleRepository.findByName(ERole.ROLE_ATTENTE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attRole);
            break;
          case "ROLE_SERVICECONSULTATION":
            Role attRol = roleRepository.findByName(ERole.ROLE_SERVICECONSULTATION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attRol);
            break;
          case "ROLE_PAIEMENT":
            Role attRo = roleRepository.findByName(ERole.ROLE_PAIEMENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attRo);
            break;
          case "ROLE_CONFIGURATION":
            Role attR = roleRepository.findByName(ERole.ROLE_CONFIGURATION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attR);
            break;
          case "ROLE_PRISERDV":
            Role att = roleRepository.findByName(ERole.ROLE_PRISERDV)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(att);
            break;
          case "ROLE_CONSULTANT":
            Role at = roleRepository.findByName(ERole.ROLE_CONSULTANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(at);
            break;
          case "Role_DASHBORD":
            Role a = roleRepository.findByName(ERole.Role_DASHBORD)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(a);
            break;
        default:
          Role utilisateurRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(utilisateurRole);

        }
      });
    }

    utilisateur.setRoles(roles);
    utilisateurRepository.save(utilisateur);

    return ResponseEntity.ok(new MessageResponse("Consultant registered successfully!"));
  }

  @PostMapping("/PersonnelMedical/signup")
  public ResponseEntity<?> registerPersonnelMedical(@Valid @RequestBody SignupRequestPersonnelMedical signUpRequest) {
    if (utilisateurRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (utilisateurRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    PersonnelMedical utilisateur = new PersonnelMedical();
    utilisateur.setUsername(signUpRequest.getUsername());
    utilisateur.setNom(signUpRequest.getNom());
    utilisateur.setPrenom(signUpRequest.getPrenom());
    utilisateur.setTelephone(signUpRequest.getTelephone());
    utilisateur.setEmail(signUpRequest.getEmail());
    utilisateur.setPassword(encoder.encode(signUpRequest.getPassword()));
    utilisateur.setPhoto(signUpRequest.getPhoto());
    utilisateur.setService(signUpRequest.getService());
    utilisateur.setTitre(signUpRequest.getTitre());
    utilisateur.setFonction(signUpRequest.getFonction());
    if(signUpRequest.getSalleAttente().getId()!=null) {
      utilisateur.setSalleAttente(signUpRequest.getSalleAttente());
    }else {
      utilisateur.setSalleAttente(null);
    }
    if(signUpRequest.getServiceConsultation().getId()!=null) {
      utilisateur.setServiceConsultation(signUpRequest.getServiceConsultation());
    }else {
      utilisateur.setServiceConsultation(null);
    }
    if(signUpRequest.getPaiement()!=null) {
      utilisateur.setPaiement(signUpRequest.getPaiement());
    }else{utilisateur.setPaiement(null);}
    if(signUpRequest.getAccueil().getId()!=null) {
      utilisateur.setAccueil(signUpRequest.getAccueil());
    }else {
      utilisateur.setAccueil(null);
    }


    Set<String> strRoles = signUpRequest.getRoles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role utilisateurRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(utilisateurRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "ROLE_ADMIN":

            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(adminRole);

            break;

          case "ROLE_ACCEUIL":
            Role acRole = roleRepository.findByName(ERole.ROLE_ACCEUIL)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(acRole);


            break;
          case "ROLE_ATTENTE":
            Role attRole = roleRepository.findByName(ERole.ROLE_ATTENTE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attRole);
            break;
          case "ROLE_SERVICECONSULTATION":
            Role attRol = roleRepository.findByName(ERole.ROLE_SERVICECONSULTATION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attRol);
            break;
          case "ROLE_PAIEMENT":
            Role attRo = roleRepository.findByName(ERole.ROLE_PAIEMENT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attRo);
            break;
          case "ROLE_CONFIGURATION":
            Role attR = roleRepository.findByName(ERole.ROLE_CONFIGURATION)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(attR);
            break;
          case "ROLE_PRISERDV":
            Role att = roleRepository.findByName(ERole.ROLE_PRISERDV)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(att);
            break;
          case "ROLE_CONSULTANT":
            Role at = roleRepository.findByName(ERole.ROLE_CONSULTANT)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(at);
            break;
          case "Role_DASHBORD":
            Role a = roleRepository.findByName(ERole.Role_DASHBORD)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(a);
            break;
        default:
          Role utilisateurRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(utilisateurRole);

        }
      });
    }

    utilisateur.setRoles(roles);
    utilisateurRepository.save(utilisateur);

    return ResponseEntity.ok(new MessageResponse("Personnel Medical registered successfully!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUtilisateur() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
