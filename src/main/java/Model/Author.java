package Model;

import java.sql.Date;
import java.sql.Timestamp;

public class Author {
	private int id;
	private String authorname;
	private Boolean status;
	private Timestamp added;
	private Timestamp updated;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorname() {
		return authorname;
	}
	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}
	public Timestamp getAdded() {
		return added;
	}
	public void setAdded(Timestamp added) {
		this.added = added;
	}
	public Timestamp getUpdated() {
		return updated;
	}
	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}
	
}
