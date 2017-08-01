package technician.inteq.com.ugshdd.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;

import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.OutletDetail;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;
import technician.inteq.com.ugshdd.ui.viewHolder.OutletDetailViewHolder;
import technician.inteq.com.ugshdd.ui.viewHolder.OutletViewHolder;

/**
 * Created by Nishant Sambyal on 14-Jul-17.
 */

public class ExpandablePendingCaseAdapter extends ExpandableRecyclerAdapter<Outlets, OutletDetail, OutletViewHolder, OutletDetailViewHolder> {
    private static final int PARENT_VEGETARIAN = 0;
    private static final int PARENT_NORMAL = 1;
    private static final int CHILD_NORMAL = 3;
    private static final int PARENT_NORMAL_WITH_BACKGROUND = 4;
    private LayoutInflater mInflater;
    private List<Outlets> outletsList;


    public ExpandablePendingCaseAdapter(Context context, @NonNull List<Outlets> parentList) {
        super(parentList);
        outletsList = parentList;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OutletViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View outletView = null;
        switch (viewType) {
            default:
            case PARENT_NORMAL:
                outletView = mInflater.inflate(R.layout.pending_case_single_item, parentViewGroup, false);
                break;

            case PARENT_NORMAL_WITH_BACKGROUND:
                outletView = mInflater.inflate(R.layout.pending_case_single_item, parentViewGroup, false);
                CardView view = (CardView) outletView.findViewById(R.id.card_view);
                view.setCardBackgroundColor(Color.parseColor("#dac0b4"));
//                outletView.setBackgroundColor(Color.parseColor("#596b4739"));
                break;


        }
        return new OutletViewHolder(outletView);
    }

    @NonNull
    @Override
    public OutletDetailViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View outletDetailView;
        switch (viewType) {
            default:
            case CHILD_NORMAL:
                outletDetailView = mInflater.inflate(R.layout.pending_case_detail_item_view, childViewGroup, false);
                break;

        }
        return new OutletDetailViewHolder(outletDetailView);
    }

    @Override
    public void onBindParentViewHolder(@NonNull OutletViewHolder parentViewHolder, int parentPosition, @NonNull Outlets parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull OutletDetailViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull OutletDetail child) {
        childViewHolder.bind(child);
    }

    @Override
    public int getParentViewType(int parentPosition) {
        if (outletsList.get(parentPosition).getChildList().get(0).getIsAcknowledge().equals("1")) {
            return PARENT_NORMAL_WITH_BACKGROUND;
        }
        return PARENT_NORMAL;

    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {

        /*OutletDetail ingredient = mRecipeList.get(parentPosition).getIngredient(childPosition);
        if (ingredient.isVegetarian()) {
            return CHILD_VEGETARIAN;
        } else {*/
        return CHILD_NORMAL;
//        }
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_VEGETARIAN || viewType == PARENT_NORMAL || viewType == PARENT_NORMAL_WITH_BACKGROUND;
    }
}
