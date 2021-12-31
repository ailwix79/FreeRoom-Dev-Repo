package server;

import java.sql.Date;
import java.sql.Time;

// DB DATA HANDLER FUNCTION. PREPARES DATA TO BE SENT TO THE DB
public class TableCreator {

    private int classId;
    private String cdate;
    private int ctime;
    private boolean isFree;
    private String userID;

    public TableCreator() {

    }

    public TableCreator(int classId, String cdate, int ctime, boolean isFree) {
        this.classId = classId;
        this.cdate = cdate;
        this.ctime = ctime;
        this.isFree = isFree;
    }

    public TableCreator(int classId, String cdate, int ctime, String userID) {
        this.classId = classId;
        this.cdate = cdate;
        this.ctime = ctime;
        this.userID = userID;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDate() {
        return cdate;
    }

    public void setDate(String cdate) {
        this.cdate = cdate;
    }

    public int getTime() {
        return ctime;
    }

    public void setTime(int ctime) {
        this.ctime = ctime;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public String getUserID() { return userID; }

    public void setUserID() { this.userID = userID; }

    @Override
    public String toString() {
        return "Class_Data{" +
                "class_id=" + classId +
                ", date='" + cdate + '\'' +
                ", time='" + ctime + '\'' +
                ", isFree=" + isFree +
                '}';
    }
}