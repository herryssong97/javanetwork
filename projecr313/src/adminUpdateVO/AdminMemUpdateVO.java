package adminUpdateVO;

public class AdminMemUpdateVO {

	private String guestName;
	private String guestId;
	private String guestPw;
	private String phone;
	
	public AdminMemUpdateVO() {}
	
	public AdminMemUpdateVO(String guestName, String guestId, String guestPw, String phone) {
		super();
		this.guestName = guestName;
		this.guestId = guestId;
		this.guestPw = guestPw;
		this.phone = phone;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	public String getGuestPw() {
		return guestPw;
	}

	public void setGuestPw(String guestPw) {
		this.guestPw = guestPw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
