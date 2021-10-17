package com.todo.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class Regular {

	public static void event(TodoList l) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		System.out.print("\n========== �������� ���\n����Ϸ��� �׸��� ��ȣ(id)�� �Է��Ͻʽÿ�\n>");
		Scanner s = new Scanner(System.in);
		int num = s.nextInt();
		System.out.print("���� �ݺ� ������ �Է��Ͻÿ�(M:��, Y:��)\n>");
		String choice = s.next();
		System.out.print("�ݺ� Ƚ���� �Է��Ͻÿ�.\n>");
		int re = s.nextInt();
		TodoItem t = l.regularItem(num).get(0);
		
		if(choice.equals("Y")) {	// ���� ���
			for(int i=1;i<re;i++) {
				int due = Integer.parseInt(t.getDue_date()) + 10000;
				String new_due_date = Integer.toString(due);
				t.setTitle(t.getTitle()+"'");
				t.setDue_date(new_due_date);
				l.addItem(t);
			}
			System.out.println(num+"�� �׸��� "+re+"ȸ ����Ǵ� �������� ��ϵǾ����ϴ�.\n");
		}
		else if(choice.equals("M")) {	// ���� ���
			for(int i=1;i<re;i++) {
				int due = Integer.parseInt(t.getDue_date()) + 100;
				if((due/100)%100 >12) due = due-1200+10000;
				String new_due_date = Integer.toString(due);
				t.setDue_date(new_due_date);
				t.setTitle(t.getTitle()+"'");
				l.addItem(t);
			}
			System.out.println(num+"�� �׸��� "+re+"ȸ ����Ǵ� �������� ��ϵǾ����ϴ�.\n");
		}
		else System.out.println("���õ� ����(M/Y/x)�� �ùٸ��� �Է����� �ʾҽ��ϴ�.\n");
	}

}
