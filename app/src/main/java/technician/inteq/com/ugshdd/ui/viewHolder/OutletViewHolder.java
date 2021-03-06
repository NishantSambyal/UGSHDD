package technician.inteq.com.ugshdd.ui.viewHolder;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Outlets;

/**
 * Created by Nishant Sambyal on 13-Jul-17.
 */

public class OutletViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    private TextView outlet, isDone;
    private ImageView arrowImage;
    private View view;

    public OutletViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        outlet = (TextView) itemView.findViewById(R.id.outlet);
        arrowImage = (ImageView) itemView.findViewById(R.id.arrowImage);
        isDone = (TextView) itemView.findViewById(R.id.is_done);
    }

    public void bind(Outlets outlets) {
        view.setTag(outlets.getOutletName());
        outlet.setText(outlets.getOutletName());
        String vis = outlets.getChildList().get(0).getIsCompleted();
        if (vis != null && outlets.getChildList().get(0).getIsCompleted().equals("1")) {
            isDone.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
                arrowImage.setRotation(ROTATED_POSITION);
            } else {
                arrowImage.setRotation(INITIAL_POSITION);
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }

            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
            arrowImage.startAnimation(rotateAnimation);
        }
    }
}