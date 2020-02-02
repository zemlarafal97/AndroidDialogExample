package pl.rzemla.sharedprefstest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView firstTV;
    private TextView secondTV;
    private TextView thirdTV;

    private HashMap<String,String> customCharacNamesMap;
    SharedPrefsUtils sharedPrefsUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstTV = findViewById(R.id.text_view_1);
        secondTV = findViewById(R.id.text_view_2);
        thirdTV = findViewById(R.id.text_view_3);

        sharedPrefsUtils = new SharedPrefsUtils(MainActivity.this);

        customCharacNamesMap = sharedPrefsUtils.getCharacCustomNamesMap();

        firstTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new ChangeDialog(MainActivity.this, firstTV.getText().toString(), new CharacteristicTitleChanged() {
                    @Override
                    public void onTitleChanged(String title) {
                        firstTV.setText(title);
                    }
                },customCharacNamesMap);
                dialog.show();
            }
        });

        secondTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new ChangeDialog(MainActivity.this, secondTV.getText().toString(), new CharacteristicTitleChanged() {
                    @Override
                    public void onTitleChanged(String title) {
                        secondTV.setText(title);
                    }
                },customCharacNamesMap);
                dialog.show();
            }
        });

        thirdTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new ChangeDialog(MainActivity.this, thirdTV.getText().toString(), new CharacteristicTitleChanged() {
                    @Override
                    public void onTitleChanged(String title) {
                        thirdTV.setText(title);
                    }
                },customCharacNamesMap);
                dialog.show();
            }
        });

        if(customCharacNamesMap.containsKey(firstTV.getText().toString())) {
            firstTV.setText(customCharacNamesMap.get(firstTV.getText().toString()));
        }

        if(customCharacNamesMap.containsKey(secondTV.getText().toString())) {
            secondTV.setText(customCharacNamesMap.get(secondTV.getText().toString()));
        }

        if(customCharacNamesMap.containsKey(thirdTV.getText().toString())) {
            thirdTV.setText(customCharacNamesMap.get(thirdTV.getText().toString()));
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
        sharedPrefsUtils.saveCharaCustomNamesMap(customCharacNamesMap);

    }

    public interface CharacteristicTitleChanged {
        void onTitleChanged(String title);
    }
}
