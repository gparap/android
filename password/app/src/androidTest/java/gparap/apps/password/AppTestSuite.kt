/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.password

import gparap.apps.password.ui.evaluator.EvaluatorFragmentInstrumentedTest
import gparap.apps.password.ui.generator.GeneratorFragmentInstrumentedTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    MainActivityInstrumentedTest::class,
    BottomNavigationInstrumentedTest::class,
    gparap.apps.password.ui.generator.WidgetsVisibilityInstrumentedTest::class,
    GeneratorFragmentInstrumentedTest::class,
    gparap.apps.password.ui.evaluator.WidgetsVisibilityInstrumentedTest::class,
    EvaluatorFragmentInstrumentedTest::class,
    gparap.apps.password.ui.manager.WidgetsVisibilityInstrumentedTest::class
)
class AppTestSuite