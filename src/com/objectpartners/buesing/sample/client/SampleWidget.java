package com.objectpartners.buesing.sample.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

public class SampleWidget implements IsWidget {

    private EventBus bus = GWT.create(SimpleEventBus.class);

    private VerticalPanel panel = GWT.create(VerticalPanel.class);


    public SampleWidget() {
        final Button button = GWT.create(Button.class);
        button.setText("Click me");
        final Label label = GWT.create(Label.class);
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    SampleApplicationService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }

                bus.fireEvent(new MyEvent());
            }
        });

        final HTML html = GWT.create(HTML.class);

        bus.addHandler(MyEvent.getType(), new MyEvent.MyEventHandler() {
            public void doSomething(MyEvent event) {
                html.setHTML("event fired.");
            }
        });

        panel.add(button);
        panel.add(label);
        panel.add(html);
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }

    public Widget asWidget() {
       return panel;
    }
}
