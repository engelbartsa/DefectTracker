//Libby Lundgren

public class UserInfo {
	
	private String userID;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	
	
	public UserInfo(String uID, String fn, String ln, String r, String e){
		userID = uID;
		firstName = fn;
		lastName = ln;
		role = r;
		email = e;
	}

//Heather Added this.  Thought it was needed but may not be.
	public UserInfo(String uID){
		userID = uID;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
//Heather changed this toString as this is what displays on the assigneeId and reporterId drop downs, it wasn't easy to read so I changed
//it up. 
	public String toString() {
//		return "UserInfo [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
//				+ ", role=" + role + "]";
		return userID + "; " + firstName + " " + lastName + "; " + role + "; " + email;
	}

}
