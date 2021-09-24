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
			return;
		}
		System.out.print("ī�װ��� �Է��Ͻʽÿ�.\n>");
		category = sc.next();
		sc.nextLine();	// �� �ʿ����� ����?
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		desc = sc.nextLine().trim();	// trim �յ� ���� ����
		System.out.print("������ �Է��Ͻʽÿ�(YYYYMMDD)\n>");
		due_date = sc.next();
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("����� �Ϸ�Ǿ����ϴ�.\n");
	}

	
	public static void deleteItem(TodoList l) {
		System.out.print("\n"
				+ "========== ���� �׸� ����\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ�.\n>");
		
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		if (l.size() < num) System.out.println("�ش� �׸��� �������� �ʽ��ϴ�.\n");
//		for (TodoItem item : l.getList()) {
//			if (title.equals(item.getTitle())) {
//				l.deleteItem(item);
//				System.out.println(title+"�׸��� �����Ǿ����ϴ�.\n");
//				break;
//			}
//		}
		else {
			l.deleteItem(num-1);
			System.out.println(num+"�� �׸��� �����Ǿ����ϴ�.\n");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== ���� �׸� ����\n"
				+ "������ �׸� ��ȣ�� �Է��Ͻÿ�.\n>" );
		int num = sc.nextInt();
		if (l.size() < num) {
			System.out.println("�������� �ʴ� �׸��Դϴ�.\n");
			return;
		}
		System.out.print("�ش� �׸񿡼� �����Ϸ��� �̸��� �Է��Ͻÿ�.\n>");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� �ִ� �׸� �̸��Դϴ�.\n");   
			return;
		}
		System.out.print("ī�װ� ���� �Է��Ͻÿ�.\n>");
		String new_category = sc.next();
		sc.nextLine();
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		String new_description = sc.nextLine().trim();
		System.out.print("������ �Է��Ͻÿ�(YYYY/MM/DD).\n>");
		String new_due_date = sc.next();
//		for (TodoItem item : l.getList()) {
//			if (item.getTitle().equals(title)) {
//				l.deleteItem(item);
////				SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
////		        String current_date = f.format(new Date());
////				TodoItem t = new TodoItem(new_title, new_description, current_date);
//				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
//				l.addItem(t);
//				System.out.println("������ �Ϸ�Ǿ����ϴ�.\n");
//			}
//		}
		l.deleteItem(num-1);
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		l.addItem(t);
		System.out.println("������ �Ϸ�Ǿ����ϴ�.\n");
	}
	

	public static void listAll(TodoList l) {
//		System.out.println("\n========== ��� �׸� ���(�� "+l.size()+"��)");
//		int serial_num = 1;
//		for (TodoItem item : l.getList()) {
//			System.out.print(serial_num+". ");
//			System.out.println(item.toString());
//		}
//		System.out.println("");	
		l.listAll();	// TodoList�� listAll�޼ҵ带 Ȱ��ȭ�ϸ� ���� 7���� �� 1�ٷ� ��ü�� �� ����
	}
	
	
	// ���α׷� ���� �� �б� & ���� �� ����
	public static void saveList(TodoList l, String filename) {
		try {
			File file = new File("todolist.txt");
			Writer w = new FileWriter(filename);
			int count=0;
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
				count++;
			}
			w.close();
			System.out.println(count+"���� �׸��� "+filename+"�� ����Ǿ����ϴ�.");
		}  catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
