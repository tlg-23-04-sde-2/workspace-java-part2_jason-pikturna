package com.entertainment;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TelevisionTest {
    private Television tv;
    private Television tv1;
    private Television tv2;
    @Before
    public void setUp() {
        tv = new Television();
        tv1 = new Television("Sony", 50, DisplayType.OLED);
        tv2 = new Television("Sony", 50, DisplayType.OLED);

    }

    @Test
    public void compareTo_shouldReturnTrue_differentBrand() {
        tv2.setBrand("Toshiba");
        assertNotEquals(0, tv1.getBrand().compareTo(tv2.getBrand()));
    }

    @Test
    public void compareTo_shouldReturnTrue_sameBrand() {
        assertEquals(0,tv1.getBrand().compareTo(tv2.getBrand()));
    }

    @Test
    public void equals_shouldReturnFalse_differentBrand_sameVolume_sameDisplay() {
        tv2.setBrand("Samsung");
        assertNotEquals(tv1,tv2);
    }

    @Test
    public void equals_shouldReturnFalse_sameBrand_differentVolume_sameDisplay() {
        tv2.setVolume(60);
        assertNotEquals(tv1,tv2);
    }

    @Test
    public void equals_shouldReturnFalse_sameBrand_sameVolume_differentDisplay() {
        tv2.setDisplay(DisplayType.LED);
        assertNotEquals(tv1,tv2);
    }

    @Test
    public void equals_shouldReturnTrue_allPropertiesSame() {
        assertEquals(tv1,tv2);
    }

    @Test
    public void hashCode_shouldReturnSameValue_equalObjects() {
        assertEquals(tv1.hashCode(),tv2.hashCode());
    }

    @Test(expected=InvalidChannelException.class)
    public void changeChannel_shouldThrowInvalidChannelException_whenInvalid_upperBound() throws InvalidChannelException {
        tv.changeChannel(1000);
    }

    @Test(expected=InvalidChannelException.class)
    public void changeChannel_shouldThrowInvalidChannelException_whenInvalid_lowerBound()
            throws InvalidChannelException{
        tv.changeChannel(0);
    }

    @Test
    public void changeChannel_shouldStoreChannel_whenValid_upperBound() throws InvalidChannelException {
        tv.changeChannel(999);
        assertEquals(999, tv.getCurrentChannel());
    }

    @Test
    public void changeChannel_shouldStoreChannel_whenValid_lowerBound()
    throws InvalidChannelException {
        tv.changeChannel(1);
        assertEquals(1,tv.getCurrentChannel());
    }

    @Test(expected=IllegalArgumentException.class)
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalid_lowerBound() {
        tv.setVolume(-1);  // just trigger the exception and "punt"
    }

    @Test
    public void setVolume_shouldThrowIllegalArgumentException_whenInvalid_upperBound() {
      try {
          tv.setVolume(101);    // this should trigger the exception
          fail("Should have thrown IllegalArgumentException");
      }
      catch (IllegalArgumentException e) {
          assertEquals("Invalid volume: 101. Allowed range: [0,100].", e.getMessage());
      }
    }

    @Test
    public void setVolume_shouldStoreVolume_whenValid_upperBound() {
        tv.setVolume(100);
        assertEquals(100, tv.getVolume());
    }

    @Test
    public void setVolume_shouldStoreValue_whenValid_lowerBound() {
        tv.setVolume(0);

        assertEquals(0, tv.getVolume());
    }
}