package websockets.service;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Eliza.java - a simplified version of Joseph Weizenbaum's Eliza
 * <p>
 * Adapted from https://code.google.com/p/simple-semantic-desktop/source/browse/trunk/Progs2/Eliza/eliza.java
 * created by Akshat Singhal
 */
public class Eliza {

  private HashMap<String, String[]> responses;
  private String[] keywords;
  private Random random;

  public Eliza() {
    init();
  }

  private void init() {
    responses = new HashMap<>();
    String[] temp0 = {"What does that suggest to you?", "I see.", "I'm not sure I understand you fully.",
            "Can you elaborate?", "That is quite interesting."};
    // That's what it says when it do not want to say
    responses.put("NOTFOUND", temp0);

    String[] temp1 = {"Can you think of a specific example?"};
    responses.put("always", temp1);

    String[] temp2 = {"Is that the real reason?"};
    responses.put("because", temp2);

    String[] temp3 = {"Please don't apologize."};
    responses.put("sorry", temp3);

    String[] temp4 = {"You don't seem very certain."};
    responses.put("maybe", temp4);

    String[] temp5 = {"Do you really think so?"};
    responses.put("i think", temp5);

    // It thinks i am talking about it
    String[] temp6 = {"We were discussing you, not me."};
    responses.put("you", temp6);

    String[] temp7 = {"Why do you think so?", "You seem quite positive."};
    responses.put("yes", temp7);

    String[] temp8 = {"Why not?", "Are you sure?"};
    responses.put("no", temp8);

    String[] temp9 = {"I am sorry to hear you are *.", "How long have you been *?",
            "Do you believe it is normal to be *?", "Do you enjoy being *?"};
    responses.put("i am", temp9);
    responses.put("i'm", temp9);
    //That what it answers when I tell it how i feel
    String[] temp10 = {"Tell me more about such feelings.", "Do you often feel *?", "Do you enjoy feeling *?",
            "Why do you feel that way?"};
    responses.put("i feel", temp10);

    String[] temp11 = {"Tell me more about your family.", "How do you get along with your family?",
            "Is your family important to you?"};
    responses.put("family", temp11);
    responses.put("mother", temp11);
    responses.put("father", temp11);
    responses.put("mom", temp11);
    responses.put("dad", temp11);
    responses.put("sister", temp11);
    responses.put("brother", temp11);
    responses.put("husband", temp11);
    responses.put("wife", temp11);

    String[] temp12 = {"What does that dream suggest to you?", "Do you dream often?",
            "What persons appear in your dreams?", "Are you disturbed by your dreams?"};
    responses.put("dream", temp12);
    responses.put("nightmare", temp12);

    keywords = new String[]{"always", "because", "sorry", "maybe", "i think", "you", "yes", "no", "i am", "i'm",
            "i feel", "family", "mother", "mom", "dad", "father", "sister", "brother", "husband", "wife", "dream",
            "nightmare"};
    random = new Random();
  }

  public String respond(Scanner s) {
    /* initialize variables */
    String response = "";
    String[] responseArray;
    boolean found = false;
    /* - end init - */

    /* Loop through keywords */
    for (String currentKeyword : keywords)
      if ((s.findInLine(currentKeyword) != null) && (responses.get(currentKeyword) != null)) {
        /*
         * If a keyword is found in the current input, get a response
         * from HashMap and return it
         */
        found = true;
        responseArray = responses.get(currentKeyword);
        response = responseArray[random.nextInt(responseArray.length)];
        /*
         * If response has a *, replace it with the remainder of input
         * string _with the last character removed if it is a
         * punctuation character_
         */
        if (response.indexOf('*') != -1) {
          String remainingInput;
          if (s.hasNext()) {
            remainingInput = s.nextLine().trim();
            response = response.substring(0, response.indexOf('*')) +
              remainingInput.substring(0, remainingInput.length() - 1) +
              remainingInput.substring(remainingInput.length() - 1).replaceAll("[^A-Za-z]", "") +
              response.substring(response.indexOf('*') + 1);
            response = response.trim();
          } else {
            response = response.replaceAll("[*]", "");
          }
        }
      }

    /*
     * respond with a default message if no keywords were found in the input
     * string
     */
    if (!found) {
      responseArray = responses.get("NOTFOUND");
      response = responseArray[random.nextInt(responseArray.length)];
    }
    return response;
  }
}
