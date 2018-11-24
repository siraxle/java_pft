package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной "+ s.l + " = " + s.area());
    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами "+ r.a + " и " + r.b + " =  " + r.area());
    System.out.println("Hello,world!");
    Point p1 = new Point(3,2);
    Point p2 = new Point(7,8);
    System.out.println("Расстояние м/у двумя точками = " + Point.distance(p1, p2));
  }
  public double area(Square s){
    return s.l * s.l;
  }
  public double area(Rectangle r){
    return r.a * r.b;
  }


}
