package mario;

import java.awt.image.BufferedImage;

public class Obstruction implements Runnable{
	private int  x,y;

	private Thread t=new Thread(this);
	
	private int type;
	
	
	public void setType(int type) {
		this.type = type;
	}
	private int startType;
	
	private BufferedImage showImage=null;
	
	private BackGround bg;
	
	
	//构造图片
	public Obstruction (int x, int y, int type,BackGround bg)
	{	this.startType=type;
		this.x=x;
		this.y=y;
		this.bg=bg;
		this.type=type;
		setImage();
		if(this.type==11){
			t.start();
			//这一步制定最后下降的是旗子
		}
	}
	
	

	public void reset(){
		this.type = startType;
		this.setImage();
	}
	
	public void setImage(){
		showImage = StaticValue.allObstructionImage.get(type);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	public BufferedImage getShowImage() {
		return showImage;
	}
	public int getType() {
		return type;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if (this.bg.isOver()){
				if(this.y<420){
				this.y+=5;
				}else{
					this.bg.setDown(true);
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
