package com.example.scada.hannaidemo.entity;

/**
 * Created by Scada on 2017/10/20.
 */

public class Transformer {
    private int electricity;
    private int load;

    public int getElectricity() {
        return electricity;
    }

    public Transformer setElectricity(int electricity) {
        this.electricity = electricity;
        return this;
    }

    public int getLoad() {
        return load;
    }

    public Transformer setLoad(int load) {
        this.load = load;
        return this;
    }
}
