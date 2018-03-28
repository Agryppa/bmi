package com.example.herud.bmi;
/**
 * Created by Herud on 2018-03-10.
 */


public class BmiObj {
    private static double UNDERWEIGHTBORDER=18.5;
    private static double OVERWEIGHTBORDER=25;
    private double mass;
    private double height;
    private boolean isMetric;
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
        if(!(checkHImperial()&&checkMImperial())) {
            return 0;
        }

        return mass/height/height*703;

    }
    private boolean checkHImperial()
    {
        if(height<40||height>100)
            throw new IllegalArgumentException("wrong height");
        return true;
    }

    private boolean checkH()
    {
        if(height<1||height>2.5)
            throw new IllegalArgumentException("wrong height");
        return true;
    }
    private boolean checkM()
    {
        if(mass<10||mass>500)
            throw new IllegalArgumentException("wrong mass");
        return true;
    }
    private boolean checkMImperial()
    {
        if(mass<20||mass>1000)
            throw new IllegalArgumentException("wrong mass");
        return true;
    }
    public BmiCategory getCategory()
    {
        double bmi=calculate();
        if(bmi<UNDERWEIGHTBORDER)
            return BmiCategory.UNDERWEIGHT;
        else if(bmi>OVERWEIGHTBORDER)
            return BmiCategory.OVERWEIGHT;
        return BmiCategory.NORMAL;
    }

}
