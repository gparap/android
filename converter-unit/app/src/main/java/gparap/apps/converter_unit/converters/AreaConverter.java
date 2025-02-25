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

public class AreaConverter {

    public double convertSquareMeterToSquareKilometer(double squareMeter) { return squareMeter * ((double) 1 / 1000000); }
    public double convertSquareMeterToSquareMile(double squareMeter) { return squareMeter * (1 / 2589988.11); }
    public double convertSquareMeterToHectare(double squareMeter) { return squareMeter * ((double) 1 / 10000); }
    public double convertSquareMeterToAcre(double squareMeter) { return squareMeter * (1 / 4046.8564224); }

    public double convertSquareKilometerToSquareMeter(double squareKilometer) { return squareKilometer * 1000000 ;}
    public double convertSquareKilometerToSquareMile(double squareKilometer) { return squareKilometer * (1 / 2.58998811); }
    public double convertSquareKilometerToHectare(double squareKilometer) { return squareKilometer * 100; }
    public double convertSquareKilometerToAcre(double squareKilometer) { return squareKilometer * 247.1053814672; }

    public double convertSquareMileToSquareMeter(double squareMile) { return squareMile * 2589988.11; }
    public double convertSquareMileToSquareKilometer(double squareMile) { return squareMile * 2.58998811; }
    public double convertSquareMileToHectare(double squareMile) { return squareMile * 258.998811; }
    public double convertSquareMileToAcre(double squareMile) { return squareMile * 640; }

    public double convertHectareToSquareMeter(double hectare) { return hectare * 10000; }
    public double convertHectareToSquareKilometer(double hectare) { return hectare * 0.01; }
    public double convertHectareToSquareMile(double hectare) { return hectare * (1 / 258.998811); }
    public double convertHectareToAcre(double hectare) { return hectare * 2.471053814672; }

    public double convertAcreToSquareMeter(double acre) { return acre * 4046.8564224; }
    public double convertAcreToSquareKilometer(double acre) { return acre * (1 / 247.1053814672); }
    public double convertAcreToSquareMile(double acre) { return acre * ((double) 1 / 640); }
    public double convertAcreToHectare(double acre) { return acre * 0.40468564224; }
}
