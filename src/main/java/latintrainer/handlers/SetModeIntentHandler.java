package main.java.latintrainer.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
<<<<<<< HEAD
import main.java.latintrainer.model.LatinTrainerTools;
=======
>>>>>>> 71ca4bde6cbacae9187b063083e274eb1aac4d60

import java.util.Map;
import java.util.Optional;

import static main.java.latintrainer.handlers.LaunchRequestHandler.MODE_SLOT;

import static com.amazon.ask.request.Predicates.intentName;
import static main.java.latintrainer.handlers.SetConfigIntentHandler.MODE_SLOT;

public class SetModeIntentHandler implements RequestHandler{
<<<<<<< HEAD

=======
>>>>>>> 71ca4bde6cbacae9187b063083e274eb1aac4d60
    public static String DIR_SLOT = "dir";

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("SetModeIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
<<<<<<< HEAD
        Slot answerSlot = LatinTrainerTools.getAnswerSlot(DIR_SLOT, input);  // slots.get(MODE_SLOT);
=======
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        Slot answerSlot = slots.get(MODE_SLOT);
>>>>>>> 71ca4bde6cbacae9187b063083e274eb1aac4d60

        String speechText;
        String repromptText;

        if (answerSlot != null) {

            String userAnswer = answerSlot.getValue();

            if (userAnswer.equalsIgnoreCase("Fortschritt") || userAnswer.equalsIgnoreCase("Zufall")) {
<<<<<<< HEAD
                speechText = "Wenn ich lateinische Vokabeln ansagen soll, sage lateinisch. Ansonsten sage deutsch.";
                LatinTrainerTools.saveData("modus", userAnswer, input);
=======
                speechText = String.format("Okay. Dein Modus ist %s. Waehle nun die Richtung: Willst du lieber die " +
                        "deutschen oder die lateinischen Worte sagen? Sage zum Beispiel Waehle Richtung deutsch.", userAnswer);
                AttributesManager attributesManager = input.getAttributesManager();
                Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
                persistentAttributes.put("modus", userAnswer.toLowerCase());
                attributesManager.setPersistentAttributes(persistentAttributes);
                attributesManager.savePersistentAttributes();
>>>>>>> 71ca4bde6cbacae9187b063083e274eb1aac4d60
                repromptText = "Sage deutsch oder lateinisch.";
            } else {
                speechText = "Ich konnte dich nicht verstehen. Sage Fortschritt für deinen letzten Speicherstand oder Zufall für eine zufällige Abfrage.";
                repromptText = "Sage Fortschritt oder Zufall.";
            }
        }
        else {
            speechText = "Das habe ich leider nicht verstanden. Bitte versuche es noch einmal.";
            repromptText = "Sage Fortschritt oder Zufall.";
        }

        return input.getResponseBuilder()
                .withSimpleCard("LatinTrainerSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }
}
