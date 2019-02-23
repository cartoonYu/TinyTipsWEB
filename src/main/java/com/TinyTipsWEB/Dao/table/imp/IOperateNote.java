package com.TinyTipsWEB.DAO.table.imp;

import com.TinyTipsWEB.Model.table.Note;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface IOperateNote {

    void add(Note note, ValueCallBack<String> callBack);  //增加笔记

    void delete(Note note, ValueCallBack<String> callBack);  //删除笔记

    void update(Note oldNote, Note newNote, ValueCallBack<String> callBack);   //更新笔记

    void query(Note note, ValueCallBack<List<Note>> callBack);   //查询笔记
}
