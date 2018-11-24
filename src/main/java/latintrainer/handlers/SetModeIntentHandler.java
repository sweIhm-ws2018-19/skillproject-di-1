package main.java.latintrainer.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class SetModeIntentHandler implements RequestHandler{
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SetModeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Danke für das Öffnen von Latein Trainer. Latein Trainer hilft dir, deine lateinischen " +
                "Vokabeln besser zu verinnerlichen. Außerdem kannst du selber festlegen, wie du abgefragt werden " +
                "willst. Möchtest du die Erfahrung beginnen? Sage Starte die Demo, um loszulegen.";
        String repromptText = "Sage Start, um loszulegen.";
        return input.getResponseBuilder()
                .withSimpleCard("LatinTrainerSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }
}
