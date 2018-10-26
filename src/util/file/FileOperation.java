package util.file;

import util.GetUUID;

import java.io.*;

public class FileOperation {

    private GetUUID getUUID;

    private Coder coder;

    /**
     * 功能
     * 根据传入的文件路径，文件类型将文件写入到磁盘中
     *
     * 使用方法
     * 1.传入形参
     *   file（经过Base64处理的字符串)
     *   filePath(文件存放路径)
     *   fileType(文件类型)
     * 2.从返回值获取写入的不带后缀的文件名
     *
     * 注意
     * 1.fileType必须以 . 为前缀
     * 2.file必须经过Base64处理
     *
     * @param file
     * @param filePath
     * @param fileType
     * @return
     */
    public String addFile(String file,String filePath,String fileType){
        byte[] bytes=coder.decode(file);
        String fileName=getUUID.getUUID().concat(fileType);  //文件名
        File result=new File(filePath.concat(fileName));
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
        if(result.exists()){
            String name=result.getName();
            return name.substring(0,name.lastIndexOf("."));
        }
        else {
            return null;
        }
    }

    /**
     * 功能
     * 根据传入的文件名，文件类型将文件从磁盘中删除
     *
     * 使用方法
     * 1.传入形参
     *   filePath(文件存放路径)
     *   fileName(文件名)
     *   fileType(文件类型)
     * 2.从返回值获取经过Base64转码的文件字符串
     *
     * 注意
     * 1.fileType必须以 . 为前缀
     *
     * @param filePath
     * @param fileName
     * @param fileType
     * @return
     */
    public boolean deleteFile(String filePath,String fileName,String fileType){
        File file=new File(filePath.concat(fileName).concat(fileType));
        if(file.exists()){
            file.delete();
        }
        if(!file.exists()){
            return true;
        }
        return false;
    }

    /**
     * 功能
     * 根据传入的文件路径，文件类型查询文件
     *
     * 使用方法
     * 1.传入形参
     *   filePath(文件存放路径)
     *   fileName(文件名)
     *   fileType(文件类型)
     * 2.从返回值获取经过Base64转码的文件字符串
     *
     * 注意
     * 1.fileType必须以 . 为前缀
     *
     * @param filePath
     * @param fileName
     * @param fileType
     * @return
     */
    public String queryFile(String filePath,String fileName,String fileType){
        try{
            FileInputStream stream=new FileInputStream(filePath.concat(fileName).concat(fileType));
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

    public FileOperation(){
    }

    public void setGetUUID(GetUUID getUUID) {
        this.getUUID = getUUID;
    }

    public void setCoder(Coder coder){
        this.coder=coder;
    }

}
