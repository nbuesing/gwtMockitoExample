package com.objectpartners.buesing.sample.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.objectpartners.buesing.sample.client.SampleApplicationService;

public class SampleApplicationServiceImpl extends RemoteServiceServlet implements SampleApplicationService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}