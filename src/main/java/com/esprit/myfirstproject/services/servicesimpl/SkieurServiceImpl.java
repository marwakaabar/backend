package com.esprit.myfirstproject.services.servicesimpl;

import com.esprit.myfirstproject.entities.*;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;
import com.esprit.myfirstproject.repositories.*;
import com.esprit.myfirstproject.services.SkieurService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class SkieurServiceImpl implements SkieurService {


    private final SkieurRepository skieurRepository;

    private final PisteRepository pisteRepository;

    private final CoursRepository coursrepository;

    private final InscriptionRepository inscriptionRepository;

    private final AbonnementRepository abonnementRepository;

    //    Les méthodes implémentées
    @Override
    public Skieur addSkieur(Skieur skieur) {
        return skieurRepository.save(skieur);
    }

    @Override
    public Skieur updateSkieur(Skieur skieur) {
        return skieurRepository.save(skieur);
    }

    @Override
    public List<Skieur> getAll() {
        return skieurRepository.findAll();
    }

    @Override
    public Skieur getSkieurbyId(Long id) {
        return skieurRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Skieur " + id + " INEXISTANT !!!"));
    }

    @Override
    public boolean deleteById(Long id) {
        skieurRepository.deleteById(id);
        return !(skieurRepository.existsById(id));
    }

    //    Les méthodes avancées
    @Transactional
    @Override
    public Skieur assignSkieurToPiste(Long numSkieur, Long numPiste) {

        Skieur skieur = skieurRepository.findById(numSkieur).orElse(null);
        System.out.println(skieur);
        Piste piste = pisteRepository.findById(numPiste).orElse(null);
        System.out.println(piste);
        skieur.getPistes().add(piste);
//        skieurRepository.save(skieur); => car @Transactionnal

        return skieur;

    }

    //@Transactional ne fonctionne que lorsque les objets sont récupérés à partir de la bd(managed ref),on leur modifie,puis on leur sauvegarde,
    //mais si on travail sur un nouveau objet passé en param, qui n'a pas de ref à partir de la bd, donc transactional ne fonctionne pas
    //pareil si on récupère un objet à partir de la bd, mais on le modifie et l'affecte à un nouveau objet(soit instancié ou passé en param)
    //donc transactional ne fonctionne pas dans ces 2 cas


    @Override
    public Skieur addSkieurAndAssignToCourse(Skieur skieur, Long numCourse) {
//      Etape 1 : recup des objets


        Cours cours = coursrepository.findById(numCourse).orElse(null);
        // Abonnement abonnement = skieur.getAbonnement(); // car il y a un attribut abonnement dans skieur
        // ligne au dessus facultative car relation de composition, sinon il est obligaoire pour le save !!!


        Inscription inscription = skieur.getInscriptions().stream().findFirst().orElse(null);
        // on a  tranformé le set en stream pour utiliser la méthode findFirst()
        // on peut aussi utiliser skieur.getInscriptions().stream()..get();

//      Etape 2 : recup des objets
        inscription.setSkieur(skieur);
        inscription.setCours(cours);

        skieurRepository.save(skieur);
        inscriptionRepository.save(inscription); // doit être sauvegardé car il est le pont entre le skieur et le cours

        return skieur;
    }

    @Override
    public List<Skieur> retrieveSkiersBySubscriptionType(TypeAbonnement typeAbonnement) {

//        pas @Transactional car pas de sauvegarde d'objets => pas de SAVE car getMapping

//      Etape 1 : on crée 1 liste contenant tous les skieurs (SkieursAbonnes)


        List<Skieur> skieursAbonnes = skieurRepository.findAll();


//      Etape 2 : on fait une boucle for sur la liste Skieurs et on teste pour chaque skieur son type d'abonnement (cond if)
//        attention le typeAbonnement est un string donc .equals()


        skieursAbonnes.removeIf(skieur -> !skieur.getAbonnement().getTypeAbon().equals(typeAbonnement));

        //        Cette Ligne au-dessus est similaire à la boule for en dessous

//        for (Skieur skieur : skieursAbonnes) {
//            if (!skieur.getAbonnement().getTypeAbonnement().equals(typeAbonnement)) {
//                skieursAbonnes.remove(skieur);
//            }
//        }

//
//      Etape 3 : on retourne la liste des Skieurs qui ont le même type d'abonnement que celui passé en paramètre

        return skieursAbonnes;
    }

    public List<Skieur> getSkieurbyMoniteurNameJPQL(String MoniteurName) {
        return skieurRepository.getSkieurbyMoniteurNameJPQL(MoniteurName);
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void testscheduler() {
        List<Skieur> skieurs = skieurRepository.findAll();
        for (Skieur skieur : skieurs){
            log.info("Skieur : " + skieur.getNumSkieur());
            log.info("Nom-Prénom : " + skieur.getNomS()+"-"+skieur.getPrenomS());
            log.info("Num Abonnement : " + String.valueOf(skieur.getAbonnement().getNumAbon())+"\n");
        }

    }

    @Override
    public LocalDate dateTest(LocalDate date) {
        log.info("Date : " + String.valueOf(date));
        return date;
    }
}
