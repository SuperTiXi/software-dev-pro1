package com.aim.questionnaire.controller;

import com.aim.questionnaire.beans.HttpResponseEntity;
import com.aim.questionnaire.dao.QuestionnaireEntityMapper;
import com.aim.questionnaire.dao.entity.QuestionnaireEntity;
import com.aim.questionnaire.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    QuestionnaireEntityMapper questionnaireEntityMapper;

    @RequestMapping(value = "/addQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireAll",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireAll(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireById",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addAnswerQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addAnswerQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/getShortUrlForLink",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity getShortUrlForLink(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectCorrelation",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectCorrelation(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectCheckedCorrelation",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectCheckedCorrelation(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryAllQuestionnaireByCreated",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryAllQuestionnaireByCreated(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireCount",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireCount(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireAboutSchool",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireAboutSchool(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryHistoryQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryHistoryQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireMould",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireMould(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/deleteQuestionnaireById",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyQuestionnaireInfo",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaireInfo(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestionnaireList",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestionnaireList(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyHistoryQuestionnaireStatus",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyHistoryQuestionnaireStatus(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/queryQuestContextEnd",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryQuestContextEnd(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addSendQuestionnaire",method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addSendQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        return httpResponseEntity;
    }
}