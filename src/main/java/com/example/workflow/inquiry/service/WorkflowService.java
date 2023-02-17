package com.example.workflow.inquiry.service;

public interface WorkflowService {
    public String startProcessInstanceByKey(Long businessObjectId);

    void completeInquiryTask(String taskDefKey, Long inquiryId);
}
