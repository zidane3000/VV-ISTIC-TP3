package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @Test
    void testIsValidDate() {
        assertTrue(Date.isValidDate(1, 1, 1));
        assertTrue(Date.isValidDate(31, 12, 9999));
        assertFalse(Date.isValidDate(0, 1, 1));
        assertFalse(Date.isValidDate(1, 0, 1));
        assertFalse(Date.isValidDate(1, 1, 0));
        assertFalse(Date.isValidDate(32, 1, 1));
        assertFalse(Date.isValidDate(1, 13, 1));
        assertFalse(Date.isValidDate(1, 1, -1));
    }

    @Test
    void testIsLeapYear() {
        assertTrue(Date.isLeapYear(4));
        assertTrue(Date.isLeapYear(400));
        assertTrue(Date.isLeapYear(2000));
        assertFalse(Date.isLeapYear(100));
        assertFalse(Date.isLeapYear(200));
        assertFalse(Date.isLeapYear(300));
        assertFalse(Date.isLeapYear(500));
    }

    @Test
    void testNextDate() {
        Date date = new Date(1, 1, 1);
        Date nextDate = date.nextDate();
        assertEquals(2, nextDate.getDay());

    }

    @Test
    void testPreviousDate() {
        Date date = new Date(2, 1, 1);
        Date previousDate = date.previousDate();
        assertEquals(1, previousDate.getDay());
       }

    @Test
    void testCompareToSupDate() {
        Date date1 = new Date(1,1,2001);
        Date date2 = date1.nextDate();
        assertEquals(-1, date1.compareTo(date2));

        Date date3 = new Date(1,2,2001);
        assertEquals(-1, date1.compareTo(date3));

        Date date4 = new Date(1,1,2002);
        assertEquals(-1, date1.compareTo(date4));


    }

    @Test
    void testCompareToInfDate() {
        Date date1 = new Date(2,2,2002);
        Date date2 = date1.previousDate();
        assertEquals(1, date1.compareTo(date2));

        Date date3 = new Date(2,1,2002);
        assertEquals(1, date1.compareTo(date3));

        Date date4 = new Date(2,2,2001);
        assertEquals(1, date1.compareTo(date4));
    }


    

    @Test
    void testDateConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Date(0, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 0, 1));
        assertThrows(IllegalArgumentException.class, () -> new Date(32, 1, 1));
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 13, 1));
        assertThrows(IllegalArgumentException.class, () -> new Date(1, 1, -1));
    }

    
    @Test
    void testNextDateEndOfMonth() {
        Date date = new Date(31, 1, 2021);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(2, nextDate.getMonth());
        assertEquals(2021, nextDate.getYear());
    }

    @Test
    void testNextDateEndOfYear() {
        Date date = new Date(31, 12, 2021);
        Date nextDate = date.nextDate();
        assertEquals(1, nextDate.getDay());
        assertEquals(1, nextDate.getMonth());
        assertEquals(2022, nextDate.getYear());
    }

    @Test
    void testPreviousDateStartOfMonth() {
        Date date = new Date(1, 2, 2021);
        Date previousDate = date.previousDate();
        assertEquals(31, previousDate.getDay());
        assertEquals(1, previousDate.getMonth());
        assertEquals(2021, previousDate.getYear());

        // Test for June
    date = new Date(1, 7, 2021);
    previousDate = date.previousDate();
    assertEquals(30, previousDate.getDay());
    assertEquals(6, previousDate.getMonth());
    assertEquals(2021, previousDate.getYear());

    }

    @Test
    void testPreviousDateStartOfYear() {
        Date date = new Date(1, 1, 2021);
        Date previousDate = date.previousDate();
        assertEquals(31, previousDate.getDay());
        assertEquals(12, previousDate.getMonth());
        assertEquals(2020, previousDate.getYear());
    }

    @Test
    void testCompareToEqualDate() {
        Date date1 = new Date(1, 1, 2021);
        Date date2 = new Date(1, 1, 2021);
        assertEquals(0, date1.compareTo(date2));
    }

@Test
void testDaysInMonth() {
    // Test for January
    Date date = new Date(31, 1, 2021);
    Date nextDate = date.nextDate();
    assertEquals(1, nextDate.getDay());
    assertEquals(2, nextDate.getMonth());
    assertEquals(2021, nextDate.getYear());

    // Test for February in a leap year
    date = new Date(28, 2, 2020);
    nextDate = date.nextDate();
    assertEquals(29, nextDate.getDay());
    assertEquals(2, nextDate.getMonth());
    assertEquals(2020, nextDate.getYear());

    date = new Date(29, 2, 2020);
    nextDate = date.nextDate();
    assertEquals(1, nextDate.getDay());
    assertEquals(3, nextDate.getMonth());
    assertEquals(2020, nextDate.getYear());

    // Test for February in a non-leap year
    date = new Date(28, 2, 2021);
    nextDate = date.nextDate();
    assertEquals(1, nextDate.getDay());
    assertEquals(3, nextDate.getMonth());
    assertEquals(2021, nextDate.getYear());

    // Test for April
    date = new Date(30, 4, 2021);
    nextDate = date.nextDate();
    assertEquals(1, nextDate.getDay());
    assertEquals(5, nextDate.getMonth());
    assertEquals(2021, nextDate.getYear());

    // Test for December
    date = new Date(31, 12, 2021);
    nextDate = date.nextDate();
    assertEquals(1, nextDate.getDay());
    assertEquals(1, nextDate.getMonth());
    assertEquals(2022, nextDate.getYear());
    
}
}