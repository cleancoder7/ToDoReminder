package com.fearefull.todoreminder.data.model.other;

import com.fearefull.todoreminder.data.model.db.Repeat;
import com.fearefull.todoreminder.data.model.other.type.RepeatResponseType;

public class RepeatModel {
    private Repeat repeat;
    private int minute;
    private int hour;
    private int dayWeek;
    private int dayMonth;
    private int weekMonth;
    private int weekYear;
    private int month;
    private int year;

    public RepeatModel() {
        reset();
    }

    public Repeat getRepeat() {
        return repeat;
    }

    public void setRepeat(Repeat repeat) {
        this.repeat = repeat;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    public int getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(int dayMonth) {
        this.dayMonth = dayMonth;
    }

    public int getWeekMonth() {
        return weekMonth;
    }

    public void setWeekMonth(int weekMonth) {
        this.weekMonth = weekMonth;
    }

    public int getWeekYear() {
        return weekYear;
    }

    public void setWeekYear(int weekYear) {
        this.weekYear = weekYear;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void reset() {
        repeat = null;
        minute = -1;
        hour = -1;
        dayWeek = -1;
        dayMonth = -1;
        weekMonth = -1;
        weekYear = -1;
        month = -1;
        year = -1;
    }

    public RepeatResponseType isValid() {
        if (repeat == null)
            return RepeatResponseType.NOT_READY;
        if (repeat == Repeat.ONCE)
            return isOnceRepeatValid();
        if (repeat == Repeat.HOURLY)
            return isHourlyRepeatValid();
        if (repeat == Repeat.DAILY)
            return isDailyRepeatValid();
        if (repeat == Repeat.WEEKLY)
            return isWeeklyRepeatValid();
        if (repeat == Repeat.MONTHLY)
            return isMonthlyRepeatValid();
        if (repeat == Repeat.YEARLY)
            return isYearlyRepeatValid();
        return RepeatResponseType.NOT_READY;
    }

    public RepeatResponseType isOnceRepeatValid() {
        if (repeat == Repeat.ONCE && minute != -1 && hour != -1 && dayMonth != -1 && month != -1 && year != -1) {
            PersianDate currentDate = new PersianDate();
            PersianDate checkDate = new PersianDate();
            checkDate.setMinute(minute);
            checkDate.setHour(hour);
            checkDate.setShDay(dayMonth);
            checkDate.setShMonth(month);
            checkDate.setShYear(year);

            if (currentDate.after(checkDate))
                return RepeatResponseType.TRUE;
            checkDate.addDay(1);
            if (currentDate.after(checkDate)) {
                minute = checkDate.getMinute();
                hour = checkDate.getHour();
                dayMonth = checkDate.getShDay();
                month = checkDate.getShMonth();
                year = checkDate.getShYear();
                return RepeatResponseType.TRUE;
            }
            checkDate.addYear(1);
            if (currentDate.after(checkDate)) {
                minute = checkDate.getMinute();
                hour = checkDate.getHour();
                dayMonth = checkDate.getShDay();
                month = checkDate.getShMonth();
                year = checkDate.getShYear();
                return RepeatResponseType.TRUE;
            }
            reset();
            return RepeatResponseType.FALSE;
        }
        return RepeatResponseType.NOT_READY;
    }

    public RepeatResponseType isHourlyRepeatValid() {
        if (repeat == Repeat.HOURLY && minute != -1)
            return RepeatResponseType.TRUE;
        return RepeatResponseType.NOT_READY;
    }

    public RepeatResponseType isDailyRepeatValid() {
        if (repeat == Repeat.DAILY && minute != -1 && hour != -1)
            return RepeatResponseType.TRUE;
        return RepeatResponseType.NOT_READY;
    }

    public RepeatResponseType isWeeklyRepeatValid() {
        if (repeat == Repeat.WEEKLY && minute != -1 && hour != -1 && dayWeek != -1)
            return RepeatResponseType.TRUE;
        return RepeatResponseType.NOT_READY;
    }

    public RepeatResponseType isMonthlyRepeatValid() {
        if (repeat == Repeat.MONTHLY && minute != -1 && hour != -1 && dayMonth != -1)
            return RepeatResponseType.TRUE;
        return RepeatResponseType.NOT_READY;
    }

    public RepeatResponseType isYearlyRepeatValid() {
        if (repeat == Repeat.YEARLY && minute != -1 && hour != -1 && dayMonth != -1 && month != -1)
            return RepeatResponseType.TRUE;
        return RepeatResponseType.NOT_READY;
    }
}