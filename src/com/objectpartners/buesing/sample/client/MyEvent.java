package com.objectpartners.buesing.sample.client;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class MyEvent extends GwtEvent<MyEvent.MyEventHandler> {

    public interface MyEventHandler extends EventHandler {
        void doSomething(MyEvent event);
    }

    private static Type<MyEventHandler> TYPE;

    public static Type<MyEventHandler> getType() {
        return TYPE != null ? TYPE : (TYPE = new Type<MyEventHandler>());
    }

    @Override
    public final Type<MyEventHandler> getAssociatedType() {
        return (Type) TYPE;
    }

    @Override
    protected void dispatch(MyEventHandler handler) {
        handler.doSomething(this);
    }
}
