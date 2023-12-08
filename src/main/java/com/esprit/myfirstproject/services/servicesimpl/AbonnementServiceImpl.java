package com.esprit.myfirstproject.services.servicesimpl;

import com.esprit.myfirstproject.entities.Abonnement;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;
import com.esprit.myfirstproject.repositories.AbonnementRepository;
import com.esprit.myfirstproject.services.AbonnementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor // pour l'injection des dépendances niveau constructor
@Service
@Slf4j
public class AbonnementServiceImpl implements AbonnementService {

    //    @Autowired // injection Dépendance niveau attribut
    private final AbonnementRepository abonnementRepository; // on rajoute private final pr qu'il n'yait pas de changement au niveau repository


//    Si on utilise @RequiredArgsConstructor(injection dependance niveau constructeur)
//    => on supprime @Autowired et on écrit pr chaque repository :
//    private final AbonnementRepository repository; (on n'oublie le private final) (par exemple)
//    On l'utilise si on a plusieurs Repository

//    Si on utilise @Autowired(injection par champs)
//    => on supprime @RequiredArgsConstructor et on écrit pr chaque repository :
//    AbonnementRepository repository; (par exemple)
//    utilisé lorsqu'on a un seul  repository (moins de code)


    @Override
    public Abonnement addAbonnement(Abonnement a) {
        return abonnementRepository.save(a);
    }

    @Override
    public Abonnement updateAbonnement(Abonnement a) {
        return abonnementRepository.save(a);
    }

    @Override
    public List<Abonnement> getAll() {
        return abonnementRepository.findAll();
    }

    @Override
    public Abonnement getId(Long id) {
        return abonnementRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Abonnement " + id + " INEXISTANT !!!"));
    }

    @Override
    public boolean deleteById(Long id) {
        abonnementRepository.deleteById(id);
        return !(abonnementRepository.existsById(id));
    }

    @Override
    public Set<Abonnement> getAbonnementByType(TypeAbonnement typeAbonnement) { // pas de @Transactional car getMapping

        List<Abonnement> abonnements = abonnementRepository.findByTypeAbonOrderByDateDebut(typeAbonnement);
//
//        abonnements.removeIf(abonnement -> !abonnement.getTypeAbonnement().equals(typeAbonnement));
//
//        abonnements.sort(Comparator.comparing(Abonnement::getDateDebut));

//      ligne au-dessus similiare à ligne en dessous

//      abonnements.sort((abonnement1, abonnement2) -> abonnement1.getDateDebut().compareTo(abonnement2.getDateDebut()));

//      Convertir la liste triée en un ensemble

        return new HashSet<>(abonnements);
    }

    @Override
    public List<Abonnement> retrieveSubscriptionsByDates(LocalDate startDate, LocalDate endDate) {

        List<Abonnement> abonnements = abonnementRepository.findByDateDebutAfterAndDateFinBefore(startDate, endDate);
//
//        abonnements.removeIf(abonnement -> abonnement.getDateDebut().isBefore(startDate) || abonnement.getDateFin().isAfter(endDate));
//
////        for (Abonnement abonnement : abonnements) {
////            if (abonnement.getDateDebut().compareTo(startDate) < 0 || abonnement.getDateFin().compareTo(endDate) > 0) {
////                abonnements.remove(abonnement);
////            }
////        }
        return abonnements;
    }
//    @Override
//    @Scheduled(fixedRate = 60000)
//    public void testscheduler(){
//        List<Abonnement> abonnements = abonnementRepository.findAll();
//        for (Abonnement abonnement : abonnements){
//            log.info(String.valueOf(abonnement.getPrixAbon()));
//        }
//    }


}
