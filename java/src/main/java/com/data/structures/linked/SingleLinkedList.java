package com.data.structures.linked;

import com.data.exception.IllegalDataSizeException;

/**
 * @author [wangjiahui]
 * @ClassName: SingleLinkedList
 * @CreateDate: [2019-05-06 15:19]
 * @Description: [TODO]
 * @version: [V1.0]
 */
public class SingleLinkedList<E> {
    /**
     * @Title: [size]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-06 15:46]
     * @Description: [链表大小]
     * @version: [V1.0]
     */
    transient int size = 0;
    /**
     * @Title: [modCount]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-06 15:48]
     * @Description: [链表改变次数]
     * @version: [V1.0]
     */
    protected transient int modCount = 0;

    /**
     * @Title: [first]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-06 16:18]
     * @Description: [首节点]
     * @version: [V1.0]
     */
    transient Node<E> first;
    /**
     * @Title: [last]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-06 16:18]
     * @Description: [尾节点]
     * @version: [V1.0]
     */
    transient Node<E> last;

    /**
     * @Title: [SingleLinkedList]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-06 16:13]
     * @Description: [无参构造方法]
     * @version: [V1.0]
     */
    public SingleLinkedList(){
    }

    public boolean add(E element){
        linkLast(element);
        return true;
    }

    public boolean add(int index, E element){
        checkElementIndex(index);
        Node<E> next = this.first;
        Node<E> pre = null;
        if (index == 0){
            this.first = new Node<E>(element,next);
        }else {
            for(int i=0;i<index;i++) {
                pre = next;
                next = pre.next;
            }
            pre.next = new Node<E>(element,next);
        }
        size++;
        modCount++;
        return true;
    }

    /**
     * @Title: [getLast]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-13 14:28]
     * @Description: [获取最后一个元素]
     * @version: [V1.0]
     * @return E
     */
    public E getLast(){
        return last.item;
    }

    public E getFirst(){
        return first.item;
    }

    /**
     * @Title: [get]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-13 14:28]
     * @Description: [获取第index元素]
     * @version: [V1.0]
     * @param [index]
     * @return E
     * @throws SingleLinkedList
     */
    public E get(int index){
        checkElementIndex(index);
        return node(index).item;
    }

    private Node<E> node(int index) {
        Node<E> x = first;
        for (int i=0;i<index;i++) {
            x = x.next;
        }
        return x;
    }

    /**
     * @Title: [checkElementIndex]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-13 14:29]
     * @Description: [检验index]
     * @version: [V1.0]
     * @param [index]
     * @return void
     * @throws SingleLinkedList
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException("index = "+index+", size = "+size);
        }
    }

    private boolean isElementIndex(int index) {
        return index>=0 && index<size;
    }

    /**
     * @Title: [linkLast]
     * @author: [wangjiahui]
     * @CreateDate: [2019-05-06 16:17]
     * @Description: [追加元素]
     * @version: [V1.0]
     * @param [element]
     * @return void
     * @throws SingleLinkedList
     */
    private void linkLast(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(element,null);
        last = newNode;
        if (l == null) {
            first = newNode;
        }else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    private class Node<E> {
        E item;
        private Node<E> next;
        Node(E item, Node<E> next){
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        Node<E> node = this.first;
        StringBuffer nodeStr = new StringBuffer();
        for(int i=0; i<size; i++) {
            if (nodeStr==null || nodeStr.length()==0){
                nodeStr.append(node.item);
            }else {
                nodeStr.append("->").append(node.item);
            }
            node = node.next;
        }
        if (node==null) {
            nodeStr.append("->").append("null");
        }else {
            nodeStr.append("=>").append(node.item);
        }
        return nodeStr.toString();
    }

    private boolean remove(int index) {
        checkElementIndex(index);
        Node<E> next = this.first;
        if (index==0){
            this.first = next.next;
            return true;
        }
        Node<E> pre = next;
        for (int i=0;i<index;i++){
            pre = next;
            next = next.next;
        }
        pre.next = next.next;
        if (index+1==size){
            this.last = pre;
        }
        size--;
        modCount++;
        return true;
    }

    public boolean isCycle() {
        if (size>1){
            this.last.next=this.first;
        }else {
            throw new IllegalDataSizeException("size <= 1, can't become cycleLinked");
        }
        modCount++;
        return true;
    }

    public static void main(String[] args){
        SingleLinkedList<Integer> single = new SingleLinkedList<Integer>();
        single.add(1);
        single.add(2);
        single.add(3);
        System.out.println(single);
        single.isCycle();
        System.out.println(single);
    }

}
