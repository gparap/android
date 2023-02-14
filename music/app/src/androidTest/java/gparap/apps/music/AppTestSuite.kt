package gparap.apps.music

import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    MainActivityInstrumentedTest::class,
    SongActivityInstrumentedTest::class
)
class AppTestSuite