package ru.stqa.pft.sandbox;

public class MyFirstProgram {
  public static void main(String[] args) {
    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной "+ s.l + " = " + s.area());
    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами "+ r.a + " и " + r.b + " =  " + r.area());
    System.out.println("Hello,world!");
    Point p = new Point(3,2,7,8);
//    Point p2 = new Point();
//    p1.x = 3.0;
//    p1.y = 2.0;
//    p2.x = 7.0;
//    p2.y = 8.0;
    System.out.println("Расстояние м/у двумя точками = " + p.distance());
  }
  public double area(Square s){
    return s.l * s.l;
  }
  public double area(Rectangle r){
    return r.a * r.b;
  }
//  public static double distance(Point p1, Point p2){
//    return Math.sqrt(((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y)));
//  }
}
