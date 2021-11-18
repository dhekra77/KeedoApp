package tn.esprit.pi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Language;

@Repository
public interface LanguageRepository extends CrudRepository<Language, Integer> {

}
