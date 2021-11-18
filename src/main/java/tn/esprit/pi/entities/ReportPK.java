package tn.esprit.pi.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class ReportPK implements Serializable{
	    private int idUser;
	    private int idPost;


	    public ReportPK() {
			super();
		}





		public ReportPK(int idUser, int idPost) {
			super();
			this.idUser = idUser;
			this.idPost = idPost;
		}





		public int getIdUser() {
			return idUser;
		}


		public void setIdUser(int idUser) {
			this.idUser = idUser;
		}


		public int getIdPost() {
			return idPost;
		}


		public void setIdPost(int idPost) {
			this.idPost = idPost;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + idPost;
			result = prime * result + idUser;
			return result;
		}





		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ReportPK other = (ReportPK) obj;
			if (idPost != other.idPost)
				return false;
			if (idUser != other.idUser)
				return false;
			return true;
		}





		@Override
		public String toString() {
			return "ReportPK [idUser=" + idUser + ", idPost=" + idPost + "]";
		}




	}
