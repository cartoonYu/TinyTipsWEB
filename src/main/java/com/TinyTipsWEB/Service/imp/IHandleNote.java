package com.TinyTipsWEB.Service.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Note;

import java.util.List;

public interface IHandleNote {

    Result add(Note note);

    Result delete(Note note);

    Result update(Note oldNote,Note note);

    List<Note> query(Note note);

}
