package com.ggar.framework.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ggar.framework.algorithm.sorting.BubbleSort;

public class BubbleSortTest {

	public static void main(String[] args) {
		Algorithm<Comparator<Integer>, List<Integer>> alg = new BubbleSort<Integer>(new ArrayList<Integer>() {
			{
				int min = 0;
				int max = 100;
				
				for (int i = 0; i < 20; i++) {
					int n = (int)(Math.random() * max - min + 1) + min;
					add(n);
				}
			}
		});
		
		List<Integer> result = alg.execute(Comparator.naturalOrder());
		
		for (int i : result) {
			System.out.println(i);
		}
	}
	
}
