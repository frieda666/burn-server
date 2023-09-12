package edu.uci.banerjee.burnserver.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "*")
@RepositoryRestResource(collectionResourceRel = "escapedfires", path = "escapedfires")
public interface EscapedFireRepo extends JpaRepository<EscapedFire, Integer> {

  List<EscapedFire> findByEscapedTrue();

  List<EscapedFire> findByEscapedFalse();

    @Query(
        "SELECT e FROM EscapedFire e WHERE (:escaped is null or e.escaped = :escaped)")
    List<EscapedFire> findByEscapedParams(
        @Param("escaped") Boolean escaped);
      
}
