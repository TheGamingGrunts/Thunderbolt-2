package me.projectx.thunderbolt2.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.projectx.thunderbolt2.interfaces.FileLayout;
import me.projectx.thunderbolt2.org.json.JSONObject;

/**
 * File wrapper class containing numerous useful methods for setting, saving, and retrieving information
 * from JSON files
 * 
 * @author Daniel S. (The Gaming Grunts)
 */
public class ThunderFile implements FileLayout{
	
	private String name, path;
	public Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * @see ThunderFile 
	 * @param name : The name of the file, excluding the .json extension.
	 * @param path : The path to the file
	 */
	public ThunderFile(String name, String path){
		this.name = name;
		this.path = path;
		File f = new File(path + File.separator + name + ".json");
		if (!f.exists()){
			try {
				f.createNewFile();
				System.out.println("[ThunderBolt 2] Created new file " + name + ".json at " + path);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getName(){
		return name;
	}
	
	public String getPath(){
		return path;
	}
	
	public void set(String key, Object value){
		map.put(key, value);
	}
	
	public Object get(String key){
		return map.get(key);
	}
	
	public String getString(String key){
		return (String)map.get(key);
	}
	
	public byte getByte(String key){
		return Byte.parseByte(map.get(key).toString());
	}
	
	public short getShort(String key){
		return Short.parseShort(map.get(key).toString());
	}
	
	public int getInt(String key){
		return Integer.parseInt(map.get(key).toString());
	}
	
	public double getDouble(String key){
		return Double.parseDouble(map.get(key).toString());
	}
	
	public long getLong(String key){
		return Long.parseLong(map.get(key).toString());
	}
	
	public float getFloat(String key){
		return Float.parseFloat(map.get(key).toString());
	}
	
	public boolean getBoolean(String key){
		return Boolean.parseBoolean(map.get(key).toString());
	}
    
	/**
	 * Courtesy of the Bukkit Project 
	 * {@link https://github.com/Bukkit/Bukkit/blob/master/src/main/java/org/bukkit/configuration/MemorySection.java#L355}
	 */
	private List<?> getList(String key, List<?> l){
		Object o = map.get(key);
		return (List<?>) ((o instanceof List) ? o : l);
		
	}
	
	/**
	 * Courtesy of the Bukkit Project 
	 * {@link https://github.com/Bukkit/Bukkit/blob/master/src/main/java/org/bukkit/configuration/MemorySection.java#L360}
	 */
    private List<?> getList(String key) {
        Object o = map.get(key);
        return getList(path, (o instanceof List) ? (List<?>) o : null);
    }
    
    public List<String> getStringList(String key){
    	List<?> temp = getList(key);
    	List<String> list = new ArrayList<String>();
    	for (Object o : temp){
    		if (o instanceof String){
    			list.add(String.valueOf(o).replace("\"", ""));
    		}
    	}
    	return list;
    }
    
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

    /*public Map<?, ?> getMap(String key){
    	Iterator<?> i = map.entrySet().iterator();
    	Map<String, Object> newMap = new HashMap<String, Object>();
    	while(i.hasNext()){
    		String key1 = (String)i.next();
    		Object value = map.get(key1);
    		newMap.put(key1, value);
    	}
    	return newMap;
    }*/
	
	public void save(){
		new Thread(){
			public void run(){
				JSONObject obj = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()){
					obj.put(entry.getKey(), entry.getValue());
				}
				try {
					PrintWriter writer = new PrintWriter(new FileWriter(path + File.separator + name + ".json", false));
					writer.write(obj.toString(2));
					writer.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				this.interrupt();
			}
		}.start();
	}
}
