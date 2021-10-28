package demo.blog.solution

import demo.blog.Article
import demo.blog.BlogService
import demo.blog.Comment
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class BlogServiceRefactoredTest {
    private val blogService = BlogService()

    @Test
    fun `add a new comment in an empty Article including given text & author`() {
        val updatedArticle = blogService.addComment(emptyArticle, text, author)
        assert(updatedArticle.isRight)
        assertAddedComment(updatedArticle.get(), text, author)
    }

    @Test
    fun `add a new comment in an Article containing existing comments`() {
        val newText = "Finibus Bonorum et Malorum"
        val newAuthor = "Al Capone"

        val updatedArticle = blogService.addComment(articleWith1Comment, newText, newAuthor)

        assert(updatedArticle.isRight)
        Assertions.assertEquals(updatedArticle.get().comments.size, 2)
        assertAddedComment(updatedArticle.get(), newText, newAuthor)
    }

    @Test
    fun `return an error when adding existing comment`() {
        val updatedArticle = blogService.addComment(articleWith1Comment, text, author)
        assert(updatedArticle.isLeft)
        Assertions.assertEquals(1, updatedArticle.left.size)
        Assertions.assertEquals(BlogService.ErrorMessage.existingCommentError, updatedArticle.left.first().description)
    }

    companion object BlogRefactoredTests {
        private const val text = "Amazing article !!!"
        private const val author = "Pablo Escobar"

        private val emptyArticle: Article = articleWithComments(emptyList())
        private val articleWith1Comment: Article = articleWithComments(listOf(Comment(text, author, LocalDate.now())))

        private fun articleWithComments(comments: List<Comment>): Article =
            Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore",
                comments
            )

        private fun assertAddedComment(
            article: Article,
            expectedText: String,
            expectedAuthor: String
        ) {
            val addedComment = article.comments.last()
            Assertions.assertEquals(expectedText, addedComment.text)
            Assertions.assertEquals(expectedAuthor, addedComment.author)
            Assertions.assertEquals(LocalDate.now(), addedComment.creationDate)
        }
    }
}