package com.example.lenovo.attendance;

public class ListItem {
    private String Subcode, Sub,name,attendance;



    public ListItem(String Subcode, String Sub, String name, String attendance) {
        this.Subcode = Subcode;
        this.Sub = Sub;
        this.name = name;
        this.attendance=attendance;
    }

    public String getSubcode() {
        return Subcode;
    }

    public void setSubcode(String subcode) {
        this.Subcode = subcode;
    }

    public String getSub() {
        return Sub;
    }

    public void setSub(String sub) {
        this.Sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String id) {
        this.name = id;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }
}
