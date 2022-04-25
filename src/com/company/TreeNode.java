package com.company;

import com.fasterxml.jackson.annotation.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeNode {
    private String data;
    private TreeNode yes;
    private TreeNode no;


    public TreeNode(String data, TreeNode yes, TreeNode no) {
        this.data = data;
        this.yes = yes;
        this.no = no;
    }

    public TreeNode(String val) {
        this.yes = null;
        this.no = null;
        this.data = val;
    }

    public TreeNode() {
        this("");
    }

    @JsonIgnore
    public boolean isLeaf() {
        return no == null && yes == null;
    }

    @JsonGetter("yes")
    public TreeNode getYes() {
        return yes;
    }

    @JsonSetter("yes")
    public void setYes(TreeNode yes) {
        this.yes = yes;
    }

    @JsonGetter("no")
    public TreeNode getNo() {
        return no;
    }

    @JsonSetter("no")
    public void setNo(TreeNode no) {
        this.no = no;
    }

    @JsonGetter("data")
    public String getData() {
        return data;
    }

    @JsonSetter("data")
    public void setData(String data) {
        this.data = data;
    }
}
