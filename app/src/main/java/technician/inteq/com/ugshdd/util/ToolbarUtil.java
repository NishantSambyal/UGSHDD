package technician.inteq.com.ugshdd.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import technician.inteq.com.ugshdd.R;

/**
 * Created by Nishant Sambyal on 16-Jun-17.
 */

public class ToolbarUtil {

    public static final String LOGGED_IN = "logged_in";
    private static final String TECH_LOGIN = "tech_session";
    static TextView[] view;
    private static Context context;

    private static void setToolbar(AppCompatDelegate delegate, Toolbar toolbar, TextView[] views, String[] string) {
        delegate.setSupportActionBar(toolbar);
        delegate.getSupportActionBar().setDisplayShowTitleEnabled(false);
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);
        delegate.getSupportActionBar().setLogo(R.mipmap.ic_launcher);
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

    public AppCompatDelegate initializeDeligate(Activity activity, @LayoutRes int resId, Bundle savedInstanceState, String[] string) {
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
