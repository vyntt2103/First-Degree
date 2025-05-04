package com.example.k22411caproject2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText edtCoefficientA;
    EditText edtCoefficientB;
    TextView txtResult;
    Button btnLangVi, btnLangEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        addViews();
        addLanguageButtons();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addViews() {
        edtCoefficientA = findViewById(R.id.edtCoefficientA);
        edtCoefficientB = findViewById(R.id.edtCoefficientB);
        txtResult = findViewById(R.id.txtResult);

    }

    private void addLanguageButtons() {
        btnLangVi = findViewById(R.id.btnLangVi);
        btnLangEn = findViewById(R.id.btnLangEn);

        btnLangVi.setOnClickListener(v -> setLocale("vi"));
        btnLangEn.setOnClickListener(v -> setLocale("en"));
    }

    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // restart activity to apply new language
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void do_solution(View view) {
        //để lấy giá trị hệ số a:
        String hsa=edtCoefficientA.getText().toString();
        double a=Double.parseDouble(hsa);

        //để lấy giá trị hệ số b:
        double b=Double.parseDouble(edtCoefficientB.getText().toString());

        //tiến hành giải và biên luận phương trình:
        if (a==0 && b==0)
        {
            txtResult.setText(getResources().getText(R.string.title_infinity));
        }
        else if (a==0 && b!=0)
        {
            txtResult.setText(getResources().getText(R.string.title_nosolution));
        }
        else
        {
            txtResult.setText("X="+(-b/a));
        }
    }

    public void do_next(View view) {
        edtCoefficientA.setText("");
        edtCoefficientB.setText("");
        txtResult.setText("");
        edtCoefficientA.requestFocus();
    }

    public void do_exit(View view) {
        finish();
    }
}