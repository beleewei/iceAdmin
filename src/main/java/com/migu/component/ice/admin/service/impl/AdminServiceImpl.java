package com.migu.component.ice.admin.service.impl;

import Ice.InitializationData;
import Ice.ObjectPrx;
import IceGrid.*;
import com.alibaba.fastjson.JSON;
import com.migu.component.ice.admin.entity.ServerVO;
import com.migu.component.ice.admin.service.AdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author li wei  <liwei_yy1@migu.cn>
 * @Description: TODO
 * Package Name:com.migu.component.ice.admin.service.impl
 * Date: 2017/10/30 9:47
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company    MiGu  Co., Ltd.
 **/
@Service
public class AdminServiceImpl implements AdminService {
    protected final static Logger LOG = LogManager.getLogger(AdminServiceImpl.class);
    private static final String USER = "mygrid";
    private static final String PASSWORD = "mygrid";

    /**
     * @param endpoint
     * @return
     * @throws Exception
     */
    @Override
    public List<ServerVO> getAllServerVO(String endpoint) throws Exception {
        List<ServerVO> serverVOS = new ArrayList<>();
        Ice.Communicator ic = null;
        AdminSessionPrx session = null;
        try {
            Ice.Properties properties = Ice.Util.createProperties();
            properties.setProperty("Ice.Default.Locator", "IceGrid/Locator:" + endpoint);
            InitializationData initData = new InitializationData();
            initData.properties = properties;
            ic = Ice.Util.initialize(initData);
            ObjectPrx base = ic.stringToProxy("IceGrid/Registry");
            RegistryPrx registry = RegistryPrxHelper.checkedCast(base);
            session = registry.createAdminSession(USER, PASSWORD);
        } catch (PermissionDeniedException e) {
            LOG.error("ice registry connection error", e);
            throw new Exception("ice registry connection error endpoint:" + endpoint);
        }
        try {
            AdminPrx adminPrx = session.getAdmin();
            String[] serverIds = adminPrx.getAllServerIds();
            if (serverIds == null || serverIds.length < 1) {
                throw new Exception("no server found at " + endpoint);
            }
            ServerVO serverVO = null;
            for (String serverId : serverIds) {
                serverVO = this.queryServerDetail(adminPrx, serverId);
                serverVOS.add(serverVO);
            }
        } catch (Exception e) {
            LOG.error("query server error", e);
            throw new Exception("query server error " + endpoint);
        } finally {
            if (ic != null) {
                try {
                    ic.destroy();
                } catch (Exception e) {
                    LOG.error("ice close error", e);
                }
            }
        }
        Collections.sort(serverVOS);
        return serverVOS;
    }

    /**
     * query server detail
     *
     * @param adminPrx
     * @param serverId
     * @return
     */
    private ServerVO queryServerDetail(AdminPrx adminPrx, String serverId) {
        ServerVO serverVO = new ServerVO();
        serverVO.setServerId(serverId);
        ServerState state = null;
        ServerInfo serverInfo = null;
        try {
            state = adminPrx.getServerState(serverId);
            serverInfo = adminPrx.getServerInfo(serverId);
            serverVO.setState(state);
            serverVO.setServerId(serverId);
            serverVO.setNode(serverInfo.node);
            if (serverInfo.descriptor == null
                    || serverInfo.descriptor.propertySet == null
                    || serverInfo.descriptor.propertySet.properties == null) {
                LOG.error("no property found on the server {}", serverId);
            } else {
                serverVO.setProperties(serverInfo.descriptor.propertySet.properties);
            }
            LOG.info(JSON.toJSONString(serverVO));
        } catch (Exception e) {
            LOG.error("query server detail error id={}", serverId, e);
        }
        return serverVO;
    }

}
