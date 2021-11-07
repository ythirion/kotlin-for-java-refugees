package demo.solution.blog

import org.junit.jupiter.api.Assertions
import java.time.LocalDate

/**
 *  Top level functional
 */

fun articleWithComments(comments: List<Comment>): Article =
    Article(
        "Lorem Ipsum",
        "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
        comments
    )

fun assertAddedComment(
    article: Article,
    expectedText: String,
    expectedAuthor: String
) {
    val addedComment = article.comments.last()

    assertionJunit5.assertEquals(expectedAuthor, addedComment.author)
    assertionJ.assertThat( addedComment.creationDate).isEqualTo(LocalDate.now())
    /**
     * Structural Equality (‘==’ & !=) : Operator is used to compare the data of two variables.*Whereas in Java or other languages == is generally used to compare the references.
     * Referential equality (‘===’ & ‘!==’) : operator is used to compare the reference of two variable or object.
     * Equals Methos : method is implemented in Any class and can be overridden. equals method also compares the content just like ==
     */
    Assertions.assertTrue(expectedText == addedComment.text)
}

// using typealias to differentiate two libs Junit and AssertJ
typealias assertionJunit5 = org.junit.jupiter.api.Assertions
typealias assertionJ = org.assertj.core.api.Assertions