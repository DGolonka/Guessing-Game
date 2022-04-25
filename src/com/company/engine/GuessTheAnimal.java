package com.company.engine;

import com.company.ResourceBoundle.SingletonResourceBundle;
import com.company.TypeFileStrategy.StrategyFile;

import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuessTheAnimal {
    private final ResourceBundle resourceBundle;
    private final Scanner sc;
    private final Tree tree = new Tree();
    private ArrayList<String> listOfAnimals = new ArrayList<>();

    public GuessTheAnimal(StrategyFile strategyFile) {
       this.resourceBundle = SingletonResourceBundle.getInstance();
       this.sc = new Scanner(System.in);
        if (strategyFile != null) {
            tree.setStrategyFile(strategyFile);
            tree.setRoot(tree.getStrategyFile().executeRead());
        } else {
            tree.setRoot(null);
        }
    }

    private String correctFormWord(String animal) {
        String[] animals = animal.toLowerCase(Locale.ROOT).split("\\s");
        String results = "";
        if (animals.length >= 2) {
            if ("a".equals(animals[0]) || "an".equals(animals[0])) {
                results = animal;
            } else {
                if (resourceBundle.getString("the").equals(animals[0])) {
                    results = (String) ((UnaryOperator) resourceBundle
                            .getObject("animal.name"))
                            .apply(animal.substring(animals[0].length() + 1));
                } else {
                    results = (String) ((UnaryOperator) resourceBundle
                            .getObject("animal.name")).apply(animal);
                }
            }
        } else {
            results = (String) ((UnaryOperator) resourceBundle
                    .getObject("animal.name")).apply(animal);
        }
        return results;
    }


    public String start() {
        String firstAnimal = "";
        if (tree.isEmpty()) {
            firstAnimal = firstStart();
        } else {
            System.out.println(resourceBundle.getString("welcome system"));
        }
        return firstAnimal;
    }

    private String toQuestion(String question) {
        String[] facts = question.toLowerCase().split(resourceBundle
                .getString("start facts"));
        if (question.toLowerCase().contains(resourceBundle.getString("it has?"))) {
            return String.valueOf(((UnaryOperator) resourceBundle
                    .getObject("facts.does")).apply(facts[1]));
        } else if (question.toLowerCase().contains(resourceBundle.getString("it is?"))) {
            return String.valueOf(((UnaryOperator) resourceBundle
                    .getObject("facts.is")).apply(facts[1]));
        } else {
            return String.valueOf(((UnaryOperator) resourceBundle
                    .getObject("facts.can")).apply(facts[1]));
        }
    }

    public void guessingGame(String firstAnimal) {
        if (tree.isEmpty()) {
            guessingGameFirstGame(firstAnimal);
        } else {
            guessingGame();
        }
    }

    private void guessingGame() {
        System.out.println(resourceBundle.getString("start game"));
        while (!sc.nextLine().equals(""));
        String animalResult = "";
        TreeNode treeNode = tree.getRoot();
        while (treeNode != null) {
            if (treeNode.isLeaf()) {
                animalResult = treeNode.getData();
                UnaryOperator animalName = (UnaryOperator) resourceBundle
                        .getObject("animal.question");
                System.out.println(animalName.apply(animalResult));
                if(TypeAnswer.isItATrueAnswer(resourceBundle)) {
                    System.out.println(resourceBundle.getString("win"));
                    return;
                } else {
                    break;
                }
            } else {
                System.out.println(toQuestion(treeNode.getData()));
            }
            if (TypeAnswer.isItATrueAnswer(resourceBundle)) {
                treeNode = treeNode.getYes();
            } else {
                treeNode = treeNode.getNo();
            }

        }
        String animal = "";
        System.out.println(resourceBundle.getString("give up"));
        animal = correctFormWord(sc.nextLine());
        System.out.printf("%s %s %s %s.%n",
                resourceBundle.getString("distinguish"),
                resourceBundle.getString("from"),
                animalResult, animal);
        getFacts(treeNode, animalResult, animal);
    }

    private String firstStart() {
        final Scanner sc = new Scanner(System.in);
        System.out.println(resourceBundle.getString("like animal"));
        String firstAnimal = sc.nextLine();
        System.out.println(resourceBundle.getString("welcome system"));
        return firstAnimal;
    }

    private void guessingGameFirstGame(String firstAnimal) {
        System.out.println(resourceBundle.getString("first start"));
        sc.nextLine();
        firstAnimal = correctFormWord(firstAnimal).toLowerCase(Locale.ROOT);
        System.out.println(((UnaryOperator) resourceBundle
                .getObject("animal.question")).apply(firstAnimal));
        TypeAnswer.isItATrueAnswer(resourceBundle);
        System.out.println(resourceBundle.getString("give up"));
        String resultAnimal = sc.nextLine();
        resultAnimal = correctFormWord(resultAnimal).toLowerCase(Locale.ROOT);
        System.out.printf("%s %s %s %s.%n",
                resourceBundle.getString("distinguish"),
                firstAnimal,
                resourceBundle.getString("from"),
                resultAnimal);
        getFacts(null, firstAnimal, resultAnimal);
    }

    public void listOfAllAnimals() {
        if (!tree.isEmpty()) {
            listOfAnimals = new ArrayList<>();
            System.out.println(resourceBundle.getString("animal know"));
            listOfAllAnimals(tree.getRoot());
            listOfAnimals.stream().sorted()
                    .forEach((x -> System.out.println("- " + x)));
        } else {
            System.out.println("Empty");
        }
    }

    private void listOfAllAnimals(TreeNode treeNode) {
        if (treeNode != null) {

            if (treeNode.isLeaf()) {
                listOfAnimals.add(treeNode.getData());
            }
            listOfAllAnimals(treeNode.getNo());
            listOfAllAnimals(treeNode.getYes());
        }
    }

    public void calculateStatistic() {
        int totalNumberOfNodes = tree.numberOfNodes();
        int totalNumberOfAnimal = tree.numberOfAnimal();
        int numberOfStatements = totalNumberOfNodes - totalNumberOfAnimal;
        int minimumAnimalDepth = tree.minimumAnimalsDepth();
        int height = tree.height();
        double average = (double) totalNumberOfNodes / (double) totalNumberOfAnimal;
        System.out.printf(resourceBundle.getString("knowledge tree"),
                tree.getRoot().getData(), totalNumberOfNodes, totalNumberOfAnimal,
                numberOfStatements, height, minimumAnimalDepth,
                average);
    }

    public void printTree() {
        System.out.println(tree.getRoot());
    }

    public void factAboutAnimal(){
        System.out.println(resourceBundle.getString("enter animal"));
        String animalSearch = sc.nextLine();
        System.out.println(resourceBundle.getString("fact search") + animalSearch);
        tree.searchFactAboutAnimal(animalSearch);
    }


    //add to resources file
    private void getFacts(TreeNode treeNode, String firstAnimal, String resultAnimal) {
        TreeNode data = new TreeNode("");
        TreeNode no = new TreeNode("");
        TreeNode yes = new TreeNode("");
        Pattern pattern = Pattern.compile("^" + resourceBundle.getString("start facts"),
                Pattern.CASE_INSENSITIVE);
        String factOfAnimal = "";
        while (true) {
            factOfAnimal = sc.nextLine().trim();
            factOfAnimal = factOfAnimal.toLowerCase(Locale.ROOT);
            if (pattern.matcher(factOfAnimal).find() || factOfAnimal.toLowerCase().contains("ĝi")) {
                break;
            } else {
                System.out.println("error");
            }
        }
        String shortFactOfAnimal = factOfAnimal.split( resourceBundle.getString("start facts"))[1].trim();

        System.out.printf(resourceBundle.getString("correct for"), resultAnimal);
        String isCorrect = sc.nextLine().toLowerCase(Locale.ROOT);
        while (!resourceBundle.getString("no").equals(isCorrect)
                && !resourceBundle.getString("yes").equals(isCorrect)) {
            System.out.println(resourceBundle.getString("contains yes no"));
            isCorrect = sc.nextLine().toLowerCase(Locale.ROOT);
        }
        String shortFirstAnimal = firstAnimal;
        String shortResultAnimal = resultAnimal;
        if (firstAnimal.split(" ").length > 1) {
            shortFirstAnimal = firstAnimal.split(" ")[1];
            shortResultAnimal = resultAnimal.split(" ")[1];
        }
        Pattern patternFact = Pattern.compile("(it can|is|has) .*");
        Matcher matcherFact = patternFact.matcher(factOfAnimal.toLowerCase(Locale.ROOT));
        matcherFact.find();
        Map<String, String> answer = (Map<String, String>) resourceBundle
                .getObject(matcherFact.group(1));
        if (resourceBundle.getString("no").equals(isCorrect)) {
            System.out.printf(
                    resourceBundle.getString("have learned") +
                            answer.get("yes") +
                            answer.get("no"),
                    shortFirstAnimal, shortFactOfAnimal,
                    shortResultAnimal, shortFactOfAnimal);
            yes.setData(firstAnimal);
            no.setData(resultAnimal);
        } else {
            System.out.printf(resourceBundle.getString("have learned") +
                            resourceBundle.getString("the can't") +
                            resourceBundle.getString("the can"),
                    shortFirstAnimal, shortFactOfAnimal,
                    shortResultAnimal, shortFactOfAnimal);
            yes.setData(resultAnimal);
            no.setData(firstAnimal);
        }
        System.out.println(resourceBundle.getString("asking question"));
        String question = String.format(answer.get("question"), shortFactOfAnimal);
        System.out.println(question);

        data.setData(factOfAnimal);
        data.setYes(yes);
        data.setNo(no);
        tree.insert(treeNode, data);
        if (tree.getStrategyFile() != null) {
            tree.getStrategyFile().executeWrite(tree.getRoot());
        }
    }
}
