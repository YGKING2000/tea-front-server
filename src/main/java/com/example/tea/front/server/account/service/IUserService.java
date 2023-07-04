package com.example.tea.front.server.account.service;

import com.example.tea.front.server.account.pojo.param.UserAddNewParam;
import com.example.tea.front.server.account.pojo.param.UserLoginInfoParam;
import com.example.tea.front.server.account.pojo.vo.UserListItemVO;
import com.example.tea.front.server.account.pojo.vo.UserLoginResultVO;
import com.example.tea.front.server.account.pojo.vo.UserStandardVO;
import com.example.tea.front.server.common.pojo.vo.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * 处理用户数据的业务接口
 *
 * @author YGKING e-mail:hrd18960706057@163.com
 * @version 1.0
 * @date 2023/06/13 17:52
 */
@Transactional
public interface IUserService {

    /**
     * 启用状态的显示文本
     */
    String[] ENABLE_TEXT = {"禁用", "启用"};

    /**
     * 用户登录
     *
     * @param ip                 用户IP地址
     * @param userAgent          用户的浏览器信息
     * @param userLoginInfoParam 封装了登录信息的对象
     *                           // * @return 用户成功登录的JWT
     */
    UserLoginResultVO login(UserLoginInfoParam userLoginInfoParam, String ip, String userAgent);

    /**
     * 添加用户
     *
     * @param userAddNewParam 用户数据
     */
    void addNew(UserAddNewParam userAddNewParam);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void delete(Long id);

    /**
     * 启用用户
     *
     * @param id 用户ID
     */
    void setEnable(Long id);

    /**
     * 禁用用户
     *
     * @param id 用户ID
     */
    void setDisable(Long id);

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 匹配的用户信息
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查询用户列表，将使用默认的每页记录数
     *
     * @param pageNum 页码
     * @return 用户列表
     */
    PageData<UserListItemVO> list(Integer pageNum);

    /**
     * 查询用户列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 用户列表
     */
    PageData<UserListItemVO> list(Integer pageNum, Integer pageSize);

}
