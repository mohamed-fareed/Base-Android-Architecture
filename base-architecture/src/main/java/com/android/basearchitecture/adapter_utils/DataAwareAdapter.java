package com.android.basearchitecture.adapter_utils;

import android.support.v7.widget.RecyclerView;

import com.drbridge.doctorsapp.ui.adapter.helpers.AdapterDataManager;
import com.drbridge.doctorsapp.ui.adapter.helpers.AdapterDataManagerListImpl;

import java.util.List;

/**
 * allows add, remove, update of data and notifies the UI on these changes
 * Created by ahmed on 11/23/2016.
 */
public abstract class DataAwareAdapter<V> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final AdapterDataManager.Identifier<V> mIdentifier;
    private final AdapterDataManager<V> mDataManager;

    public DataAwareAdapter(AdapterDataManager.Identifier<V> identifier) {
        mIdentifier = identifier;
        mDataManager = new AdapterDataManagerListImpl<V>(identifier);
    }

    public void addAll(List<V> data) {
        addAll(data, true);
    }

    public void addAll(List<V> data, boolean animate) {
        AdapterDataManager.Range range = mDataManager.addAll(data);
        if (animate) {
            if (range.getCount() > 0)
                notifyItemRangeInserted(range.getStart()+1, range.getCount());
        } else
            notifyDataSetChanged();

    }

    public V getByPosition(int position) {
        return mDataManager.getByPosition(position);
    }

    public V getById(String id) {
        return mDataManager.getById(id);
    }

    public void add(V item) {
        int position = mDataManager.add(item);
        if (position != -1)
            notifyItemInserted(position);
    }

    public void update(String id, V newItem) {
        int position = mDataManager.update(id, newItem);
        if (position != -1)
            notifyItemChanged(position);
    }

    public void remove(String id) {
        int position = mDataManager.remove(id);
        if (position != -1)
            notifyItemRemoved(position);
    }

    public void clear() {
        AdapterDataManager.Range range = mDataManager.clear();
        if (range.getCount() > 0)
            notifyItemRangeRemoved(range.getStart(), range.getCount());
    }

    @Override
    public int getItemCount() {
        return mDataManager.getCount();
    }


}
