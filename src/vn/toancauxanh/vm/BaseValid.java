package vn.toancauxanh.vm;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.sys.ValidationMessages;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.SimpleConstraint;
import org.zkoss.zul.mesg.MZul;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.model.Model;
import vn.toancauxanh.model.QModel;
import vn.toancauxanh.service.BasicService;

public class BaseValid extends AbstractValidator {

	@Override
	public void validate(final ValidationContext ctx) {
		final ValidationMessages vmsgs = (ValidationMessages) ctx.getValidatorArg("vmsg");
		
		if (vmsgs != null) {
			System.out.println("zô messs");
			if (vmsgs.getKeyMessages("fileGiaoViec") != null) {
				System.out.println("key file:"+vmsgs.getKeyMessages("fileGiaoViec"));
			}
			
			vmsgs.clearKeyMessages(Throwable.class.getSimpleName());
			vmsgs.clearMessages(ctx.getBindContext().getComponent());
		}
		validateConstraint(ctx);
		validateNamSinh(ctx);
		validateNgaySinh(ctx);
		validateUnique(ctx);
		validatePasswords(ctx);
		validateEmail(ctx);
	}

	private boolean validateConstraint(final ValidationContext ctx) {
		boolean result;
		final Object constraint = ctx.getValidatorArg("constraint"); // Lấy dữ liệu biến param đặt trong @Validator
		System.out.println("vào basevalid constraint");
		if (constraint == null) {
			result = true;
		} else {
			System.out.println("value"+ctx.getProperty().getValue());
			try {
				new SimpleConstraint(constraint.toString()).validate(null, ctx.getProperty().getValue());
				result = true;
			} catch (final WrongValueException ex) {
				Object msg;
				Object msgid = ctx.getValidatorArg("msgid");
				if (ex.getCode() == MZul.EMPTY_NOT_ALLOWED) {
					if (msgid == null) {
						msg = "Không được để trống trường này.";
					} else {
						msg = "Vui lòng nhập đầy đủ thông tin!";
					}

				} else if (ex.getCode() == MZul.NO_TODAY) {
					msg = "no today";
				} else if (ex.getCode() == MZul.NO_NEGATIVE) {
					msg = "Vui lòng nhập số dương";
				} else if (ex.getCode() == MZul.NO_POSITIVE) {
					msg = "no positive";
				} else if (ex.getCode() == MZul.NO_FUTURE) {
					msg = "Không được lớn hơn ngày hiện tại";
				} else if (ex.getCode() == MZul.NO_ZERO) {
					msg = "Vui lòng nhập lớn hơn 0";
				} else if (ex.getCode() == MZul.NO_PAST) {
					msg = "Không được nhỏ hơn ngày hiện tại";
				} else if (ex.getCode() == MZul.NO_POSITIVE_ZERO) {
					msg = "NO_POSITIVE_ZERO";
				} else if (ex.getCode() == MZul.NO_POSITIVE_NEGATIVE_ZERO) {
					msg = "NO_POSITIVE_NEGATIVE_ZERO";
				} else if (ex.getCode() == MZul.NO_POSITIVE_NEGATIVE) {
					msg = "NO_POSITIVE_NEGATIVE";
				} else if (ex.getCode() == MZul.NO_FUTURE_TODAY) {
					msg = "NO_FUTURE_TODAY";
				} else if (ex.getCode() == MZul.NO_PAST_TODAY) {
					msg = "NO_PAST_TODAY";
				} else if (ex.getCode() == MZul.NO_FUTURE_PAST_TODAY) {
					msg = "NO_FUTURE_PAST_TODAY";
				} else if (ex.getCode() == MZul.NO_FUTURE_PAST) {
					msg = "NO_FUTURE_PAST";
				} else if (ex.getCode() == MZul.VALUE_NOT_MATCHED) {
					msg = "VALUE_NOT_MATCHED";
				} else if (ex.getCode() == 655433733) {
					msg = "Chỉ được nhập số dương";
				} else if (ctx.getValidatorArg("cmsg") == null) {
					msg = ex.getMessage();
				} else {
					msg = ctx.getValidatorArg("cmsg");
				}
				java.util.logging.Logger.getAnonymousLogger().info(ctx.getBindContext().getComponent() + "");
				java.util.logging.Logger.getAnonymousLogger().info(ctx.getBindContext().getComponent().getId() + "");
				if (msgid == null) {
					addInvalidMessage(ctx,msg.toString());
					addInvalidMessage(ctx, "errorEmpty",msg.toString());
				} else {
					addInvalidMessage(ctx, msgid.toString(), msg.toString());
				}
				result = false;
			}
		}
		return result;
	}

	private boolean validatePasswords(final ValidationContext ctx) {
		final Object retype = ctx.getValidatorArg("password");
		boolean result;
		if (retype == null) {
			result = true;
		} else {
			Object pass = ctx.getProperty().getValue();
			if (pass == null) {
				pass = "";
			}
			if (retype.equals(pass)) {
				result = true;
			} else {
				addInvalidMessage(ctx, "Xác nhận mật khẩu không trùng khớp!");
				addInvalidMessage(ctx,"errorPass", "Xác nhận mật khẩu không trùng khớp!");
				result = false;
			}
		}
		return result;
	}

	private boolean validateEmail(final ValidationContext ctx) {
		String email = (String) ctx.getValidatorArg("email");
		boolean result;
		if (email == null || email.trim().matches(".+@.+\\.[a-z]+")) {
			result = true;
		} else {
			addInvalidMessage(ctx, "Địa chỉ email không đúng định dạng.");
			result = false;
		}
		return result;
	}

	// private boolean validateCaptcha(final ValidationContext ctx) {
	// String captcha = (String) ctx.getValidatorArg("captcha");
	// System.out.println("captcha: " + captcha);
	// final String recaptcha = (String) ctx.getValidatorArg("recaptcha");
	// System.out.println("recaptcha: " + recaptcha);
	//
	// boolean result;
	// if (captcha != null && captcha.equals(recaptcha)) {
	// result = true;
	// } else {
	// addInvalidMessage(ctx, "captchaerr", "Mã kiểm tra không đúng.");
	// result = false;
	// }
	// System.out.println("ket qua: " + result);
	// return result;
	// }

	private boolean validateUnique(final ValidationContext ctx) {
		boolean result;
		final Object field = ctx.getValidatorArg("field");
		if (field == null) {
			result = true;
		} else if (ctx.getProperty().getValue() != null) {
			final Model<?> object = (Model<?>) ctx.getValidatorArg("id");
			BasicService<Object> basicService = new BasicService<>();
			JPAQuery<?> q = basicService.find(object.getClass());
			Path<?> path = (Path<?>) q.getMetadata().getJoins().get(0).getTarget();
			PathBuilder<?> pb = new PathBuilder<>(object.getClass(), path.getMetadata().getName());
			// Model<?> a = alias(object.getClass(),
			// path.getMetadata().getName());
			// q =
			// q.where($(a.getTrangThai()).ne(basicService.core().TT_DA_XOA))
			// .where($(a.getId()).ne(object.getId()))
			q = q.where(pb.get(QModel.model.trangThai).ne(basicService.core().TT_DA_XOA))
					.where(pb.get(QModel.model.id).ne(object.getId()))
					.where(Expressions.path(Object.class, path, field.toString()).eq(ctx.getProperty().getValue()));
			if (q.fetchCount() > 0) {
				String replaceMsgs = (String) ctx.getValidatorArg("cmsg");
				if (replaceMsgs != null || !"".equals(replaceMsgs)) {
					addInvalidMessage(ctx, replaceMsgs);
				} else {
					addInvalidMessage(ctx, "Giá trị này đã tồn tại trong hệ thống.");
				}

				result = false;
			} else {
				result = true;
			}
		} else {
			result = true;
		}

		return result;
	}
	
	private boolean validateNamSinh(final ValidationContext ctx) {
		boolean result = true;
		String messg = "Năm sinh không được lớn hơn năm hiện tại";
		final Boolean flag = (Boolean) ctx.getValidatorArg("flagNamSinh");
		String namSinhStr = (String) ctx.getValidatorArg("namSinh");
		Date now = new Date();
		if (flag == null || flag==false) {
			return true;
		}
		if (namSinhStr != null && !namSinhStr.isEmpty()) {
			int namSinh = Integer.parseInt(namSinhStr);
			if (namSinh > DateUtils.toCalendar(now).get(Calendar.YEAR)) {
				addInvalidMessage(ctx, messg);
				result = false;
			} else if (DateUtils.toCalendar(now).get(Calendar.YEAR) - namSinh > 150) {
				addInvalidMessage(ctx, "Tuổi đã vượt quá 150");
				result = false;
			}
		}
		return result;
	}
	
	private boolean validateNgaySinh(final ValidationContext ctx) {
		boolean result = true;
		String messg = "Không được để trống trường này.";
		String messg1 = "Ngày sinh không được lớn hơn ngày hiện tại.";
		String messg2 = "Tuổi đã vượt quá 150.";
		Date now = new Date();
		Date ngaySinh = (Date) ctx.getValidatorArg("ngaySinhConstraint");
		int namSinh = 0;
		final Boolean flag = (Boolean) ctx.getValidatorArg("flagBirth");
		if (flag == null || flag==false) {
			return true;
		} else {
			try {
				namSinh = (int) ctx.getValidatorArg("namSinhConstraint");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		if (ngaySinh == null && namSinh == 0) {
			addInvalidMessage(ctx, messg);
			result = false;
		} else {
			if (ngaySinh != null) {
				if (ngaySinh.after(now)) {
					addInvalidMessage(ctx, messg1);
					result = false;
				} else if (DateUtils.toCalendar(now).get(Calendar.YEAR) - DateUtils.toCalendar(ngaySinh).get(Calendar.YEAR) > 150){
					addInvalidMessage(ctx, messg2);
					result = false;
				}
			} else if (namSinh > 0) {
				int nam = 0;
				try {
					nam = namSinh;
					if (DateUtils.toCalendar(now).get(Calendar.YEAR) < nam) {
						addInvalidMessage(ctx, "Năm sinh không được lớn hơn năm hiện tại");
						result = false;
					} else if (DateUtils.toCalendar(now).get(Calendar.YEAR) - nam > 150) {
						addInvalidMessage(ctx, messg2);
						result = false;
					}
				} catch (Exception e) {
					addInvalidMessage(ctx, "errNgaySinh", "Năm sinh không đúng");
					result = false;
				}
			}
		}
		return result;
	}
}
