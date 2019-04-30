package nano.dto;

import java.util.List;

public class GetAllDTO<T> {
	private int maxPage;
	private List<T> list;

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	

}
