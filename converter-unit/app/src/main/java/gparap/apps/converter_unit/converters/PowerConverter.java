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

public class PowerConverter {
    public double convertWattToKilowatt(double watts) { return 1000 * watts; }
    public double convertWattToMegawatt(double watts) { return 1000000 * watts; }
    public double convertWattToMetricHorsepower(double watts) { return 735.49875 * watts; }
    public double convertWattToImperialHorsepower(double watts) { return 745.69987 * watts; }
    public double convertWattToVoltAmpere(double watts) { return 1 * watts; }
    public double convertWattToKilovoltAmpere(double watts) { return 1000 * watts; }

    public double convertKilowattToWatt(double kilowatts) { return 0.001 * kilowatts; }
    public double convertKilowattToMegawatt(double kilowatts) { return 1000 * kilowatts; }
    public double convertKilowattToMetricHorsepower(double kilowatts) { return 0.73549875 * kilowatts; }
    public double convertKilowattToImperialHorsepower(double kilowatts) { return 0.74569987 * kilowatts; }
    public double convertKilowattToVoltAmpere(double kilowatts) { return 0.001 * kilowatts; }
    public double convertKilowattToKilovoltAmpere(double kilowatts) { return 1 * kilowatts; }

    public double convertMegawattToWatt(double megawatts) { return 0.000001 * megawatts; }
    public double convertMegawattToKilowatt(double megawatts) { return 0.001 * megawatts; }
    public double convertMegawattToMetricHorsepower(double megawatts) { return 0.00073549875 * megawatts; }
    public double convertMegawattToImperialHorsepower(double megawatts) { return 0.00074569987 * megawatts; }
    public double convertMegawattToVoltAmpere(double megawatts) { return 0.000001 * megawatts; }
    public double convertMegawattToKilovoltAmpere(double megawatts) { return 0.001 * megawatts; }

    public double convertMetricHorsepowerToWatt(double metricHorsepower) { return 0.0013596216 * metricHorsepower; }
    public double convertMetricHorsepowerToKilowatt(double metricHorsepower) { return 1.3596216 * metricHorsepower; }
    public double convertMetricHorsepowerToMegawatt(double metricHorsepower) { return 1359.6216 * metricHorsepower; }
    public double convertMetricHorsepowerToImperialHorsepower(double metricHorsepower) { return 1.0138697 * metricHorsepower; }
    public double convertMetricHorsepowerToVoltAmpere(double metricHorsepower) { return 0.0013596216 * metricHorsepower; }
    public double convertMetricHorsepowerToKilovoltAmpere(double metricHorsepower) { return 1.3596216 * metricHorsepower; }

    public double convertImperialHorsepowerToWatt(double imperialHorsepower) { return 0.0013410221 * imperialHorsepower; }
    public double convertImperialHorsepowerToKilowatt(double imperialHorsepower) { return 1.3410221 * imperialHorsepower; }
    public double convertImperialHorsepowerToMegawatt(double imperialHorsepower) { return 1341.0221 * imperialHorsepower; }
    public double convertImperialHorsepowerToMetricHorsepower(double imperialHorsepower) { return 0.9863201 * imperialHorsepower; }
    public double convertImperialHorsepowerToVoltAmpere(double imperialHorsepower) { return 0.0013410221 * imperialHorsepower; }
    public double convertImperialHorsepowerToKilovoltAmpere(double imperialHorsepower) { return 1.3410221 * imperialHorsepower; }

    public double convertVoltAmpereToWatt(double voltAmperes) { return 1 * voltAmperes; }
    public double convertVoltAmpereToKilowatt(double voltAmperes) { return 1000 * voltAmperes; }
    public double convertVoltAmpereToMegawatt(double voltAmperes) { return 1000000 * voltAmperes; }
    public double convertVoltAmpereToMetricHorsepower(double voltAmperes) { return 735.49875 * voltAmperes; }
    public double convertVoltAmpereToImperialHorsepower(double voltAmperes) { return 745.69987 * voltAmperes; }
    public double convertVoltAmpereToKilovoltAmpere(double voltAmperes) { return 745.69987 * voltAmperes; }

    public double convertKilovoltAmpereToWatt(double kilovoltAmperes) { return 0.001 * kilovoltAmperes; }
    public double convertKilovoltAmpereToKilowatt(double kilovoltAmperes) { return 1 * kilovoltAmperes; }
    public double convertKilovoltAmpereToMegawatt(double kilovoltAmperes) { return 1000 * kilovoltAmperes; }
    public double convertKilovoltAmpereToMetricHorsepower(double kilovoltAmperes) { return 0.73549875 * kilovoltAmperes; }
    public double convertKilovoltAmpereToImperialHorsepower(double kilovoltAmperes) { return 0.74569987 * kilovoltAmperes; }
    public double convertKilovoltAmpereToVoltAmpere(double kilovoltAmperes) { return 0.001 * kilovoltAmperes; }
}
