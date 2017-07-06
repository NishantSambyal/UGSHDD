package technician.inteq.com.ugshdd.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import technician.inteq.com.ugshdd.R;
import technician.inteq.com.ugshdd.util.ToolbarUtil;

public class MainActivity extends Activity {

    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ToolbarUtil().initializeDeligate(this, R.layout.login_activity, savedInstanceState, new String[]{"UGS HDD", ""});
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
}
