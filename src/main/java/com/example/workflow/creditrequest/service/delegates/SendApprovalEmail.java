package com.example.workflow.creditrequest.service.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendApprovalEmail implements JavaDelegate {

    private final static Logger LOG = LoggerFactory.getLogger(SendApprovalEmail.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOG.info("Run SendApprovalEmail");
    }
}
