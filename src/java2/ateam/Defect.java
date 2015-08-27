package java2.ateam;


public class Defect implements Comparable<Defect> {
	
	private int mId;
	private Status mStatus;
	private Priority mPriority;
	private User mAssigneeId;
	private String mSummary;
	private String mDescription;
	
	public Defect(int id, Status status, Priority priority, User assignedId, String summary, String description){
		
		mId = id;
		mStatus = status;
		mPriority = priority;
		mAssigneeId = assignedId;
		mSummary = summary;
		mDescription = description;
	}
	
	public int compareTo(Defect defect) {
		
		return mId - defect.getId();
	}
	
	/**** mId ***********/
	public int getId(){
		return mId;
	}
	
	public void setId(int id){
		
		mId = id;
	}
	
	/**** mStatus ***********/
	public Status getStatus() {
		return mStatus;
	}
	
	public void setStatus(Status status) {
		mStatus = status;
	}
	
	/**** mPriority ***********/
	public Priority getPriority() {
		return mPriority;
	}
	
	public void setPriority(Priority priority) {
		mPriority = priority;
	}
	
	/**** mAssigned ***********/
	public User getAssigneeId() {
		return mAssigneeId;
	}
	
	public void setAssigneeId(User assigneeId) {
		mAssigneeId = assigneeId;
	}
	
	/**** mSummary ***********/
	public String getSummary() {
		return mSummary;
	}
	
	public void setSummary(String summary) {
		mSummary = summary;
	}
	
	/**** mDescription ***********/
	public String getDescription() {
		return mDescription;
	}
	
	public void setDescription(String description) {
		mDescription = description;
	}

	

}
