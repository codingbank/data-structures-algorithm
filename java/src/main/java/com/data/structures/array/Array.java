package com.data.structures.array;


import java.util.Arrays;

/**
 * @author [wangjiahui]
 * @ClassName: Array
 * @CreateDate: [2019-04-19 17:04]
 * @Description: [TODO]
 * @version: [V1.0]
 */
public class Array<E> {
    /**
     * @Title: [DEFAULT_CAPACITY]
     * @author: [wangjiahui]
     * @CreateDate: [2019-04-19 17:14]
     * @Description: [default size of Array]
     * @version: [V1.0]
     * @param
     * @return
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * @Title: [DEFAULT_EMPTY_ELEMENT]
     * @author: [wangjiahui]
     * @CreateDate: [2019-04-19 17:18]
     * @Description: [default element]
     * @version: [V1.0]
     * @param
     * @return
     */
    private static final Object[] DEFAULT_EMPTY_ELEMENT = {};
    /**
     * @Title: [size]
     * @author: [wangjiahui]
     * @CreateDate: [2019-04-19 17:10]
     * @Description: [the size of Array]
     * @version: [V1.0]
     * @param
     * @return
     */
    private int size;

    /**
     * @Title: [elementData]
     * @author: [wangjiahui]
     * @CreateDate: [2019-04-22 11:10]
     * @Description: [数据元素]
     * @version: [V1.0]
     * @param
     * @return
     */
    transient Object[] elementData;

    /**
     * @Title: [EMPTY_ELEMENTDATA]
     * @author: [wangjiahui]
     * @CreateDate: [2019-04-22 11:10]
     * @Description: [区分默认为空还是操作置为空]
     * @version: [V1.0]
     * @param
     * @return
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    protected transient int modCount = 0;

    public Array(){
        this.elementData = DEFAULT_EMPTY_ELEMENT;
    }

    public Array(int intialCapacity){
        if (intialCapacity==0) {
            this.elementData = DEFAULT_EMPTY_ELEMENT;
        }else if (intialCapacity>0) {
            this.elementData = new Object[intialCapacity];
        }
    }

    public boolean add(E element){
        ensureCapacityInternal(size+1);
        this.elementData[size++] = element;
        return true;
    }
    public boolean add(int index, E element){
        checkIndex(index);
        ensureCapacityInternal(size+1);
        System.arraycopy(elementData,index,elementData,index+1,size - index);
        elementData[index] = element;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData,minCapacity));
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity>>1);
        if (newCapacity - minCapacity <0) {
            newCapacity = minCapacity;
        }
        elementData = Arrays.copyOf(elementData,newCapacity);
    }

    private int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULT_EMPTY_ELEMENT) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    public int size(){
        return size;
    }

    public E get(int index) {
        checkIndex(index);
        return elementData(index);
    }

    public E set(int index, E element) {
        checkIndex(index);
        modCount ++;
        E oldElement = elementData(index);
        elementData[index] = element;
        return oldElement;
    }

    public boolean remove(Object o){
        if (o == null) {
            for (int index=0; index<size; index++) {
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
            }
        }else {
            for (int index=0; index<size; index++) {
                if (elementData[index] == o) {
                    fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    private void fastRemove(int index) {
        modCount++;
        int numMv = size - index - 1;
        if(numMv > 0) {
            System.arraycopy(elementData,index+1, elementData, index, numMv);
        }
        elementData[size--] = null;
    }

    public E remove(int index){
        E oldElement = elementData(index);
        if (index<0 || index>=size) {
            throw new IndexOutOfBoundsException("index ["+index+"] out of band");
        }
        fastRemove(index);
        return oldElement;
    }
    E elementData(int index) {
        return (E)elementData[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static void main(String[] args){
        Array<Integer> a = new Array<Integer>(1);
        a.add(1);
        System.out.println(a.get(-1));
    }
}
