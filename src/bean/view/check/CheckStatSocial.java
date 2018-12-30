package bean.view.check;

import BaseClass.ValueCallBack;
import bean.view.StatSocial;
import sql.OperateDB;
import util.JudgeEmpty;
import util.ListAndString;
import util.file.FileOperation;
import util.file.ImageConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CheckStatSocial {

    private FileOperation fileOperation;

    private ImageConstant imageConstant;

    private String viewName;

    private OperateDB db;

    private ListAndString ls;

    public void getSocial(StatSocial statSocial, ValueCallBack<List<StatSocial>> callBack){
        List<StatSocial> result=new ArrayList<>();
        if(JudgeEmpty.isEmpty(statSocial)){
            callBack.onFail("300");
            return;
        }
        try {
            ResultSet set=db.query(viewName,changeSocialToMap(statSocial));
            while (set.next()){
                StatSocial temp=new StatSocial();
                temp.setUserId(set.getLong("userId"));
                temp.setHeadPortraitName(set.getString("headPortrait"));
                if(JudgeEmpty.isNotEmpty(temp.getHeadPortraitName())){
                    temp.setHeadPortrait(fileOperation.queryFile(imageConstant.getInformation(),temp.getHeadPortraitName(),".jpg"));
                }
                temp.setNickName(set.getString("nickName"));
                temp.setNoteId(set.getLong("noteId"));
                temp.setTitle(set.getString("title"));
                String word=set.getString("wordDetails");
                if(JudgeEmpty.isNotEmpty(word)){
                    temp.setWordDetails(ls.changeStringToList(word,"$"));
                }
                String photo=set.getString("photoDetails");
                if(JudgeEmpty.isNotEmpty(photo)){
                    List<String> name=ls.changeStringToList(photo,"$");
                    Map<String,String> map=new LinkedHashMap<>();
                    for(String s:name){
                        map.put(s,fileOperation.queryFile(imageConstant.getNote(),s,".jpg"));
                    }
                    temp.setPhoto(map);
                }
                temp.setDate(set.getString("date"));
                temp.setNumOfLove(set.getInt("numOfLove"));
                temp.setNumOfComment(set.getInt("numOfComment"));
                temp.setNumOfCollect(set.getInt("numOfCollect"));
                temp.setNumOfForward(set.getInt("numOfForward"));
                temp.setCommentList(getSocialList(set.getString("commentList")));
                temp.setLoveList(getSocialList(set.getString("loveList")));
                temp.setCollectList(getSocialList(set.getString("collectList")));
                temp.setForwardList(getSocialList(set.getString("forwardList")));
                result.add(temp);
            }
            callBack.onSuccess(result);
        }catch (SQLException e){
            callBack.onFail("400");
        }
    }

    /**
     * 将数据库返回的String型列表转换成List<Integer>型列表
     * @param source
     * @return
     */
    private List<Integer> getSocialList(String source){
        List<Integer> result=new ArrayList<>();
        if(JudgeEmpty.isEmpty(source)){
            return result;
        }
        List<String> sList=ls.changeStringToList(source,",");
        for(String s:sList){
            result.add(Integer.parseInt(s));
        }
        return result;
    }

    private Map<String,String> changeSocialToMap(StatSocial source){
        Map<String,String> data=new HashMap<>();
        if(source.getUserId()!=0){
            data.put("userId",Long.toString(source.getUserId()));
        }
        if(JudgeEmpty.isNotEmpty(source.getNickName())){
            data.put("nickName",source.getNickName());
        }
        if(JudgeEmpty.isNotEmpty(source.getTitle())){
            data.put("title",source.getTitle());
        }
        if(source.getNoteId()!=0){
            data.put("noteId",Long.toString(source.getNoteId()));
        }
        if(JudgeEmpty.isNotEmpty(source.getDate())){
            data.put("date",source.getDate());
        }
        return data;
    }

    public void setDb(OperateDB db) {
        this.db = db;
    }

    public void setFileOperation(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    public void setImageConstant(ImageConstant imageConstant) {
        this.imageConstant = imageConstant;
    }

    public void setLs(ListAndString ls) {
        this.ls = ls;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }


}
