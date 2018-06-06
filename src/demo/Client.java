package demo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import map.*;

import java.util.ArrayList;
import java.util.List;

import demo.Team;
import cmd.Action;
import cmd.RoundAction;

//1是砖墙 2是钢墙 3是子弹 4是分包 6敌方坦克位置 7是河 8是超级道具位置
public class Client {
	public   int width;
	public  int height;
	private int team_id = 0;
	private String team_name = "";
	private Team self = null;
	private Team enemy = null;
	private int roundId = 0;
	private int[][] Map;
	private List<Player> players = new ArrayList<Player>();

	public Client(int team_id, String team_name) {
		this.team_id = team_id;
		this.team_name = team_name;

	}

	public void legStart(JSONObject data) {
		System.out.println("leg start");

		JSONObject map = data.getJSONObject("map");

		 width = map.getInt("width");
		height = map.getInt("height");
		Map=new int[height][width];
		System.out.printf("map width:%d, map height %d\n", width, height);

		JSONArray teams = data.getJSONArray("teams");

		for (int i = 0; i < 2; i++) {
			JSONObject team = teams.getJSONObject(i);
			int team_id = team.getInt("id");
			if (this.team_id == team_id) {
				System.out.println("self team");
				this.self = new Team(team);
			} else {
				System.out.println("enemy team");
				this.enemy = new Team(team);
			}
		}
	}

	public void legEnd(JSONObject data) {
		System.out.println("leg end");

		JSONArray results = data.getJSONArray("teams");
		for (int i = 0; i < results.size(); i++) {
			Result result = new Result(results.getJSONObject(i));
		}
	}

	public void round(JSONObject data) {
		this.roundId = data.getInt("round_id");
		System.out.printf("round %d\n", this.roundId);

		JSONArray bullets = data.getJSONArray("bullets");
		for (int i = 0; i < bullets.size(); i++) {
			JSONObject object = bullets.getJSONObject(i);
			Bullet bullet = new Bullet(object);
			Map[bullet.getY()][bullet.getX()]=3;
		}

		JSONArray brickWalls = data.getJSONArray("brick_walls");
		for (int i = 0; i < brickWalls.size(); i++) {
			JSONObject object = brickWalls.getJSONObject(i);
			BrickWall wall = new BrickWall(object);
			Map[wall.getY()][wall.getX()]=1;
		}

		JSONArray ironWalls = data.getJSONArray("iron_walls");
		for (int i = 0; i < ironWalls.size(); i++) {
			JSONObject object = ironWalls.getJSONObject(i);
			IronWall wall = new IronWall(object);
			Map[wall.getY()][wall.getX()]=2;
		}
		JSONArray rivers = data.getJSONArray("river");
		for (int i = 0; i < rivers.size(); i++) {
			JSONObject object = rivers.getJSONObject(i);
			River river = new River(object);
			Map[river.getY()][river.getX()]=7;
		}
		JSONArray coins = data.getJSONArray("coins");
		for (int i = 0; i < coins.size(); i++) {
			JSONObject object = coins.getJSONObject(i);
			Coin coin = new Coin(object);
			Map[coin.getY()][coin.getX()]=4;
		}
		JSONArray stars = data.getJSONArray("stars");
		for (int i = 0; i < stars.size(); i++) {
			JSONObject object = stars.getJSONObject(i);
			Star star = new Star(object);
			Map[star.getY()][star.getX()]=8;
		}

		this.players.clear();
		JSONArray players = data.getJSONArray("players");
		for (int i = 0; i < players.size(); i++) {
			JSONObject object = players.getJSONObject(i);
			Player player = new Player(object);

			if (player.getTeam() == this.team_id) {
				this.players.add(player);
			}
			else {
				Map[player.getY()][player.getX()]=6;
			}
		}

		JSONArray scores = data.getJSONArray("teams");
		for (int i = 0; i < scores.size(); i++) {
			JSONObject object = scores.getJSONObject(i);
			Score Score = new Score(object);
		}
	}

	public RoundAction act() {
		List<Action> actions = new ArrayList<Action>();
		for(Player player : this.players)
		{
			actions.add(new Action(player.getTeam(), player.getId(), 0, dfs(player.getY(),player.getX(),player),"down"));
		}
		
		RoundAction roundAction = new RoundAction(this.roundId, actions);
		return roundAction;
	}

	public String dfs(int x,int y,Player player){
		int []h = new int[]{0,1,0,-1};
		int []z = new int[]{1,0,-1,0};
		System.out.println(player.getX()+player.getY());
		String []move = new String[]{"right","down","left","up"};
		int j = 0;
		for(int i =0;i<4;i++){
		if(Map[player.getY()+h[i]][player.getX()] != 2){
			j = i;
			break;
		}
		if(Map[player.getY()+h[i]][player.getX()] == 2){
			j = i+1;
			System.out.println(x+" "+y);
			break;
		}
		}
		System.out.println(move[j]);
		return move[j];
	}

}
