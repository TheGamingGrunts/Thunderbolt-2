package me.projectx.thunderbolt2.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import me.projectx.thunderbolt2.org.json.JSONObject;

public class ThunderFile {
	
	private String name, path;
	private Map<String, Object> map = new HashMap<String, Object>();
	
	public ThunderFile(String name, String path){
		this.name = name;
		this.path = path;
		File f = new File(path + File.separator + name + ".json");
		if (!f.exists()){
			try {
				f.createNewFile();
				JSONObject obj = new JSONObject();
				obj.put("", "");
				PrintWriter writer = new PrintWriter(new FileWriter(path + File.separator + name + ".json", true));
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
	public int getInt(String key){
		return Integer.valueOf(key);
	}
	
	public double getDouble(String key){
		return Double.valueOf(key);
	}
	
	public long getLong(String key){
		return Long.valueOf(key);
	}
	
	public float getFloat(String key){
		return Float.valueOf(key);
	}
	
	public void save(){
		new Thread(){
			public void run(){
				JSONObject obj = new JSONObject();
				for (Map.Entry<String, Object> entry : map.entrySet()){
					obj.put(entry.getKey(), entry.getValue());
					//System.out.println("Added key:" + entry.getKey() + " & value: " + entry.getValue());
				}
				try {
					PrintWriter writer = new PrintWriter(new FileWriter(path + File.separator + name + ".json", true));
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
