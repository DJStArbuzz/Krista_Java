package ru.ac.uniyar.Shebeta;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Node {
    static Integer currId = 0;
    private Integer id;
    private String name;
    private ArrayList<Node> children = new ArrayList();
    private Integer Level = 0;
    private Integer prevNode = -1;

    public Node() {
        this.name = "bad_name";
        this.id = -1;
    }

    public Integer getPrevNode(){
        return this.prevNode;
    }

    public void setPrevId(Integer idNew){
        this.prevNode = idNew;
    }

    public void setLevel(Integer level) {
        Level = level;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public Node(String name) {
        this.name = name;
        this.id = currId;
        currId++;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getLevel() {
        return this.Level;
    }

    public void changeLevel(Integer newLevel) {
        this.Level = newLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(Node newNode) {
        newNode.changeLevel(this.Level + 1);
        this.children.add(newNode);

    }

    public Node find_by_name(String node_name) {
        if(this.getName().equals(node_name))
        {
            return this;
        }
        for (Node child : this.children) {
            Node tmp = child.find_by_name(node_name);
            if(tmp != null)
            {
                return tmp;
            }
        }
        return null;
    }

    public Node find_by_id(int node_id) {
        if(this.getId().equals(node_id))
        {
            return this;
        }
        for (Node child : this.children) {
            Node tmp = child.find_by_id(node_id);
            if(tmp != null)
            {
                return tmp;
            }
        }
        return null;
    }

    public void changename(String new_name) {
        this.name = new_name;
    }

    public void delete_all() {
        this.children = new ArrayList<Node>();
    }

    public void delete_by_id(Integer Id) {
        for (Node child : children) {
            if (child.getId().equals(Id)) {
                this.children.remove(child);
                break;
            }
            else
            {
                child.delete_by_id(Id);
            }
        }
    }

    public void delete_by_name(String name) {
        for (Node child : children) {
            if (child.getName().equals(name)) {
                this.children.remove(child);
                break;
            }
            else
            {
                child.delete_by_name(name);
            }
        }
    }

    public void print_tree() {
        String space = "  ";
        String repeated = space.repeat(this.Level);
        System.out.println((repeated + this.name));
        for (Node child : this.children) {
            child.print_tree();
        }
    }

    public String print_tree_to_HTML(Integer level) {
        String res = "<ul>";
        res+="<li>";
        res+=this.name + " <a href=\"edit/" + this.getId() + "\">Редактировать</a>" + " <a href=\"delete/" + this.getId() + "\">Удалить</a>";
        for (Node child : this.children){
            res += child.print_tree_to_HTML(level);
        }

        res += "</li>";
        res+="</ul>";
        return res;
    }
    public void print_to_HTMLfile(Node head)
    {
        try {
            FileWriter file_editer = new FileWriter("test.html");
            String res = print_tree_to_HTML(head.getLevel());
            file_editer.write(res);
            file_editer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        this.children = object.children;
        this.Level = object.Level;
    }

    public ArrayList<Node> getChildrenList() {return this.children; }
}
