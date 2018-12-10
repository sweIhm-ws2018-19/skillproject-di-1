package test.java.latintrainer.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;
import main.java.latintrainer.handlers.CancelandStopIntentHandler;
import org.junit.Before;
import org.junit.Test;
import static main.java.latintrainer.model.LatinTrainerTools.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CancelandStopIntentHandlerTest {

    private CancelandStopIntentHandler sut;
    private HandlerInput inputMock;

    @Before
    public void setup() {
        sut = new CancelandStopIntentHandler();
        inputMock = mock(HandlerInput.class);
    }

    @Test
    public void testCtor() {
        assertEquals(sut.getClass(), CancelandStopIntentHandler.class);
    }

    @Test
    public void testCanHandle() {
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(sut.canHandle(inputMock));
    }
/*
    @Test
    public void testHandle() {
        AttributesManager attributesManager = mock(AttributesManager.class);
        Map<String, Object> persistentAttributes = new HashMap<String, Object>();
        persistentAttributes.put(MODE, new HashMap<String, Object>());

        when(inputMock.getAttributesManager()).thenReturn(attributesManager);
        when(attributesManager.getPersistentAttributes()).thenReturn(persistentAttributes);
        when(inputMock.getResponseBuilder()).thenReturn(new ResponseBuilder());

        Optional<Response> response = sut.handle(inputMock);
        assertTrue(response.isPresent());
        assertFalse(response.get().getShouldEndSession());
    }
*/
}
