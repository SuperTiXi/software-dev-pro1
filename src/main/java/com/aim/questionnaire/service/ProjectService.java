package com.aim.questionnaire.service;

import com.aim.questionnaire.common.utils.DateUtil;
import com.aim.questionnaire.common.utils.UUIDUtil;
import com.aim.questionnaire.dao.ProjectEntityMapper;
import com.aim.questionnaire.dao.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wln on 2018\8\6 0006.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectEntityMapper projectEntityMapper ;


    /**
     * 添加项目
     * @param projectEntity
     * @return
     */
    public int addProjectInfo(ProjectEntity projectEntity) {

        String id = UUIDUtil.getOneUUID();
        projectEntity.setId(id);
        projectEntity.setCreationDate(DateUtil.dateToSqlDate(DateUtil.getCurrentDate(),null));
        projectEntity.setLastUpdateDate(DateUtil.dateToSqlDate(DateUtil.getCurrentDate(),null));

        return projectEntityMapper.insert(projectEntity);
    }

    /**
     * 修改项目
     * @param projectEntity
     * @return
     */
    public int modifyProjectInfo(ProjectEntity projectEntity) {
        return projectEntityMapper.updateByPrimaryKey(projectEntity);
    }

    /**
     * 删除项目
     * @param projectEntity
     * @return
     */
    public int deleteProjectById(ProjectEntity projectEntity) {

        return projectEntityMapper.deleteProjectById(projectEntity.getId());
    }

    /**
     * 查询项目列表
     * @param projectEntity
     * @return
     */
    public List<Object> queryProjectList(ProjectEntity projectEntity) {

        List<Object> resultList = new ArrayList<Object>();
        List<Map<String,Object>> map =projectEntityMapper.queryProjectList(projectEntity);

        return resultList;
    }

    /**
     * 查询全部项目的名字接口
     * @return
     */
    public List<Map<String,Object>> queryAllProjectName() {
        return null;
    }

    /**
     * 根据ID查询项目
     * @param projectEntity
     * @return
     */
    public HashMap<String, Object> queryProjectById(ProjectEntity projectEntity){
        return projectEntityMapper.queryProjectById(projectEntity);
    }
}
