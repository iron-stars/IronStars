package com.xekr.ironstars.energy;

public class Energy {
    private int generationEfficiency;
    private int powerEfficiency;
    private int voltage;
    private double voltageRatio;

    private Energy() {
        super();
    }

    public void add(int amount) {
        this.generationEfficiency += amount;
    }

    public void reduce(int amount) {
        this.powerEfficiency += amount;
    }

    public double getVoltageRatio() {
        this.voltageRatio = this.generationEfficiency;
        this.voltageRatio /= this.powerEfficiency;
//        if (this.voltageRatio >= 1) {
//            this.voltageRatio = 1.0;
//        } else if (this.voltageRatio >= 0.5) {
//            this.voltageRatio = 0.5;
//        } else if (this.voltageRatio >= 0.25) {
//            this.voltageRatio = 0.25;
//        } else if (this.voltageRatio >= 0.125) {
//            this.voltageRatio = 0.125;
//        } else if (this.voltageRatio >= 0.0625) {
//            this.voltageRatio = 0.0625;
//        } else if (this.voltageRatio >= 0.03125) {
//            this.voltageRatio = 0.03125;
//        } else if (this.voltageRatio >= 0.015625) {
//            this.voltageRatio = 0.015625;
//        } else if (this.voltageRatio >= 0.0078125) {
//            this.voltageRatio = 0.0078125;
//        } else {
//            this.voltageRatio = 0;
//        }
        return this.voltageRatio;
    }

    public int getVoltage() {
        this.voltage = (int) (128 * this.getVoltageRatio());
        return this.voltage;
    }
}
