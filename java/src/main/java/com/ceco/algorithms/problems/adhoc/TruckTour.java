package com.ceco.algorithms.problems.adhoc;

import java.util.Scanner;

/**
 * @see <a href="https://www.hackerrank.com/challenges/truck-tour">
 *          Truck Tour
 *     </a>
 *
 * Example input:
 * 3
 * 1 5
 * 10 3
 * 3 4
 *
 * Output:
 * 1
 *
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * @since 23-Sep-2016
 */
public class TruckTour {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int pumpsCount = scanner.nextInt();

        int[] petrolAmounts = new int[pumpsCount];
        int[] distancesToNextPump = new int[pumpsCount];

        for (int i = 0; i < pumpsCount; i++) {
            petrolAmounts[i] = scanner.nextInt();
            distancesToNextPump[i] = scanner.nextInt();
        }

        int petrolPumpIdx = 0;
        int truckTankAmount = 0;

        // subtract the distance to next pump from the petrol amount
        // if the distance is more than the petrol amount this is not
        // the right pump from where the truck should start because it
        // will run out of gas before completing the round trip
        // if the petrol is more than the distance choose for now that pump
        // and continue to the next which will either add more petrol to the tank
        // and at the same time subtract when the truck reaches the next pump
        // due to consuming it when travelling
        // if at the end the truck's tank amount is non negative, you have completed
        // the round trip and the chosen pump is suitable as a starting point for the
        // journey ;)
        for (int i = 0; i < pumpsCount; i++) {
            truckTankAmount += petrolAmounts[i] - distancesToNextPump[i];
            if (truckTankAmount < 0) {
                truckTankAmount = 0;
                petrolPumpIdx = i + 1;
            }
        }

        System.out.println(petrolPumpIdx);
    }
}
