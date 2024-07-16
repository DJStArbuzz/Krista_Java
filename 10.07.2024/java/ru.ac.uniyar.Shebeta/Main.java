package ru.ac.uniyar.Shebeta;

public class Main {
    public static void problemWithCreation(){
        System.out.println("Создание дерева.");

        Node testNodeNull = new Node();
        System.out.println("Имя - " + testNodeNull.getName() +
                ", id - " + testNodeNull.getId() +
                ", кол-во дочерних узлов - " + testNodeNull.getChildrenList().size());

        Node testNodeMain = new Node("main");
        Node testNodeTmp1 = new Node("daughter №1");
        Node testNodeTmp2 = new Node("daughter №2");
        testNodeMain.add(testNodeTmp1);
        testNodeMain.add(testNodeTmp2);
        System.out.println("\nИмя - " + testNodeMain.getName() +
                ", id - " + testNodeMain.getId() +
                ", кол-во дочерних узлов - " + testNodeMain.getChildrenList().size());
        System.out.print("Дочерние узлы: ");
        for (Node children : testNodeMain.getChildrenList()){
            System.out.print(children.getName() + " (id - " +
                    children.getId() + "), ");
        }
        System.out.println("\n\n-------------------");
    }

    public static void problemWithAddition(){
        System.out.println("Добавление узла (дочернего) в дерево.\n");

        Node testNodeMain = new Node("main");
        System.out.println("У корня 'main' " +
                testNodeMain.getChildrenList().size() + " дочерних узлов. Добавим первые 2 узла.");

        Node testNodeTmp1 = new Node("daughter №1");
        Node testNodeTmp2 = new Node("daughter №2");
        testNodeMain.add(testNodeTmp1);
        testNodeMain.add(testNodeTmp2);

        System.out.println("Имя - " + testNodeMain.getName() +
                " , id - " + testNodeMain.getId() +
                " , кол-во дочерних узлов - " + testNodeMain.getChildrenList().size());
        System.out.print("Дочерние узлы: ");
        for (Node children : testNodeMain.getChildrenList()){
            System.out.print(children.getName() + " (id: " +
                    children.getId() + "), ");
        }
        System.out.println("\n\n-------------------");
    }

    public static void problemWithFind(){
        System.out.println("Поиск дочернего узла по имени.\n");

        Node testNodeMain = new Node("main");

        Node testNodeTmp1 = new Node("daughter №1");
        Node testNodeTmp2 = new Node("daughter №2");
        Node testNodeTmp3 = new Node("не доч. узел");
        testNodeTmp1.add(testNodeTmp2);
        testNodeMain.add(testNodeTmp1);

        System.out.print("Если узел есть в дереве: ");
        Node goodTest = testNodeMain.findWithName(testNodeTmp2.getName());
        System.out.println("имя - " + goodTest.getName() +
                ", id - " + goodTest.getId());
        System.out.println("Если узел есть, то передается вся информация о нем.");
        System.out.println("Для теста был взят дочерний узел дочернего узла по корню \n" +
                "(поиск не только на первом уровне).\n");

        System.out.print("Если узла в дереве нет: ");
        Node badTest = testNodeMain.findWithName(testNodeTmp3.getName());
        System.out.println("имя - " + badTest.getName() +
                ", id - " + badTest.getId());
        System.out.println("Если узел не находится, то мы создаем 'сломанный' узел\n" +
                "(без имени, отрицательный id).");
        System.out.println("\n-------------------");
    }

    public static void problemWithDelete(){
        System.out.println("Удаление дочернего узла по имени или идентификатору\n");

        Node testNodeMain = new Node("main");
        Node testNodeTmp1 = new Node("daughter №1");
        Node testNodeTmp2 = new Node("daughter №2");
        Node testNodeTmp3 = new Node("daughter №3");
        Node testNodeTmp4 = new Node("not_daughter");

        testNodeMain.add(testNodeTmp1);
        testNodeMain.add(testNodeTmp2);
        testNodeMain.add(testNodeTmp3);

        System.out.println("Имя - " + testNodeMain.getName() +
                " , id - " + testNodeMain.getId() +
                " , кол-во дочерних узлов - " + testNodeMain.getChildrenList().size());
        System.out.print("Дочерние узлы: ");
        for (Node children : testNodeMain.getChildrenList()){
            System.out.print(children.getName() + " (id: " +
                    children.getId() + ") ");
        }

        System.out.println("\n\nУдалим №1 по имени, а №2 по id.");
        testNodeMain.deleteWithName(testNodeTmp1.getName());
        testNodeMain.deleteWithId(testNodeTmp2.getId());
        System.out.println("Итого дочерних узлов у корня осталось - " + testNodeMain.getChildrenList().size());
        System.out.print("Дочерние корни: ");
        for (Node children : testNodeMain.getChildrenList()){
            System.out.print(children.getName() + ", ");
        }

        System.out.println("\n\nПопробуем удалить не дочерний узел " + testNodeTmp4.getName());
        testNodeMain.deleteWithName(testNodeTmp4.getName());
        System.out.println("Итого дочерних узлов у корня осталось - " + testNodeMain.getChildrenList().size());
        System.out.print("Дочерние корни: ");
        for (Node children : testNodeMain.getChildrenList()){
            System.out.print(children.getName() + " ");
        }

        System.out.println("\n\n-------------------");
    }

    public static void problemWithAllDelete() {
        System.out.println("Удаление всех дочерних узлов.\n");

        Node testNodeMain = new Node("main");
        Node testNodeTmp1 = new Node("daughter №1");
        Node testNodeTmp2 = new Node("daughter №2");
        Node testNodeTmp3 = new Node("daughter №3");

        testNodeMain.add(testNodeTmp1);
        testNodeMain.add(testNodeTmp2);
        testNodeMain.add(testNodeTmp3);

        System.out.println("Имя - " + testNodeMain.getName() +
                " , id - " + testNodeMain.getId() +
                " , кол-во дочерних узлов - " + testNodeMain.getChildrenList().size());
        System.out.print("Дочерние узлы: ");
        for (Node children : testNodeMain.getChildrenList()) {
            System.out.print(children.getName() + " (id: " +
                    children.getId() + ") ");
        }

        System.out.println("\n\nОчистим список дочерних узлов корня.");
        testNodeMain.delAll();
        System.out.println("Новый размер: " + testNodeMain.getChildrenList().size());

        System.out.println("\n-------------------");
    }

    public static void problemWithChangeName_Id(){
        System.out.println("Изменение значения(имени) узла.");

        Node testNodeMain = new Node("main");
        Node testNodeTmp1 = new Node("daughter №1");
        Node testNodeTmp2 = new Node("daughter №2");
        testNodeMain.add(testNodeTmp1);
        testNodeMain.add(testNodeTmp2);
        System.out.println("\nИмя - " + testNodeMain.getName() +
                ", id - " + testNodeMain.getId() +
                ", кол-во дочерних узлов - " + testNodeMain.getChildrenList().size());
        System.out.print("Дочерние узлы: ");
        for (Node children : testNodeMain.getChildrenList()){
            System.out.print(children.getName() + " (id - " +
                    children.getId() + "), ");
        }

        System.out.println("\n\nПоменяем имя и id главному узлу.");
        testNodeMain.changeName("AbsoluteMain");
        testNodeMain.setId(1000);
        System.out.println("Имя - " + testNodeMain.getName() +
                ", id - " + testNodeMain.getId() +
                ", кол-во дочерних узлов - " + testNodeMain.getChildrenList().size());
        System.out.println("\n-------------------");
    }

    public static void main(String[] args) {
        //System.out.println("Проверка всех основных операций\n");

        //problemWithCreation();
        //problemWithAddition();
        //problemWithFind();
        //problemWithDelete();
        //problemWithAllDelete();
        //problemWithChangeName_Id();
        //System.out.println("\n-------------------");

        String res = "<!DOCTYPE HTML>\n" +
                "<html>\n" +
                "\n" +
                " <head>\n" +
                "  <title>Маркированный список</title>\n" +
                " </head>\n" +
                "\n" +
                " <body>\n";
        Node main = new Node("main");
        Node tmp1 = new Node("son1");
        Node tmp2 = new Node("son2");
        Node tmp3 = new Node("grandson1");
        Node tmp4 = new Node("grandson2");
        Node tmp5 = new Node("grand-grandson1");
        Node tmp6 = new Node("grand-grandson2");

        main.add(tmp1);
        main.add(tmp2);

        tmp1.add(tmp3);
        tmp1.add(tmp4);

        tmp4.add(tmp5);
        tmp3.add(tmp6);

        String tmp = main.printAllInfo();
        res += tmp;

        res += " </body>\n" +
                "\n" +
                "</html>";
        System.out.println(res);

    }
}