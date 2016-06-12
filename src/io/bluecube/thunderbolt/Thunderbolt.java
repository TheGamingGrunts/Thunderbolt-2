package io.bluecube.thunderbolt;

import io.bluecube.thunderbolt.exceptions.FileLoadException;
import io.bluecube.thunderbolt.io.ThunderFile;
import io.bluecube.thunderbolt.io.ThunderboltThreadPool;
import io.bluecube.thunderbolt.utils.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public abstract class Thunderbolt {

    private final static Map<String, ThunderFile> fileMap = new HashMap<String, ThunderFile>();

    /**
	 * Get a file by its name. Doesn't require .json extension. This method is thread-safe
	 *
     * <p>This method returns null if the file is not loaded</p>
     *
	 * @param name : The name of the file to get.
	 */
    public ThunderFile get(String name) {
        name = Validator.checkName(name);

        synchronized (fileMap) {
            return fileMap.get(name);
        }
    }

    private static ThunderFile create(String name, String path) throws FileLoadException {
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
    
    /**
	 * Load a file into memory. 
	 * <p>
	 * NOTE: Make sure to call {@link Thunderbolt#unload(String)} when you're done using this file,
	 * otherwise a memory leak may occur!
	 * 
	 * @param name : The name of the file to load.
	 * @param path : The path to the file.
	 * @throws IOException 
	 * @throws FileLoadException 
	 */
    public static ThunderFile load(String name, String path) throws FileLoadException, IOException {
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
    				final String s1 = name;
    				final String s2 = path;
    				Callable<byte[]> c = new Callable<byte[]>(){
						@Override
						public byte[] call() throws Exception {
							return Files.readAllBytes(Paths.get(s2 + File.separator + s1 + ".json"));
						}
    				};
    				try {
						tf = new ThunderFile(name, path, new String(ThunderboltThreadPool.getPool().submit(c).get()));
					} catch(InterruptedException | ExecutionException e) {
						tf = new ThunderFile(name, path);
						System.out.println("[Thunderbolt] Error loading '" + name + ".json'. Please check for errors and try again.");
					}
    			} else {
    				tf = new ThunderFile(name, path);
                }
    			fileMap.put(name, tf);
    			return tf;
    		} else {
    			return create(name, path);
    		}
    	} else {
    		throw new FileLoadException(name);
    	}
    }

    /**
	 * Unload a file from memory. 
	 * 
	 * @param name : The name of the file to unload
	 * @throws IllegalArgumentException
	 */
    public static void unload(String name) {
        name = Validator.checkName(name);

        ThunderFile file;
        synchronized (fileMap) {
            file = fileMap.remove(name);
        }

        if (file == null) {
            new FileNotFoundException("[Thunderbolt] The file '" + name + ".json' isn't loaded and/or doesn't exist.")
                    .printStackTrace();
        }
    }

    /**
	 * Delete a file and remove it from memory.
	 * 
	 * @param name : The name of the file to delete
	 * @throws IOException 
	 */
    public static void delete(String name) {
        name = Validator.checkName(name);

        ThunderFile tf;
        synchronized (fileMap) {
            tf = fileMap.remove(name);
        }

        if (tf == null) {
            new FileNotFoundException("[Thunderbolt] The file '" + name + ".json' isn't loaded and/or doesn't exist.")
                    .printStackTrace();
        } else {
        	delete(name, tf.getPath());
        }
    }

    /**
	 * Delete a file on disk. 
	 * 
	 * @param name : The name of the file
	 * @param path : The path to the file
	 */
    private static void delete(String name, String path) {
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
            delete(name);
        }
    }
}