package HW9_simplePaint;

public class Page {
	private String fileAddress;
	private String username;

	public String getFileAddress() {
		return fileAddress;
	}

	public void setFileAddress(String fileAddress) {
		if (fileAddress != null)
			this.fileAddress = fileAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null)
			this.username = username;
	}

	public Page(String fA, String username) {
		setFileAddress(fA);
		setUsername(username);
	}

	public Page(String username) {
		setUsername(username);
	}
}
