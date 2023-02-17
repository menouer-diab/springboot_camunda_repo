package com.example.workflow.inquiry.service;

import com.example.workflow.inquiry.repository.InquiryRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    public static final String PROCESS_KEY = "h2camunda-process";

    private final InquiryRepository inquiryRepository;

    private final RuntimeService runtimeService;

    private final TaskService taskService;

    public WorkflowServiceImpl(InquiryRepository inquiryRepository, RuntimeService runtimeService, TaskService taskService) {
        this.inquiryRepository = inquiryRepository;
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @Override
    public String startProcessInstanceByKey(Long businessObjectId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, String.valueOf(businessObjectId));
        return processInstance.getProcessInstanceId();
    }

    @Override
    public void completeInquiryTask(String taskDefKey, Long inquiryId) {
        taskService.createTaskQuery().processDefinitionKey(PROCESS_KEY).processInstanceBusinessKeyIn(String.valueOf(inquiryId)).taskDefinitionKey(taskDefKey);
    }
}
