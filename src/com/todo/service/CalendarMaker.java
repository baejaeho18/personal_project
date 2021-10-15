package com.todo.service;

import java.util.Calendar;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class CalendarMaker {
	public static void showMaker(TodoList l) {
		// ���� �ؿ� ī�װ�, �̸�, �߿䵵 ǥ��
		//due_date
		//[cate]
		//title(impx*)
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(start.get(Calendar.YEAR), start.get(Calendar.MONTH), 1);
        end.set(end.get(Calendar.YEAR), end.get(Calendar.MONTH)+1, 1);
        end.add(Calendar.DATE, -1);       //� ���� �������� = ������ 1�Ͽ��� �Ϸ縦 �� ��
        int first_date = start.get(Calendar.DAY_OF_WEEK);
        int END_DAY = end.get(Calendar.DATE);
        
        System.out.println("��� �޷� : \t["+start.get(Calendar.YEAR)+"�� "+(start.get(Calendar.MONTH)+1)+"��]\n"	// calendar.month�� ����-1���� ��ȯ�Ѵ�
                + "\t��\t\t��\t\tȭ\t\t��\t\t��\t\t��\t\t��");
        int cnt = first_date - 1;
        for(int q = 0 ; q < cnt ; q++) System.out.print("\t\t");
        for(int q = 1 ; q <= END_DAY ; q++) {
            System.out.print("\t"+q+"\t");
            if((q+cnt)%7==0) {
                System.out.println("");
                for (TodoItem item : l.getDate(q)) {
                	System.out.print("\t");
                    System.out.print("["+item.getCategory()+"]");
                	System.out.print("\t");
                }
                System.out.println("");
                for (TodoItem item : l.getDate(q)) {
                	System.out.print("\t");
                	if (item.getImp() == 3) System.out.print(item.getTitle()+"(***)");
                	else if (item.getImp() == 2) System.out.print(item.getTitle()+"(**)");
                	else if (item.getImp() == 1) System.out.print(item.getTitle()+"(*)");
                	System.out.print("\t");
                }
            }
        }
		
		
		
	}
	
}
