package com.TinyTipsWEB.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author cartoon
 * @version 1.0
 *
 * description
 * 获取随机名
 *
 * how to use
 *
 * notice
 * none
 *
 */

@Component("getUUID")
public class GetUUID {

    private UUID uuid;

    public String getUUID(){
        uuid=UUID.randomUUID();
        return uuid.toString().replace("-","");
    }

}
