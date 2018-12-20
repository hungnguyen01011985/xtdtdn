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
import vn.toancauxanh.model.DonViSoNoiVu;
import vn.toancauxanh.model.QDonViSoNoiVu;

public class DonViSoNoiVuService extends BasicService<DonViSoNoiVu> {
	
	public JPAQuery<DonViSoNoiVu> getTargetQuery() {
		String param = MapUtils.getString(argDeco(), "tuKhoa", "").trim();
		JPAQuery<DonViSoNoiVu> q = find(DonViSoNoiVu.class);

		if (param != null && !param.isEmpty() && !"".equals(param)) {
			String tuKhoa = "%" + param + "%";
			q.where(QDonViSoNoiVu.donViSoNoiVu.tenDonVi.like(tuKhoa)
					.or(QDonViSoNoiVu.donViSoNoiVu.tenCapDonVi.like(tuKhoa)));
		}

		q.orderBy(QDonViSoNoiVu.donViSoNoiVu.ngaySua.desc());
		return q;
	}
	
	@Command
	public void getAllPhongThuocDonVi() {
		try {
			ThamSo maDonViBXTDTDN = find(ThamSo.class).where(QThamSo.thamSo.ma.eq("MA_DON_VI_BXTDTDN")).fetchFirst();
			String maSoDonVi = maDonViBXTDTDN != null ? maDonViBXTDTDN.getValue().trim().toString() : "00033";

			URL url;
			URLConnection connection;
			BufferedReader bfr;
			Document doc;

			url = new URL(Application.app.getApiDonVi() + "&parent_maso=" + maSoDonVi);
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
			NodeList donViNodes = array.getFirstChild().getChildNodes();

			for (int temp = 0; temp < donViNodes.getLength(); temp++) {
				Node nNode = donViNodes.item(temp);
				if (nNode.getNodeName().equals("donvi")) {
					DonViSoNoiVu dv = new DonViSoNoiVu(nNode);
					long count = find(DonViSoNoiVu.class).where(QDonViSoNoiVu.donViSoNoiVu.tenDonVi.eq(dv.getTenDonVi())).fetchCount();
					if (count == 0) {
						dv.saveNotShowNotification();
					}
				}
			}
			BindUtils.postNotifyChange(null, null, this, "targetQuery");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
