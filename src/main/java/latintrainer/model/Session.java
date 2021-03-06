package main.java.latintrainer.model;

import java.util.List;
import java.util.Random;

public class Session {

    private Direction dir;
    private Mode mode;
    private Chapter chapter;
    private List<Query> wordList;
    private Query currentQuery;
    private int currentWordIndex;
    private Highscore currentHighscore = new Highscore(0);
    private Highscore allTimeHighscore;
    private boolean[] alreadyAsked;
    private boolean[] answeredCorrectly;
    private boolean isChangingSession;
    private String currentHandler;
    private int correct;
    private int asked;
    private boolean lastWordAnswered = true;


    public Session() {
        currentWordIndex = 0;
        alreadyAsked = new boolean[20];
        answeredCorrectly = new boolean[20];
        isChangingSession = true;
        currentHandler = "Launch";
        chapter = new Chapter(0);
        wordList = this.chapter.getWordsOfThisChapter();
        currentQuery = wordList.get(currentWordIndex);
    }


    public Query nextQuery() {

        if(lastWordAnswered) {
            currentWordIndex = mode == Mode.RANDOM ? new Random().nextInt(wordList.size()) : (currentWordIndex++) % wordList.size();
            int savePoint = currentWordIndex;
            currentQuery = checkForNextUnasked(currentWordIndex);

            if (currentQuery == null)
                currentQuery = checkForNextFailed(savePoint);

            if (currentQuery == null) {
                setChapter(chapter.getChapterAsInt() + 1);
                alreadyAsked = new boolean[wordList.size()];
                answeredCorrectly = new boolean[wordList.size()];
                setIsChangingSession(false);
            }
            asked++;
            lastWordAnswered = false;
        }
        return currentQuery;
    }

    public void setLastWordAnswered() {
        lastWordAnswered = true;
    }

    public Query getCurrentWord() {
        return currentQuery;
    }

    public String getCurrentHandler() {
        return currentHandler;
    }

    public Session setCurrentHandler(String handler) {
        currentHandler = handler;
        return this;
    }

    public void answeredCorrectly() {
        setLastWordAnswered();
        correct++;
        answeredCorrectly[currentWordIndex] = true;
    }

    public int getAnsweredCorrectlyAsInt() {
        return getBooleanArrayAsInt(answeredCorrectly);
    }

    public int getAlreadyAskedAsInt() {
        return getBooleanArrayAsInt(alreadyAsked);
    }

    private int getBooleanArrayAsInt(boolean[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i])
                result++;
        }
        return result;
    }

    public boolean isChangingSession() {
        return isChangingSession;
    }

    public void setIsChangingSession(boolean changes) {
        isChangingSession = changes;
    }

    public int getChapterSize(){
        return wordList.size();
    }

    private Query checkForNextUnasked(int index) {
        return findSpecificQuery(alreadyAsked, index, true);
    }

    private Query checkForNextFailed(int index) {
        return findSpecificQuery(answeredCorrectly, index, false);
    }

    private Query findSpecificQuery(boolean[] arr, int index, boolean flag) {
        int i = index;
        int counter = 0;
        while(arr[i] && counter < wordList.size()) {
            i = (i+1)%wordList.size();
            counter++;
        }
        currentWordIndex = i;

        // flag for setting the alreadyAskedArray on index i to true
        if(flag)
            arr[i] = true;

        return counter < wordList.size()? wordList.get(i) : null;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public Session setChapter(int index) {
        chapter.setChapterNumber(index);
        currentWordIndex = 0;
        wordList = chapter.getWordsOfThisChapter();
        currentQuery = wordList.get(currentWordIndex);
        return this;
    }

    public Direction getDir() {
        return dir;
    }

    public Session setDir(Direction dir) {
        this.dir = dir;
        return this;
    }

    public Mode getMode() {
        return mode;
    }

    public Session setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public void updateHighscore() {
        int localAsked = lastWordAnswered? asked : asked-1;
        int updatedHighscore = (correct*2) + ((correct*2) - (localAsked*2));
        updatedHighscore = updatedHighscore < 0? 0 : updatedHighscore;
        getCurrentHighscore().setHighscoreValue(updatedHighscore);
    }

    public Highscore getCurrentHighscore() {
        return currentHighscore;
    }

    public Highscore getAllTimeHighscore() {
        return allTimeHighscore;
    }

    public Session setAllTimeHighscore(int highscore) {
        this.allTimeHighscore = new Highscore(highscore);
        return this;
    }

    @Override
    public String toString() {
        return "Richtung: " + dir + "; Modus: " + mode + "; Highscore: " + currentHighscore
                + "; Kapitel: " + chapter;
    }
}