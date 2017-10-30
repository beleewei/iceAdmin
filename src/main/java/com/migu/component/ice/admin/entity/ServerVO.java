package com.migu.component.ice.admin.entity;

import IceGrid.PropertyDescriptor;
import IceGrid.ServerState;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author li wei  <liwei_yy1@migu.cn>
 * @Description: TODO
 * Package Name:com.migu.component.ice.admin.entity
 * Date: 2017/10/27 15:30
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company    MiGu  Co., Ltd.
 **/
public class ServerVO implements Comparable<ServerVO> {
    @JSONField(ordinal = 1)
    private String bizType = "ICE_PROGRESS_STATE";
    @JSONField(ordinal = 2)
    private String companyId;
    @JSONField(ordinal = 3)
    private String serverId;
    @JSONField(ordinal = 4)
    private ServerState state;
    @JSONField(ordinal = 5)
    private String node;
    @JSONField(ordinal = 6)
    private List<PropertyDescriptor> properties;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public ServerState getState() {
        return state;
    }

    public void setState(ServerState state) {
        this.state = state;
    }

    public List<PropertyDescriptor> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyDescriptor> properties) {
        this.properties = properties;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    @Override
    public int compareTo(ServerVO other) {
        return this.getServerId().compareTo(other.getServerId());
    }
}
