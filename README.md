# ConstraintDemo
ConstraintLayout1.1 简单实现展开菜单
Constraintlayout 1.1新增了很多实用功能
> 今天用constraintset，constraintCircle做个展开菜单
![GIF.gif](https://upload-images.jianshu.io/upload_images/6456519-adcd774fbe0781f9.gif?imageMogr2/auto-orient/strip)

1. constraintset可以方便的实现动画，转换不同状态约束到constraintlayout
2. constraintCircle 是可以一个控件以一定半径围绕另一个控件布局
![WV5F2EJAF5E%CG1_A`Q%Q04.png](https://upload-images.jianshu.io/upload_images/6456519-71c51290694c9bd0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 先看看constraintset官方的用法示例
总结用法就是：new  ConstraintSet ，调用克隆记录起、始状态下布局的约束属性。然后调用applyTo生效。如果要动画过程就在applyto前调用TransitionManager.beginDelayedTransition(mConstraintLayout);
```
public class MainActivity extends AppCompatActivity {
    ConstraintSet mConstraintSet1 = new ConstraintSet(); // create a Constraint Set
    ConstraintSet mConstraintSet2 = new ConstraintSet(); // create a Constraint Set
    ConstraintLayout mConstraintLayout; // cache the ConstraintLayout
    boolean mOld = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;
        mConstraintSet2.clone(context, R.layout.state2); // get constraints from layout
        setContentView(R.layout.state1);
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.activity_main);
        mConstraintSet1.clone(mConstraintLayout); // get constraints from ConstraintSet
    }

    public void foo(View view) {
        TransitionManager.beginDelayedTransition(mConstraintLayout);
        if (mOld = !mOld) {
            mConstraintSet1.applyTo(mConstraintLayout); // set new constraints
        }  else {
            mConstraintSet2.applyTo(mConstraintLayout); // set new constraints
        }
    }
}
```

### 实现
[github github](https://github.com/While1true/ConstraintDemo)
> 写两套布局，一个是折叠状态，一个是展开状态,保证相同控件id一致
>用constraintSet记录两套布局的约束，调用applyTo生效
```
  final ConstraintLayout constraintLayout = findViewById(R.id.constraint);
        final ConstraintSet constraintSet = new ConstraintSet();
        final ConstraintSet constraintSeto = new ConstraintSet();
        constraintSet.clone(this, R.layout.activity_main2);
        constraintSeto.clone(constraintLayout);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(constraintLayout, new ChangeBounds());
                }
                if (!change) {
                    constraintSet.applyTo(constraintLayout);
                    change = true;
                } else {
                    change = false;
                    constraintSeto.applyTo(constraintLayout);
                }
            }
        });
```
