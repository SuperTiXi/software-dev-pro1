package com.aim.questionnaire.controller;

import com.aim.questionnaire.beans.HttpResponseEntity;
import com.aim.questionnaire.common.Constans;
import com.aim.questionnaire.dao.QuestionnaireEntityMapper;
import com.aim.questionnaire.dao.entity.QuestionnaireEntity;
import com.aim.questionnaire.service.QuestionnaireService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    @RequestMapping(value = "/addQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaire(@RequestBody HashMap<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        List<Map<String, Object>> maps = questionnaireEntityMapper.queryQuestionnaireList(map);
        for (Map<String, Object> map1 : maps) {
            if(map1.get("questionName").equals(map.get("questionName"))){
                httpResponseEntity.setMessage("问卷名重复");
                httpResponseEntity.setCode("111");
                return httpResponseEntity;
            }
        }
        int insert = questionnaireService.addQuestionnaire(map);
        if (insert != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(map);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireAll",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireAll(@RequestBody HashMap<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        //System.out.println(map);
        Map<String, String> stringStringMap = questionnaireService.queryQuestionnaireById(map);
        if (stringStringMap != null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(stringStringMap);
            httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireById",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireById(@RequestBody HashMap<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        //System.out.println(map);
        Map<String, String> stringStringMap = questionnaireService.queryQuestionnaireById(map);
        if (stringStringMap != null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(stringStringMap);
            httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/addAnswerQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addAnswerQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData("提交成功");
        httpResponseEntity.setMessage(Constans.ADD_MESSAGE);

        return httpResponseEntity;
    }

    @RequestMapping(value = "/getShortUrlForLink",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity getShortUrlForLink(@RequestBody HashMap<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        System.err.println(map);

        String tinyurl = (String) map.get("link");
        tinyurl += map.get("id");
        tinyurl += "&l";
        map.put("tinyurl", tinyurl);
        JSONObject jsonObject = new JSONObject(map);
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData(jsonObject.toJSONString());
        httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);

        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectCorrelation",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectCorrelation(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int insert = questionnaireService.insert(questionnaireEntity);
        if (insert != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectCheckedCorrelation",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectCheckedCorrelation(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int insert = questionnaireService.insert(questionnaireEntity);
        if (insert != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryAllQuestionnaireByCreated",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryAllQuestionnaireByCreated(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int insert = questionnaireService.insert(questionnaireEntity);
        if (insert != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireCount",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireCount(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int insert = questionnaireService.insert(questionnaireEntity);
        if (insert != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireAboutSchool",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireAboutSchool(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int insert = questionnaireService.insert(questionnaireEntity);
        if (insert != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryHistoryQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryHistoryQuestionnaire(@RequestBody HashMap<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        List<Map<String, Object>> maps = questionnaireService.queryHistoryQuestionnaire(map);
        if (maps != null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(maps);
            httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireMould",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireMould(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        List<Map<String, Object>> maps = questionnaireService.queryQuestionnaireMould(questionnaireEntity.getDataId());
        if (maps != null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(maps);
            httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/deleteQuestionnaireById",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int delete = questionnaireService.deleteQuestionnaireById(questionnaireEntity.getId());
        if (delete != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.DELETE_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/deleteQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int delete = questionnaireService.deleteQuestionnaire(questionnaireEntity.getId());
        if (delete != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage("删除成功");
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage("删除失败");
        }

        return httpResponseEntity;
    }
    @RequestMapping(value = "/modifyQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaire(@RequestBody HashMap<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        //System.err.println(map);
        String data = questionnaireService.modifyQuestionnaire(map);
        if (data != null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(data);
            httpResponseEntity.setMessage(Constans.UPDATE_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyQuestionnaireInfo",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int insert = questionnaireService.modifyQuestionnaireInfo(questionnaireEntity);
        if (insert != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryHistoryQuestionnaireList",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryHistoryQuestionnaireList(@RequestBody HashMap<String, Object> questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        List<Map<String, Object>> maps = questionnaireService.queryHistoryQuestionnaire(questionnaireEntity);
        if (maps != null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(maps);
            httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        } else{
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }


    @RequestMapping(value = "/queryQuestionnaireByProId",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireByProId(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        List<Map<String,Object>> map = questionnaireService.queryQuestionnaireByProId(questionnaireEntity);
        httpResponseEntity.setCode(Constans.SUCCESS_CODE);
        httpResponseEntity.setData(map);
        httpResponseEntity.setMessage(Constans.ADD_MESSAGE);

        return httpResponseEntity;
    }
    @RequestMapping(value = "/modifyHistoryQuestionnaireStatus",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyHistoryQuestionnaireStatus(@RequestBody HashMap<String,Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int effect = questionnaireService.modifyHistoryQuestionnaireStatus(map);
        if (effect != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(effect);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectQuestionnaireStatus",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectQuestionnaireStatus(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        String insert = questionnaireService.selectQuestionnaireStatus(questionnaireEntity);
        if (insert!=null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(insert);
            httpResponseEntity.setMessage(Constans.ADD_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }
    @RequestMapping(value = "/queryQuestContextEnd",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestContextEnd(@RequestBody Map<String, String> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        QuestionnaireEntity questionnaireEntity = questionnaireService.queryQuestContextEnd(map.get("id"));
        if (questionnaireEntity != null) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(questionnaireEntity);
            httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }

    @RequestMapping(value = "/addSendQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addSendQuestionnaire(@RequestBody HashMap<String, Object> map) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();

        int success = questionnaireService.addSendQuestionnaire(map);
        if (success != 0) {
            httpResponseEntity.setCode(Constans.SUCCESS_CODE);
            httpResponseEntity.setData(success);
            httpResponseEntity.setMessage(Constans.STATUS_MESSAGE);
        } else {
            httpResponseEntity.setCode(Constans.EXIST_CODE);
            httpResponseEntity.setData(null);
            httpResponseEntity.setMessage(Constans.EXIST_MESSAGE);
        }

        return httpResponseEntity;
    }
}
