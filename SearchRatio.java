import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SearchRatio {
	
	String config;
	File file;
	double ratioX, ratioY;
	public SearchRatio(File file){
		config = new String("");
		this.file = file;
		this.read();
	}
	
	public void read(){
		try{
			InputStream ips = new FileInputStream(this.file);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				//System.out.println(ligne);
				this.config+=ligne+"\n";
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void findR(String s){
		s = s.toLowerCase();
		int pos = this.config.indexOf(s)+s.length();
		String[] sub = config.substring(pos).split(";", 3);
		this.ratioX = Double.parseDouble(sub[0]);
		this.ratioY = Double.parseDouble(sub[1]);
		//System.out.println(ratioX + " " + ratioY);
	}

}
