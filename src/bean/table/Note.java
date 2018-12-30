package bean.table;

import util.CurrentTime;

import java.util.List;
import java.util.Map;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 笔记bean类
 *
 * notice
 * 1.属性id为数据库自增长，不需另赋值
 * 2.属性userId关联个人信息bean类的id
 * 3.属性date已通过spring注入，不需另进行赋值操作
 * 3.属性tag,wordDetails,photoDetails转换成String以符号$进行分隔
 */

public class Note {

    private long id;       //主键，笔记编号，用于关联用户

    private long userId;     //外键

    private String title;     //标题

    private String author;     //作者

    private String date;       //时间

    private List<String> tag;     //标签

    private List<String> wordDetails;    //文字性内容

    private Map<String,String> photo;   //图片性内容

    public Note(){
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public List<String> getWordDetails() {
        return wordDetails;
    }

    public Map<String, String> getPhoto() {
        return photo;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWordDetails(List<String> wordDetails) {
        this.wordDetails = wordDetails;
    }

    public void setPhoto(Map<String, String> photo) {
        this.photo = photo;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

}
