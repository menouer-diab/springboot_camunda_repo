package com.example.workflow.creditrequest.repository;

import com.example.workflow.creditrequest.model.CreditRequestEntity;
import com.example.workflow.creditrequest.workflow.CreditRequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequestEntity, Long> {
    List<CreditRequestEntity> findByStatus(CreditRequestStatusEnum status);
}
