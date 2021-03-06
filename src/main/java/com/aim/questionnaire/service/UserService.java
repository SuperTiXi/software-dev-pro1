package com.aim.questionnaire.service;

import com.aim.questionnaire.common.utils.DateUtil;
import com.aim.questionnaire.common.utils.UUIDUtil;
//import com.aim.questionnaire.config.shiro.SysUserService;
//import com.aim.questionnaire.config.shiro.entity.UserOnlineBo;
import com.aim.questionnaire.dao.QuestionnaireEntityMapper;
import com.aim.questionnaire.dao.UserEntityMapper;
import com.aim.questionnaire.dao.entity.UserEntity;
//import com.alibaba.fastjson.JSONArray;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wln on 2018\8\9 0009.
 */
@Service
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    //@Autowired
    //private SysUserService sysUserService;

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    /**
     * 查询用户列表（模糊搜索）
     * @param map
     * @return
     */
    public PageInfo queryUserList(Map<String,Object> map) {
        List<Map<String,Object>> maps = userEntityMapper.queryUserList(map);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(maps);
        return pageInfo;
    }

    /**
     * 创建用户的基本信息
     * @param map
     * @return
     */
    public int addUserInfo(Map<String,Object> map) {
        if(map.get("username") != null) {
            int userResult = userEntityMapper.queryExistUser(map);
            if(userResult != 0) {
                //用户名已经存在
                return 3;
            }
        }

        String id = UUIDUtil.getOneUUID();
        map.put("id",id);
        //创建时间
        Date date = DateUtil.getCreateTime();
        map.put("creationDate",date);
        map.put("lastUpdateDate",date);
        //创建人
        String user = "admin";
        map.put("createdBy",user);
        map.put("lastUpdatedBy",user);
        //前台传入的时间戳转换
        String startTimeStr = map.get("startTime").toString();
        String endTimeStr = map.get("stopTime").toString();
        Date startTime = DateUtil.getMyTime(startTimeStr);
        Date endTime = DateUtil.getMyTime(endTimeStr);
        map.put("startTime",startTime);
        map.put("stopTime",endTime);

        int result = userEntityMapper.addUserInfo(map);

        return result;
    }

    /**
     * 编辑用户的基本信息
     * @param map
     * @return
     */
    public int modifyUserInfo(Map<String, Object> map) {
        String id = (String)map.get("id");
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        long startTime = (long) map.get("startTime");
        long endTime = (long) map.get("stopTime");
        UserEntity user = userEntityMapper.selectAllByName(username);
        if (user!=null) {
//            用户名已存在 USER_USERNAME_MESSAGE
            return 1;
        }
        UserEntity newUser = userEntityMapper.selectByPrimaryKey(id);

        newUser.setUsername(username);
        if (password!=null){
            newUser.setPassword(password);
        }
        System.out.println(password);
        Date start = new Date(startTime);
        newUser.setStartTime(start);
        Date end = new Date(endTime);
        newUser.setStopTime(end);
        userEntityMapper.updateByPrimaryKey(newUser);
        return 0;
    }

    /**
     * 修改用户状态
     * @param map
     * @return
     */
    public int modifyUserStatus(Map<String, Object> map) {
        String id = (String)map.get("id");
        UserEntity newUser = userEntityMapper.selectByPrimaryKey(id);
        String status = newUser.getStatus();
        if(status.equals("1")){
            status = "0";
        }
        else if(status.equals("0")){
            status = "1";
        }
        newUser.setStatus(status);
        userEntityMapper.updateByPrimaryKey(newUser);
        return 1;
    }

    /**
     * 根据id查询用户信息
     * @param userEntity
     * @return
     */
    public Map<String,Object> selectUserInfoById(UserEntity userEntity) {
  
        return null;
    }

    /**
     * 删除用户信息
     * @param userEntity
     * @return
     */
    public int deleteUserInfoById(UserEntity userEntity) {
        int flag = userEntityMapper.deleteUserInfoById(userEntity);
        return flag;
    }

    public int resetUserPassword(Map<String,Object> map){
        String id = (String) map.get("id");
        String password = (String) map.get("password");
        UserEntity userEntity = userEntityMapper.selectByPrimaryKey(id);
        userEntity.setPassword(password);
        userEntityMapper.updateByPrimaryKey(userEntity);
        return 1;
    }
}
