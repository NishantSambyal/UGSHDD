package technician.inteq.com.ugshdd.adapters.expandableRVAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.technicalRequestBean.TechnicalRequests;
import technician.inteq.com.ugshdd.model.technicalRequestBean.TechnicalRequestsDetails;
import technician.inteq.com.ugshdd.ui.viewHolder.TechnicalRequestsDetailsViewHolder;
import technician.inteq.com.ugshdd.ui.viewHolder.TechnicalRequestsViewHolder;

/**
 * Created by Patyal on 7/18/2017.
 */

public class ExpandableTechnicalRVAdapter extends ExpandableRecyclerAdapter<TechnicalRequests, TechnicalRequestsDetails, TechnicalRequestsViewHolder, TechnicalRequestsDetailsViewHolder> {

    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_NORMAL = 3;
    private LayoutInflater mInflater;
    private List<TechnicalRequests> technicalRequests;

    public ExpandableTechnicalRVAdapter(Context context, @NonNull List<TechnicalRequests> parentList) {
        super(parentList);
        technicalRequests = parentList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TechnicalRequestsViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View outletView;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                outletView = mInflater.inflate(R.layout.pending_case_single_item, parentViewGroup, false);
                break;
        }
        return new TechnicalRequestsViewHolder(outletView);
    }

    @NonNull
    @Override
    public TechnicalRequestsDetailsViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View outletDetailView;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                outletDetailView = mInflater.inflate(R.layout.technical_request_detail_item_view, childViewGroup, false);
                break;
        }
        return new TechnicalRequestsDetailsViewHolder(outletDetailView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull TechnicalRequestsViewHolder parentViewHolder, int parentPosition, @NonNull TechnicalRequests parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull TechnicalRequestsDetailsViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull TechnicalRequestsDetails child) {
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
