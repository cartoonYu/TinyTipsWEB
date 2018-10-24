package util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static java.lang.System.out;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * decode and encode String by Base64
 *
 * how to use
 * 1.use public method
 *
 * notice
 * none
 */

public class Coder {

    private Base64.Decoder decoder;

    private Base64.Encoder encoder;

    /**
     * 功能
     * 1.对byte数组进行Base64编码
     *
     * @param bytes
     * @return
     */
    public String encode(byte[] bytes){
        out.println(encoder.encodeToString(bytes));
        return encoder.encodeToString(bytes);
    }

    /**
     * 功能
     * 1.对String进行Base64解码
     *
     * @param source
     * @return
     */
    public byte[] decode(String source){
        source=source.replace("\n","");
        out.println(source);
        byte[] decoded=null;
        try{
            byte[] bytes=source.getBytes("UTF-8");
            decoded=decoder.decode(bytes);
            return decoded;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Coder(){
        decoder=Base64.getDecoder();
        encoder=Base64.getEncoder();
    }
}
