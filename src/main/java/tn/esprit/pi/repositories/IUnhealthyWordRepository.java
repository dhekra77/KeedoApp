package tn.esprit.pi.repositories;

import tn.esprit.pi.entities.UnhealthyWord;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnhealthyWordRepository extends CrudRepository<UnhealthyWord, Integer> {
	
	@Transactional
	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from UnhealthyWord u where u.word =:word")
    void deleteByWordIs(@Param("word") String word);
	
    boolean existsByWord(String word);
}