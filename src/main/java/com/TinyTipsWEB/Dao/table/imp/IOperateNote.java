package com.TinyTipsWEB.DAO.table.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Note;

import java.util.List;

public interface IOperateNote {

    Result add(Note note);  //增加笔记

    Result delete(Note note);  //删除笔记

    Result update(Note oldNote, Note newNote);   //更新笔记

    List<Note> query(Note note);   //查询笔记
}
