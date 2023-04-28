package com.example.workflow.creditrequest.service;

import com.example.workflow.creditrequest.model.CreditRequestEntity;

import java.util.List;

public interface WorkflowService {
    public String startProcessInstanceByKey(Long businessObjectId);

    void completeCreditRequestTask(String taskDefKey, CreditRequestEntity inquiry);

    List<Long> getCreditRequestIdsByTaskKey(String taskDefinitionKey);
}
