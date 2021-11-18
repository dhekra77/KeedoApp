package tn.esprit.pi.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.pi.entities.Consultation;
import tn.esprit.pi.entities.Kid;
import tn.esprit.pi.entities.User;

@Repository
public interface ConsultationRepository extends CrudRepository<Consultation, Integer> {

	@Query("select new Consultation(c.idConsultation, c.dateConsultation, c.time) from Consultation c")
	List<Consultation> displayAllConsultations();

	@Query(" select c from Consultation c where c.kid = :kid")
	List<Consultation> findConsultationByKid(@Param("kid") Kid kid);

	@Transactional
	@Modifying
	@Query("DELETE from Consultation c where c.id= :id")
	void deleteConsultById(@Param("id") int id);

	@Query(" select c from Consultation c where c.doctor = :doctor and c.dateConsultation= :dateConsultation and c.time= :time")
	List<Consultation> findDoctorAvailable(@Param("doctor") User doctor,
			@Param("dateConsultation") Date dateConsultation, @Param("time") Date time);

	@Query(" select c from Consultation c where c.doctor = :doctor")
	List<Consultation> findConsultationByDoctor(@Param("doctor") User doctor);

	@Query(value = "SELECT * FROM Consultation WHERE date_consultation Like %?1%", nativeQuery = true)
	List<Consultation> findByMatchMonthAndMatchDay(@Param("month") String month);

	List<Consultation> findAllByDateConsultation(Date currentDate);
}
