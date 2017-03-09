package com.simurg.passwordgenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PwdGen extends AppCompatActivity {
    Spinner spPassLength;
    CheckBox cbSymbols;
    CheckBox cbNumbers;
    CheckBox cbLowerChars;
    CheckBox cbUpperChars;
    CheckBox cbExcludedSimilar;
    CheckBox cbExcludedAmbiguous;
    TextView tvGeneratedPassword;
    Button btnGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_gen);

        spPassLength = (Spinner) findViewById(R.id.spPassLength);
        spPassLength.setAdapter(getSpinnerList());
        cbSymbols = (CheckBox) findViewById(R.id.cbSymbols);
        cbNumbers = (CheckBox) findViewById(R.id.cbNumbers);
        cbLowerChars = (CheckBox) findViewById(R.id.cbLowerChars);
        cbUpperChars = (CheckBox) findViewById(R.id.cbUpperChars);
        cbExcludedSimilar = (CheckBox) findViewById(R.id.cbExcludedSimilar);
        cbExcludedAmbiguous = (CheckBox) findViewById(R.id.cbExcludedAmbiguous);
        tvGeneratedPassword = (TextView) findViewById(R.id.tvGeneratedPassword);
        btnGenerate = (Button) findViewById(R.id.btnGenerate);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!spPassLength.getSelectedItem().toString().equals("") &&
                        !cbSymbols.isChecked() &&
                        !cbNumbers.isChecked() &&
                        !cbLowerChars.isChecked() &&
                        !cbUpperChars.isChecked()) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.CHECKBOX), Toast.LENGTH_SHORT).show();
                } else {
                    // boolean sy, boolean nu, boolean lo, boolean up, boolean si, boolean am
                    PasswordGenerator pg = new PasswordGenerator(cbSymbols.isChecked(),
                            cbNumbers.isChecked(), cbLowerChars.isChecked(),
                            cbUpperChars.isChecked(), cbExcludedSimilar.isChecked(),
                            cbExcludedAmbiguous.isChecked(),
                            Integer.valueOf(spPassLength.getSelectedItem().toString()));
                    String generatedPassword = getResources().getString(R.string.GENERATED_PASSWORD) + pg.generate();
                    tvGeneratedPassword.setText(generatedPassword);
                }
            }
        });
    }

    private ArrayAdapter<String> getSpinnerList() {
        List<String> arrPassLength = new ArrayList<>();
        for (int i = 4; i < 26; i++) {
            arrPassLength.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrAdapterPL = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrPassLength);
        arrAdapterPL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arrAdapterPL;
    }
}
