package com.example.workflow.inquiry.api;

import com.example.workflow.inquiry.model.InquiryEntity;
import com.example.workflow.inquiry.model.InquiryStatusEnum;
import com.example.workflow.inquiry.model.TaskKeyEnum;
import com.example.workflow.inquiry.service.InquiryService;
import com.example.workflow.inquiry.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    private final WorkflowService workflowService;

    public InquiryController(InquiryService inquiryService, WorkflowService workflowService) {
        this.inquiryService = inquiryService;
        this.workflowService = workflowService;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public InquiryEntity createInquiry(@RequestBody InquiryEntity inquiry) {
        return inquiryService.saveInquiry(inquiry);
    }

    @PostMapping(path = "/start-inquiry-process/{inquiryId}", produces = "text/plain")
    public String startInquiryProcessInstance(@PathVariable("inquiryId") Long inquiryId) {
        String processInstanceId = workflowService.startProcessInstanceByKey(inquiryId);
        return String.format("Process Instance with Id: %s created successfully for inquiry with id: %d", processInstanceId, inquiryId);
    }

    /*
     * Method to find all the user tasks defined with a given key and waiting for completion.
     * @param taskKey
     * The task definition key
     * @return List of Inquiries by task definition key
     */
    @GetMapping(path = "/{taskDefKey}", produces = "application/json")
    public List<InquiryEntity> getInquiriesByTaskKey(@PathVariable("taskDefKey") String taskDefinitionKey) {
        List<Long> inquiryBusinessObjectIds = workflowService.getInquiriesBusinessObjectIdsByTaskKey(taskDefinitionKey);
        return inquiryService.findInquiriesById(inquiryBusinessObjectIds);
    }

    @GetMapping(path = "", produces = "application/json")
    public List<InquiryEntity> getAllInquiries() {
        return inquiryService.getAllInquiries();
    }

    @PostMapping(path = "/complete-task/{inquiryId}", produces = "text/plain")
    public String completeInquiryTask(@PathVariable Long inquiryId, @RequestParam("taskDefKey") String taskDefKey) {
        InquiryEntity inquiry = inquiryService.getInquiryById(inquiryId);
        workflowService.completeInquiryTask(taskDefKey, inquiry);
        inquiryService.updateStatus(inquiry, InquiryStatusEnum.REQUEST_CHECKED);
        return String.format("Task with key: %s for Inquiry with Id: %d completed!", taskDefKey, inquiryId);
    }

    @GetMapping(path = "/closed-inquiries", produces = "application/json")
    public List<InquiryEntity> getClosedInquiries() {
        return inquiryService.getClosedInquiries();
    }

}
