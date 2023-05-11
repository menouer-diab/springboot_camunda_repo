package com.example.workflow.creditrequest.api;

import com.example.workflow.creditrequest.model.CreditRequestEntity;
import com.example.workflow.creditrequest.service.CreditRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/credit-requests")
public class CreditRequestController {

    private final CreditRequestService creditRequestService;

    public CreditRequestController(CreditRequestService creditRequestService) {
        this.creditRequestService = creditRequestService;
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public CreditRequestEntity createCreditRequest(@RequestBody CreditRequestEntity creditRequest) {
        return creditRequestService.createCreditRequest(creditRequest);
    }

    // Methods delivering credit Requests to be processed.

    @GetMapping(path = "/to-be-checked", produces = "application/json")
    public List<CreditRequestEntity> getCreditRequestsToBeReviewed() {
        // List<Long> creditRequestIds = workflowService.getCreditRequestIdsByTaskKey(taskDefinitionKey);
        return creditRequestService.getCreditRequestsToBeReviewed();
    }

    @GetMapping(path = "/customer-solvency-check", produces = "application/json")
    public List<CreditRequestEntity> getCreditRequestsRequiringCustomerSolvencyCheck() {
        return creditRequestService.getCreditRequestsRequiringCustomerSolvencyCheck();
    }

    @GetMapping(path = "/???", produces = "application/json")
    public List<CreditRequestEntity> getCreditRequestsRequiringMoneyTransfer() {
        return creditRequestService.getCreditRequestsRequiringMoneyTransfer();
    }

    @GetMapping(path = "/???", produces = "application/json")
    public List<CreditRequestEntity> getCreditRequestsToBeArchived() {
        return creditRequestService.getCreditRequestsToBeArchived();
    }

    @GetMapping(path = "/???", produces = "application/json")
    public List<CreditRequestEntity> getNonValidCreditRequests() {
        return creditRequestService.getNonValidCreditRequests();
    }

    @GetMapping(path = "/???", produces = "application/json")
    public List<CreditRequestEntity> getCreditRequestsRejectedDueToCustomerSolvency() {
        return creditRequestService.getCreditRequestsRejectedDueToCustomerSolvency();
    }

    @GetMapping(path = "/???", produces = "application/json")
    public List<CreditRequestEntity> getApprovedCreditRequests() {
        return creditRequestService.getApprovedCreditRequests();
    }

    // Methods for tasks execution
    @PatchMapping(path = "/{creditRequestId}/{decision}", produces = "application/json")
    public ResponseEntity<String> completeCreditRequestReviewTask(@PathVariable Long creditRequestId, @PathVariable String decision) {
        creditRequestService.completeCreditRequestReviewTask(creditRequestId, decision);
        return new ResponseEntity<>(
                String.format("Credit request with Id: %d has been reviewed. The check decision was %s", creditRequestId, decision),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/{creditRequestId}/{decision}", produces = "application/json")
    public ResponseEntity<String> completeCreditRequestSolvencyCheckTask(@PathVariable Long creditRequestId) {
        creditRequestService.completeCreditRequestSolvencyCheckTask(creditRequestId);
        return new ResponseEntity<>(
                String.format("Money has been transfered to customer of the credit request with Id: %d .", creditRequestId),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/{creditRequestId}/{decision}", produces = "application/json")
    public ResponseEntity<String> completeCreditRequestMoneyTransferTask(@PathVariable Long creditRequestId) {
        creditRequestService.completeCreditRequestMoneyTransferTask(creditRequestId);
        return new ResponseEntity<>(
                String.format("Money has been transfered to customer of the credit request with Id: %d .", creditRequestId),
                HttpStatus.OK);
    }

    @PatchMapping(path = "/{creditRequestId}", produces = "application/json")
    public ResponseEntity<String> completeCreditRequestArchivingTask(@PathVariable Long creditRequestId) {
        creditRequestService.completeCreditRequestArchivingTask(creditRequestId);
        return new ResponseEntity<>(
                String.format("Credit request with Id: %d has been archived", creditRequestId),
                HttpStatus.OK);
    }

}
