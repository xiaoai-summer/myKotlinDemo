package com.example.myapplication

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mStartBtn: Button? = null
    private var mEndBtn: Button? = null
    private var mCancelBtn: Button? = null
    private var mPauseBtn: Button? = null
    private var mResumeBtn: Button? = null
    private var mReverseBtn: Button? = null
    private var mProgressBar: ProgressBar? = null
    private var mMyLove: ImageView? = null
    private var mMyLoveIdleBasha: ImageView? = null
    private var mMyLoveIdle: ImageView? = null
    private var mProgressAnimator: ValueAnimator? = null
    private var mColorAnimator: ValueAnimator? = null
    private var mAlphaAnimator: ObjectAnimator? = null
    private var mScaleAnimatorX: ObjectAnimator? = null
    private var mScaleAnimatorY: ObjectAnimator? = null
    private var mAnimatorSet: AnimatorSet? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mStartBtn = findViewById(R.id.start_btn)
        mEndBtn = findViewById(R.id.end_btn)
        mCancelBtn = findViewById(R.id.cancel_btn)
        mPauseBtn = findViewById(R.id.pause_btn)
        mResumeBtn = findViewById(R.id.resume_btn)
        mReverseBtn = findViewById(R.id.reverse_btn)
        mProgressBar = findViewById(R.id.progress_bar)
        mMyLove = findViewById(R.id.my_love)
        mMyLoveIdleBasha = findViewById(R.id.my_love_basha)
        mMyLoveIdle = findViewById(R.id.my_love_idle)
        initValueAnimator()
        initObjectAnimator()
        initOnClickListener()
    }

    private fun initTransationDrawable() {
        val drawableA = mMyLoveIdleBasha?.drawable
        val drawableB = mMyLoveIdle?.drawable
        val transitionDrawable = TransitionDrawable(arrayOf(drawableA, drawableB))
        transitionDrawable.isCrossFadeEnabled = true
        mMyLoveIdle?.setImageDrawable(transitionDrawable)
        transitionDrawable.startTransition(3000)
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun initObjectAnimator() {
        mAlphaAnimator = ObjectAnimator.ofFloat(mMyLove, "alpha", 0.2f, 1f)
        mScaleAnimatorX = ObjectAnimator.ofFloat(mMyLove, "scaleX", 1f, 1.1f)
        mScaleAnimatorY = ObjectAnimator.ofFloat(mMyLove, "scaleY", 1f, 1.1f)
        mAnimatorSet = AnimatorSet()
        mAnimatorSet?.duration = 3000
        mAnimatorSet?.playTogether(mScaleAnimatorX, mScaleAnimatorY, mAlphaAnimator)
    }

    @SuppressLint("NewApi")
    private fun initOnClickListener() {
        mStartBtn?.setOnClickListener {
            mProgressAnimator?.start()
            mColorAnimator?.start()
            mAnimatorSet?.start()
            initTransationDrawable()
        }
        mEndBtn?.setOnClickListener { mProgressAnimator?.end() }
        mCancelBtn?.setOnClickListener {
            mProgressAnimator?.cancel()
            Toast.makeText(this@MainActivity, "动画取消", Toast.LENGTH_SHORT).show()
        }
        mPauseBtn?.setOnClickListener {
            mProgressAnimator?.pause()
            Toast.makeText(this@MainActivity, "动画暂停", Toast.LENGTH_SHORT).show()
        }
        mResumeBtn?.setOnClickListener {
            mProgressAnimator?.resume()
            Toast.makeText(this@MainActivity, "动画继续", Toast.LENGTH_SHORT).show()
        }
        mReverseBtn?.setOnClickListener {
            mProgressAnimator?.reverse()
            Toast.makeText(this@MainActivity, "动画重复", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initValueAnimator() {
        mProgressAnimator = ValueAnimator.ofInt(0, 100)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mColorAnimator = ValueAnimator.ofArgb(-0x1, -0x10000, -0xffff01, -0xff0100)
        }
        mColorAnimator?.duration = 3000
        mProgressAnimator?.setDuration(3000)
        mProgressAnimator?.addUpdateListener(AnimatorUpdateListener { animation ->
            Log.i(TAG, "onAnimationUpdate :: " + animation.animatedValue)
            mProgressBar?.progress = (animation.animatedValue as Int)
        })
        mColorAnimator?.addUpdateListener { animation -> mStartBtn?.setBackgroundColor((animation.animatedValue as Int)) }
        mProgressAnimator?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Toast.makeText(this@MainActivity, "动画开始", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationEnd(animation: Animator) {
                Toast.makeText(this@MainActivity, "动画结束", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationCancel(animation: Animator) {
                Toast.makeText(this@MainActivity, "动画取消", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationRepeat(animation: Animator) {
                Toast.makeText(this@MainActivity, "动画重复", Toast.LENGTH_SHORT).show()
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}