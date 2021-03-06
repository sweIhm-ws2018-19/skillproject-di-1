package test.java.latintrainer.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import main.java.latintrainer.handlers.LaunchRequestHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class LaunchRequestHandlerTest {

    private LaunchRequestHandler sut;

    @Before
    public void setup() {
        sut = new LaunchRequestHandler();
    }

    @Test
    public void testCtor() {
        assertEquals(sut.getClass(), LaunchRequestHandler.class);
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(sut.canHandle(inputMock));
    }

    @Test
    public void testHandle() {
        final Response response = TestUtil.standardTestForHandle(sut);
        assertTrue(response.getOutputSpeech().toString().contains("Hallo. Ich bin Dein Latein Trainer. Sage starte direkt, um direkt anzufangen, oder Hilfe, " +
                "um Zusatzinformationen zu bekommen (besonders für Erstbenutzer empfohlen)."));
    }

}
