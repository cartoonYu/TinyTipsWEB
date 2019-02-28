package com.TinyTipsWEB.DAO.view;

import com.TinyTipsWEB.DAO.sql.OperateDB;
import com.TinyTipsWEB.DAO.view.imp.ICheckStatSocial;
import com.TinyTipsWEB.Model.view.StatSocial;
import com.TinyTipsWEB.ValueCallBack;
import com.TinyTipsWEB.util.CollectionAndString;
import com.TinyTipsWEB.util.JudgeEmpty;
import com.TinyTipsWEB.util.file.FileOperation;
import com.TinyTipsWEB.util.file.ImageConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository("checkSocial")
public class CheckStatSocial implements ICheckStatSocial {

    @Resource(name = "fileOperation")
    private FileOperation fileOperation;

    @Resource(name = "imageConstant")
    private ImageConstant imageConstant;

    @Value("social")
    private String viewName;

    @Resource(name = "operateDB")
    private OperateDB db;

    @Resource(name = "collectAndString")
    private CollectionAndString cs;

    @Override
    public List<StatSocial> getSocial(StatSocial statSocial) {
        List<StatSocial> result=new ArrayList<>();
        if(JudgeEmpty.isEmpty(statSocial)){
            return null;
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
                    temp.setWordDetails(cs.changeStringToList(word,"$"));
                }
                String photo=set.getString("photoDetails");
                if(JudgeEmpty.isNotEmpty(photo)){
                    List<String> name=cs.changeStringToList(photo,"$");
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
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
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
        List<String> sList=cs.changeStringToList(source,",");
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

}
