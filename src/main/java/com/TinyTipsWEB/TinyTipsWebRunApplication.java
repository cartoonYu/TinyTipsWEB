package com.TinyTipsWEB;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author cartoon
 * @version 1.0
 *
 * 用于配置生成war包供服务器使用
 *
 */

public class TinyTipsWebRunApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TinyTipsWebApplication.class);
    }
}
