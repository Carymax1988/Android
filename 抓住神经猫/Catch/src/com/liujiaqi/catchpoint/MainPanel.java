package com.liujiaqi.catchpoint;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.AvoidXfermode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.AlteredCharSequence;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class MainPanel extends SurfaceView implements OnTouchListener{
	
	private static final int ROW = 10;
	private static final int COL = 10;
	private static final int BLOCK = 10;
	private static int COL_SIZE = 40;
	private Dot[][] dots;
	private Dot cat;
	boolean isend = false;
	private int path = 0;
	
	public static final String DATA = "com.liujiaqi.catch";
	
	public static final String BESTSCORE = "bestscore";
	
	SharedPreferences sp;
	
	int bestscore = 99;
	
	Editor editor;
	
	public MainPanel(Context context) {
		super(context);
		init(context);
	}
	
	
	
	public MainPanel(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}



	public MainPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		sp = context.getSharedPreferences(DATA, Context.MODE_PRIVATE);
		editor = sp.edit();
		bestscore = sp.getInt(BESTSCORE, bestscore);
		getHolder().addCallback(callback);
		dots = new Dot[ROW][COL];
		initData();
		setOnTouchListener(this);
		initGame();
	}

	public void redraw(){
		Canvas c = getHolder().lockCanvas();
		c.drawColor(Color.LTGRAY);
		Paint pen = new Paint();
		pen.setFlags(Paint.ANTI_ALIAS_FLAG);
		for(int i=0;i<ROW;i++){
			int offset = 0;
			int col_offset = COL_SIZE/4;
			if(i%2 != 0){
				offset = COL_SIZE/2; 
			}
			for(int j=0;j<COL;j++){
				Dot d = getDot(j, i);
				switch (d.getStatus()) {
				case Dot.STATUS_OFF:
					pen.setColor(0xFFEEEEEE);
					break;
				case Dot.STATUS_ON:
					pen.setColor(0xFFFFAA00);
					break;
				case Dot.STATUS_IN:
					pen.setColor(0xFFFF0000);
					break;
				default:
					break;
				}
				c.drawOval(new RectF(d.getX()*COL_SIZE+offset,
						d.getY()*COL_SIZE, 
						(d.getX()+1)*COL_SIZE+offset, 
						(d.getY()+1)*COL_SIZE), 
						pen);
			}
		}
		getHolder().unlockCanvasAndPost(c);
	}
	
	private void initData() {
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				Dot dot = new Dot(j,i);
				dots[i][j] = dot;
			}
		}
	}
	
	public void initGame() {
		isend = false;
		path = 0;
		for(int i=0;i<ROW;i++){
			for(int j=0;j<COL;j++){
				dots[i][j].setStatus(Dot.STATUS_OFF);
			}
		}
		cat = getDot(4, 5);
		cat.setStatus(Dot.STATUS_IN);
		for(int i=0;i<BLOCK;){
			int x = (int)(Math.random()*1000)%COL;
			int y = (int)(Math.random()*1000)%ROW;
			if(getDot(x, y).getStatus() == Dot.STATUS_OFF){
				getDot(x, y).setStatus(Dot.STATUS_ON);
				i++;
			}
		}
	}
	
	private Dot getDot(int x,int y){
		return dots[y][x];
	}
	
	Callback callback = new Callback() {
		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
		}
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			redraw();
		}
		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			COL_SIZE = width/(COL+1);
			redraw();
		}
	};

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			int x,y;
			y = (int) (event.getY()/COL_SIZE);
			if(y%2 == 0){
				x = (int) ((event.getX())/COL_SIZE);
			}else{
				x = (int) ((event.getX()-COL_SIZE/2)/COL_SIZE);
			}
			if(x+1>COL || y+1>ROW){
//				initGame();
			}else{
				if(isend)
					return true;
				Dot d = getDot(x, y);
				if(d.getStatus() == Dot.STATUS_OFF){
					d.setStatus(Dot.STATUS_ON);
					path++;
					move();
				}
			}
			redraw();
			break;
		default:
			break;
		}
		return true;
	}
	
	private boolean isAtEdge(Dot d) {
		if(d.getX()*d.getY() == 0 
		|| (d.getX()+1) == COL 
		|| (d.getY()+1) == ROW){
			return true;
		}
		return false;
	}
	
	private Dot getNieghbor(Dot d,int index) {
		
		switch (index) {
			case 1:
				return getDot(d.getX()-1, d.getY());
			case 2:
				if(d.getY()%2 == 0){
					return getDot(d.getX()-1,d.getY()-1);
				}else{
					return getDot(d.getX(),d.getY()-1);
				}
			case 3:
				if(d.getY()%2 == 0){
					return getDot(d.getX(),d.getY()-1);
				}else{
					return getDot(d.getX()+1,d.getY()-1);
				}
			case 4:
				return getDot(d.getX()+1, d.getY());
			case 5:
				if(d.getY()%2 == 0){
					return getDot(d.getX(),d.getY()+1);
				}else{
					return getDot(d.getX()+1,d.getY()+1);
				}
			case 6:
				if(d.getY()%2 == 0){
					return getDot(d.getX()-1,d.getY()+1);
				}else{
					return getDot(d.getX(),d.getY()+1);
				}
			default:
				break;
		}
		
		return null;
	}
	
	private int getDistance(Dot d,int index) {
		int distance = 0;
		if(isAtEdge(d)){
			return distance;
		}
		Dot ore = d,next;
		while(true){
			next = getNieghbor(ore, index);
			if(next.getStatus() == Dot.STATUS_ON){
				if(distance == 0)
					return -1;
				return (distance+1)*-1;
			}
			if(isAtEdge(next)){
				distance++;
				return distance;
			}
			distance++;
			ore = next;
		}
	}
	
	private void MoveTo(Dot d) {
		d.setStatus(Dot.STATUS_IN);
		getDot(cat.getX(), cat.getY()).setStatus(Dot.STATUS_OFF);
		cat = d;
	}
	private void move() {
		if(isAtEdge(cat)){
			lose();
			return;
		}
		Vector<Dot> avaliable = new Vector<Dot>();
		Vector<Dot> positive = new Vector<Dot>();
		HashMap<Dot, Integer> al = new HashMap<Dot, Integer>();
		for(int i=1;i<=6;i++){
			Dot n = getNieghbor(cat, i);
			if(n.getStatus() == Dot.STATUS_OFF){
				avaliable.add(n);
				al.put(n, i);
				if(getDistance(n, i)>=0){
					positive.add(n);
				}
			}
		}
		if(avaliable.size() == 0){
			win();
		}else if(avaliable.size() == 1){
			MoveTo(avaliable.get(0));
		}else{
			Dot best = null;
			if(positive.size() != 0){
				int min = 999;
				for(int i = 0;i<positive.size();i++){
					int a = getDistance(positive.get(i), al.get(positive.get(i)));
					System.out.println("i = "+i+"   Distance == "+a);
					if(a < min){
						min = a;
						best = positive.get(i);
					}
				}
			}else{
				int max = 0;
				for(int i=0;i<avaliable.size();i++){
					int k = getDistance(avaliable.get(i), al.get(avaliable.get(i)));
					if(k<=max){
						max = k;
						best = avaliable.get(i);
					}
				}
			}
			MoveTo(best);
		}
	}
	private void lose() {
		isend = true;
		Dialog d = new AlertDialog.Builder(getContext())
		.setTitle("你失败了")
		.setMessage("You loss")
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton("确定", null)
		.create();
		d.setCancelable(false);
		d.show();
	}
	private void win() {
		isend = true;
		String message = "You win: ("+path+") step";
		Dialog d = new AlertDialog.Builder(getContext())
		.setTitle("你赢了")
		.setMessage(message)
		.setIcon(R.drawable.ic_launcher)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if(path<bestscore){
					editor.putInt(BESTSCORE, path);
					editor.commit();
					listener.OnReStart(true);
				}else{
					listener.OnReStart(false);
				}
			}
		})
		.create();
		d.setCancelable(false);
		d.show();
	}
	
	public void setRestartListener(restartListener listener){
		this.listener = listener;
	}
	
	restartListener listener;
	
	public interface restartListener{
		public void OnReStart(boolean isRefresh);
	}
}
