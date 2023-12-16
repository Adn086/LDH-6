/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.flights.Flight;

/**
 * @class Passenger
 * @brief Representa un pasajero con identificación, nombre y código de país.
 *
 * Gestiona la información de un pasajero, incluyendo su asignación a vuelos.
 * Valida el código del país y administra el vuelo asociado al pasajero.
 */
public class Passenger {

    private String identifier;
    private String name;
    private String countryCode;
    private Flight flight;

    /**
     * @brief Constructor de Passenger que asigna identificador, nombre y código de país.
     * @param identifier Identificador del pasajero.
     * @param name Nombre del pasajero.
     * @param countryCode Código del país del pasajero.
     * @throws RuntimeException Si el código del país no es válido.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Obtiene el identificador del pasajero.
     * @return Identificador del pasajero.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Obtiene el nombre del pasajero.
     * @return Nombre del pasajero.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Obtiene el código de país del pasajero.
     * @return Código de país.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Obtiene el vuelo asignado al pasajero.
     * @return Vuelo asignado.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Asigna al pasajero a un vuelo.
     * @param flight El vuelo a asignar.
     * @throws RuntimeException Si no se puede agregar o eliminar al pasajero del vuelo.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Establece el vuelo del pasajero.
     * @param flight El vuelo a establecer.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Devuelve una representación en cadena del pasajero.
     * @return Una cadena que representa al pasajero.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}

