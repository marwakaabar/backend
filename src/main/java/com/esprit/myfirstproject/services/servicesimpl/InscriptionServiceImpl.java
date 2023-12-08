package com.esprit.myfirstproject.services.servicesimpl;

import com.esprit.myfirstproject.entities.Cours;
import com.esprit.myfirstproject.entities.Inscription;
import com.esprit.myfirstproject.entities.Skieur;
import com.esprit.myfirstproject.entities.enums.TypeCours;
import com.esprit.myfirstproject.repositories.CoursRepository;
import com.esprit.myfirstproject.repositories.InscriptionRepository;
import com.esprit.myfirstproject.repositories.SkieurRepository;
import com.esprit.myfirstproject.services.InscriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InscriptionServiceImpl implements InscriptionService {


    private final InscriptionRepository inscriptionRepository;

    private final SkieurRepository skieurRepository;

    private final CoursRepository coursRepository;


    @Override
    public Inscription addInscription(Inscription ins) {
        return inscriptionRepository.save(ins);
    }

    @Override
    public Inscription updateInscription(Inscription ins) {
        return inscriptionRepository.save(ins);
    }

    @Override
    public List<Inscription> getAll() {
        return inscriptionRepository.findAll();
    }

    @Override
    public Inscription getId(Long id) {
        return inscriptionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Inscription " + id + " INEXISTANTE !!!"));
    }

    @Override
    public boolean deleteById(Long id) {
        inscriptionRepository.deleteById(id);
        return !(inscriptionRepository.existsById(id));
    }

    //méthodes avancées

    @Override
    public Inscription addInscriptionAndAssignToSkieur(Long idSkieur, Inscription inscription) {

//        ici on crée un nouvel objet de type inscription (n'appartient pas à la BD) => donc pas besoin de @Transactionnal


        Skieur skieur = skieurRepository.findById(idSkieur).orElse(null); //peremt de récupérer le skieur selon l'id passée en paramêtre

        inscription.setSkieur(skieur); // on sauvegarde la valeur de skeiur dans l'insscrption var inscription gère skieur (Voir cardinalités)
        return inscriptionRepository.save(inscription); // on sauvegarde l'inscription => preuve qu'on utilise aps @Transactionnal
    }

    @Override
    @Transactional // permet d'annuler toute transaction de la méthode ci-dessous si cette dernière est anodine
    public Inscription assignInscriptionToCourse(Long numInscription, Long numCours) {

//        ici, on a 2 id comme paramêtres. Ces id proviennent d'objets déjà existants (NON créés)=> par conséquent
//        ils proviennent de la BD => on rajoute @Transactionnal

//      1ère Etape : la recherche des objets

        Cours cours = coursRepository.findById(numCours).orElse(null);//ou .get() pemet de récupérer le tuple de la BD(et génère un exception ). On peut aussi ecrire à sa place : orElse(null)
        Inscription inscription = inscriptionRepository.findById(numInscription).orElse(null);


//      2ème étape: 'affectation d'un objet dans l'autre selon la conception INITIALE (PAS LA METHODE !!!!)

        inscription.setCours(cours); // ici on sauvegarde le cours dans l'inscription (pas le contraire)
        // même si la méthode est dîte de manière contraire (sauvegarde l'inscription dans Cours),
        // il ne faut regarder que LA CONCEPTION INITIALE (Inscription gère Cours donc clé étrangère cours dans Inscription)

//        inscriptionRepository.save(inscription); => plus la peine de l'écrire car @Transactional s'en occupera.


//      Etape 3 : le return
        return inscription;
    }

    @Override
    public Inscription addInscriptionAndAssignToSkierAndCourse(Inscription inscription, Long numSkieur, Long numCours) {

//        pas de @Transactional car nouvel objet créé

        Skieur skieur = skieurRepository.findById(numSkieur).orElse(null);
        Cours cours = coursRepository.findById(numCours).orElse(null);


        if (Period.between(skieur.getDateNaissance(), LocalDate.now()).getYears() > 18 &&
                (cours.getTypeCours() != TypeCours.COLLECTIF_ENFANT && cours.getTypeCours() != TypeCours.COLLECTIF_ADULTE ||
                        cours.getInscriptions() == null || cours.getInscriptions().size() < 6)) {
            inscription.setSkieur(skieur);
            inscription.setCours(cours);
        } else {
            // Gérer le cas où la condition n'est pas satisfaite
            return null; // Ou lever une exception appropriée
        }

        return inscriptionRepository.save(inscription);
    }
    //Les objets cours et inscription sont appelés Managed Entities => ont le choix entre commit et rollback

}
