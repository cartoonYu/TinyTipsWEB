package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {

    private String date;

    private void getTime(){
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date=df.format(date);
    }

    public String getDate(){
        getTime();
        return date;
    }
}
