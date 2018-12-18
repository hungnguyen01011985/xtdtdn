package vn.toancauxanh.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "keyapi")
public class KeyApi extends Model<KeyApi> {

	private String ten;

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}
	
}
