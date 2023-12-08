package com.esprit.myfirstproject.repositories;

import com.esprit.myfirstproject.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {

//    JPQL = Jakarta Persistence API (QL = Querry Language)

    @Query(value = "SELECT *" +
            " FROM cours c" +
            " JOIN moniteur_cours_set mc ON c.num_cours=mc.cours_set_num_cours " +
            "JOIN moniteur m on m.num_moniteur=mc.moniteur_num_moniteur   " +
            "WHERE m.nomm = :nameMon ", nativeQuery = true)
        //native va conserver la forme intiale de la requête (pas JPQL => )
    List<Cours> getCoursByMoniteurSQL(@Param("nameMon") String name);

    //JPQL s'adapte à n'importe quelle base de donnée contrairment à SQL, qui ne s'adapte qu'au language SQL.
    //


    @Query( "SELECT c" +
            " FROM Cours  c" +
            " JOIN Moniteur m ON c member m.cours " + // member parce que Cours n'a pas d'attribut venant de Moniteur
            "WHERE m.nomM = :nameMon ")
        //pas native donc JPQL
    List<Cours> getCoursByMoniteurJPQL(@Param("nameMon") String nom);










    // JPQL propre à JPA , MySQL => nativeQuery = true

}
