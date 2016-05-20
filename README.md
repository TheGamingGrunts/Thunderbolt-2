<img src="http://i.imgur.com/FjGH2mG.png" align="center">
# Thunderbolt 2- Lightning Fast Data Storage

<p>Want an easy and fast way to store your data? Well, look no further! Thunderbolt is a Java file library designed from the ground-up to be as fast, simple, and efficient, in addition to using a JSON (JavaScript Object Notation) backend for file storage. </p>

[Jar Download](https://www.spigotmc.org/resources/thunderbolt-2-lightning-fast-data-storage.3179/)

##Table of Contents
- [JavaDocs] (http://http://docs.bluecube.io/thunderbolt/)
- [Syntax] (https://github.com/TheGamingGrunts/Thunderbolt-2#syntax)
- [Example Usage] (https://github.com/TheGamingGrunts/Thunderbolt-2#example-usage)

==
### Syntax
The Thunderbolt syntax is extremely simple and easy to use. Use the following tables to get started, in addition to the JavaDocs listed above.

**Thunderbolt.java**

*All methods in this class are static*

| Method | Explanation |
|--------|-------------|
| get(String name) | Get a file by its name. Doesn't require .json extension. Returns a ThunderFile object representation. |
| load(String name, String path) | Load a specific file into memory. If the file doesn't exist, this will create it at the specified path. Doesn't require .json extention. |
| unload(String name) | Unload a file from memory |
| delete(String name) | Delete a file and remove it from memory |

**ThunderFile.java**

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
| keySet() | Get a Set containing all of the keys |
| valueSet() | Get a Set containing all of the values |

### Example Usage

```java
import io.bluecube.thunderbolt.Thunderbolt;
import io.bluecube.thunderbolt.exceptions.FileLoadException;
import io.bluecube.thunderbolt.io.ThunderFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Test{

	public static void main(String[] args){
		ThunderFile tf = null;
		try {
			tf = Thunderbolt.load("test", "C:/Users/Daniel/Desktop");
		} catch(FileLoadException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		List<String> l = new ArrayList<String>();
		l.add("String 1");
		l.add("String 2");
		l.add("String 3");
		tf.set("List", l);
		tf.set("Double", 2.5);
		tf.set("herp", "derp");
		try {
			tf.save();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(tf.getDouble("Double"));
		System.out.println(tf.getString("herp"));
		System.out.println(tf.getStringList("List"));
	}
}
```
Here's the output:

```java
[Thunderbolt 2] Loaded test.json
2.5
derp
[String 1, String 2, String 3]
```
And here's what the file looks like:

```java
{
  "List": [
    "String 1",
    "String 2",
    "String 3"
  ],
  "herp": "derp",
  "Double": 2.5
}
```

