package com.TinyTipsWEB.util.JSON;


import com.TinyTipsWEB.Model.table.Comment;
import com.TinyTipsWEB.Model.table.Information;
import com.TinyTipsWEB.Model.table.Note;
import com.TinyTipsWEB.Model.table.Social;
import com.TinyTipsWEB.Model.view.CommentDetails;
import com.TinyTipsWEB.Model.view.StatSocial;
import com.TinyTipsWEB.util.JudgeEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.lang.System.out;

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

@Component("jsonObjectOperation")
public class JSONObjectOperation {

    /**
     * 将传入的social转换成json文件
     * @param social
     * @return
     */
    public JSONObject setSocialToJSON(Social social){
        if(JudgeEmpty.isEmpty(social)){
            return null;
        }
        JSONObject result=new JSONObject();
        if(JudgeEmpty.isNotEmpty(social.getType())){
            result.put("type",social.getType());
        }
        if(social.getUserId()!=0){
            result.put("userId",social.getUserId());

        }
        if(social.getNoteId()!=0){
            result.put("noteId",social.getNoteId());
        }
        if(JudgeEmpty.isNotEmpty(social.getDate())){
            result.put("date",social.getDate());
        }
        return result;
    }

    /**
     * 将传入的json文件转化成Social
     * @param object
     * @return
     */
    public Social getSocialFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        Social social=new Social();
        if(object.has("type")){
            social.setType(object.getString("type"));
        }
        if(object.has("userId")){
            social.setUserId(object.getLong("userId"));
        }
        if(object.has("noteId")){
            social.setNoteId(object.getLong("noteId"));
        }
        if(object.has("date")){
            social.setDate(object.getString("date"));
        }
        return social;
    }

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
                    result.put("noteId", comment.getNoteId());
                }
                if(comment.getUserId()!=0){
                    result.put("userId", comment.getUserId());
                }
                result.put("date", comment.getDate());
                result.put("details", comment.getDetails());
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
    public Comment getCommentFromJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return null;
        }
        else{
            Comment comment =new Comment();
            try{
                if(object.has("noteId")){
                    if(object.getLong("noteId")!=0){
                        comment.setNoteId(object.getLong("noteId"));
                    }
                }
                if(object.has("userId")){
                    if(object.getLong("userId")!=0){
                        comment.setUserId(object.getLong("userId"));
                    }
                }
                if(object.has("details")){
                    comment.setDetails(object.getString("details"));
                }
            }catch(JSONException e){
                out.println("jsonObjectException:将json文件转换comment出现错误");
                e.printStackTrace();
            }
            return comment;
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

    public String getStringFromJSON(JSONObject object, String key){
        String method=null;
        if(object.has(key)){
            method=object.getString(key);
        }
        return method;
    }

    /**
     * 获取传入json文件的StatSocial
     * @param object
     * @return
     */
    public StatSocial getStatSocialFromJSON(JSONObject object){
        StatSocial data=new StatSocial();
        if(object.has("userId")&&object.getLong("userId")!=0){
            data.setUserId(object.getLong("userId"));
        }
        if(object.has("nickName")){
            data.setNickName(object.getString("nickName"));
        }
        if(object.has("noteId")&&object.getLong("noteId")!=0){
            data.setNoteId(object.getLong("noteId"));
        }
        if(object.has("title")){
            data.setTitle(object.getString("title"));
        }
        if(object.has("word")){
            data.setWordDetails(changeStringToList("word"));
        }
        if(object.has("photo")){
            data.setPhoto(changeStringToMap("photo"));
        }
        if(object.has("date")){
            data.setDate(object.getString("date"));
        }
        if(object.has("numOfLove")){
            data.setNumOfLove(object.getInt("numOfLove"));
        }
        if(object.has("loveList")){
            List<String> temp=changeStringToList(object.getString("loveList"));
            List<Integer> loveList=new ArrayList<>();
            for(String s:temp){
                loveList.add(Integer.parseInt(s));
            }
            data.setLoveList(loveList);
        }
        if(object.has("numOfComment")){
            data.setNumOfComment(object.getInt("numOfComment"));
        }
        if(object.has("commentList")){
            List<String> temp=changeStringToList(object.getString("commentList"));
            List<Integer> commentList=new ArrayList<>();
            for(String s:temp){
                commentList.add(Integer.parseInt(s));
            }
            data.setCommentList(commentList);
        }
        if(object.has("numOfCollect")){
            data.setNumOfCollect(object.getInt("numOfCollect"));
        }
        if(object.has("collectList")){
            List<String> temp=changeStringToList(object.getString("collectList"));
            List<Integer> collectList=new ArrayList<>();
            for(String s:temp){
                collectList.add(Integer.parseInt(s));
            }
            data.setLoveList(collectList);
        }
        if(object.has("numOfForward")){
            data.setNumOfForward(object.getInt("numOfForward"));
        }
        if(object.has("forwardList")){
            List<String> temp=changeStringToList(object.getString("forwardList"));
            List<Integer> forwardList=new ArrayList<>();
            for(String s:temp){
                forwardList.add(Integer.parseInt(s));
            }
            data.setLoveList(forwardList);
        }
        return data;
    }

    /**
     * 将StatSocial对象转换成json文件
     * @param social
     * @return
     */
    public JSONObject setStatSocialToJSON(StatSocial social){
        JSONObject result=new JSONObject();
        if(social.getUserId()!=0){
            result.put("userId",social.getUserId());
        }
        if(JudgeEmpty.isNotEmpty(social.getHeadPortrait())){
            result.put("headPortrait",social.getHeadPortrait());
        }
        if(JudgeEmpty.isNotEmpty(social.getHeadPortraitName())){
            result.put("headPortraitName",social.getHeadPortraitName());
        }
        if(JudgeEmpty.isNotEmpty(social.getNickName())){
            result.put("nickName",social.getNickName());
        }
        if(social.getNoteId()!=0){
            result.put("noteId",social.getNoteId());
        }
        if(JudgeEmpty.isNotEmpty("title")){
            result.put("title",social.getTitle());
        }
        if(JudgeEmpty.isNotEmpty(social.getWordDetails())){
            result.put("word",social.getWordDetails());
        }
        if(JudgeEmpty.isNotEmpty(social.getPhoto())){
            result.put("photo",social.getPhoto().toString());
        }
        if(JudgeEmpty.isNotEmpty("date")){
            result.put("date",social.getDate());
        }
        if(JudgeEmpty.isNotEmpty(social.getLoveList())){
            result.put("loveList",social.getLoveList().toString());
        }
        if(JudgeEmpty.isNotEmpty(social.getCollectList())){
            result.put("collectList",social.getCollectList().toString());
        }
        if(JudgeEmpty.isNotEmpty(social.getCommentList())){
            result.put("commentList",social.getCommentList().toString());
        }
        if(JudgeEmpty.isNotEmpty(social.getForwardList())){
            result.put("forwardList",social.getForwardList().toString());
        }
        result.put("numOfLove",social.getNumOfLove());
        result.put("numOfComment",social.getNumOfComment());
        result.put("numOfCollect",social.getNumOfCollect());
        result.put("numOfForward",social.getNumOfForward());
        return result;
    }

    /**
     * 获取传入json文件的CommentDetails
     * @param object
     * @return
     */
    public CommentDetails getCommentDetailsFromJSON(JSONObject object){
        CommentDetails details=new CommentDetails();
        if(object.has("noteId")){
            details.setNoteId(object.getLong("noteId"));
        }
        if(object.has("userId")){
            details.setUserId(object.getLong("userId"));
        }
        if(object.has("date")){
            details.setDate(object.getString("date"));
        }
        if(object.has("headPortrait")){
            details.setHeadPortrait(object.getString("headPortrait"));
        }
        if(object.has("headPortraitName")){
            details.setHeadPortraitName(object.getString("headPortraitName"));
        }
        if(object.has("nickName")){
            details.setNickName(object.getString("nickName"));
        }
        if(object.has("details")){
            details.setDetails(object.getString("details"));
        }
        return details;
    }

    /**
     * 将CommentDetails转换成JSON文件
     * @param details
     * @return
     */
    public JSONObject setCommentDetailsToJSON(CommentDetails details){
        JSONObject object=new JSONObject();
        if(details.getNoteId()!=0){
            object.put("noteId",details.getNoteId());
        }
        if(details.getUserId()!=0){
            object.put("userId",details.getUserId());
        }
        if(JudgeEmpty.isNotEmpty(details.getDate())){
            object.put("date",details.getDate());
        }
        if(JudgeEmpty.isNotEmpty(details.getDetails())){
            object.put("details",details.getDetails());
        }
        if(JudgeEmpty.isNotEmpty(details.getHeadPortrait())){
            object.put("headPortrait",details.getHeadPortrait());
        }
        if(JudgeEmpty.isNotEmpty(details.getHeadPortraitName())){
            object.put("headPortraitName",details.getHeadPortraitName());
        }
        if(JudgeEmpty.isNotEmpty(details.getNickName())){
            object.put("nickName",details.getNickName());
        }
        return object;
    }

    /**
     * 遍历json文件，用于测试
     * @param object
     */
    public void displayJSON(JSONObject object){
        if(JudgeEmpty.isEmpty(object)){
            return;
        }
        Iterator<String> it=object.keys();
        while (it.hasNext()){
            String key=it.next();
            Object value=object.get(key);
            out.print(key+":");
            if(value instanceof List){
                List list=(List)value;
                out.println(list.size());
            }
            else {
                out.println(value.toString());
            }
        }
        out.println();
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
