package vn.toancauxanh.rest.model;

import java.util.ArrayList;
import java.util.List;

public class PagingObject<T> {

	private Long total;
	private Integer totalPage;
	private List<T> data = new ArrayList<>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
