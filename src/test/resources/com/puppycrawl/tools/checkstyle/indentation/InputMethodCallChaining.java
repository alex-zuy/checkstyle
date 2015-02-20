package com.puppycrawl.tools.checkstyle.indentation;

public class InputMethodCallChaining
{
  void m()
  {
//      Thread.doAnswer(new Answer() {
//        @Override
//        public Object answer(InvocationOnMock invocation) {
//          return null;
//        }
//      }).when(someInstance).someMethod(Mockito.eq(someParam),
//        AdditionalMatchers.aryEq(someOtherParam));
        
    String.valueOf(new Object() {
        @Override
        public String toString() {
            return null;
        }
    }).substring(1, 3).codePointCount(2,
        5);
  }

//  void m()
//  {
//    Mockito.doAnswer(new Answer() {
//      @Override
//      public Object answer(InvocationOnMock invocation) {
//        return null;
//      }
//    }).when(someInstance).someMethod(Mockito.eq(someParam),
//      AdditionalMatchers.aryEq(someOtherParam));
//  }
}
