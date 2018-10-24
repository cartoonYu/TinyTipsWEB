package util.file;

import util.Coder;
import util.GetUUID;

import java.io.*;

import static java.lang.System.out;

public class FileOperation {

    private GetUUID getUUID;

    private Coder coder;

    /**
     * 功能
     * 根据传入图片名找到服务器中图片并转换成经过Base64处理的String输出
     *
     * 使用方法
     * 1.传入图片名以及图片路径
     * 2.通过返回值得到经过Base64处理的String
     *
     * 注意
     *
     * @param path
     * @param fileName
     * @return
     */
    public String transFileToString(String path,String fileName){
        try{
            FileInputStream stream=new FileInputStream(path+fileName);
            byte[] bytes=new byte[stream.available()];
            stream.read(bytes);
            return coder.encode(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 功能
     * 根据传入字符串转换成图片储存到服务器中并返回文件名
     *
     * 使用方法
     * 1.传入经过Base64处理的图片字符串
     * 2.通过返回值得到图片文件名
     *
     * 注意
     * 1.传入的字符串必须经过Base64处理
     *
     */
    public String transStringToFile(String path,String file){
        byte[] bytes=coder.decode(file);
        String fileName=getUUID.getUUID()+".jpg";
        File result=new File(path+fileName);
        try{
            FileOutputStream stream=new FileOutputStream(result);
            stream.write(bytes,0,bytes.length);
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }


    public FileOperation(){
    }

    public void setGetUUID(GetUUID getUUID) {
        this.getUUID = getUUID;
    }

    public void setCoder(Coder coder){
        this.coder=coder;
    }

}
