package cs2.adt

import org.junit._
import org.junit.Assert._

class DEPQTester {
  var empty:DEPQ[Int] = new DEPQ[Int]()
  var plusOne:DEPQ[Int] = new DEPQ[Int]()
  var plusTwo:DEPQ[Int] = new DEPQ[Int]()
  var plusThree:DEPQ[Int] = new DEPQ[Int]()
  var plusThreeOOF:DEPQ[Int] = new DEPQ[Int]()


  @Before def init():Unit = {
    empty = new DEPQ[Int]()
    plusOne.add(1)
    plusTwo = new DEPQ[Int]()
    plusTwo.add(1)
    plusTwo.add(2)
    plusThree = new DEPQ[Int]()
    plusThree.add(1)
    plusThree.add(2)
    plusThree.add(3)
    plusThreeOOF = new DEPQ[Int]()
    plusThreeOOF.add(1)
    plusThreeOOF.add(2)
    plusThreeOOF.add(3)
  }

  @Test def Given_empty_When_isEmpty_Then_true():Unit = {
    assertTrue(empty.isEmpty())
  }

  @Test def Given_plusOne_When_isEmpty_Then_false():Unit = {
    assertTrue(empty.isEmpty())
  }
  //two
  @Test def Given_plusTwo_When_isEmpty_Then_false():Unit = {
    assertTrue(empty.isEmpty())
  }

  @Test def Given_plusTwo_When_peekMAX_Then_Two():Unit = {
    assertEquals(2, plusTwo.peekMax())
  }

  @Test def Given_plusTwo_When_peekMin_Then_One():Unit = {
    assertEquals(1, plusTwo.peekMin())
  }

  //three
  @Test def Given_plusThree_When_peekMax_Then_three():Unit = {
    assertEquals(3, plusThree.peekMax())
  }

  @Test def Given_plusThree_When_peekMin_Then_three():Unit = {
    assertEquals(1, plusThree.peekMin())
  }

  //threeOOF

  @Test def Given_plusThreeOOF_When_peekMax_Then_three():Unit = {
    assertEquals(1, plusThreeOOF.peekMin())
  }
  @Test def Given_plusThreeOOF_When_peekMin_Then_three():Unit = {
    assertEquals(1, plusThreeOOF.peekMin())
  }

  //other
  @Test def Given_empty_when_max_then_exception():Unit = {
    try {
      empty.max()
    }
    catch {
      case _:RuntimeException => return
    }
    fail("Expected RE")
  }

  @Test def Given_plusOne_max_then_1():Unit = {
    assertEquals(1, plusOne.max())
    assertTrue(plusOne.isEmpty())
  }

  @Test def Given_plusTwo_max_then_2():Unit = {
    assertEquals(2, plusTwo.max())
  }

  @Test def Given_plusThree_max_then_3():Unit = {
    assertEquals(3, plusThree.max())
    assertEquals(2, plusThree.max())
    assertEquals(1, plusThree.max())
    assertTrue(plusThree.isEmpty())
  }

  @Test def Given_plusthreeOOF_max_then_3():Unit = {
    assertEquals(3, plusThreeOOF.max())
    assertEquals(2, plusThreeOOF.max())
    assertEquals(1, plusThreeOOF.max())
    assertTrue(plusThreeOOF.isEmpty())
  }
  
  @Test def Given_plusThreeOOF_min_then_3():Unit = {
    assertEquals(1, plusThreeOOF.min())
    assertEquals(2, plusThreeOOF.min())
    assertEquals(3, plusThreeOOF.min())
    assertTrue(plusThreeOOF.isEmpty())
  }
  
  @Test def Given_plusThreeOOF_minmax_then_3():Unit = {
    assertEquals(1, plusThreeOOF.min())
    assertEquals(2, plusThreeOOF.min())
    assertEquals(3, plusThreeOOF.max())
    assertTrue(plusThreeOOF.isEmpty())
  }
}