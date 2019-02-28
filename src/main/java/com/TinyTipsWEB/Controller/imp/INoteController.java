package com.TinyTipsWEB.Controller.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Note;

import java.util.List;

public interface INoteController {

    Result note();

    Result addNote(String data);

    Result deleteNote(String data);

    Result updateNote(String data);

    List<Note> queryNote(String data);

}
