package com.aim.questionnaire.service;

import com.aim.questionnaire.dao.QuestionnaireEntityMapper;
import com.aim.questionnaire.dao.entity.ProjectEntity;
import org.springframework.stereotype.Service;
import com.aim.questionnaire.common.utils.DateUtil;
import com.aim.questionnaire.common.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionnaireService {
    
    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    /**
     * 
     * @param record 
     * @return
     */
    public int insert(ProjectEntity record) {
        return 0;
    }

    /**
     * 
     * @param record
     * @return
     */
    public  int insertSelective(ProjectEntity record) {
        return 0;
    }
    public  int updateByPrimaryKeySelective(ProjectEntity record) {
        return 0;
    }

    /**
     * 根据项目id删除项目
     * @param id
     * @return
     */
    public int deleteProjectById(String id) {
        return 0;
    }

    /**
     * 查询项目列表
     * @param projectEntity
     * @return
     */
    List<Map<String,Object>> queryProjectList(ProjectEntity projectEntity){
        return null;
    }

    /**
     * 查询全部项目的名字接口
     * @return
     */
    List<Map<String,Object>> queryAllProjectName() {
        return null;
    }

    public int updateByPrimaryKey(ProjectEntity record) {
        return 0;
    }
}
