package com.aim.questionnaire.service;

import com.aim.questionnaire.common.utils.DateUtil;
import com.aim.questionnaire.common.utils.UUIDUtil;
import com.aim.questionnaire.dao.ProjectEntityMapper;
import com.aim.questionnaire.dao.QuestionnaireEntityMapper;
import com.aim.questionnaire.dao.entity.ProjectEntity;
import com.aim.questionnaire.dao.entity.QuestionnaireEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class QuestionnaireService {
    
    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    /**
     * 
     * @param
     * @return
     */
    public int addQuestionnaire(HashMap<String, Object> map) {
        if (map.get("startTime").equals("")) {
            map.put("startTime", System.currentTimeMillis());
        }
        long startTime = (long) map.get("startTime");
        Date date = new Date(startTime);
        map.put("startTime", date);
        if (map.get("endTime").equals("")) {
            map.put("endTime", System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
        }
        long endTime = (long) map.get("endTime");
        date = new Date(endTime);
        map.put("endTime", date);
        if (map.get("id") == null) {
            map.put("id", UUIDUtil.getOneUUID());
        }
        return questionnaireEntityMapper.addQuestionnaire(map);
    }

    public int insert(QuestionnaireEntity questionnaireEntity) {
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        return 0;
    }

    /**
     * 查询项目下所有问卷
     * @param questionnaireEntity
     * @return
     */
    public List<Map<String,Object>> queryQuestionnaireByProId(QuestionnaireEntity questionnaireEntity){
        return questionnaireEntityMapper.queryQuestionnaireByProId(questionnaireEntity);
    }

    public QuestionnaireEntity queryQuestContextEnd(String id) {
        return questionnaireEntityMapper.queryQuestContextEnd(id);
    }

    /**
     * 修改问卷
     * @param questionnaireEntity
     * @return
     */
    public int modifyQuestionnaireInfo(QuestionnaireEntity questionnaireEntity){
        return questionnaireEntityMapper.modifyQuestionnaireInfo(questionnaireEntity);
    }

    public String modifyQuestionnaire(HashMap<String, Object> map) {
        Object questionList = map.get("questionList");
        String temp = JSONObject.toJSONString(questionList);
        map.put("questionList", temp);
        int flag = questionnaireEntityMapper.modifyQuestionnaire(map);
        if (flag == 0) {
            return null;
        }
        return (String) map.get("questionId");
    }

    public int addSendQuestionnaire(HashMap<String, Object> map) {
        return questionnaireEntityMapper.addSendQuestionnaire(map);
    }


    /**
     * 查询问卷状态
     * @param questionnaireEntity
     * @return
     */
    public String selectQuestionnaireStatus(QuestionnaireEntity questionnaireEntity){
        return questionnaireEntityMapper.selectQuestionnaireStatus(questionnaireEntity);
    }
    /**
     * 
     * @param record
     * @return
     */
    public  int insertSelective(QuestionnaireEntity record) {
        return 0;
    }
    public  int updateByPrimaryKeySelective(QuestionnaireEntity record) {
        return 0;
    }

    /**
     * 根据项目id删除项目
     * @param id
     * @return
     */
    public int deleteQuestionnaireById(String id) {
        return questionnaireEntityMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询项目列表
     * @param
     * @return
     */
    public List<Map<String,Object>> queryQuestionnaireList(HashMap<String, Object> map) {
        List<Map<String, Object>> maps = questionnaireEntityMapper.queryQuestionnaireList(map);
        for (Map<String, Object> stringObjectMap : maps) {
            String projectId = (String) stringObjectMap.get("projectId");
            HashMap<String, Object> map1 = projectEntityMapper.queryProjectById(new ProjectEntity(projectId));
            stringObjectMap.put("projectName", map1.get("projectName"));
        }
        return maps;
    }

    public List<Map<String,Object>> queryQuestionnaireMould(String dataId) {
        List<Map<String, Object>> maps = questionnaireEntityMapper.queryQuestionnaireMould(dataId);
        return maps;
    }

    public List<Map<String,Object>> queryHistoryQuestionnaire(HashMap<String, Object> map) {
        return questionnaireEntityMapper.queryHistoryQuestionnaire(map);
    }

    public Map<String,String> queryQuestionnaireById(HashMap<String, Object> map) {
        return questionnaireEntityMapper.queryQuestionnaireById(map);
    }

    /**
     * 查询全部项目的名字接口
     * @return
     */
    public List<Map<String,Object>> queryAllQuestionnaireName() {
        return null;
    }

    public int updateByPrimaryKey(QuestionnaireEntity record) {
        return 0;
    }

    public Map<String,Object> queryQuestionnaireInfoById(String id) {
        return null;
    }

    public List<Map<String, Object>> queryQuestionListByProjectId(String id) {
        List<Map<String, Object>> maps = questionnaireEntityMapper.queryQuestionListByProjectId(id);
        return maps;
    }

    public int modifyHistoryQuestionnaireStatus(HashMap<String, Object> map) {
        Object endTime = map.get("endTime");
        Date date = new Date((long) endTime);
        map.put("endTime", date);
        return questionnaireEntityMapper.modifyHistoryQuestionnaireStatus(map);
    }
}
