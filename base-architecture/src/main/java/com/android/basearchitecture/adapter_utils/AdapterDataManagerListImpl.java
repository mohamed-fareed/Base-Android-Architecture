package com.android.basearchitecture.adapter_utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ahmed on 11/23/2016.
 * add            O(1)
 * update         O(n)
 * remove         O(n)
 * getByPosition  O(1)
 * getById        O(1)
 */
public class AdapterDataManagerListImpl<V> implements AdapterDataManager<V> {


    private Identifier<V> mIdentifier;
    private List<V> mData;
    private Map<String, V> mIdToItem;

    public AdapterDataManagerListImpl(Identifier<V> identifier) {
        mIdentifier = identifier;
        mData = new ArrayList<>();
        mIdToItem = new HashMap<>();
    }

    @Override
    public Range addAll(List<V> list) {
        int start = mData.size();

        for (V item : list) {
            String id = mIdentifier.getId(item);
            if (!mIdToItem.containsKey(id)) {
                mIdToItem.put(id, item);
                mData.add(item);
            }
        }

        int end = mData.size() - 1;
        int count = end - start + 1;
        return new Range(start, count);
    }

    @Override
    public int add(V item) {
        String id = mIdentifier.getId(item);
        if (!mIdToItem.containsKey(id)) {
            mIdToItem.put(id, item);
            mData.add(item);
            return mData.size() - 1;
        } else
            return -1;
    }

    @Override
    public Range clear() {
        int start = 0;
        int count = mData.size();
        mData.clear();
        mIdToItem.clear();
        return new Range(start, count);
    }

    @Override
    public int remove(String id) {
        int position = findPosition(id);
        if (position == -1)
            return -1;

        mData.remove(position);
        mIdToItem.remove(id);
        return position;
    }

    private int findPosition(String id) {
        for (int i = 0; i < mData.size(); i++)
            if (mIdentifier.getId(mData.get(i)).equals(id))
                return i;
        return -1;
    }

    @Override
    public V getByPosition(int position) {
        if (position >= mData.size() || position < 0)
            return null;
        return mData.get(position);
    }

    @Override
    public V getById(String id) {
        return mIdToItem.get(id);
    }

    @Override
    public int update(String id, V newItem) {
        int position = findPosition(id);
        if (position == -1)
            return -1;

        mData.set(position, newItem);
        mIdToItem.put(id, newItem);
        return position;
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
