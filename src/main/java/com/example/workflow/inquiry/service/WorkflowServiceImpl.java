package com.example.workflow.inquiry.service;

import com.example.workflow.inquiry.model.InquiryEntity;
import com.example.workflow.inquiry.repository.InquiryRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.camunda.bpm.engine.runtime.VariableInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    public static final String PROCESS_KEY = "h2camunda-process";
    public static final String BUSINESS_OBJECT_ID = "businessObjectId";

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
        Map<String, Object> variablesMap = new HashMap<>();
        variablesMap.put(BUSINESS_OBJECT_ID, businessObjectId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(PROCESS_KEY, String.valueOf(businessObjectId), variablesMap);
        return processInstance.getProcessInstanceId();
    }

    @Override
    public void completeInquiryTask(String taskDefKey, InquiryEntity inquiry) {
        TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey(PROCESS_KEY).processInstanceBusinessKeyIn(String.valueOf(inquiry.getId())).taskDefinitionKey(taskDefKey);
        Task task = taskQuery.singleResult();
        Boolean ok = inquiry.getAmountInEuros() > 5000 ? Boolean.TRUE : Boolean.FALSE;
        runtimeService.setVariable(task.getExecutionId(), "valid_request", ok);
        taskService.complete(task.getId());
    }

    @Override
    public List<Long> getInquiriesBusinessObjectIdsByTaskKey(String taskDefKey) {
        List<Task> activeTaskList = findAllActiveTasksByProcessDefKeyAndTaskDefKey(PROCESS_KEY, taskDefKey);
        Set<String> processInstanceIds = getProcessInstanceIds(activeTaskList);
        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery().processInstanceIds(processInstanceIds);
        List<ProcessInstance> processInstances = processInstanceQuery.list();
        return processInstances.stream().map(inst -> inst.getBusinessKey()).mapToLong(Long::valueOf).boxed().collect(Collectors.toList());
    }

    private static Set<String> getProcessInstanceIds(List<Task> activeTaskList) {
        return Set.copyOf(activeTaskList.stream().map(task -> task.getProcessInstanceId()).distinct().collect(Collectors.toList()));
    }

    private List<Task> findAllActiveTasksByProcessDefKeyAndTaskDefKey(String processDefKey, String taskDefKey) {
        TaskQuery taskByProcessDefKeyAndTaskDefKeyQuery = taskService.createTaskQuery().processDefinitionKey(PROCESS_KEY).taskDefinitionKey(taskDefKey).active();
        return taskByProcessDefKeyAndTaskDefKeyQuery.active().list();
    }
}
