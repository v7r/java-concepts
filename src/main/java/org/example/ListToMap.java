package org.example;

/*
 * Given a list of entities, write a function to return the entity list as a Map.
 * The entities can have one or more children who can further have one or more children.
 * The code should be able to handle this nesting of entities.
 * - Print each id before adding it
 * - Print Item Ids layer by layer
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

class Entity {
    String id;
    List<Entity> children = new ArrayList<>();

    public Entity(String id, List<Entity> children) {
        this.id = id;
        this.children = children;
    }

    public Entity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Entity> getChildren() {
        return children;
    }

}

public class ListToMap {

    static Map<String, Entity> resultMap = new HashMap<String, Entity>();

    public static Map<String, Entity> convertListToMap(List<Entity> entities) {
        // TODO:

        /*final Function<Map<String, Entity>, Entity> flatten = (Entity parent) -> {
            Map<String, Entity> r1 = new HashMap<String, Entity>();
            r1.put(parent.getId(), parent);
            if (parent.getChildren().isEmpty()) return r1;
            parent.getChildren().forEach(child -> {
                Map<String, Entity> r2 = new HashMap<String, Entity>();
                r2.put(child.getId(), child);
                if (child.getChildren().isEmpty()) return r2;
                //r1.putAll(flatten(child.getChildren()));
                return r1;
            });
        };

        Map<String, Entity> r = new HashMap<String, Entity>();
        entities.forEach(entity -> {
            r.put(flatten.apply(entity));
        });
        return r;
        */
        return null;
    }

    public static void main(String[] args) {
        Entity one = new Entity("a");
        Entity two = new Entity("b");
        ArrayList<Entity> entityList = new ArrayList<Entity>();
        entityList.add(one);
        entityList.add(two);
        Entity three = new Entity("c", entityList);
        Entity four = new Entity("d", Arrays.asList(three));

        List<Entity> entities = new ArrayList<Entity>();
        entities.add(four);
        System.out.println(convertListToMap(entities).toString());

    }
}
