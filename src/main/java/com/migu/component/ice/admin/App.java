package com.migu.component.ice.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author li wei  <liwei_yy1@migu.cn>
 * @Description: TODO
 * Package Name:com.migu.component.ice.admin
 * Date: 2017/10/27 15:21
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company    MiGu  Co., Ltd.
 **/
@EnableAutoConfiguration
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.migu.component.ice.admin"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
