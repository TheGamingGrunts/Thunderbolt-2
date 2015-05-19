package me.projectx.thunderbolt2.managers;

<<<<<<< HEAD
=======
import me.projectx.thunderbolt2.Thunderbolt;
import me.projectx.thunderbolt2.exceptions.FileLoadException;
import me.projectx.thunderbolt2.models.ThunderFile;
import me.projectx.thunderbolt2.utils.Validator;

>>>>>>> 6052ec4e32512de95c9732b56271d60162960a09
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

<<<<<<< HEAD
import me.projectx.thunderbolt2.Thunderbolt;
import me.projectx.thunderbolt2.exceptions.FileLoadException;
import me.projectx.thunderbolt2.models.ThunderFile;
import me.projectx.thunderbolt2.utils.Validator;

public class ThunderboltManager implements Thunderbolt {
	
	private Map<String, ThunderFile> fileMap = new HashMap<String, ThunderFile>();
	
	public ThunderFile get(String name){
		return fileMap.get(Validator.checkName(name));
	}
	
	private ThunderFile create(String name, String path) throws FileLoadException{
		name = Validator.checkName(name);
		if (!fileMap.containsKey(name)){
			ThunderFile tf = new ThunderFile(name, path);
			fileMap.put(name, tf);
			return tf;
		}else{
			throw new FileLoadException(name);
		}
	}
	
	public ThunderFile load(String name, String path) throws IOException, FileLoadException{
		name = Validator.checkName(name);
		if (!fileMap.containsKey(name)){
			File f = new File(path + File.separator + name + ".json");
			if (f.exists()){
				ThunderFile tf = null;
				if (f.length() != 0){
					byte[] data = Files.readAllBytes(Paths.get(path + File.separator + name + ".json"));
					tf = new ThunderFile(name, path, new String(data));
				}else{
					tf = new ThunderFile(name, path);
				}
				fileMap.put(name, tf);
				return tf;
			}else{
				return this.create(name, path);
			}
		}else{
			throw new FileLoadException(name);
		}
	}
	
	public void unload(String name){
		name = Validator.checkName(name);
		if (fileMap.containsKey(name)){
			fileMap.remove(name);
		}else{
			try {
				throw new FileNotFoundException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String name){
		try{
			name = Validator.checkName(name);
			ThunderFile tf = fileMap.get(name);
			if (tf != null){			
				fileMap.remove(name);
			}else{
				throw new FileNotFoundException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.");
			}
			this.delete(name, tf.getPath());
		} catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public void delete(String name, String path){
		name = Validator.checkName(name);
		if (!fileMap.containsKey(name)){
			try{
				Files.delete(Paths.get(path + File.separator + name + ".json"));
			}catch(IOException e){
				e.printStackTrace();
			}
		}else{
			this.delete(name);
		}
	}
=======
public class ThunderboltManager implements Thunderbolt {

    private final Map<String, ThunderFile> fileMap = new HashMap<String, ThunderFile>();

    public ThunderFile get(String name) {
        name = Validator.checkName(name);

        synchronized (fileMap) {
            return fileMap.get(name);
        }
    }

    private ThunderFile create(String name, String path) throws FileLoadException {
        name = Validator.checkName(name);

        synchronized (fileMap) {
            if (!fileMap.containsKey(name)) {
                ThunderFile tf = new ThunderFile(name, path);
                fileMap.put(name, tf);

                return tf;
            } else {
                throw new FileLoadException(name);
            }
        }
    }

    public ThunderFile load(String name, String path) throws FileLoadException, IOException {
        name = Validator.checkName(name);

        boolean contains;
        synchronized (fileMap) {
            contains = fileMap.containsKey(name);
        }

        if (!contains) {
            File f = new File(path + File.separator + name + ".json");
            if (f.exists()) {
                ThunderFile tf;
                if (f.length() != 0) {
                    byte[] data = Files.readAllBytes(Paths.get(path + File.separator + name + ".json"));
                    tf = new ThunderFile(name, path, new String(data));
                } else {
                    tf = new ThunderFile(name, path);
                }
                fileMap.put(name, tf);
                return tf;
            } else {
                return this.create(name, path);
            }
        } else {
            throw new FileLoadException(name);
        }
    }

    public void unload(String name) {
        name = Validator.checkName(name);

        ThunderFile file;
        synchronized (fileMap) {
            file = fileMap.remove(name);
        }

        if (file == null) {
            new FileNotFoundException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.")
                    .printStackTrace();
        }
    }

    public void delete(String name) {
        name = Validator.checkName(name);

        ThunderFile tf;
        synchronized (fileMap) {
            tf = fileMap.remove(name);
        }

        if (tf == null) {
            new FileNotFoundException("[Thunderbolt 2] The file '" + name + ".json' isn't loaded and/or doesn't exist.")
                    .printStackTrace();
        } else {
            this.delete(name, tf.getPath());
        }
    }

    public void delete(String name, String path) {
        name = Validator.checkName(name);

        boolean exists;
        synchronized (fileMap) {
            exists = fileMap.containsKey(name);
        }

        if (!exists) {
            try {
                Files.delete(Paths.get(path + File.separator + name + ".json"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.delete(name);
        }
    }
>>>>>>> 6052ec4e32512de95c9732b56271d60162960a09
}