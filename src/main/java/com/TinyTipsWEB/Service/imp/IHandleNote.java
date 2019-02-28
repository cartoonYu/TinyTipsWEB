package com.TinyTipsWEB.Service.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Note;

import java.util.List;

public interface IHandleNote {

    Result addNote(Note note);

    Result deleteNote(Note note);

    Result updateNote(Note oldNote,Note note);

    List<Note> queryNote(Note note);

}
