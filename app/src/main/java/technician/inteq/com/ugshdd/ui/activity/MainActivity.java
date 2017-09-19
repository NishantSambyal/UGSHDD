package technician.inteq.com.ugshdd.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.Services.ReceiveSMS;
import technician.inteq.com.ugshdd.ui.dialogFragment.AddIPDialog;
import technician.inteq.com.ugshdd.util.AndroidDatabaseManager;
import technician.inteq.com.ugshdd.util.Utility;

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, ReceiveSMS.class));
        setContentView(R.layout.login_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_user = username.getText().toString().trim();
                String str_pass = password.getText().toString().trim();
                if (TextUtils.isEmpty(str_user)) {
                    Toast.makeText(MainActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(str_pass)) {
                    Toast.makeText(MainActivity.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else {
                    if (str_user.equals("master") && str_pass.equals("master")) {
                        startActivity(new Intent(MainActivity.this, Dashboard.class));
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.logout).setVisible(false);
        menu.findItem(R.id.database).setVisible(false);
        menu.findItem(R.id.completed_tasks).setVisible(false);
        menu.findItem(R.id.import_csv).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.database:
                startActivity(new Intent(this, AndroidDatabaseManager.class));
                break;
            case R.id.logout:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.about:
                Utility.about(this);
                break;
            case R.id.add_ip_address:
                AddIPDialog addIPDialog = new AddIPDialog();
                addIPDialog.show(getFragmentManager(), "add_ip_fragment");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}