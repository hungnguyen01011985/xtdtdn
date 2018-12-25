package vn.toancauxanh.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zul.Window;

import com.querydsl.jpa.impl.JPAQuery;

@Entity
@Table(name = "keyapi")
public class KeyApi extends Model<KeyApi> {

	private String ten;
	private String keyApi;

	public KeyApi() {
	}
	
	public KeyApi(String ten, String keyApi) {
		super();
		this.ten = ten;
		this.keyApi = keyApi;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
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
		this.setKeyApi(getKeyApi(ten));
		if (wdn != null) {
			wdn.detach();
		}
		save();
		BindUtils.postNotifyChange(null, null, list, attr);
	}
	
	@Transient
	public AbstractValidator getValidateKeyApi() {
		return new AbstractValidator() {
			@Override
			public void validate(final ValidationContext ctx) {
				String value = (String) ctx.getProperty().getValue();
				if (value.isEmpty()||"".equals(value)) {
					addInvalidMessage(ctx, "Không được để trống trường này");
				}
				if (!value.isEmpty()) {
					JPAQuery<KeyApi> q = find(KeyApi.class).where(QKeyApi.keyApi1.ten.eq(value));
					if (!KeyApi.this.noId()) {
						q.where(QKeyApi.keyApi1.id.ne(getId()));
					}
					if (q.fetchCount() > 0) {
						addInvalidMessage(ctx, "Tên này đã được sử dụng");
					}
				}	
			}
		};
	}
}
