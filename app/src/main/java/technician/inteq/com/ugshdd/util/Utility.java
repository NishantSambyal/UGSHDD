package technician.inteq.com.ugshdd.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import technician.inteq.com.ugshdd.R;

import static android.R.layout.list_content;

/**
 * Created by Nishant Sambyal on 16-Jun-17.
 */

public class Utility {

    public static final String LOGGED_IN = "logged_in";
    private static final String TECH_LOGIN = "tech_session";
    public static AlertDialog alertDialog;
    static TextView[] view;
    private static Context context;

    private static void setToolbar(AppCompatDelegate delegate, Toolbar toolbar, TextView[] views, String[] string) {
        delegate.setSupportActionBar(toolbar);
        delegate.getSupportActionBar().setDisplayShowTitleEnabled(false);
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setDisplayUseLogoEnabled(true);
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.option_menu);
        toolbar.setOverflowIcon(drawable);
        view = views;
        setNames(string);
    }

    public static void setNames(String[] string) {
        view[0].setText(string[0]);
        if (!string[1].equals("")) {
            view[1].setVisibility(View.VISIBLE);
            view[1].setText(string[1]);
        } else {
            view[1].setVisibility(View.GONE);
        }
    }

    public static void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void createLoginSession(String tech_fullname) {
        SharedPreferences mPref = context.getSharedPreferences(TECH_LOGIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString("full_name", tech_fullname);
        editor.putBoolean(LOGGED_IN, true);
//        editor.putInt("run_id", runID);
//        editor.putString("staff_id", dmStaffID);
        editor.apply();
    }

    public static SharedPreferences getLoginSession(Context context) {
        SharedPreferences userPref = context.getSharedPreferences(TECH_LOGIN, Context.MODE_PRIVATE);
        return userPref;
    }

    public static void clearLoginSession() {
        SharedPreferences.Editor editor = context.getSharedPreferences(TECH_LOGIN, Context.MODE_PRIVATE).edit();
        editor.putBoolean(LOGGED_IN, false);
        editor.apply();
    }

    public static RecyclerView.OnScrollListener addFabBehaviour(final FloatingActionButton newCashReport) {
        RecyclerView.OnScrollListener scrollBehaviour = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    // Scroll Down
                    if (newCashReport.isShown()) {
                        newCashReport.hide();
                    }
                } else if (dy < 0) {
                    // Scroll Up
                    if (!newCashReport.isShown()) {
                        newCashReport.show();
                    }
                }
            }
        };
        return scrollBehaviour;
    }

    public static void chooseOptions(final Context context, AdapterView.OnItemClickListener clickListener, String... args) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        final ArrayAdapter adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, args) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setTextColor(Color.BLACK);
                return view;
            }
        };
        ListView listview;
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View popup = inflater.inflate(list_content, null);
            View title = inflater.inflate(R.layout.popup_title, null);

            LinearLayout layout = new LinearLayout(context);
            layout.setOrientation(LinearLayout.VERTICAL);

            listview = (ListView) popup.findViewById(android.R.id.list);
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(clickListener);

            layout.addView(title);
            layout.addView(popup);

            dialog.setView(layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        alertDialog = dialog.create();
        alertDialog.show();
    }

    public static String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return String.format("%.2f", (double) tmp / factor);
    }

    public static String round(String valueTemp, int places) {
        double value = Double.parseDouble(valueTemp);
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return String.format("%.2f", (double) tmp / factor);
    }

    public AppCompatDelegate initializeDelegate(Activity activity, @LayoutRes int resId, Bundle savedInstanceState, String[] string) {
        context = activity;
        AppCompatDelegate appCompatdelegate = null;
        appCompatdelegate = AppCompatDelegate.create(activity, new Deligate());
        appCompatdelegate.onCreate(savedInstanceState);
        appCompatdelegate.setContentView(resId);
        setToolbar(appCompatdelegate, (Toolbar) appCompatdelegate.findViewById(R.id.toolbar), new TextView[]{(TextView) appCompatdelegate.findViewById(R.id.toolbar_title), (TextView) appCompatdelegate.findViewById(R.id.toolbar_subtitle)}, string);
        return appCompatdelegate;
    }

    class Deligate implements AppCompatCallback {

        @Override
        public void onSupportActionModeStarted(ActionMode mode) {

        }

        @Override
        public void onSupportActionModeFinished(ActionMode mode) {

        }

        @Nullable
        @Override
        public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
            return null;
        }
    }
}
