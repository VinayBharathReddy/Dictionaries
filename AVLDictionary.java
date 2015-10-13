package searchengine.dictionary;

class AVLTree<K extends Comparable<K>, V>
{
	K key;
	V value;
	public int height;
	public AVLTree<K,V> left;
	public AVLTree<K,V> right;
	
	public void setheight(int height)
	{
		this.height=height;
	}
	
	public AVLTree(K key,V value) //node constructor
	{
    	this.key=key;
    	this.value=value;
    	this.left=null;
    	this.right=null;
	}

	public AVLTree(K key,V value,AVLTree<K,V> left, AVLTree<K,V> right) //node constructor
	{
		this.key=key;
		this.value = value;
		this.left = left;
		this.right = right;
	}	
}
public class AVLDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>{
	AVLTree root;
	AVLTree parent;
	int i;
	int size=0;
	public K[] keys;

	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		i=0;
		K[] s = (K[]) new String[size];
	}

	private K[] preOrder(K[] s, AVLTree root2) {
		// TODO Auto-generated method stub
		 if(root2 != null)
		 {
		    /*calling the preorder function recursively*/
			 s[i]=(K)root2.key;
				i++; 
			preOrder(s,root2.left);
			
		    preOrder(s,root2.right); 
		 }
		  return (K[]) s;
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
		AVLTree current = root;
		if(current.key.equals(str))
		{
			return (V)current.value;
		}
		else
		{
			while(current != null)
			{
				int comp = ((Comparable<K>) str).compareTo((K) current.key);
			
			//checking whether it is present left or right side of the root node 
				if(comp<0)   //left child
				{
					current = current.left;
					if(current.key.equals(str))
					{
						return (V) current.value;
					}
				}
				else
				{
					current = current.right;
					if(current.key.equals(str))
					{
						return (V) current.value;
					}
				}
		}
	}
	return null;
	}
	AVLTree head;
	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		parent=new AVLTree(key, value);
		head=root;
		root=insert1(root,parent,key,(V) value);
		size++;
	}
	
	private AVLTree insert1(AVLTree root,AVLTree parent,K key,V value)
	{
		if(root==null)
		{
			root=new AVLTree(key,value);
			root.key=key;
			root.value=value;
			height(root);
		}
		else if(key.compareTo((K) root.key)<0)
		{
			root.left=insert1(root.left,root,key,value);
			if((height(root.left))-(height(root.right))==2)
			{
				System.out.println("Height");
				if(key.compareTo((K) root.left.key)<0)
				{
					System.out.println("Welcome..."+root.left.key);
					//height(root);
					root=Rotate1L(root);
				}
				else
				{
					System.out.println("Bye..."+root.key);
					//height(root);
					root=Rotate2L(root);
				}
			}
		}
		else if(key.compareTo((K) root.key)>0)
		{
			root.right=insert1(root.right,root,key,value);
			if(((height(root.right))-(height(root.left))==2))
				
			{
				if(key.compareTo((K) root.right.key)<0)
				{
					root=Rotate2R(root);
				}
				else
				{
					root=Rotate1R(root);
				}
			}
			
		}
		else
		{
			System.out.println("Key already present");
		}
		root.setheight(Math.max(height(root.left), height(root.right))+1);
		return root;
		
	}

	private AVLTree Rotate2R(AVLTree drroot) {
		// TODO Auto-generated method stub
		System.out.println("RL....");
		drroot.right=Rotate1L(drroot.right);
		return Rotate1R(drroot);
	}

	private AVLTree Rotate1R(AVLTree rroot) {
		// TODO Auto-generated method stub
		AVLTree newnode=rroot.right;
		rroot.right=newnode.left;
		newnode.left=rroot;
		rroot.setheight(Math.max(height(rroot.right), height(rroot.left))+1);
		newnode.setheight(Math.max(height(newnode.right), rroot.height)+1);
		return newnode;
		//return null;
	}

	private AVLTree Rotate2L(AVLTree dlroot) {
		// TODO Auto-generated method stub
		System.out.println("LR...");
		dlroot.left=Rotate1R(dlroot.left);
		return Rotate1L(dlroot);
		//return null;
	}

	private AVLTree Rotate1L(AVLTree lroot) {
		// TODO Auto-generated method stub
		AVLTree newnode=lroot.left;
		lroot.left=newnode.right;
		newnode.right=lroot;
		lroot.setheight(Math.max(height(lroot.left), height(lroot.right))+1);
		newnode.setheight(Math.max(height(newnode.left), lroot.height)+1);
		return newnode;
	}

	
	private int height(AVLTree node) {
		// TODO Auto-generated method stub
		if(node==null) {
			return -1;
			}
			if(node.left==null && node.right==null) {
			return 0;
			} else if(node.left==null) {
			return 1+height(node.right);
			} else if(node.right==null) {
			return 1+height(node.left);
			} else {
			return 1+Math.max(height(node.left),height(node.right));
			}

	//	return 0;
	}

	
	
	public void remove(K key) {
		// TODO Auto-generated method stub
		
		
		head=delete(root,key);
		size--;
		root=balance(root);
	}
	
	
	public AVLTree delete(AVLTree head,K key)
	{
		AVLTree trav1,traverse,temp2;
		trav1=head;
		
		if (head == null)
			return head;
		if (key.compareTo((K)head.key) < 0) 
		{
			head.left=(delete(head.left, key));
		}
		else if(key.compareTo((K)head.key) > 0)
		{
			head.right=(delete(head.right, key));
		}
		else
		{
			if(head.left==null && head.right==null)
			{
				head=null;
				//return head;
			}
			else if(head.left==null)
			{
				head=head.right;
				//return head;
			}
			else if(head.right==null)
			{
				head=head.left;
				//return head;
			}
			else
			{				
				traverse=head;
				trav1=traverse;
				temp2=head.left;
				head=head.right;
				
				boolean x=true;
				while(head.left!=null)
				{
					x=false;
					traverse=head;
					head=head.left;
				}
				if(!x){
				
				trav1.key=head.key;
				trav1.value=head.value;
				trav1.left=temp2;
				traverse.left=null;
				}
				
				else
				{
					trav1.key=head.key;
					trav1.value=head.value;
					trav1.right=head.right;
					//trav1.left=temp2;
					
				}
				//return trav1;
				head=trav1;
			}
		}
		
		return head;
	}
	
	public AVLTree balanceL(AVLTree node,K key)
	{
		if((height(node.left))-(height(node.right))==2 )
		{
			
			if(key.compareTo((K) node.left.key)<0)
			{
				System.out.println("..."+node.key);
				node=Rotate1L(node);
			}
			else
			{
				System.out.println("..."+node.key);
				node=Rotate2L(node);
			}
		}
		return node;
	}
	
	
	
	public AVLTree balanceR(AVLTree node,K key)
	{
		if((height(node.left))-(height(node.right))== -2 )
		{
			if(key.compareTo((K) node.right.key)<0)
			{
				System.out.println("..."+node.key);
				node=Rotate2R(node);
			}
			else
			{
				System.out.println("..."+node.key);
				node=Rotate1R(node);
			}
		}
		return node;
	}
	
	public AVLTree balance(AVLTree node)
	{
		if(node!=null)
		{
			node.left=balance(node.left);
			
			if((height(node.left))-(height(node.right))==2 )
			{
				
				if(height(node.left.left)>height(node.left.right))
				{
					System.out.println("..."+node.key);
					System.out.println("node.left.key is "+node.left.key+"    node.left.left.key is "+node.left.left.key);
					node=Rotate1L(node);
					
				}
				else
				{
					System.out.println("..."+node.key);
					node=Rotate2L(node);
				}
			}
			
			
			if((height(node.left))-(height(node.right))== -2 )
			{
				if(height(node.right.left)>height(node.right.right))
				{
					System.out.println("..."+node.key);
					node=Rotate2R(node);
				}
				else
				{
					System.out.println("..."+node.key);
					node=Rotate1R(node);
				}
			}
			node.right=balance(node.right);
			return node;
		}
		return null;
	}
}
