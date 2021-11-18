package tn.esprit.pi.services;

import org.springframework.stereotype.Service;
import tn.esprit.pi.entities.Report;

import java.util.List;

@Service
public interface IReportService {
    public List<Report> getReportList();
	public void addReport(Report r);
}