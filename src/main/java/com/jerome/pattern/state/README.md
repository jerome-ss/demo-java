## 状态模式
当一个对象的内部状态改变时允许改变其行为，这个对象看起来像是改变了其类。  
**何时使用**：代码中包含大量与对象状态有关的条件语句。  

例子：  
实体是电梯，这个大家一定不陌生。我们知道电梯主要有4种状态：电梯门关闭、电梯门打开、电梯上下运载、电梯停止。而且我们知道，电梯在门打开的时候，只能是关闭电梯门，不能是其他的任何操作。在学习状态模式之前，如果我们要编写这个逻辑，一定是长篇累读地 if … else … 。而且逻辑混乱，很难维护。当然，这里你可以使用 if … else …，因为电梯的这些状态基本是稳定的，不会有什么变动。而如果你的需求里，状态会不断更新，而你之前使用 if … else … 埋下的患根这时就会让你苦不堪言。  

以下我们使用状态模式进行优化：  
所有状态要有一个公共的接口LiftState：
```
public abstract class LiftState {

    protected Context context;

    public void setContext(Context _context) {
        this.context = _context;
    }

    public abstract void open();

    public abstract void close();

    public abstract void run();

    public abstract void stop();
}
```  
Context 对象的作用是去调节状态的变化，它就是电梯，你的电梯状态肯定是针对电梯来说的，所以组合一个 Context 一点也不奇怪。  

LiftState 的实现类就拿 StoppingState 类来说吧，其他的实现跟这个类很像。 
```
public class StoppingState extends LiftState {

    @Override
    public void close() {
        // do nothing;
    }

    @Override
    public void open() {
        super.context.setLiftState(Context.openningState);
        super.context.getLiftState().open();
    }

    @Override
    public void run() {
        super.context.setLiftState(Context.runningState);
        super.context.getLiftState().run();
    }

    @Override
    public void stop() {
        System.out.println("电梯停止了...");
    }
}
```  
在停下来的时候，我们不能让电梯关闭，因为它原本就是关闭的，我这里做法是不处理，当然你可以选择抛出异常。当电梯停下来的时候，电梯是可以打开的，所以在 open() 方法里可以将电梯的状态标识为打开状态；当然，也可以标识为运载状态。而究竟会转换成哪一种状态，就要依据实际乘客的使用情况了。  
下面看看我们的关键实体 Context 是怎么实现的。   
```
public class Context {

    // 定义出所有的电梯状态
    public final static OpenningState openningState = new OpenningState();
    public final static ClosingState closeingState = new ClosingState();
    public final static RunningState runningState = new RunningState();
    public final static StoppingState stoppingState = new StoppingState();

    // 定一个当前电梯状态
    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        // 把当前的环境通知到各个实现类中
        this.liftState.setContext(this);
    }

    public void open() {
        this.liftState.open();
    }

    public void close() {
        this.liftState.close();
    }

    public void run() {
        this.liftState.run();
    }

    public void stop() {
        this.liftState.stop();
    }
}
```  
Context 组合了所有状态，这一点不奇怪，因为它是电梯嘛。在上面的代码中，你们可能很迷惑，这里 Context 都是去调用 LiftState 接口的相应方法，哪里体现了状态的转移呢？其实状态转移的逻辑是在各自的状态里面进行的，就像上面的 StoppingState 类。如果调用了 StoppingState 类，是不是说当前 Context 里的状态是 StoppingState 呢？而它却在 open() 方法里将 Context 的状态转换成了 OpenningState 。这样就完成了状态的转换了。Context 类的作用我想只是去触发状态的转换。  
参考：  
Java 设计模式——状态模式 http://blog.csdn.net/lemon_tree12138/article/details/51596556  
状态模式 | 菜鸟教程 http://www.runoob.com/design-pattern/state-pattern.html