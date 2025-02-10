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
    @Test
    public void isCorrect_convertCubicMeterToLiter() {
        double cubicMeters = 1;
        double litersExpected = 1000.0;
        assert litersExpected == cubicMeters * 1000;
    }

    @Test
    public void isCorrect_convertCubicMeterToMilliliter() {
        double cubicMeters = 1;
        double millilitersExpected = 1000000.0;
        assert millilitersExpected == cubicMeters * 1000000;
    }

    @Test
    public void isCorrect_convertCubicMeterToUSGallon() {
        double cubicMeters = 1;
        double usGallonsExpected = 264.172;
        assert usGallonsExpected == cubicMeters * 264.172;
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialGallon() {
        double cubicMeters = 1;
        double imperialGallonsExpected = 219.969;
        assert imperialGallonsExpected == cubicMeters * 219.969;
    }

    @Test
    public void isCorrect_convertCubicMeterToUSQuart() {
        double cubicMeters = 1;
        double usQuartsExpected = 1056.688;
        assert usQuartsExpected == cubicMeters * 1056.688;
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialQuart() {
        double cubicMeters = 1;
        double imperialQuartsExpected = 879.876;
        assert imperialQuartsExpected == cubicMeters * 879.876;
    }

    @Test
    public void isCorrect_convertCubicMeterToUSPint() {
        double cubicMeters = 1;
        double usPintsExpected = 2113.376;
        assert usPintsExpected == cubicMeters * 2113.376;
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialPint() {
        double cubicMeters = 1;
        double imperialPintsExpected = 1759.752;
        assert imperialPintsExpected == cubicMeters * 1759.752;
    }

    @Test
    public void isCorrect_convertCubicMeterToUSFluidOunce() {
        double cubicMeters = 1;
        double usFluidOuncesExpected = 33814.022;
        assert usFluidOuncesExpected == cubicMeters * 33814.022;
    }

    @Test
    public void isCorrect_convertCubicMeterToImperialFluidOunce() {
        double cubicMeters = 1;
        double imperialFluidOuncesExpected = 35195.073;
        assert imperialFluidOuncesExpected == cubicMeters * 35195.073;
    }

    @Test
    public void isCorrect_convertLiterToCubicMeter() {
        double liters = 1;
        double cubicMetersExpected = 0.001;
        assert cubicMetersExpected == liters * 0.001;
    }

    @Test
    public void isCorrect_convertLiterToMilliliter() {
        double liters = 1;
        double millilitersExpected = 1000.0;
        assert millilitersExpected == liters * 1000;
    }

    @Test
    public void isCorrect_convertLiterToUSGallon() {
        double liters = 1;
        double usGallonsExpected = 0.264172;
        assert usGallonsExpected == liters * 0.264172;
    }

    @Test
    public void isCorrect_convertLiterToImperialGallon() {
        double liters = 1;
        double imperialGallonsExpected = 0.219969;
        assert imperialGallonsExpected == liters * 0.219969;
    }

    @Test
    public void isCorrect_convertLiterToUSQuart() {
        double liters = 1;
        double usQuartsExpected = 1.056688;
        assert usQuartsExpected == liters * 1.056688;
    }

    @Test
    public void isCorrect_convertLiterToImperialQuart() {
        double liters = 1;
        double imperialQuartsExpected = 0.879876;
        assert imperialQuartsExpected == liters * 0.879876;
    }

    @Test
    public void isCorrect_convertLiterToUSPint() {
        double liters = 1;
        double usPintsExpected = 2.113376;
        assert usPintsExpected == liters * 2.113376;
    }

    @Test
    public void isCorrect_convertLiterToImperialPint() {
        double liters = 1;
        double imperialPintsExpected = 1.759752;
        assert imperialPintsExpected == liters * 1.759752;
    }

    @Test
    public void isCorrect_convertLiterToUSFluidOunce() {
        double liters = 1;
        double usFluidOuncesExpected = 33.814022;
        assert usFluidOuncesExpected == liters * 33.814022;
    }

    @Test
    public void isCorrect_convertLiterToImperialFluidOunce() {
        double liters = 1;
        double imperialFluidOuncesExpected = 35.195073;
        assert imperialFluidOuncesExpected == liters * 35.195073;
    }

    @Test
    public void isCorrect_convertMilliliterToCubicMeter() {
        double milliliters = 1;
        double cubicMetersExpected = 1.0E-6;
        assert cubicMetersExpected == milliliters * 0.000001;
    }

    @Test
    public void isCorrect_convertMilliliterToLiter() {
        double milliliters = 1;
        double litersExpected = 0.001;
        assert litersExpected == milliliters * 0.001;
    }

    @Test
    public void isCorrect_convertMilliliterToUSGallon() {
        double milliliters = 1;
        double usGallonsExpected = 2.64172E-4;
        assert usGallonsExpected == milliliters * 0.000264172;
    }

    @Test
    public void isCorrect_convertMilliliterToImperialGallon() {
        double milliliters = 1;
        double imperialGallonsExpected = 2.19969E-4;
        assert imperialGallonsExpected == milliliters * 0.000219969;
    }

    @Test
    public void isCorrect_convertMilliliterToUSQuart() {
        double milliliters = 1;
        double usQuartsExpected = 0.001056688;
        assert usQuartsExpected == milliliters * 0.001056688;
    }

    @Test
    public void isCorrect_convertMilliliterToImperialQuart() {
        double milliliters = 1;
        double imperialQuartsExpected = 8.79876E-4;
        assert imperialQuartsExpected == milliliters * 0.000879876;
    }

    @Test
    public void isCorrect_convertMilliliterToUSPint() {
        double milliliters = 1;
        double usPintsExpected = 0.002113376;
        assert usPintsExpected == milliliters * 0.002113376;
    }

    @Test
    public void isCorrect_convertMilliliterToImperialPint() {
        double milliliters = 1;
        double imperialPintsExpected = 0.001759752;
        assert imperialPintsExpected == milliliters * 0.001759752;
    }

    @Test
    public void isCorrect_convertMilliliterToUSFluidOunce() {
        double milliliters = 1;
        double usFluidOuncesExpected = 0.033814022;
        assert usFluidOuncesExpected == milliliters * 0.033814022;
    }

    @Test
    public void isCorrect_convertMilliliterToImperialFluidOunce() {
        double milliliters = 1;
        double imperialFluidOuncesExpected = 0.035195073;
        assert imperialFluidOuncesExpected == milliliters * 0.035195073;
    }

    @Test
    public void isCorrect_convertUSGallonToCubicMeter() {
        double usGallons = 1;
        double cubicMetersExpected = 0.00378541;
        assert cubicMetersExpected == usGallons * 0.00378541;
    }

    @Test
    public void isCorrect_convertUSGallonToLiter() {
        double usGallons = 1;
        double litersExpected = 3.78541;
        assert litersExpected == usGallons * 3.78541;
    }

    @Test
    public void isCorrect_convertUSGallonToMilliliter() {
        double usGallons = 1;
        double millilitersExpected = 3785.41;
        assert millilitersExpected == usGallons * 3785.41;
    }

    @Test
    public void isCorrect_convertUSGallonToImperialGallon() {
        double usGallons = 1;
        double imperialGallonsExpected = 0.832674;
        assert imperialGallonsExpected == usGallons * 0.832674;
    }

    @Test
    public void isCorrect_convertUSGallonToUSQuart() {
        double usGallons = 1;
        double usQuartsExpected = 4.0;
        assert usQuartsExpected == usGallons * 4;
    }

    @Test
    public void isCorrect_convertUSGallonToImperialQuart() {
        double usGallons = 1;
        double imperialQuartsExpected = 3.330695;
        assert imperialQuartsExpected == usGallons * 3.330695;
    }

    @Test
    public void isCorrect_convertUSGallonToUSPint() {
        double usGallons = 1;
        double usPintsExpected = 8.0;
        assert usPintsExpected == usGallons * 8;
    }

    @Test
    public void isCorrect_convertUSGallonToImperialPint() {
        double usGallons = 1;
        double imperialPintsExpected = 6.66139;
        assert imperialPintsExpected == usGallons * 6.66139;
    }

    @Test
    public void isCorrect_convertUSGallonToUSFluidOunce() {
        double usGallons = 1;
        double usFluidOuncesExpected = 128.0;
        assert usFluidOuncesExpected == usGallons * 128;
    }

    @Test
    public void isCorrect_convertUSGallonToImperialFluidOunce() {
        double usGallons = 1;
        double imperialFluidOuncesExpected = 133.227;
        assert imperialFluidOuncesExpected == usGallons * 133.227;
    }

    @Test
    public void isCorrect_convertImperialGallonToCubicMeter() {
        double imperialGallons = 1;
        double cubicMetersExpected = 0.00454609;
        assert cubicMetersExpected == imperialGallons * 0.00454609;
    }

    @Test
    public void isCorrect_convertImperialGallonToLiter() {
        double imperialGallons = 1;
        double litersExpected = 4.54609;
        assert litersExpected == imperialGallons * 4.54609;
    }

    @Test
    public void isCorrect_convertImperialGallonToMilliliter() {
        double imperialGallons = 1;
        double millilitersExpected = 4546.09;
        assert millilitersExpected == imperialGallons * 4546.09;
    }

    @Test
    public void isCorrect_convertImperialGallonToUSGallon() {
        double imperialGallons = 1;
        double usGallonsExpected = 1.20095;
        assert usGallonsExpected == imperialGallons * 1.20095;
    }

    @Test
    public void isCorrect_convertImperialGallonToUSQuart() {
        double imperialGallons = 1;
        double usQuartsExpected = 4.80064;
        assert usQuartsExpected == imperialGallons * 4.80064;
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialQuart() {
        double imperialGallons = 1;
        double imperialQuartsExpected = 4.0;
        assert imperialQuartsExpected == imperialGallons * 4;
    }

    @Test
    public void isCorrect_convertImperialGallonToUSPint() {
        double imperialGallons = 1;
        double usPintsExpected = 9.60127;
        assert usPintsExpected == imperialGallons * 9.60127;
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialPint() {
        double imperialGallons = 1;
        double imperialPintsExpected = 8.0;
        assert imperialPintsExpected == imperialGallons * 8;
    }

    @Test
    public void isCorrect_convertImperialGallonToUSFluidOunce() {
        double imperialGallons = 1;
        double usFluidOuncesExpected = 160.0;
        assert usFluidOuncesExpected == imperialGallons * 160;
    }

    @Test
    public void isCorrect_convertImperialGallonToImperialFluidOunce() {
        double imperialGallons = 1;
        double imperialFluidOuncesExpected = 160.0;
        assert imperialFluidOuncesExpected == imperialGallons * 160;
    }

    @Test
    public void isCorrect_convertUSQuartToCubicMeter() {
        double usQuarts = 1;
        double cubicMetersExpected = 9.46353E-4;
        assert cubicMetersExpected == usQuarts * 0.000946353;
    }

    @Test
    public void isCorrect_convertUSQuartToLiter() {
        double usQuarts = 1;
        double litersExpected = 0.946353;
        assert litersExpected == usQuarts * 0.946353;
    }

    @Test
    public void isCorrect_convertUSQuartToMilliliter() {
        double usQuarts = 1;
        double millilitersExpected = 946.353;
        assert millilitersExpected == usQuarts * 946.353;
    }

    @Test
    public void isCorrect_convertUSQuartToUSGallon() {
        double usQuarts = 1;
        double usGallonsExpected = 0.25;
        assert usGallonsExpected == usQuarts * 0.25;
    }

    @Test
    public void isCorrect_convertUSQuartToImperialGallon() {
        double usQuarts = 1;
        double imperialGallonsExpected = 0.208168;
        assert imperialGallonsExpected == usQuarts * 0.208168;
    }

    @Test
    public void isCorrect_convertUSQuartToImperialQuart() {
        double usQuarts = 1;
        double imperialQuartsExpected = 0.833348;
        assert imperialQuartsExpected == usQuarts * 0.833348;
    }

    @Test
    public void isCorrect_convertUSQuartToUSPint() {
        double usQuarts = 1;
        double usPintsExpected = 2.0;
        assert usPintsExpected == usQuarts * 2;
    }

    @Test
    public void isCorrect_convertUSQuartToImperialPint() {
        double usQuarts = 1;
        double imperialPintsExpected = 1.666696;
        assert imperialPintsExpected == usQuarts * 1.666696;
    }

    @Test
    public void isCorrect_convertUSQuartToUSFluidOunce() {
        double usQuarts = 1;
        double usFluidOuncesExpected = 32.0;
        assert usFluidOuncesExpected == usQuarts * 32;
    }

    @Test
    public void isCorrect_convertUSQuartToImperialFluidOunce() {
        double usQuarts = 1;
        double imperialFluidOuncesExpected = 33.327;
        assert imperialFluidOuncesExpected == usQuarts * 33.327;
    }

    @Test
    public void isCorrect_convertImperialQuartToCubicMeter() {
        double imperialQuarts = 1;
        double cubicMetersExpected = 0.00113652;
        assert cubicMetersExpected == imperialQuarts * 0.00113652;
    }

    @Test
    public void isCorrect_convertImperialQuartToLiter() {
        double imperialQuarts = 1;
        double litersExpected = 1.13652;
        assert litersExpected == imperialQuarts * 1.13652;
    }

    @Test
    public void isCorrect_convertImperialQuartToMilliliter() {
        double imperialQuarts = 1;
        double millilitersExpected = 1136.52;
        assert millilitersExpected == imperialQuarts * 1136.52;
    }

    @Test
    public void isCorrect_convertImperialQuartToUSGallon() {
        double imperialQuarts = 1;
        double usGallonsExpected = 0.832674;
        assert usGallonsExpected == imperialQuarts * 0.832674;
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialGallon() {
        double imperialQuarts = 1;
        double imperialGallonsExpected = 0.25;
        assert imperialGallonsExpected == imperialQuarts * 0.25;
    }

    @Test
    public void isCorrect_convertImperialQuartToUSQuart() {
        double imperialQuarts = 1;
        double usQuartsExpected = 1.20095;
        assert usQuartsExpected == imperialQuarts * 1.20095;
    }

    @Test
    public void isCorrect_convertImperialQuartToUSPint() {
        double imperialQuarts = 1;
        double usPintsExpected = 2.4009;
        assert usPintsExpected == imperialQuarts * 2.4009;
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialPint() {
        double imperialQuarts = 1;
        double imperialPintsExpected = 2.0;
        assert imperialPintsExpected == imperialQuarts * 2;
    }

    @Test
    public void isCorrect_convertImperialQuartToUSFluidOunce() {
        double imperialQuarts = 1;
        double usFluidOuncesExpected = 40.0;
        assert usFluidOuncesExpected == imperialQuarts * 40;
    }

    @Test
    public void isCorrect_convertImperialQuartToImperialFluidOunce() {
        double imperialQuarts = 1;
        double imperialFluidOuncesExpected = 40.0;
        assert imperialFluidOuncesExpected == imperialQuarts * 40;
    }

    @Test
    public void isCorrect_convertUSPintToCubicMeter() {
        double usPints = 1;
        double cubicMetersExpected = 4.73176E-4;
        assert cubicMetersExpected == usPints * 0.000473176;
    }

    @Test
    public void isCorrect_convertUSPintToLiter() {
        double usPints = 1;
        double litersExpected = 0.473176;
        assert litersExpected == usPints * 0.473176;
    }

    @Test
    public void isCorrect_convertUSPintToMilliliter() {
        double usPints = 1;
        double millilitersExpected = 473.176;
        assert millilitersExpected == usPints * 473.176;
    }

    @Test
    public void isCorrect_convertUSPintToUSGallon() {
        double usPints = 1;
        double usGallonsExpected = 0.125;
        assert usGallonsExpected == usPints * 0.125;
    }

    @Test
    public void isCorrect_convertUSPintToImperialGallon() {
        double usPints = 1;
        double imperialGallonsExpected = 0.104084;
        assert imperialGallonsExpected == usPints * 0.104084;
    }

    @Test
    public void isCorrect_convertUSPintToUSQuart() {
        double usPints = 1;
        double usQuartsExpected = 0.5;
        assert usQuartsExpected == usPints * 0.5;
    }

    @Test
    public void isCorrect_convertUSPintToImperialQuart() {
        double usPints = 1;
        double imperialQuartsExpected = 0.416674;
        assert imperialQuartsExpected == usPints * 0.416674;
    }

    @Test
    public void isCorrect_convertUSPintToImperialPint() {
        double usPints = 1;
        double imperialPintsExpected = 0.832674;
        assert imperialPintsExpected == usPints * 0.832674;
    }

    @Test
    public void isCorrect_convertUSPintToUSFluidOunce() {
        double usPints = 1;
        double usFluidOuncesExpected = 16.0;
        assert usFluidOuncesExpected == usPints * 16;
    }

    @Test
    public void isCorrect_convertUSPintToImperialFluidOunce() {
        double usPints = 1;
        double imperialFluidOuncesExpected = 16.664;
        assert imperialFluidOuncesExpected == usPints * 16.664;
    }

    @Test
    public void isCorrect_convertImperialPintToCubicMeter() {
        double imperialPints = 1;
        double cubicMetersExpected = 5.68261E-4;
        assert cubicMetersExpected == imperialPints * 0.000568261;
    }

    @Test
    public void isCorrect_convertImperialPintToLiter() {
        double imperialPints = 1;
        double litersExpected = 0.568261;
        assert litersExpected == imperialPints * 0.568261;
    }

    @Test
    public void isCorrect_convertImperialPintToMilliliter() {
        double imperialPints = 1;
        double millilitersExpected = 568.261;
        assert millilitersExpected == imperialPints * 568.261;
    }

    @Test
    public void isCorrect_convertImperialPintToUSGallon() {
        double imperialPints = 1;
        double usGallonsExpected = 0.133419;
        assert usGallonsExpected == imperialPints * 0.133419;
    }

    @Test
    public void isCorrect_convertImperialPintToImperialGallon() {
        double imperialPints = 1;
        double imperialGallonsExpected = 0.125;
        assert imperialGallonsExpected == imperialPints * 0.125;
    }

    @Test
    public void isCorrect_convertImperialPintToUSQuart() {
        double imperialPints = 1;
        double usQuartsExpected = 0.625;
        assert usQuartsExpected == imperialPints * 0.625;
    }

    @Test
    public void isCorrect_convertImperialPintToImperialQuart() {
        double imperialPints = 1;
        double imperialQuartsExpected = 0.5;
        assert imperialQuartsExpected == imperialPints * 0.5;
    }

    @Test
    public void isCorrect_convertImperialPintToUSPint() {
        double imperialPints = 1;
        double imperialPintsExpected = 1.2009501917917456;
        assert imperialPintsExpected == imperialPints / 0.832674;
    }

    @Test
    public void isCorrect_convertImperialPintToUSFluidOunce() {
        double imperialPints = 1;
        double usFluidOuncesExpected = 20.0;
        assert usFluidOuncesExpected == imperialPints * 20;
    }

    @Test
    public void isCorrect_convertImperialPintToImperialFluidOunce() {
        double imperialPints = 1;
        double imperialFluidOuncesExpected = 20.0;
        assert imperialFluidOuncesExpected == imperialPints * 20;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToCubicMeter() {
        double usFluidOunces = 1;
        double cubicMetersExpected = 2.95735E-5;
        assert cubicMetersExpected == usFluidOunces * 0.0000295735;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToLiter() {
        double usFluidOunces = 1;
        double litersExpected = 0.0295735;
        assert litersExpected == usFluidOunces * 0.0295735;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToMilliliter() {
        double usFluidOunces = 1;
        double millilitersExpected = 29.5735;
        assert millilitersExpected == usFluidOunces * 29.5735;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSGallon() {
        double usFluidOunces = 1;
        double usGallonsExpected = 0.0078125;
        assert usGallonsExpected == usFluidOunces * 0.0078125;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialGallon() {
        double usFluidOunces = 1;
        double imperialGallonsExpected = 0.0074607;
        assert imperialGallonsExpected == usFluidOunces * 0.0074607;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSQuart() {
        double usFluidOunces = 1;
        double usQuartsExpected = 0.03125;
        assert usQuartsExpected == usFluidOunces * 0.03125;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialQuart() {
        double usFluidOunces = 1;
        double imperialQuartsExpected = 0.031619;
        assert imperialQuartsExpected == usFluidOunces * 0.031619;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToUSPint() {
        double usFluidOunces = 1;
        double usPintsExpected = 0.0625;
        assert usPintsExpected == usFluidOunces * 0.0625;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialPint() {
        double usFluidOunces = 1;
        double imperialPintsExpected = 0.063238;
        assert imperialPintsExpected == usFluidOunces * 0.063238;
    }

    @Test
    public void isCorrect_convertUSFluidOunceToImperialFluidOunce() {
        double usFluidOunces = 1;
        double imperialFluidOuncesExpected = 0.96076;
        assert imperialFluidOuncesExpected == usFluidOunces * 0.96076;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToCubicMeter() {
        double imperialFluidOunces = 1;
        double cubicMetersExpected = 2.84131E-5;
        assert cubicMetersExpected == imperialFluidOunces * 0.0000284131;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToLiter() {
        double imperialFluidOunces = 1;
        double litersExpected = 0.0284131;
        assert litersExpected == imperialFluidOunces * 0.0284131;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToMilliliter() {
        double imperialFluidOunces = 1;
        double millilitersExpected = 28.4131;
        assert millilitersExpected == imperialFluidOunces * 28.4131;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSGallon() {
        double imperialFluidOunces = 1;
        double usGallonsExpected = 0.00750594;
        assert usGallonsExpected == imperialFluidOunces * 0.00750594;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialGallon() {
        double imperialFluidOunces = 1;
        double imperialGallonsExpected = 0.0078125;
        assert imperialGallonsExpected == imperialFluidOunces * 0.0078125;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSQuart() {
        double imperialFluidOunces = 1;
        double usQuartsExpected = 0.03125;
        assert usQuartsExpected == imperialFluidOunces * 0.03125;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialQuart() {
        double imperialFluidOunces = 1;
        double imperialQuartsExpected = 0.03125;
        assert imperialQuartsExpected == imperialFluidOunces * 0.03125;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSPint() {
        double imperialFluidOunces = 1;
        double usPintsExpected = 0.0625;
        assert usPintsExpected == imperialFluidOunces * 0.0625;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToImperialPint() {
        double imperialFluidOunces = 1;
        double imperialPintsExpected = 0.0625;
        assert imperialPintsExpected == imperialFluidOunces * 0.0625;
    }

    @Test
    public void isCorrect_convertImperialFluidOunceToUSFluidOunce() {
        double imperialFluidOunces = 1;
        double usFluidOuncesExpected = 1.0408426662225738;
        assert usFluidOuncesExpected == imperialFluidOunces / 0.96076;
    }
}
