package com.migu.component.ice.admin.monitor;

import com.alibaba.fastjson.JSON;
import com.migu.component.ice.admin.Config;
import com.migu.component.ice.admin.entity.ServerVO;
import com.migu.component.ice.admin.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author li wei  <liwei_yy1@migu.cn>
 * @Description: TODO
 * Package Name:com.migu.component.ice.admin.monitor
 * Date: 2017/10/27 15:21
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company    MiGu  Co., Ltd.
 **/
@Component
public class ServerMonitor {
    protected final static Logger LOG = LogManager.getLogger(ServerMonitor.class);

    @Autowired
    private Config config;
    @Autowired
    private AdminService service;

    @Scheduled(cron = "${iceAdmin.monitorCron}")
    public void checkServer() {
        LOG.info("======================begin check=====================");
        for (Map.Entry<String, String> entry : config.getMapEndpoints().entrySet()) {
            Logger log = LogManager.getLogger(entry.getKey());
            String company = entry.getKey();
            String endpoint = entry.getValue();
            log.info("server state start company :{},endpoint: {}", entry.getKey(), entry.getValue());
            try {
                List<ServerVO> serverVOList = service.getAllServerVO(endpoint);
                for (ServerVO serverVO : serverVOList) {
                    serverVO.setCompanyId(company);
                    log.fatal(JSON.toJSONString(serverVO));
                }
            } catch (Exception e) {
                LOG.error("server state error at endpoint:{} ", endpoint, e);
            }
            log.info("server state end ");
        }
        LOG.info("======================check end=====================");
    }
}
