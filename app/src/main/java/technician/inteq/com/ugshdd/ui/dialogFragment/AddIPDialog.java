package technician.inteq.com.ugshdd.ui.dialogFragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.util.PreferenceUtility;

import static android.widget.Toast.makeText;
import static technician.inteq.com.ugshdd.R.id.gone;
import static technician.inteq.com.ugshdd.util.PreferenceUtility.getCurrentPortAndIpAddress;

/**
 * Created by Nishant Sambyal on 15-Sep-17.
 */

public class AddIPDialog extends DialogFragment implements View.OnClickListener {
    private TextView et_ip_address, et_port_no, selected;
    private LinearLayout layout;
    private Button add, cancle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ip_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        loadUi(view);
        String currentIP = getCurrentPortAndIpAddress(getActivity());
        if (!currentIP.equals("")) {
            layout.setVisibility(View.GONE);
            selected.setText(currentIP);
        }

        return view;
    }

    private void loadUi(View view) {
        et_ip_address = (TextView) view.findViewById(R.id.et_ip_address);
        et_port_no = (TextView) view.findViewById(R.id.et_port_no);
        selected = (TextView) view.findViewById(R.id.selected);
        layout = (LinearLayout) view.findViewById(gone);
        add = (Button) view.findViewById(R.id.add);
        cancle = (Button) view.findViewById(R.id.cancel);
        add.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                if (!TextUtils.isEmpty(et_ip_address.getText()) && !TextUtils.isEmpty(et_port_no.getText())) {
                    PreferenceUtility.setCurrentPortAndIpAddress(getActivity().getApplicationContext(), String.valueOf(et_port_no.getText()), String.valueOf(et_ip_address.getText()));
                    selected.setText(PreferenceUtility.getCurrentPortAndIpAddress(getActivity()));
                    makeText(getActivity(), et_ip_address.getText(), Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(et_ip_address.getText()))
                    Toast.makeText(getActivity(), "Enter IP ADDRESS", Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(et_port_no.getText()))
                    Toast.makeText(getActivity(), "Enter PORT NUMBER", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Enter IP ADDRESS and PORT NUMBER", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cancel:
                getDialog().dismiss();
                break;
        }
    }
}
