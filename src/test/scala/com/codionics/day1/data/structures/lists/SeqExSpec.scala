package com.codionics.day1.data.structures.lists

import com.codionics.day1.data.structures.lists.SeqEx._

import com.codionics.BaseSpec

class SeqExSpec extends BaseSpec {

  "A SeqEx" should "have a sequence containing numbers from 1 to 10" in {
    seq should not be empty
    seq should have length 10 // can also use size instead of length
    seq.head should be(1)
    seq.head should be < 2
    seq.head should be <= 1

    seq should contain(5)
    seq should contain allOf (4, 7, 9)
    seq should contain atLeastOneOf (4, 7, 9)
    seq should contain inOrder (4, 7, 9)
  }
}
