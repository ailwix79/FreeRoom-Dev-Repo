import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    @Test
    public void getIsFree() {
        Room r = new Room("101","MON",10,Boolean.TRUE,1);
        assertEquals(Boolean.TRUE,r.getIsFree());
    }

}