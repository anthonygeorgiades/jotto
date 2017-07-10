package jotto
import org.scalatest.FunSuite

class JottoTest extends FunSuite {

  val f1 = 1
  val f2 = 1
  test("Test multiplication") {
    assert(f1 * f1 == f2)
  }

  val properString = "super"
  val nonproperString = "asfasdf"
  val nonproperString2 = ""
  val nonproperString3 = "suppr"
  test("Testing hasProperForm on nonproper and proper strings") {
    assert(jotto.hasProperForm(properString))
    assert(!jotto.hasProperForm(nonproperString))
    assert(!jotto.hasProperForm(nonproperString2))
    assert(!jotto.hasProperForm(nonproperString3))

  }

  //  val wordListTest = List("jumbo", "manto")
  val legalWord = "clipt"
  val notlegalWord = "tanya"
  val notlegalWord2 = ""

  test("Testing isLegalWord by checking legal and nonlegal words in list") {
    assert(jotto.isLegalWord("super"))
    assert(jotto.isLegalWord(legalWord))
    assert(!jotto.isLegalWord(notlegalWord))
    assert(!jotto.isLegalWord(notlegalWord2))
  }

  val countword = "aaaaaa"
  val countword2 = "aaaaa"
  val countword3 = "annnnnnnnaaaaaa"
  test("Testing countMatchingLetters by checking on previous strings") {
    assertResult(1) { jotto.countMatchingLetters(legalWord, notlegalWord) }
    assertResult(0) { jotto.countMatchingLetters(legalWord, notlegalWord2) }
    assertResult(5) { jotto.countMatchingLetters(countword, countword2) }
    assertResult(2) { jotto.countMatchingLetters(countword, notlegalWord) }
    assertResult(3) { jotto.countMatchingLetters(countword3, notlegalWord) }

  }

  val secretWord = "super"
  val secretWord2 = ""
  val listStrings = List("paper", "clipt")
  val secretWord3 = "paperclipt"
  test("Testing provenLetters") {
    assertResult(Set('p', 'e', 'r')) { jotto.provenLetters(secretWord, listStrings) }
    assertResult(Set('p', 'e', 'r')) { jotto.provenLetters(secretWord, listStrings) }
    assertResult(Set()) { jotto.provenLetters(secretWord2, listStrings) }
    assertResult(Set('p', 'a', 'e', 'r', 'c', 'l', 'i', 't')) { jotto.provenLetters(secretWord3, listStrings) }

  }

  val anotherSecretWord = "abcdefghijklmnopqrxtuvwxy"
  val listStrings2 = List("zebra")
  val listStrings3 = List("lebra", "monkey")
  test("Testing disprovenLetters") {
    assertResult(Set('c', 'l', 'i', 't', 'a')) { jotto.disprovenLetters(secretWord, listStrings) }
    assertResult(Set('z')) { jotto.disprovenLetters(anotherSecretWord, listStrings2) }
    assertResult(Set()) { jotto.disprovenLetters(anotherSecretWord, listStrings3) }
  }

}
