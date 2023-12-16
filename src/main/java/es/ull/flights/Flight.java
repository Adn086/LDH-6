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
package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Representa un vuelo con un número específico y una cantidad limitada de asientos.
 *
 * Gestiona los pasajeros y sus asignaciones a un vuelo específico.
 * Valida el número del vuelo y administra la adición y eliminación de pasajeros.
 */
public class Flight {

    private String flightNumber;
    private int seats;
    private Set<Passenger> passengers = new HashSet<>();

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
    private static Pattern pattern = Pattern.compile(flightNumberRegex);

    /**
     * @brief Constructor de Flight que valida y asigna el número y asientos del vuelo.
     * @param flightNumber Número del vuelo.
     * @param seats Número de asientos disponibles en el vuelo.
     * @throws RuntimeException Si el número del vuelo no es válido.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Obtiene el número del vuelo.
     * @return Número del vuelo.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Obtiene el número de pasajeros en el vuelo.
     * @return Cantidad de pasajeros.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Añade un pasajero al vuelo.
     * @param passenger El pasajero a añadir.
     * @return Verdadero si el pasajero fue añadido, falso de lo contrario.
     * @throws RuntimeException Si no hay suficientes asientos.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Elimina un pasajero del vuelo.
     * @param passenger El pasajero a eliminar.
     * @return Verdadero si el pasajero fue eliminado, falso de lo contrario.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}

