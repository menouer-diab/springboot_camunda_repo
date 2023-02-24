package com.example.workflow.inquiry.service;

import com.example.workflow.inquiry.model.InquiryEntity;
import com.example.workflow.inquiry.model.InquiryStatusEnum;

import java.util.List;

public interface InquiryService {

    InquiryEntity saveInquiry(InquiryEntity inquiry);

    void changeInquiryStatus(InquiryEntity inquiry, InquiryStatusEnum newStatus);

    List<InquiryEntity> getClosedInquiries();

    List<InquiryEntity> getAllInquiries();

    InquiryEntity getInquiryById(Long inquiryId);

    List<InquiryEntity> findInquiriesById(List<Long> inquiryBusinessObjectIds);

    void updateStatus(InquiryEntity inquiry, InquiryStatusEnum requestChecked);
}
