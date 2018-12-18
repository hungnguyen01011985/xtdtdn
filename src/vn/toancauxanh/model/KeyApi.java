package vn.toancauxanh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.zkoss.bind.annotation.Command;

@Entity
@Table(name = "keyapi")
public class KeyApi extends Model<KeyApi> {

	private String keyApi;

	public String getKeyApi() {
		return keyApi;
	}

	public void setKeyApi(String keyApi) {
		this.keyApi = keyApi;
	}

	@Transient
	public String getKeyApi(String ten) {
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		String salKey = encryptor.encryptPassword(new Date().toString());
		return encryptor.encryptPassword(ten).concat(salKey);
	}
	
	@Command
	public void saveKeyApi() {
		this.setKeyApi(getKeyApi(keyApi));
		save();
	}

}
