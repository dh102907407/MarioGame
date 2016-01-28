package mario;

import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Mario implements Runnable {
	private int x;
	private int y;
	private int xmove=0;
	private int ymove=0;
	private int moving =0;
	private int upTime=0;
	private boolean isDead=false;
	private boolean isClear=false;
	
	public boolean isClear() {
		return isClear;
	}
	private BackGround bg;
	
	public void setBg(BackGround bg) {
		this.bg = bg;
	}
	private Thread t=null;
	private BufferedImage showImage = null;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}




	public BufferedImage getShowImage() {
		return showImage;
	}
	private String status;
	private int life;
	private int score;
	
	
	
	public Mario(int x,int y){
		this.x=x;
		this.y=y;
		showImage=StaticValue.allMarioImage.get(0);
		this.score=0;
		this.life=3;
		this.status="rightstanding";
		t=new Thread(this);
		t.start();
	}
	
	public void dead(){
		this.life--;
		if (this.life==0){
		this.isDead=true;
		}else{
			this.bg.reset();
		}
		this.x=0;
		this.y=480;
	}
	
	
	public boolean isDead() {
		return isDead;
	}

	public void leftMove(){
		xmove=-5;
		if(this.status.indexOf("jumping")!=-1){
		this.status="leftjumping";
		}else{
		this.status="leftmoving";
		}
	}
	
	public void rightMove(){
		xmove=5;
		if(this.status.indexOf("jumping")!=-1){
			this.status="rightjumping";
			}else{
				this.status="rightmoving";
			}
		
		
	}
	
	public void leftStop(){
		xmove=0;
		if(this.status.indexOf("jumping")!=-1){
			this.status="leftjumping";
			}else{
			this.status="leftstanding";
			}
	}
	
	public void rightStop(){
		xmove=0;
		if(this.status.indexOf("jumping")!=-1){
			this.status="rightjumping";
			}else{
			this.status="rightstanding";
			}
	}
	public void jump(){
		if(this.status.indexOf("jumping")==-1){
			if (this.status.indexOf("left")!=-1){
				this.status="leftjumping";
			}
			else{
				this.status="rightjumping";
			}
			ymove=-5;
			upTime=36;
			//System.out.println(1);
		}
	}
	
	public void down(){	
			if (this.status.indexOf("left")!=-1){
				this.status="leftjumping";
			}
			else{
				this.status="rightjumping";
			}
			ymove=5;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(this.bg.isFlag()&&this.x>=520){
				this.bg.setOver(true);
				if(this.bg.isDown()){
					this.status="rightmoving";
					if(this.x<580){
						x+=5;
					}else{
						if (this.y<480){
							y+=5;
						}
						x+=5;
					}
					if (x>=780){
						this.isClear=true;
					}
				}else{
					if(this.y<420){
						this.y+=5;
					}
					if(this.y>=420){
						this.y=420;
						this.status="rightstanding";
					}
					}
			}else{
			
			
			
			boolean canLeft=true;
			boolean canRight=true;
			boolean onLand=false;
			
			for(int i=0;i<this.bg.getAllObstruction().size();i++){
				Obstruction ob =this.bg.getAllObstruction().get(i);
				//马里奥与障碍物接触
				if (ob.getX()==this.x+60 && ob.getY()+50>this.y && ob.getY()-50<this.y){
					if(ob.getType()!=3){
					canRight=false; }
				}
				if (ob.getX()==this.x-60 && ob.getY()+50>this.y && ob.getY()-50<this.y){
					if(ob.getType()!=3){
						canLeft=false; }
				}
				//是否与地面连接
				if (ob.getX()+60>this.x && ob.getX()-60<this.x && ob.getY()==this.y+60){
					
					if(ob.getType()!=3){
					onLand=true;}
				}
				//是否顶到障碍物
				if(ob.getX()+60>this.x && ob.getX()-50<this.x && ob.getY()==this.y-50){
					//对于砖块的处理
					if (ob.getType()==0){
						//将该砖块从场景中移除
						this.bg.getAllObstruction().remove(ob);
						//将被移除的砖块保存到一个集合中
						this.bg.getRemovedObstruction().add(ob);
					}
					//对于问号的处理
					if (ob.getType()==4||ob.getType()==3&&upTime>0){
						ob.setType(2);
						ob.setImage();
					}
				
					upTime=0;
				}
			}
				//如果在地上并且上升时间为0
				if(onLand && upTime==0){
					
					
					
					if (this.status.indexOf("left")!=-1){ 
						if(xmove!=0){
							this.status="leftmoving";
						}else{
						this.status="leftstanding";
						}
					}
					else{
						if(xmove!=0){
							this.status="rightmoving";
						}
						else{
						this.status="rightstanding";
						}
					}	
					
					
					
				}	
				else{
//					//表示当前状态为上升
//					if (upTime!=0){
//						upTime--;
//					}
//					else{
//						this.down();
//					}	y+=ymove;
					if (upTime!=0){
						upTime--;
					}else{
						this.down();
					}
					y+=ymove;
			}
				
			for(int i=0;i<this.bg.getAllEnemy().size();i++){
				Enemy e=this.bg.getAllEnemy().get(i);
				if (e.getX()+50>this.x&&e.getX()-50<this.x
						&&e.getY()+60>this.y&&e.getY()-60<this.y){
					this.dead();
				}
				if (e.getX()+60>this.x&&e.getX()-60<this.x
						&&e.getY()==this.y+60){
					if (e.getType()==1){
						e.death();
						this.upTime=10;
						this.ymove=-5;
					}else if (e.getType()==2){
							this.dead();
					}
				}
			}	
				
			if(this.y>600){
				this.dead();
			}
			if((canLeft && xmove<0)||(canRight&&xmove>0)){
			x=x+xmove;
			if(x<0){
				x=0;
			}
			}
	
			}
			//面向右，否则面向左
			int temp=0;
			if(this.status.indexOf("left")!=-1){
				temp+=5;
			}
			if(this.status.indexOf("moving")!=-1){
				temp+=this.moving;
				moving++;
				if (moving==4){
					moving=0;
				}
			
			}
			if(this.status.indexOf("jumping")!=-1){
				temp+=4;
			}
			this.showImage=StaticValue.allMarioImage.get(temp); 
			try {
				Thread.sleep(20);//单位毫秒
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
	}
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	
}
