package tn.esprit.pi.services;

import tn.esprit.pi.entities.Report;
import tn.esprit.pi.repositories.IReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {
	@Autowired 
	IReportRepository reportRepository;

	@Override
	public List<Report> getReportList() {
        return (List<Report>) reportRepository.findAll();
	}

	@Override
	public void addReport(Report r) {
		reportRepository.save(r);		
	}

}