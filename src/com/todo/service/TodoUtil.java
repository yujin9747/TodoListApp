package com.todo.service;
 
import java.util.*;
import java.io.* ;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
 
public class TodoUtil {
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		  
		System.out.println("\nadd(항목 추가)를 선택하셨습니다!");
		System.out.print("항목 이름을 입력해주세요 > ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 존재하는 항목입니다!\n\n");

			return ;
		}
		sc.nextLine();
		System.out.print("내용을 입력해주세요 > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("**항목 추가 완료**\n");

	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\ndel(항목 삭제)를 선택하셨습니다!");
		System.out.print("삭제할 항목 이름을 입력해주세요 > ");
		String title = sc.next();
		
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("**항목 삭제 완료**\n");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nedit(항목 수정)를 선택하셨습니다!");
		System.out.print("수정할 항목 이름을 입력해주세요 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("존재하지 않는 항목입니다!\n\n");
			sc.close();
			return;
		}

		System.out.print("새로운 항목 이름을 입력해주세요 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 존재하는 항목입니다!\n\n");
			sc.close();
			return;
		}
		sc.nextLine() ;
		System.out.print("새 항목의 내용을 입력해주세요 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("**기존의 항목이 새로운 항목으로 수정되었습니다**\n");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n\n<< 전체 목록 >>");
		for (TodoItem item : l.getList()) {
			System.out.println("항목 : " + item.getTitle() + "  내용 :  " + item.getDesc());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		
	    try {
			Writer w = new FileWriter(filename) ;
	    	for (TodoItem myitem : l.getList()) {
				w.write(myitem.toSaveString());
			}
	    	w.close();
	    }catch (FileNotFoundException e){
	    	e.printStackTrace() ;
	    } catch (IOException e ) {
	    	e.printStackTrace() ;
	    }
	    System.out.println("todolist.txt에 저장되었습니다.") ;
	  
	} 
	
	public static void loadList(TodoList l, String filename)  {
		try {
	         FileReader fin = new FileReader(filename) ;
	         BufferedReader br = new BufferedReader(fin) ;
	         
	         String line ;
	         while((line = br.readLine()) != null) {
	            StringTokenizer st = new StringTokenizer(line, "##") ;
	            String title = st.nextToken() ;
	            String desc = st.nextToken() ;
	            
	            TodoItem t = new TodoItem(title, desc) ;
	            l.addItem(t) ;
	         }
	         System.out.println("todolist.txt로부터 기존의 데이타를 로드했습니다.") ;
	      }catch(FileNotFoundException e) {
	         System.out.println("todolist.txt파일이 없습니다.") ;
	         e.printStackTrace() ;
	      }catch (IOException e ) {
		    	e.printStackTrace() ;
		   }

		
	}
}
