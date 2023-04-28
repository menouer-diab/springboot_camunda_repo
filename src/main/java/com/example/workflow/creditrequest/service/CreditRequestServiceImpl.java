package com.example.workflow.creditrequest.service;

import com.example.workflow.creditrequest.model.CreditRequestEntity;
import com.example.workflow.creditrequest.model.CreditRequestStatusEnum;
import com.example.workflow.creditrequest.repository.CreditRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditRequestServiceImpl implements CreditRequestService {

    private final CreditRequestRepository repository;

    public CreditRequestServiceImpl(CreditRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CreditRequestEntity> findCreditRequestsByIds(List<Long> creditRequestsIds) {
        return repository.findAllById(creditRequestsIds);
    }

    @Override
    public void updateStatus(CreditRequestEntity creditRequest, CreditRequestStatusEnum newStatus) {
        creditRequest.setStatus(newStatus);
        repository.save(creditRequest);
    }

    @Override
    public CreditRequestEntity saveCreditRequest(CreditRequestEntity creditRequest) {
        return repository.save(creditRequest);
    }

    @Override
    public void changeCreditRequestStatus(CreditRequestEntity creditRequest, CreditRequestStatusEnum newStatus) {
        creditRequest.setStatus(newStatus);
        repository.save(creditRequest);
    }

    @Override
    public List<CreditRequestEntity> getClosedCreditRequests() {
        return null;
    }

    @Override
    public List<CreditRequestEntity> getAllCreditRequests() {
        return repository.findAll();
    }

    @Override
    public CreditRequestEntity getCreditRequestById(Long creditRequestId) {
        return Optional.of(repository.findById(creditRequestId)).get().orElse(null);
    }

}
