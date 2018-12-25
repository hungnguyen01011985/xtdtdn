package vn.toancauxanh.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.collections.MapUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;

import com.querydsl.jpa.impl.JPAQuery;

import vn.toancauxanh.Application;
import vn.toancauxanh.gg.model.QThamSo;
import vn.toancauxanh.gg.model.ThamSo;
import vn.toancauxanh.model.CongChucSoNoiVu;
import vn.toancauxanh.model.QCongChucSoNoiVu;

public class CongChucSoNoiVuService extends BasicService<CongChucSoNoiVu> {
	
	public JPAQuery<CongChucSoNoiVu> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<CongChucSoNoiVu> q = find(CongChucSoNoiVu.class);

		if (param != null && !param.isEmpty() && !"".equals(param)) {
			q.where(QCongChucSoNoiVu.congChucSoNoiVu.email.like("%" + param.trim() + "%"));
		}

		q.orderBy(QCongChucSoNoiVu.congChucSoNoiVu.ngaySua.desc());
		return q;
	}
	
	@Command
	public void getCongChucTheoThuocSoNoiVu() {
		try {
			ThamSo idDonViBXTDTDN = find(ThamSo.class).where(QThamSo.thamSo.ma.eq("ID_DON_VI_BXTDTDN")).fetchFirst();
			String donViId = idDonViBXTDTDN != null ? idDonViBXTDTDN.getValue() : "151710";
			
			String EXACT_DONVI = "&exact_donvi=true";
			String NO_EXACT_DONVI = "&exact_donvi=false";

			URL url;
			URLConnection connection;
			BufferedReader bfr;
			Document doc;
			
			boolean donViOnly = true;
			
			String urlStr = Application.app.getApiCongChuc() + "&donvi=" + donViId + NO_EXACT_DONVI;
			if (donViOnly) {
				urlStr = Application.app.getApiCongChuc() + "&donvi=" + donViId + EXACT_DONVI;
			}
			
			url = new URL(urlStr);
			connection = url.openConnection();
			connection.setRequestProperty("accept", "application/xml");
			bfr = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String inputLine;
			String output;
			StringBuilder sb = new StringBuilder();
			while ((inputLine = bfr.readLine()) != null) {
				sb.append(inputLine);
			}
			bfr.close();
			output = sb.toString();
			doc = getDOMDocument(output);
			Node root = doc.getDocumentElement().getFirstChild();
			Document array = getDOMDocument(root.getNodeValue());
			NodeList congChucNodes = array.getFirstChild().getChildNodes();

			for (int temp = 0; temp < congChucNodes.getLength(); temp++) {
				Node nNode = congChucNodes.item(temp);
				if (nNode.getNodeName().equals("ccvc")) {
					CongChucSoNoiVu cc = new CongChucSoNoiVu(nNode);
					long count = find(CongChucSoNoiVu.class).where(QCongChucSoNoiVu.congChucSoNoiVu.email.isNotNull()
							.and(QCongChucSoNoiVu.congChucSoNoiVu.email.isNotEmpty())
							.and(QCongChucSoNoiVu.congChucSoNoiVu.email.eq(cc.getEmail()))
							.or((QCongChucSoNoiVu.congChucSoNoiVu.email.isNull()
									.or(QCongChucSoNoiVu.congChucSoNoiVu.email.isEmpty()))
									.and(QCongChucSoNoiVu.congChucSoNoiVu.hoTen.eq(cc.getHoTen())))).fetchCount();
					if (count == 0) {
						cc.saveNotShowNotification();
					}
				}
			}
			BindUtils.postNotifyChange(null, null, this, "targetQuery");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
