package apps.weverton.com.br.validarapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginSuccessActivity extends AppCompatActivity {
    private TextView txtNome;
    private TextView txtEmail;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        txtNome = (TextView) findViewById(R.id.txt_success_name);
        txtEmail = (TextView) findViewById(R.id.txt_success_email);
        btnLogout = (Button) findViewById(R.id.btn_logout);

        Intent intent = getIntent();
        String loginNome = intent.getStringExtra("nome");
        String loginEmail = intent.getStringExtra("email");
        txtNome.setText("Seja Bem-Vindo, " + loginNome);
        txtEmail.setText(loginEmail);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LoginSuccessActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Deseja sair ?");
                builder.setPositiveButton("Sair!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(LoginSuccessActivity.this, MainActivity.class);
                        startActivity(intent);

                        finish();

                    }
                });

                builder.setNegativeButton("Ficar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
