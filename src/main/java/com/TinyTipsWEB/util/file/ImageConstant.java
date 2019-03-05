package com.TinyTipsWEB.util.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("imageConstant")
public class ImageConstant {

    @Value("${imageConstant.information}")
    private String information;

    @Value("${imageConstant.note}")
    private String note;

    @Value("${imageConstant.comment}")
    private String comment;

    public String getNote() {
        return note;
    }

    public String getInformation() {
        return information;
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

    public ImageConstant(){
    }

}
