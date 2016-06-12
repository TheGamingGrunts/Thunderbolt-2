package io.bluecube.thunderbolt.io;

import io.bluecube.thunderbolt.org.json.JSONArray;
import io.bluecube.thunderbolt.org.json.JSONObject;
import io.bluecube.thunderbolt.utils.Validator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * File class containing numerous useful methods for 
 * setting, saving, and retrieving information from JSON files
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public class ThunderFile {
	
	private final String name, path;
	private JSONObject jo = new JSONObject();
	
	/**
	 * @see ThunderFile 
	 * @param name : The name of the file, excluding the .json extension.
	 * @param path : The path to the file
	 */
	public ThunderFile(String name, String path){
		this.path = path;
		this.name = Validator.checkName(name);

		File f = new File(path + File.separator + name + ".json");
		if (!f.exists()){
			try {
				f.createNewFile();
				System.out.println("[Thunderbolt] Created new file " + name + ".json at " + path);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("[Thunderbolt] A file named '" + name + ".json' already exists at the specified path, " + path);
		}
	}
	
	/**
	 * @see ThunderFile
	 * @param name : The name of the file, excluding the .json extension.
	 * @param path : The path to the file
	 * @param jsonData : JSON-formatted data
	 */
	public ThunderFile(String name, String path, String jsonData){
		this(name, path);
		jo = new JSONObject(jsonData);
	}
	
	/**
	 * Get the name of the file
	 * 
	 * @return The name of the File
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Get the path to the file
	 * 
	 * @return The path to the file
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Set an object in the file's map. This works like any old Map.
	 * 
	 * @param key : The key
	 * @param value : The value
	 */
	public void set(String key, Object value){
		jo.put(key, value);
	}
	
	/**
	 * Get an object by a specified key
	 * 
	 * @param key : The key associated with the object
	 * @return The object associated with this key
	 */
	public Object get(String key){
		return jo.get(key);
	}
	
	/**
	 * Get a String by a specific key
	 * 
	 * @param key : The key associated with this String
	 * @return The String associated with this key
	 */
	public String getString(String key){
		return (String)jo.get(key);
	}
	
	/**
	 * Get a Byte by a specific key
	 * 
	 * @param key : The key associated with this Byte
	 * @return The Byte associated with this key
	 */
	public byte getByte(String key){
		return (byte)jo.get(key);
	}
	
	/**
	 * Get a Short by a specific key
	 * 
	 * @param key : The key associated with this Short
	 * @return The Short associated with this key
	 */
	public short getShort(String key){
		return (short)jo.get(key);
	}
	
	/**
	 * Get an Integer by a specific key
	 * 
	 * @param key : The key associated with this Integer
	 * @return The Integer associated with this key
	 */
	public int getInt(String key){
		return (int)jo.get(key);
	}
	
	/**
	 * Get a Double by a specific key
	 * 
	 * @param key : The key associated with this Double
	 * @return The Double associated with this key
	 */
	public double getDouble(String key){
		return (double)jo.get(key);
	}
	
	/**
	 * Get a Long by a specific key
	 * 
	 * @param key : The key associated with this Long
	 * @return The Long associated with this key
	 */
	public long getLong(String key){
		return (long)jo.get(key);
	}
	
	/**
	 * Get a Float by a specific key
	 * 
	 * @param key : The key associated with this Float
	 * @return The Float associated with this key
	 */
	public float getFloat(String key){
		return (float)jo.get(key);
	}
	
	/**
	 * Get a Boolean by a specific key
	 * 
	 * @param key : The key associated with this Boolean
	 * @return The Boolean associated with this key
	 */
	public boolean getBoolean(String key){
		return (boolean)jo.get(key);
	}
    
	
	private List<?> getList(String key) {
        Object o = jo.get(key);
        List<Object> l = new ArrayList<Object>();
        JSONArray ja = new JSONArray(o.toString());
        for (int i = 0; i < ja.length(); i++){
        	l.add(ja.get(i));
        }
        return l;
    }
    
	/**
	 * Get a List containing various Strings
	 * 
	 * @param key : The key associated with this list
	 * @return String List
	 */
    public List<String> getStringList(String key){
    	List<?> temp = getList(key);
    	List<String> list = new ArrayList<String>();
    	for (Object o : temp){
    		if (o instanceof String){
    			list.add(String.valueOf(o));
    		}
    	}
    	return list;
    }
    
    /**
	 * Get a List containing various Bytes
	 * 
	 * @param key : The key associated with this list
	 * @return Byte List
	 */
    public List<Byte> getByteList(String key){
    	List<?> temp = getList(key);
    	List<Byte> list = new ArrayList<Byte>();
    	for (Object o : temp){
    		if (o instanceof Byte){
    			list.add((Byte)o);
    		}else if (o instanceof String){
    			list.add(Byte.valueOf((String)o));
    		}
    	}
    	return list;
    }
    
    /**
	 * Get a List containing various Shorts
	 * 
	 * @param key : The key associated with this list
	 * @return Short List
	 */
    public List<Short> getShortList(String key){
    	List<?> temp = getList(key);
    	List<Short> list = new ArrayList<Short>();
    	for (Object o : temp){
    		if (o instanceof Short){
    			list.add((Short)o);
    		}else if (o instanceof String){
    			list.add(Short.valueOf((String)o));
    		}
    	}
    	return list;
    }
    
    /**
	 * Get a List containing various Integers
	 * 
	 * @param key : The key associated with this list
	 * @return Integer List
	 */
    public List<Integer> getIntList(String key){
    	List<?> temp = getList(key);
    	List<Integer> list = new ArrayList<Integer>();
    	for (Object o : temp){
    		if (o instanceof Integer){
    			list.add((Integer)o);
    		}else if (o instanceof String){
    			list.add(Integer.valueOf((String)o));
    		}
    	}
    	return list;
    }
    
    /**
	 * Get a List containing various Doubles
	 * 
	 * @param key : The key associated with this list
	 * @return Double List
	 */
    public List<Double> getDoubleList(String key){
    	List<?> temp = getList(key);
    	List<Double> list = new ArrayList<Double>();
    	for (Object o : temp){
    		if (o instanceof Double){
    			list.add((Double)o);
    		}else if (o instanceof String){
    			list.add(Double.valueOf((String)o));
    		}
    	}
    	return list;
    }
    
    /**
	 * Get a List containing various Longs
	 * 
	 * @param key : The key associated with this list
	 * @return Long List
	 */
    public List<Long> getLongList(String key){
    	List<?> temp = getList(key);
    	List<Long> list = new ArrayList<Long>();
    	for (Object o : temp){
    		if (o instanceof Long){
    			list.add((Long)o);
    		}else if (o instanceof String){
    			list.add(Long.valueOf((String)o));
    		}
    	}
    	return list;
    }
    
    /**
	 * Get a List containing various Floats
	 * 
	 * @param key : The key associated with this list
	 * @return Float List
	 */
    public List<Float> getFloatList(String key){
    	List<?> temp = getList(key);
    	List<Float> list = new ArrayList<Float>();
    	for (Object o : temp){
    		if (o instanceof Integer){
    			list.add((Float)o);
    		}else if (o instanceof String){
    			list.add(Float.valueOf((String)o));
    		}
    	}
    	return list;
    }
    
    /**
     * Get an array given a key
     * 
     * @param key The key associated with the array
     * @return A generic object array
     * @throws IllegalArgumentException Value is not an array
     */
	@SuppressWarnings("unchecked")
	public <T> T[] getArray(String key){
    	Object array = jo.get(key);
    	if (array instanceof Object[]){
    		return (T[]) jo.get(key);
    	}else{
    		throw new IllegalArgumentException("Value associated with key '" + key + "' is not an Array");
    	}
    }
    
	/**
	 * Get a Map given a key
	 * 
	 * @param key The key associated with the Map
	 * @return A Map
	 * @throws IllegalArgumentException Value is not a Map
	 */
    public Map<?, ?> getMap(String key){
    	Object map = jo.get(key);
    	if (map instanceof Map){
    		return (Map<?, ?>) jo.get(key);
    	}else{
    		throw new IllegalArgumentException("Value associated with key '" + key + "' is not an instance of Map");
    	}
    }
	
    /**
	 * Save this file to disk
	 * 
	 * @throws IOException 
	 */
	public void save() throws IOException{
		Runnable r = new Runnable(){
			public void run(){
				try {
					Writer writer = new PrintWriter(new FileWriter(path + File.separator + name + ".json", false));
					writer.write(jo.toString(2));
					writer.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		};
		ThunderboltThreadPool.getPool().submit(r);
	}
	
	/**
	 * Get a Set containing all the keys in the map
	 * 
	 * @return A Set containing all keys
	 */
	public Set<String> keySet(){
		return jo.keySet();
	}
	
	/**
	 * Get a Set containing all the values in the map
	 * 
	 * @return A Set containing all the values
	 */
	public Set<Object> valueSet(){
		Set<Object> set = new HashSet<Object>();
		Iterator<String> i = jo.keys();
		while (i.hasNext()){
			set.add(jo.get(i.next()));
		}
		return set;
	}
}
