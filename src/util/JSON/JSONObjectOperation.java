package util.JSON;

import static java.lang.System.out;
import bean.Comment;
import bean.CommentDetails;
import bean.Information;
import bean.Note;
import org.json.JSONException;
import org.json.JSONObject;
import util.JudgeEmpty;
import util.file.FileOperation;
import util.file.ImageConstant;

import java.util.*;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * JSONObject与Java Bean的相互转化
 *
 * notice
 * none
 */
public class JSONObjectOperation {

    /**
     * 将传入的comment转换成json文件
     * @param comment
     * @return
     */
    public JSONObject setCommentToJSON(Comment comment){
        if(JudgeEmpty.isEmpty(comment)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                if(comment.getNoteId()!=0){
                    result.put("noteId",comment.getNoteId());
                }
                if(JudgeEmpty.isNotEmpty(comment.getTag())){
                    result.put("tag",comment.getTag().toString());
                }
                if(comment.getLike()!=0){
                    result.put("like",comment.getLike());
                }
                if(comment.getComment()!=0){
                    result.put("comment",comment.getComment());
                }
                if(comment.getCollect()!=0){
                    result.put("collect",comment.getCollect());
                }
                if(comment.getForward()!=0){
                    result.put("forward",comment.getForward());
                }
            }catch(JSONException e){
                out.println("jsonObjectException:将comment转换json文件出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 获取传入json文件中的comment值
     * @param object
     * @return
     */
    public Comment getCommentFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Comment comment=new Comment();
            try{
                if(object.has("noteId")){
                    comment.setNoteId(object.getLong("noteId"));
                }
                if(object.has("tag")){
                    String temp=object.getString("tag");
                    comment.setTag(changeStringToList(temp));
                }
                if(object.has("like")){
                    comment.setLike(object.getInt("like"));
                }

                if(object.has("comment")){
                    comment.setComment(object.getInt("comment"));
                }
                if(object.has("collect")){
                    comment.setCollect(object.getInt("collect"));
                }
                if(object.has("forward")){
                    comment.setForward(object.getInt("forward"));
                }
            }catch(JSONException e){
                out.println("jsonObjectException:将json文件转换commentDetails出现错误");
                e.printStackTrace();
            }
            return comment;
        }
    }

    /**
     * 将传入的commentDetails转换成json文件
     * @param commentDetails
     * @return
     */
    public JSONObject setCommentDetailsToJSON(CommentDetails commentDetails){
        if(JudgeEmpty.isEmpty(commentDetails)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                if(commentDetails.getNoteId()!=0){
                    result.put("noteId",commentDetails.getNoteId());
                }
                if(commentDetails.getUserId()!=0){
                    result.put("userId",commentDetails.getUserId());
                }
                result.put("date",commentDetails.getDate());
                result.put("headPro",commentDetails.getHeadPro());
                result.put("nickName",commentDetails.getNickName());
                result.put("details",commentDetails.getDetails());
            }catch(JSONException e){
                out.println("jsonObjectException:将commentDetails转换json文件出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 获取传入json文件中的comment值
     * @param object
     * @return
     */
    public CommentDetails getCommentDetailsFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            CommentDetails commentDetails=new CommentDetails();
            try{
                if(object.has("noteId")){
                    if(object.getLong("noteId")!=0){
                        commentDetails.setNoteId(object.getLong("noteId"));
                    }
                }
                if(object.has("userId")){
                    if(object.getLong("userId")!=0){
                        commentDetails.setUserId(object.getLong("userId"));
                    }
                }
                if(object.has("details")){
                    commentDetails.setDetails(object.getString("details"));
                }
                if(object.has("headPro")){
                    commentDetails.setHeadPro(object.getString("headPro"));
                }
                if(object.has("nickName")){
                    commentDetails.setNickName(object.getString("nickName"));
                }
            }catch(JSONException e){
                out.println("jsonObjectException:将json文件转换comment出现错误");
                e.printStackTrace();
            }
            return commentDetails;
        }
    }

    /**
     * 将传入的note转换成json文件
     * @param note
     * @return
     */
    public JSONObject setNoteToJSON(Note note){
        if(JudgeEmpty.isEmpty(note)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                if(note.getUserId()!=0){
                    result.put("userId",note.getUserId());
                }
                if(note.getId()!=0){
                    result.put("id",note.getId());
                }
                result.put("title",note.getTitle());
                if(JudgeEmpty.isNotEmpty(note.getWordDetails())){
                    if(!note.getWordDetails().isEmpty()){{
                        result.put("word",note.getWordDetails().toString());
                    }}
                }
                if(JudgeEmpty.isNotEmpty(note.getPhoto())){
                    if(!note.getPhoto().isEmpty()){
                        result.put("photo",note.getPhoto().toString());
                    }
                }
                result.put("author",note.getAuthor());
                result.put("date",note.getDate());
                if(JudgeEmpty.isNotEmpty(note.getTag())){
                    if(!note.getTag().isEmpty()){
                        result.put("tag",note.getTag().toString());
                    }

                }
            }catch(JSONException e){
                out.println("jsonObjectException:将note转换json文件出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 获取传入json文件中的note值
     * @param object
     * @return
     */
    public Note getNoteFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Note note=new Note();
            try{
                if(object.has("userId")){
                    note.setUserId(object.getLong("userId"));
                }
                if(object.has("id")){
                    note.setId(object.getLong("id"));
                }
                if(object.has("title")){
                    note.setTitle(object.getString("title"));
                }
                if(object.has("word")) {
                    String word = object.getString("word");
                    note.setWordDetails(changeStringToList(word));
                }
                if(object.has("photo")){
                    String photo=object.getString("photo");
                    note.setPhoto(changeStringToMap(photo));
                }
                if(object.has("author")){
                    note.setAuthor(object.getString("author"));
                }
                if(object.has("date")){
                    note.setDate(object.getString("date"));
                }
                if (object.has("tag")){
                    String tag=object.getString("tag");
                    note.setTag(changeStringToList(tag));
                }
            }catch(JSONException e){
                out.println("jsonObjectException:将json文件转换note出现错误");
                e.printStackTrace();
            }
            return note;
        }
    }

    /**
     * 将传入的Information转换成json文件
     * @param information
     * @return
     */
    public JSONObject setInformationToJSON(Information information){
        if(JudgeEmpty.isEmpty(information)){
            return null;
        }
        else{
            JSONObject result=new JSONObject();
            try{
                result.put("id",information.getId());
                result.put("account",information.getAccount());
                result.put("password",information.getPassword());
                result.put("date",information.getDate());
                result.put("headPortraitName",information.getHeadPortraitName());
                result.put("headPortrait",information.getHeadPortrait());
                result.put("nickName",information.getNickName());
                result.put("sex",information.isSex());
                if(JudgeEmpty.isNotEmpty(information.getInterest())){
                    result.put("interest",information.getInterest().toString());
                }
                result.put("school",information.getSchool());
                result.put("major",information.getMajor());
                result.put("background",information.getBackground());
                result.put("resume",information.getResume());
            }catch(JSONException e){
                out.println("jsonObjectException:将Information转换json文件出现错误");
                e.printStackTrace();
            }
            return result;
        }
    }

    /**
     * 获取传入json文件中的Information值
     * @param object
     * @return
     */
    public Information getInformationFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Information information=new Information();
            try{
                if(object.has("id")){
                    information.setId(object.getLong("id"));
                }
                if(object.has("account")){
                    information.setAccount(object.getString("account"));
                }
                if(object.has("password")){
                    information.setPassword(object.getString("password"));
                }
                if(object.has("date")){
                    information.setDate(object.getString("date"));
                }
                if(object.has("headPortrait")){
                    information.setHeadPortrait(object.getString("headPortrait"));
                }
                if(object.has("nickName")){
                    information.setNickName(object.getString("nickName"));
                }
                if(object.has("sex")){
                    information.setSex(object.getBoolean("sex"));
                }
                if(object.has("interest")){
                    String interests=object.getString("interest");
                    information.setInterest(changeStringToList(interests));
                }
                if(object.has("school")){
                    information.setSchool(object.getString("school"));
                }
                if(object.has("major")){
                    information.setMajor(object.getString("major"));
                }
                if(object.has("background")){
                    information.setBackground(object.getString("background"));
                }
                if(object.has("resume")){
                    information.setResume(object.getString("resume"));
                }
            }catch(JSONException e){
                out.println("jsonObjectException:将json文件转换Information出现错误");
                e.printStackTrace();
            }
            return information;
        }
    }

    /**
     * 将结果组合成JSON文件
     * @param result
     * @return
     */
    public JSONObject setResultToJSON(String result) {
        if (JudgeEmpty.isEmpty(result)) {
            return null;
        }
        JSONObject object = new JSONObject();
        object.put("result", result);
        return object;
    }

    public String getMethodFromJSON(JSONObject object){
        String method=null;
        if(object.has("method")){
            method=object.getString("method");
        }
        return method;
    }

    /**
     * 功能
     * 将json文件中的List字符串转换回List
     *
     * 使用方法
     * 1.传入形如[data1, data2, data3...]的字符串
     * 2.
     * @param data
     * @return
     */
    private List<String> changeStringToList(String data){
        List<String> result=new ArrayList<>();
        data=data.substring(1);  //去除开头的"["
        data=data.substring(0,data.length()-1);   //去除结尾的"]"
        String[] strs=data.split(",");
        for(int i=0;i<strs.length;i++){
            result.add(strs[i].trim());
        }
        return result;
    }

    /**
     * 功能
     * 将json文件中的Map字符串转换回Map
     *
     * 使用方法
     * 1.传入形如[data1, data2, data3...]的字符串
     * 2.
     * @param data
     * @return
     */
    private Map<String,String> changeStringToMap(String data){
        data=data.substring(1,data.length()-1);
        String[] strs=data.split(",");
        Map<String,String> result=new LinkedHashMap<>();
        for(int i=0,length=strs.length;i<length;i++){
            int flag=strs[i].indexOf("=");
            result.put(strs[i].substring(0,flag).trim(),strs[i].substring(flag+1).trim());
        }
        return result;
    }

}
