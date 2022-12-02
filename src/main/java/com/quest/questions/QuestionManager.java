package com.quest.questions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionManager {

    public final static Map<Integer, Question> questionList = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        int id = 0;
        questionList.put(++id, new Question(id, "You have lost your memory. Take the UFO Challenge?", List.of(
                new Answer("Accept the Challenge"),
                new Answer("Reject the Challenge", "You rejected the Challenge. You Defeat!"))));

        questionList.put(++id, new Question(id, "You have accepted the Challenge. Going up to the bridge to see the captain?", List.of(
                new Answer("Accept to climb the bridge"),
                new Answer("Refuse to climb the bridge", "You didn't going to negotiate. You Defeat!"))));

        questionList.put(++id ,new Question(id, "You climb to the bridge. Who are you?", List.of(
                new Answer("Lying about yourself", "Your lies have been exposed. You Defeat!"),
                new Answer("Tell the truth"))));
    }

    public Question getById(int id) {
        return questionList.get(id);
    }
}
