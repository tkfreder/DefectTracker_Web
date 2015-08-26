package java2.ateam;

public class Status {
	
	private String statusId;
	private String statusName;
	
	public Status(String id, String name){
		
		this.setStatusId(id);
		this.setStatusName(name);
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}	
}

