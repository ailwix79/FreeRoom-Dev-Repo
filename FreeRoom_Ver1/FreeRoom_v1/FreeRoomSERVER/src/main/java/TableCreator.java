import java.sql.Date;
import java.sql.Time;

public class TableCreator {

    private Long classId;
    private Date cdate;
    private Time ctime;
    private boolean isFree;

    public TableCreator() {

    }

    public TableCreator(Long classId, Date cdate, Time ctime, boolean isFree) {
        this.classId = classId;
        this.cdate = cdate;
        this.ctime = ctime;
        this.isFree = isFree;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Date getDate() {
        return cdate;
    }

    public void setDate(Date cdate) {
        this.cdate = cdate;
    }

    public Time getTime() {
        return ctime;
    }

    public void setTime(Time ctime) {
        this.ctime = ctime;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

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