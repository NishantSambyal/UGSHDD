package technician.inteq.com.ugshdd.adapters.expandableRVAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.AcceptedItemBean.AcceptedItem;
import technician.inteq.com.ugshdd.model.AcceptedItemBean.AcceptedItemDetails;
import technician.inteq.com.ugshdd.ui.viewHolder.AcceptedItemDetailViewHolder;
import technician.inteq.com.ugshdd.ui.viewHolder.AcceptedItemViewHolder;

/**
 * Created by Patyal on 7/17/2017.
 */

public class ExpandableAccepedItemRVAdapter extends ExpandableRecyclerAdapter<AcceptedItem, AcceptedItemDetails, AcceptedItemViewHolder, AcceptedItemDetailViewHolder> {


    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_NORMAL = 3;

    private LayoutInflater mInflater;
    private List<AcceptedItem> items;

    public ExpandableAccepedItemRVAdapter(Context context, @NonNull List<AcceptedItem> items) {
        super(items);
        items = items;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AcceptedItemViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                view = mInflater.inflate(R.layout.pending_case_single_item, parentViewGroup, false);
                break;
        }
        return new AcceptedItemViewHolder(view);
    }

    @NonNull
    @Override
    public AcceptedItemDetailViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                view = mInflater.inflate(R.layout.accepted_item_detail_item_view, childViewGroup, false);
                break;
        }
        return new AcceptedItemDetailViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull AcceptedItemViewHolder parentViewHolder, int parentPosition, @NonNull AcceptedItem parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull AcceptedItemDetailViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull AcceptedItemDetails child) {
        childViewHolder.bind(child);
    }

    @Override
    public int getParentViewType(int parentPosition) {
        return PARENT_NORMAL;
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        return CHILD_NORMAL;
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_VEGETARIAN || viewType == PARENT_NORMAL;
    }

}