# EditText修改软键盘右下角按钮，并隐藏软键盘

## 单行EditText
在EditText的xml设置：  
android:inputType="text"  
android:singleLine="true"  
android:imeOptions="actionSend"        

## 多行EditText
在EditText的xml设置：  
android:inputType="text"

然后在Java代码设置：                                                                               
editText.setHorizontallyScrolling(false);   
editText.setMaxLines(Integer.MAX_VALUE);

## 软键盘
这样子EditText软键盘监听就生效了

把EditText的imeOptions属性设置成不同的值，Enter键可以显示不同的文字或图案：   
actionNone ： 回车键，按下后光标到下一行   
actionGo ： Go  
actionSearch ： 一个放大镜  
actionSend ： Send  
actionNext ： Next  
actionDone ： Done，隐藏软键盘，即使不是最后一个文本输入框   

## EditText多行内容滑动惯性       
TODO        
   
   
    
