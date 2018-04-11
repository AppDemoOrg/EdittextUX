# EdittextUX


如果对行数没有要求，
直接在EditText的xml设置android:singleLine="true"

如果需要多行输入的，
那么EditText在xml设置android:inputType="text"

然后在Java代码里面写                                                                                 editText.setHorizontallyScrolling(false);
editText.setMaxLines(Integer.MAX_VALUE);

这样子EditText的imeOptions设置和软键盘监听就可以生效了
