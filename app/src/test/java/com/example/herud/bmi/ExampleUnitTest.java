package com.example.herud.bmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void for_valid_data_BMI_is_returned_correctly() {
        BmiObj bmi = new BmiObj(60, 1.70, true);
        double bmiVal = bmi.calculate();
        assertEquals(20.761, bmiVal, 0.001);
    }
    @Test
    public void for_valid_data_CATEGORY_is_returned_correctly() {
        BmiObj bmi = new BmiObj(60, 1.70, true);
        BmiCategory category=bmi.getCategory();
        assertEquals(BmiCategory.NORMAL, category);
    }
    @Test(expected = IllegalArgumentException.class)
    public void arguments_equal_0()throws Exception
    {
        BmiObj bmi=new BmiObj(0, 0, true);
        bmi.calculate();
    }
    @Test
    public void for_valid_imperial_data_BMI_is_returned_correctly() {
        BmiObj bmi = new BmiObj(150, 80, false);
        double bmiVal = bmi.calculate();
        assertEquals(16.476, bmiVal, 0.001);
    }
    @Test(expected = IllegalArgumentException.class)
    public void height_less_than_0()throws Exception
    {
        BmiObj bmi=new BmiObj(100, -12, true);
        bmi.calculate();
    }
    @Test(expected = IllegalArgumentException.class)
    public void mass_less_than_0()throws Exception
    {
        BmiObj bmi=new BmiObj(-1, 2, true);
        bmi.calculate();
    }

}