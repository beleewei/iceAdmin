package com.migu.component.ice.admin.service;

import com.migu.component.ice.admin.entity.ServerVO;

import java.util.List;

/**
 * @author li wei  <liwei_yy1@migu.cn>
 * @Description: TODO
 * Package Name:com.migu.component.ice.admin.service
 * Date: 2017/10/27 15:33
 * All rights Reserved, Designed By MiGu
 * Copyright: Copyright(C) 2016-2020
 * Company    MiGu  Co., Ltd.
 **/
public interface AdminService {
    /**
     * query all server info on signed registry
     *
     * @param endpoint
     * @return
     */
    public List<ServerVO> getAllServerVO(String endpoint) throws Exception;
}
