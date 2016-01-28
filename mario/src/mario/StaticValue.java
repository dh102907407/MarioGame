package mario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
	public static List <BufferedImage> allMarioImage =new ArrayList<BufferedImage>();

	public static BufferedImage startImage=null;
	
	public static BufferedImage endImage=null;
	
	public static BufferedImage bgImage=null;
	
	public static List<BufferedImage>  allFlowerImage=new ArrayList<BufferedImage>();
	
	public static List<BufferedImage>  allTriangleImage=new ArrayList<BufferedImage>();

	public static List<BufferedImage>  allTurtleImage=new ArrayList<BufferedImage>();

	public static List<BufferedImage>  allObstructionImage=new ArrayList<BufferedImage>();
	
	public static BufferedImage marioDeadImage=null;
	
	public static String ImagePath=System.getProperty("user.dir")+"/bin/" ;
	//初始化图片

	
	public static void init() {
		for (int i=1;i<=10;i++)
			try {
				//user.dir是用户自己保存的路径
				allMarioImage.add( ImageIO.read(new File(ImagePath+i+".gif")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		try {
			startImage =ImageIO.read(new File(ImagePath+"start.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			endImage =ImageIO.read(new File(ImagePath+"firststageend.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bgImage =ImageIO.read(new File(ImagePath+"firststage.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=1;i<=5;i++){
			try{
			if (i<=2){
				allFlowerImage.add( ImageIO.read(new File(ImagePath+"flower"+i+".gif")));
			}
			if(i<=3){
				allTriangleImage.add( ImageIO.read(new File(ImagePath+"triangle"+i+".gif")));
			}
			if(i<=5){
				allTurtleImage.add( ImageIO.read(new File(ImagePath+"Turtle"+i+".gif")));
			}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		for (int i=1;i<=12;i++)
		{
			try {
				allObstructionImage.add( ImageIO.read(new File(ImagePath+"ob"+i+".gif")));
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
}
