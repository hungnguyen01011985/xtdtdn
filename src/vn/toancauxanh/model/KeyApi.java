package vn.toancauxanh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Window;

@Entity
@Table(name = "keyapi")
public class KeyApi extends Model<KeyApi> {

	private String keyApi;

	
	public KeyApi() {	
	}

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
	public void saveKeyApi(@BindingParam("list") Object list, @BindingParam("attr") String attr, @BindingParam("wdn") Window wdn) {
		this.setKeyApi(getKeyApi(keyApi));
		if (wdn != null) {
			wdn.detach();
		}
		save();
		BindUtils.postNotifyChange(null, null, list, attr);
	}

}
