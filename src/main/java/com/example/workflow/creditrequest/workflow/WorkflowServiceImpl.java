package com.example.workflow.creditrequest.workflow;

import com.example.workflow.creditrequest.repository.CreditRequestRepository;
import com.example.workflow.creditrequest.workflow.util.WorkflowUtility;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkflowServiceImpl implements WorkflowService {

    private final CreditRequestRepository creditRequestRepository;

    private WorkflowUtility utility;

    public WorkflowServiceImpl(CreditRequestRepository inquiryRepository, WorkflowUtility utility) {
        this.creditRequestRepository = inquiryRepository;
        this.utility = utility;
    }

    @Override
    public String startCreditRequestWorkflowInstance(Long businessObjectId) {
        return utility.startCreditRequestWorkflowInstance(businessObjectId);
    }

    @Override
    public List<Long> getIdsOfCreditRequestsToBeReviewed() {
        return utility.getCreditRequestIdsByTaskKey(TaskKeyEnum.TK_REVIEW_CREDIT_REQUEST);
    }

    @Override
    public List<Long> getIdsOfCreditRequestsRequiringCustomerSolvencyCheck() {
        return utility.getCreditRequestIdsByTaskKey(TaskKeyEnum.TK_CHECK_CUSTOMER_SOLVENCY);
    }

    @Override
    public List<Long> getIdsOfCreditRequestsRequiringMoneyTransfer() {
        return utility.getCreditRequestIdsByTaskKey(TaskKeyEnum.TK_TRANSFER_MONEY_TO_CUSTOMER);
    }

    @Override
    public List<Long> getIdsOfCreditRequestsToBeArchived() {
        return utility.getCreditRequestIdsByTaskKey(TaskKeyEnum.TK_ARCHIVE_CREDIT_REQUEST);
    }

    @Override
    public List<Long> getIdsOfNonValidCreditRequests() {
        return utility.getCreditRequestIdsClosedWithStatus(CreditRequestStatusEnum.NON_VALID_REQUEST_REJECTED);
    }

    @Override
    public List<Long> getIdsOfCreditRequestsRejectedDueToCustomerSolvency() {
        return utility.getCreditRequestIdsClosedWithStatus(CreditRequestStatusEnum.REQUEST_REJECTED_DUE_TO_SOLVENCY);
    }

    @Override
    public List<Long> getIdsOfApprovedCreditRequests() {
        return utility.getCreditRequestIdsClosedWithStatus(CreditRequestStatusEnum.REQUEST_APPROVED);
    }

    @Override
    public void completeCreditRequestReviewTask(Long creditRequestId, String decision) {
        utility.completeTaskByTaskDefKey(TaskKeyEnum.TK_REVIEW_CREDIT_REQUEST, creditRequestId, decision);
    }

}
