package com.datraen.bdosimpletrading;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.*;


public class ListCycle extends AppCompatActivity {
    public static String[] locations = new String[]{"Velia","Toscani Farm","Western Guard Camp",
                                            "Bartali Farm","Marino Farm","Finto Farm",
                                            "Loggia Farm","Balenos Forest","Heidel",
                                            "Moretti Plantation","Costa Farm","Northwestern Gateway",
                                            "Northern Ceinaga","Glish"};
    public static int[] locOrder = new int[]{0,1,0,2,3,2,4,5,6,7,8,9,8,10,8,11,12,13,8,0};

    public static String PREFS_FILE = "BDOST";

    public int curLocInt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cycle);

        SharedPreferences settings = getSharedPreferences(PREFS_FILE, 0);
        curLocInt = settings.getInt("curLocInt",0);
        updateCurrentScreen();
    }
    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_FILE,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("curLocInt",curLocInt);
        editor.commit();
    }
    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
        state.putInt("curLocInt", curLocInt);
    }
    @Override
    public void onRestoreInstanceState(Bundle state){
        super.onRestoreInstanceState(state);
        curLocInt = state.getInt("curLocInt");
        updateCurrentScreen();
    }
    public void updateCurrentScreen() {
        TextView Textfill = (TextView) this.findViewById(R.id.editText);
        Textfill.setText(locations[locOrder[curLocInt]]);
        Textfill = (TextView) this.findViewById(R.id.editText2);
        Textfill.setText(locations[locOrder[curLocInt+1]]);
    }
    public void onNextPress(View view){
        if(curLocInt == 18){
        curLocInt = 0;
        }
        else{
        ++curLocInt;
        }
        updateCurrentScreen();

    }
    public void onResetPress(View view) {
        curLocInt = 0;
        updateCurrentScreen();
    }
}
