package ru.ac.uniyar.Shebeta;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Контроллер отвечающий за представление списка.
 */
@Path("/")
public class TreePresentationController {
    private final List<Node> list;

    /**
     * Запоминает список, с которым будет работать.
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
                "      <form method=\"post\" action=\"add_random_item\">" +
                "        <input type=\"submit\" value=\"Add random item\"/>" +
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
    @Path("add_random_item")
    @Produces("text/html")
    public Response addRandomItem() {
        Node tmp = new Node("random_elem");
        tmp.setId(list.size());
        list.add(tmp);
        try {
            return Response.seeOther(new URI("/")).build();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Ошибка построения URI для перенаправления");
        }
    }

    /**
     * Выводит страничку для редактирования одного элемента.
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
                        "    <title>Редактирование элемента списка</title>" +
                        "  </head>" +
                        "  <body>" +
                        "    <h1>Редактирование элемента списка</h1>" +
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
     * Редактирует элемент списка на основе полученных данных.
     * @param itemId индекс элемента списка.
     * @return перенаправление на основную страницу со списком.
     */
    @POST
    @Path("/edit/{id}")
    @Produces("text/html")
    public Response editItem(@PathParam("id") int itemId, @FormParam("value") Node itemValue) {
        itemValue.setId(itemId);
        list.set(itemId, itemValue);
        System.out.println(itemValue.getId());
        System.out.println(itemId);
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
