package main.java.latintrainer.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

import static main.java.latintrainer.handlers.LaunchRequestHandler.CURRENT_SESSION;

public class SetConfigIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SetConfigIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        CURRENT_SESSION.updateHighscore();
        CURRENT_SESSION.setCurrentHandler("SetConfig");
        String speechText = "Okay, Du kannst zwischen den Modi Zufall, Fortschritt und Kapitel entscheiden. " +
                "Sage zum Beispiel: Wähle Modus Fortschritt oder Hilfe für genauere Informationen";
        String repromptText = "Sage zum Beispiel: Wähle Modus Fortschritt";
        return input.getResponseBuilder()
                .withSimpleCard("LatinTrainerSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }
}
