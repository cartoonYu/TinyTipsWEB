package com.TinyTipsWEB.Dao.view.imp;

import com.TinyTipsWEB.Model.view.StatSocial;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface ICheckStatSocial {

    void getSocial(StatSocial statSocial, ValueCallBack<List<StatSocial>> callBack);  //获取详细社交信息
}
