package com.todo.service;
 
import java.util.*;
import java.io.* ;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
 
public class TodoUtil {
	public static void createItem(TodoList list) { //2번 수정 완료
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		  
		System.out.println("\nadd(항목 추가)를 선택하셨습니다!");
		System.out.print("항목 카테고리를 입력해주세요 > ");
		category = sc.next();
		System.out.print("항목 이름을 입력해주세요 > ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("이미 존재하는 항목입니다!\n\n");

			return ;
		}
		sc.nextLine();
		System.out.print("내용을 입력해주세요 > ");
		desc = sc.nextLine();
		
		System.out.print("마감기한을 입력해주세요 > ");
		due_date = sc.nextLine();
		TodoItem t = new TodoItem(title, desc, category, due_date);
		list.addItem(t);
		System.out.println(list.indexOf(t)+1 + ". [" + t.getCategory() + "] " + t.getTitle() + " - " + t.getDesc() + " - " + t.getDue_date() + " - " + t.getCurrent_date());
		System.out.println("**항목 추가 완료**\n");

	}

	public static void deleteItem(TodoList l) { //2번 수정 완료
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\ndel(항목 삭제)를 선택하셨습니다!");
		System.out.print("삭제할 항목 번호를 입력해주세요 > ");
		int number = sc.nextInt() ;

		int count = 1 ;
		for (TodoItem item : l.getList()) {
			if (count == number) {
				System.out.println(number + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				break ;
			}
			count++ ;
		}
		System.out.print("위 항목을 삭제하시겠습니까? (y/n) > ") ;
		String yesOrno = sc.next() ; 
		if(yesOrno.equals("y")) {
			int count2 = 1 ;
			for (TodoItem item : l.getList()) {
				if (count2 == number) {
					l.deleteItem(item);
					System.out.println("**항목 삭제 완료**\n");
					break;
				}
				count2++ ;
			}
			
		}
		else {
			System.out.println("**항목 삭제를 취소하였습니다\n") ;
		}

	}


	public static void updateItem(TodoList l) { //2번 수정 완료
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nedit(항목 수정)를 선택하셨습니다!");
		System.out.print("수정할 항목 번호를 입력해주세요 > ");
		int number = sc.nextInt();
		int count = 1 ;
		for (TodoItem item : l.getList()) {
			if (count == number) {
				System.out.println(number + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			}	
			count++ ;
		}
		
		System.out.print("새로운 항목 카테고리를 입력해주세요 > ");
		String new_category = sc.next().trim();
		sc.nextLine() ;
		
		System.out.print("새로운 항목 이름을 입력해주세요 > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("이미 존재하는 항목입니다!\n\n");
			return ;
		}
		sc.nextLine() ;
		System.out.print("새 항목의 내용을 입력해주세요 > ");
		String new_description = sc.nextLine().trim();

		System.out.print("새 항목의 마감기한을 입력해주세요 > ");
		String new_due_date = sc.nextLine().trim();
		int i=1 ; //수정할 항목 번호를 찾기 위한 인덱스
		for (TodoItem item : l.getList()) {
			if(i==number) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("**기존의 항목이 새로운 항목으로 수정되었습니다**\n");
			}
			i++ ;
		}

	}

	public static void listAll(TodoList l) { //1번 갯수 표현 완료, 2번 카테고리 추가해서 출력 완료
		int count=0 ;
		for (TodoItem item : l.getList()) {
			count++ ;
		}
		System.out.println("\n\n<< 전체 목록 , 총 " + count +"개 >>");
		int i=0 ; //일련번호 출력을 위한 카운팅
		for (TodoItem item : l.getList()) {
			i++ ;
			System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
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
	         //BufferedReader br = new BufferedReader(new FileReader(filename)) ; is okay
	         
	         String line ;
	         while((line = br.readLine()) != null) {
	            StringTokenizer st = new StringTokenizer(line, "##") ;
	            String category = st.nextToken() ;
	            String title = st.nextToken() ;
	            String desc = st.nextToken() ;
	            String due_date = st.nextToken() ;
	            String current_date = st.nextToken() ;
	            TodoItem t = new TodoItem(title, desc, category, due_date) ;
	            t.setCurrent_date(current_date);
	            l.addItem(t) ;
	         }
	         br.close();
	         System.out.println("todolist.txt로부터 기존의 데이타를 로드했습니다.") ;
	      }catch(FileNotFoundException e) {
	         System.out.println(filename + "파일이 없습니다.") ;
	         e.printStackTrace() ;
	      }catch (IOException e ) {
		    	e.printStackTrace() ;
		   }

		
	}

	public static void find(TodoList l, String keyword) {
		int count = 0 ; //키워드로 찾아진 항목의 갯수 카운팅
		int i=0 ; //일련번호 출력을 위한 카운팅
		for (TodoItem item : l.getList()) {
			i++ ;
			int title = 0 ; //제목이 키워드를 포함하고 있지않은 상태
			int desc = 0 ; //내용이 키워드를 포함하고 있지 않은 상태
			//제목이 키워드를 포함하고 있거나
			if(item.getTitle().contains(keyword))
				title = 1 ;
			if(item.getDesc().contains(keyword))
				desc = 1 ;
			if(title != 0 || desc != 0) {
				System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				count++ ;
			}
			
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n\n", count) ;
	}

	public static void find_cat(TodoList l, String find_cat_keyword) {
		int count = 0 ; //키워드로 찾아진 항목의 갯수 카운팅
		int i=0 ; //일련번호 출력을 위한 카운팅
		for (TodoItem item : l.getList()) {
			i++ ;
			if(item.getCategory().contains(find_cat_keyword)) {
				System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				count++ ;
			}
			
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n\n", count) ;
		
	}
}
