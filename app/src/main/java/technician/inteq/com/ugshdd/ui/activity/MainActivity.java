package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.util.AndroidDatabaseManager;
import technician.inteq.com.ugshdd.util.Utility;

public class MainActivity extends Activity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Utility().initializeDelegate(this, R.layout.login_activity, savedInstanceState, new String[]{"UGS HHD", ""});
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
                about();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setIcon(getResources().getDrawable(R.mipmap.tick));
        alertDialog.setTitle("About Version !");
        alertDialog.setCancelable(true);
        alertDialog.setMessage("Version : 001 \n Release Date : 27th-July-2017");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
}