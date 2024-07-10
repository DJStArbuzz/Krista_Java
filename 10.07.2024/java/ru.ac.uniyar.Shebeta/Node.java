package ru.ac.uniyar.Shebeta;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private Integer id;
    private String name;
    private ArrayList<Node> childrens = new ArrayList();

    public Node(){
        this.name = "no_name";
        this.id = -1;
    }

    public Node(String name){
        this.name = name;
        this.id = 1;
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

    public void add(Node newNode) {
        childrens.add(newNode);
    }

    public Node findWithName(String nodeName) {
        for (Node children : childrens){
            if (Objects.equals(children.getName(), nodeName)){
                return children;
            }
        }
        Node tmp = new Node();
        return tmp;
    }

    public void deleteWithName(String nodeName) {
        Node tmp = new Node();
        for (Node children : childrens){
            if (Objects.equals(children.getName(), nodeName)){
                children = tmp;
            }
        }
    }

    public void deleteWithId(Integer nodeId) {
        Node tmp = new Node();
        for (Node children : childrens){
            if (Objects.equals(children.getId(), nodeId)){
                children = tmp;
            }
        }
    }

    public void delAll() {
        childrens = new ArrayList<>();
    }

    public void changeName(String newName) {
        this.name = newName;
    }
}
