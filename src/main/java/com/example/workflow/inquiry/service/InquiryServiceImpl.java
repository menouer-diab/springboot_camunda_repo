package com.example.workflow.inquiry.service;

import com.example.workflow.inquiry.model.InquiryEntity;
import com.example.workflow.inquiry.model.InquiryStatusEnum;
import com.example.workflow.inquiry.repository.InquiryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository repository;

    public InquiryServiceImpl(InquiryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InquiryEntity> findInquiriesById(List<Long> inquiryBusinessObjectIds) {
        return repository.findAllById(inquiryBusinessObjectIds);
    }

    @Override
    public void updateStatus(InquiryEntity inquiry, InquiryStatusEnum newStatus) {
        inquiry.setStatus(newStatus);
        repository.save(inquiry);
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
    public List<InquiryEntity> getClosedInquiries() {
        return null;
    }

    @Override
    public List<InquiryEntity> getAllInquiries() {
        return repository.findAll();
    }

    @Override
    public InquiryEntity getInquiryById(Long inquiryId) {
        return Optional.of(repository.findById(inquiryId)).get().orElse(null);
    }

}
