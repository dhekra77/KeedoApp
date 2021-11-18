package tn.esprit.pi.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.pi.entities.Report;
import tn.esprit.pi.entities.ReportPK;

public interface IReportRepository extends CrudRepository <Report,ReportPK> {
	@Query("select count(r) from Report r where r.user.id =:idu and r.post.id =:idp")
	public int isReportExists(@Param("idu")int idu, @Param("idp")int idp);
	
	@Transactional
	@Modifying (clearAutomatically = true)
	@Query (value= "Delete from Report r where r.post.id= :id")
	public void deleteReport(@Param("id") int id);
	
	
	void deleteByPost(Integer id);
}