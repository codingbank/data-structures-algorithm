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
        linkedLast(e);
        return true;
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
