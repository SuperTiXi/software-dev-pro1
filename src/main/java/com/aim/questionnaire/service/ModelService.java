package com.aim.questionnaire.service;


import com.aim.questionnaire.common.utils.UUIDUtil;
import com.aim.questionnaire.dao.ModelEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ModelService {

    @Autowired
    private ModelEntityMapper modelEntityMapper;

    public List<Map<String, Object>> queryModelListInfo(Map<String, Object> map) {
        List<Map<String, Object>> maps = modelEntityMapper.queryModelListInfo(map);
        return maps;
    }

    public int addModelInfo(Map<String, Object> map) {
        if (map.get("id") == null) {
            map.put("id", UUIDUtil.getOneUUID());
        }
        return modelEntityMapper.addModelInfo(map);
    }

    public int deleteByPrimaryKey(Integer id) {
        return modelEntityMapper.deleteByPrimaryKey(id);
    }

    public int deleteModelInfo(Map<String, Object> map) {
        return modelEntityMapper.deleteModelInfo(map);
    }

    public int modifyModelInfo(Map<String, Object> map) {
        return modelEntityMapper.modifyModelInfo(map);
    }

}
