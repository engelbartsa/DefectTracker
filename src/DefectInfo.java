import java.time.LocalDateTime;

//Libby Lundgren

public class DefectInfo {
	
	private int defectID;
	private String userID;
	private String summary;
	private String description;
	private String priority;
	private String status;
//	private LocalDateTime openDate;
	private String openDate;
	private String closeDate;
	private String reporterID;
	private String assigneeID;
	private String comments;
	private String openDt;
	private String changeLog;
	
//Heather changed this constructor so we are not passing defect_id, open_date, or close_date on an add defect.  The table handles those
//fields so we don't need to pass them to the insert statement.
	public DefectInfo(String tempReporterID, String tempDefectSummary, String tempDetailDescription, String tempAssignee, String tempStatus, String tempPriority, String tempComments){
		reporterID = tempReporterID;
		summary = tempDefectSummary;
		description = tempDetailDescription;
		priority = tempPriority;
		status = tempStatus;
		assigneeID = tempAssignee;
		comments = tempComments;
	}
	
// Heather added this constructor to use string for the tempOpenDate.  We need to play with that abit.  this will be needed on the view,
// only I don't know if the veiw will need the dates as a string or datetime field like we have in the constructor below this one.  
// As we are working through the view we will know this which one we will need.
	public DefectInfo(int tempDefectID, String tempOpenDate, String tempCloseDate, String tempReporterID, String tempDefectSummary, String tempDetailDescription, String tempAssignee, String tempStatus, String tempPriority, String tempComments){
		defectID = tempDefectID;
		openDate = tempOpenDate;
		closeDate = tempCloseDate;
		reporterID = tempReporterID;
		summary = tempDefectSummary;
		description = tempDetailDescription;
		priority = tempPriority;
		status = tempStatus;
		assigneeID = tempAssignee;
		comments = tempComments;
	}
	
//Heather commented this out.
/*	public DefectInfo(int tempDefectID, LocalDateTime tempOpenDate, String tempCloseDate, String tempReporterID, String tempDefectSummary, String tempDetailDescription, String tempAssignee, String tempStatus, String tempPriority, String tempComments){
		defectID = tempDefectID;
		openDate = tempOpenDate;
		closeDate = tempCloseDate;
		reporterID = tempReporterID;
		summary = tempDefectSummary;
		description = tempDetailDescription;
		priority = tempPriority;
		status = tempStatus;
		assigneeID = tempAssignee;
		comments = tempComments;
	}*/
	
	
	public DefectInfo(int tempDefectID, String tempReporterID, String tempChangeLog){
		defectID = tempDefectID;
		reporterID = tempReporterID;
		changeLog = tempChangeLog;
	}

	public int getDefectID() {
		return defectID;
	}

	public void setDefectID(int defectID) {
		this.defectID = defectID;
	}
	
// Heather don't think we need these, we called the id assignee id and reporter id.
/*	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}*/

	
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReporterID() {
		return reporterID;
	}

	public void setCreatorID(String reporterID) {
		this.reporterID = reporterID;
	}

	public String getAssigneeID() {
		return assigneeID;
	}

	public void setAssigneeID(String assigneeID) {
		this.assigneeID = assigneeID;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(String closeDate) {
		this.closeDate = closeDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getChangeLog() {
		return changeLog;
	}

	public void setChangeLog(String changeLog) {
		this.changeLog = changeLog;
	}


	@Override
	public String toString() {
		return "Defect ID = " + defectID + "; Open Date = " + openDate + "; Close Date = " + closeDate + "; Reporter ID = " + reporterID 
				+"; Summary = " + summary + ", Description = " + description + ", Priority = " + priority + ", Status = " + status 
				+", Assignee ID=" + assigneeID + ", Comments=" + comments;
	}
}
