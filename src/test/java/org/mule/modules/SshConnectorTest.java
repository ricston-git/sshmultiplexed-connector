/**
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */

/**
 * This file was automatically generated by the Mule Development Kit
 */
package org.mule.modules;

import junit.framework.Assert;

import org.junit.Test;
import org.mule.api.MuleEvent;
import org.mule.construct.Flow;
import org.mule.tck.junit4.FunctionalTestCase;

public class SshConnectorTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources() {
        return "mule-config.xml";
    }

    @Test
    public void singleCall() throws Exception {
    	runFlowAndExpect("singleCallTest");
    	Thread.sleep(5000L);
    }
    
    @Test
    public void shellMode() throws Exception {
    	runFlowAndExpect("shellModeTest");
    	Thread.sleep(15000L);
    	
    }
    
    /**
    * Run the flow specified by name and assert equality on the expected output
    *
    * @param flowName The name of the flow to run
    */
    protected void runFlowAndExpect(String flowName) throws Exception {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = getTestEvent(null);
        flow.process(event);
    }

    /**
    * Run the flow specified by name using the specified payload and assert
    * equality on the expected output
    *
    * @param flowName The name of the flow to run
    * @param expect The expected output
    * @param payload The payload of the input event
    */
    protected <T, U> void runFlowWithPayloadAndExpect(String flowName, T expect, U payload) throws Exception {
        Flow flow = lookupFlowConstruct(flowName);
        MuleEvent event = getTestEvent(payload);
        MuleEvent responseEvent = flow.process(event);

        Assert.assertEquals(expect, responseEvent.getMessage().getPayload());
    }

    /**
     * Retrieve a flow by name from the registry
     *
     * @param name Name of the flow to retrieve
     */
    protected Flow lookupFlowConstruct(String name) {
        return (Flow) muleContext.getRegistry().lookupFlowConstruct(name);
    }
}
