package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.transferItemBean.TransferItem;
import technician.inteq.com.ugshdd.model.transferItemBean.TransferItemDetail;
import technician.inteq.com.ugshdd.ui.viewHolder.TransferItemDetailViewHolder;
import technician.inteq.com.ugshdd.ui.viewHolder.TransferItemViewHolder;

/**
 * Created by Patyal on 7/15/2017.
 */

public class ExpandableMaterialListRVAdapter extends ExpandableRecyclerAdapter<TransferItem, TransferItemDetail, TransferItemViewHolder, TransferItemDetailViewHolder> {


    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_NORMAL = 3;

    private LayoutInflater mInflater;
    private List<TransferItem> items;

    public ExpandableMaterialListRVAdapter(Context context, @NonNull List<TransferItem> parentList) {
        super(parentList);
        items = parentList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TransferItemViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View view;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                view = mInflater.inflate(R.layout.pending_case_single_item, parentViewGroup, false);
                break;
        }
        return new TransferItemViewHolder(view);
    }

    @NonNull
    @Override
    public TransferItemDetailViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View view;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                view = mInflater.inflate(R.layout.transfer_item_detail_item_view, childViewGroup, false);
                break;
        }
        return new TransferItemDetailViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(@NonNull TransferItemViewHolder parentViewHolder, int parentPosition, @NonNull TransferItem parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull TransferItemDetailViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull TransferItemDetail child) {
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

