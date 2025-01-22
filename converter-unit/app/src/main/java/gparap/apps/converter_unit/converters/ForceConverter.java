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

public class ForceConverter {
    public double convertNewtonToPoundForce(double newtons) { return newtons / 4.44822; }
    public double convertNewtonToKilogramForce(double newtons) { return newtons / 9.80665; }
    public double convertNewtonToDyne(double newtons) { return newtons * 1e5; }
    public double convertNewtonToMetricTonForce(double newtons) { return newtons / 9806.65 ; }
    public double convertNewtonToUSTonForce(double newtons) { return newtons / 8896.44; }

    public double convertPoundForceToNewton(double poundsForce) { return poundsForce * 4.44822; }
    public double convertPoundForceToKilogramForce(double poundsForce) { return poundsForce * 0.453592; }
    public double convertPoundForceToDyne(double poundsForce) { return poundsForce * 4.44822 * 1e5; }
    public double convertPoundForceToMetricTonForce(double poundsForce) { return poundsForce * 0.000453592; }
    public double convertPoundForceToUSTonForce(double poundsForce) { return poundsForce / (double)2000; }

    public double convertKilogramForceToNewton(double kilogramsForce) { return kilogramsForce * 9.80665; }
    public double convertKilogramForceToPoundForce(double kilogramsForce) { return kilogramsForce / 0.453592; }
    public double convertKilogramForceToDyne(double kilogramsForce) { return kilogramsForce * 9.80665 * 1e5; }
    public double convertKilogramForceToMetricTonForce(double kilogramsForce) { return kilogramsForce / (double)1000; }
    public double convertKilogramForceToUSTonForce(double kilogramsForce) { return kilogramsForce / 907.1847; }

    public double convertDyneToNewton(double dynes) { return dynes / 1e5; }
    public double convertDyneToPoundForce(double dynes) { return dynes / (4.44822 * 1e5); }
    public double convertDyneToKilogramForce(double dynes) { return dynes / (9.80665 * 1e5); }
    public double convertDyneToMetricTonForce(double dynes) { return dynes / (9.80665 * 1e8); }
    public double convertDyneToUSTonForce(double dynes) { return dynes / (8.89644 * 1e8); }

    public double convertMetricTonForceToNewton(double metricTonsForce) { return metricTonsForce * 9806.65; }
    public double convertMetricTonForceToPoundForce(double metricTonsForce) { return metricTonsForce / 0.000453592; }
    public double convertMetricTonForceToKilogramForce(double metricTonsForce) { return metricTonsForce * 1000; }
    public double convertMetricTonForceToDyne(double metricTonsForce) { return metricTonsForce * 9.80665 * 1e8; }
    public double convertMetricTonForceToUSTonForce(double metricTonsForce) { return metricTonsForce * 1.10231; }

    public double convertUSTonForceToNewton(double usTonsForce) { return usTonsForce * 8896.44; }
    public double convertUSTonForceToPoundForce(double usTonsForce) { return usTonsForce * 2000; }
    public double convertUSTonForceToKilogramForce(double usTonsForce) { return usTonsForce * 907.1847; }
    public double convertUSTonForceToDyne(double usTonsForce) { return usTonsForce * 8.89644 * 1e8; }
    public double convertUSTonForceToMetricTonForce(double usTonsForce) { return usTonsForce / 1.10231; }
}
