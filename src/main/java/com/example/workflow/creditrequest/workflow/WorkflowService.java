package com.example.workflow.creditrequest.workflow;

import java.util.List;

public interface WorkflowService {
    public String startCreditRequestWorkflowInstance(Long businessObjectId);

    List<Long> getIdsOfCreditRequestsToBeReviewed();

    List<Long> getIdsOfCreditRequestsRequiringCustomerSolvencyCheck();

    List<Long> getIdsOfCreditRequestsRequiringMoneyTransfer();

    List<Long> getIdsOfCreditRequestsToBeArchived();

    List<Long> getIdsOfNonValidCreditRequests();

    List<Long> getIdsOfCreditRequestsRejectedDueToCustomerSolvency();

    List<Long> getIdsOfApprovedCreditRequests();

    void completeCreditRequestReviewTask(Long creditRequestId, String decision);
}
