package com.todo.service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		String category, title, desc, due_date;
		Scanner s = new Scanner(System.in);
		System.out.print("\n========== �ű� �׸� �ۼ�\n�̸��� �Է��Ͻʽÿ�.\n>");
		title = s.next();
		if (list.isDuplicate(title)) {
			System.out.printf("�̹� �ִ� �׸� �̸��Դϴ�.\n");
			return;
		}
		System.out.print("ī�װ��� �Է��Ͻʽÿ�.\n>");
		category = s.next();
		s.nextLine();	// �� �ʿ����� ����?
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		desc = s.nextLine().trim();	// trim �յ� ���� ����
		System.out.print("������ �Է��Ͻʽÿ�(YYYYMMDD).\n>");
		due_date = s.next();
		TodoItem t = new TodoItem(category, title, desc, due_date);
		if(list.addItem(t) > 0) System.out.println("����� �Ϸ�Ǿ����ϴ�.\n");
	}
	/// TA���� ����!!!! sc.close() -> s.close() �ȵǴ� ����?

	public static void deleteItem(TodoList l) {
		System.out.print("\n========== ���� �׸� ����\n������ �׸��� ��ȣ�� �Է��Ͻÿ�.\n>");
		Scanner s = new Scanner(System.in);
		int id = s.nextInt();
		if (l.getCount() < id) {
			System.out.println("�ش� �׸��� �������� �ʽ��ϴ�.\n");
			return;
		}
		if(l.deleteItem(id)>0) System.out.println(id+"�� �׸��� �����Ǿ����ϴ�.\n");	// id�� db�Ϸù�ȣ�� 1���� ����
	}

	public static void updateItem(TodoList l) {
		Scanner s = new Scanner(System.in);
		System.out.print("\n========== ���� �׸� ����\n������ �׸� ��ȣ�� �Է��Ͻÿ�.\n>" );
		int id = s.nextInt();
//		for (TodoItem item : l.getList()) {
//			int cnt=0;
//			if (item.getId() != id) cnt++;
//			if(l.getCount() == cnt) {
//				System.out.println("�������� �ʴ� �׸��Դϴ�.\n");
//				return;
//			}
//		}
		System.out.print("�ش� �׸񿡼� �����Ϸ��� �̸��� �Է��Ͻÿ�.\n>");
		String new_title = s.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�̹� �ִ� �׸� �̸��Դϴ�.\n");
			return;
		}
		System.out.print("ī�װ� ���� �Է��Ͻÿ�.\n>");
		String new_category = s.next();
		s.nextLine();
		System.out.print("���� ������ �Է��Ͻÿ�.\n>");
		String new_description = s.nextLine().trim();
		System.out.print("������ �Է��Ͻÿ�(YYYYMMDD).\n>");
		String new_due_date = s.next();
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
		if(l.updateItem(t, id) > 0) System.out.println("������ �Ϸ�Ǿ����ϴ�.\n");
		else System.out.println("������ �����Ͽ����ϴ�. �Է��� ����� �Ǿ����� Ȯ���Ͻʽÿ�.\n");
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
			System.out.print(item.toString());
			count++;
		}
		System.out.println("\n�˻� ��� �� " + count + "��.\n");
	}
	public static void find_cate(TodoList l, String cate_keyword) {
		System.out.print("\n========== ī�װ� �˻�\n");
		int count =0;
		for (TodoItem item : l.getCategory(cate_keyword)) {
			System.out.print(item.toString());
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
	
	public static void comp(TodoList l, int complete) {
		int count = l.completeItem(complete);
		if(l.completeItem(complete) == 0) System.out.println("\n�ش� ��ȣ�� ��ϵ� �׸��� ���������ʽ��ϴ�.\n");
		else System.out.println("\n�� "+ count + "���� �׸��� �Ϸ�üũ�Ǿ����ϴ�.\n");
		// ���߿� ��ȣ ������ �Է� �޾Ƽ� �ѹ���? while(sc.next();)?
	}
	public static void find_comp(TodoList l) {
		System.out.print("\n========== �Ϸ�� �׸� Ȯ��\n");
		int count=0;
		for (TodoItem item : l.getComp()) {
			System.out.print(item.toString());
			count ++;
		}
		System.out.println("\n�˻� ��� �� "+ count + "��.\n");
	}

	public static void importance(TodoList l, int impo_id) {
		System.out.print("\n========== �߿䵵 ����\n�ش� �׸��� �߿䵵�� �Է��Ͻÿ�(0~3)\n>");
		Scanner s = new Scanner(System.in);
		int important = s.nextInt();
		if (l.importance(impo_id, important) > 0) System.out.println("\n"+impo_id+"�� �׸��� �߿䵵 "+important+"(��)�� �����Ǿ����ϴ�.\n");
		else System.out.println("\n�߿䵵 ���� ����. ���ڸ� �˸°� �Է��ߴ��� Ȯ���Ͻÿ�.\n");
	}

	public static void mate(TodoList l) {
		Scanner s = new Scanner(System.in);
		System.out.print("\n========== ���� �ο��� ����\n������ �׸� ��ȣ�� �Է��Ͻÿ�\n>");
		int mate_id = s.nextInt();
		System.out.print("�ִ� ���� �ο����� �Է��Ͻÿ�\n>");
		int member = s.nextInt();
		if(l.mate(mate_id, member) > 0) System.out.println("\n�ο��� ������ �Ϸ�Ǿ����ϴ�!\n");
		else System.out.println("\n�������� �ʴ� ��ȣ�Դϴ�.\n");
	}
		
	
	 // ���α׷� ���� �� �б� & ���� �� ����
	public static void saveList(TodoList l, String filename) {
		try {
			//File file = new File("todolist.txt");
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
//	public static void loadList(TodoList l, String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			String items;
//			int count=0;
//			while( (items = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(items, "##");
//				String category = st.nextToken();
//				String title = st.nextToken();
//				String desc = st.nextToken();
//				String due_date = st.nextToken();
//				String current_date = st.nextToken();
//				TodoItem t = new TodoItem(category, title, desc, due_date);
//				t.setCurrent_date(current_date);
//				l.addItem(t);
//				count++;
////				System.out.println(title+desc+current_date);
//			}
//			br.close();
//			System.out.println(count+"���� �׸��� "+filename+"���� ����Ǿ����ϴ�.");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
}
