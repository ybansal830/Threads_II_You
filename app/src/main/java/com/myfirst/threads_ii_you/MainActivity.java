package com.myfirst.threads_ii_you;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private Button mButton;
    private TextView mTextView;
    private final String PATH = "com.myfirst.threads_ii_you";

    private SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(PATH, Context.MODE_PRIVATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mEditText = findViewById(R.id.editText);
        mButton = findViewById(R.id.button);
        mTextView = findViewById(R.id.textView);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask.execute();
            }
        });
    }

    private AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {

        @Override
        protected Void doInBackground(Void... voids) {
            SharedPreferences.Editor edit = getSharedPreference(MainActivity.this).edit();
            edit.putString("name", mEditText.getText().toString());
            edit.apply();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            mTextView.setText(getSharedPreference(MainActivity.this).getString("name", null));
        }
    };
}