package pl.rzemla.sharedprefstest;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ChangeDialog extends Dialog {

    private Button saveB;
    private Button cancelB;
    private TextView identifierTV;
    private EditText characteristicNameET;

    private MainActivity.CharacteristicTitleChanged onChanged;
    private HashMap<String,String> map;


    public ChangeDialog(final Context context, final String identifier, final MainActivity.CharacteristicTitleChanged onChanged, final HashMap<String,String> map) {
        super(context);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setContentView(R.layout.dialog_change);

        saveB = findViewById(R.id.button_save);
        cancelB = findViewById(R.id.button_cancel);
        identifierTV = findViewById(R.id.text_view_identifier);
        characteristicNameET = findViewById(R.id.edit_text_characteristic_name);

        this.map = map;
        this.onChanged = onChanged;

        identifierTV.setText(identifier);


        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put(identifier,characteristicNameET.getText().toString());
                dismiss();
                onChanged.onTitleChanged(characteristicNameET.getText().toString());
            }
        });

        cancelB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
