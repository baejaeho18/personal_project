package com.todo.menu;
public class Menu {
	
	public static void prompt() {   
        System.out.println();
        System.out.print(">> Enter command (if u need, type 'help')\n>> ");
    }
    
    public static void displaymenu() {
    	System.out.println("\n========== ����");
    	System.out.println("1. add\t\t�ű� �׸� ���");
        System.out.println("2. del\t\t�׸� ����");
        System.out.println("3. edit\t\t�׸� ����");
        System.out.println("4. comp <��ȣ>\t�Ϸ� üũ");
        System.out.println("5. ls\t\t��� �׸� ���");
        System.out.println("6. ls_name\t�̸������� ����");
        System.out.println("7. ls_name_desc\t�̸� �������� ����");
        System.out.println("8. ls_date\t������ ������� ����");
        System.out.println("9. ls_date_desc\t������ �������� ����");
        System.out.println("10. ls_cate\t��ϵ� ī�װ� ���");
        System.out.println("11. ls_comp\t�Ϸ�� �׸�� ���");
        System.out.println("12. find <Ű����>\t����� ���뿡�� Ű���� �˻�");
        System.out.println("13. find_cate <Ű����>\tī�װ����� Ű���� �˻�");
        System.out.println("new! 14. importance <��ȣ> \t�߿䵵 ����");
        System.out.println("new! 15. mate <��ȣ> \t������ �ο��� �Է�");
        System.out.println("14. exit\t������\n");
    }
}


