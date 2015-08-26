package java2.ateam;

public class Priority {
	private int priorityId;
	private String name;

	public Priority(int priorityId, String name) {
		this.setPriorityId(priorityId);
		this.setName(name);
	}

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
