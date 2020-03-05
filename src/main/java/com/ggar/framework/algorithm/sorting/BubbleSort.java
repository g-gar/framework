package com.ggar.framework.algorithm.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ggar.framework.algorithm.Algorithm;

public class BubbleSort<T> implements Algorithm<Comparator<T>, List<T>>{

	private final List<T> items;
	
	public BubbleSort(List<T> items) {
		super();
		this.items = items;
	}
	
	@Override public List<T> execute(Comparator<T> param) {
		
		for (int i = 0; i < items.size() - 1; i++) {
			for (int j = 0; j < items.size() - i - 1; j++) {
				if (param.compare(items.get(j), items.get(j+1)) == 1) {
					swap(j, j+1);
				}
			}
		}
		
		return items;
	}
	
	private void swap(int a, int b) {
		T temp = this.items.get(b);
		this.items.set(b, this.items.get(a));
		this.items.set(a, temp);
	}

}
