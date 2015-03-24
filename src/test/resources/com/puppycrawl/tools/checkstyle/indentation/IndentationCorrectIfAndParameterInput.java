package com.puppycrawl.tools.checkstyle.indentation; //indent:0 exp:0

/**                                                                           //indent:0 exp:0
 * This test-input is intended to be checked using following configuration:   //indent:1 exp:1
 *                                                                            //indent:1 exp:1
 * arrayInitIndent = 4                                                        //indent:1 exp:1
 * basicOffset = 2                                                            //indent:1 exp:1
 * braceAdjustment = 0                                                        //indent:1 exp:1
 * caseIndent = 4                                                             //indent:1 exp:1
 * forceStrictCondition = false                                               //indent:1 exp:1
 * lineWrappingIndentation = 4                                                //indent:1 exp:1
 * tabWidth = 4                                                               //indent:1 exp:1
 * throwsIndent = 4                                                           //indent:1 exp:1
 *                                                                            //indent:1 exp:1
 *                                                                            //indent:1 exp:1
 */                                                                           //indent:1 exp:1
class FooIfClass { //indent:0 exp:0

  String getString(int someInt, String someString) { //indent:2 exp:2
    return "String"; //indent:4 exp:4
  } //indent:2 exp:2

  void fooMethodWithIf() { //indent:2 exp:2

    if (conditionFirst("Loooooooooooooooooong", new //indent:4 exp:4
        SecondClassWithVeryVeryVeryLongName("Loooooooooooooooooog"). //indent:8 exp:8
            getInteger(new FooIfClass(), "Loooooooooooooooooog"), //indent:12 exp:12
        new InnerClassFoo())) {} //indent:8 exp:8

    if (conditionSecond(10000000000.0, new //indent:4 exp:4
        SecondClassWithVeryVeryVeryLongName("Looooooooooooo" //indent:8 exp:8
            + "oooooooooooong").getString(new FooIfClass(), //indent:12 exp:12
                new SecondClassWithVeryVeryVeryLongName("loooooooooong"). //indent:16 exp:16
                    getInteger(new FooIfClass(), "loooooooooooooong")), "loooooooooooong") //indent:20 exp:20
        || conditionThird(2048) || conditionFourth(new //indent:8 exp:8
            SecondClassWithVeryVeryVeryLongName("Looooooooooooooo" //indent:12 exp:12
                + "ooooooooooooong").getBoolean(new FooIfClass(), false)) || //indent:16 exp:16
        conditionFifth(true, new SecondClassWithVeryVeryVeryLongName(getString(2048, "Looo" //indent:8 exp:8
            + "ooooooooooooooooooooooooooooooooooooooooooong")).getBoolean( //indent:12 exp:12
                new FooIfClass(), true)) || conditionSixth(false, new //indent:16 exp:16
                    SecondClassWithVeryVeryVeryLongName(getString(100000, "Loooooong" //indent:20 exp:20
                        + "Fooooooo><"))) || conditionNoArg() //indent:24 exp:24
        || conditionNoArg() || //indent:8 exp:8
        conditionNoArg() || conditionNoArg()) {} //indent:8 exp:8
  } //indent:2 exp:2

  private boolean conditionFirst(String longString, int //indent:2 exp:2
      integer, InnerClassFoo someInstance) { //indent:6 exp:6
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  private boolean conditionSecond(double longLongLongDoubleValue, //indent:2 exp:2
      String longLongLongString, String secondLongLongString) { //indent:6 exp:6
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  private boolean conditionThird(long veryLongValue) { //indent:2 exp:2
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  private boolean conditionFourth(boolean flag) { //indent:2 exp:2
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  private boolean conditionFifth(boolean flag1, boolean flag2) { //indent:2 exp:2
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  private boolean conditionSixth(boolean flag, //indent:2 exp:2
      SecondClassWithVeryVeryVeryLongName instance) { //indent:6 exp:6
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  private boolean conditionNoArg() { //indent:2 exp:2
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  class InnerClassFoo { //indent:2 exp:2

    void fooMethodWithIf() { //indent:4 exp:4
      if (conditionFirst("Loooooooooooooooooong", new //indent:6 exp:6
          SecondClassWithVeryVeryVeryLongName("Loooooooooooooooooog"). //indent:10 exp:10
              getInteger(new FooIfClass(), "Loooooooooooooooooog"), //indent:14 exp:14
          new InnerClassFoo())) {} //indent:10 exp:10

      if (conditionSecond(10000000000.0, new //indent:6 exp:6
          SecondClassWithVeryVeryVeryLongName("Looooooooooooo" //indent:10 exp:10
              + "oooooooooooong").getString(new FooIfClass(), //indent:14 exp:14
                  new SecondClassWithVeryVeryVeryLongName("loooooooooong"). //indent:18 exp:18
                      getInteger(new FooIfClass(), "loooooooooooooong")), "loooooooooooong") //indent:22 exp:22
          || conditionThird(2048) || conditionFourth(new //indent:10 exp:10
              SecondClassWithVeryVeryVeryLongName("Looooooooooooooo" //indent:14 exp:14
                  + "ooooooooooooong").getBoolean(new FooIfClass(), false)) || //indent:18 exp:18
          conditionFifth(true, new SecondClassWithVeryVeryVeryLongName(getString(2048, "Looo" //indent:10 exp:10
              + "ooooooooooooooooooooooooooooooooooooooooooong")).getBoolean( //indent:14 exp:14
                  new FooIfClass(), true)) || conditionSixth(false, new //indent:18 exp:18
                      SecondClassWithVeryVeryVeryLongName(getString(100000, "Loooooong" //indent:22 exp:22
                          + "Fooooooo><"))) || conditionNoArg() //indent:26 exp:26
          || conditionNoArg() || //indent:10 exp:10
          conditionNoArg() || conditionNoArg()) {} //indent:10 exp:10
    } //indent:4 exp:4

    FooIfClass anonymousClass = new FooIfClass() { //indent:4 exp:4

      void fooMethodWithIf(String stringStringStringStringLooooongString, //indent:6 exp:6
          int intIntIntVeryLongNameForIntVariable, boolean //indent:10 exp:10
          fooooooooobooleanBooleanVeryLongName) { //indent:10 exp:10

        if (conditionFirst("Loooooooooooooooooong", new //indent:8 exp:8
            SecondClassWithVeryVeryVeryLongName("Loooooooooooooooooog"). //indent:12 exp:12
                getInteger(new FooIfClass(), "Loooooooooooooooooog"), //indent:16 exp:16
            new InnerClassFoo())) {} //indent:12 exp:12

        if (conditionSecond(10000000000.0, new //indent:8 exp:8
            SecondClassWithVeryVeryVeryLongName("Looooooooooooo" //indent:12 exp:12
                + "oooooooooooong").getString(new FooIfClass(), //indent:16 exp:16
                    new SecondClassWithVeryVeryVeryLongName("loooooooooong"). //indent:20 exp:20
                        getInteger(new FooIfClass(), "loooooooooooooong")), "loooooooooooong") //indent:24 exp:24
            || conditionThird(2048) || conditionFourth(new //indent:12 exp:12
                SecondClassWithVeryVeryVeryLongName("Looooooooooooooo" //indent:16 exp:16
                    + "ooooooooooooong").getBoolean(new FooIfClass(), false)) || //indent:20 exp:20
            conditionFifth(true, new SecondClassWithVeryVeryVeryLongName(getString(2048, "Looo" //indent:12 exp:12
                + "ooooooooooooooooooooooooooooooooooooooooooong")).getBoolean( //indent:16 exp:16
                    new FooIfClass(), true)) || conditionSixth(false, new //indent:20 exp:20
                        SecondClassWithVeryVeryVeryLongName(getString(100000, "Loooooong" //indent:24 exp:24
                            + "Fooooooo><"))) || conditionNoArg() //indent:28 exp:28
            || conditionNoArg() || //indent:12 exp:12
            conditionNoArg() || conditionNoArg() //indent:12 exp:12
            && fooooooooobooleanBooleanVeryLongName) {} //indent:12 exp:12
      } //indent:6 exp:6
    }; //indent:4 exp:4
  } //indent:2 exp:2
} //indent:0 exp:0

class SecondClassWithVeryVeryVeryLongName { //indent:0 exp:0

  public SecondClassWithVeryVeryVeryLongName(String string) { //indent:2 exp:2

  } //indent:2 exp:2

  String getString(FooIfClass instance, int integer) { //indent:2 exp:2
    return "String"; //indent:4 exp:4
  } //indent:2 exp:2

  int getInteger(FooIfClass instance, String string) { //indent:2 exp:2
    return -1;   //indent:4 exp:4
  } //indent:2 exp:2

  boolean getBoolean(FooIfClass instance, boolean flag) { //indent:2 exp:2
    return false; //indent:4 exp:4
  } //indent:2 exp:2

  SecondClassWithVeryVeryVeryLongName getInstanse() { //indent:2 exp:2
    return new SecondClassWithVeryVeryVeryLongName("VeryLoooooooooo" //indent:4 exp:4
        + "oongString"); //indent:8 exp:8
  } //indent:2 exp:2
} //indent:0 exp:0
