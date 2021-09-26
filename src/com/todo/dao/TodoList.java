package com.todo.dao;
  
import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByDateDesc;
import com.todo.service.TodoSortByName;
 
public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		int i=1 ;
		for (TodoItem item : list) {
			System.out.println(i + ". [" + item.getCategory() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			i++ ;
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}

	public void sortByDateDesc() {
		Collections.sort(list, new TodoSortByDateDesc());
	}

	public void sortByCat() {
		HashSet<String> hs =  new HashSet<String>() ;
		for (TodoItem item : list) {
			hs.add(item.getCategory()) ;
		}
		Iterator<String> iter = hs.iterator();

		while (iter.hasNext()) {
		    System.out.print(iter.next() + " / ");
		}
		System.out.printf("총 %d개의 카테고리가 있습니다.\n", hs.size()) ;
		
	}
}
