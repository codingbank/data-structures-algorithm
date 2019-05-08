package com.data.structures.linked;

import java.util.LinkedList;

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

    public E get(){
        return last.item;
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


}
