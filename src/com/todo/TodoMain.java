package com.todo;
  
import java.io.IOException;
import java.util.Scanner;
import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil; 
public class TodoMain {
	public static void start() throws IOException {
		Scanner sc = new Scanner(System.in); 
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt") ;
		Menu.displaymenu();
		do {
	
			isList = false;
			Menu.prompt() ;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("제목을 오름차순으로 정렬합니다.") ;
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("제목을 역순으로 정렬합니다.") ;
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("항목이 입력된 순서대로 정렬합니다.") ;
				l.sortByDate();
				isList = true;
				break;

			case "exit":
				quit = true;
				break;
				
			case "help" :
				Menu.displaymenu();
				break ;
			default:
				System.out.println("잘못 입력하셨습니다! 관리 명령어 사용법이 필요하시면 help를 입력해주세요");
				choice = sc.next();
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt") ;
		sc.close() ;
	}
}
