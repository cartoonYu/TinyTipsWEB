package com.TinyTipsWEB.DAO.view.imp;

import com.TinyTipsWEB.Model.view.StatSocial;
import com.TinyTipsWEB.ValueCallBack;

import java.util.List;

public interface ICheckStatSocial {

    List<StatSocial> getSocial(StatSocial statSocial);  //获取详细社交信息
}
