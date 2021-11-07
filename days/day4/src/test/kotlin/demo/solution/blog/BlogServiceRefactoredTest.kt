package demo.solution.blog

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate

class BlogServiceRefactoredTest {
    private val blogService = BlogService()

    @Nested
    inner class `add new comment`{
        @Test
        fun `in an empty Article including given text & author`() {
            val updatedArticle = blogService.addComment(emptyArticle, text, author)
            assert(updatedArticle.isRight)
            assertAddedComment(updatedArticle.get(), text, author)
        }
        @Test
        fun `in an Article containing existing comments`() {
            val newText = "Finibus Bonorum et Malorum"
            val newAuthor = "Al Capone"

            val updatedArticle = blogService.addComment(articleWith1Comment, newText, newAuthor)

            assert(updatedArticle.isRight)
            Assertions.assertEquals(updatedArticle.get().comments.size, 2)
            assertAddedComment(updatedArticle.get(), newText, newAuthor)
        }

    }


    @Test
    fun `return an error when adding existing comment`() {
        val updatedArticle = blogService.addComment(articleWith1Comment, text, author)
        Assertions.assertTrue(updatedArticle.isLeft)
        Assertions.assertEquals(1, updatedArticle.left.size)
        Assertions.assertEquals(BlogService.ErrorMessage.existingCommentError, updatedArticle.left.first().description)
    }

    companion object BlogRefactoredTests {
        private const val text = "Amazing article !!!"
        private const val author = "Pablo Escobar"

        private val emptyArticle: Article = articleWithComments(emptyList())
        private val articleWith1Comment: Article = articleWithComments(listOf(Comment(text, author, LocalDate.now())))

    }
}