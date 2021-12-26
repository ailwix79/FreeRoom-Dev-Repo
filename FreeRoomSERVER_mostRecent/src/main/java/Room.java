import java.io.Serializable;

public class Room implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String isFree;

	public Room() {
		this.setId(new String());
		this.setIsFree(new String());
	}

	public Room(String id, String isFree) {
		this.setIsFree(isFree);
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	
	
}
