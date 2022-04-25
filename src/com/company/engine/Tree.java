package com.company.engine;

import com.company.TypeFileStrategy.StrategyFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Tree {
    private TreeNode root;
    private StrategyFile strategyFile;
    private boolean isSearch = false;
    private List<String> facts;

    public Tree() {
        this.root = null;
    }

    public StrategyFile getStrategyFile() {
        return strategyFile;
    }

    public void setStrategyFile(StrategyFile strategyFile) {
        this.strategyFile = strategyFile;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        return false;
    }

    public void insert(TreeNode treeNode, TreeNode data) {
        if (root == null) {
            root = data;
            return;
        }
        treeNode.setData(data.getData());
        treeNode.setYes(data.getYes());
        treeNode.setNo(data.getNo());
    }

    public int numberOfNodes() {
        return numberOfNodes(root);
    }

    private int numberOfNodes(TreeNode treeNode) {
       if (treeNode == null) {
           return 0;
       } else {
           return (1 + numberOfNodes(treeNode.getNo()) + numberOfNodes(treeNode.getYes()));
       }
    }

    public int numberOfAnimal() {
        return numberOfAnimal(root);
    }

    public int height() {
        return height(root);
    }

    private int height(TreeNode treeNode) {
        if (treeNode == null) {
            return -1;
        } else {
            return (1 + max(height(treeNode.getNo()), height(treeNode.getYes())));
        }
    }

    private int max(int x, int y) {
        if (x > y) {
            return x;
        } else {
            return y;
        }
    }

    private int min(int x, int y) {
        if (x < y) {
            return x;
        } else {
            return y;
        }
    }

    private int numberOfAnimal(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else  {
            if (treeNode.isLeaf()) {
                return 1;
            } else {
                return numberOfAnimal(treeNode.getNo()) + numberOfAnimal(treeNode.getYes());
            }
        }
    }

    public int minimumAnimalsDepth() {
        return minimumAnimalsDepth(root);
    }

    public int minimumAnimalsDepth(TreeNode treeNode) {
        if (treeNode == null) {
            return -1;
        } else {
            return (1 + min(minimumAnimalsDepth(treeNode.getNo()), minimumAnimalsDepth(treeNode.getYes())));
        }
    }

    public int averageAnimalsDepth() {
        return averageAnimalsDepth(root, -1);
    }

    private int averageAnimalsDepth(TreeNode treeNode, int x) {
        if (treeNode == null) {
            return 1;
        } else {
            return x + averageAnimalsDepth(treeNode.getYes(), height(treeNode.getYes())) +
                    averageAnimalsDepth(treeNode.getNo(), height(treeNode.getNo()));
        }
    }

    public void searchFactAboutAnimal(String animal) {
        facts = new ArrayList<>();
        searchFactAboutAnimal(root, animal);
        for (int i = facts.size() - 1; i >= 0; i--) {
            System.out.println(facts.get(i));
        }
    }

    private void searchFactAboutAnimal(TreeNode treeNode, String animal) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.getData().contains(animal)) {
            isSearch = true;
            return;
        }
        searchFactAboutAnimal(treeNode.getNo(), animal);
        if (isSearch) {
            facts.add( "- " + isNoFactAnimal(treeNode.getData()));
            return;
        }
        searchFactAboutAnimal(treeNode.getYes(), animal);
        if (isSearch) {
            facts.add("- " + treeNode.getData() + ".\n");
            return;
        }
    }

    private String isNoFactAnimal(String animalFact) {
        String[] fact = animalFact.toLowerCase(Locale.ROOT).split("it is|has|can ");
        if (animalFact.toLowerCase().contains("it is")) {
            return "It isn't" + fact[1] + ".\n";
        } else if (animalFact.toLowerCase().contains("it has")) {
            return "It doesn't have" + fact[1] + ".\n";
        } else if (animalFact.toLowerCase().contains("it can")) {
            return "It can't" + fact[1] + ".\n";
        }
        return "";
    }

}
