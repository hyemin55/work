/*
- 자바에서는 부모클래스는 하나이고 상속 받는 자식클래스는 여럿일 수 있다.
    하지만 자식클래스가 하나이고 부모클래스가 여럿일 수는 없다.
- 기본 생성자는 다른 생성자를 선언하게 되면 생략 불가이다.
- 상속관계에서는 부모 클래스 기본생성자를 호출하는게 생략되어져 있다.
- 만약 부모클래스에 기본생성자가 선언되어있지 않을 때엔
    다른생성자 호출을 통해 부모클래스의 다른생성자를 호출할 수 있다.

- 상속관계에서는 부모클래스 생성자는 무조건 기본생성자 또는 다른 생성자를 호출해야한다.
 */
package org.example;

import org.example.aa.BizMan;

public class ex03 {
    public static void main(String[] args) {
        BizMan bm = new BizMan("홍길동","그린컴퓨터","수강생");
        bm.showInfo();
    }
}
