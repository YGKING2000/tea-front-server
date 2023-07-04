package com.example.tea.front.server.account.dao.cache;

import com.example.tea.front.server.common.consts.UserCacheConsts;
import com.example.tea.admin.server.common.pojo.po.UserLoginInfoPO;

/**
 * @author YGKING  e-mail:hrd18960706057@163.com
 * @version 0.0.1
 * @date 2023/07/03 10:15
 */
public interface IUserCacheRepository extends UserCacheConsts {
    void saveLoginInfo(String jwt, UserLoginInfoPO userLoginInfoPO);

    void saveEnableByUserId(Long userId, Integer enable);

    UserLoginInfoPO getLoginInfo(String jwt);

    Integer getEnableByUserId(Long userId);
}
