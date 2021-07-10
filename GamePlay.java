import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.ImageIcon;
public class GamePlay extends JPanel implements KeyListener,ActionListener
{
	int i;
	private int[] sxlen=new int[750];              //snake x length
	private int[] sylen=new int[750];           //snake y length

	private boolean right=false;
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;

	private ImageIcon rm;
	private ImageIcon lm;
	private ImageIcon um;
	private ImageIcon dm;
	private ImageIcon si;

	private int[] ex={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] ey={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	private ImageIcon p;

	private Random random=new Random();
	private int xp=random.nextInt(34);
	private int yp=random.nextInt(23);

	private Timer timer;
	private int delay=100;

	private int lengthofsnake=3;
	private int  moves=0;

	private int score=0;

	private ImageIcon t;
	public GamePlay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		timer=new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g)
	{
		if(moves==0)
		{
			sxlen[0]=100;
			sxlen[1]=75;
			sxlen[2]=50;

			sylen[0]=100;
			sylen[1]=100;
			sylen[2]=100;
		}
		g.setColor(Color.white);
		g.drawRect(24,10,851,55);

		t=new ImageIcon("image/t.jpg");
		t.paintIcon(this,g,25,11);
		g.setColor(Color.white);
		g.drawRect(24,74,851,577);
		g.setColor(Color.black);
		g.fillRect(25,75,850,575);

		//scores..............
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("scores : "+score,780,30);

		//length............
		g.drawString("length : "+lengthofsnake,780,50);

		rm=new ImageIcon("image/rm.png");
		rm.paintIcon(this,g,sxlen[0],sylen[0]);
		for(i=0;i<lengthofsnake;i++)
		{
			if(i==0 && right)
			{
				rm=new ImageIcon("image/rm.png");
				rm.paintIcon(this,g,sxlen[i],sylen[i]);
			}

			if(i==0 && left)
			{
				lm=new ImageIcon("image/lm.png");
				lm.paintIcon(this,g,sxlen[i],sylen[i]);
			}

			if(i==0 && up)
			{
				um=new ImageIcon("image/um.png");
				um.paintIcon(this,g,sxlen[i],sylen[i]);
			}

			if(i==0 && down)
			{
				dm=new ImageIcon("image/dm.png");
				dm.paintIcon(this,g,sxlen[i],sylen[i]);
			}
			if(i!=0)
			{
				si=new ImageIcon("image/si.png");
				si.paintIcon(this,g,sxlen[i],sylen[i]);
			}
		}
		p=new ImageIcon("image/p.png");
		p.paintIcon(this,g,ex[xp],ey[yp]);

		if(ex[xp]==sxlen[0] && ey[yp]==sylen[0])
		{
			lengthofsnake++;
			score++;
			xp=random.nextInt(34);
			yp=random.nextInt(23);
		}
		p.paintIcon(this,g,ex[xp],ey[yp]);
		for(int n=1;n<lengthofsnake;n++)
		{
			if(sxlen[n]==sxlen[0] && sylen[n]==sylen[0])
			{
				right=false;
				left=false;
				up=false;
				down=false;

				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("Game over",300,300);

				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("space to restart",350,340);

			}
		}
		g.dispose();
	}
	public void actionPerformed(ActionEvent e )
	{
		if(right)
		{
			for(i=lengthofsnake-1;i>=0;i--)
			{
				sylen[i+1]=sylen[i];
			}
			for(i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					sxlen[i]=sxlen[i]+25;
				}
				else
				{
					sxlen[i]=sxlen[i-1];
				}
				if(sxlen[i]>851)
				{
					sxlen[i]=25;
				}

			}
			repaint();
		}

		if(left)
		{
			for(i=lengthofsnake-1;i>=0;i--)
			{
				sylen[i+1]=sylen[i];
			}
			for(i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					sxlen[i]=sxlen[i]-25;
				}
				else
				{
					sxlen[i]=sxlen[i-1];
				}
				if(sxlen[i]<25)
				{
					sxlen[i]=851;
				}

			}
			repaint();
		}

		if(up)
		{
			for(i=lengthofsnake-1;i>=0;i--)
			{
				sxlen[i+1]=sxlen[i];
			}
			for(i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					sylen[i]=sylen[i]-25;
				}
				else
				{
					sylen[i]=sylen[i-1];
				}
				if(sylen[i]<75)
				{
					sylen[i]=625;
				}
			}
			repaint();
		}

		if(down)
		{
			for(i=lengthofsnake-1;i>=0;i--)
			{
				sxlen[i+1]=sxlen[i];
			}
			for(i=lengthofsnake-1;i>=0;i--)
			{
				if(i==0)
				{
					sylen[i]=sylen[i]+25;
				}
				else
				{
					sylen[i]=sylen[i-1];
				}
				if(sylen[i]>625)
				{
					sylen[i]=75;
				}

			}
			repaint();
		}



	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moves++;
			if(!left)
			{
				right=true;
			}
			else
			{
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves++;
			if(!right)
			{
				left=true;
			}
			else
			{
				left=false;
				right=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves++;
			if(!down)
			{
				up=true;
			}
			else
			{
				up=false;
				down=true;
			}
			right=false;
			left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves++;
			if(!up)
			{
				down=true;
			}
			else
			{
				down=false;
				up=true;
			}
			right=false;
			left=false;
		}

		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			score=0;
			moves=0;
			lengthofsnake=3;
			repaint();
		}
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
}
