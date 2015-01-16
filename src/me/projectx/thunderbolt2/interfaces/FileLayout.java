package me.projectx.thunderbolt2.interfaces;

import java.io.IOException;
import java.util.List;

public interface FileLayout {
	
	/**
	 * Get the name of the file
	 * 
	 * @return The name of the File
	 */
	public String getName();
	
	/**
	 * Get the path to the file
	 * 
	 * @return The path to the file
	 */
	public String getPath();
	
	/**
	 * Set an object in the file's map. This works like any old Map.
	 * 
	 * @param key : The key
	 * @param value : The value
	 */
	public void set(String key, Object value);
	
	/**
	 * Get an object by a specified key
	 * 
	 * @param key : The key associated with the object
	 * @return The object associated with this key
	 */
	public Object get(String key);
	
	/**
	 * Get a String by a specific key
	 * 
	 * @param key : The key associated with this String
	 * @return The String associated with this key
	 */
	public String getString(String key);
	
	/**
	 * Get a Byte by a specific key
	 * 
	 * @param key : The key associated with this Byte
	 * @return The Byte associated with this key
	 */
	public byte getByte(String key);
	
	/**
	 * Get a Short by a specific key
	 * 
	 * @param key : The key associated with this Short
	 * @return The Short associated with this key
	 */
	public short getShort(String key);
	
	/**
	 * Get an Integer by a specific key
	 * 
	 * @param key : The key associated with this Integer
	 * @return The Integer associated with this key
	 */
	public int getInt(String key);
	
	/**
	 * Get a Double by a specific key
	 * 
	 * @param key : The key associated with this Double
	 * @return The Double associated with this key
	 */
	public double getDouble(String key);
	
	/**
	 * Get a Long by a specific key
	 * 
	 * @param key : The key associated with this Long
	 * @return The Long associated with this key
	 */
	public long getLong(String key);
	
	/**
	 * Get a Float by a specific key
	 * 
	 * @param key : The key associated with this Float
	 * @return The Float associated with this key
	 */
	public float getFloat(String key);
	
	/**
	 * Get a Boolean by a specific key
	 * 
	 * @param key : The key associated with this Boolean
	 * @return The Boolean associated with this key
	 */
	public boolean getBoolean(String key);
	
	/**
	 * Get a List containing various Strings
	 * 
	 * @param key : The key associated with this list
	 * @return String List
	 */
	public List<String> getStringList(String key);
	
	/**
	 * Get a List containing various Bytes
	 * 
	 * @param key : The key associated with this list
	 * @return Byte List
	 */
	public List<Byte> getByteList(String key);
	
	/**
	 * Get a List containing various Shorts
	 * 
	 * @param key : The key associated with this list
	 * @return Short List
	 */
	public List<Short> getShortList(String key);
	
	/**
	 * Get a List containing various Integers
	 * 
	 * @param key : The key associated with this list
	 * @return Integer List
	 */
	public List<Integer> getIntList(String key);
	
	/**
	 * Get a List containing various Doubles
	 * 
	 * @param key : The key associated with this list
	 * @return Double List
	 */
	public List<Double> getDoubleList(String key);
	
	/**
	 * Get a List containing various Longs
	 * 
	 * @param key : The key associated with this list
	 * @return Long List
	 */
	public List<Long> getLongList(String key);
	
	/**
	 * Get a List containing various Floats
	 * 
	 * @param key : The key associated with this list
	 * @return Float List
	 */
	public List<Float> getFloatList(String key);
	
	/**
	 * Save this file to disk
	 * @throws IOException 
	 */
	public void save() throws IOException;
}
