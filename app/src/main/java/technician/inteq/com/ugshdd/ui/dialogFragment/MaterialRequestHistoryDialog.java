package technician.inteq.com.ugshdd.ui.dialogFragment;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.adapters.ArrayAdapters.MaterialRequestHistoryAdapter;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequest;
import technician.inteq.com.ugshdd.model.materialRequest.MaterialRequestTransaction;
import technician.inteq.com.ugshdd.ui.activity.MaterialRequestTransactionSummary;

/**
 * Created by Nishant Sambyal on 02-Sep-17.
 */

public class MaterialRequestHistoryDialog extends DialogFragment {

    public static final String TRANSACTION_ID = "transaction_id";
    ListView listView;
    List<MaterialRequestTransaction> materialRequestTransactionList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.material_request_history, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        listView = (ListView) view.findViewById(R.id.listView);
        prepareList();

        view.findViewById(R.id.done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        listView.setAdapter(new MaterialRequestHistoryAdapter(getActivity(), R.layout.summary_pt_single_item, materialRequestTransactionList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), MaterialRequestTransactionSummary.class);
                intent.putExtra(TRANSACTION_ID, materialRequestTransactionList.get(position).getTransactionID());
                startActivity(intent);

            }
        });

        if (materialRequestTransactionList.size() < 1) {
            view.findViewById(R.id.empty).setVisibility(View.VISIBLE);
        }

        return view;

    }

    private void prepareList() {
        materialRequestTransactionList = new ArrayList<>();
        MaterialRequest.getMaterialRequestTransactions(materialRequestTransactionList);
    }

}
