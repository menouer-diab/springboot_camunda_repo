package com.example.workflow.inquiry.service;

import com.example.workflow.inquiry.model.InquiryEntity;
import com.example.workflow.inquiry.model.InquiryStatusEnum;

import java.util.List;

public interface InquiryService {
    InquiryEntity findInquiryById(Long id);

    InquiryEntity saveInquiry(InquiryEntity inquiry);

    void changeInquiryStatus(InquiryEntity inquiry, InquiryStatusEnum newStatus);

    List<InquiryEntity> getInquiriesByTaskKey(String taskDefinitionKey);

    List<InquiryEntity> getClosedInquiries();
}
