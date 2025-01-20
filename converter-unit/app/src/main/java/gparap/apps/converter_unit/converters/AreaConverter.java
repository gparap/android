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

    public double convertSquareMeterTo_SquareKilometer(double squareMeter) { return squareMeter * ((double) 1 / 1000000); }
    public double convertSquareMeterTo_SquareMile(double squareMeter) { return squareMeter * (1 / 2589988.11); }
    public double convertSquareMeterTo_Hectare(double squareMeter) { return squareMeter * ((double) 1 / 10000); }
    public double convertSquareMeterTo_Acre(double squareMeter) { return squareMeter * (1 / 4046.8564224); }

    public double convertSquareKilometerTo_SquareMeter(double squareKilometer) { return squareKilometer * 1000000 ;}
    public double convertSquareKilometerTo_SquareMile(double squareKilometer) { return squareKilometer * (1 / 2.58998811); }
    public double convertSquareKilometerTo_Hectare(double squareKilometer) { return squareKilometer * 100; }
    public double convertSquareKilometerTo_Acre(double squareKilometer) { return squareKilometer * 247.1053814672; }

    public double convertSquareMileTo_SquareMeter(double squareMile) { return squareMile * 2589988.11; }
    public double convertSquareMileTo_SquareKilometer(double squareMile) { return squareMile * 2.58998811; }
    public double convertSquareMileTo_Hectare(double squareMile) { return squareMile * 258.998811; }
    public double convertSquareMileTo_Acre(double squareMile) { return squareMile * 640; }

    public double convertHectareTo_SquareMeter(double hectare) { return hectare * 10000; }
    public double convertHectareTo_SquareKilometer(double hectare) { return hectare * 0.01; }
    public double convertHectareTo_SquareMile(double hectare) { return hectare * (1 / 258.998811); }
    public double convertHectareTo_Acre(double hectare) { return hectare * 2.471053814672; }

    public double convertAcreTo_SquareMeter(double acre) { return acre * 4046.8564224; }
    public double convertAcreTo_SquareKilometer(double acre) { return acre * (1 / 247.1053814672); }
    public double convertAcreTo_SquareMile(double acre) { return acre * ((double) 1 / 640); }
    public double convertAcreTo_Hectare(double acre) { return acre * 0.40468564224; }
}
