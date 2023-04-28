package com.example.workflow.creditrequest.service;

import com.example.workflow.creditrequest.model.CreditRequestEntity;
import com.example.workflow.creditrequest.model.CreditRequestStatusEnum;

import java.util.List;

public interface CreditRequestService {

    CreditRequestEntity saveCreditRequest(CreditRequestEntity creditRequest);

    void changeCreditRequestStatus(CreditRequestEntity creditRequest, CreditRequestStatusEnum newStatus);

    List<CreditRequestEntity> getClosedCreditRequests();

    List<CreditRequestEntity> getAllCreditRequests();

    CreditRequestEntity getCreditRequestById(Long creditRequestId);

    List<CreditRequestEntity> findCreditRequestsByIds(List<Long> creditRequestsIds);

    void updateStatus(CreditRequestEntity creditRequest, CreditRequestStatusEnum requestChecked);
}
