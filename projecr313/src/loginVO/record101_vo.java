package loginVO;

public class record101_vo {
	private String cli_name;
	private String cli_id;
	private String cli_phone;
	private String cli_pw;
	
	public record101_vo(){}
	
	public String getCli_name() {
		return cli_name;
	}
	public void setCli_name(String cli_name) {
		this.cli_name = cli_name;
	}
	public String getCli_id() {
		return cli_id;
	}
	public void setCli_id(String cli_id) {
		this.cli_id = cli_id;
	}
	public String getCli_phone() {
		return cli_phone;
	}
	public void setCli_phone(String cli_phone) {
		this.cli_phone = cli_phone;
	}
	public String getCli_pw() {
		return cli_pw;
	}
	public void setCli_pw(String cli_pw) {
		this.cli_pw = cli_pw;
	}
	public record101_vo(String cli_name, String cli_id, String cli_phone, String cli_pw) {
		super();
		this.cli_name = cli_name;
		this.cli_id = cli_id;
		this.cli_phone = cli_phone;
		this.cli_pw = cli_pw;
	}
	

}
