package adminUpdateVO;

import java.util.Date;

public class AdminUpdateVO {
	private String cdName;
	private String cdOrder;
	private int cdPrice;
	private String cdDate;
	private int cdSoo;
	
	public AdminUpdateVO() {
		
	}

	public AdminUpdateVO(String cdName, String cdOrder, int cdPrice, String cdDate, int cdSoo) {
		super();
		this.cdName = cdName;
		this.cdOrder = cdOrder;
		this.cdPrice = cdPrice;
		this.cdDate = cdDate;
		this.cdSoo = cdSoo;
	
	}

	public String getCdName() {
		return cdName;
	}

	public void setCdName(String cdName) {
		this.cdName = cdName;
	}

	public String getCdOrder() {
		return cdOrder;
	}

	public void setCdOrder(String cdOrder) {
		this.cdOrder = cdOrder;
	}

	public int getCdPrice() {
		return cdPrice;
	}

	public void setCdPrice(int cdPrice) {
		this.cdPrice = cdPrice;
	}

	public String getCdDate() {
		return cdDate;
	}

	public void setCdDate(String cdDate) {
		this.cdDate = cdDate;
	}

	public int getCdSoo() {
		return cdSoo;
	}

	public void setCdSoo(int cdSoo) {
		this.cdSoo = cdSoo;
	}


	
	
}
