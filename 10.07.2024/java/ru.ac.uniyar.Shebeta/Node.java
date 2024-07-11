package ru.ac.uniyar.Shebeta;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    static Integer currId = 0;
    private Integer id = -1;
    private String name = "no_name";
    private ArrayList<Node> childrens = new ArrayList<Node>();

    public Node(){
        this.name = "no_name";
        this.id = -1;
    }

    public Node(String name){
        this.name = name;
        this.id = currId;
        currId++;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Node> getChildrenList() {
        return this.childrens;
    }

    public void setId(Integer idNew){
        this.id = idNew;
    }

    public void add(Node newNode) {
        this.childrens.add(newNode);
    }

    public Node findWithName(String nodeName) {
        Node result = new Node();
        for (Node children : this.childrens){
            if (Objects.equals(children.getName(), nodeName)){
                result = children;
            }
            else{
                result = children.findWithName(nodeName);
            }
        }
        return result;
    }

    public void deleteWithName(String nodeName) {
        for (Node children : this.childrens){
            if (Objects.equals(children.getName(), nodeName)){
                this.childrens.remove(children);
                break;
            }
        }
    }

    public void deleteWithId(Integer nodeId) {
        for (Node children : this.childrens){
            if (Objects.equals(children.getId(), nodeId)){
                this.childrens.remove(children);
                break;
            }
        }
    }

    public void delAll() {
        this.childrens.clear();
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}