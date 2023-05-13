package com.week2.minesweeper2.views.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.week2.minesweeper2.GameEngine;
import com.week2.minesweeper2.R;


import androidx.core.content.ContextCompat;

public class Cell extends BaseCell implements View.OnClickListener, View.OnLongClickListener{

    int BOMB_NUMBER;
    public Cell(Context context, int x,int y) {

        super(context);


        setPosition(x,y);

        setOnClickListener(this);
        setOnLongClickListener(this);

        //Log.i("TAG,", "cell"+x,y);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //redraws the grid
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        //onClick, event of clicking
        //when click a button, send info to the game engine.
        GameEngine.getInstance().click(getXPos(), getYPos());
    }
    @Override
    public boolean onLongClick(View v) {
        //onLongClick, the event of clicking the button 1 sec or longer.
        GameEngine.getInstance().flag(getXPos(),getYPos());


        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawButton(canvas);
         //below here, the code is checking it if its flagged, then draw flag. If not flagged and not clicked and we click somewhere else except the bomb the it shows the counter.
        //if we clicked and its a bomb, then draw a explosion of bomb. if its not put a number.
        if(isFlagged()){
            drawFlag(canvas);
            
        } else if (isRevealed()&&isBomb()&&!isClicked()) {
            drawNormalBomb(canvas);
            
        }else {
            if (isClicked()){
                if(getValue()==-1){
                    drawBombExploded(canvas);
                }else{
                    drawNumber(canvas);
                }
            }else {
                drawButton(canvas);
            }
        }

    }

    private void drawFlag(Canvas canvas){
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.flag);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawButton(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.button);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }

    private void drawNormalBomb(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_normal);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }
    private void drawBombExploded(Canvas canvas) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.bomb_exploded);
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }


    private void drawNumber(Canvas canvas) {
        Drawable drawable = null;
        switch (getValue()) {
            case 0:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_0);
                break;
            case 1:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_1);

                break;
            case 2:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_2);

                break;
            case 3:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_3);
                break;
            case 4:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_4);
                break;
            case 5:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_5);
                break;
            case 6:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_6);
                break;
            case 7:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_7);
                break;
            case 8:
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.number_8);
                break;


        }
        drawable.setBounds(0, 0, getWidth(), getHeight());
        drawable.draw(canvas);
    }



}

