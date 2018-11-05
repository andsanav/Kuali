package mx.itesm.kuali.kuali;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

public class AcercaDe extends AppCompatActivity {

    TextView correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        correo = findViewById(R.id.correo_contacto);
        correo.setText(Html.fromHtml("<a href=\"mailto:app.kuali.mx@gmail.com\">app.kuali.mx@gmail.com</a>"));
        correo.setMovementMethod(LinkMovementMethod.getInstance());
    }



}
