package com.example.workflow.inquiry.service;

import com.example.workflow.inquiry.model.InquiryEntity;

import java.util.List;

public interface WorkflowService {
    public String startProcessInstanceByKey(Long businessObjectId);

    void completeInquiryTask(String taskDefKey, InquiryEntity inquiry);

    List<Long> getInquiriesBusinessObjectIdsByTaskKey(String taskDefinitionKey);
}
