/**

Long Project 3 LP Group 19: Folder: VXB220005

Below are the team member names and NET ID:

Amulya Atluri / AXA210091
Venkat Biyyapu / VXB220005
Poojan Patel / PRP200000
Batul Petiwala / BJP210000

**/

package VXB220005;

import java.util.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> {
    static final int maxLevel = 32;
    int size;
    Entry<T> head;
    Entry<T> cur;
    Entry[] prev;

    int[] steps;

    Random r ;

    static class Entry<E> {
        E element;
        Entry[] next;
        int[] span;

        public Entry(E x, int lev) {
            element = x;
            next = new Entry[lev + 1];
            span = new int[lev + 1];
            // add more code if needed
        }

        public E getElement() {
            return element;
        }
    }

    // Constructor
    public SkipList() {
        size = 0;
        head = new Entry<T>(null, maxLevel);
        r=new Random();
        for(int i=0;i<maxLevel;i++){
            head.next[i]=null;
            head.span[i]=1;
        }
    }

    // Add x to list. If x already exists, reject it. Returns true if new node is added to list else returns false
    public boolean add(T x) {
        if(contains(x)){
            return false;
        }
        int lvl = chooseLevel();// Choose a random level for the new entry
        Entry<T> entry = new Entry<>(x, lvl);
        int s = 0;
        Entry<T> prev_;
        for (int i = 0; i < lvl + 1; i++) {
            prev_ = prev[i];//Get the previous entry at level i
            entry.next[i] = prev_.next[i];// Update the next pointer of entry to the next pointer of prev_
            prev_.next[i] = entry;
            entry.span[i] = prev_.span[i] - s;
            prev_.span[i] = s + 1;
            s += steps[i];
        }
        // Update the span of previous entries beyond the new level
        for (int i = lvl + 1; i < maxLevel; i++) {
            prev[i].span[i] += 1;
        }
        size ++;
        return true;
    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) {
        if (!contains(x)) {
            return null;
        }
        Entry<T> prev_;
        for (int i = 0; i < cur.next.length; i++) {
                prev_ = prev[i];//Get the previous entry at level i
                prev_.span[i] += prev_.next[i].span[i] - 1;
                prev_.next[i] = prev_.next[i].next[i]; // removing element at each level but updating the pointer to next field
        }
        // Update the span of previous entries beyond the current level
        for (int i = cur.next.length; i < maxLevel; i++)
            prev[i].span[i] -= 1;

        size--;
        return x;
    }
    //Returns the random level of next field.
    public int chooseLevel() {
        int lvl;
        lvl = 1 + Integer.numberOfTrailingZeros(r.nextInt());
        return Math.min(lvl, maxLevel - 1);
    }

    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
        if(isEmpty())
            return null;
        find(x);
        return (T) prev[0].next[0].element;
    }

    // Does list contain x?
    // Returns true if the list contains x, else false.
    public boolean contains(T x) {
        prev = (Entry[]) find(x).get(0);
        cur = (Entry<T>) find(x).get(1);
        cur = cur.next[0];
        return (cur != null && ((T) cur.element).compareTo((T) x) == 0);
    }

    // Return first element of list.
    public T first() {
        if(isEmpty())
            return null;
        return (T) head.next[0].element;
    }

    // Find largest element that is less than or equal to x and the value is returned,
    public T floor(T x) {
        if(isEmpty())
            return null;
        find(x);
        if(prev[0].next[0].element.equals(x))
            return (T) prev[0].next[0].element;
        return (T) prev[0].element;
    }

    // Return element at index n of list.  First element is at index 0.
    public T get(int n) {
        return getLog(n);
    }

    // O(n) algorithm for get(n)
    // Return the nth index of the list in O(n)
    public T getLinear(int n) {
        if (size==0)
            return null;
        if (n > size-1)
            throw new IndexOutOfBoundsException("Index"+ n + "not in the list");
        Entry temp=head;
        for(int i=0;i<=n;i++)
            temp=temp.next[0];
        return (T) temp.element;
    }

    // Optional operation: Eligible for EC.
    // O(log n) expected time for get(n).
    // Return the nth index of the list in Expected O(log(n))
    public T getLog(int n) {
        if (size()==0)
            return null;
        if(n > size-1)
            throw new IndexOutOfBoundsException("Index"+ n +"not in the list");
        Entry<T> p = head;
        if (n < size) {
            n += 1;
            // Loop through the list from the highest level to the lowest level
            for (int i = maxLevel; i >= 0; i--) {
                while (p.next[i] != null && p.span[i] <= n) { // Move 'p' to the next node while decrementing 'n' by the span of the node at the current level
                    n -= p.span[i];
                    p = p.next[i];
                }
            }
        }
        return p.element;
    }
    // Check whether the list is empty or not .

    public boolean isEmpty() {
        return size==0;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
        return null;
    }

    // Return last element of list
    public T last() {
        return get(size()-1);
    }


    // Not a standard operation in skip lists.
    public void rebuild() {

    }

    // Return the number of elements in the list
    public int size() {
        return size;
    }

    //Returns the cursor and the prev entry for the given element.
    public List<Object> find(T x){
        prev = new Entry[maxLevel + 1];
        steps = new int[maxLevel];
        cur = head;
        for (int i = maxLevel; i >= 0; i -= 1) {
            while (cur.next[i] != null && ((T) cur.next[i].getElement()).compareTo((T) x) < 0) {
                steps[i] += cur.span[i];
                cur = cur.next[i];
            }
            prev[i] = cur;
        }
        return Arrays.asList(prev,cur);
    }
    public int getLevel(Entry x){
        return x.next.length;
    }
    public static int getMaxLevel() {
        return maxLevel;
    }

}
