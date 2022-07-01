package com.aim.questionnaire.service;

import com.aim.questionnaire.common.utils.DateUtil;
import com.aim.questionnaire.common.utils.UUIDUtil;
import com.aim.questionnaire.dao.QuestionnaireEntityMapper;
import com.aim.questionnaire.dao.entity.QuestionnaireEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
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
    public int addQuestionnaire(HashMap<String, Object> map) {
        return questionnaireEntityMapper.addQuestionnaire(map);
    }

    public int insert(QuestionnaireEntity questionnaireEntity) {
        return questionnaireEntityMapper.insert(questionnaireEntity);
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
     * @param QuestionnaireEntity
     * @return
     */
    public List<Map<String,Object>> queryQuestionnaireList(QuestionnaireEntity QuestionnaireEntity){
        return null;
    }

    public List<Map<String,Object>> queryQuestionnaireMould(String dataId) {
        List<Map<String, Object>> maps = questionnaireEntityMapper.queryQuestionnaireMould(dataId);
        return maps;
    }

    public List<Map<String,Object>> queryHistoryQuestionnaire(HashMap<String, Object> map) {
        return questionnaireEntityMapper.queryHistoryQuestionnaire(map);
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
}
