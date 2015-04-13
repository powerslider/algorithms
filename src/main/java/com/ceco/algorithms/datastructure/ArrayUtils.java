package com.ceco.algorithms.datastructure;

import java.lang.reflect.Array;

/**
 * @author Tsvetan Dimitrov <tsvetan.dimitrov23@gmail.com>
 * <p/>
 * Date added: 2015-02-10
 */
public class ArrayUtils {

    public static Object newArray(int size, Object[] arr) {
        //Stack
	    return Array.newInstance(arr.getClass().getComponentType(), size);
	}
}
