package com.example.workflow.inquiry.model;

import javax.persistence.*;

@Entity(name="inquiry")
public class InquiryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="score")
    private Integer score;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private InquiryStatusEnum status;

    @Column(name="amount_in_euros")
    private Double amountInEuros;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public InquiryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InquiryStatusEnum status) {
        this.status = status;
    }

    public Double getAmountInEuros() {
        return amountInEuros;
    }

    public void setAmountInEuros(Double amountInEuros) {
        this.amountInEuros = amountInEuros;
    }
}
