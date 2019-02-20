package com.TinyTipsWEB.util.file;

import org.springframework.stereotype.Component;

@Component("imageConstant")
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

    public void setInformation(String information) {
        this.information = information;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    public ImageConstant(){
    }

}
