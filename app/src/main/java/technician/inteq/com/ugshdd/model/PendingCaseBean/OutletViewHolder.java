package technician.inteq.com.ugshdd.model.PendingCaseBean;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

/**
 * Created by Nishant Sambyal on 13-Jul-17.
 */

public class OutletViewHolder extends ParentViewHolder {

    private TextView mRecipeTextView;

    public OutletViewHolder(View itemView) {
        super(itemView);
//        mRecipeTextView = itemView.findViewById(R.id.recipe_textview);
    }

    public void bind(Outlets outlets) {
        mRecipeTextView.setText(outlets.getmName());
    }
}