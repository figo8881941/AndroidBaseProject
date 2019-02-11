package com.duoduo.commonbase.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.duoduo.commonbase.utils.DataUtils;


/**
 * 带有小icon和数字的ImageView
 * 
 * @author wangzhuobin
 *
 */
public class IconImageView extends ImageView {

	private Drawable icon;
	private String iconText;

	private int iconMarginTop;
	private int iconMarginRight;

	private int textXOffset;
	private int textYOffset;

	private boolean isDigText;
	private float digTextYOffset;
	
	private Paint iconTextPaint = new Paint();
	private Rect iconTextRect = new Rect();
	private float iconTextSize;
	private int iconTextColor = Color.WHITE;
	// 显示的字符超过icon长度，就显示...
	private final String EXCEEDSTRING = "...";
	private boolean mShowIcon;

	public IconImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public IconImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public IconImageView(Context context) {
		super(context);
		init();
	}

	private void init() {
		iconTextPaint.setAntiAlias(true);
		digTextYOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, Resources.getSystem().getDisplayMetrics());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (icon != null || iconText != null) {
			int width = getWidth();
			if (icon != null && mShowIcon) {
				canvas.save();
				canvas.translate(
						width / 2 + iconMarginRight,
						iconMarginTop);
				icon.draw(canvas);
				canvas.restore();
			}
			if (iconText != null && mShowIcon) {
				iconTextPaint.setTextSize(iconTextSize);
				iconTextPaint.setColor(iconTextColor);
				String drawText = iconText;
				boolean isDigit = isDigText;
				iconTextPaint.getTextBounds(drawText, 0, drawText.length(),
						iconTextRect);
				float textHeight = iconTextRect.height();
				float textWidth = iconTextPaint.measureText(drawText);
				float x = width - iconMarginRight - textWidth;
				float y = iconMarginTop + textHeight;
				if (icon != null) {
					Rect rect = icon.getBounds();
					int iconWidth = rect.width();
					int iconHeight = rect.height();
					if (iconWidth <= textWidth) {
						drawText = EXCEEDSTRING;
						isDigit = false;
						iconTextPaint.getTextBounds(drawText, 0,
								drawText.length(), iconTextRect);
						textHeight = iconTextRect.height();
						textWidth = iconTextPaint.measureText(drawText);
					}
					x = width / 2 + iconMarginRight
							+ (iconWidth - textWidth) / 2 + textXOffset;
					y = iconMarginTop + textHeight + (iconHeight - textHeight)
							/ 2 + textYOffset;
				}
				if (!isDigit) {
					y -= digTextYOffset;
				}
				canvas.drawText(drawText, x, y, iconTextPaint);
			}
		}
	}

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
		if (this.icon != null) {
			this.icon.setBounds(0, 0, this.icon.getIntrinsicWidth(),
					this.icon.getIntrinsicHeight());
		}
		invalidate();
	}

	public void setIconSize(int width, int height) {
		if (icon != null) {
			icon.setBounds(0, 0, width, height);
		}
		invalidate();
	}

	public String getIconText() {
		return iconText;
	}

	public void setIconText(String iconText) {
		if (iconText != null && iconText.length() > 3) {
			iconText = iconText.substring(0,2) + EXCEEDSTRING;
		}
		this.iconText = iconText;
		isDigText = DataUtils.isDigitString(this.iconText);
		invalidate();
	}

	public int getIconMarginTop() {
		return iconMarginTop;
	}

	public void setIconMarginTop(int iconMarginTop) {
		this.iconMarginTop = iconMarginTop;
		invalidate();
	}

	public int getIconMarginRight() {
		return iconMarginRight;
	}

	public void setIconMarginRight(int iconMarginRight) {
		this.iconMarginRight = iconMarginRight;
		invalidate();
	}

	public int getTextXOffset() {
		return textXOffset;
	}

	public void setTextXOffset(int textXOffset) {
		this.textXOffset = textXOffset;
		invalidate();
	}

	public int getTextYOffset() {
		return textYOffset;
	}

	public void setTextYOffset(int textYOffset) {
		this.textYOffset = textYOffset;
		invalidate();
	}

	public float getIconTextSize() {
		return iconTextSize;
	}

	public void setIconTextSize(float iconTextSize) {
		this.iconTextSize = iconTextSize;
		invalidate();
	}

	public int getIconTextColor() {
		return iconTextColor;
	}

	public void setIconTextColor(int iconTextColor) {
		this.iconTextColor = iconTextColor;
		invalidate();
	}

	public void hideIcon() {
		mShowIcon = false;
		invalidate();
	}

	public void setShowIcon() {
		mShowIcon = true;
		invalidate();

	}
}
