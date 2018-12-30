package util;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能
 * List与String的互相转换
 * @author cartoon
 * @version 1.0
 */
public class ListAndString {

    /**
     * 根据特定字符分隔String，并将其转化成List输出
     *
     * 使用方法
     * 1.传入非空的源字符串以及分隔符
     * 2.接收转换的List
     *
     * 注意
     * 1.接收之后需对List进行非空判断后再使用
     *
     * * @param source
     * @param separator
     * @return
     */
    public List<String> changeStringToList(String source,String separator){
        List<String> result=new ArrayList<>();
        if(JudgeEmpty.isEmpty(source)){
            return result;
        }
        int index=source.indexOf(separator);
        int length;
        if(index==-1){
            result.add(source);
        }
        else {
            while(index!=-1){
                result.add(source.substring(0,index));
                length=source.length();
                source=source.substring(index+1,length);
                index=source.indexOf(separator);
            }
        }
        return result;
    }

    /**
     * 根据特定字符合成String，并将其输出
     *
     * 使用方法
     * 1.传入非空的集合以及分隔符
     * 2.接收转换的String
     *
     * 注意
     * 1.接收之后需对String进行非空判断后再使用
     *
     * * @param source
     * @param separator
     * @return
     */
    public String changeListToString(List<String> source,String separator){
        if(JudgeEmpty.isEmpty(source)){
            return null;
        }
        StringBuilder result=new StringBuilder();
        for(String temp:source){
            result.append(temp).append(separator);
        }
        return result.toString();
    }
}
