package demo;

import cmd.Action;
import map.Player;

public class Move {
    private int[][] Map;
    private String moveAction="right";
    private String fire="right";
    private Player player;
    int dx[]={1,0,-1,0};
    int dy[]={0,-1,0,1};
    int width,height;
    int bullet=0;
    public Move(int[][] Map,Player player,int width,int height)
    {
        this.Map=Map;
        this.player=player;
        this.width=width;
        this.height=height;
    }
    public Action getMoveAction()
    {
        countMoveAction();
        return new Action(player.getTeam(),player.getId(),bullet,moveAction,fire);
    }
    private void countMoveAction()
    {
        int ok=0;
        for(int i=0;i<4;i++)
        {
            if(ok==1)break;
            if(i==0)
            {
                if(player.getX()+1<width){
                    if(Map[player.getY()][player.getX()+1]==1)
                    {
                        fire="right";
                        moveAction="right";
                        ok=1;
                    }
                    if(ok!=1&&(Map[player.getY()][player.getX()+1]==3||(player.getX()+2<width&&Map[player.getY()][player.getX()+2]==3)||(player.getX()+3<width&&Map[player.getY()][player.getX()+3]==3)))
                    {
                        fire="right";
                        moveAction="down";
                        ok=1;
                    }
                    if(ok!=1&&Map[player.getY()][player.getX()+1]==2)
                    {
                        if(player.getSuperBullet()==1)
                        {
                            bullet=1;
                            fire="right";
                            moveAction="right";
                            ok=1;
                        }
                    }

                    if(ok!=1&&(Map[player.getY()][player.getX()+1]==0||Map[player.getY()][player.getX()+1]==4||Map[player.getY()][player.getX()+1]==8))
                    {
                        fire="right";
                        moveAction="right";
                        ok=1;
                    }
                }
            }
            if(i==1)
            {
                if(player.getY()+1<height){
                    if(Map[player.getY()+1][player.getX()]==1)
                    {
                        fire="down";
                        moveAction="down";
                        ok=1;
                    }
                    if(ok!=1&&(Map[player.getY()+1][player.getX()]==3||(player.getY()+2<height&&Map[player.getY()+2][player.getX()]==3)||(player.getY()+3<height&&Map[player.getY()+3][player.getX()]==3)))
                    {
                        fire="down";
                        moveAction="right";
                        ok=1;
                    }
                    if(ok!=1&&Map[player.getY()+1][player.getX()]==2)
                    {
                        if(player.getSuperBullet()==1)
                        {
                            bullet=1;
                            fire="down";
                            moveAction="down";
                            ok=1;
                        }
                    }

                    if(ok!=1&&(Map[player.getY()+1][player.getX()]==0||Map[player.getY()+1][player.getX()]==4||Map[player.getY()+1][player.getX()]==8))
                    {
                        fire="down";
                        moveAction="down";
                        ok=1;
                    }
                }
            }
            if(i==2)
            {
                if(player.getX()-1>=0){
                    if(Map[player.getY()][player.getX()-1]==1)
                    {
                        fire="left";
                        moveAction="left";
                        ok=1;
                    }
                    if(ok!=1&&(Map[player.getY()][player.getX()-1]==3||(player.getX()-2>=0&&Map[player.getY()][player.getX()-2]==3)||(player.getX()-3>=0&&Map[player.getY()][player.getX()-3]==3)))
                    {
                        fire="left";
                        moveAction="right";
                        ok=1;
                    }
                    if(ok!=1&&Map[player.getY()][player.getX()-1]==2)
                    {
                        if(player.getSuperBullet()==1)
                        {
                            bullet=1;
                            fire="left";
                            moveAction="left";
                            ok=1;
                        }
                    }

                    if(ok!=1&&(Map[player.getY()][player.getX()-1]==0||Map[player.getY()][player.getX()-1]==4||Map[player.getY()][player.getX()-1]==8))
                    {
                        fire="left";
                        moveAction="left";
                        ok=1;
                    }
                }
            }
            if(i==3)
            {
                if(player.getY()-1>=0){
                    if(Map[player.getY()-1][player.getX()]==1)
                    {
                        fire="up";
                        moveAction="up";
                        ok=1;
                    }
                    if(ok!=1&&(Map[player.getY()-1][player.getX()]==3||(player.getY()-2>=0&&Map[player.getY()-2][player.getX()]==3)||(player.getY()-3>=0&&Map[player.getY()-3][player.getX()]==3)))
                    {
                        fire="up";
                        moveAction="left";
                        ok=1;
                    }
                    if(ok!=1&&Map[player.getY()-1][player.getX()]==2)
                    {
                        if(player.getSuperBullet()==1)
                        {
                            bullet=1;
                            fire="up";
                            moveAction="up";
                            ok=1;
                        }
                    }

                    if(ok!=1&&(Map[player.getY()-1][player.getX()]==0||Map[player.getY()-1][player.getX()]==4||Map[player.getY()-1][player.getX()]==8))
                    {
                        fire="up";
                        moveAction="up";
                        ok=1;
                    }
                }
            }
        }
    }


}
