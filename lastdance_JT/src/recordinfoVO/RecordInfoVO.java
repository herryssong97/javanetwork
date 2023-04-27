package recordinfoVO;

public class RecordInfoVO {
	// 속성
	private String cdName; // 음반 이름
	private String cdOrder; // 음반 가수
	private String cdPrice; // 음반 가격
	private String cdDate; // 음반 출시일
	private int cdSoo; // 음반 수량
	
	//-------------------------------------------------
	// default 생성자
	public RecordInfoVO() {
		
	}
	// 인자가 있는 생성자
	public RecordInfoVO(String cdName, String cdOrder, String cdPrice, String cdDate, int cdSoo) {
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
	public String getCdPrice() {
		return cdPrice;
	}
	public void setCdPrice(String cdPrice) {
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
	public void setCdSoo(int string) {
		this.cdSoo = string;
	}
	public String showinfo() {
		// TODO Auto-generated method stub
		System.out.println(cdOrder);
		return cdOrder;
	}
	
	
}
