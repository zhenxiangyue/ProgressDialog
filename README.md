# ProgressDialog
弹出ProgressDialog：通过异步线程请求数据来更新主线程
1. 先创建ProgressDialog
2. 在主线程中创建我的子线程 MyThread
3. 点击按钮时弹出提示框，并启动异步线程【请求服务器耗时操作全部放在这里】，执行完后
   用handler.sendEmptyMessage(0)通知主线程
4. hanlder接收通知后更新主线程。



