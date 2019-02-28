package com.TinyTipsWEB.Controller.imp;

import com.TinyTipsWEB.Model.Result;

public interface IInformationController {

    Result information();

    Result addInformation(String data);

    Result deleteInformation(String data);

    Object queryInformation(String data);

    Object updateInformation(String data);

}
