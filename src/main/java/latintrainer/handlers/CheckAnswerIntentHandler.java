package main.java.latintrainer.handlers;


import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.response.ResponseBuilder;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static main.java.latintrainer.handlers.NextWordIntentHandler.currentQuery;
import static main.java.latintrainer.handlers.NextWordIntentHandler.ANSWER_SLOT;

public class CheckAnswerIntentHandler implements RequestHandler{
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("CheckAnswerIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        String a1 = (String) persistentAttributes.get("modus");

        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        // Get the color slot from the list of slots.
        Slot answerSlot = slots.get(ANSWER_SLOT);
        String answer = currentQuery.getGermanWord();
        String speechText, repromptText;
        boolean isAskResponse = false;

        // Check for favorite color and create output to user.
        if (answerSlot != null) {
            // Store the user's favorite color in the Session and create response.
            String userAnswer = answerSlot.getValue();
            if (userAnswer.equalsIgnoreCase(answer)) {
                speechText = String.format("Richtig. Sage Neues Wort um weiterzumachen und %s",a1);
                repromptText = "Sage Neues Wort um weiterzumachen";
            } else{
                speechText = String.format("Falsch. Es ist nicht %s. Willst du das Wort wiederholen, überspringen oder auflösen?", userAnswer);
                repromptText = "Willst du das Wort wiederholen, überspringen oder auflösen?";
            }
        } else {
            // Render an error since we don't know what the users answer is.
            speechText = "Das habe ich leider nicht verstanden. Bitte versuche es noch einmal.";
            repromptText = "Bitte versuche es noch einmal.";
            isAskResponse = true;
        }

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        responseBuilder.withSimpleCard("LatinTrainerSession", speechText)
                .withSpeech(speechText)
                .withShouldEndSession(false);

        if (isAskResponse) {
            responseBuilder.withShouldEndSession(false)
                    .withReprompt(repromptText);
        }

        return responseBuilder.build();
    }

}
