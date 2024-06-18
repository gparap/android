package gparap.apps.file_manager

import gparap.apps.file_manager.utils.Utils
import org.junit.Test

import org.junit.Assert.*

class UtilsUnitTest {
    @Test
    fun isSystemDirectory_isCorrect() {
        val dir1 = "/storage/emulated/0/DCIM"
        val dir2 = "/storage/emulated/0/Android/data"
        val dir3 = "/storage/emulated/0/Android/data/com.google.android.apps.messaging"
        assertFalse(Utils.isSystemDirectory(dir1))
        assertTrue(Utils.isSystemDirectory(dir2))
        assertTrue(Utils.isSystemDirectory(dir3))
    }

    @Test
    fun isHiddenFile_isCorrect() {
        val filename = ".nomedia"
        assertTrue(Utils.isHiddenFile(filename))
    }

    @Test
    fun isDatabaseFile_isCorrect() {
        val filename = "map_cache.db"
        assertTrue(Utils.isDatabaseFile(filename))
    }
}