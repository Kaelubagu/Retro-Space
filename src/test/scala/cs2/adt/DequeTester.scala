package cs2.adt

import org.junit._
import org.junit.Assert._

class DequeTester {
  //Include your thorough tester code here, including @Begin and @Test methods
  //and any fields needed for testing
  var empty:Deque[Int] = Deque[Int]()
  var unoNode:Deque[Int] = Deque[Int]()
  var dosNodeA:Deque[Int] = Deque[Int]()
  var dosNodeP:Deque[Int] = Deque[Int]()
  var tresNodeA:Deque[Int] = Deque[Int]()
  var tresNodeP:Deque[Int] = Deque[Int]()

  @Before def init():Unit = {
    empty = Deque[Int]()
    unoNode = Deque[Int]()
    unoNode.append(1)
    dosNodeA = Deque[Int]()
    dosNodeA.append(1)
    dosNodeA.append(2)
    dosNodeP = Deque[Int]()
    dosNodeP.prepend(1)
    dosNodeP.prepend(2)
    tresNodeA = Deque[Int]()
    tresNodeA.append(1)
    tresNodeA.append(2)
    tresNodeA.append(3)
    tresNodeP = Deque[Int]()
    tresNodeP.prepend(1)
    tresNodeP.prepend(2)
    tresNodeP.prepend(3)
  }

  @Test def GIVEN_newDQ_WHEN_isEmpty_returnsTrue():Unit = {
    assertTrue(empty.isEmpty())
  }

 @Test def GIVEN_NewDQ_WHEN_peekFront_THEN_Exception():Unit = {
    try {
      empty.peekFront()
    }
    catch{
      case _:RuntimeException => return
    }

    fail("Expected RuntimeException")

    
  }

  @Test def GIVEN_NewDQ_WHEN_peekBack_THEN_Exception():Unit = {
    try {
      empty.peekBack()
    }
    catch{
      case _:RuntimeException => return
    }

    fail("Expected RuntimeException")

    
  }

  @Test def GIVEN_NewDQ_WHEN_back_THEN_Exception():Unit = {
    try {
      empty.back()
    }
    catch{
      case _:RuntimeException => return
    }

    fail("Expected RuntimeException")

    
  }

  @Test def GIVEN_NewDQ_WHEN_front_THEN_Exception():Unit = {
    try {
      empty.front()
    }
    catch{
      case _:RuntimeException => return
    }

    fail("Expected RuntimeException")

    
  }

  @Test def GIVEN_NewDQ_WHEN_append_THEN_notEmpty():Unit = {
    empty.append(1)
    assertFalse(empty.isEmpty())
  }

  @Test def GIVEN_NewDQ_WHEN_prepend_THEN_notEmpty():Unit = {
    empty.prepend(1)
    assertFalse(empty.isEmpty())
  }

  @Test def GIVEN_unoNode_WHEN_checklen_THEN_len1():Unit = {
    assertEquals(1, unoNode.len())
  }

  @Test def GIVEN_dosNodeA_WHEN_len_THEN_two():Unit = {
    assertEquals(2, dosNodeA.len)
  }

  @Test def GIVEN_dosNodeA_WHEN_peekFront_THEN_one():Unit = {
    assertEquals(1, dosNodeA.peekFront())
  }

  @Test def GIVEN_dosNodeA_WHEN_peekBack_THEN_two():Unit = {
    assertEquals(2, dosNodeA.peekBack())
  }

  @Test def GIVEN_dosNodeP_WHEN_peekFront_THEN_two():Unit = {
    assertEquals(2,dosNodeP.peekFront())
  }
  @Test def GIVEN_dosNodeP_WHEN_peekBack_THEN_one():Unit = {
    assertEquals(1, dosNodeP.peekBack())
  }

  @Test def GIVEN_dosNodeP_WHEN_len_THEN_two():Unit = {
    assertEquals(2, dosNodeP.len())
  }

  @Test def GIVEN_dosNodeA_WHEN_isEmpty_Then_False():Unit = {
    assertFalse(dosNodeA.isEmpty())
    assertFalse(dosNodeP.isEmpty())
  }


  @Test def GIVEN_tresNodeA_WHEN_len_THEN_three():Unit = {
    assertEquals(3, tresNodeA.len())
  }

  @Test def GIVEN_tresNodeA_WHEN_peekFront_THEN_one():Unit = {
    assertEquals(1, tresNodeA.peekFront())
  }
  @Test def GIVEN_tresNodeA_WHEN_peekBack_THEN_three():Unit = {
    assertEquals(3, tresNodeA.peekBack())
  }

  @Test def GIVEN_threeNode_WHEN_FrontBack_THEN_check():Unit = {
  assertEquals(1, tresNodeA.front())
  assertEquals(3, tresNodeA.back())
  assertEquals(3, tresNodeP.front())
  assertEquals(1, tresNodeP.back())
  }

  @Test def GIVEN_threeNode_WHEN_3Front_THEN_len0():Unit = {
    assertEquals(1,tresNodeA.front())
    assertEquals(2,tresNodeA.front())
    assertEquals(3,tresNodeA.front())
    assertEquals(3, tresNodeP.front())
    assertEquals(2, tresNodeP.front())
    assertEquals(1, tresNodeP.front())
    assertEquals(0, tresNodeA.len())
    assertEquals(0, tresNodeP.len())
  }

  @Test def GIVEN_threeNode_WHEN_3Back_THEN_len0():Unit = {
    assertEquals(3,tresNodeA.back())
    assertEquals(2,tresNodeA.back())
    assertEquals(1,tresNodeA.back())
    assertEquals(1, tresNodeP.back())
    assertEquals(2, tresNodeP.back())
    assertEquals(3, tresNodeP.back())
    assertEquals(0, tresNodeA.len())
    assertEquals(0, tresNodeP.len())
  }

  @Test def GIVEN_threeNode_WHEN_3BackFRONT_THEN_len0():Unit = {
    assertEquals(3, tresNodeA.back)
    assertEquals(1, tresNodeA.front())
    assertEquals(2, tresNodeA.front())
    assertEquals(1, tresNodeP.back())
    assertEquals(3, tresNodeP.front())
    assertEquals(2, tresNodeP.back())
    assertTrue(tresNodeA.isEmpty())
    assertTrue(tresNodeP.isEmpty())
  }

  @Test def GIVEN_tresNodeA_WHEN_4Front_THEN_Exception():Unit = {
    try {
      tresNodeA.front()
      tresNodeA.front()
      tresNodeA.front()
      tresNodeA.front()
    }
    catch{
      case _:RuntimeException => return
    }

    fail("Expected RuntimeException")

    
  }



  @Test def GIVEN_tresNodeP_WHEN_4Front_THEN_Exception():Unit = {
    try {
      tresNodeP.front()
      tresNodeP.front()
      tresNodeP.front()
      tresNodeP.front()
    }
    catch{
      case _:RuntimeException => return
    }

    fail("Expected RuntimeException")

    
  }
}


