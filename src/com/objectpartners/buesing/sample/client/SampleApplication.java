package com.objectpartners.buesing.sample.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class SampleApplication implements EntryPoint {
    public void onModuleLoad() {
        RootPanel.get("widget").add(new SampleWidget());


    }
}
