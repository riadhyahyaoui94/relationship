package com.datajpa.relationship.dto;


import com.datajpa.relationship.dto.response.*;
import com.datajpa.relationship.model.*;
import com.datajpa.relationship.repository.RoleRepository;
import com.datajpa.relationship.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class mapper {


    private static List<DossierMedical> dossierMedicals;

    public static AssuranceResponseDto assuranceToAssuranceResponseDto(Assurance assurance) {
        AssuranceResponseDto assuranceResponseDto = new AssuranceResponseDto();
       assuranceResponseDto.setId(assurance.getId());
       assuranceResponseDto.setNomPatient(assurance.getNomPatient());
       assuranceResponseDto.setPrenomPatient(assurance.getPrenomPatient());
       assuranceResponseDto.setDatePrestation(assurance.getDatePrestation());
       assuranceResponseDto.setIdentitePatient(assurance.getNomPatient());
       assuranceResponseDto.setMontantConvention(assurance.getMontantConvention());
       assuranceResponseDto.setNumAffilation(assurance.getNumAffilation());
       assuranceResponseDto.setTypePrestation(assurance.getTypePrestation());

        List<String> names = new ArrayList<>();
        List<Paiement> paiements = assurance.getPaiements();
        for (Paiement paiement:paiements){
            names.add(paiement.getPrestation());

            names.add(String.valueOf(paiement.getCodeFacture()));
            names.add(String.valueOf(paiement.getDatePaiement()));
            names.add(paiement.getPrestation());
            names.add(String.valueOf(paiement.getAssurance()));
            names.add(String.valueOf(paiement.getMontantPrestation()));
            names.add(String.valueOf(paiement.getTauxCouverture()));
            names.add(String.valueOf(paiement.getDatePaiement()));
        }
        assuranceResponseDto.setPaiementNames(names);
        return assuranceResponseDto;
    }

    public static List<AssuranceResponseDto> assurancesToAssuranceResponseDtos(List<Assurance> assurances) {
        List<AssuranceResponseDto> assuranceResponseDtos = new ArrayList<>();
        for (Assurance assurance : assurances) {
            assuranceResponseDtos.add(assuranceToAssuranceResponseDto(assurance));
        }
        return assuranceResponseDtos;
    }


    public static AccueilResponseDto accueilToAccueilResponseDto(Accueil accueil) {
        AccueilResponseDto accueilResponseDto = new AccueilResponseDto();
       accueilResponseDto.setId(accueil.getId());
        //accueilResponseDto.setNouveauPatient(accueil.getNouveauPatient());
        accueilResponseDto.setNom(accueil.getNom());
        accueilResponseDto.setPersonnelMedicals(accueil.getPersonnelMedicals());

        List<String> names = new ArrayList<>();
        if(accueil.getPaiements()!=null) {
            List<Paiement> paiements = accueil.getPaiements();
            // List<SalleAttente> salleAttentes= accueil.getSalleAttentes();

            for (Paiement paiement : paiements) {
                names.add(paiement.getPrestation());
                names.add(String.valueOf(paiement.getCodeFacture()));
                names.add(String.valueOf(paiement.getDatePaiement()));
                names.add(String.valueOf(paiement.getAssurance()));
                names.add(String.valueOf(paiement.getMontantPrestation()));
                names.add(String.valueOf(paiement.getTauxCouverture()));
                names.add(String.valueOf(paiement.getDatePaiement()));
            }
            /*
        for (SalleAttente salleAttente:salleAttentes){
            names.add(salleAttente.getNom());
            names.add(String.valueOf(salleAttente.getNumOrdre()));
        }*/

            accueilResponseDto.setPaiementNames(names);
        }
       // accueilResponseDto.setSalleAttenteNames(names);
          return accueilResponseDto;
    }

    public static List<AccueilResponseDto> accueilsToAccueilResponseDtos(List<Accueil> accueils) {
        List<AccueilResponseDto> accueilResponseDtos = new ArrayList<>();
        for (Accueil accueil : accueils) {
            accueilResponseDtos.add(accueilToAccueilResponseDto(accueil));
        }
        return accueilResponseDtos;
    }
  /*  public static ConsultantResponseDto consultantToConsultantResponseDto(Consultant consultant) {
        ConsultantResponseDto consultantResponseDto = new ConsultantResponseDto();
       consultantResponseDto.setId(consultant.getId());
        consultantResponseDto.setNomService(consultant.getNomService());
        consultantResponseDto.setCalendrier(consultant.getCalendrier());


        List<String> names = new ArrayList<>();
        List<Ordonnance> ordonnances = consultant.getOrdonnances();
        List<DossierMedical> dossierMedicals = consultant.getDossierMedicals();
        for (Ordonnance ordonnance :ordonnances) {
            names.add(ordonnance.getNomPatient());
            names.add(String.valueOf(ordonnance.getDateOrdonnance()));
            names.add(ordonnance.getNatureOrdonnance());
        }
        for (DossierMedical dossierMedical:dossierMedicals){
            names.add(dossierMedical.getNomPatient());
            names.add(dossierMedical.getPrenomPatient());
            names.add(dossierMedical.getConsultationEffectuee());
            names.add(dossierMedical.getResultatPrestation());

        }
        consultantResponseDto.setOrdonnanceNames(names);
        consultantResponseDto.setDossierMedicalNames(names);
        return consultantResponseDto;
    }
*/
    public static List<ConsultantResponseDto> consultantsToConsultantResponseDtos(List<Consultant> consultants) {
        List<ConsultantResponseDto> consultantResponseDtos = new ArrayList<>();
        for (Consultant consultant : consultants) {
            consultantResponseDtos.add(consultantToConsultantResponseDto(consultant));
        }
        return consultantResponseDtos;
    }

    public static ServiceConsultationResponseDto serviceConsultationToServiceConsultationResponseDto
            (ServiceConsultation   serviceConsultation) {
        ServiceConsultationResponseDto serviceConsultationResponseDto = new ServiceConsultationResponseDto();
        serviceConsultationResponseDto.setId(serviceConsultation.getId());
        //serviceConsultationResponseDto.setDateConsultation(serviceConsultationResponseDto.getDateConsultation());
        serviceConsultationResponseDto.setTypeService(serviceConsultation.getTypeService());
        serviceConsultationResponseDto.setNomService(serviceConsultation.getNomService());
        //serviceConsultationResponseDto.setAncienPatient(serviceConsultationResponseDto.getAncienPatient());
       // serviceConsultationResponseDto.setDiagnostic(serviceConsultationResponseDto.getDiagnostic());
        //serviceConsultationResponseDto.setNomConsultant(serviceConsultation.getNomConsultation());
       // serviceConsultationResponseDto.setFonctionConsultant(serviceConsultationResponseDto.getFonctionConsultant());
/*
        List<String> names = new ArrayList<>();
        List<Consultant> consultants = serviceConsultation.getConsultants();
        List<DossierMedical>dossierMedicals = serviceConsultation.getDossierMedicals();
        List<PriseRDV> priseRDVS = serviceConsultation.getPriseRDVS();

*/

/*
        for (DossierMedical dossierMedical:dossierMedicals){
            names.add(dossierMedical.getNomPatient());
            names.add(dossierMedical.getPrenomPatient());
            names.add(dossierMedical.getConsultationEffectuee());
            names.add(dossierMedical.getResultatPrestation());

        }
        for (Consultant consultant: consultants){
            names.add(consultant.getNom());
            names.add(String.valueOf(consultant.getCalendrier()));
            names.add(String.valueOf(consultant.getTelephone()));
        }
        for (PriseRDV priseRDV: priseRDVS){
            names.add(priseRDV.getNomPatient());
            names.add(priseRDV.getPrenomPatient());
            names.add(String.valueOf(priseRDV.getDateRDV()));
            names.add(String.valueOf(priseRDV.getHeureRDV()));
            names.add(priseRDV.getConsultant());
            names.add(priseRDV.getMotif());
            names.add(String.valueOf(priseRDV.getAncienPatient()));
        }
      serviceConsultationResponseDto.setConsultantNames(names);
       serviceConsultationResponseDto.setDossierMedicalNames(names);
       serviceConsultationResponseDto.setPriseRDVNames(names);

 */
        serviceConsultationResponseDto.setConsultants(serviceConsultation.getConsultants());
        serviceConsultationResponseDto.setDossierMedicals(serviceConsultation.getDossierMedicals());
        serviceConsultationResponseDto.setPriseRDVS(serviceConsultation.getPriseRDVS());
        serviceConsultationResponseDto.setSalleAttente(serviceConsultation.getSalleAttente());
        serviceConsultationResponseDto.setPersonnelMedicals(serviceConsultation.getPersonnelMedicals());
        serviceConsultationResponseDto.setPostedAt(serviceConsultation.getPostedAt());
        serviceConsultationResponseDto.setLastUpdatedAt(serviceConsultation.getLastUpdatedAt());
        return serviceConsultationResponseDto;
    }

public static ConsultationResponseDto consultationToServiceConsultationResponseDto
            (Consultation   consultation) {

        ConsultationResponseDto consultationResponseDto = new ConsultationResponseDto();
        consultationResponseDto.setId(consultation.getId());
        consultationResponseDto.setTypeConsultation(consultation.getTypeConsultation());
        consultationResponseDto.setDateConsultation(consultation.getDateConsultation());

        consultationResponseDto.setConsultant(consultation.getConsultant());
        consultationResponseDto.setDossierMedical(consultation.getDossierMedical());
        consultationResponseDto.setDiagnostic(consultation.getDiagnostic());
        consultationResponseDto.setPatient(consultation.getPatient());
        consultationResponseDto.setPostedAt(consultation.getPostedAt());
        consultationResponseDto.setLastUpdatedAt(consultation.getLastUpdatedAt());
        return consultationResponseDto;
    }
public static ConsultationServiceResponseDto consultationServiceConsultationsServiceResponseDto
            (ConsultationByService consultation) {

        ConsultationServiceResponseDto consultationServiceResponseDto = new ConsultationServiceResponseDto();
        consultationServiceResponseDto.setNames(consultation.getName());
        consultationServiceResponseDto.setConsultations(consultation.getConsultations());


        return consultationServiceResponseDto;
    }

    public static List<ServiceConsultationResponseDto> serviceConsultationsToServiceConsultationResponseDtos
            (List<ServiceConsultation> serviceConsultations) {
        List<ServiceConsultationResponseDto> serviceConsultationResponseDtos = new ArrayList<>();
        for (ServiceConsultation serviceConsultation : serviceConsultations) {
         serviceConsultationResponseDtos.add(serviceConsultationToServiceConsultationResponseDto
                   (serviceConsultation));
        }
        return serviceConsultationResponseDtos;

    }

    public static List<ConsultationResponseDto> consultationsToServiceConsultationResponseDtos
            (List<Consultation> consultations) {
        List<ConsultationResponseDto> consultationResponseDtos = new ArrayList<>();
        for (Consultation consultation : consultations) {
         consultationResponseDtos.add(consultationToServiceConsultationResponseDto
                   (consultation));
        }
        return consultationResponseDtos;

    }
 public static List<ConsultationServiceResponseDto> consultationsServiceToConsultationsServiceResponseDtos
            (List<ConsultationByService> consultations) {
        List<ConsultationServiceResponseDto> consultationServiceResponseDtos = new ArrayList<>();
        for (ConsultationByService consultation : consultations) {
         consultationServiceResponseDtos.add(consultationServiceConsultationsServiceResponseDto
                   (consultation));
        }
        return consultationServiceResponseDtos;

    }

    public static FacturationResponseDto facturationToFacturationResponseDto(Facturation facturation) {
        FacturationResponseDto facturationResponseDto = new FacturationResponseDto();
        facturationResponseDto.setId(facturation.getId());
        facturationResponseDto.setCodeFacture(facturation.getCodeFacture());
        facturationResponseDto.setMontantAPayer(facturation.getMontantPaye());
        facturationResponseDto.setMontantPaye(facturation.getMontantAPayer());
        facturationResponseDto.setTypePaiement(facturation.getTypePaiement());
        facturationResponseDto.setDateFacture(facturation.getDateFacture());
        facturationResponseDto.setSommeRecue(facturation.getSommeRecue());
        facturationResponseDto.setSommeRendue(facturation.getSommeRendue());
        facturationResponseDto.setExamen(facturation.getExamen());
        facturationResponseDto.setPriseRDV(facturation.getPriseRDV());
        facturationResponseDto.setNomAgent(facturation.getNomAgent());
        List<String> names = new ArrayList<>();
       /* List<Prestation> prestations = facturation.getPrestations();
        for (Prestation prestation:prestations){
            names.add(prestation.getNaturePrestation());
            names.add(String.valueOf(prestation.getCoutPourNonAssure()));
            names.add(String.valueOf(prestation.getCoutPourAssure()));
        }
        facturationResponseDto.setPrestationNames(names);*/
        return facturationResponseDto;
    }

    public static List<FacturationResponseDto> facturationsToFacturationResponseDtos(List<Facturation>facturations) {
        List<FacturationResponseDto> facturationResponseDtos = new ArrayList<>();
        for (Facturation facturation: facturations) {
            facturationResponseDtos.add(facturationToFacturationResponseDto(facturation));
        }
        return facturationResponseDtos;
    }

    public static PaiementResponseDto paiementToPaiementResponseDto(Paiement paiement) {
        PaiementResponseDto paiementResponseDto = new PaiementResponseDto();
        paiementResponseDto.setId(paiement.getId());
       paiementResponseDto.setNomAssurance(paiement.getNomAssurance());
       paiementResponseDto.setCodeFacture(paiement.getCodeFacture());
       paiementResponseDto.setPrestation(paiement.getPrestation());
       paiementResponseDto.setMontantPrestation(paiement.getMontantPrestation());
       paiementResponseDto.setDatePaiement(paiement.getDatePaiement());
       paiementResponseDto.setTauxCouverture(paiement.getTauxCouverture());
        List<String> names = new ArrayList<>();
        List<Patient> patients = paiement.getPatients();
        List<Facturation> facturations = paiement.getFacturations();
        for (Facturation facturation:facturations){
            names.add(String.valueOf(facturation.getCodeFacture()));
            names.add(facturation.getTypePaiement());
            names.add(String.valueOf(facturation.getDateFacture()));
            names.add(facturation.getTypePaiement());
            names.add(String.valueOf(facturation.getMontantPaye()));
            names.add(String.valueOf(facturation.getSommeRecue()));
            names.add(String.valueOf(facturation.getSommeRendue()));
        }

        for (Patient patient: patients) {
            names.add(patient.getNom());
            names.add(patient.getPrenom());
            names.add(patient.getAssurance());
            names.add(patient.getDomicile());
            names.add(patient.getGenre());
            names.add(String.valueOf(patient.getNumIdendite()));
            names.add(patient.getEmail());
            names.add(String.valueOf(patient.getDateNaissance()));
            names.add(patient.getPhoto());
            names.add(String.valueOf(patient.getTauxAssurance()));
            names.add(String.valueOf(patient.getEstAssure()));

        }
       paiementResponseDto.setFacturationNames(names);
        paiementResponseDto.setPatientNames(names);
        return paiementResponseDto;
    }

    public static List<PaiementResponseDto>paiementsToPaiementResponseDtos(List<Paiement> paiements) {
        List<PaiementResponseDto> paiementResponseDtos = new ArrayList<>();
        for (Paiement  paiement: paiements) {
            paiementResponseDtos.add(paiementToPaiementResponseDto(paiement));
        }
        return paiementResponseDtos;
    }

    public static PatientResponseDto patientToPatientResponseDto(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getId());
        patientResponseDto.setNom(patient.getNom());
        patientResponseDto.setPrenom(patient.getPrenom());
        patientResponseDto.setGenre(patient.getGenre());
        patientResponseDto.setDateNaissance(patient.getDateNaissance());

        patientResponseDto.setNumIdendite(patient.getNumIdendite());
        patientResponseDto.setPhoto(patient.getPhoto());
        patientResponseDto.setDomicile(patient.getDomicile());
        patientResponseDto.setEstAssure(patient.getEstAssure());
        patientResponseDto.setAssurance(patient.getAssurance());
        patientResponseDto.setTauxAssurance(patient.getTauxAssurance());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setNumAffeliation(patient.getNumAffeliation());
        patientResponseDto.setPostedAt(patient.getPostedAt());
        patientResponseDto.setLastUpdatedAt(patient.getLastUpdatedAt());
        patientResponseDto.setPriseRDVs(patient.getPriseRDVs());
        patientResponseDto.setPaiement(patient.getPaiement());
        patientResponseDto.setSalleAttente(patient.getSalleAttente());
        patientResponseDto.setDossierMedical(patient.getDossierMedicals());
        patientResponseDto.setDetailsPatient(patient.getDetailsPatient());

        List<String> names = new ArrayList<>();
        List<DossierMedical> dossierMedicals = patient.getDossierMedicals();
        List<PriseRDV> priseRDVS= patient.getPriseRDVs();

/*

      if(priseRDVS!=null) {
          for (PriseRDV priseRDV : priseRDVS) {

              names.add(priseRDV.getNomPatient());
              names.add(priseRDV.getId().toString());
              names.add(priseRDV.getPrenomPatient());
              names.add(String.valueOf(priseRDV.getDateRDV()));
              names.add(String.valueOf(priseRDV.getHeureRDV()));
              names.add(priseRDV.getConsultant().getId().toString());
              names.add(priseRDV.getMotif());
              names.add(String.valueOf(priseRDV.getAncienPatient()));
          }
          patientResponseDto.setPriseRDVNames(names);
      }
        List<String> names2 = new ArrayList<>();
        if(dossierMedicals!=null) {
            for (DossierMedical dossierMedical : dossierMedicals) {
                names2.add(dossierMedical.getNomPatient());
                names2.add(dossierMedical.getPrenomPatient());
                names2.add(dossierMedical.getConsultationEffectuee());
                names2.add(dossierMedical.getResultatPrestation());

            }
            patientResponseDto.setDossierMedicalNames(names2);
        }
*/


        return patientResponseDto;
    }

    public static List<PatientResponseDto> patientsToPatientResponseDtos(List<Patient> patients) {
        List<PatientResponseDto>patientResponseDtos = new ArrayList<>();
        for (Patient patient: patients) {
            patientResponseDtos.add(patientToPatientResponseDto(patient));

        }
        return patientResponseDtos;
           }




    public static SalleAttenteResponseDto salleAttenteToSalleAttenteResponseDto(SalleAttente salleAttente) {
        SalleAttenteResponseDto salleAttenteResponseDto = new SalleAttenteResponseDto();
        salleAttenteResponseDto.setId(salleAttente.getId());
        salleAttenteResponseDto.setNumOrdre(salleAttente.getNumOrdre());
        salleAttenteResponseDto.setNom(salleAttente.getNom());
        salleAttenteResponseDto.setEtage(salleAttente.getEtage());

        List<String> names = new ArrayList<>();
        List<ServiceConsultation> serviceConsultations = salleAttente.getServiceConsultations();
        List<Patient> patients=salleAttente.getPatients();
/*
        if(serviceConsultations!=null) {
            for (ServiceConsultation serviceConsultation : serviceConsultations) {
                names.add(serviceConsultation.getNomConsultation());
                names.add(serviceConsultation.getTypeConsultation());
                // names.add(serviceConsultation.getFonctionConsultant());
                // names.add(serviceConsultation.getNomConsultant());
                //  names.add(serviceConsultation.getDiagnostic());
                //  names.add(String.valueOf(serviceConsultation.getDateConsultation()));
                //  names.add(String.valueOf(serviceConsultation.getAncienPatient()));

            }
        }*/
/*        for (Patient patient: patients) {
            names.add(patient.getNom());
            names.add(patient.getPrenom());
            names.add(patient.getAssurance());
            names.add(patient.getDomicile());
            names.add(patient.getGenre());
            names.add(String.valueOf(patient.getNumIdendite()));
            names.add(patient.getEmail());
            names.add(String.valueOf(patient.getDateNaissance()));
            names.add(patient.getPhoto());
            names.add(String.valueOf(patient.getTauxAssurance()));
            names.add(String.valueOf(patient.getEstAssure()));

        }
        salleAttenteResponseDto.setPatientNames(names);*/
        salleAttenteResponseDto.setLastUpdatedAt(salleAttente.getLastUpdatedAt());
        salleAttenteResponseDto.setCreatedAt(salleAttente.getPostedAt());
        salleAttenteResponseDto.setServiceConsultations(salleAttente.getServiceConsultations());

        salleAttenteResponseDto.setPatients(salleAttente.getPatients());
        salleAttenteResponseDto.setPersonnelMedicals(salleAttente.getPersonnelMedicals());
        return salleAttenteResponseDto;


    }
    public static List<SalleAttenteResponseDto> salleAttentesToSalleAttenteResponseDtos(List<SalleAttente> salleAttentes) {
        List<SalleAttenteResponseDto> salleAttenteResponseDtos = new ArrayList<>();
        for (SalleAttente salleAttente : salleAttentes) {
            salleAttenteResponseDtos.add(salleAttenteToSalleAttenteResponseDto(salleAttente));
        }
        return salleAttenteResponseDtos;

    }
    public static OrdonnanceResponseDto ordonnanceToOrdonnanceResponseDto(Ordonnance ordonnance) {
        OrdonnanceResponseDto ordonnanceResponseDto = new OrdonnanceResponseDto();
        ordonnanceResponseDto.setId(ordonnance.getId());
        ordonnanceResponseDto.setNomPatient(ordonnance.getNomPatient());
        ordonnanceResponseDto.setDateOrdonnance(ordonnance.getDateOrdonnance());
        ordonnanceResponseDto.setNatureOrdonnance(ordonnance.getNatureOrdonnance());

      /*  List<String> names = new ArrayList<>();
        List<Examen> examens = ordonnance.getExamens();
        List<Medicament> medicaments = ordonnance.getMedicaments();
        for (Examen examen: examens) {
            names.add(examen.getNomExamen());
            names.add(String.valueOf(examen.getPrixExamen()));
                  }
        for (Medicament medicament :medicaments){
            names.add(medicament.getNomMedicament());
            names.add(medicament.getDureeDePrise());
        }*/
        ordonnanceResponseDto.setExamens(ordonnance.getExamens());
        ordonnanceResponseDto.setMedicaments(ordonnance.getMedicaments());
        ordonnanceResponseDto.setConsultant(ordonnance.getConsultant());
        ordonnanceResponseDto.setConsultation(ordonnance.getConsultation());
        return ordonnanceResponseDto;
    }

    public static List<OrdonnanceResponseDto> ordonnancesToOrdonnanceResponseDtos(List<Ordonnance> ordonnances){
        List<OrdonnanceResponseDto> ordonnanceResponseDtos = new ArrayList<>();
        for (Ordonnance ordonnance: ordonnances) {
            ordonnanceResponseDtos.add(ordonnanceToOrdonnanceResponseDto(ordonnance));
        }
        return ordonnanceResponseDtos;
    }

    public static UtilisateurResponseDto utilisateurToUtilisateurResponseDto(Utilisateur utilisateur) {
        UtilisateurResponseDto utilisateurResponseDto = new UtilisateurResponseDto();
        utilisateurResponseDto.setId(utilisateur.getId());
        utilisateurResponseDto.setNom(utilisateur.getNom());
        utilisateurResponseDto.setPrenom(utilisateur.getPrenom());

        utilisateurResponseDto.setEmail(utilisateur.getEmail());
        utilisateurResponseDto.setPhoto(utilisateur.getPhoto());

        utilisateurResponseDto.setTelephone(utilisateur.getTelephone());

        utilisateurResponseDto.setUsername(utilisateur.getUsername());
        utilisateurResponseDto.setLastUpdatedAt(utilisateur.getLastUpdatedAt());
        utilisateurResponseDto.setPostedAt(utilisateur.getPostedAt());
        List<String> roles = new ArrayList<String>();
        for (Role role: utilisateur.getRoles()) {
            roles.add(role.getName().name());
        }
        utilisateurResponseDto.setRoles(roles);
        // utilisateurResponseDto.setPassword(utilisateur.getPassword());

        return utilisateurResponseDto;
    }

    public static DetailsPatientResponseDto detailsPatientToDetailsPatientResponseDto(DetailsPatient detailsPatient) {
        DetailsPatientResponseDto detailsPatientResponseDto = new DetailsPatientResponseDto();
        detailsPatientResponseDto.setId(detailsPatient.getId());
        detailsPatientResponseDto.setPatient(detailsPatient.getPatient());
        detailsPatientResponseDto.setEtatDuPatient(detailsPatient.getEtatDuPatient());

        detailsPatientResponseDto.setStatus(detailsPatient.getStatus());
        detailsPatientResponseDto.setHeurePriseCharge(detailsPatient.getHeurePriseCharge());

        detailsPatientResponseDto.setServiceAffectation(detailsPatient.getServiceAffectation());


        detailsPatientResponseDto.setLastUpdatedAt(detailsPatient.getLastUpdatedAt());
        detailsPatientResponseDto.setPostedAt(detailsPatient.getPostedAt());


        return detailsPatientResponseDto;
    }

    public static ConsultantResponseDto consultantToConsultantResponseDto(Consultant consultant) {
        ConsultantResponseDto consultantResponseDto = new ConsultantResponseDto();
        consultantResponseDto.setId(consultant.getId());
        consultantResponseDto.setNom(consultant.getNom());
        consultantResponseDto.setPrenom(consultant.getPrenom());

        consultantResponseDto.setEmail(consultant.getEmail());
        consultantResponseDto.setPhoto(consultant.getPhoto());

        consultantResponseDto.setTelephone(consultant.getTelephone());

        consultantResponseDto.setUsername(consultant.getUsername());
        consultantResponseDto.setCalendrier(consultant.getCalendrier());
        consultantResponseDto.setTitre(consultant.getTitre());
        consultantResponseDto.setFonction(consultant.getFonction());
        consultantResponseDto.setDossierMedicals(consultant.getDossierMedicals());
        consultantResponseDto.setOrdonnances(consultant.getOrdonnances());
        consultantResponseDto.setServiceConsultation(consultant.getServiceConsultation());
        consultantResponseDto.setPriseRDVs(consultant.getPriseRDVs());
        consultantResponseDto.setLastUpdatedAt(consultant.getLastUpdatedAt());
        consultantResponseDto.setPostedAt(consultant.getPostedAt());
        List<String> roles = new ArrayList<String>();
        for (Role role: consultant.getRoles()) {
            roles.add(role.getName().name());
        }
        consultantResponseDto.setRoles(roles);
        // utilisateurResponseDto.setPassword(utilisateur.getPassword());

        return consultantResponseDto;
    }

    public static Consultant consultantResponseDtoToConsultant(ConsultantResponseDto consultantResponseDto) {
        Consultant consultant = new Consultant();
        consultant.setId(consultantResponseDto.getId());
        consultant.setNom(consultantResponseDto.getNom());
        consultant.setPrenom(consultantResponseDto.getPrenom());

        consultant.setEmail(consultantResponseDto.getEmail());
        consultant.setPhoto(consultantResponseDto.getPhoto());

        consultant.setTelephone(consultantResponseDto.getTelephone());

        consultant.setUsername(consultantResponseDto.getUsername());
        consultant.setCalendrier(consultantResponseDto.getCalendrier());
        consultant.setTitre(consultantResponseDto.getTitre());
        consultant.setFonction(consultantResponseDto.getFonction());

        consultant.setDossierMedicals(consultantResponseDto.getDossierMedicals());
        consultant.setOrdonnances(consultantResponseDto.getOrdonnances());
        consultant.setServiceConsultation(consultantResponseDto.getServiceConsultation());
        consultant.setLastUpdatedAt(consultantResponseDto.getLastUpdatedAt());
        consultant.setPostedAt(consultantResponseDto.getPostedAt());
/*
        List<Optional<Role>> roles = new ArrayList<Optional<Role>>();
        for (String role: consultantResponseDto.getRoles()) {
            roles.add(roleRepository.findByName(ERole.valueOf(role)));
        }
        consultant.setRoles(roles);*/
        // utilisateurResponseDto.setPassword(utilisateur.getPassword());

        return consultant;
    }

    public static PersonnelMedicalResponseDto personnelMedicalToPersonnelMedicalResponseDto(PersonnelMedical personnelMedical) {
        PersonnelMedicalResponseDto personnelMedicalResponseDto = new PersonnelMedicalResponseDto();
        personnelMedicalResponseDto.setId(personnelMedical.getId());
        personnelMedicalResponseDto.setNom(personnelMedical.getNom());
        personnelMedicalResponseDto.setPrenom(personnelMedical.getPrenom());

        personnelMedicalResponseDto.setEmail(personnelMedical.getEmail());
        personnelMedicalResponseDto.setPhoto(personnelMedical.getPhoto());

        personnelMedicalResponseDto.setTelephone(personnelMedical.getTelephone());

        personnelMedicalResponseDto.setUsername(personnelMedical.getUsername());
        personnelMedicalResponseDto.setFonction(personnelMedical.getFonction());
        personnelMedicalResponseDto.setService(personnelMedical.getService());
        personnelMedicalResponseDto.setTitre(personnelMedical.getTitre());
        personnelMedicalResponseDto.setLastUpdatedAt(personnelMedical.getLastUpdatedAt());
        personnelMedicalResponseDto.setPostedAt(personnelMedical.getPostedAt());
        personnelMedicalResponseDto.setAccueil(personnelMedical.getAccueil());
        personnelMedicalResponseDto.setPaiement(personnelMedical.getPaiement());
        personnelMedicalResponseDto.setSalleAttente(personnelMedical.getSalleAttente());
        personnelMedicalResponseDto.setServiceConsultation(personnelMedical.getServiceConsultation());
        List<String> roles = new ArrayList<String>();
        for (Role role: personnelMedical.getRoles()) {
            roles.add(role.getName().name());
        }
        personnelMedicalResponseDto.setRoles(roles);

        // utilisateurResponseDto.setPassword(utilisateur.getPassword());

        return personnelMedicalResponseDto;
    }

    public static List<PersonnelMedicalResponseDto> personnelMedicalResponseDtos(List<PersonnelMedical> personnelMedicals){
        List<PersonnelMedicalResponseDto> personnelMedicalResponseDtos = new ArrayList<>();
        for (PersonnelMedical personnel: personnelMedicals) {
            personnelMedicalResponseDtos.add(personnelMedicalToPersonnelMedicalResponseDto(personnel) );
        }
        return personnelMedicalResponseDtos;

    }
    public static List<UtilisateurResponseDto> utilisateursToUtilisateurResponseDtos(List<Utilisateur> utilisateurs){
        List<UtilisateurResponseDto> utilisateurResponseDtos = new ArrayList<>();
        for (Utilisateur utilisateur: utilisateurs) {
           utilisateurResponseDtos.add(utilisateurToUtilisateurResponseDto(utilisateur) );
        }
        return utilisateurResponseDtos;

    }

    public static DossierMedicalResponseDto dossierMedicalToDossierMedicalResponseDto(DossierMedical dossierMedical) {
        DossierMedicalResponseDto dossierMedicalResponseDto = new DossierMedicalResponseDto();
        dossierMedicalResponseDto.setId(dossierMedical.getId());
       dossierMedicalResponseDto.setNomPatient(dossierMedical.getNomPatient());
       dossierMedicalResponseDto.setPrenomPatient(dossierMedical.getPrenomPatient());
       dossierMedicalResponseDto.setConsultationEffectuee(dossierMedical.getConsultationEffectuee());
       dossierMedicalResponseDto.setResultatPrestation(dossierMedical.getResultatPrestation());

     /*  Patient patient = dossierMedical.getPatient();
       ServiceConsultation serviceConsultation=dossierMedical.getServiceConsultation();
       Consultant consultant=dossierMedical.getConsultant();*/

       dossierMedicalResponseDto.setConsultant(dossierMedical.getConsultant());
       dossierMedicalResponseDto.setServiceConsultation(dossierMedical.getServiceConsultation());
       dossierMedicalResponseDto.setPatient(dossierMedical.getPatient());

        return dossierMedicalResponseDto;
            }
    public static List<DossierMedicalResponseDto> dossierMedicalsToDossierMedicalResponseDtos(List<DossierMedical> dossierMedicals){
         List<DossierMedicalResponseDto> dossierMedicalResponseDtos = new ArrayList<>();
        for (DossierMedical dossierMedical: dossierMedicals) {
           dossierMedicalResponseDtos.add(dossierMedicalToDossierMedicalResponseDto(dossierMedical) );
        }
        return dossierMedicalResponseDtos;

    }
   /* public static DetailsPatientResponseDto detailsPatientToDetailsPatientResponseDto(DetailsPatient detailsPatient) {
        DetailsPatientResponseDto detailsPatientResponseDto = new DetailsPatientResponseDto();
      detailsPatientResponseDto.setId(detailsPatient.getId());
      detailsPatientResponseDto.setEtatDuPatient(detailsPatient.getEtatDuPatient());
      detailsPatientResponseDto.setHeurePriseCharge(detailsPatient.getHeurePriseCharge());
      detailsPatientResponseDto.setStatus(detailsPatient.getStatus());
      detailsPatientResponseDto.setServiceAffectation(detailsPatient.getServiceAffectation());

        return detailsPatientResponseDto;
    }*/

    public static List<DetailsPatientResponseDto> detailsPatientsToDetailsPatientResponseDtos(List<DetailsPatient> detailsPatients) {
        List<DetailsPatientResponseDto> detailsPatientResponseDtos = new ArrayList<>();
        for (DetailsPatient detailsPatient : detailsPatients) {
            detailsPatientResponseDtos.add(detailsPatientToDetailsPatientResponseDto(detailsPatient));
        }
        return detailsPatientResponseDtos;
    }
    public static ExamenResponseDto examenToExamenResponseDto(Examen examen) {
        ExamenResponseDto examenResponseDto= new ExamenResponseDto();
        examenResponseDto.setId(examen.getId());
        examenResponseDto.setNomExamen(examen.getNomExamen());
        examenResponseDto.setPrixExamen(examen.getPrixExamen());
        examenResponseDto.setPostedAt(examen.getPostedAt());
        examenResponseDto.setLastUpdatedAt(examen.getLastUpdatedAt());

      //  Ordonnance ordonnance = examen.getOrdonnance();
        examenResponseDto.setOrdonnances(examen.getOrdonnances());
        examenResponseDto.setPriseRDVs(examen.getPriseRDVs());
         return examenResponseDto;
    }
    public static List<ExamenResponseDto> examensToExamenResponseDtos(List<Examen> examens){
        List<ExamenResponseDto> examenResponseDtos = new ArrayList<>();
        for (Examen examen: examens) {
            examenResponseDtos.add(examenToExamenResponseDto(examen));
        }
        return examenResponseDtos;

    }
    public static MedicamentResponseDto medicamentToMedicamentResponseDto(Medicament medicament) {
        MedicamentResponseDto medicamentResponseDto= new MedicamentResponseDto();
       medicamentResponseDto.setId(medicament.getId());
       medicamentResponseDto.setNomMedicament(medicament.getNomMedicament());
       medicamentResponseDto.setDureeDePrise(medicament.getDureeDePrise());


        medicamentResponseDto.setOrdonnances(medicament.getOrdonnances());
        return medicamentResponseDto;
    }
    public static List<MedicamentResponseDto> medicamentsToMedicamentResponseDtos(List<Medicament> medicaments){
        List<MedicamentResponseDto> medicamentResponseDtos = new ArrayList<>();
        for (Medicament medicament: medicaments) {
            medicamentResponseDtos.add(medicamentToMedicamentResponseDto(medicament));
        }
        return medicamentResponseDtos;

    }

    public static PrestationResponseDto prestationToPrestationResponseDto(Prestation prestation) {
        PrestationResponseDto prestationResponseDto= new PrestationResponseDto();
        prestationResponseDto.setId(prestation.getId());
        prestationResponseDto.setNaturePrestation(prestation.getNaturePrestation());
        prestationResponseDto.setCoutPourAssure(prestation.getCoutPourAssure());
        prestationResponseDto.setCoutPourNonAssure(prestation.getCoutPourNonAssure());

        Facturation facturation = prestation.getFacturation();
        prestationResponseDto.setFacturationName(prestationResponseDto.getFacturationName());
        return prestationResponseDto;
    }
    public static List<PrestationResponseDto> prestationsToPrestationResponseDtos(List<Prestation> prestations){
        List<PrestationResponseDto> prestationResponseDtos = new ArrayList<>();
        for (Prestation prestation: prestations) {
            prestationResponseDtos.add(prestationToPrestationResponseDto(prestation));
        }
        return prestationResponseDtos;

    }

    public static PriseRDVResponseDto priseRDVToPriseRDVResponseDto(PriseRDV priseRDV) {
       PriseRDVResponseDto priseRDVResponseDto= new PriseRDVResponseDto();
       priseRDVResponseDto.setId(priseRDV.getId());
    /*   priseRDVResponseDto.setNomPatient(priseRDV.getNomPatient());
       priseRDVResponseDto.setPrenomPatient(priseRDV.getPrenomPatient());*/
       priseRDVResponseDto.setDateRDV(priseRDV.getDateRDV());
       priseRDVResponseDto.setHeureRDV(priseRDV.getHeureRDV());
       priseRDVResponseDto.setConsultant(priseRDV.getConsultant());
       priseRDVResponseDto.setPayed(priseRDV.getPayed());
       priseRDVResponseDto.setMotif(priseRDV.getMotif());
       priseRDVResponseDto.setFacturation(priseRDV.getFacturation());
       priseRDVResponseDto.setLastUpdatedAt(priseRDV.getLastUpdatedAt());

       priseRDVResponseDto.setPostedAt(priseRDV.getPostedAt());

       //ServiceConsultation serviceConsultation = priseRDV.getServiceConsultation();
        priseRDVResponseDto.setServiceConsultation(priseRDV.getServiceConsultation());

      //  Patient patient =priseRDV.getPatient();
        priseRDVResponseDto.setPatient(priseRDV.getPatient());
        priseRDVResponseDto.setExamen(priseRDV.getExamen());
        return priseRDVResponseDto;
    }
    public static List<PriseRDVResponseDto> priseRDVSToPriseRDVResponseDtos(List<PriseRDV> priseRDVS) {
        List<PriseRDVResponseDto> priseRDVResponseDtos = new ArrayList<>();
        for (PriseRDV priseRDV : priseRDVS) {
            priseRDVResponseDtos.add(priseRDVToPriseRDVResponseDto(priseRDV));
        }
        return priseRDVResponseDtos;
    }
  /*  public static PersonnelMedicalResponseDto personnelMedicalToPersonnelMedicalResponseDto(PersonnelMedical personnelMedical) {
        PersonnelMedicalResponseDto personnelMedicalResponseDto = new PersonnelMedicalResponseDto();
        personnelMedicalResponseDto.setId(personnelMedical.getId());
       personnelMedicalResponseDto.setService(personnelMedical.getService());
       personnelMedicalResponseDto.setFonction(personnelMedical.getFonction());
       personnelMedicalResponseDto.setTitre(personnelMedical.getTitre());

        Paiement paiement = personnelMedical.getPaiement();
        ServiceConsultation serviceConsultation=personnelMedical.getServiceConsultation();
        SalleAttente salleAttente=personnelMedical.getSalleAttente();
        Accueil accueil = personnelMedical.getAccueil();

       personnelMedicalResponseDto.setPaiementName(personnelMedicalResponseDto.getPaiementName());
       personnelMedicalResponseDto.setServiceConsultationName(personnelMedicalResponseDto.getServiceConsultationName());
      personnelMedicalResponseDto.setAccueilName(personnelMedicalResponseDto.getAccueilName());
      personnelMedicalResponseDto.setSalleAttenteName(personnelMedicalResponseDto.getSalleAttenteName());

        return personnelMedicalResponseDto;
    }*/
    public static List<PersonnelMedicalResponseDto> personnelMedicalsToPersonnelMedicalResponseDtos(List<PersonnelMedical> personnelMedicals){
        List<PersonnelMedicalResponseDto> personnelMedicalResponseDtos= new ArrayList<>();
        for (PersonnelMedical personnelMedical: personnelMedicals) {
            personnelMedicalResponseDtos.add(personnelMedicalToPersonnelMedicalResponseDto(personnelMedical) );
        }
        return personnelMedicalResponseDtos;

    }

}



