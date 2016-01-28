package mario;

import java.awt.image.BufferedImage;

public class Enemy implements Runnable {
	
	private int x;
	private int y;
	
	private int startX;
	private int startY;
	
	private int type;
	
	private int imageType;
	
	private BufferedImage showImage;
	
	private boolean isLeftOrUp =true;
	private boolean startIsLeftOrUp;
	
	private int upMax=0;
	private int downMax=0;
	
	private BackGround bg;
	
	Thread t=new Thread(this);
	
		public Enemy(int x, int y,boolean isLeft, int type, BackGround bg){
			this.x=x;
			this.y=y;
			this.startX=x;
			this.startY=y;
			this.startIsLeftOrUp=isLeft;
			this.isLeftOrUp=isLeft;
			this.type=type;
			this.bg=bg;
			if (type==1){
				this.showImage=StaticValue.allTriangleImage.get(0);
			}
			t.start();
			t.suspend();
		} 
		
		public void startMove(){
			t.resume();
		}
	
		
		public Enemy(int x, int y,boolean isUp, int type, int upMax, int downMax, BackGround bg){
			this.x=x;
			this.y=y;
			this.startX=x;
			this.startY=y;
			this.bg=bg;
			this.isLeftOrUp=isUp;
			this.startIsLeftOrUp=isUp;
			this.type=type;
			this.upMax=upMax;
			this.downMax=downMax;
			if (type==2){
				this.showImage=StaticValue.allFlowerImage.get(0);
			}
			t.start();
		} 

	
	
	public int getType() {
			return type;
		}


	public void reset(){
		this.x=this.startX;
		this.y=this.startY;
		this.isLeftOrUp=this.startIsLeftOrUp;
		if(this.type==1){
		this.showImage=StaticValue.allTriangleImage.get(0);
		}else if (this.type==3){
		this.showImage=StaticValue.allFlowerImage.get(0);	
		}
	}
	
	public void death(){
		this.showImage=StaticValue.allTriangleImage.get(2);
		this.bg.getAllEnemy().remove(this);
		this.bg.getRemovedEnemy().add(this);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			boolean canLeft=true;
			boolean canRight=true;
		if (type==1){
			if (this.isLeftOrUp){
				this.x-=2;
			}else{
				this.x+=2;
			}
			if (this.imageType==0){
				imageType=1;
			}else{
				imageType=0;
			}
			
			for(int i=0;i<this.bg.getAllObstruction().size();i++){
				Obstruction ob =this.bg.getAllObstruction().get(i);
				//马里奥与障碍物接触
				if (ob.getX()==this.x+60 && ob.getY()+50>this.y && ob.getY()-50<this.y){
					canRight=false; 
				}
				if (ob.getX()==this.x-60 && ob.getY()+50>this.y && ob.getY()-50<this.y){
					canLeft=false;
				}
			}
			if(this.isLeftOrUp&&!canLeft||this.x==0){
				this.isLeftOrUp=false;
			}else if(!this.isLeftOrUp&&!canRight||this.x==840){
				this.isLeftOrUp=true;
			}
				
			
				
			this.showImage=StaticValue.allTriangleImage.get(imageType);
		}
		if (type==2){
			if (this.isLeftOrUp){
				this.y-=2;
			}else{
				this.y+=2;
			}
			if (this.imageType==0){
				imageType=1;
			}else{
				imageType=0;
			}
			this.showImage=StaticValue.allFlowerImage.get(imageType);
			//System.out.println(this.upMax);
			//System.out.println(this.downMax);
			
			if(this.isLeftOrUp&&this.y==this.upMax){
				this.isLeftOrUp=false;
			}
			if(!this.isLeftOrUp&&this.y==this.downMax){
				this.isLeftOrUp=true;
			}
		}
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		

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

}
