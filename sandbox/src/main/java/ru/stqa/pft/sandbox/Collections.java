package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main(String[] args) {
    //String[] langs = new String[4];
    String[] langs = {"JAVA","C#","Python","PHP"};

//    List<String> languages = new ArrayList<>();
//    languages.add("JAVA");
//    languages.add("PHP");
//    languages.add("C#");

    List<String> languages = Arrays.asList("JAVA","C#","Python","PHP");//преобразование массива в список

    for (String l : languages){
      System.out.println("Я хочу выучить " + l );
    }

  }

}
