package com.puppycrawl.tools.checkstyle.indentation; //indent:0 exp:0

import java.util.Arrays; //indent:0 exp:0

/**                                                                           //indent:0 exp:0
 * This test-input is intended to be checked using following configuration:   //indent:1 exp:1
 *                                                                            //indent:1 exp:1
 * arrayInitIndent = 4                                                        //indent:1 exp:1
 * basicOffset = 2                                                            //indent:1 exp:1
 * braceAdjustment = 0                                                        //indent:1 exp:1
 * caseIndent = 4                                                             //indent:1 exp:1
 * forceStrictCondition = true                                                //indent:1 exp:1
 * lineWrappingIndentation = 4                                                //indent:1 exp:1
 * tabWidth = 4                                                               //indent:1 exp:1
 * throwsIndent = 4                                                           //indent:1 exp:1
 *                                                                            //indent:1 exp:1
 *                                                                            //indent:1 exp:1
 */                                                                           //indent:1 exp:1
public class InputValidLineWrapping { //indent:0 exp:0
  public //indent:2 exp:2
      InputValidLineWrapping() { //indent:6 exp:6
  } //indent:2 exp:2

  public InputValidLineWrapping(String //indent:2 exp:2
      arg) { //indent:6 exp:6
  } //indent:2 exp:2

  public //indent:2 exp:2
      InputValidLineWrapping( //indent:6 exp:6
          String arg1, //indent:10 exp:10
          String arg2 //indent:10 exp:10
          , //indent:10 exp:10
          String arg3) { //indent:10 exp:10
  } //indent:2 exp:2

  public InputValidLineWrapping( //indent:2 exp:2
      Integer arg) { //indent:6 exp:6

  } //indent:2 exp:2

  public //indent:2 exp:2
      void //indent:6 exp:6
      method1(String arg) //indent:6 exp:6
  { //indent:2 exp:2
  } //indent:2 exp:2

  public //indent:2 exp:2
      void //indent:6 exp:6
      method2(String arg) { //indent:6 exp:6
  } //indent:2 exp:2

  public void method3(String //indent:2 exp:2
      arg) //indent:6 exp:6
  { //indent:2 exp:2
  } //indent:2 exp:2

  public //indent:2 exp:2
      final String field1 = "hello"; //indent:6 exp:6

  public String field2 = //indent:2 exp:2
      "hello"; //indent:6 exp:6

  public String field3 //indent:2 exp:2
      = "hello"; //indent:6 exp:6

  public String field4 //indent:2 exp:2
      = //indent:6 exp:6
      "hello" //indent:6 exp:6
      + //indent:6 exp:6
      "there"; //indent:6 exp:6

  public String field5 = String.valueOf( //indent:2 exp:2
      3); //indent:6 exp:6

  public String field6 = String.valueOf(Integer //indent:2 exp:2
      .valueOf("12")); //indent:6 exp:6

  public String field7 = String.valueOf(Integer //indent:2 exp:2
      .valueOf("12") //indent:6 exp:6
  ); //indent:2 exp:2

  public String field8 = String.valueOf(Integer //indent:2 exp:2
      .valueOf( //indent:6 exp:6
          "12") //indent:10 exp:10
  ); //indent:2 exp:2

  public String field9 = String.valueOf(Integer //indent:2 exp:2
      .valueOf( //indent:6 exp:6
          "12" //indent:10 exp:10
      ) //indent:6 exp:6
  ); //indent:2 exp:2

  public String field10 = String.valueOf(Integer //indent:2 exp:2
      .valueOf( //indent:6 exp:6
          "1" //indent:10 exp:10
          + //indent:10 exp:10
          "2" //indent:10 exp:10
      ) //indent:6 exp:6
  ); //indent:2 exp:2

  public void m() //indent:2 exp:2
  { //indent:2 exp:2
    String.valueOf( //indent:4 exp:4
        Math.subtractExact( //indent:8 exp:8
            Integer.valueOf( //indent:12 exp:12
                "12"), //indent:16 exp:16
            Integer.valueOf( //indent:12 exp:12
                "14") //indent:16 exp:16
        ) //indent:8 exp:8
    ); //indent:4 exp:4

    String var1; //indent:4 exp:4

    String //indent:4 exp:4
        var2; //indent:8 exp:8

    String var3 = String //indent:4 exp:4
        .valueOf(12) //indent:8 exp:8
        .substring(2, 4) //indent:8 exp:8
        .replace('a', 'b') //indent:8 exp:8
        .trim().concat("asda") //indent:8 exp:8
        .intern(); //indent:8 exp:8

    String var4 = String //indent:4 exp:4
        .valueOf(12) //indent:8 exp:8
        .substring(2, 4) //indent:8 exp:8
        .replace( //indent:8 exp:8
            'a', //indent:12 exp:12
            'b') //indent:12 exp:12
        .trim().concat( //indent:8 exp:8
            "asda") //indent:12 exp:12
        .intern(); //indent:8 exp:8

    System //indent:4 exp:4
        .out //indent:8 exp:8
        .println("hl"); //indent:8 exp:8

    System. //indent:4 exp:4
        out //indent:8 exp:8
        .println("hl"); //indent:8 exp:8

    System. //indent:4 exp:4
        out. //indent:8 exp:8
        println("hl"); //indent:8 exp:8

    System //indent:4 exp:4
        .out. //indent:8 exp:8
        println("hl"); //indent:8 exp:8

    "hello there" //indent:4 exp:4
        .length(); //indent:8 exp:8

    this //indent:4 exp:4
        .hashCode(); //indent:8 exp:8

    String o1 = new String() //indent:4 exp:4
        .substring(1, 2); //indent:8 exp:8

    for(int i = 0; i < 4; ++i //indent:4 exp:4
    ) { //indent:4 exp:4

    } //indent:4 exp:4

    for( //indent:4 exp:4
        int i = 0; i < 4; ++i) { //indent:8 exp:8

    } //indent:4 exp:4

    for(int //indent:4 exp:4
        i = 0; i < 4; ++i) { //indent:8 exp:8

    } //indent:4 exp:4

    for( //indent:4 exp:4
        int i = 0; //indent:8 exp:8
        i < 4; //indent:8 exp:8
        ++i) { //indent:8 exp:8

    } //indent:4 exp:4

    for(int i = //indent:4 exp:4
        Integer.valueOf("12"); i < 4; ++i){ //indent:8 exp:8

    } //indent:4 exp:4

    for(int i = //indent:4 exp:4
        Integer.valueOf( //indent:8 exp:8
            "12" //indent:12 exp:12
            ); //indent:12 exp:12
        i < 4 && //indent:8 exp:8
        i > 2; //indent:8 exp:8
        ++i){ //indent:8 exp:8

    } //indent:4 exp:4

    for(int i = //indent:4 exp:4
        Integer //indent:8 exp:8
            .valueOf( //indent:12 exp:12
                "12" //indent:16 exp:16
                ); //indent:16 exp:16
        i < 4 && //indent:8 exp:8
        i > 2; //indent:8 exp:8
        ++i){ //indent:8 exp:8

    } //indent:4 exp:4

    boolean cond = true; //indent:4 exp:4

    if( //indent:4 exp:4
        cond) { //indent:8 exp:8

    } //indent:4 exp:4

    if( //indent:4 exp:4
        cond //indent:8 exp:8
    ) { //indent:4 exp:4

    } //indent:4 exp:4

    if(cond) { //indent:4 exp:4

    } //indent:4 exp:4
    else if( //indent:4 exp:4
        cond) { //indent:8 exp:8

    } //indent:4 exp:4
    else if( //indent:4 exp:4
        cond) { //indent:8 exp:8

    } //indent:4 exp:4

    String str ="asd"; //indent:4 exp:4
    str.concat(new String("asda")) //indent:4 exp:4
        .substring(2) //indent:8 exp:8
        .trim(); //indent:8 exp:8
    str.concat(String.valueOf(1) //indent:4 exp:4
        .substring(2) //indent:8 exp:8
        .trim()); //indent:8 exp:8

    getClass() //indent:4 exp:4
        .getName(); //indent:8 exp:8
    Arrays.asList( //indent:4 exp:4
        Arrays.asList(1,2).toArray(), //indent:8 exp:8
        Arrays.asList(1)); //indent:8 exp:8
  } //indent:2 exp:2

  /*@Override*/ Integer subSetImpl(Integer fromElement, //indent:2 exp:2
      Integer toElement, boolean toInclusive) {return 0;}  //indent:6 exp:6

  public Object[] createValueArray(int length) { //indent:2 exp:2
    return Arrays.asList(1,2,3) //indent:4 exp:4
        .toArray();//indent:8 exp:8
  }//indent:2 exp:2

} //indent:0 exp:0
