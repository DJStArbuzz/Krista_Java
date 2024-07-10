package org.example;

import org.junit.jupiter.api.Test;
import ru.ac.uniyar.Shebeta.Node;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
    /**
     * Проверка создания дерева
     */
    @Test
    void createNewTree(){
        Node node = new Node("node1");
        assertNotNull(node, "Узел не создан");
        // getName - метод показа наименования узла
        assertEquals("node1", node.getName(),
                "Узел создан не корректно");
    }

    /** Проверка добавления потомка */
    @Test
    void addNode(){
        Node nodeFirst = new Node("node1");
        Node nodeSecond = new Node("node2");
        // add - добавление дочернего узла
        nodeFirst.add(nodeSecond);
        // getChildrenList - метод получения списка дочерних узлов
        assertEquals(1, nodeFirst.getChildrenList().size(), "Узел не добавил потомка");
    }

    // Проверка поиска дочернего узла по имени
    @Test
    void findNodeByName(){
        Node mainNode = new Node("mainNode");

        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");
        Node node4 = new Node("node4");

        mainNode.add(node1);
        mainNode.add(node2);
        mainNode.add(node3);

        // findNode - проверка на наличие дочернего узла
        assertEquals(node1, mainNode.findWithName(node1.getName()), "Дочерний узел не был найден");
    }

    // Проверка поиска дочернего узла по имени
    @Test
    void notFindNodeByName(){
        Node mainNode = new Node("mainNode");

        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");
        Node node4 = new Node("node4");

        mainNode.add(node1);
        mainNode.add(node2);
        mainNode.add(node3);

        Node tmp = mainNode.findWithName(node4.getName());
        // findNode - проверка на наличие дочернего узла
        assertEquals("no_name", tmp.getName(), "'НЕ' дочерний узел является дочерним");
    }

    // Проверка удаления дочернего узла по имени или идентификатору
    @Test
    void deleteNodeByName()
    {
        Node mainNode = new Node("mainNode");

        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");

        mainNode.add(node1);
        mainNode.add(node2);
        mainNode.add(node3);

        // delete - удаление дочернего узла
        mainNode.deleteWithName(node1.getName());
        assertEquals(2, mainNode.getChildrenList().size(), "Дочерний узел не был удален");
        assertEquals(node2, mainNode.getChildrenList().get(0), "Удален не тот узел");
        assertEquals(node3, mainNode.getChildrenList().get(1));
    }

    @Test
    void deleteNodeById()
    {
        Node mainNode = new Node("mainNode");

        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");

        mainNode.add(node1);
        mainNode.add(node2);
        mainNode.add(node3);

        // delete - удаление дочернего узла
        Integer prevSize = mainNode.getChildrenList().size();
        mainNode.deleteWithId(node1.getId());
        assertEquals(prevSize - 1, mainNode.getChildrenList().size(), "Дочерний узел не был удален");
    }

    // Проверка удаления всех дочерних узлов
    @Test
    void deleteAll(){
        Node mainNode = new Node("node0");

        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node node3 = new Node("node3");

        mainNode.add(node1);
        mainNode.add(node2);
        mainNode.add(node3);

        mainNode.delAll();
        assertEquals(0, mainNode.getChildrenList().size(), "Все дочерние узлы не были удалены");
    }

    // Проверка изменения значения(имени) узла
    @Test
    void changeNodeName(){
        Node node = new Node("node1");
        // changName - метод изменения наименования узла
        node.changeName("node2");
        assertEquals("node2", node.getName(), "Наименование не поменялось");
        assertNotEquals("node1", node.getName(), "Наименование не поменялось");

    }
}
