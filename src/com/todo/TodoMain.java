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
		String choice = " " ;
		TodoUtil.loadList(l, "todolist.txt") ;
		Menu.displaymenu();
		do {
	
			isList = false;
			Menu.prompt() ;
			choice = sc.next();
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
				
			case "ls_date_desc":
				System.out.println("항목이 입력된 역순서대로 정렬합니다.") ;
				l.sortByDateDesc();
				isList = true;
				break;
				
			case "ls_cat":
				System.out.println("카테고리를 출력합니다.") ;
				l.sortByCat() ;
				break;

			case "exit":
				quit = true;
				break;
				
			case "help" :
				Menu.displaymenu();
				break ;
			case "find" :
				String keyword = sc.next();
				TodoUtil.find(l, keyword);
				break ;
				
			case "find_cat" :
				String find_cat_keyword = sc.next();
				TodoUtil.find_cat(l, find_cat_keyword);
				break ;
			default:
				System.out.println("잘못 입력하셨습니다! 관리 명령어 사용법이 필요하시면 help를 입력해주세요");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt") ;
		sc.close() ;
	}
}

