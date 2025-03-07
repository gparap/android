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

public class AreaConverterUnitTest {
    private final AreaConverter converter = new AreaConverter();

    @Test
    public void isCorrect_convertSquareMeterToSquareKilometer() {
        int squareMeter = 1;
        double squareKilometerExpected = 0.000001;
        assert squareKilometerExpected == converter.convertSquareMeterToSquareKilometer(squareMeter);
    }

    @Test
    public void isCorrect_convertSquareMeterToSquareMile() {
        int squareMeter = 1;
        double squareMileExpected = 3.86102158592535E-7;
        assert squareMileExpected == converter.convertSquareMeterToSquareMile(squareMeter);
    }

    @Test
    public void isCorrect_convertSquareMeterToHectare() {
        int squareMeter = 1;
        double hectareExpected = 0.0001;
        assert hectareExpected == converter.convertSquareMeterToHectare(squareMeter);
    }

    @Test
    public void isCorrect_convertSquareMeterToAcre() {
        int squareMeter = 1;
        double acreExpected = 2.471053814671653E-4;
        assert acreExpected == converter.convertSquareMeterToAcre(squareMeter);
    }

    @Test
    public void isCorrect_convertSquareKilometerToSquareMeter() {
        int squareKilometer = 1;
        double squareMeterExpected = 1000000.0;
        assert squareMeterExpected == converter.convertSquareKilometerToSquareMeter(squareKilometer);
    }

    @Test
    public void isCorrect_convertSquareKilometerToSquareMile() {
        int squareKilometer = 1;
        double squareMileExpected = 0.386102158592535;
        assert squareMileExpected == converter.convertSquareKilometerToSquareMile(squareKilometer);
    }

    @Test
    public void isCorrect_convertSquareKilometerToHectare() {
        int squareKilometer = 1;
        double hectareExpected = 100.0;
        assert hectareExpected == converter.convertSquareKilometerToHectare(squareKilometer);
    }

    @Test
    public void isCorrect_convertSquareKilometerToAcre() {
        int squareKilometer = 1;
        double acreExpected = 247.1053814672;
        assert acreExpected == converter.convertSquareKilometerToAcre(squareKilometer);
    }

    @Test
    public void isCorrect_convertSquareMileToSquareMeter() {
        int squareMile = 1;
        double squareMeterExpected = 2589988.11;
        assert squareMeterExpected == converter.convertSquareMileToSquareMeter(squareMile);
    }

    @Test
    public void isCorrect_convertSquareMileToSquareKilometer() {
        int squareMile = 1;
        double squareKilometerExpected = 2.58998811;
        assert squareKilometerExpected == converter.convertSquareMileToSquareKilometer(squareMile);
    }

    @Test
    public void isCorrect_convertSquareMileToHectare() {
        int squareMile = 1;
        double hectareExpected = 258.998811;
        assert hectareExpected == converter.convertSquareMileToHectare(squareMile);
    }

    @Test
    public void isCorrect_convertSquareMileToAcre() {
        int squareMile = 1;
        double acreExpected = 640.0;
        assert acreExpected == converter.convertSquareMileToAcre(squareMile);
    }

    @Test
    public void isCorrect_convertHectareToSquareMeter() {
        int hectare = 1;
        double squareMeterExpected = 10000.0;
        assert squareMeterExpected == converter.convertHectareToSquareMeter(hectare);
    }

    @Test
    public void isCorrect_convertHectareToSquareKilometer() {
        int hectare = 1;
        double squareKilometerExpected = 0.01;
        assert squareKilometerExpected == converter.convertHectareToSquareKilometer(hectare);
    }

    @Test
    public void isCorrect_convertHectareToSquareMile() {
        int hectare = 1;
        double squareMileExpected = 0.0038610215859253504;
        assert squareMileExpected == converter.convertHectareToSquareMile(hectare);
    }

    @Test
    public void isCorrect_convertHectareToAcre() {
        int hectare = 1;
        double acreExpected = 2.471053814672;
        assert acreExpected == converter.convertHectareToAcre(hectare);
    }

    @Test
    public void isCorrect_convertAcreToSquareMeter() {
        int acre = 1;
        double squareMeterExpected = 4046.8564224;
        assert squareMeterExpected == converter.convertAcreToSquareMeter(acre);
    }

    @Test
    public void isCorrect_convertAcreToSquareKilometer() {
        int acre = 1;
        double squareKilometerExpected = 0.004046856422399432;
        assert squareKilometerExpected == converter.convertAcreToSquareKilometer(acre);
    }

    @Test
    public void isCorrect_convertAcreToSquareMile() {
        int acre = 1;
        double squareMileExpected = 0.0015625;
        assert squareMileExpected == converter.convertAcreToSquareMile(acre);
    }

    @Test
    public void isCorrect_convertAcreToHectare() {
        int acre = 1;
        double hectareExpected = 0.40468564224;
        assert hectareExpected == converter.convertAcreToHectare(acre);
    }
}
