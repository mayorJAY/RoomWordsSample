package com.example.josycom.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import java.util.List;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.josycom.roomwordssample.REPLY";
    public static final String EXTRA_REPLY_ID = "com.example.josycom.roomwordssample.REPLY_ID";
    private EditText mEditWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);

        final Bundle extra = getIntent().getExtras();
        if (extra != null){
            String word = extra.getString(MainActivity.EXTRA_DATA_UPDATE_WORD, "");
            if (!word.isEmpty()){
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();
            }
        }
        final Button saveButton = findViewById(R.id.button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    if (extra != null && extra.containsKey(MainActivity.EXTRA_DATA_ID)){
                        int id  = extra.getInt(MainActivity.EXTRA_DATA_ID, -1);
                        if (id != -1){
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                        }
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
