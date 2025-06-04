package io.github.winnpixie.glawt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DisplayLists {
    private static final AtomicInteger LIST_NAME_TICKER = new AtomicInteger(1);
    private static final Map<Integer, CommandList> LISTS = new HashMap<>();
    private static final CommandList ROOT_COMMAND_LIST = new CommandList(ListMode.GL_COMPILE_AND_EXECUTE);

    private static int ACTIVE_LIST = 0;

    static {
        LISTS.put(0, ROOT_COMMAND_LIST);
    }

    public static CommandList getList(int name) {
        return LISTS.get(name);
    }

    public static void setList(int name, CommandList list) {
        LISTS.put(name, list);
    }

    public static void deleteList(int name) {
        LISTS.remove(name);
    }

    public static int createList() {
        int name = LIST_NAME_TICKER.getAndIncrement();

        setList(name, new CommandList(ListMode.GL_COMPILE));

        return name;
    }

    public static CommandList getActiveList() {
        return LISTS.get(ACTIVE_LIST);
    }

    public static void setActiveList(int name) {
        ACTIVE_LIST = name;
    }
}
