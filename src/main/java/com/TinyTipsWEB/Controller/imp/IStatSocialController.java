package com.TinyTipsWEB.Controller.imp;

import com.TinyTipsWEB.Model.Result;
import com.TinyTipsWEB.Model.view.StatSocial;

import java.util.List;

public interface IStatSocialController {

    Result social();

    List<StatSocial> getSocial(String data);
}
