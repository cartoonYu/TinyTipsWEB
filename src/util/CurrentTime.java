package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cartoon
 * @version 1.1
 *
 * description
 * get current time
 *
 * how to use
 *
 * note
 */
public class CurrentTime {

    private String date;

    private void getTime(String format){
        Date date=new Date();
        SimpleDateFormat df=new SimpleDateFormat(format);
        this.date=df.format(date);
    }

    public String getDate(String choice){
        switch (choice){
            case "day":{
                getTime("yyyy-MM-dd");
                break;
            }
            case "time":{
                getTime("yyyy-MM-dd HH:mm:ss");
            }
        }

        return date;
    }
}
