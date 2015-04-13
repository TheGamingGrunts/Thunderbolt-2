package me.projectx.thunderbolt2.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.projectx.thunderbolt2.models.ThunderFile;
import me.projectx.thunderbolt2.org.json.JSONObject;

public abstract class FileManager {
	
	private volatile Map<String, ThunderFile> fileMap = new HashMap<String, ThunderFile>();
	
	public ThunderFile get(String name){
		return fileMap.get(name);
	}
	
	private ThunderFile create(final String name, final String path){
		if (fileMap.get(name) == null){
			ThunderFile tf = new ThunderFile(name, path);
			fileMap.put(name, tf);
			return tf;
		}else{
			throw new IllegalArgumentException("[Thunderbolt 2] The file '" + name + ".json' is already loaded!");
		}
	}
	
	protected synchronized ThunderFile load(String name, String path) throws IOException{
		if (fileMap.get(name) == null){
			File f = new File(path + File.separator + name + ".json");
			if (f.exists()){
				ThunderFile tf = new ThunderFile(name, path);
				if (f.length() != 0){
					BufferedReader br = new BufferedReader(new FileReader(f));
					String line;
					String jsonData = "";
					while ((line = br.readLine()) != null){
						jsonData += line + "\n";
					}
					br.close();
					JSONObject obj = new JSONObject(jsonData);
					Iterator<?> i = obj.keySet().iterator();
					while (i.hasNext()){
						String key = (String) i.next();
						tf.set(key, obj.get(key));
					}
					return tf;
				}
				fileMap.put(name, tf);
				return tf;
			}else{
				create(name, path);
			}
		}else{
			throw new IllegalArgumentException("[Thunderbolt 2] The file '" + name + ".json' is already loaded!");
		}
		return null;
	}
	
	protected synchronized void unload(final String name) throws IllegalArgumentException{
		new Thread(){
			public void run(){
				ThunderFile tf = fileMap.get(name);
				if (tf != null){
					fileMap.remove(tf);
					tf = null;
				}else{
					throw new IllegalArgumentException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
				}
			}
		}.start();
	}
	
	protected synchronized void delete(final String name) throws IOException{
		new Thread(){
			public void run(){
				ThunderFile tf = fileMap.get(name);
				if (tf != null){
					try {
						fileMap.remove(name);
						Files.delete(Paths.get(tf.getPath() + File.separator + name + ".json"));
						tf = null;
					} catch(IOException e) {
						e.printStackTrace();
					}
				}else{
					throw new IllegalArgumentException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
				}
			}
		}.start();
	}
}