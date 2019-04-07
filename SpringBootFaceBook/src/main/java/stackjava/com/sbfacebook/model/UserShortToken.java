package stackjava.com.sbfacebook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_token")
@JsonIgnoreProperties
public class UserShortToken {
	
	@Column(name = "short_token")
	private String short_token;

	@Id
	@Column(name="iduser_token")
	private int iduser_token;

	public String getShort_token() {
		return short_token;
	}

	public void setShort_token(String short_token) {
		this.short_token = short_token;
	}

	public int getIduser_token() {
		return iduser_token;
	}

	public void setIduser_token(int iduser_token) {
		this.iduser_token = iduser_token;
	}
	
	

}
