package ru.ac.uniyar.Shebeta;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    static Integer currId = 1;
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

    public void setName(String nameNew) {
        this.name = nameNew;
    }

    public void setChildrenList(ArrayList<Node> childrensNew) {
        this.childrens = childrensNew;
    }


    public void add(Node newNode) {
        this.childrens.add(newNode);
    }

    public Node findWithName(String nodeName) {
        for (Node children : this.childrens){
            if (Objects.equals(children.getName(), nodeName)){
                return children;
            }
            else{
                children.findWithName(nodeName);
            }
        }
        Node tmp = new Node();
        return tmp;
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
