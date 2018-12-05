package com.demo.holder.tview.tablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author Created by yangjian-ds3 on 2018/4/10.
 */
public class TabStripLayout extends LinearLayout {

	private Paint mPaint = null;
	private RectF mRectF = null;

	private int mMinSize = 0;

	public TabStripLayout(Context context) {
		this(context, null);
	}

	public TabStripLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TabStripLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setOrientation(LinearLayout.HORIZONTAL);
		setWillNotDraw(false);
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		setLayoutParams(params);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = 0;
		for(int i=0;i<getChildCount();i++){
			width += getChildAt(i).getMeasuredWidth();
		}
		if(width < mMinSize){
			setMeasuredDimension(mMinSize,heightMeasureSpec);
		}
	}

	public void setMinSize(int minSize){
		mMinSize = minSize;
		requestLayout();
	}
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		int width = 0;
		for(int i=0;i<getChildCount();i++){
			width += getChildAt(i).getMeasuredWidth();
		}
		int maxWidth = mMinSize;
		if(width < maxWidth){
			int childWidth = 0;
			int everyWidth = (int)((float)maxWidth / getChildCount());
			for(int i=0;i<getChildCount();i++){


				getChildAt(i).layout(childWidth + (everyWidth - getChildAt(i).getMeasuredWidth())/2,0,
						childWidth + (everyWidth + getChildAt(i).getMeasuredWidth())/2,
						getChildAt(i).getMeasuredHeight());
				childWidth += everyWidth;
			}
		}
	}

	/**
	 * Set the paint.
	 * 
	 * @param paint
	 */
	public void setPaint(Paint paint) {
		mPaint = paint;
	}

	public Paint getLinePaint(){

		return mPaint;
	}

	/**
	 * Set the RectF.
	 * 
	 * @param rectF
	 */
	public void setRectF(RectF rectF) {
		mRectF = rectF;
		postInvalidate();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mPaint != null && mRectF != null) {
			drawStrip(canvas, mPaint, mRectF);
		}
	}

	/**
	 * Draw strip.
	 * 
	 * @param canvas
	 * @param paint
	 * @param rectF
	 */
	protected void drawStrip(Canvas canvas, Paint paint, RectF rectF) {
		canvas.drawRect(rectF, paint);
	}

	/**
	 * Update strip position.
	 * 
	 * @param left
	 */
	public void updateHorizontalStrip(float left,float width) {
		if (mRectF != null) {
			mRectF.left = left;
			mRectF.right = mRectF.left + width;
			postInvalidateOnAnimation();
		}
	}

	public void updateHorizontalStrip(int position,int tabWidthPrelong) {

		View childView = getChildAt(position);
		if(childView != null){
			float left = childView.getLeft() + tabWidthPrelong / 2;
			float width = childView.getMeasuredWidth() - tabWidthPrelong;
			updateHorizontalStrip(left,width);
		}
	}
}
