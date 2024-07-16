package ru.ac.uniyar.Shebeta;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Node {
    static Integer currId = 0;
    private Integer id = -1;
    private String name = "no_name";
    private ArrayList<Node> childrens = new ArrayList<Node>();
    private Integer level = 0;

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

    public Integer getLevel() {
        return this.level;
    }

    public ArrayList<Node> getChildrenList() {
        return this.childrens;
    }

    public void setId(Integer idNew){
        this.id = idNew;
    }

    public void changeLevel(Integer newLevel) {
        this.level = newLevel;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void add(Node newNode) {
        newNode.changeLevel(this.level + 1);
        this.childrens.add(newNode);
        for (Node child : newNode.getChildrenList()){
            child.changeLevel(newNode.level + 1);
        }
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

    public void print_tree() {
        String space = "  ";
        String repeated = space.repeat(this.level);
        System.out.println((repeated + this.name));
        for (Node child : this.childrens) {
            child.print_tree();
        }
    }

    public String printAllInfoHTML(Integer level){
        String res = "";
        if (level == this.level){
            res = "<!DOCTYPE HTML>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Маркированный список</title>\n" +
                    "</head>\n" +
                    "<body>\n";
        }

        res += ("<li>" + this.name);

        if (this.childrens.size() != 0){
            res += "<ul>";
        }
        for (Node child : this.childrens){
            res += child.printAllInfoHTML(level);
        }

        if (this.childrens.size() != 0){
            res += "</ul>";
        }

        res += "</li>";

        if (level == this.level){
            res += "\n</body>\n" +
                    "</html>";
        }
        return res;
    }

    public void printAllInfoJSON() throws IOException {
        String fileName = "test.json";
        ObjectMapper mapper = new ObjectMapper();
        Node curr = this;
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), curr);
    }

    public void readJson() throws IOException{
        Node object = new ObjectMapper().readValue(new File("test.json"), Node.class);
        this.name = object.name;
        this.childrens = object.childrens;

    }
}