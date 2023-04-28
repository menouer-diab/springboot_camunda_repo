package com.example.workflow.creditrequest.api;

import com.example.workflow.creditrequest.model.CreditRequestEntity;
import com.example.workflow.creditrequest.model.CreditRequestStatusEnum;
import com.example.workflow.creditrequest.service.CreditRequestService;
import com.example.workflow.creditrequest.service.WorkflowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/creditrequest")
public class CreditRequestController {

    private final CreditRequestService creditRequestService;

    private final WorkflowService workflowService;

    public CreditRequestController(CreditRequestService creditRequestService, WorkflowService workflowService) {
        this.creditRequestService = creditRequestService;
        this.workflowService = workflowService;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public CreditRequestEntity createCreditRequest(@RequestBody CreditRequestEntity creditRequest) {
        return creditRequestService.saveCreditRequest(creditRequest);
    }

    @PostMapping(path = "/start-inquiry-process/{creditRequestId}", produces = "text/plain")
    public String startCreditRequestProcessInstance(@PathVariable("creditRequestId") Long creditRequestId) {
        String processInstanceId = workflowService.startProcessInstanceByKey(creditRequestId);
        return String.format("Process Instance with Id: %s created successfully for creditrequest with id: %d", processInstanceId, creditRequestId);
    }

    /*
     * Method to find all the user tasks defined with a given key and waiting for completion.
     * @param taskKey
     * The task definition key
     * @return List of Inquiries by task definition key
     */
    @GetMapping(path = "/{taskDefKey}", produces = "application/json")
    public List<CreditRequestEntity> getCreditRequestsByTaskKey(@PathVariable("taskDefKey") String taskDefinitionKey) {
        List<Long> creditRequestIds = workflowService.getCreditRequestIdsByTaskKey(taskDefinitionKey);
        return creditRequestService.findCreditRequestsByIds(creditRequestIds);
    }

    @GetMapping(path = "", produces = "application/json")
    public List<CreditRequestEntity> getAllCreditRequests() {
        return creditRequestService.getAllCreditRequests();
    }

    @PostMapping(path = "/complete-task/{creditRequestId}", produces = "text/plain")
    public String completeCreditRequestTask(@PathVariable Long creditRequestId, @RequestParam("taskDefKey") String taskDefKey) {
        CreditRequestEntity creditRequest = creditRequestService.getCreditRequestById(creditRequestId);
        workflowService.completeCreditRequestTask(taskDefKey, creditRequest);
        creditRequestService.updateStatus(creditRequest, CreditRequestStatusEnum.REQUEST_CHECKED);
        return String.format("Task with key: %s for credit request with Id: %d completed!", taskDefKey, creditRequestId);
    }

    @GetMapping(path = "/closed-inquiries", produces = "application/json")
    public List<CreditRequestEntity> getClosedCreditRequests() {
        return creditRequestService.getClosedCreditRequests();
    }

}
