package com.apkglobal.voicetext;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

@TargetApi(Build.VERSION_CODES.DONUT)
@RequiresApi(api = Build.VERSION_CODES.DONUT)
public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{
    Button btn_bolo,btn_likho;
    TextView tv_dekho;
    int pid=1;
    EditText et_likho;
TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_dekho=(TextView)findViewById(R.id.tv_dekho);
        et_likho=(EditText)findViewById(R.id.et_likho);
        btn_likho=(Button)findViewById(R.id.btn_likho);
        btn_bolo=(Button)findViewById(R.id.btn_bolo);
        tts=new TextToSpeech(this,this);

        btn_likho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likhkersuno();
            }
        });

        btn_bolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // to take the information as voice
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                // allow all language to take as voice
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                // take local language from user side
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                // show some information while clickng
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Bol Bhi Doo Jara");

                //to send the intent with process id to os
                startActivityForResult(intent,pid);

            }
        });

    }

    private void likhkersuno() {
        String bakvass=et_likho.getText().toString();
      //  tts.setLanguage(Locale.ENGLISH);
        tts.speak(bakvass,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==pid)
        {
            if (resultCode==RESULT_OK  && data!=null)
            {
                ArrayList<String> arrayList= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                tv_dekho.setText(arrayList.get(0).toString());
            }
        }
    }

    @Override
    public void onInit(int i) {

    }
}
