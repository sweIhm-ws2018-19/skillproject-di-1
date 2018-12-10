package test.java.latintrainer.handlers;


import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import main.java.latintrainer.handlers.CheckAnswerIntentHandler;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckAnswerIntentHandlerTest {

    private CheckAnswerIntentHandler sut;
    private HandlerInput inputMock;

    @Before
    public void setup() {
        sut = new CheckAnswerIntentHandler();
        inputMock = mock(HandlerInput.class);
    }

    @Test
    public void testCtor() {
        assertEquals(sut.getClass(), CheckAnswerIntentHandler.class);
    }

    @Test
    public void testCanHandle() {
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(sut.canHandle(inputMock));
    }

}
