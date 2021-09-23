package com.todo.menu;
public class Menu {   
    public static void displaymenu()
    {  
        System.out.println();
        System.out.println("TodoList 관리 명령어 사용법");
        System.out.println("add - 항목 추가하기");
        System.out.println("del - 항목 삭제하기 )");
        System.out.println("edit - 항목 수정하기");
        System.out.println("ls - 전체 목록 보기");
        System.out.println("ls_name_asc - 목록을 이름순으로 정렬하기(오름차순)");
        System.out.println("ls_name_desc - 목록을 이름순으로 정렬하기(내림차순)");
        System.out.println("ls_date - 목록을 항목이 추가된 순서대로 정렬하기");
        System.out.println("exit - 종료");
    }
    public static void prompt() {
    	System.out.print("원하시는 메뉴를 입력해주세요 > (도움말 띄우기 : help)") ;
    }
}
