package com.todo.dao; 
 
import java.text.SimpleDateFormat;
import java.util.Date;
public class TodoItem {
    private String title;
    private String desc;
    private String current_date;

 
    public TodoItem(String title, String desc){
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss") ;
        this.current_date=f.format(new Date()) ;
    }
    
    public String toSaveString() {
    	return title + "##" + desc + "##" + current_date + "\n" ;
    }


	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //current_date를 string으로 다룰 수 있도록 아래의 두 함수의 리턴타입과 메소드 타입을 고쳐주었다
    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
}
