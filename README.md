<img src="http://i.imgur.com/FjGH2mG.png" align="center">
# Thunderbolt 2- Lightning Fast Data Storage

<p>Want an easy and fast way to store your data? Well, look no further! Thunderbolt is a Java file library designed from the ground-up to be as fast as possible, in addition to using a JSON (JavaScript Object Notation) backend for file storage. </p>

[Jar Download](https://dl.project-x.me/files/Thunderbolt.jar)

##Table of Contents
- [JavaDocs] (https://docs.project-x.me/thunderbolt)
- [Syntax] (https://github.com/TheGamingGrunts/Thunderbolt-2#syntax)
- [Example Usage] (https://github.com/TheGamingGrunts/Thunderbolt-2#example-usage)

==
### Syntax
The Thunderbolt syntax is extremely simple and easy to use. All you have to do is instantiate the Thunderbolt class.

```java
Thunderbolt t = new ThunderboltManager();
```

OR

You can simply extend the ThunderboltManager class:

```java
public class Example extends ThunderboltManager {
```

Afterwards, all you have to do is call one of the following methods

| Method | Explanation |
|--------|-------------|
| get(String name) | Get a file by its name. Doesn't require .json extension. Returns a ThunderFile object representation. |
| load(String name, String path) | Load a specific file into memory. If the file doesn't exist, this will create it at the specified path. Doesn't require .json extention. |
| unload(String name) | Unload a file from memory |
| delete(String name) | Delete a file and remove it from memory |

That's basically it in terms of file manipulation. Now, there are numerous options within the file wrapper class, ThunderFile, that you can use as well. Here are all of the things that you can do:

| Method | Explanation |
|--------|-------------|
| getName() | Get the name of the file |
| getPath() | Get the path to the file |
| set(String key, Object value) | Set an object with a given key, like a Map |
| get(String key) | Get an object from the map with the specified key |
| getString(String key) | Get a string from the map with the specified key |
| getByte(String key) | Get a byte from the map with a specified key |
| getShort(String key) | Get a short from the map with a specified key |
| getInt(String key) | Get an Integer from the map with a specified key |
| getDouble(String key) | Get a Double from the map with a specified key |
| getLong(String key) | Get a Long from the map with a specified key |
| getFloat(String key) | Get a Float from the map with a specified key |
| getBoolean(String key) | Get a Boolean from the map with a specified key |
| getStringList(String key) | Return a String list from the map with a specified key |
| getByteList(String key) | Return a Byte list from the map with a specified key |
| getShortList(String key) | Return a Short list from the map with a specified key |
| getIntList(String key) | Return an Integer list from the map with a specified key |
| getDoubleList(String key) | Return a Double list from the map with a specified key |
| getLongList(String key) | Return a Long list from the map with a specified key |
| getFloatList(String key) | Return a Float list from the map with a specified key |
| save() | Save the map contents to the JSON file |

### Example Usage

```java
package me.projectx.thunderbolt2.test;

import java.util.ArrayList;
import java.util.List;

import me.projectx.thunderbolt2.Thunderbolt;
import me.projectx.thunderbolt2.models.ThunderFile;

public class Example { 
	
	public static void main(String[] args){
		Thunderbolt t = new ThunderboltManager(); //You can also just extend Thunderbolt instead of doing this
		
		ThunderFile tf = t.load("test", "C:/Users/Daniel/Desktop");
		List<String> l = new ArrayList<String>();
		l.add("String 1");
		l.add("String 2");
		l.add("String 3");
		tf.set("List", l);
		tf.set("Double", 2.5);
		tf.set("herp", "derp");
		tf.save();
		System.out.println(tf.getDouble("Double"));
		System.out.println(tf.getString("herp"));
		System.out.println(tf.getStringList("List"));
	}
}
```
And here's the output:

```java
[Thunderbolt 2] Loaded test.json
2.5
derp
[String 1, String 2, String 3]
```


