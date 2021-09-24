package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		// 3_2 ���� ����
		TodoUtil.loadList(l, "todolist.txt");			// ?????? �� �ڲ� L�� �ڵ���ȯ����???
		do {
//			int i=0;
//			Menu.displaymenu(i);
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {
			case "help":
//				i=1;
//				Menu.displaymenu(i);
				Menu.displaymenu();
				break;

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
				isList = true;
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();	//comparator�� ����ؼ��� ����� ����.
				isList = true;
				break;
			
			case "find":
				TodoUtil.find(l);
				break;
				
			case "find_cate":
				
				break;
				
			case "ls_cate":
				
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("������ ��ɾ ����Ͻʽÿ�.\n������ �ʿ��ϴٸ� help ��ɾ �Է��Ͻÿ�.\n");
				break;
			}
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
