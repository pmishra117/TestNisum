package com.rbc.dif.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RestrictionRange {

	public static void main(String[] args) {
		System.out.println("Hello");
        if(args != null && args.length%2==0) {
	 	int[] lowerBounds = new int[args.length / 2];
		int[] upperBounds = new int[args.length / 2];
		int j = 0, k = 0;
		for (int i = 0; i < args.length; i++) {
			if (i % 2 == 0) {
				lowerBounds[j++] = Integer.parseInt(args[i]);
			} else {
				upperBounds[k++] = Integer.parseInt(args[i]);
			}

		}
		System.out.println(" lowerBounds size " + lowerBounds.length);
		System.out.println(" upperBounds size " + upperBounds.length);
		ConcurrentHashMap<Integer, Integer> optimizeRange = optimiseRange(lowerBounds, upperBounds);
		printResult(optimizeRange);
        }
        else System.out.printf("Error with input ........, "+args);
	}

	public static ConcurrentHashMap<Integer, Integer> optimiseRange(int[] lowerBound, int[] upperBound) {
		int i = lowerBound.length - 1;
		int j = upperBound.length - 1;
		ConcurrentHashMap<Integer, Integer> intervalsModified = new ConcurrentHashMap<Integer, Integer>();
		while (i >= 0 && j >= 0) {
			if (lowerBound[i] <= upperBound[j]) {
				if (intervalsModified.size() != 0) {
					Set<Integer> keySet = intervalsModified.keySet();
					Iterator<Integer> itr = keySet.iterator();
					// [94133,94133] [94200,94299] [94226,94399]
					while (itr.hasNext()) {
						int lowerValue = itr.next();
						int upperValue = intervalsModified.get(lowerValue);
						if ((lowerBound[i] <= lowerValue) && (upperBound[j] <= upperValue)
								&& upperBound[j] >= lowerValue) {
							intervalsModified.remove(lowerValue);
							intervalsModified.put(lowerBound[i], upperValue);
						} else
							intervalsModified.put(lowerBound[i], upperBound[j]);
					}
				} else
					intervalsModified.put(lowerBound[i], upperBound[j]);
			}
			i--;
			j--;
		}

		return intervalsModified;

	}

	public static void printResult(ConcurrentHashMap<Integer, Integer> data) {
		Set<Integer> set = data.keySet();
		Iterator<Integer> itr = set.iterator();
		while (itr.hasNext()) {
			int lowerBound = itr.next();
			System.out.println("[" + lowerBound + "," + data.get(lowerBound) + "]");
		}
	}
}
