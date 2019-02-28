package com.TinyTipsWEB.Controller;

import com.TinyTipsWEB.Controller.imp.INoteController;
import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.table.Note;
import com.TinyTipsWEB.Service.imp.IHandleNote;
import com.TinyTipsWEB.util.JSON.JSONObjectOperation;
import com.TinyTipsWEB.util.network.IGetDataFromHttp;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/TinyTipsWEB/Note")
public class NoteController implements INoteController{

    @Resource(name = "jsonObjectOperation")
    private JSONObjectOperation objectOperation;

    @Resource(name = "handleNote")
    private IHandleNote handle;

    @Resource(name = "getDataFromHttp")
    private IGetDataFromHttp getData;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    @Override
    public Result note() {
        Result result=new Result();
        result.setSuccess();
        return result;
    }

    @ResponseBody
    @RequestMapping("/add")
    @Override
    public Result addNote(@RequestBody String data) {
        Note note=objectOperation.getNoteFromJSON(getData.getJSONObject(data));
        return handle.addNote(note);
    }

    @ResponseBody
    @RequestMapping("/delete")
    @Override
    public Result deleteNote(@RequestBody String data) {
        Note note=objectOperation.getNoteFromJSON(getData.getJSONObject(data));
        return handle.deleteNote(note);
    }

    @ResponseBody
    @RequestMapping("/update")
    @Override
    public Result updateNote(@RequestBody String data) {
        JSONArray array=getData.getJSONArray(data);
        Note oldNote=objectOperation.getNoteFromJSON(array.getJSONObject(0));
        Note note=objectOperation.getNoteFromJSON(array.getJSONObject(1));
        return handle.updateNote(oldNote,note);
    }

    @ResponseBody
    @RequestMapping("/query")
    @Override
    public List<Note> queryNote(@RequestBody String data) {
        Note note=objectOperation.getNoteFromJSON(getData.getJSONObject(data));
        return handle.queryNote(note);
    }
}
