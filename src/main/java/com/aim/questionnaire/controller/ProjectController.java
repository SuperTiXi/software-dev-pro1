package com.aim.questionnaire.controller;

import com.aim.questionnaire.beans.HttpResponseEntity;
import com.aim.questionnaire.common.Constans;
import com.aim.questionnaire.common.utils.DateUtil;
import com.aim.questionnaire.dao.ProjectEntityMapper;
import com.aim.questionnaire.dao.entity.ProjectEntity;
import com.aim.questionnaire.service.ProjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wln on 2018\8\6 0006.
 */
@RestController
public class ProjectController {

    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    private ProjectService projectService;

    @Autowired
    ProjectEntityMapper projectEntityMapper;

    /**
     * 查询全部项目的信息
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/queryProjectList",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        List<Map<String,Object>> res = projectEntityMapper.queryProjectList(projectEntity);
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData(res);
        httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        return httpResponseEntity;
    }

    /**
     * 根据id删除项目
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/deleteProjectById",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteProjectById(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int flag = projectService.deleteProjectById(projectEntity);
        if(flag!=0){

            httpResponseEntity.setMessage(Constans.DELETE_MESSAGE);
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(projectEntity);

        }
        else {
            httpResponseEntity.setMessage("删除失败！！");
            httpResponseEntity.setCode(Constans.MODEL_DELETE_FAIL);
            httpResponseEntity.setData(null);
        }
        return httpResponseEntity;
    }

    /**
     * 添加项目
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/addProjectInfo",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addProjectInfo(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        List<Map<String, Object>> maps = projectEntityMapper.queryProjectList(projectEntity);
        for (Map<String, Object> map : maps) {
            if(map.get("projectName").equals(projectEntity.getProjectName())){
                httpResponseEntity.setMessage("项目名重复");
                httpResponseEntity.setCode("111");
                return httpResponseEntity;
            }
        }

        int flag = projectService.addProjectInfo(projectEntity);
        if(flag!=0){

            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(projectEntity);
        }
        else {
            httpResponseEntity.setMessage("创建失败！！");
            httpResponseEntity.setCode(Constans.LOGOUT_NO_CODE);
            httpResponseEntity.setData(null);
        }




        return httpResponseEntity;
    }

    /**
     * 根据项目id修改项目
     * @param projectEntity
     * @return
     */
    @RequestMapping(value = "/modifyProjectInfo",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyProjectInfo(@RequestBody ProjectEntity projectEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int info = projectService.modifyProjectInfo(projectEntity);
        if (info!=0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setMessage(Constans.UPDATE_MESSAGE);
            httpResponseEntity.setData(projectEntity);
        }else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setMessage("修改失败！");
            httpResponseEntity.setData(null);
        }
        return httpResponseEntity;
    }



    /**
     * 查询全部项目的名字接口
     * @return
     */
    @RequestMapping(value = "/queryAllProjectName",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryAllProjectName() {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryProjectById",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryProjectById(@RequestBody ProjectEntity projectEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        HashMap<String, Object> map = projectService.queryProjectById(projectEntity);

        if(map!=null){
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(map);
        }
        return httpResponseEntity;
    }
}
