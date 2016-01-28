package mario;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
	private BufferedImage bgImage=null;
	//场景顺序
	private int sort;
	
	private boolean isOver=false;
	//旗子是否降下来
	private boolean isDown=false;

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	//当前是否为最后一个场景
	private boolean flag;
	public boolean isFlag() {
		return flag;
	}

	//通过集合来保存
	//全部地扔
	private List<Enemy> allEnemy = new ArrayList<Enemy>();
	public List<Enemy> getAllEnemy() {
		return allEnemy;
	}

	public List<Enemy> getRemovedEnemy() {
		return removedEnemy;
	}

	//全部障碍物
	private List<Obstruction> allObstruction =new ArrayList<Obstruction>();
	//全部被消灭的障碍物
	private List<Obstruction> removedObstruction = new ArrayList<Obstruction>();
	
	public List<Obstruction> getRemovedObstruction() {
		return removedObstruction;
	}

	private List<Enemy> removedEnemy = new ArrayList<Enemy>();
	
	//构造方法
	public BackGround (int sort, boolean flag){
		this.sort=sort;
		this.flag=flag;
		if (flag){
			bgImage=StaticValue.endImage;
		}
		else{
			bgImage=StaticValue.bgImage;
		}
		if (sort==1){
			for (int i=0;i<15;i++){
				 this.allObstruction.add(new Obstruction(60*i,540,9,this));
			}
			
			
			
			
			
		this.allObstruction.add(new Obstruction(120,360,4,this));
		this.allObstruction.add(new Obstruction(300,360,0,this));
		this.allObstruction.add(new Obstruction(360,360,4,this));
		this.allObstruction.add(new Obstruction(420,360,0,this));
		this.allObstruction.add(new Obstruction(420,180,4,this));
		this.allObstruction.add(new Obstruction(480,360,4,this));
		this.allObstruction.add(new Obstruction(540,360,0,this));
		
		this.allObstruction.add(new Obstruction(720,540,5,this));
		this.allObstruction.add(new Obstruction(660,540,6,this));
		this.allObstruction.add(new Obstruction(720,480,7,this));
		this.allObstruction.add(new Obstruction(660,480,8,this));
		
		this.allObstruction.add(new Obstruction(660,300,3,this));
		
		this.allEnemy.add(new Enemy(600,480,true,1,this));
		this.allEnemy.add(new Enemy(690,540,true,2,420,540,this));
		
		
		
		
		}
		if(sort==2){
			for (int i=0;i<15;i++){
				if(i!=10){
				 this.allObstruction.add(new Obstruction(60*i,540,9,this));
				}
			}
			//水管
			this.allObstruction.add(new Obstruction(60,540,6,this));
			this.allObstruction.add(new Obstruction(120,540,5,this));
			this.allObstruction.add(new Obstruction(60,480,6,this));
			this.allObstruction.add(new Obstruction(120,480,5,this));
			this.allObstruction.add(new Obstruction(60,420,8,this));
			this.allObstruction.add(new Obstruction(120,420,7,this));
			
			this.allObstruction.add(new Obstruction(240,540,6,this));
			this.allObstruction.add(new Obstruction(300,540,5,this));
			this.allObstruction.add(new Obstruction(240,480,6,this));
			this.allObstruction.add(new Obstruction(300,480,5,this));
			this.allObstruction.add(new Obstruction(240,420,6,this));
			this.allObstruction.add(new Obstruction(300,420,5,this));
			this.allObstruction.add(new Obstruction(240,360,8,this));
			this.allObstruction.add(new Obstruction(300,360,7,this));
			
		}
		if(sort==3){
			for (int i=0;i<15;i++){
				 this.allObstruction.add(new Obstruction(60*i,540,9,this));
			}
			this.allObstruction.add(new Obstruction(550,170,11,this));
			this.allObstruction.add(new Obstruction(520,480,2,this));
		}
		
	}
	
	public void enemyStartMove(){
		for(int i=0;i<this.allEnemy.size();i++){
			System.out.println(this.allEnemy.size());
			this.allEnemy.get(i).startMove();
		}
	}
	
	public List<Obstruction> getAllObstruction() {
		return allObstruction;
	}
	public BufferedImage getBgImage() {
		return bgImage;
	}

	//重置方法：将所有的障碍物和敌人返回到原有坐标，并将状态也改回去
	public void reset(){
		
		//将已经移除的障碍物放回到全部的内容中
		this.allEnemy.addAll(this.removedEnemy);
		this.allObstruction.addAll(this.removedObstruction);
		for(int i=0;i<this.allEnemy.size();i++){
			this.allEnemy.get(i).reset();
		}
		for(int i=0;i<this.allObstruction.size();i++){
			this.allObstruction.get(i).reset();
		}
		
	}
	public int getSort() {
		return sort;
	}
}
