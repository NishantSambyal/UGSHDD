package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.widget.ListView;

import technician.inteq.com.ugshdd.R;

/**
 * Created by Nishant Sambyal on 21-Aug-17.
 */

public class MaterialRequestList extends Activity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    ListView listView;
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_request_list);
        listView = (ListView) findViewById(R.id.listView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        getLoaderManager().initLoader(listView.getId(), null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
