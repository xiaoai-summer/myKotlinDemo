package com.example.myapplication

import android.animation.*
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint
import android.graphics.drawable.TransitionDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var mTransBtn: Button? = null
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
    private val mHandler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTransBtn = findViewById(R.id.trans_btn)
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

        initXmlObjectAnimator()
        initTransationAnimation()
    }

    private fun initTransationAnimation() {
        val translateAnimation = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 1.5f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f)
        translateAnimation.fillAfter = true
        mHandler.postDelayed(Runnable { mTransBtn?.startAnimation(translateAnimation) }, 2000)
    }

    private fun initXmlObjectAnimator() {
        //加载xml中属性动画
        val animatorset = AnimatorInflater.loadAnimator(this, R.animator.animator_set) as AnimatorSet
        val animator = AnimatorInflater.loadAnimator(this, R.animator.animator_alpha)

        //使用组合属性动画
        animatorset.setTarget(mMyLove)
        animatorset.start()

        //使用属性动画
        animator.setTarget(mMyLove)
        animator.start()
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
        mTransBtn?.setOnClickListener {
            Toast.makeText(this@MainActivity, getString(R.string.click_trans_btn), Toast.LENGTH_SHORT).show()
        }
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

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacksAndMessages(null);
    }
}