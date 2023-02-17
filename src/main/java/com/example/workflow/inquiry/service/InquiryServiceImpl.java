package com.example.workflow.inquiry.service;

import com.example.workflow.inquiry.model.InquiryEntity;
import com.example.workflow.inquiry.model.InquiryStatusEnum;
import com.example.workflow.inquiry.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryServiceImpl implements InquiryService {

    @Autowired
    InquiryRepository repository;

    @Override
    public InquiryEntity findInquiryById(Long id) {
        return Optional.of(repository.findById(id)).get().orElse(null);
    }

    @Override
    public InquiryEntity saveInquiry(InquiryEntity inquiry) {
        return repository.save(inquiry);
    }

    @Override
    public void changeInquiryStatus(InquiryEntity inquiry, InquiryStatusEnum newStatus) {
        inquiry.setStatus(newStatus);
        repository.save(inquiry);
    }

    @Override
    public List<InquiryEntity> getInquiriesByTaskKey(String taskDefinitionKey) {
        return null;
    }

    @Override
    public List<InquiryEntity> getClosedInquiries() {
        return null;
    }
}
