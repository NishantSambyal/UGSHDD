package technician.inteq.com.ugshdd.ui.dialogFragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import technician.inteq.com.ugshdd.Controller.CaseController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.PendingCaseBean.Case;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 01-Aug-17.
 */

public class EditItemDialog extends DialogFragment {

    Button edit;
    TextView title, itemName, itemDescription, itemRate, itemAmount, itemQuantity;
    int quantity;
    short itemID;
    double amount;
    RefreshItem refreshItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemID = getArguments().getShort("item_id");
        refreshItem = (RefreshItem) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_action_inventory_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        view.findViewById(R.id.spinner_section).setVisibility(View.GONE);
        view.findViewById(R.id.spinner_category).setVisibility(View.GONE);
        view.findViewById(R.id.spinner_items).setVisibility(View.GONE);
        title = (TextView) view.findViewById(R.id.title);
        itemName = (TextView) view.findViewById(R.id.item_name);
        itemDescription = (TextView) view.findViewById(R.id.item_description);
        itemRate = (TextView) view.findViewById(R.id.item_rate);
        itemAmount = (TextView) view.findViewById(R.id.item_amount);
        itemQuantity = (TextView) view.findViewById(R.id.quantity);
        edit = (Button) view.findViewById(R.id.add);
        edit.setText("Edit");
        title.setText("Edit Item");
        itemName.setText(getArguments().getString("item_name"));
        itemDescription.setText(getArguments().getString("item_description"));
        itemRate.setText(getArguments().getString("item_rate"));
        itemAmount.setText(getArguments().getString("item_amount"));
        itemQuantity.setText(String.valueOf(getArguments().getInt("item_quantity")));
        quantity = getArguments().getInt("item_quantity");

        view.findViewById(R.id.inc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                amount = Integer.parseInt(itemRate.getText().toString()) * quantity;
                itemQuantity.setText(String.valueOf(quantity));
                itemAmount.setText(String.valueOf(amount));
            }
        });
        view.findViewById(R.id.dec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    amount = Integer.parseInt(itemRate.getText().toString()) * quantity;
                    itemQuantity.setText(String.valueOf(quantity));
                    itemAmount.setText(String.valueOf(amount));
                }
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Case updateCase = new Case(quantity, String.valueOf(amount), itemID);
                try {
                    CaseController.updateCase(updateCase);
                    refreshItem.refresh();
                    getDialog().dismiss();
                    Utility.toast(getActivity(), "Item Updated");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
        return view;
    }

    public interface RefreshItem {
        void refresh();
    }
}
