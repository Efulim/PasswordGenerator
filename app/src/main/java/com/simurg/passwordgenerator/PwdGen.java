package com.simurg.passwordgenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
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

        setDefaults();
    }

    public void generatePassword(View view) {
        if (!spPassLength.getSelectedItem().toString().equals("") &&
                !cbSymbols.isChecked() &&
                !cbNumbers.isChecked() &&
                !cbLowerChars.isChecked() &&
                !cbUpperChars.isChecked()) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.CHECKBOX), Toast.LENGTH_SHORT).show();
        } else {
            // boolean sy, boolean nu, boolean lo, boolean up, boolean si, boolean am, int len
            PasswordGenerator pg = new PasswordGenerator(cbSymbols.isChecked(),
                    cbNumbers.isChecked(), cbLowerChars.isChecked(),
                    cbUpperChars.isChecked(), cbExcludedSimilar.isChecked(),
                    cbExcludedAmbiguous.isChecked(),
                    Integer.valueOf(spPassLength.getSelectedItem().toString()));
            String generatedPassword = getResources().getString(R.string.GENERATED_PASSWORD) + pg.generate();
            tvGeneratedPassword.setText(generatedPassword);
        }
    }

    private ArrayAdapter<String> getSpinnerList() {
        List<String> arrPassLength = new ArrayList<>();
        for (int i = 4; i < 26; i++) {
            arrPassLength.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrAdapterPL = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, arrPassLength);
        arrAdapterPL.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arrAdapterPL;
    }

    private void setDefaults() {
        spPassLength.setSelection(4, true); // 4, 5, 6, 7, 8, 9, 10
        cbSymbols.setChecked(true);
        cbNumbers.setChecked(true);
        cbLowerChars.setChecked(true);
        cbUpperChars.setChecked(true);
        cbExcludedSimilar.setChecked(true);
    }
}
