package vn.toancauxanh.service.thongbao;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.gg.model.enums.LoaiCongViec;
import vn.toancauxanh.gg.model.enums.LoaiThongBao;
import vn.toancauxanh.gg.model.enums.TrangThaiGiaoViec;
import vn.toancauxanh.model.GiaoViec;
import vn.toancauxanh.model.QGiaoViec;
import vn.toancauxanh.model.ThongBao;
import vn.toancauxanh.service.BasicService;

@Configuration
@EnableScheduling
public class ScheduledTask extends BasicService<ScheduledTask>{
	@Scheduled(cron = "0 0 12 * * *")
	public void kiemTraVaGuiThongBaoDenHanCongViec() {
		JPAQuery<GiaoViec> q = find(GiaoViec.class)
				.where(QGiaoViec.giaoViec.trangThaiGiaoViec.ne(TrangThaiGiaoViec.HOAN_THANH))
				.where(QGiaoViec.giaoViec.hanThucHien.eq(resetHourMinuteSecondMilli(new Date())));
		if (q.fetchCount() > 0) {
			q.fetch().forEach(item -> {
				ThongBao thongBao = new ThongBao();
				thongBao.setNoiDung("Công việc @" + item.getTenCongViec()
								+ "@ đã đến hạn hoàn thành.");
				thongBao.setNguoiNhan(item.getNguoiDuocGiao());
				if (LoaiCongViec.DU_AN.equals(item.getLoaiCongViec())) {
					thongBao.setIdObject(item.getDuAn().getId());
				} else {
					thongBao.setIdObject(item.getDoanVao().getId());
				}
				thongBao.setLoaiThongBao(LoaiThongBao.DEN_HAN_CONG_VIEC);
				thongBao.saveNotShowNotification();
			});
		}
		
	}
}
