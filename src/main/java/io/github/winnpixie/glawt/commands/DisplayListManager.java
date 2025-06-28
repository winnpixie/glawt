package io.github.winnpixie.glawt.commands;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class DisplayListManager {
    private final AtomicLong nameTicker = new AtomicLong(1L);
    private final Map<Long, DisplayList> lists = new ConcurrentHashMap<>();

    private long activeList = 0;

    public DisplayListManager() {
        // Root command list
        lists.put(0L, new DisplayList(DisplayListMode.GL_COMPILE_AND_EXECUTE));
    }

    public long getActiveList() {
        return activeList;
    }

    public void setActiveList(long name) {
        activeList = name;
    }

    public DisplayList getList(long name) {
        return lists.get(name);
    }

    public void setList(long name, DisplayList list) {
        lists.put(name, list);
    }

    public long createList() {
        long name = nameTicker.getAndIncrement();

        setList(name, new DisplayList(DisplayListMode.GL_COMPILE));

        return name;
    }

    public void deleteList(long name) {
        lists.remove(name);
    }

    public DisplayList getActiveDisplayList() {
        return getList(activeList);
    }
}
