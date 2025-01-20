/*
 * Copyright 2025 gparap
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
package gparap.apps.converter_unit.converters;

import org.junit.Test;

public class AnglesConverterUnitTest {
    private final AnglesConverter converter = new AnglesConverter();

    @Test
    public void isCorrect_convertDegreeToRadian() {
        double degrees = 1;
        double radiansExpected = 0.017453292519943295;
        assert radiansExpected == converter.convertDegreeToRadian(degrees);
    }

    @Test
    public void isCorrect_convertDegreeToGradian() {
        double degrees = 1;
        double gradiansExpected = 1.1111111111111112;
        assert gradiansExpected == converter.convertDegreeToGradian(degrees);
    }

    @Test
    public void isCorrect_convertRadianToDegree() {
        double radians =  0.017453292519943295;
        double degreesExpected = 1;
        assert degreesExpected == converter.convertRadianToDegree(radians);
    }

    @Test
    public void isCorrect_convertRadianToGradian() {
        double radians =  0.017453292519943295;
        double gradiansExpected = 1.1111111111111112;
        assert gradiansExpected == converter.convertRadianToGradian(radians);
    }

    @Test
    public void isCorrect_convertGradianToDegree() {
        double gradians = 1.1111111111111112;
        double degreesExpected = 1;
        assert degreesExpected == converter.convertGradianToDegree(gradians);
    }

    @Test
    public void isCorrect_convertGradianToRadian() {
        double gradians = 1.1111111111111112;
        double radiansExpected = 0.017453292519943295;
        assert radiansExpected == converter.convertGradianToRadian(gradians);
    }
}
