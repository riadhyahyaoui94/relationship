package com.datajpa.relationship.service;

import com.datajpa.relationship.dto.mapper;
import com.datajpa.relationship.dto.request.SignupRequest;
import com.datajpa.relationship.dto.request.SignupRequestConsultant;
import com.datajpa.relationship.dto.request.SignupRequestPersonnelMedical;
import com.datajpa.relationship.dto.request.UtilisateurRequestDto;
import com.datajpa.relationship.dto.response.ConsultantResponseDto;
import com.datajpa.relationship.dto.response.PersonnelMedicalResponseDto;
import com.datajpa.relationship.dto.response.UtilisateurResponseDto;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.RoleRepository;
import com.datajpa.relationship.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {


    private final UtilisateurRepository  utilisateurRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

@Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository
                                 ) {
        this.utilisateurRepository = utilisateurRepository;

}
@Transactional
      @Override
    public UtilisateurResponseDto addUtilisateur(UtilisateurRequestDto utilisateurRequestDto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(utilisateurRequestDto.getNom());
             utilisateurRepository.save(utilisateur);
        return mapper.utilisateurToUtilisateurResponseDto(utilisateur);}


          @Override
    public List<UtilisateurResponseDto> getUtilisateurs() {
        List<Utilisateur> utilisateurs = StreamSupport
                .stream(utilisateurRepository.findUtilisateursByRoles(roleRepository.findByName(ERole.ROLE_ADMIN)).spliterator(), false)
                .collect(Collectors.toList());
        return mapper.utilisateursToUtilisateurResponseDtos(utilisateurs);
    }


    @Override
    public Utilisateur getUtilisateur(Long utilisateurId) {
        return utilisateurRepository.findById(utilisateurId).orElseThrow(() ->
                new IllegalArgumentException("could not find utilisateur with id: " + utilisateurId));
    }

    @Override
    public String deleteUtilisateur(Long utilisateurId) {
       // Utilisateur utilisateur = getUtilisateur(utilisateurId);
        utilisateurRepository.deleteById(utilisateurId);
        return "user deleted";
    }

    @Override
    public UtilisateurResponseDto getUtilisateurById(Long utilisateurId) {
        Utilisateur utilisateur = getUtilisateur(utilisateurId);
        return mapper.utilisateurToUtilisateurResponseDto(utilisateur);
    }

    @Transactional
    @Override
    public UtilisateurResponseDto editUtilisateur(SignupRequest utilisateurRequestDto) {

        Utilisateur utilisateur =utilisateurRepository.findConsultantById(utilisateurRequestDto.getId());

     //   utilisateur.setId( utilisateurRequestDto.getId());
        utilisateur.setUsername( utilisateurRequestDto.getUsername());
        utilisateur.setEmail( utilisateurRequestDto.getEmail());
        utilisateur.setTelephone( utilisateurRequestDto.getTelephone());
        utilisateur.setPhoto( utilisateurRequestDto.getPhoto());
        utilisateur.setPrenom( utilisateurRequestDto.getPrenom());
        utilisateur.setNom( utilisateurRequestDto.getNom());
        utilisateur.setPassword( utilisateur.getPassword());
        //utilisateur.setRoles( utilisateurRequestDto.getRoles());
        Set<String> strRoles = utilisateurRequestDto.getRoles();
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




        return mapper.utilisateurToUtilisateurResponseDto(utilisateurRepository.save(utilisateur));
    }

    @Override
    public List<ConsultantResponseDto> getConsultants() {
        List<Consultant> consultants = StreamSupport
                .stream(utilisateurRepository.findConsultants().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.consultantsToConsultantResponseDtos(consultants);
    }

    @Override
    public ConsultantResponseDto getConsultantById(Long consultantId) {

        Consultant consultant = utilisateurRepository.findConsultantById(consultantId);
        return mapper.consultantToConsultantResponseDto(consultant) ;
    }

    @Transactional
    @Override
    public ConsultantResponseDto editConsultant(SignupRequestConsultant utilisateurRequestDto) {
        //Utilisateur u = getUtilisateur(utilisateurRequestDto.getId());
        Consultant consultant = utilisateurRepository.findConsultantById(utilisateurRequestDto.getId());

       // consultant.setId( utilisateurRequestDto.getId());
        consultant.setUsername( utilisateurRequestDto.getUsername());
        consultant.setEmail( utilisateurRequestDto.getEmail());
        consultant.setTelephone( utilisateurRequestDto.getTelephone());
        consultant.setPhoto( utilisateurRequestDto.getPhoto());
        consultant.setPrenom( utilisateurRequestDto.getPrenom());
        consultant.setPassword( consultant.getPassword());
        consultant.setNom( utilisateurRequestDto.getNom());
        consultant.setCalendrier( utilisateurRequestDto.getCalendrier());
        consultant.setFonction( utilisateurRequestDto.getFonction());
        consultant.setTitre( utilisateurRequestDto.getTitre());
        consultant.setServiceConsultation( utilisateurRequestDto.getServiceConsultation());
        //consultant.setOrdonnances( utilisateurRequestDto.getOrdonnances());
        //consultant.setDossierMedicals( utilisateurRequestDto.getDossierMedicals());
        Set<String> strRoles = utilisateurRequestDto.getRoles();

        Set<Role> roles = new HashSet<>();
        System.out.println("ssdsdddssdsdd");System.out.println("ssdsdddssdsdd");
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


        consultant.setRoles(roles);


        return mapper.consultantToConsultantResponseDto(utilisateurRepository.save(consultant));
    }

    @Override
    public List<PersonnelMedicalResponseDto> getPersonnelMedicals() {
        List<PersonnelMedical> personnels = StreamSupport
                .stream(utilisateurRepository.findPersonnelMedicals().spliterator(), false)
                .collect(Collectors.toList());
        return mapper.personnelMedicalResponseDtos(personnels);
    }

    @Override
    public PersonnelMedicalResponseDto getPersonnelMedicalById(Long personnelId) {
        PersonnelMedical personnelMedical = utilisateurRepository.findPersonnelMedicalById(personnelId);

        return mapper.personnelMedicalToPersonnelMedicalResponseDto(personnelMedical);
    }

    @Transactional
    @Override
    public PersonnelMedicalResponseDto editPersonnel(SignupRequestPersonnelMedical utilisateurRequestDto) {

        PersonnelMedical personnelMedical = utilisateurRepository.findPersonnelMedicalById( utilisateurRequestDto.getId());

       // personnelMedical.setId( utilisateurRequestDto.getId());
        personnelMedical.setUsername( utilisateurRequestDto.getUsername());
        personnelMedical.setEmail( utilisateurRequestDto.getEmail());
        personnelMedical.setTelephone( utilisateurRequestDto.getTelephone());
        personnelMedical.setPhoto( utilisateurRequestDto.getPhoto());
        personnelMedical.setPrenom( utilisateurRequestDto.getPrenom());
        personnelMedical.setNom( utilisateurRequestDto.getNom());
        personnelMedical.setPassword( personnelMedical.getPassword());
        personnelMedical.setTitre( utilisateurRequestDto.getTitre());
        personnelMedical.setFonction( utilisateurRequestDto.getFonction());
        personnelMedical.setService( utilisateurRequestDto.getService());

        if(utilisateurRequestDto.getAccueil().getId() != null) {
            personnelMedical.setAccueil(utilisateurRequestDto.getAccueil());
        }else{
            personnelMedical.setAccueil(null);
        }
        if(utilisateurRequestDto.getServiceConsultation().getId() != null) {
            personnelMedical.setServiceConsultation( utilisateurRequestDto.getServiceConsultation());

        }
        else{
            personnelMedical.setServiceConsultation( null);

        }
        if(utilisateurRequestDto.getSalleAttente().getId() != null) {
            personnelMedical.setSalleAttente( utilisateurRequestDto.getSalleAttente());

        }
        else{
            personnelMedical.setSalleAttente( null);

        }
        personnelMedical.setPaiement( utilisateurRequestDto.getPaiement());
        Set<String> strRoles = utilisateurRequestDto.getRoles();
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

        personnelMedical.setRoles(roles);
        return mapper.personnelMedicalToPersonnelMedicalResponseDto(utilisateurRepository.save(personnelMedical));
    }


}









