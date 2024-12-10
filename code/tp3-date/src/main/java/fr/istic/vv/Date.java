package fr.istic.vv;

class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) { 
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }


    public static boolean isValidDate(int day, int month, int year) { 
        if (year < 1) {
            return false;
        }
        else if (month < 1 || month > 12) {
            return false;
        }
        else if (day < 1 || day > 31) {
            return false;
        }
        return true;

     }

    public static boolean isLeapYear(int year) { 
        if (year % 4 != 0) {
            return false;
        }
        else if (year % 100 != 0) {
            return true;
        }
        else if (year % 400 != 0) {
            return false;
        }
        return true;
     }

    public Date nextDate() { 
        return new Date(this.day + 1, this.month, this.year);
     }

    public Date previousDate() { 
        return new Date(this.day - 1, this.month, this.year);
     }

    public int compareTo(Date other) { 
        if (this.year < other.year) {
            return -1;
        }
        else if (this.year > other.year) {
            return 1;
        }
        else if (this.month < other.month) {
            return -1;
        }
        else if (this.month > other.month) {
            return 1;
        }
        else if (this.day < other.day) {
            return -1;
        }
        else if (this.day > other.day) {
            return 1;
        }
        return 0;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    
}