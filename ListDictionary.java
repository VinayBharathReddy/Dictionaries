package searchengine.dictionary;



public class ListDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>
{
	class ListNode<K,V>
	{
		K key;
		V value;
		ListNode next_value;
		ListNode appendroot;
		int newcount;
		ListNode(K key,V value,ListNode next_value)
		{
			this.key=key;
			this.value=value;
			this.next_value=next_value;
			
		}
		public ListNode(K key2, V value2)
		{
			this.key=key2;
			this.value=value2;
		}
	}
	private ListNode <K,V> first=null;
	private int count;
	public ListDictionary()
	{
		first=new ListNode<K,V>(null,null,null);
	}

	@Override
	public K[] getKeys()
	{
		int i=0;
		String[] allkey = new String[count];
		ListNode<K,V> temp;
		temp=first;
		while(temp.next_value!=null)
		{
			temp=temp.next_value;
			allkey[i]=(String) temp.key;
			i++;
		}
		
		
		return (K[]) allkey;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V getValue(K str)
	{
		ListNode<K,V> temp;
		ListNode<K,V> temp1 = new ListNode<K, V>(null,null);
		int i=0;
		
		temp=first;
		while(temp.next_value!=null)
		{
			temp=temp.next_value;
			if(temp.key.equals(str))
			{
				String[] allvalue = new String[temp.newcount+1];
				allvalue[i]=(String) temp.value;
				i++;
				temp1.next_value=temp.appendroot;
				if(temp1.next_value!=null)
				{
					
				while(temp1.next_value!=null)
				{
					
					temp1=temp1.next_value;
					allvalue[i]=(String) temp1.value;
					i++;
				}			
				}
				return (V) allvalue;
			}
		}
		return null;
	}

	@Override
	public void insert(K key, V value)
	{
		int flag=0;
		ListNode<K,V> newListNode = new ListNode<K,V>(key,value);
		ListNode<K,V> tempo;
		tempo=first;
		if(tempo.next_value!=null)
		{
			while(tempo.next_value!=null)
			{
				tempo=tempo.next_value;
				if(tempo.key.equals(key))
				{
					
					if(tempo.appendroot==null)
					{
						tempo.appendroot=newListNode;
						tempo.newcount=tempo.newcount+1;
					}
					else
					{
						ListNode<K,V> tempo1;
						tempo1=tempo.appendroot;
						tempo.appendroot=newListNode;
						newListNode.next_value=tempo1;
						tempo.newcount=tempo.newcount+1;
						
					}
					flag=1;
				}
			}
		}
		
		if(flag!=1)
		{
		if(first.next_value==null)
		{
			first.next_value=newListNode;
		}
		else
		{
			ListNode<K,V> temp;
			temp=first.next_value;
			first.next_value=newListNode;
			newListNode.next_value=temp;
		}
		count=count+1;
		}
	}

	@Override
	public void remove(K key) 
	{
		ListNode<K,V> l=new ListNode<K,V>( key,getValue(null));

		   ListNode current = first;
		    ListNode previous=current ;
		    while(current != null)
		    {
		    	//System.out.println("haiii");
		    	 if(first.key.equals(key))
		            {
		    		 first=first.next_value;
		            
		            break;
		            }
		        if(current.key.equals(key))
		        {
			           // current=previous.next_value ;
			            previous.next_value=current.next_value;
			            break;
		           
		           // break;
		        }
		        previous = current;
		        current = current.next_value;             
		           
		    }
		    count=count-1;
		    getKeys();

	}
}