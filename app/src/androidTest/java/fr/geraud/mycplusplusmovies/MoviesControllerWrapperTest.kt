package fr.geraud.mycplusplusmovies

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MoviesControllerWrapperTest {
    @Test
    fun testCreateAndDelete() {
        // WHEN
        val controller = MoviesControllerWrapper()

        // THEN
        assertNotNull(controller)
        controller.dispose()
    }

    @Test
    fun testGetMovies() {
        // GIVEN
        val controller = MoviesControllerWrapper()

        // WHEN
        val movies = controller.getMovies()

        // THEN
        assertNotNull(movies)
        assertEquals(10, movies.size)
        controller.dispose()
    }

    @Test
    fun testGetMovieDetails() {
        // GIVEN
        val controller = MoviesControllerWrapper()
        val movies = controller.getMovies()

        // WHEN
        val details = movies
            .map { controller.getMovieDetail(it.name) }

        // THEN
        assertEquals(10, details.size)

        controller.dispose()
    }
}
