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
        int newDay = this.day + 1;
        int newMonth = this.month;
        int newYear = this.year;
    
        if (newDay > daysInMonth(this.month, this.year)) {
            newDay = 1;
            newMonth++;
            if (newMonth > 12) {
            newMonth = 1;
            newYear++;
            }
        }
    
        return new Date(newDay, newMonth, newYear);
    }
    
    public Date previousDate() {
        int newDay = this.day - 1;
        int newMonth = this.month;
        int newYear = this.year;
    
        if (newDay < 1) {
            newMonth--;
            if (newMonth < 1) {
                newMonth = 12;
                newYear--;
            }
            newDay = daysInMonth(newMonth, newYear);
        }
    
        return new Date(newDay, newMonth, newYear);
    }
    
    private int daysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
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