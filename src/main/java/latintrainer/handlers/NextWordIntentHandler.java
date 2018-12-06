package main.java.latintrainer.handlers;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

import main.java.latintrainer.model.Query;
import main.java.latintrainer.model.Session;
import main.java.latintrainer.model.*;


import static main.java.latintrainer.model.QueryList.WORDS;

public class NextWordIntentHandler implements RequestHandler{
    public static final String ANSWER_KEY = "ANSWER";
    public static final String ANSWER_SLOT = "Answer";
    public static Session currentSession;
    public static Query currentQuery;
    Mode sessionMode;
    Direction sessionDir;
    public static boolean currentDirIsGerman;
    public static boolean isChangingSession = true;
    private boolean isFirst = true;
    String savedMode;
    String savedDir;
    int savedChapter;
    int savedHighscore;

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("NextWordIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        if (isChangingSession) {
            AttributesManager attributesManager = input.getAttributesManager();
            Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
<<<<<<< HEAD
<<<<<<< HEAD
            persistentAttributes.put("kapitel", 1);
            persistentAttributes.put("highscore", 4);
            attributesManager.setPersistentAttributes(persistentAttributes);
            attributesManager.savePersistentAttributes();
=======
>>>>>>> 71ca4bde6cbacae9187b063083e274eb1aac4d60
=======
>>>>>>> 71ca4bde6cbacae9187b063083e274eb1aac4d60
            savedMode = (String) persistentAttributes.get("modus");
            savedDir = (String) persistentAttributes.get("richtung");
            savedChapter = Integer.parseInt((String)persistentAttributes.get("kapitel"));
            savedHighscore = Integer.parseInt((String) persistentAttributes.get("highscore"));


            switch (savedMode) {
                case "fortschritt":
                    sessionMode = Mode.PROGRESS;
                    break;
                case "zufall":
                    sessionMode = Mode.CHAPTER;
                    break;
                case "kapitel":
                    sessionMode = Mode.RANDOM;
                    break;
                default:
                    throw new RuntimeException();
            }
            switch (savedDir) {
                case "deutsch":
                    sessionDir = Direction.GERMAN;
                    currentDirIsGerman = true;
                    break;
                case "lateinisch":
                    sessionDir = Direction.LATIN;
                    currentDirIsGerman = false;
                    break;
                case "zufall":
                    sessionDir = Direction.RANDOM;
                    currentDirIsGerman = Math.random() < 0.5;
                    break;
                default:
                    throw new RuntimeException();
            }
            if (isFirst) {
                currentSession = new Session(sessionDir, sessionMode, savedChapter, savedHighscore);
                isFirst = false;
            } else {
                currentSession.setDir(sessionDir);
                currentSession.setMode(sessionMode);
                currentSession.setChapter(savedChapter);
                currentSession.setAllTimeHighscore(savedHighscore);
            }
            isChangingSession = false;
        }
        // check mode
        currentQuery = WORDS.get((int) (Math.random()*20));
        // check direction
        // String toTranslate = currentQuery.getLatinWord(); //old
        String toTranslate = currentDirIsGerman ? currentQuery.getLatinWord(): currentQuery.getGermanWord();
        String speechText = String.format("Das zu übersetzende Wort lautet %s. Bitte sage, die Antwort ist x y, " +
                "oder wenn du es nicht weißt, keine Ahnung. Session: %s, %s, %d, %d", toTranslate, sessionDir.getDirection(),
                sessionMode.getModeName(), savedChapter, savedHighscore);

        return input.getResponseBuilder()
                .withSimpleCard("LatinTrainerSession", speechText)
                .withSpeech(speechText)
                .withReprompt(speechText)
                .withShouldEndSession(false)
                .build();
    }
}