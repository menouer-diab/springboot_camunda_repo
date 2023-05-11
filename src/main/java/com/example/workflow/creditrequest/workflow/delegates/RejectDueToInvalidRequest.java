package com.example.workflow.creditrequest.workflow.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RejectDueToInvalidRequest implements JavaDelegate {

    private final static Logger LOG = LoggerFactory.getLogger(RejectDueToInvalidRequest.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOG.info("Run RejectDueToInvalidRequest");
    }
}
