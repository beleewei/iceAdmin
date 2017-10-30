package com.migu.component.ice.admin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author li wei  <liwei_yy1@migu.cn>
 * @Description: TODO
 * Package Name:com.migu.component.ice.admin
 * Date: 2017/10/27 15:26
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company    MiGu  Co., Ltd.
 **/
@Component
@ConfigurationProperties(prefix = "iceAdmin")
public class Config {
    private Map<String, String> mapEndpoints;

    public Map<String, String> getMapEndpoints() {
        return mapEndpoints;
    }

    public void setMapEndpoints(Map<String, String> mapEndpoints) {
        this.mapEndpoints = mapEndpoints;
    }
}
