package com.todo;

import java.util.ArrayList;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.CalendarMaker;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
//		l.importData("todolist.txt");
		boolean quit = false;
		// 3_2 ���� ����
//		TodoUtil.loadList(l, "todolist.txt");			// ?????? �� �ڲ� L�� �ڵ���ȯ����???
		do {
			Menu.prompt();
			String choice = sc.next();
			switch (choice) {
			case "help":
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
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				TodoUtil.listAll(l, "due_date", 0);
				break;
			
			case "find":
				String keyword = sc.next().trim();
				TodoUtil.find(l, keyword);
				break;
				
			case "find_cate":
				String cate_keyword = sc.next().trim();
				TodoUtil.find_cate(l, cate_keyword);
				break;
				
			case "ls_cate":
				TodoUtil.ls_cate(l);
				break;
			
			case "comp":
				String complete = sc.nextLine().trim();
				System.out.println(complete);
				TodoUtil.comp(l, complete);
				break;
				
			case "ls_comp":
				TodoUtil.find_comp(l);
				break;
			
			case "importance":
				int impo_id = sc.nextInt();
				TodoUtil.importance(l, impo_id);
				break;
			case "ls_importance":
				TodoUtil.listAll(l, "importance", 0);
				break;
			case "mate":
				TodoUtil.mate(l);
				break;
			case "find_mate":
				int mates = sc.nextInt();
				TodoUtil.find_mate(l, mates);
				break;
				
			case "exit":
				quit = true;
				break;
				
			case "calendar":
				CalendarMaker.showMaker(l);
				break;
				
			case "regular":
				Regular.event(l);
				break;

			default:
				System.out.println("������ ��ɾ ����Ͻʽÿ�.\n������ �ʿ��ϴٸ� help ��ɾ �Է��Ͻÿ�.\n");
				break;
			}
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
		sc.close();
	}
}
