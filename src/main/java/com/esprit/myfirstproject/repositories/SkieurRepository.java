package com.esprit.myfirstproject.repositories;

import com.esprit.myfirstproject.entities.Skieur;
import com.esprit.myfirstproject.entities.enums.Couleur;
import com.esprit.myfirstproject.entities.enums.Support;
import com.esprit.myfirstproject.entities.enums.TypeAbonnement;
import com.esprit.myfirstproject.entities.enums.TypeCours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkieurRepository extends JpaRepository<Skieur, Long> {

    List<Skieur> findByAbonnement_TypeAbon(TypeAbonnement typeAbonnement); // récupérer des Skieurs selon un Type d'abonnement
//    1 condition <=> 1 paramêtre
//  Skieur contient comme attribut l'objet Abonnement, qui contient comme attribut l'objet TypeAbonnement.

//   on peut aussi écrire : List<Skieur> findByAbonnementTypeAbonnement(TypeAbonnement typeAbonnement);

    List<Skieur> findByAbonnement_TypeAbonAndPistes_CouleurAndInscriptions_Cours_TypeCoursAndInscriptions_Cours_Support(TypeAbonnement abonnement_typeAbonnement, Couleur pistes_couleur, TypeCours inscriptions_cours_typeCours, Support inscriptions_cours_support);

    //4 conditions donc 4 paramêtres

//    Il arrive parfois de vouloir récupérer un objet qui n'appartient pas aux attributs disponibles.


    @Query(value = "SELECT * " +
            "FROM skieur s " +
            "JOIN inscription i ON s.num_skieur = i.skieur_num_skieur " +
            "JOIN moniteur_cours mc ON i.cour_num_cours = mc.cours_num_cours " +
            "JOIN moniteur m ON m.num_moniteur=mc.moniteur_num_moniteur " +
            "WHERE m.nomm = :MoniteurName ", nativeQuery = true)
    List<Skieur> getSkieurbyMoniteurNameSQL(@Param("MoniteurName") String MoniteurName);


    @Query("SELECT i.skieur " +
            "FROM Inscription i " +
            "order by SIZE(i.skieur.pistes) ")
        //pas native donc JPQL
    List<Skieur> getSkieurbyMoniteurNameJPQL(@Param("MoniteurName") String MoniteurName);

}
