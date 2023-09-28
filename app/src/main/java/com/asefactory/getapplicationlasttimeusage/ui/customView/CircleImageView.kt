package com.asefactory.getapplicationlasttimeusage.ui.customView

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.toRectF
import com.asefactory.getapplicationlasttimeusage.R

class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : androidx.appcompat.widget.AppCompatImageView(
    context, attrs, defStyleAttr
) {

    @Px
    var borderWidth: Float = dpToPx(context, DEFAULT_BORDER_WIDTH)

    @ColorInt
    var borderColor: Int = DEFAULT_BORDER_COLOR
    var isBorderUsed: Boolean = DEFAULT_IS_BORDER_USED

    val maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val viewRect = Rect()
    lateinit var resultBm : Bitmap
    lateinit var maskBm : Bitmap
    lateinit var srcBm : Bitmap

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderWidth = typedArray.getDimension(
                R.styleable.CircleImageView_cav_borderWidth,
                dpToPx(context, DEFAULT_BORDER_WIDTH)
            )

            borderColor = typedArray.getColor(
                R.styleable.CircleImageView_cav_borderColor,
                DEFAULT_BORDER_COLOR
            )

            isBorderUsed = typedArray.getBoolean(
                R.styleable.CircleImageView_cav_is_border_used,
                DEFAULT_IS_BORDER_USED
            )
        }
        scaleType = ScaleType.CENTER_CROP
        setup()
    }

    private fun setup() {
        with(maskPaint){
            color = Color.RED
            style = Paint.Style.FILL
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d("CircleImageView", "onMeasure: ")
        val initSize = resolveDefaultSize(widthMeasureSpec)
        setMeasuredDimension(initSize, initSize)
    }

    private fun resolveDefaultSize(spec: Int): Int {
        return when (spec) {
            MeasureSpec.UNSPECIFIED -> dpToPx(context, DEFAULT_SIZE).toInt()
            else -> MeasureSpec.getSize(spec)
        }
    }

    private fun dpToPx(context: Context, dp: Int): Float {
        return dp.toFloat() * context.resources.displayMetrics.density
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d("CircleImageView", "onSizeChanged: ")
        if (w == 0) return
        with(viewRect){
            left = 0
            top=0
            right=w
            bottom=h
        }
        prepareBitmaps(w, h)
    }

    private fun prepareBitmaps(w: Int, h: Int) {
        maskBm = Bitmap.createBitmap(w, h, Bitmap.Config.ALPHA_8)

        val maskCanvas = Canvas(maskBm)
        maskCanvas.drawOval(viewRect.toRectF(), maskPaint)
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)



    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("CircleImageView", "onDraw: ")
        drawable?.let {
            srcBm = drawable.toBitmap(viewRect.width(), viewRect.width(),Bitmap.Config.ARGB_8888)
            canvas.drawBitmap(maskBm, viewRect, viewRect, null)
            canvas.drawBitmap(srcBm, viewRect, viewRect, maskPaint)
        }

    }

    companion object {
        private const val DEFAULT_BORDER_COLOR = Color.WHITE
        private const val DEFAULT_BORDER_WIDTH = 2
        private const val DEFAULT_SIZE = 120
        private const val DEFAULT_IS_BORDER_USED = false
    }
}