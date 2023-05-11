package com.example.workflow.creditrequest.service;

import com.example.workflow.creditrequest.model.CreditRequestEntity;

import java.util.List;

public interface CreditRequestService {

    CreditRequestEntity createCreditRequest(CreditRequestEntity creditRequest);

    List<CreditRequestEntity> getCreditRequestsToBeReviewed();

    List<CreditRequestEntity> getCreditRequestsRequiringCustomerSolvencyCheck();

    List<CreditRequestEntity> getCreditRequestsRequiringMoneyTransfer();

    List<CreditRequestEntity> getCreditRequestsToBeArchived();

    List<CreditRequestEntity> getNonValidCreditRequests();

    List<CreditRequestEntity> getCreditRequestsRejectedDueToCustomerSolvency();

    List<CreditRequestEntity> getApprovedCreditRequests();

    void completeCreditRequestReviewTask(Long creditRequestId, String decision);

    void completeCreditRequestMoneyTransferTask(Long creditRequestId);

    void completeCreditRequestArchivingTask(Long creditRequestId);

    void completeCreditRequestSolvencyCheckTask(Long creditRequestId, String decision);
}
