package com.jfsoft.checker.service;

import java.util.List;
import java.util.Map;

/**
 * 体检者业务接口
 */
public interface ICheckerService {

    /**
     * 查询体检者的综合排队信息
     */
    public List<Map<String, Object>> getCheckerQueueInfo(String testno) throws Exception;

}
