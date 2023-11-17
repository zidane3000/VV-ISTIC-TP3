package fr.istic.vv;

class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { }

    public static boolean isValidDate(int day, int month, int year) { return false; }

    public static boolean isLeapYear(int year) { return false; }

    public Date nextDate() { return null; }

    public Date previousDate() { return null; }

    public int compareTo(Date other) { return 0; }

}