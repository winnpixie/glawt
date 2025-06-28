package io.github.winnpixie.glawt.utilities;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SynchronizedList<T> implements List<T> {
    private final List<T> listImpl;

    public SynchronizedList(List<T> list) {
        this.listImpl = list;
    }

    @Override
    public int size() {
        synchronized (listImpl) {
            return listImpl.size();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (listImpl) {
            return listImpl.isEmpty();
        }
    }

    @Override
    public boolean contains(Object o) {
        synchronized (listImpl) {
            return listImpl.contains(o);
        }
    }

    @Override
    public Iterator<T> iterator() {
        synchronized (listImpl) {
            return listImpl.iterator();
        }
    }

    @Override
    public Object[] toArray() {
        synchronized (listImpl) {
            return listImpl.toArray();
        }
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        synchronized (listImpl) {
            return listImpl.toArray(a);
        }
    }

    @Override
    public boolean add(T t) {
        synchronized (listImpl) {
            return listImpl.add(t);
        }
    }

    @Override
    public boolean remove(Object o) {
        synchronized (listImpl) {
            return listImpl.remove(o);
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        synchronized (listImpl) {
            return listImpl.containsAll(c);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        synchronized (listImpl) {
            return listImpl.addAll(c);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        synchronized (listImpl) {
            return listImpl.addAll(index, c);
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        synchronized (listImpl) {
            return listImpl.removeAll(c);
        }
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        synchronized (listImpl) {
            return listImpl.retainAll(c);
        }
    }

    @Override
    public void clear() {
        synchronized (listImpl) {
            listImpl.clear();
        }
    }

    @Override
    public T get(int index) {
        synchronized (listImpl) {
            return listImpl.get(index);
        }
    }

    @Override
    public T set(int index, T element) {
        synchronized (listImpl) {
            return listImpl.set(index, element);
        }
    }

    @Override
    public void add(int index, T element) {
        synchronized (listImpl) {
            listImpl.add(index, element);
        }
    }

    @Override
    public T remove(int index) {
        synchronized (listImpl) {
            return listImpl.remove(index);
        }
    }

    @Override
    public int indexOf(Object o) {
        synchronized (listImpl) {
            return listImpl.indexOf(o);
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        synchronized (listImpl) {
            return listImpl.lastIndexOf(o);
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        synchronized (listImpl) {
            return listImpl.listIterator();
        }
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        synchronized (listImpl) {
            return listImpl.listIterator(index);
        }
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        synchronized (listImpl) {
            return new SynchronizedList<>(listImpl.subList(fromIndex, toIndex));
        }
    }
}
