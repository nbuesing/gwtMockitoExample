package com.objectpartners.buesing.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("SampleApplicationService")
public interface SampleApplicationService extends RemoteService {
    // Sample interface method of remote interface
    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use SampleApplicationService.App.getInstance() to access static instance of SampleApplicationServiceAsync
     */
    public static class App {
        private static SampleApplicationServiceAsync ourInstance = GWT.create(SampleApplicationService.class);

        public static synchronized SampleApplicationServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
