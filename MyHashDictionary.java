package searchengine.dictionary;

class ListNode<K,V>
{
	K key;
	V value;
	ListNode next;
	ListNode perp;
	int count1;
}


public class MyHashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>

{
	ListNode<K,V>[] hasharray;
	int count;
	int arraysize;
	public MyHashDictionary() 
	{
		hasharray = new ListNode[13];
		int i = 0;
		arraysize = 13;
		while(i<arraysize)
		{
			hasharray[i]=new ListNode<K,V>();
			i++;
		 }
	}
	private int hashCode(String key)
	{
	    int hashVal = 0;
	    for (int i = 0; i < key.length(); i++) 
	    {
	      hashVal = (127 * hashVal + key.charAt(i)) % 16908799;
	    }
	    return hashVal;
	 }
	private int compressionfunc(int hashval)
	{
		return hashval % arraysize;
	}

	@Override
	public K[] getKeys() 
	{
		int i=0;
		String[] allkey = new String[count];
		ListNode<K, V> temp;
		for(int k=0;k<13;k++)
		{

			if(hasharray[k].next!=null)
			{
				temp=hasharray[k];
				while(temp.next!=null)
				{
					temp=temp.next;
					allkey[i]=(String) temp.key;
					i++;
					
				}
			}
			if(k==12)	
			return (K[]) allkey;
		}
		
		return null;
	}

	@Override
	public V getValue(K str) 
	{
		int i=0;
		ListNode<K, V> temp;
		ListNode<K,V> temp1 = new ListNode<K,V>();
		String[] allvalue = null;
		for(int k=0;k<13;k++)
		{
			if(hasharray[k].next!=null)
			{
				temp=hasharray[k];
				while(temp.next!=null)
				{
					temp=temp.next;
					if(temp.key.equals(str))
					{
						 allvalue = new String[temp.count1+1];
						allvalue[i]=(String) temp.value;
						System.out.println(allvalue[i]+"*");
						i++;
						temp1.next=temp.perp;
						if(temp1.next!=null)
						{
							
						while(temp1.next!=null)
						{
							
							temp1 = temp1.next;
							allvalue[i]=(String) temp1.value;
							System.out.println(allvalue[i]+"*");
							i++;
						}			
						}
					}
					
				}
			}
			if(k==12)
				return  (V)allvalue;
		}
		
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(K key, V value) 
	{
		int j,flag=0;
		ListNode<K,V> l1= new ListNode<K,V>();
		ListNode<K,V> temp;
		ListNode<K,V> tempo;
		l1.key=key;
		l1.value=value;
		j=compressionfunc(hashCode((String) key));
		tempo=hasharray[j];
		if(tempo.next!=null)
		{
			while(tempo.next!=null)
			{
				tempo=tempo.next;
				if(tempo.key.equals(key))
				{
					if(tempo.perp==null)
					{
						tempo.perp=l1;
						tempo.count1=tempo.count1+1;
					}
					else
					{
						ListNode<K,V> tempo1;
						tempo1=tempo.perp;
						tempo.perp=l1;
						l1.next=tempo1;
						tempo.count1=tempo.count1+1;
						
					}
					flag=1;
				}
			}
		}
		if(flag!=1)
		{
			if(hasharray[j].next==null)
			{
				hasharray[j].next=l1;	
				count=count+1;
			}
			else
			{
				temp=hasharray[j].next;
				hasharray[j].next=l1;
				l1.next=temp;
				count=count+1;
			}
		}
	}

	@Override
	public void remove(K key) 
	{
		int i=0;
		String[] allkey = new String[count];
		ListNode<K, V> temp;
		for(int k=0;k<13;k++)
		{
	
			if(hasharray[k].next!=null)
			{
				temp=hasharray[k];
				while(temp.next!=null)
				{
					if(temp.next.key.equals(key))
						temp.next=temp.next.next;
					temp=temp.next;
					if(temp==null)
						break;
				}
			}
		}
		count=count-1;
	}
	
}
