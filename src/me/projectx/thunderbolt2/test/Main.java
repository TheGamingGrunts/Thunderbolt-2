package me.projectx.thunderbolt2.test;

import me.projectx.thunderbolt2.Thunderbolt;
import me.projectx.thunderbolt2.models.ThunderFile;

public class Main {
	
	public static void main(String[] args){
		Thunderbolt t = new Thunderbolt();
		t.load("test", "C:/Users/Daniel/Desktop");
		
		ThunderFile tf = t.get("test");
		//tf.set("test", "hello");
		//tf.set("hello", "goodbye");
		//tf.save();
		
		//t.unload("test");
		
		//t.load("test", "C:/Users/Daniel/Desktop");
		
		System.out.println(tf.get("test"));
	}
}
