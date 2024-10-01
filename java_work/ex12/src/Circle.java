class Circle {
    // private 변수는 setter getter로 참조 가능하다.
    private int rad;
    private String name;

//    커서를 class안에 두고 alt+insert키를 누르면 getter setter constuctor 생성가능.

    public Circle(int rad) {
        this.rad = rad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.equals("")) {
            this.name = "홍길동";
            return;
        }
        this.name = name;
    }

    //    이 함수를 통해서 반지름 넣는 것만 해라.
    public void setRad(int r) {
        if (r < 0) {
            rad = 0;
            return;
        }
        rad = r;
    }

    public int getRad() {
        return rad;
    }

    public double getArea() {
        return 3.14*rad*rad;
    }
}
