package com.TinyTipsWEB.Service;

import com.TinyTipsWEB.DAO.table.imp.IOperateNote;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Note;
import com.TinyTipsWEB.Service.imp.IHandleNote;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HandleNote implements IHandleNote {

    @Resource(name = "operateNote")
    private IOperateNote operateNote;

    @Override
    public Result addNote(Note note) {
        return operateNote.add(note);
    }

    @Override
    public Result deleteNote(Note note) {
        return operateNote.delete(note);
    }

    @Override
    public Result updateNote(Note oldNote, Note note) {
        return operateNote.update(oldNote, note);
    }

    @Override
    public List<Note> queryNote(Note note) {
        return operateNote.query(note);
    }
}
