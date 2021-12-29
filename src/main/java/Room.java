import java.io.Serializable;

public class Room implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String cdate;
	private int ctime;
	private boolean isFree;
	private int floor;
	private String userID;

	// CLASSROOM HANDLER FUNCTION.
	public Room(String id, boolean isFree, int floor) {
		this.setIsFree(isFree);
		this.setId(id);
		this.setFloor(floor);
	}

	public Room(String id, String cdate, int ctime, boolean isFree, int floor) {
		this.id = id;
		this.cdate = cdate;
		this.ctime = ctime;
		this.isFree = isFree;
		this.floor = floor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}

	public int getFloor() { return floor; }

	public void setFloor (int floor) { this.floor = floor; }

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public int getCtime() {
		return ctime;
	}

	public void setCtime(int ctime) {
		this.ctime = ctime;
	}

	public String getUserID() { return userID; }

	public void setUserID() { this.userID = userID; }

	@Override
	public String toString() {
		return "Room{" +
				"id='" + id + '\'' +
				", cdate='" + cdate + '\'' +
				", ctime=" + ctime +
				", isFree='" + isFree + '\'' +
				", floor=" + floor +
				'}';
	}
}
