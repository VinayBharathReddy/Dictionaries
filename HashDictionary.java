package searchengine.dictionary;

import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class HashDictionary <K extends Comparable<K>, V> implements DictionaryInterface <K,V>{

	
	Hashtable<K,V> hash=new Hashtable<K,V>();
	
	@Override
	public K[] getKeys() {
		// TODO Auto-generated method stub
		Enumeration<K> fr = hash.keys();
		String array[];
		array=new String[hash.size()];//Array.newInstance(keys.getClass(),keys.size());
		for(int i=0;fr.hasMoreElements();i++)
		{
			array[i]=(String)fr.nextElement();
		}
		return (K[])array;
	}

	@Override
	public V getValue(K str) {
		// TODO Auto-generated method stub
		
		return (V) hash.get(str);
	}

	@Override
	public void insert(K key, V value) {
		// TODO Auto-generated method stub
		
		hash.put(key, (V)value);
		//count++;
		
	}

	@Override
	public void remove(K key) {
		// TODO Auto-generated method stub
		
		hash.remove(key);
		
	}

}
