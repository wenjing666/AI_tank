package MoveAndFire;

import cmd.Action;
import map.Player;

public class Move {
    private Player player;
    private int[][] Map;
    private String moveaction;
    private int width;
    private int height;
    private int pp=0;
    private String[] move={"right","up","left","down"};
    private int x[]={1,0,-1,0};
    private int y[]={0,1,0,-1};
    public Move(Player player,int[][] Map,int width,int height)
    {
        this.player=player;
        this.Map=Map;
        this.width=width;
        this.height=height;
    }
    public String MoveAction()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=1;j<=4;j++)
            {
                if((player.getX()+(x[i]*j))<width-1&&(player.getX()+(x[i]*j))>=0&&(player.getY()+(y[i]*j))<height-1&&(player.getY()+(y[i]*j))>=0&&Map[player.getY()+y[i]][player.getX()+x[i]]==0)
                {
                    pp=1;
                    break;
                }
            }
            if (pp==1){
                moveaction=move[i];
                break;
            }
        }
    return moveaction;
    }

}
