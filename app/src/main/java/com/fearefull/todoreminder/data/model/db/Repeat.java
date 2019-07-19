package com.fearefull.todoreminder.data.model.db;

public enum Repeat {
    ONCE(0, "فقط یک بار"),
    HOURLY(1, "ساعتی"),
    DAILY(2, "روزانه"),
    WEEKLY(3, "هفتگی"),
    MONTHLY(4, "ماهانه"),
    YEARLY(5, "سالانه"),
    CUSTOM(6, "شخصی سازی");

    private final Integer value;
    private final String text;

    Repeat(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static Repeat getRepeat(Integer value){
        for(Repeat repeat : values()){
            if(repeat.value.equals(value)){
                return repeat;
            }
        }
        return null;
    }

    public static Integer getRepeatInt(Repeat repeat){
        if(repeat != null)
            return repeat.value;
        return  null;
    }

    public static int getCount() {
        return values().length;
    }
}
