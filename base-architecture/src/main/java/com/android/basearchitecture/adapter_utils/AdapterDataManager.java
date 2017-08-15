package com.android.basearchitecture.adapter_utils;

import java.util.List;

/**
 * Created by ahmed on 11/23/2016.
 * support add, addall, remove, update and query by position
 * this function can be used for list adapters to store the data
 */
public interface AdapterDataManager<V> {
    interface Identifier<V> {
        String getId(V item);
    }

    /**
     * @return start -> end positions that were added, end is inclusive
     */
    AdapterDataManagerListImpl.Range addAll(List<V> v);

    /**
     * @return the position of the added item, return -1 if the item is already there
     */
    int add(V item);

    /**
     * @return start -> end positions of the items removed, end is inclusive
     */
    AdapterDataManagerListImpl.Range clear();

    /**
     * @return the position of the removed item, return -1 if the item is already there
     */
    int remove(String id);

    /**
     * @return the item at this ordered position, or null if no such id exists
     */
    V getByPosition(int position);

    /**
     * @return the item with this id, or null of no such item existed
     */
    V getById(String id);

    /**
     * @return the position of the updated item
     */
    int update(String id, V newItem);

    int getCount();

    class Range {
        private int start;
        private int count;

        public Range(int start, int count) {
            this.start = start;
            this.count= count;
        }

        public int getStart() {
            return start;
        }

        public int getCount() {
            return count;
        }
    }
}
