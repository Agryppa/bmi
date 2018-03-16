package com.example.herud.bmi;
/**
 * Created by Herud on 2018-03-10.
 */

public class BmiObj {
    private double mass;
    private double height;
    boolean isMetric;
    public BmiObj(double m, double h, boolean isM)
    {
        mass=m;
        height=h;
        isMetric=isM;
    }
    public double calculate()
    {
        if(isMetric)
            return calcMetric();
        else
            return calcImperial();


    }
    private double calcMetric()
    {
        if(!(checkH()&&checkM())) {
            return 0;
        }

        return mass/height/height;

    }
    private double calcImperial()
    {


        return mass/height/height*703;

    }
    public boolean checkH()
    {
        if(height<1||height>2.5)
            throw new IllegalArgumentException("wrong height");
        return true;
    }
    public boolean checkM()
    {
        if(mass<10||mass>500)
            throw new IllegalArgumentException("wrong mass");
        return true;
    }


}
