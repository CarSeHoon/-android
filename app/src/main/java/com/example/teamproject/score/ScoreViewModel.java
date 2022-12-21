package com.example.teamproject.score;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

public class ScoreViewModel extends ViewModel {

    public MutableLiveData<String> par1 = new MutableLiveData<>();
    public MutableLiveData<String> par2 = new MutableLiveData<>();
    public MutableLiveData<String> par3 = new MutableLiveData<>();
    public MutableLiveData<String> par4 = new MutableLiveData<>();
    public MutableLiveData<String> par5 = new MutableLiveData<>();
    public MutableLiveData<String> par6 = new MutableLiveData<>();
    public MutableLiveData<String> par7 = new MutableLiveData<>();
    public MutableLiveData<String> par8 = new MutableLiveData<>();
    public MutableLiveData<String> par9 = new MutableLiveData<>();

    public MutableLiveData<String> total1 = new MutableLiveData<>();


    public MutableLiveData<String> par10 = new MutableLiveData<>();
    public MutableLiveData<String> par11 = new MutableLiveData<>();
    public MutableLiveData<String> par12 = new MutableLiveData<>();
    public MutableLiveData<String> par13 = new MutableLiveData<>();
    public MutableLiveData<String> par14 = new MutableLiveData<>();
    public MutableLiveData<String> par15 = new MutableLiveData<>();
    public MutableLiveData<String> par16 = new MutableLiveData<>();
    public MutableLiveData<String> par17 = new MutableLiveData<>();
    public MutableLiveData<String> par18 = new MutableLiveData<>();


    public MutableLiveData<String> total2 = new MutableLiveData<>();

    private int getIntValue(LiveData<String> livedata) {
        try {
            if (livedata.getValue() != null) {
                return Integer.parseInt(livedata.getValue());
            }

            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    public void total1() {
        int total = getIntValue(par1) +
                getIntValue(par2) +
                getIntValue(par3) +
                getIntValue(par4) +
                getIntValue(par5) +
                getIntValue(par6) +
                getIntValue(par7) +
                getIntValue(par8) +
                getIntValue(par9);
        Log.d("ScoreViewModel", "total" + total);
        total1.setValue(String.valueOf(total));
    }

    public void total2() {
        int score1 = getIntValue(par1) +
                getIntValue(par2) +
                getIntValue(par3) +
                getIntValue(par4) +
                getIntValue(par5) +
                getIntValue(par6) +
                getIntValue(par7) +
                getIntValue(par8) +
                getIntValue(par9);

        int score2 = getIntValue(par10) +
                getIntValue(par11) +
                getIntValue(par12) +
                getIntValue(par13) +
                getIntValue(par14) +
                getIntValue(par15) +
                getIntValue(par16) +
                getIntValue(par17) +
                getIntValue(par18);

        Log.d("ScoreViewModel", "total2" + score1);
        Log.d("ScoreViewModel", "total2" + score2);
        total2.setValue(String.valueOf(score1 + score2));
    }
}
