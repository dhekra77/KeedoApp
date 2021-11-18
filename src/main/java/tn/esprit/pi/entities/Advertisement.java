package tn.esprit.pi.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Advertisement implements Serializable{
	
	
	private static final long serialVersionUID = 2329896158303253039L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int Id;
	private String canal;
	@Temporal(TemporalType.DATE)
	private Date beginningDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private int targetViews;
	private int views;
	
	@Enumerated(EnumType.STRING)
	private TypeAd typeAd;
	@ManyToOne
	private Event event;
	public Advertisement() {
		super();
	}

	public Advertisement(int id, String canal, Date beginningDate, Date endDate, int targetViews, int views) {
		super();
		Id = id;
		canal = canal;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.targetViews = targetViews;
		this.views = views;
	}

	public Advertisement(String canal, Date beginningDate, Date endDate, int targetViews, int views) {
		super();
		canal = canal;
		this.beginningDate = beginningDate;
		this.endDate = endDate;
		this.targetViews = targetViews;
		this.views = views;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		canal = canal;
	}

	public Date getBeginningDate() {
		return beginningDate;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getTargetViews() {
		return targetViews;
	}

	public void setTargetViews(int targetViews) {
		this.targetViews = targetViews;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
	
	
	
	public TypeAd getTypeAd() {
		return typeAd;
	}

	public void setTypeAd(TypeAd typeAd) {
		this.typeAd = typeAd;
	}

	@Override
	public String toString() {
		return "Advertisement [Id=" + Id + ", Canal=" + canal + ", beginningDate=" + beginningDate + ", endDate="
				+ endDate + ", targetViews=" + targetViews + ", views=" + views + "]";
	}


	
	
}
