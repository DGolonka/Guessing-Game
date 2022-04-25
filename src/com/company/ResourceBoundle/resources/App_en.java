package com.company.ResourceBoundle.resources;

import java.util.ListResourceBundle;
import java.util.Map;
import java.util.function.UnaryOperator;

public class App_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"fact search", "Facts about the "},
                {"hello", "Hello!"},
                {"good morning" , "Good morning!"},
                {"good afternoon", "Good afternoon!"},
                {"good evening", "Good evening!"},
                {"from", "from"},
                {"menu", "What do you want to do:\n" +
                        "\n" +
                "1. Play the guessing game\n" +
                "2. List of all animals\n" +
                "3. Search for an animal\n" +
                "4. Calculate statistics\n" +
                "0. Exit\n"},
                {"start facts", "it is|has|can"},
                {"knowledge tree", "The Knowledge Tree stats%n%n" +
                        "- root node                    %s\n" +
                        "- total number of nodes        %d\n" +
                        "- total number of animals      %d\n" +
                        "- total number of statements   %d\n" +
                        "- height of the tree           %d\n" +
                        "- minimum animal's depth       %d\n" +
                        "- average animal's depth       %.1f\n"},
                {"distinguish", "Enter a statement that can help me distinguishes"},
                {"win", "I'm win"},
                {"give up", "I give up. What animal do you have in mind?"},
                {"it has?", "it has"},
                {"it is?", "it is"},
                {"it can?", "it can"},
                {"the", "the"},
                {"no", "no"},
                {"yes", "yes"},
                {"the can", "- The %s can %s.%n"},
                {"the can't", "- The %s can't %s.%n"},
                {"Can?", "Can it %s?%n"},
                {"it can", Map.of("question", "Can it %s?%n",
                        "yes", "- The %s can %s.%n",
                        "no", "- The %s can't %s.%n")},
                {"it is", Map.of("question", "Is it %s?%n",
                        "yes", "- The %s is %s.%n",
                        "no", "- The %s isn't %s.%n")},
                {"it has", Map.of("question", "Does it have %s?%n",
                        "yes", "- The %s has %s.%n",
                        "no",  "- The %s doesn't have %s.%n")},
                {"the has",  "- The %s has %s.%n"},
                {"the doesn't",  "- The %s doesn't have %s.%n"},
                {"Does?",  "Does it have %s?%n"},
                {"the is", "- The %s is %s.%n"},
                {"the isn't", "- The %s isn't %s.%n"},
                {"Is?", "Is it %s?%n"},
                {"correct for", "Is the statement correct for %s?%n"},
                {"asking question", "I can distinguish these animals by asking the question:"},
                {"have learned", "I have learned the following facts about animals:%n"},
                {"contains yes no", "Expected contains: \"yes or no\""},
                {"animal know","Here are the animals I know:"},
                {"first start", "You think of an animal, and I guess it.\n" +
                        "Press enter when you're ready."},
                {"start game", "I know a lot about animals.\n" +
                        "Let's play a game!\n" +
                        "You think of an animal, and I guess it.\n" +
                        "Press enter when you're ready."},
                {"thank you", "Thank you and goodbye!"},
                {"like animal", "I want to learn about animals.\n" +
                        "Which animal do you like most?"},
                {"welcome system", "Welcome to the animal expert system!"},
                {"enter animal", "Enter the animal:"},
                {"play again", "I've learned so much about animals!\n" +
                        "Would you like to play again?"},
                {"bye", new String[]{
                "Bye!",
                "Bye, bye!",
                "See you later!",
                "See you soon!",
                "Have a nice one!"
                }},
                {"facts.does", (UnaryOperator<String>) facts ->
                    "Does it have" + facts + "?"
                },
                {"facts.is", (UnaryOperator<String>) facts ->
                     "Is it" + facts + "?"
                },
                {"facts.can", (UnaryOperator<String>) facts ->
                     "Can it" + facts + "?"
                },
                {"animal.name", (UnaryOperator<String>) name -> {
            if (name.matches("[aeiou].*")) {
                return "an " + name;
            } else {
                return "a " + name;
            }
                }},
                {"animal.question", (UnaryOperator<String>) animal -> "Is it " + animal + "?"},
                {"answer" , new String[]{"I'm not sure I caught you: was it yes or no?",
                        "Funny, I still don't understand, is it yes or no?",
                        "Oh, it's too complicated for me: just tell me yes or no.",
                        "Could you please simply say yes or no?",
                        "Oh, no, don't try to confuse me: say yes or no."}},
                {"positive", new String[]{"y", "yes", "yeah", "yep", "sure", "right"
                        , "affirmative", "correct", "indeed", "you bet", "exactly", "you said it"}},
                {"negative", new String[]{"n", "no", "no way", "nah", "nope"
                        , "negative", "I don't think so", "yeah no"}}
        };
    }
}
