package com.data.structures.linked;

/**
 * @author [wangjiahui]
 * @ClassName: DoubleLinkedList
 * @CreateDate: [2019-06-03 16:07]
 * @Description: [双向链表]
 * @version: [V1.0]
 */
public class DoubleLinkedList<E> {

    transient int size = 0;

    transient int modCount = 0;

    transient Node<E> first;

    transient Node<E> last;

    public DoubleLinkedList(){}

    private class Node<E>{
        E item;
        Node<E> next;
        Node<E> pre;
        Node(Node<E> pre, E e, Node<E> next){
            this.item = e;
            this.next = next;
            this.pre = pre;
        }
    }

    public boolean add(E e){
        linkedLast(e);
        return true;
    }

    public boolean add(int index, E e){
        checkPositionIndex(index);
        if (index==size) {
            linkedLast(e);
        }else {
            linkedBefore(e,node(index));
        }
        return true;
    }

    void linkedBefore(E e, Node<E> succ) {
        final Node<E> pre = succ.pre;
        final Node<E> newNode = new Node<E>(pre,e,succ);
        succ.pre = newNode;
        if (pre == null) {
            first = newNode;
        }else {
            pre.next = newNode;
        }
        size++;
        modCount++;
    }

    Node node(int index) {
        if (index<(size>>1)) {
            Node<E> x = first;
            for (int i=0;i<index;i++){
                x = x.next;
            }
            return x;
        }else {
            Node<E> x = last;
            for (int i=size-1;i>index;i--){
                x = x.pre;
            }
            return x;
        }
    }

    private void checkPositionIndex(int index) {
        if(!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+ index +", Size: "+size;
    }

    private boolean isPositionIndex(int index) {
        return index>=0 && index<=size;
    }

    private void linkedLast(E e) {
        final Node<E> l =last;
        final Node<E> newNode = new Node<E>(l,e,null);
        if (l==null) {
            first = newNode;
        }else {
            l.next = newNode;
        }
        last = newNode;
        modCount++;
        size++;
    }



}
