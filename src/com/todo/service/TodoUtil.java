package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== �ű� �׸� �ۼ�\n"
				+ "�׸� �̸��� �Է��Ͻʽÿ�.\n>");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("�̹� �ִ� �׸� �̸��Դϴ�.");
			return;
		}
		sc.nextLine();	// �� �ʿ����� ����?
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("����� �Ϸ�Ǿ����ϴ�.\n");
	}

	
	public static void deleteItem(TodoList l) {
		System.out.print("\n"
				+ "========== ���� �׸� ����\n"
				+ "������ �׸��� �̸��� �Է��Ͻÿ�.\n>" );
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		if (!l.isDuplicate(title)) System.out.println("�ش� �׸��� �������� �ʽ��ϴ�.\n");
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println(title+"�׸��� �����Ǿ����ϴ�.\n");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "========== ���� �׸� ����\n"
				+ "������ �׸� �̸��� �Է��Ͻÿ�.\n>" );
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("�������� �ʴ� �׸��Դϴ�.\n");
			return;
		}

		System.out.print("�ش� �׸񿡼� �����Ϸ��� �̸��� �Է��Ͻÿ�.\n>");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� �ִ� �׸� �̸��Դϴ�.\n");
			return;
		}
		sc.nextLine();
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("������ �Ϸ�Ǿ����ϴ�.\n");
			}
		}

	}
	

	public static void listAll(TodoList l) {
		System.out.println("\n========== ��� �׸� ���");
		for (TodoItem item : l.getList()) {
			System.out.println("[" + item.getTitle() + "]\t" + item.getDesc());
		}
		System.out.println("");
	}
}
