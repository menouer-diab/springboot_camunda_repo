package com.example.workflow.creditrequest.repository;

import com.example.workflow.creditrequest.model.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRequestRepository extends JpaRepository<CreditRequestEntity, Long> {
}
