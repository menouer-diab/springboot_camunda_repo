package com.example.workflow.creditrequest.service;

import com.example.workflow.creditrequest.model.CreditRequestEntity;
import com.example.workflow.creditrequest.repository.CreditRequestRepository;
import com.example.workflow.creditrequest.workflow.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CreditRequestServiceImpl implements CreditRequestService {

    private static final Logger logger = LoggerFactory.getLogger(CreditRequestServiceImpl.class);

    private final CreditRequestRepository repository;

    private final WorkflowService workflowService;

    public CreditRequestServiceImpl(CreditRequestRepository repository, WorkflowService workflowService1) {
        this.repository = repository;
        this.workflowService = workflowService1;
    }

    @Override
    public CreditRequestEntity createCreditRequest(CreditRequestEntity creditRequest) {
        CreditRequestEntity request = repository.save(creditRequest);
        Long creditRequestId = request.getId();
        String processInstanceId = workflowService.startCreditRequestWorkflowInstance(creditRequestId);
        logger.info(String.format("Process Instance with Id: %s created successfully for credit-request with id: %d", processInstanceId, creditRequestId));
        return request;
    }

    @Override
    public List<CreditRequestEntity> getCreditRequestsToBeReviewed() {
        List<Long> ids = workflowService.getIdsOfCreditRequestsToBeReviewed();
        return repository.findAllById(ids);
    }

    @Override
    public List<CreditRequestEntity> getCreditRequestsRequiringCustomerSolvencyCheck() {
        List<Long> ids = workflowService.getIdsOfCreditRequestsRequiringCustomerSolvencyCheck();
        return repository.findAllById(ids);
    }

    @Override
    public List<CreditRequestEntity> getCreditRequestsRequiringMoneyTransfer() {
        List<Long> ids = workflowService.getIdsOfCreditRequestsRequiringMoneyTransfer();
        return repository.findAllById(ids);
    }

    @Override
    public List<CreditRequestEntity> getCreditRequestsToBeArchived() {
        List<Long> ids = workflowService.getIdsOfCreditRequestsToBeArchived();
        return repository.findAllById(ids);
    }

    @Override
    public List<CreditRequestEntity> getNonValidCreditRequests() {
        List<Long> ids = workflowService.getIdsOfNonValidCreditRequests();
        return repository.findAllById(ids);
    }

    @Override
    public List<CreditRequestEntity> getCreditRequestsRejectedDueToCustomerSolvency() {
        List<Long> ids = workflowService.getIdsOfCreditRequestsRejectedDueToCustomerSolvency();
        return repository.findAllById(ids);
    }

    @Override
    public List<CreditRequestEntity> getApprovedCreditRequests() {
        List<Long> ids = workflowService.getIdsOfApprovedCreditRequests();
        return repository.findAllById(ids);
    }

    @Override
    public void completeCreditRequestReviewTask(Long creditRequestId, String decision) {
        workflowService.completeCreditRequestReviewTask(creditRequestId, decision);
    }

    @Override
    public void completeCreditRequestSolvencyCheckTask(Long creditRequestId, String decision) {

    }

    @Override
    public void completeCreditRequestMoneyTransferTask(Long creditRequestId) {

    }

    @Override
    public void completeCreditRequestArchivingTask(Long creditRequestId) {

    }

}
