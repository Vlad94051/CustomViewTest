package ru.tms.customviewtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasViewTest(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var radiusCircle: Float = 20f
    private val diameter: Int get() = radiusCircle.toInt() * 2

    private var center: Pair<Float, Float> = 0f to 0f
    private var circlePaint: Paint = Paint().apply {
        color = resources.getColor(R.color.black)
        strokeWidth = 4f
    }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.CanvasViewTest, 0, 0)
            .apply {
                radiusCircle = getFloat(R.styleable.CanvasViewTest_circleRadiusAttr, 20f)
            }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSpec = MeasureSpec.getMode(widthMeasureSpec)
        val heightSpec = MeasureSpec.getMode(heightMeasureSpec)

        val widthViewSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightViewSize = MeasureSpec.getSize(heightMeasureSpec)

        when {
            // layout_height = someDp and layout_width = someDP
            widthSpec == MeasureSpec.EXACTLY && heightSpec == MeasureSpec.EXACTLY ->
                resolveExactlySize(widthViewSize, heightViewSize, widthSpec, heightSpec)
            // layout_height = wrap_content and layout_width = wrap_content
            widthSpec == MeasureSpec.AT_MOST && heightSpec == MeasureSpec.AT_MOST ->
                setMinimalMeasureDimension()

            else -> setDefaultMeasureDimension(widthMeasureSpec, heightMeasureSpec)
        }
    }

    private fun resolveExactlySize(
        widthViewSize: Int,
        heightViewSize: Int,
        widthSpec: Int,
        heightSpec: Int
    ) {
        if (widthViewSize < diameter || heightViewSize < diameter) {
            setMinimalMeasureDimension()
        } else {
            setDefaultMeasureDimension(widthSpec, heightSpec)
        }
    }

    private fun setDefaultMeasureDimension(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            MeasureSpec.getSize(widthMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec)
        )
    }

    private fun setMinimalMeasureDimension() {
        val viewSizeHeight = radiusCircle * 2
        val viewSizeWidth = radiusCircle * 2
        setMeasuredDimension(viewSizeWidth.toInt(), viewSizeHeight.toInt())
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        center = calculateCircleCenter()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(center.second, center.first, radiusCircle, circlePaint)
    }

    private fun calculateCircleCenter(): Pair<Float, Float> {
        return y + height / 2f to x + width / 2f
    }
}