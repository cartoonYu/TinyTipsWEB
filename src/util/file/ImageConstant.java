package util.file;

public class ImageConstant {

    private String information;

    private String note;

    private String comment;

    private String commentDetails;

    public String getNote() {
        return note;
    }

    public String getInformation() {
        return information;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public String getComment() {
        return comment;
    }

    public ImageConstant(){
        information=new String("E:\\\\TinyTips\\\\TinyTipsWEB\\\\out\\\\upload\\\\information\\\\");
    }
}
