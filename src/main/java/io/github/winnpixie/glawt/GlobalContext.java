package io.github.winnpixie.glawt;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class GlobalContext {
    private static final AtomicInteger LIST_NAME_TICKER = new AtomicInteger(1);
    private static final Map<Integer, CommandList> LIST_MAP = new HashMap<>();
    private static final Stack<CommandList> LIST_STACK = new Stack<>();
    private static final CommandList ROOT_COMMAND_LIST = new CommandList(ListMode.GL_COMPILE_AND_EXECUTE);

    static {
        LIST_MAP.put(0, ROOT_COMMAND_LIST);
        LIST_STACK.push(ROOT_COMMAND_LIST);
    }

    public static CommandList getList(int name) {
        return LIST_MAP.get(name);
    }

    public static void setList(int name, CommandList list) {
        LIST_MAP.put(name, list);
    }

    public static void deleteList(int name) {
        LIST_MAP.remove(name);
    }

    public static int createList() {
        int name = LIST_NAME_TICKER.getAndIncrement();

        setList(name, new CommandList(ListMode.GL_COMPILE));

        return name;
    }

    public static CommandList getActiveList() {
        return LIST_STACK.peek();
    }

    public static void pushListStack(CommandList list) {
        LIST_STACK.push(list);
    }

    public static void popListStack() {
        LIST_STACK.pop();
    }
}
