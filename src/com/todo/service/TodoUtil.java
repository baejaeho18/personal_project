package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		System.out.print("\n========== �ű� �׸� �ۼ�\n�̸��� �Է��Ͻʽÿ�.\n>");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("�̹� �ִ� �׸� �̸��Դϴ�.\n");
			sc.close();
			return;
		}
		System.out.print("ī�װ��� �Է��Ͻʽÿ�.\n>");
		category = sc.next();
		sc.nextLine();	// �� �ʿ����� ����?
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		desc = sc.nextLine().trim();	// trim �յ� ���� ����
		System.out.print("������ �Է��Ͻʽÿ�(YYYYMMDD).\n>");
		due_date = sc.next();
		TodoItem t = new TodoItem(category, title, desc, due_date);
		if(list.addItem(t) > 0) System.out.println("����� �Ϸ�Ǿ����ϴ�.\n");
		sc.close();
	}

	public static void deleteItem(TodoList l) {
		System.out.print("\n========== ���� �׸� ����\n������ �׸��� ��ȣ�� �Է��Ͻÿ�.\n>");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		if (l.getCount() < id) {
			System.out.println("�ش� �׸��� �������� �ʽ��ϴ�.\n");
			sc.close();
			return;
		}
		if(l.deleteItem(id)>0) System.out.println(id+"�� �׸��� �����Ǿ����ϴ�.\n");	// id�� db�Ϸù�ȣ�� 1���� ����
		sc.close();
	}

	public static void updateItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n========== ���� �׸� ����\n������ �׸� ��ȣ�� �Է��Ͻÿ�.\n>" );
		int id = sc.nextInt();
		for (TodoItem item : l.getList()) {
			int cnt=0;
			if (item.getId() != id) cnt++;
			if(l.getCount() == cnt) {
				System.out.println("�������� �ʴ� �׸��Դϴ�.\n");
				sc.close();
				return;
			}
		}
		System.out.print("�ش� �׸񿡼� �����Ϸ��� �̸��� �Է��Ͻÿ�.\n>");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� �ִ� �׸� �̸��Դϴ�.\n");   
			sc.close();
			return;
		}
		System.out.print("ī�װ� ���� �Է��Ͻÿ�.\n>");
		String new_category = sc.next();
		sc.nextLine();
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		String new_description = sc.nextLine().trim();
		System.out.print("������ �Է��Ͻÿ�(YYYYMMDD).\n>");
		String new_due_date = sc.next();
		l.deleteItem(id);
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		if(l.updateItem(t) > 0) System.out.println("������ �Ϸ�Ǿ����ϴ�.\n");
		sc.close();
	}
	
	public static void listAll(TodoList l) {
		System.out.println("\n========== ��� �׸� ���(�� "+l.getCount()+"��)\n");
//		int serial_num = 1;
		for (TodoItem item : l.getList()) {
			System.out.print(item.toString());
//			l.listAll(item);
		}
		System.out.println("");
	}
	public static void listAll(TodoList l, String orderby, int odering) {
		System.out.println("\n========== ��� �׸� ���(�� "+l.getCount()+"��)\n");
		for (TodoItem item : l.getOrderedList(orderby, odering)) {
			System.out.print(item.toString());
		}
		System.out.println("");
	}
	

	public static void find(TodoList l, String keyword) {
		System.out.print("\n========== ���� ���� �˻�\n");
		int count=0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.println("�˻� ��� �� " + count + "��.\n");
	}
	
	public static void find_cate(TodoList l, String cate_keyword) {
		System.out.print("\n========== ī�װ� �˻�\n");
		int count =0;
		for (TodoItem item : l.getCategory(cate_keyword)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.println("\n�˻� ��� �� "+ count + "��.\n");
	}
	
	public static void ls_cate(TodoList l) {
		System.out.print("\n========== ī�װ� ����\n");
		int count =0;
		for (String item : l.getCategories()) {
			System.out.println(item + " ");
			count ++;
		}
		System.out.println("\n�� "+ count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n");
	}
		
	
	
	
//	 // ���α׷� ���� �� �б� & ���� �� ����
//	public static void saveList(TodoList l, String filename) {
//		try {
//			//File file = new File("todolist.txt");
//			Writer w = new FileWriter(filename);
//			int count=0;
//			for (TodoItem item : l.getList()) {
//				w.write(item.toSaveString());
//				count++;
//			}
//			w.close();
//			System.out.println(count+"���� �׸��� "+filename+"�� ����Ǿ����ϴ�.");
//		}  catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}  catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String items;
			int count=0;
			while( (items = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(items, "##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				TodoItem t = new TodoItem(category, title, desc, due_date);
				t.setCurrent_date(current_date);
				l.addItem(t);
				count++;
//				System.out.println(title+desc+current_date);
			}
			br.close();
			System.out.println(count+"���� �׸��� "+filename+"���� ����Ǿ����ϴ�.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
