package ru.ac.uniyar.Shebeta;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Контроллер отвечающий за представление дерева.
 */
@Path("/")
public class TreePresentationController {
    private final List<Node> list;

    /**
     * Запоминает деревья, с которым будет работать.
     * @param list список, с которым будет работать контроллер.
     */
    public TreePresentationController(List<Node> list) {
        this.list = list;
    }

    /**
     * Пример вывода простого текста.
     */
    @GET
    @Path("example")
    @Produces("text/plain")
    public String getSimpleText() {
        return "hello world";
    }

    /**
     * Выводит HTML-страницу со списком, ссылками на страницы редактирования и копкой добавления записи.
     * @return HTML-страница со списком.
     */
    @GET
    @Path("/")
    @Produces("text/html")
    public String getList() {
        String result =
                "<html>" +
                "  <head>" +
                "    <title>Вывод списка</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Список</h1>" +
                "    <ul>";
        for (int i = 0; i < list.size(); i++) {
            Node listItem = list.get(i);
            if (listItem.getPrevNode() == -1){
            result += listItem.printAllInfoHTML(listItem.getLevel());}
        }
        result += "    </ul>" +
                "      <br/>" +
                "      <form method=\"post\" action=\"new_elem\">" +
                "        <input type=\"submit\" value=\"Add new Node\"/>" +
                "      </form>" +
                "  </body>" +
                "</html>";
        return result;
    }

    /**
     * Пример обработки POST запроса.
     * Добавляет одну случайную запись в список и перенаправляет пользователя на основную страницу со списком.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("new_elem")
    @Produces("text/html")
    public Response addRandomItem() {
        Node tmp = new Node("new_elem");
        tmp.setId(list.size());
        list.add(tmp);
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Выводит страничку для редактирования узла.
     * @param itemId индекс элемента списка.
     * @return страничка для редактирования одного элемента.
     */
    @GET
    @Path("/edit/{id}")
    @Produces("text/html")
    public String getEditPage(@PathParam("id") int itemId) {
        Node listItem = list.get(itemId);
        String result =
                "<html>" +
                        "  <head>" +
                        "    <title>Редактирование узла дерева</title>" +
                        "  </head>" +
                        "  <body>" +
                        "    <h1>Редактирование узла дерева</h1>" +
                        "    <form method=\"post\" action=\"/edit/" + itemId + "\">" +
                        "      <p>Значение</p>" +
                        "      <input type=\"text\" name=\"value\" value=\"" + listItem.getName() +"\"/>" +
                        "      <input type=\"submit\"/>";
        result +=
                "            </form>" +
                "  </body>" +
                "</html>";
        return result;
    }

    /**
     * Редактирует узел дерева на основе полученных данных.
     * @param itemId индекс элемента списка.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/edit/{id}")
    @Produces("text/html")
    public Response editItem(@PathParam("id") int itemId, @FormParam("value") Node itemValue) {
        itemValue.setId(itemId);
        list.set(itemId, itemValue);

        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    @GET
    @Path("/create/{id}")
    @Produces("text/html")
    public String getCreatePage(@PathParam("id") int itemId) {
        Node listItem = list.get(itemId);
        String result =
                "<html>" +
                        "  <head>" +
                        "    <title>Создание дочернего узла</title>" +
                        "  </head>" +
                        "  <body>" +
                        "    <h1>Создание дочернего узла</h1>" +
                        "    <form method=\"post\" action=\"/create/" + itemId + "\">" +
                        "      <p>Значение</p>" +
                        "      <input type=\"text\" name=\"value\" value=\"" + "" +"\"/>" +
                        "      <input type=\"submit\"/>";
        result +=
                "            </form>" +
                        "  </body>" +
                        "</html>";
        return result;
    }

    @POST
    @Path("/create/{id}")
    @Produces("text/html")
    public Response createItem(@PathParam("id") int itemId, @FormParam("value") Node itemValue) {
        itemValue.setId(list.size());
        itemValue.setPrevId(itemId);
        list.add(itemValue);

        Node tmp = list.get(itemId);
        tmp.add(itemValue);
        list.set(itemId, tmp);

        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Выводит страничку для удаления одного узла.
     * @param itemId индекс элемента списка.
     * @return страничка для редактирования одного элемента.
     */
    @GET
    @Path("/delete/{id}")
    @Produces("text/html")
    public String getDeletePage(@PathParam("id") int itemId) {
        Node listItem = list.get(itemId);
        String result =
                "<html>" +
                        "  <head>" +
                        "    <title>Удаление элемента списка</title>" +
                        "  </head>" +
                        "  <body>" +
                        "    <h1>Удаление элемента списка</h1>" +
                        "    <form method=\"post\" action=\"/delete/" + itemId + "\">" +
                        "      <p>Удалить " + listItem.getName() + "?</p>" +
                        "      <input type=\"text\" name=\"value\" value=\"" + listItem.getName() +"\"/>" +
                        "      <input type=\"submit\"/>";
        result +=
                "            </form>" +
                        "  </body>" +
                        "</html>";
        return result;
    }

    /**
     * Редактирует элемент списка на основе полученных данных.
     * @param itemId индекс элемента списка.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/delete/{id}")
    @Produces("text/html")
    public Response deleteItem(@PathParam("id") int itemId, @FormParam("value") Node itemValue) {
        Node tmp = new Node();
        tmp.setId(-100);
        itemValue.delAll();
        tmp.setPrevId(0);
        list.set(itemId, tmp);

        System.out.println(itemId);

        for (var c : list){
            System.out.println(c.getName());
        }
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Пример вывода вложенного списка.
     */
    @GET
    @Path("nested_list")
    @Produces("text/html")
    public String getNestedListExample() {
        return "<html>" +
                "  <head>" +
                "    <title>Hello world</title>" +
                "  </head>" +
                "  <body>" +
                "    <h1>Hello world</h1>" +
                "    <ul>" +
                "      <li>1</li>" +
                "      <li>2</li>" +
                "      <li>3" +
                "        <ul>" +
                "          <li>3.1</li>" +
                "        </ul>" +
                "      </li>" +
                "    </ul>" +
                "  </body>" +
                "</html>";
    }

}
