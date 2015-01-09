package me.projectx.thunderbolt2.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.projectx.thunderbolt2.org.json.JSONObject;

public class ThunderFile {
	
	private String name, path;
	private Map<String, Object> map = new HashMap<String, Object>();
	
	@SuppressWarnings("unchecked")
	public ThunderFile(String name, String path){
		this.name = name;
		this.path = path;
		File f = new File(path + File.separator + name + ".json");
		if (!f.exists()){
			try {
				f.createNewFile();
				JSONObject obj = new JSONObject();
				obj.put("", "");
				PrintWriter writer = new PrintWriter(new FileWriter(path + File.separator + name + ".json"));
				writer.write(obj.toJSONString());
				writer.close();
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
    
	private List<?> getList(String key, List<?> l){
		Object o = map.get(key);
		return (List<?>) ((o instanceof List) ? o : l);
		
	}
	
    public List<?> getList(String key) {
        Object def = map.get(key);
        return getList(path, (def instanceof List) ? (List<?>) def : null);
    }
    
    public List<String> getStringList(String key){
    	List<?> temp = getList(key);
    	List<String> list = new ArrayList<String>();
    	for (Object o : temp){
    		list.add(String.valueOf(o));
    	}
    	return list;
    }
	
	public void save(){
		new Thread(){
			@SuppressWarnings("unchecked")
			public void run(){
				JSONObject obj = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()){
					obj.put(entry.getKey(), entry.getValue());
				}
				try {
					PrintWriter writer = new PrintWriter(new FileWriter(path + File.separator + name + ".json", false));
					writer.write(obj.toJSONString());
					writer.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				this.interrupt();
			}
		}.start();
	}
}
