package mario;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class marioFrame extends JFrame implements KeyListener,Runnable {

	private List<BackGround> allBG=new ArrayList<BackGround>();
	
	private Thread t=new Thread(this);
	
	private boolean isStart=false;
	
	private Mario mario = null;
	
	private BackGround nowBG=null;
	
	public marioFrame(){
		this.setTitle("超级马里奥");
		this.setSize(900,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-100,height/2-100);
		this.addKeyListener(this);
		
		StaticValue.init();
		
		//创建全部场景3个
		for (int i=1;i<=3;i++){
			this.allBG.add(new BackGround(i,i==3?true:false));
		}
		this.nowBG=this.allBG.get(0);

		this.mario=new Mario (0,480);
		//为mario类取得当前场景
		
		
		this.mario.setBg(nowBG);
		t.start();
		this.repaint();
		this.setVisible(true);
	}

	
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		BufferedImage image = new BufferedImage(900,600,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 =image.getGraphics();  //用g2来接受
		
		if(isStart){
			
		
		//绘制背景
		g2.drawImage(this.nowBG.getBgImage(), 0, 0, this);
		g2.drawString("生命："+this.mario.getLife(),700,100);
		
		
		Iterator<Enemy> iterEnemy=this.nowBG.getAllEnemy().iterator();
		while(iterEnemy.hasNext()){
			Enemy e=iterEnemy.next();
			g2.drawImage(e.getShowImage(),e.getX(),e.getY(),this);
		}
		
		//绘制障碍物---迭代
		Iterator<Obstruction> iter = this.nowBG.getAllObstruction().iterator();
		while(iter.hasNext()){
			Obstruction ob=iter.next();
			//System.out.println(1);
			g2.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), this);
		}
		
	
		
		
		g2.drawImage(this.mario.getShowImage(),this.mario.getX(),this.mario.getY(),this);
	
		
		this.nowBG.getAllObstruction();
		}else{
			g2.drawImage(StaticValue.startImage,0,0,this);
		}
		
		g.drawImage(image, 0, 0, this); 
		
	
	}

	


	@Override
	//右箭头39
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		
		if(isStart){
			if (key.getKeyCode()==39){
				this.mario.rightMove();
			//	System.out.println("左键按下");
			}
			if (key.getKeyCode()==37){
				this.mario.leftMove();
			//	System.out.println("右键按下");
			}//空格
			if (key.getKeyCode()==32){
			//	System.out.println("空格 键按下");
				this.mario.jump();
			}
			
		}else{
			if(key.getKeyCode()==32){
				isStart=true;
				this.nowBG.enemyStartMove();
				this.mario.setScore(0);
				this.mario.setLife(3);
			}
		}
	}



	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
		if(isStart){
			if (key.getKeyCode()==39){
				this.mario.rightStop();
		
			}
			if (key.getKeyCode()==37){
				this.mario.leftStop();
			}
		}
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			this.repaint();
			try {
				Thread.sleep(20);
				if(this.mario.getX()>=840){
					this.nowBG=this.allBG.get(this.nowBG.getSort());
					this.mario.setBg(this.nowBG);
					
					this.nowBG.enemyStartMove();
					
					this.mario.setX(0);
					
					
					}	
				if(this.mario.isDead()){
					
					JOptionPane.showMessageDialog(this,"你死了！傻逼");
					System.exit(0);
				}
				if(this.mario.isClear()){
					JOptionPane.showMessageDialog(this,"恭喜恭喜，游戏通关！请期待更新！");
					System.exit(0);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
