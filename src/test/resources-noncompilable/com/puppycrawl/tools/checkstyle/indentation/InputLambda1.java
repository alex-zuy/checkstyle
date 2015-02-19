package com.puppycrawl.tools.checkstyle.indentation;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InputLambda1 {

  interface Printer
  {
    void print(String s);
  }

  void function1(Runnable x) {
    Runnable r1 = () -> { //indent:4 ; exp:4; ok
      x.run(); //indent:6 ; exp:6; ok
    }; //indent:4 ; exp:4; ok

    Runnable r2 = () -> { x.run(); };

    Runnable r3 = () ->
        x.run(); //indent:8 ; exp:8; ok

    Runnable r4 = () -> x.run();

    Printer r5 = s -> System.out.print(s);

    Printer r6 = s -> System.out
        .print(s); //indent:8 ; exp:8; ok

    Printer[] manyRunnable = new Printer[]{
        s -> System.out.print(s), //indent:8 ; exp:6,8; ok
        s -> { System.out.print(s); }, //indent:8 ; exp:6,8; ok
        s -> System.out //indent:8 ; exp:6,8; ok
            .print(s), //indent:12 ; exp:12; ok
        s -> { //indent:8 ; exp:6,8; ok
          System.out.print(s); //indent:10 ; exp:10; ok
        }, //indent:8 ; exp:8; ok

      s -> System.out.print(s), //indent:6 ; exp:6,8; ok
      s -> { System.out.print(s); }, //indent:6 ; exp:6,8; ok
      s -> System.out //indent:6 ; exp:6,8; ok
          .print(s), //indent:10 ; exp:10; ok
      s -> { //indent:6 ; exp:6,8; ok
        System.out.print(s); //indent:8 ; exp:8; ok
      } //indent:6 ; exp:6; ok
    };

    Object o = new Thread(() -> { //indent:4 ; exp:4; ok
      x.run(); //indent:6 ; exp:6; ok
    }); //indent:4 ; exp:4; ok
  }

  void function3(Runnable x) {
    function1(() -> {
      x.run(); //indent: ; exp:; ok
    }); //indent: ; exp:; ok
  }
    
  class Person {
    String name;
    int age;
    Person(String name, int age) {
    }
  }
  class Foo {
    String name;
    List<Bar> bars = new ArrayList<>();

    Foo(String name) {
      this.name = name;
    }
  }
  class Bar {
    String name;
    Bar(String name) {
      this.name = name;
    }
  }

  public void f() {
    Stream.of("d2", "a2", "b1", "b3", "c") //indent:4 exp:4
        .map(s -> { //indent:8 exp:8
          System.out.println("map: " + s); //indent:10 exp:10
          return s.toUpperCase(); //indent:10 exp:10
        }) //indent:8 exp:8
        .anyMatch(s -> { //indent:8 exp:8
          System.out.println("anyMatch: " + s); //indent:10 indent:10
          return s.startsWith("A"); //indent:10 indent:10
        }); //indent:8 indent:8

    List<Person> persons = null; //indent:4 exp:4

    Map<Integer, List<Person>> personsByAge = persons //indent:4 exp:4
        .stream() //indent:8 exp:8
        .collect(Collectors.groupingBy(p -> p.age)); //indent:8 exp:8

    personsByAge //indent:4 exp:4
        .forEach((age, p) -> System.out.format("age %s: %s\n", age, p)); //indent:8 exp:8

    Collector<Person, StringJoiner, String> personNameCollector = //indent:4 exp:4
        Collector.of( //indent:8 exp:8
            () -> new StringJoiner(" | "), //indent:12 exp:12
            (j, p) -> j.add(p.name.toUpperCase()), //indent:12 exp:12
            (j1, j2) -> j1.merge(j2), //indent:12 exp:12
            StringJoiner::toString); //indent:12 exp:12

    List<Foo> foos = new ArrayList<>(); //indent:4 exp:4

    foos.forEach(f -> //indent:4 exp:4
        IntStream //indent:8 exp:8
        .range(1, 4) //indent:8 exp:8
        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name)))); //indent:8 exp:8

    Stream.of("d2", "a2", "b1", "b3", "c") //indent:4 exp:4
        .filter(s -> { //indent:8 exp:8
         System.out.println("filter: " + s); //indent:9 exp:10 VIOLATION
           return s.startsWith("a"); //indent:11 exp:10 VIOLATION
        }) //indent:8 exp:8
        .map(s -> { //indent:8 exp:8
          System.out.println("map: " + s); //indent:10 exp:10
          return s.toUpperCase(); //indent:10 exp:10
       }) //indent:7 exp:8 VIOLATION
        .forEach(s -> //indent:8 exp:8 
            System.out.println("forEach: " + s)); //indent:12 exp:12
    
    IntStream.range(1, 4) //indent:4 exp:4
        .mapToObj(i -> new Foo("Foo" + i)) //indent:8 exp:8
        .peek(f -> IntStream.range(1, 4) //indent:8 exp:8
            .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name)) //indent:12 exp:12
            .forEach(f.bars::add)) //indent:12 exp:12
        .flatMap(f -> f.bars.stream()) //indent:8 exp:8
       .forEach(b -> System.out.println(b.name)); //indent:7 exp:8 VIOLATION
    
    IntStream.range(1, 4) //indent:4 exp:4
        .mapToObj(i -> new Foo("Foo" + i)) //indent:8 exp:8
        .peek(f -> IntStream.range(1, 4) //indent:8 exp:8
          .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name)) //indent:10 exp:12 VIOLATION
            .forEach(f.bars::add)) //indent:12 exp:12
        .flatMap(f -> f.bars.stream()) //indent:8 exp:8
       .forEach(b -> System.out.println(b.name)); //indent:7 exp:8 VIOLATION
  }
}

