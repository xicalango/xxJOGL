package gp.world.texture;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

public class ListTextureLoader extends TextureLoader {

	public ListTextureLoader(TextureStore store) {
		super(store);
	}

	
	public Texture[] load(String filename) {
		
		BufferedReader in = null;
		
		FileTextureLoader ftl = new FileTextureLoader(store);
		
		try{
			 in = new BufferedReader(new FileReader(filename));
			 
			 String line;
			 List<Texture> textures = new ArrayList<Texture>();
			 
			 while( (line=in.readLine()) != null) {
				 
				 String[] splitLine = line.split(":=");
				 
				 textures.add(ftl.load(splitLine[1], splitLine[0]));
			 }
			 
			 return textures.toArray(new Texture[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		//Fehlerfall
		return null;
		
	}
	
	
}
