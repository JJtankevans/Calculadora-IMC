package com.vitoroliveira.calculadoraimc;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Imc implements Parcelable {
    private double peso;
    private double altura;

    public Imc(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
    }

    protected Imc(Parcel in) {
        peso = in.readDouble();
        altura = in.readDouble();
    }

    public static final Creator<Imc> CREATOR = new Creator<Imc>() {
        @Override
        public Imc createFromParcel(Parcel in) {
            return new Imc(in);
        }

        @Override
        public Imc[] newArray(int size) {
            return new Imc[size];
        }
    };

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeDouble(peso);
        dest.writeDouble(altura);
    }
}
