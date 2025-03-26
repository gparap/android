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

public class VolumeConverterUnitTest {
    private final VolumeConverter converter = new VolumeConverter();

    @Test
    public void isCorrect_convertCubicMeterToLiter() {
        double cubicMeters = 1;
        double litersExpected = 1000.0;
        assert litersExpected == converter.convertCubicMeterToLiter(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToMilliliter() {
        double cubicMeters = 1;
        double millilitersExpected = 1000000.0;
        assert millilitersExpected == converter.convertCubicMeterToMilliliter(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToUSGallon() {
        double cubicMeters = 1;
        double usGallonsExpected = 264.172;
        assert usGallonsExpected == converter.convertCubicMeterToUSGallon(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialGallon() {
        double cubicMeters = 1;
        double imperialGallonsExpected = 219.969;
        assert imperialGallonsExpected == converter.convertCubicMeterToImperialGallon(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToUSQuart() {
        double cubicMeters = 1;
        double usQuartsExpected = 1056.688;
        assert usQuartsExpected == converter.convertCubicMeterToUSQuart(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialQuart() {
        double cubicMeters = 1;
        double imperialQuartsExpected = 879.876;
        assert imperialQuartsExpected == converter.convertCubicMeterToImperialQuart(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToUSPint() {
        double cubicMeters = 1;
        double usPintsExpected = 2113.376;
        assert usPintsExpected == converter.convertCubicMeterToUSPint(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialPint() {
        double cubicMeters = 1;
        double imperialPintsExpected = 1759.752;
        assert imperialPintsExpected == converter.convertCubicMeterToImperialPint(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToUSFluidOunce() {
        double cubicMeters = 1;
        double usFluidOuncesExpected = 33814.022;
        assert usFluidOuncesExpected == converter.convertCubicMeterToUSFluidOunce(cubicMeters);
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialFluidOunce() {
        double cubicMeters = 1;
        double imperialFluidOuncesExpected = 35195.073;
        assert imperialFluidOuncesExpected == converter.convertCubicMeterToImperialFluidOunce(cubicMeters);
    }

    @Test
    public void isCorrect_convertLiterToCubicMeter() {
        double liters = 1;
        double cubicMetersExpected = 0.001;
        assert cubicMetersExpected == converter.convertLiterToCubicMeter(liters);
    }

    @Test
    public void isCorrect_convertLiterToMilliliter() {
        double liters = 1;
        double millilitersExpected = 1000.0;
        assert millilitersExpected == converter.convertLiterToMilliliter(liters);
    }

    @Test
    public void isCorrect_convertLiterToUSGallon() {
        double liters = 1;
        double usGallonsExpected = 0.264172;
        assert usGallonsExpected == converter.convertLiterToUSGallon(liters);
    }

    @Test
    public void isCorrect_convertLiterToImperialGallon() {
        double liters = 1;
        double imperialGallonsExpected = 0.219969;
        assert imperialGallonsExpected == converter.convertLiterToImperialGallon(liters);
    }

    @Test
    public void isCorrect_convertLiterToUSQuart() {
        double liters = 1;
        double usQuartsExpected = 1.056688;
        assert usQuartsExpected == converter.convertLiterToUSQuart(liters);
    }

    @Test
    public void isCorrect_convertLiterToImperialQuart() {
        double liters = 1;
        double imperialQuartsExpected = 0.879876;
        assert imperialQuartsExpected == converter.convertLiterToImperialQuart(liters);
    }

    @Test
    public void isCorrect_convertLiterToUSPint() {
        double liters = 1;
        double usPintsExpected = 2.113376;
        assert usPintsExpected == converter.convertLiterToUSPint(liters);
    }

    @Test
    public void isCorrect_convertLiterToImperialPint() {
        double liters = 1;
        double imperialPintsExpected = 1.759752;
        assert imperialPintsExpected == converter.convertLiterToImperialPint(liters);
    }

    @Test
    public void isCorrect_convertLiterToUSFluidOunce() {
        double liters = 1;
        double usFluidOuncesExpected = 33.814022;
        assert usFluidOuncesExpected == converter.convertLiterToUSFluidOunce(liters);
    }

    @Test
    public void isCorrect_convertLiterToImperialFluidOunce() {
        double liters = 1;
        double imperialFluidOuncesExpected = 35.195073;
        assert imperialFluidOuncesExpected == converter.convertLiterToImperialFluidOunce(liters);
    }

    @Test
    public void isCorrect_convertMilliliterToCubicMeter() {
        double milliliters = 1;
        double cubicMetersExpected = 1.0E-6;
        assert cubicMetersExpected == converter.convertMilliliterToCubicMeter(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToLiter() {
        double milliliters = 1;
        double litersExpected = 0.001;
        assert litersExpected == converter.convertMilliliterToLiter(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToUSGallon() {
        double milliliters = 1;
        double usGallonsExpected = 2.64172E-4;
        assert usGallonsExpected == converter.convertMilliliterToUSGallon(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToImperialGallon() {
        double milliliters = 1;
        double imperialGallonsExpected = 2.19969E-4;
        assert imperialGallonsExpected == converter.convertMilliliterToImperialGallon(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToUSQuart() {
        double milliliters = 1;
        double usQuartsExpected = 0.001056688;
        assert usQuartsExpected == converter.convertMilliliterToUSQuart(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToImperialQuart() {
        double milliliters = 1;
        double imperialQuartsExpected = 8.79876E-4;
        assert imperialQuartsExpected == converter.convertMilliliterToImperialQuart(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToUSPint() {
        double milliliters = 1;
        double usPintsExpected = 0.002113376;
        assert usPintsExpected == converter.convertMilliliterToUSPint(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToImperialPint() {
        double milliliters = 1;
        double imperialPintsExpected = 0.001759752;
        assert imperialPintsExpected == converter.convertMilliliterToImperialPint(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToUSFluidOunce() {
        double milliliters = 1;
        double usFluidOuncesExpected = 0.033814022;
        assert usFluidOuncesExpected == converter.convertMilliliterToUSFluidOunce(milliliters);
    }

    @Test
    public void isCorrect_convertMilliliterToImperialFluidOunce() {
        double milliliters = 1;
        double imperialFluidOuncesExpected = 0.035195073;
        assert imperialFluidOuncesExpected == converter.convertMilliliterToImperialFluidOunce(milliliters);
    }

    @Test
    public void isCorrect_convertUSGallonToCubicMeter() {
        double usGallons = 1;
        double cubicMetersExpected = 0.00378541;
        assert cubicMetersExpected == converter.convertUSGallonToCubicMeter(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToLiter() {
        double usGallons = 1;
        double litersExpected = 3.78541;
        assert litersExpected == converter.convertUSGallonToLiter(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToMilliliter() {
        double usGallons = 1;
        double millilitersExpected = 3785.41;
        assert millilitersExpected == converter.convertUSGallonToMilliliter(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToImperialGallon() {
        double usGallons = 1;
        double imperialGallonsExpected = 0.832674;
        assert imperialGallonsExpected == converter.convertUSGallonToImperialGallon(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToUSQuart() {
        double usGallons = 1;
        double usQuartsExpected = 4.0;
        assert usQuartsExpected == converter.convertUSGallonToUSQuart(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToImperialQuart() {
        double usGallons = 1;
        double imperialQuartsExpected = 3.330695;
        assert imperialQuartsExpected == converter.convertUSGallonToImperialQuart(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToUSPint() {
        double usGallons = 1;
        double usPintsExpected = 8.0;
        assert usPintsExpected == converter.convertUSGallonToUSPint(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToImperialPint() {
        double usGallons = 1;
        double imperialPintsExpected = 6.66139;
        assert imperialPintsExpected == converter.convertUSGallonToImperialPint(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToUSFluidOunce() {
        double usGallons = 1;
        double usFluidOuncesExpected = 128.0;
        assert usFluidOuncesExpected == converter.convertUSGallonToUSFluidOunce(usGallons);
    }

    @Test
    public void isCorrect_convertUSGallonToImperialFluidOunce() {
        double usGallons = 1;
        double imperialFluidOuncesExpected = 133.227;
        assert imperialFluidOuncesExpected == converter.convertUSGallonToImperialFluidOunce(usGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToCubicMeter() {
        double imperialGallons = 1;
        double cubicMetersExpected = 0.00454609;
        assert cubicMetersExpected == converter.convertImperialGallonToCubicMeter(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToLiter() {
        double imperialGallons = 1;
        double litersExpected = 4.54609;
        assert litersExpected == converter.convertImperialGallonToLiter(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToMilliliter() {
        double imperialGallons = 1;
        double millilitersExpected = 4546.09;
        assert millilitersExpected == converter.convertImperialGallonToMilliliter(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToUSGallon() {
        double imperialGallons = 1;
        double usGallonsExpected = 1.20095;
        assert usGallonsExpected == converter.convertImperialGallonToUSGallon(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToUSQuart() {
        double imperialGallons = 1;
        double usQuartsExpected = 4.80064;
        assert usQuartsExpected == converter.convertImperialGallonToUSQuart(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialQuart() {
        double imperialGallons = 1;
        double imperialQuartsExpected = 4.0;
        assert imperialQuartsExpected == converter.convertImperialGallonToImperialQuart(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToUSPint() {
        double imperialGallons = 1;
        double usPintsExpected = 9.60127;
        assert usPintsExpected == converter.convertImperialGallonToUSPint(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialPint() {
        double imperialGallons = 1;
        double imperialPintsExpected = 8.0;
        assert imperialPintsExpected == converter.convertImperialGallonToImperialPint(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToUSFluidOunce() {
        double imperialGallons = 1;
        double usFluidOuncesExpected = 153.722;
        assert usFluidOuncesExpected == converter.convertImperialGallonToUSFluidOunce(imperialGallons);
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialFluidOunce() {
        double imperialGallons = 1;
        double imperialFluidOuncesExpected = 160.0;
        assert imperialFluidOuncesExpected == converter.convertImperialGallonToImperialFluidOunce(imperialGallons);
    }

    @Test
    public void isCorrect_convertUSQuartToCubicMeter() {
        double usQuarts = 1;
        double cubicMetersExpected = 9.46353E-4;
        assert cubicMetersExpected == converter.convertUSQuartToCubicMeter(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToLiter() {
        double usQuarts = 1;
        double litersExpected = 0.946353;
        assert litersExpected == converter.convertUSQuartToLiter(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToMilliliter() {
        double usQuarts = 1;
        double millilitersExpected = 946.353;
        assert millilitersExpected == converter.convertUSQuartToMilliliter(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToUSGallon() {
        double usQuarts = 1;
        double usGallonsExpected = 0.25;
        assert usGallonsExpected == converter.convertUSQuartToUSGallon(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToImperialGallon() {
        double usQuarts = 1;
        double imperialGallonsExpected = 0.208168;
        assert imperialGallonsExpected == converter.convertUSQuartToImperialGallon(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToImperialQuart() {
        double usQuarts = 1;
        double imperialQuartsExpected = 0.833348;
        assert imperialQuartsExpected == converter.convertUSQuartToImperialQuart(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToUSPint() {
        double usQuarts = 1;
        double usPintsExpected = 2.0;
        assert usPintsExpected == converter.convertUSQuartToUSPint(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToImperialPint() {
        double usQuarts = 1;
        double imperialPintsExpected = 1.666696;
        assert imperialPintsExpected == converter.convertUSQuartToImperialPint(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToUSFluidOunce() {
        double usQuarts = 1;
        double usFluidOuncesExpected = 32.0;
        assert usFluidOuncesExpected == converter.convertUSQuartToUSFluidOunce(usQuarts);
    }

    @Test
    public void isCorrect_convertUSQuartToImperialFluidOunce() {
        double usQuarts = 1;
        double imperialFluidOuncesExpected = 33.327;
        assert imperialFluidOuncesExpected == converter.convertUSQuartToImperialFluidOunce(usQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToCubicMeter() {
        double imperialQuarts = 1;
        double cubicMetersExpected = 0.00113652;
        assert cubicMetersExpected == converter.convertImperialQuartToCubicMeter(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToLiter() {
        double imperialQuarts = 1;
        double litersExpected = 1.13652;
        assert litersExpected == converter.convertImperialQuartToLiter(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToMilliliter() {
        double imperialQuarts = 1;
        double millilitersExpected = 1136.52;
        assert millilitersExpected == converter.convertImperialQuartToMilliliter(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToUSGallon() {
        double imperialQuarts = 1;
        double usGallonsExpected = 0.832674;
        assert usGallonsExpected == converter.convertImperialQuartToUSGallon(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialGallon() {
        double imperialQuarts = 1;
        double imperialGallonsExpected = 0.25;
        assert imperialGallonsExpected == converter.convertImperialQuartToImperialGallon(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToUSQuart() {
        double imperialQuarts = 1;
        double usQuartsExpected = 1.20095;
        assert usQuartsExpected == converter.convertImperialQuartToUSQuart(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToUSPint() {
        double imperialQuarts = 1;
        double usPintsExpected = 2.4009;
        assert usPintsExpected == converter.convertImperialQuartToUSPint(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialPint() {
        double imperialQuarts = 1;
        double imperialPintsExpected = 2.0;
        assert imperialPintsExpected == converter.convertImperialQuartToImperialPint(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToUSFluidOunce() {
        double imperialQuarts = 1;
        double usFluidOuncesExpected = 38.43;
        assert usFluidOuncesExpected == converter.convertImperialQuartToUSFluidOunce(imperialQuarts);
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialFluidOunce() {
        double imperialQuarts = 1;
        double imperialFluidOuncesExpected = 40.0;
        assert imperialFluidOuncesExpected == converter.convertImperialQuartToImperialFluidOunce(imperialQuarts);
    }

    @Test
    public void isCorrect_convertUSPintToCubicMeter() {
        double usPints = 1;
        double cubicMetersExpected = 4.73176E-4;
        assert cubicMetersExpected == converter.convertUSPintToCubicMeter(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToLiter() {
        double usPints = 1;
        double litersExpected = 0.473176;
        assert litersExpected == converter.convertUSPintToLiter(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToMilliliter() {
        double usPints = 1;
        double millilitersExpected = 473.176;
        assert millilitersExpected == converter.convertUSPintToMilliliter(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToUSGallon() {
        double usPints = 1;
        double usGallonsExpected = 0.125;
        assert usGallonsExpected == converter.convertUSPintToUSGallon(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToImperialGallon() {
        double usPints = 1;
        double imperialGallonsExpected = 0.104084;
        assert imperialGallonsExpected == converter.convertUSPintToImperialGallon(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToUSQuart() {
        double usPints = 1;
        double usQuartsExpected = 0.5;
        assert usQuartsExpected == converter.convertUSPintToUSQuart(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToImperialQuart() {
        double usPints = 1;
        double imperialQuartsExpected = 0.416674;
        assert imperialQuartsExpected == converter.convertUSPintToImperialQuart(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToImperialPint() {
        double usPints = 1;
        double imperialPintsExpected = 0.832674;
        assert imperialPintsExpected == converter.convertUSPintToImperialPint(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToUSFluidOunce() {
        double usPints = 1;
        double usFluidOuncesExpected = 16.0;
        assert usFluidOuncesExpected == converter.convertUSPintToUSFluidOunce(usPints);
    }

    @Test
    public void isCorrect_convertUSPintToImperialFluidOunce() {
        double usPints = 1;
        double imperialFluidOuncesExpected = 16.664;
        assert imperialFluidOuncesExpected == converter.convertUSPintToImperialFluidOunce(usPints);
    }

    @Test
    public void isCorrect_convertImperialPintToCubicMeter() {
        double imperialPints = 1;
        double cubicMetersExpected = 5.68261E-4;
        assert cubicMetersExpected == converter.convertImperialPintToCubicMeter(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToLiter() {
        double imperialPints = 1;
        double litersExpected = 0.568261;
        assert litersExpected == converter.convertImperialPintToLiter(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToMilliliter() {
        double imperialPints = 1;
        double millilitersExpected = 568.261;
        assert millilitersExpected == converter.convertImperialPintToMilliliter(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToUSGallon() {
        double imperialPints = 1;
        double usGallonsExpected = 0.133419;
        assert usGallonsExpected == converter.convertImperialPintToUSGallon(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToImperialGallon() {
        double imperialPints = 1;
        double imperialGallonsExpected = 0.125;
        assert imperialGallonsExpected == converter.convertImperialPintToImperialGallon(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToUSQuart() {
        double imperialPints = 1;
        double usQuartsExpected = 0.625;
        assert usQuartsExpected == converter.convertImperialPintToUSQuart(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToImperialQuart() {
        double imperialPints = 1;
        double imperialQuartsExpected = 0.5;
        assert imperialQuartsExpected == converter.convertImperialPintToImperialQuart(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToUSPint() {
        double imperialPints = 1;
        double imperialPintsExpected = 1.20095;
        assert imperialPintsExpected == converter.convertImperialPintToUSPint(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToUSFluidOunce() {
        double imperialPints = 1;
        double usFluidOuncesExpected = 19.215;
        assert usFluidOuncesExpected == converter.convertImperialPintToUSFluidOunce(imperialPints);
    }

    @Test
    public void isCorrect_convertImperialPintToImperialFluidOunce() {
        double imperialPints = 1;
        double imperialFluidOuncesExpected = 20.0;
        assert imperialFluidOuncesExpected == converter.convertImperialPintToImperialFluidOunce(imperialPints);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToCubicMeter() {
        double usFluidOunces = 1;
        double cubicMetersExpected = 2.95735E-5;
        assert cubicMetersExpected == converter.convertUSFluidOunceToCubicMeter(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToLiter() {
        double usFluidOunces = 1;
        double litersExpected = 0.0295735;
        assert litersExpected == converter.convertUSFluidOunceToLiter(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToMilliliter() {
        double usFluidOunces = 1;
        double millilitersExpected = 29.5735;
        assert millilitersExpected == converter.convertUSFluidOunceToMilliliter(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSGallon() {
        double usFluidOunces = 1;
        double usGallonsExpected = 0.0078125;
        assert usGallonsExpected == converter.convertUSFluidOunceToUSGallon(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialGallon() {
        double usFluidOunces = 1;
        double imperialGallonsExpected = 0.0074607;
        assert imperialGallonsExpected == converter.convertUSFluidOunceToImperialGallon(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSQuart() {
        double usFluidOunces = 1;
        double usQuartsExpected = 0.03125;
        assert usQuartsExpected == converter.convertUSFluidOunceToUSQuart(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialQuart() {
        double usFluidOunces = 1;
        double imperialQuartsExpected = 0.031619;
        assert imperialQuartsExpected == converter.convertUSFluidOunceToImperialQuart(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSPint() {
        double usFluidOunces = 1;
        double usPintsExpected = 0.0625;
        assert usPintsExpected == converter.convertUSFluidOunceToUSPint(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialPint() {
        double usFluidOunces = 1;
        double imperialPintsExpected = 0.063238;
        assert imperialPintsExpected == converter.convertUSFluidOunceToImperialPint(usFluidOunces);
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialFluidOunce() {
        double usFluidOunces = 1;
        double imperialFluidOuncesExpected = 0.96076;
        assert imperialFluidOuncesExpected == converter.convertUSFluidOunceToImperialFluidOunce(usFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToCubicMeter() {
        double imperialFluidOunces = 1;
        double cubicMetersExpected = 2.84131E-5;
        assert cubicMetersExpected == converter.convertImperialFluidOunceToCubicMeter(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToLiter() {
        double imperialFluidOunces = 1;
        double litersExpected = 0.0284131;
        assert litersExpected == converter.convertImperialFluidOunceToLiter(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToMilliliter() {
        double imperialFluidOunces = 1;
        double millilitersExpected = 28.4131;
        assert millilitersExpected == converter.convertImperialFluidOunceToMilliliter(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSGallon() {
        double imperialFluidOunces = 1;
        double usGallonsExpected = 0.00750594;
        assert usGallonsExpected == converter.convertImperialFluidOunceToUSGallon(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialGallon() {
        double imperialFluidOunces = 1;
        double imperialGallonsExpected = 0.0078125;
        assert imperialGallonsExpected == converter.convertImperialFluidOunceToImperialGallon(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSQuart() {
        double imperialFluidOunces = 1;
        double usQuartsExpected = 0.030;
        assert usQuartsExpected == converter.convertImperialFluidOunceToUSQuart(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialQuart() {
        double imperialFluidOunces = 1;
        double imperialQuartsExpected = 0.025;
        assert imperialQuartsExpected == converter.convertImperialFluidOunceToImperialQuart(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSPint() {
        double imperialFluidOunces = 1;
        double usPintsExpected = 0.06;
        assert usPintsExpected == converter.convertImperialFluidOunceToUSPint(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialPint() {
        double imperialFluidOunces = 1;
        double imperialPintsExpected = 0.05;
        assert imperialPintsExpected == converter.convertImperialFluidOunceToImperialPint(imperialFluidOunces);
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSFluidOunce() {
        double imperialFluidOunces = 1;
        double usFluidOuncesExpected = 1.042;
        assert usFluidOuncesExpected == converter.convertImperialFluidOunceToUSFluidOunce(imperialFluidOunces);
    }
}
