package technician.inteq.com.ugshdd.ui.dialogFragment;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import technician.inteq.com.ugshdd.Controller.ContactController;
import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.model.contact.AddContact;
import technician.inteq.com.ugshdd.util.ExpandableHeightListView;
import technician.inteq.com.ugshdd.util.Utility;

/**
 * Created by Nishant Sambyal on 08-Aug-17.
 */

public class ContactDialog extends DialogFragment {

    List<AddContact> contactList;
    ExpandableHeightListView listView;
    String number_id;
    AddContact contact;
    LinearLayout gone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactList = new ArrayList<>();
        refreshList();
        contact = ContactController.getSelectedNumber();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        gone = (LinearLayout) view.findViewById(R.id.gone);
        final EditText numberText = (EditText) view.findViewById(R.id.et_number);
        final Button add = (Button) view.findViewById(R.id.add);
        final TextView selected = (TextView) view.findViewById(R.id.selected);

        if (contact != null) {
            selected.setText(contact.getNumber());
            gone.setVisibility(View.GONE);
        }

        listView = (ExpandableHeightListView) view.findViewById(R.id.listView);
        final ArrayAdapter adapter = new ArrayAdapter<AddContact>(getActivity(), android.R.layout.simple_list_item_1, contactList) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setText(contactList.get(position).getNumber());
                return view;
            }

        };
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number_id = contactList.get(position).getId();
                selected.setText(contactList.get(position).getNumber());
                if (gone.getVisibility() == View.VISIBLE) {
                    gone.setVisibility(View.GONE);
                }

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int positionItem, long id) {
                Utility.chooseOptions(getActivity(), new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                        alertDialog.setTitle("Warning !");
                        alertDialog.setCancelable(true);
                        alertDialog.setMessage("Are you sure ?");
                        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                number_id = contactList.get(positionItem).getId();
                                ContactController.deleteNumber(number_id);
                                refreshList();
                                adapter.notifyDataSetChanged();
                                Utility.alertDialog.dismiss();
                                if (contactList.size() < 1) {
                                    gone.setVisibility(View.VISIBLE);
                                    selected.setText("");
                                    number_id = null;
                                }
                            }
                        });
                        alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Utility.alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }, "Delete");
                return false;
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberText.getVisibility() == View.VISIBLE) {
                    if (contactList.size() < 5) {
                        String number = numberText.getText().toString();
                        if (number.trim().length() < 1) {
                            Utility.toast(getActivity(), "Please enter a number");
                        } else {
                            if (ContactController.insertContact(number)) {
                                refreshList();
                                adapter.notifyDataSetChanged();
                                Utility.toast(getActivity(), "Number saved !");
                                if (gone.getVisibility() == View.VISIBLE) {
                                    gone.setVisibility(View.GONE);
                                }

                            } else {
                                Utility.toast(getActivity(), "Number already exists");
                            }
                            numberText.setText("");
                        }
                    } else {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                        alertDialog.setTitle("Error !");
                        alertDialog.setCancelable(true);
                        alertDialog.setMessage("More than 5 numbers are not allowed\nDelete one number to enter a new one");
                        alertDialog.setPositiveButton("OK", null);
                        alertDialog.show();
                    }

                } else {
                    numberText.setVisibility(View.VISIBLE);
                    add.setText("Add");
                }

            }
        });
        listView.setExpanded(true);
        listView.setAdapter(adapter);

        view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        view.findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number_id != null) {
                    ContactController.setSelectedNumber(number_id);
                    getDialog().dismiss();
                } else if (ContactController.getSelectedNumber() == null) {
                    refreshList();
                    if (contactList.size() > 0) {
                        Utility.toast(getActivity(), "No number selected\nPlease select a number");
                    } else {
                        AlertDialog.Builder alertDialogNumber = new AlertDialog.Builder(getActivity());
                        alertDialogNumber.setTitle("Warning !");
                        alertDialogNumber.setCancelable(true);
                        alertDialogNumber.setMessage("Now you cannot acknowledge a task\nAre you sure want to save ?");
                        alertDialogNumber.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                getDialog().dismiss();
                            }
                        });
                        alertDialogNumber.setNeutralButton("Cancel", null);
                        alertDialogNumber.show();
                    }

                } else {
                    Utility.toast(getActivity(), "No number selected\nlease add and select a number");
                }

            }
        });
        return view;
    }

    void refreshList() {
        contactList.clear();
        ContactController.getAllContact(contactList);
    }
}
