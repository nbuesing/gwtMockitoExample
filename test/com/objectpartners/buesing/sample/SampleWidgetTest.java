package com.objectpartners.buesing.sample;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.objectpartners.buesing.sample.client.SampleApplicationServiceAsync;
import com.objectpartners.buesing.sample.client.SampleWidget;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Collection;

import static org.mockito.Matchers.any;

@RunWith(GwtMockitoTestRunner.class)
public class SampleWidgetTest {

    // gwtmockito 1.1.x ...
    public static class TestRunner extends GwtMockitoTestRunner {
        public TestRunner(Class<?> unitTestClass) throws InitializationError {
            super(unitTestClass);
        }
        protected Collection<Class<?>> getClassesToStub() {
            Collection<Class<?>> classes = super.getClassesToStub();
            //classes.add();
            return classes;
        }
    }

    private SampleWidget sampleWidget;

    @GwtMock
    private Button button;

    @GwtMock
    private Label label;

    @GwtMock
    private Element element;

    @GwtMock
    private SampleApplicationServiceAsync sampleApplicationService;

    private ClickHandler clickHandler;

    @Before
    public void setUp() {

        Mockito.doAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                String msg = (String)invocation.getArguments()[0];
                AsyncCallback <String> callback = (AsyncCallback<String>) invocation.getArguments()[1];
                callback.onSuccess("Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"");
                return null;
            }
        }).when(sampleApplicationService)
                .getMessage(any(String.class), (AsyncCallback<String>) any());

        Mockito.when(button.addClickHandler(Mockito.any(ClickHandler.class))).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock aInvocation) throws Throwable {
                clickHandler = (ClickHandler) aInvocation.getArguments()[0];
                return null;
            }
        });


        Mockito.when(label.getText()).thenReturn("");

        Mockito.when(label.getElement()).thenReturn(element);

        // Mocking the click handler needs to be done BEFORE the constructor is called, so the 'clickHandler' can be extracted from the SampleWidget.
        sampleWidget = new SampleWidget();
    }

    @Test
    public void clickOnButton() {
        clickHandler.onClick(new ClickEvent(){});
        Mockito.verify(element).setInnerHTML("Client said: \"Hello, World!\"<br>Server answered: \"Hi!\"");
    }
}
