import java.util.ArrayList;
import java.util.List;

// DO IMPLEMENT A BINARY SEARCH TREE IN THIS CLASS

/**
 * Defines the operations required of student's BST class.
 *
 * NOTE: There are many methods in this interface 
 * that are required solely to support gray-box testing 
 * of the internal tree structure.  They must be implemented
 * as described to pass all grading tests.
 * 
 * @author Deb Deppeler (deppeler@cs.wisc.edu)
 * @param <K> A Comparable type to be used as a key to an associated value.  
 * @param <V> A value associated with the given key.
 */
/**

 *
 * Lecture: 001 Course: CS400 Semester: Spring 2020
 *
 * List collaborators:
 *
 * Other credits:
 * 
 * Known bugs:
 */

/**
 * 
 * 
 * @author iqbal (2020)
 *
 */
public class BST<K extends Comparable<K>, V> implements STADT<K,V> {
  private Node root;
  private int size = 0;
  
  private class Node{
    private Node leftChild;
    private Node rightChild;
    private K key;
    private V value;
    
    
    
    private Node(K k, V value){
      this.key = k;
      this.value = value;
      leftChild = null;
      rightChild = null;
    }  
  }
    
    /**
     * Returns the key that is in the root node of this ST.
     * If root is null, returns null.
     * @return key found at root node, or null
     */
    public K getKeyAtRoot() {
      if (root == null) {
        return null;
      }else {
        return root.key;
      }
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the left child.
     * If the left child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the left child of the found key
     * 
     * @throws IllegalNullKeyException if key argument is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (key == null) {
        throw new IllegalNullKeyException("Null key");
      }
      if (lookUp(root, key) == null) {
        throw new KeyNotFoundException("key is not in tree");
      }
      if (lookUp(root, key).leftChild != null) {
        return lookUp(root, key).leftChild.key;
      }
         return null;
    }
    
    /**
     * Tries to find a node with a key that matches the specified key.
     * If a matching node is found, it returns the returns the key that is in the right child.
     * If the right child of the found node is null, returns null.
     * 
     * @param key A key to search for
     * @return The key that is in the right child of the found key
     * 
     * @throws IllegalNullKeyException if key is null
     * @throws KeyNotFoundException if key is not found in this BST
     */
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (key == null) {
        throw new IllegalNullKeyException("key is null");
      }
      if (lookUp(root, key) == null) {
        throw new KeyNotFoundException("key is not in tree");
      }
      if(lookUp(root, key).rightChild != null) {
        return lookUp(root, key).rightChild.key;
      }
      return null;
    }
    

    /**
     * Returns the height of this BST.
     * H is defined as the number of levels in the tree.
     * 
     * If root is null, return 0
     * If root is a leaf, return 1
     * Else return 1 + max( height(root.left), height(root.right) )
     * 
     * Examples:
     * A BST with no keys, has a height of zero (0).
     * A BST with one key, has a height of one (1).
     * A BST with two keys, has a height of two (2).
     * A BST with three keys, can be balanced with a height of two(2)
     *                        or it may be linear with a height of three (3)
     * ... and so on for tree with other heights
     * 
     * @return the number of levels that contain keys in this BINARY SEARCH TREE
     */
    public int getHeight() {
      if (root == null) {
        return 0;
      }
         return getHeightHelper(root);
    }
    
    /**
     * helper method to calculate height, uses nood as current root to check 
     * children
     * @param node - current root node
     * @return - height
     */
    private int getHeightHelper(Node node) {
      int trackRight = 0;
      int trackLeft = 0;
      if (node.leftChild != null) {
        trackLeft  = getHeightHelper(node.leftChild);
      }
      if (node.rightChild != null) {
        trackRight = getHeightHelper(node.rightChild);
        
      }
      return Math.max(trackRight, trackLeft) + 1;
    }
    
    /**
     * Returns the keys of the data structure in sorted order.
     * In the case of binary search trees, the visit order is: L V R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in-order
     */
    public List<K> getInOrderTraversal() {
      List<K> keysListInOrder = new ArrayList<K>();
      getInOrderTraversalHelper(keysListInOrder, root);
        return keysListInOrder;
    }
    
    /**
     * helper method for getInOrderTraversal, adds keys to list
     * @param keysList - list to add to 
     * @param node - starting node
     */
    private void getInOrderTraversalHelper(List<K> keysList, Node node) {
      if (node != null) {
        getInOrderTraversalHelper(keysList, node.leftChild);
        keysList.add(node.key);
        getInOrderTraversalHelper(keysList, node.rightChild);
      }
    }
    
    /**
     * Returns the keys of the data structure in pre-order traversal order.
     * In the case of binary search trees, the order is: V L R
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in pre-order
     */
    public List<K> getPreOrderTraversal() {
      List<K> keysListPreOrder = new ArrayList<K>();
      getPreOrderTraversalHelper(keysListPreOrder, root);
      return keysListPreOrder;
    }
    
    private void getPreOrderTraversalHelper(List<K> keysList, Node node) {
      if (node != null) {
        keysList.add(node.key);
        getPreOrderTraversalHelper(keysList, node.leftChild);
        getPreOrderTraversalHelper(keysList, node.rightChild);
      }
    }

    /**
     * Returns the keys of the data structure in post-order traversal order.
     * In the case of binary search trees, the order is: L R V 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in post-order
     */
    public List<K> getPostOrderTraversal() {
      List<K> keysListPostOrder = new ArrayList<K>();
      getPostOrderTraversalHelper(keysListPostOrder, root);
      return keysListPostOrder;
    }
    
    private void getPostOrderTraversalHelper(List<K> keysList, Node node) {
      if (node != null || !keysList.isEmpty()) {
        getPostOrderTraversalHelper(keysList, node.leftChild);
        getPostOrderTraversalHelper(keysList, node.rightChild);
        keysList.add(node.key);
      }
    }

    /**
     * Returns the keys of the data structure in level-order traversal order.
     * 
     * The root is first in the list, then the keys found in the next level down,
     * and so on. 
     * 
     * If the SearchTree is empty, an empty list is returned.
     * 
     * @return List of Keys in level-order
     */
    public List<K> getLevelOrderTraversal() {
      int height  = getHeight();
      
      List<K> keysListLevelOrder = new ArrayList<K>();
      for (int i = 1; i <= height; i++) {
        getLevelOrderTraversalHelper(keysListLevelOrder, root, i);
      }
      
        return keysListLevelOrder;
    }
    
    private void getLevelOrderTraversalHelper(List<K> keysList, Node node, int height) {
      if (node == null) {
        return;
      }
      if (height > 1) {
        getLevelOrderTraversalHelper(keysList, node.leftChild, height - 1);
        getLevelOrderTraversalHelper(keysList, node.rightChild, height - 1);
      } else if(height==1) {
        keysList.add(node.key);
      }
    }
    
    private Node lookUp(Node node, K target) {
      if (node == null) {
        return node;
      }
      if (node.key.equals(target)) {
        return node;
      }
      if (target.compareTo(node.key)>0) {
        return lookUp(node.rightChild, target);
      }
      return lookUp(node.leftChild, target);
    }
    
    /** 
     * Add the key,value pair to the data structure and increase the number of keys.
     * If key is null, throw IllegalNullKeyException;
     * If key is already in data structure, throw DuplicateKeyException(); 
     * Do not increase the num of keys in the structure, if key,value pair is not added.
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
      if (key == null) {
        throw new IllegalNullKeyException("null key in insert");
      }
      Node newNode = new Node(key, value);
      if (root == null) {
        root = newNode;
      }
      else {
        Node search = root;
        Node parentNode;
        boolean valid = true;
        
        while(valid) {
          parentNode = search;
          
          if(key.compareTo(parentNode.key) < 0) {//case is key is less
            size++;
            search = search.leftChild;
            if(search == null) {
              parentNode.leftChild = newNode;
              valid = false;
              return;
            }//end if
          }//end if
          else if(key.compareTo(search.key) > 0) {//case if key is greater
            search = search.rightChild;
            size++;
            if(search == null) {
              parentNode.rightChild = newNode;
              valid = false;
              return;
            }//end if 
          }//end else if
          else {//if 
            throw new DuplicateKeyException("key is a duplicate");
          }
          
        }//end while
      }      
    }    
    
    /** 
     * If key is found, remove the key,value pair from the data structure 
     * and decrease num keys, and return true.
     * If key is not found, do not decrease the number of keys in the data structure, return false.
     * If key is null, throw IllegalNullKeyException
     */
    public boolean remove(K key) throws IllegalNullKeyException {
      if (key == null) {
        throw new IllegalNullKeyException("null key in remove");
      }
      if (!contains(key)) {//if the key is not in tree
        return false;
      }
      int sizeBeforeRemove = numKeys();
      
        root = removeHelper(root, key);
      
        return numKeys() < sizeBeforeRemove;
    }
    
    private Node removeHelper(Node node, K key) {
      if (node == null) {
        return node;
      }
      if (key.compareTo(node.key) < 0) {
        node.leftChild = removeHelper(node.leftChild, key);
      }
      else if(key.compareTo(node.key) > 0) {
        node.rightChild = removeHelper(node.rightChild, key);
      }
      else {
        if (node.leftChild == null) {
          return node.rightChild;
        }
        else if (node.rightChild == null) {
          return node.leftChild;
        }
        node.key = successor(node.rightChild).key;
        node.rightChild = removeHelper(node.rightChild, node.key);
        size--;
      }
      return node;
    }
    /**
     * gets to successor/leftmost node
     * @param node to start from
     * @return - successor
     */
    private Node successor(Node node) {
      if (node.leftChild!= null) {
        return successor(node);
      }
      return node;
    }

    /**
     * Returns the value associated with the specified key.
     *
     * Does not remove key or decrease number of keys
     * If key is null, throw IllegalNullKeyException 
     * If key is not found, throw KeyNotFoundException().
     */
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if (key == null) {
        throw new IllegalNullKeyException("Null key in get()");
      }
      return getHelper(root, key);
    }
    
    private V getHelper(Node node, K key) {
      if (node == null) {
        return null;
      }
      int compareVal = key.compareTo(node.key);
      if (compareVal < 0) {
        return getHelper(node.leftChild, key);
      }
      else if (compareVal > 0) {
        return getHelper(node.rightChild, key);
      }
      else {
        return node.value;
      }    
    }

    /** 
     * Returns true if the key is in the data structure
     * If key is null, throw IllegalNullKeyException 
     * Returns false if key is not null and is not present 
     */
    public boolean contains(K key) throws IllegalNullKeyException {
      if (key == null) {
        throw new IllegalNullKeyException("Null key");
      }
      boolean found = lookUp(root, key) != null;
      return found;     
    }

    /**
     *  Returns the number of key,value pairs in the data structure
     */
    public int numKeys() {
        return size;
    }
    
    
    /**
     * Print the tree. 
     *
     * For our testing purposes of your print method: 
     * all keys that we insert in the tree will have 
     * a string length of exactly 2 characters.
     * example: numbers 10-99, or strings aa - zz, or AA to ZZ
     *
     * This makes it easier for you to not worry about spacing issues.
     *
     * You can display a binary search in any of a variety of ways, 
     * but we must see a tree that we can identify left and right children 
     * of each node
     *
     * For example: 
     
           30
           /\
          /  \
         20  40
         /   /\
        /   /  \
       10  35  50 

       Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
       
       Or, you can display a tree of this kind.

       |       |-------50
       |-------40
       |       |-------35
       30
       |-------20
       |       |-------10
       
       Or, you can come up with your own orientation pattern, like this.

       10                 
               20
                       30
       35                
               40
       50                  

       The connecting lines are not required if we can interpret your tree.

     */
    public void print() {
        System.out.println("not yet implemented");
    }
    
} // copyrighted material, students do not have permission to post on public sites


//  deppeler@cs.wisc.edu
